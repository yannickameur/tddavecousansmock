package fr.valtech.tdd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;

public class GererChambresBusinessDIMockImplTest {

	@Test
	public void consulterDisponibiliteChambre() {
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		GererChambresBusinessDIImpl gererChambresBusiness = new GererChambresBusinessDIImpl();
		ChambreDao chambreDao = EasyMock.createMock(ChambreDao.class);
		List<Chambre> chambres = new ArrayList<Chambre>();
		chambres.add(new Chambre());
		EasyMock.expect(chambreDao.findAll()).andReturn(chambres);
		EasyMock.replay(chambreDao);
		gererChambresBusiness.setChambreDao(chambreDao);

		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
		EasyMock.verify(chambreDao);
	}

}
