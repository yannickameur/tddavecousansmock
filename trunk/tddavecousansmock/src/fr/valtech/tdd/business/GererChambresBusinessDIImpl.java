package fr.valtech.tdd.business;

import java.util.Date;
import java.util.List;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class GererChambresBusinessDIImpl implements GererChambresBusiness {

	private ChambreDao chambreDao;

	public void setChambreDao(ChambreDao chambreDao) {
		this.chambreDao = chambreDao;
	}

	public boolean consulterDisponibiliteChambre(int capacite, Date nuitee) {
		List<Chambre> chambres = chambreDao.findAll();
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
