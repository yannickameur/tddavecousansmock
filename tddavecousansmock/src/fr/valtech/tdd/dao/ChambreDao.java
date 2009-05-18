package fr.valtech.tdd.dao;

import java.util.List;

import fr.valtech.tdd.model.Chambre;

public interface ChambreDao {

	Chambre findById(int id);

	List<Chambre> findByCapacite(int capacite);

	List<Chambre> findAll();

}
