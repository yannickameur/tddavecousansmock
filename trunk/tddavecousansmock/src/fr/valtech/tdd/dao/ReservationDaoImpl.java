package fr.valtech.tdd.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class ReservationDaoImpl implements ReservationDao {

	private static final String PERSISTENCE_UNIT_NAME = "chambres";
	private EntityManagerFactory factory;

	@Override
	public List<Chambre> chercherChambresLibres(Date nuitee) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		// Query namedQuery = em.createQuery("select r.id from Reservation ");
		// List<Integer> reservationsId = namedQuery.getResultList();
		// .createQuery("select c from Reservation r JOIN r.chambre as c where r.nuitee=:nuitee ");
		// .createQuery("select c from Chambre c where c.reservations.id in (select r.id from Reservation r where r.nuitee= :nuitee)");
		// .createQuery("select c from Chambre c where c.reservations.nuitee= :nuitee");
		// namedQuery.setParameter("nuitee", nuitee);
		Query namedQuery2 = em.createQuery("select c from Chambre c");

		// namedQuery2.setParameter("reservationsId", reservationsId);
		//		

		Reservation reservationNuiteeDu30Mai2009 = new Reservation(nuitee);
		List<Chambre> chambresLibresPourCetteNuitee = new ArrayList<Chambre>();
		List<Chambre> chambres = namedQuery2.getResultList();
		for (Chambre chambre : chambres) {
			if (!chambre.getReservations().contains(
					reservationNuiteeDu30Mai2009)) {
				chambresLibresPourCetteNuitee.add(chambre);
			}
		}

		em.getTransaction().rollback();
		em.close();
		return chambresLibresPourCetteNuitee;

	}
}
