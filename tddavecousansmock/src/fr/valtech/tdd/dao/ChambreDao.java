package fr.valtech.tdd.dao;

import java.util.List;

import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public interface ChambreDao {

	Chambre findById(int id);

	int reserverChambre(Reservation reservation);

	List<Chambre> findAll();

}
