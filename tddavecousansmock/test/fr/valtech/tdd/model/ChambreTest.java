package fr.valtech.tdd.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.valtech.tdd.enums.EnumCategorie;

/**
 * 
 * @author anthony Classe de test du modele chambre : operations CRUD
 * 
 */
public class ChambreTest {

	private static final String PERSISTENCE_UNIT_NAME = "chambres";
	private EntityManagerFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// Read the existing entries
		Query q = em.createQuery("select c from Chambre c");
		// Persons should be empty

		// Do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);
			for (int i = 0; i < 5; i++) {
				Chambre chambre = new Chambre();
				chambre.setNumero(100 + i);
				if (i % 2 == 0) {
					chambre.setCategorie(EnumCategorie.STANDARD);
				} else {
					chambre.setCategorie(EnumCategorie.SUPERIEURE);
				}
				chambre.setDescription("Chambre standard pour couple");
				chambre.setCapacite(2);
				em.persist(chambre);
			}
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

	}

	@Test
	public void verifieNombreChambresTest() {
		// Now lets check the database and see if the created entries are there
		// Create a fresh, new EntityManager
		EntityManager em = factory.createEntityManager();

		// Perform a simple query for all the Message entities
		Query q = em.createQuery("select c from Chambre c");
		// on a bien 5 chambres en base
		assertTrue(q.getResultList().size() == 5);

		em.close();
	}

	@Test
	public void verifieChambresSontDifferentes() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select c from Chambre c");
		List<Chambre> chambresEnBase = q.getResultList();
		Chambre chambrePrecedente = new Chambre();
		for (Chambre chambre : chambresEnBase) {
			assertFalse(chambre.equals(chambrePrecedente));
			chambrePrecedente = chambre;
		}
	}

	@Test
	public void chambreBienRemplie() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("select c from Chambre c order by c.numero");
		List<Chambre> chambresEnBase = q.getResultList();
		Chambre premiereChambre = chambresEnBase.get(0);
		Assert.assertEquals(2, premiereChambre.getCapacite());
		Assert.assertEquals(100, premiereChambre.getNumero());
		Assert.assertEquals("Chambre standard pour couple", premiereChambre
				.getDescription());
		Assert.assertEquals(EnumCategorie.STANDARD, premiereChambre
				.getCategorie());
		Assert.assertEquals(1, premiereChambre.getId());

	}

	@After
	public void tearDown() throws Exception {
	}

}
