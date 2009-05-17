package fr.valtech.tdd.dao;

import java.util.Date;
import java.util.List;

import fr.valtech.tdd.model.Chambre;

public interface ReservationDao {

	List<Chambre> chercherChambresLibres(Date nuitee);

}
