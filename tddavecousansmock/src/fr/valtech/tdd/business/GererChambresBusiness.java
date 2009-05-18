package fr.valtech.tdd.business;

import java.util.Date;

public interface GererChambresBusiness {
	boolean consulterDisponibiliteChambre(int capacite, Date nuitee);
}
