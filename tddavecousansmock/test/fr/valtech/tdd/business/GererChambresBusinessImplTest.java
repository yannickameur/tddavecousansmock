package fr.valtech.tdd.business;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import fr.valtech.tdd.helper.DateHelper;

public class GererChambresBusinessImplTest {

	@Test
	public void consulterDisponibiliteChambreTest() {
		GererChambresBusinessImpl gererChambresBusiness = new GererChambresBusinessImpl();
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
	}

}
