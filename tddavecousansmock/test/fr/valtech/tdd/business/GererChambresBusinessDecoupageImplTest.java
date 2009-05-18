package fr.valtech.tdd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;

public class GererChambresBusinessDecoupageImplTest {

	@Test
	public void consulterDisponibiliteChambre() {
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		GererChambresBusinessDecoupageImpl gererChambresBusiness = new GererChambresBusinessDecoupageImpl();
		List<Chambre> chambres = new ArrayList<Chambre>();
		chambres.add(new Chambre());

		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambreSansDao(chambres, capacite,
						nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
	}
}
