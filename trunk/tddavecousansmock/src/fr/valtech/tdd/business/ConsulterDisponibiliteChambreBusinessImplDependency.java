package fr.valtech.tdd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.dao.ChambreDaoImpl;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class ConsulterDisponibiliteChambreBusinessImplDependency implements
		ConsulterDisponibiliteChambreBusiness {

	public boolean consulterDisponibiliteChambre(int capacite, Date nuitee) {
		ChambreDao chambreDaoImpl = new ChambreDaoImpl();
		List<Chambre> chambresAvecBonneCapacite = chambreDaoImpl
				.findByCapacite(capacite);
		List<Chambre> chambresOK = new ArrayList<Chambre>();
		for (Chambre chambre : chambresAvecBonneCapacite) {
			List<Reservation> reservations = chambre.getReservations();
			if (reservations.size() == 0) {
				chambresOK.add(chambre);
			} else {
				for (Reservation reservation : reservations) {
					if (nuitee.equals(reservation.getNuitee())) {
						chambresOK.add(chambre);
						break;
					}
				}
			}

		}

		return chambresOK.size() > 0 ? true : false;

	}

}
