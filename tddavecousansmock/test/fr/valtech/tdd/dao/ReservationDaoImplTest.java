package fr.valtech.tdd.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.valtech.tdd.enums.EnumCategorie;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class ReservationDaoImplTest {

	private static final String PERSISTENCE_UNIT_NAME = "chambres";
	private EntityManagerFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();
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
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(2009, 5, 30, 0, 0, 0);
			Date nuitee = calendar.getTime();

			// on reserve les chambres 101 et 102 le 30 mai 2009
			if (i == 1) {
				Reservation reservationChambre101Le30Mai2009 = new Reservation(
						nuitee);
				chambre.getReservations().add(reservationChambre101Le30Mai2009);
			}
			if (i == 2) {
				Reservation reservationChambre101Le30Mai2009 = new Reservation(
						nuitee);
				chambre.getReservations().add(reservationChambre101Le30Mai2009);
			}
			// si bien que les chambres 3 et 4 sont les seules vides le 30 Mai
			// 2009...

			em.persist(chambre);
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

	}

	@Test
	public void chercherChambresLibres() {
		ReservationDao reservationDao = new ReservationDaoImpl();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 5, 30, 0, 0, 0);
		Date nuitee = calendar.getTime();
		List<Chambre> chambresLibresLe30Mai2009 = reservationDao
				.chercherChambresLibres(nuitee);

		Assert.assertEquals(2, chambresLibresLe30Mai2009.size());
		Assert.assertEquals(new Chambre(3, 103), chambresLibresLe30Mai2009
				.get(0));
		Assert.assertEquals(new Chambre(4, 104), chambresLibresLe30Mai2009
				.get(1));

	}
}
