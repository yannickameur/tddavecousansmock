package fr.valtech.tdd.dao;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.valtech.tdd.enums.EnumCategorie;
import fr.valtech.tdd.model.Chambre;

public class ChambreDaoImplTest {

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
			for (int i = 1; i < 5; i++) {
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
	public void findByIdTest() {
		ChambreDao chambreDao = new ChambreDaoImpl();
		int id = 4;
		Chambre chambre = chambreDao.findById(id);
		Assert.assertNotNull(chambre);
		Assert.assertEquals(4, chambre.getId());
		Assert.assertEquals(104, chambre.getNumero());
		Assert.assertEquals("Chambre standard pour couple", chambre
				.getDescription());
		Assert.assertEquals(2, chambre.getCapacite());
		Assert.assertEquals(EnumCategorie.STANDARD, chambre.getCategorie());

	}

	@After
	public void tearDown() {
	}
}
