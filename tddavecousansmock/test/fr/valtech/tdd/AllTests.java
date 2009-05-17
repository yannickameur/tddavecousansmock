package fr.valtech.tdd;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.valtech.tdd.business.ConsulterDisponibiliteChambreBusinessImplDependencyTest;
import fr.valtech.tdd.dao.ChambreDaoImplTest;
import fr.valtech.tdd.enums.EnumCategorie;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

@RunWith(Suite.class)
@SuiteClasses(value = {
// les tests de DAO
		ChambreDaoImplTest.class,//
		// les tests business
		ConsulterDisponibiliteChambreBusinessImplDependencyTest.class //
})
public class AllTests {
	private static final String PERSISTENCE_UNIT_NAME = "chambres";

	// private EntityManagerFactory factory;

	@BeforeClass
	public static void setUp() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// on demarre une nouvelle transaction
		em.getTransaction().begin();

		// on verifie que la base est vide
		Query q = em.createQuery("select c from Chambre c");

		// est elle vide ?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// oui, alors on cree de nouveaux elements
		if (createNewEntries) {
			for (int i = 1; i < 5; i++) {
				Chambre chambre = new Chambre();
				chambre.setNumero(100 + i);
				if (i % 2 == 0) {
					chambre.setCategorie(EnumCategorie.STANDARD);
				} else {
					chambre.setCategorie(EnumCategorie.SUPERIEURE);
				}
				chambre.setDescription("Chambre standard pour couple");
				chambre.setCapacite(i);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(2009, 5, 30, 0, 0, 0);
				Date nuitee = calendar.getTime();

				// on reserve les chambres 101 et 102 le 30 mai 2009
				if (i == 1) {
					Reservation reservationChambre101Le30Mai2009 = new Reservation(
							nuitee);
					chambre.getReservations().add(
							reservationChambre101Le30Mai2009);
				}
				if (i == 2) {
					Reservation reservationChambre101Le30Mai2009 = new Reservation(
							nuitee);
					chambre.getReservations().add(
							reservationChambre101Le30Mai2009);
				}
				// si bien que les chambres 103 et 104 sont les seules vides le
				// 30
				// Mai 2009...

				em.persist(chambre);
			}

			// on committe pour sauvegarder
			em.getTransaction().commit();

			// on ferme l'EntityManager
			em.close();

		}

	}

}
