package fr.valtech.tdd.business;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.valtech.tdd.helper.DataBaseHelper;
import fr.valtech.tdd.helper.DateHelper;

public class GererChambresBusinessImplTest {

	@Before
	public void setUp() {
		DataBaseHelper.initBase();

	}

	@Test
	public void consulterDisponibiliteChambreTest() {
		GererChambresBusinessImpl gererChambresBusiness = new GererChambresBusinessImpl();
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 2;
		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertFalse(dispoChambre2Personnes30Mai2009);
	}

	@Test
	public void consulterDisponibiliteChambreTest__dispo_ok() {
		GererChambresBusinessImpl gererChambresBusiness = new GererChambresBusinessImpl();
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
	}

	@Test(expected = IllegalArgumentException.class)
	public void consulterDisponibiliteChambreTest__nuitee_invalide() {
		GererChambresBusinessImpl gererChambresBusiness = new GererChambresBusinessImpl();
		Date nuitee = null;
		int capacite = 2;
		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
	}

}
