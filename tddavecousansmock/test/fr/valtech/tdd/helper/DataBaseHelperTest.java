package fr.valtech.tdd.helper;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import fr.valtech.tdd.business.GererChambresBusinessImpl;

public class DataBaseHelperTest {
	@Test
	public void initBase() {
		boolean resultat = DataBaseHelper.initBase();
		GererChambresBusinessImpl gererChambresBusiness = new GererChambresBusinessImpl();
		assertTrue(resultat);
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		assertTrue(dispoChambre2Personnes30Mai2009);
	}
}
