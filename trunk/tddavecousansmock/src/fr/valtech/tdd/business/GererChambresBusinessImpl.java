package fr.valtech.tdd.business;

import java.util.Date;
import java.util.List;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.dao.ChambreDaoImpl;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class GererChambresBusinessImpl implements GererChambresBusiness {

	/**
	 * si capacite inexistante return true <br>
	 * si nuitee == null return true => BUG
	 */
	public boolean consulterDisponibiliteChambre(int capacite, Date nuitee) {
		ChambreDao chambreDaoImpl = new ChambreDaoImpl();
		List<Chambre> chambres = chambreDaoImpl.findAll();
		for (Chambre chambre : chambres) {
			if (chambre.getCapacite() == capacite) {
				List<Reservation> reservations = chambre.getReservations();
				for (Reservation reservation : reservations) {
					if (reservation.getNuitee().equals(nuitee)) {
						return false;
					}
				}
			}
		}
		return true;

	}
}
