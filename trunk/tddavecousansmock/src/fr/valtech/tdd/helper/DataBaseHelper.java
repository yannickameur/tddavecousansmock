package fr.valtech.tdd.helper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.EnumCategorie;
import fr.valtech.tdd.model.Reservation;

public class DataBaseHelper {
	// la capacite 2 avec une nuit reservee le 30 mai 2009 est la chambre id 4
	public static boolean initBase() {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("chambres");
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
				Date nuitee = DateHelper.createDate(2009, 5, 30);

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
		return true;
	}
}
