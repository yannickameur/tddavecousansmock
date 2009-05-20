package fr.valtech.tdd.business;

import java.util.Date;
import java.util.List;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class GererChambresBusinessImpl2 implements GererChambresBusiness {

	private ChambreDao chambreDao;
	private List<Chambre> chambres;

	@Override
	public boolean consulterDisponibiliteChambre(int capacite, Date nuitee) {
		isNullNuitee(nuitee);
		if (chambres == null) {

			chambres = chambreDao.findAll();
		}
		return verifierDispoChambre(capacite, nuitee, chambres);
	}

	boolean verifierDispoChambre(int capacite, Date nuitee,
			List<Chambre> chambres) {
		loopchambe: for (Chambre chambre : chambres) {
			if (chambre.getCapacite() == capacite) {
				List<Reservation> reservations = chambre.getReservations();
				for (Reservation reservation : reservations) {
					if (nuitee.equals(reservation.getNuitee())) {
						continue loopchambe;
					}
				}
				return true;
			}
		}
		return false;
	}

	void isNullNuitee(Date nuitee) {
		if (nuitee == null) {
			throw new IllegalArgumentException();
		}
	}

	public void setChambreDao(ChambreDao chambreDao) {
		this.chambreDao = chambreDao;
	}

}
