package fr.valtech.tdd.dao;

import java.util.Date;

import fr.valtech.tdd.model.Chambre;

public interface ReservationDao {

	int insererReservation(Chambre chambre, Date nuitee);

}
