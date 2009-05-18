package fr.valtech.tdd.business;

import java.util.Date;
import java.util.List;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class GererChambresBusinessWithCacheImpl implements GererChambresBusiness {

	private ChambreDao chambreDao;
	private List<Chambre> chambres;

	public void setChambreDao(ChambreDao chambreDao) {
		this.chambreDao = chambreDao;
	}

	/**
	 * si nuitee == null return true => BUG <br>
	 * si capacite inexistante return true
	 */
	public boolean consulterDisponibiliteChambre(int capacite, Date nuitee) {
		if (chambres == null) {
			chambres = chambreDao.findAll();
		}
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
