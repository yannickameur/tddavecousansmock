package fr.valtech.tdd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;

public class GererChambresBusinessDIAnonymeImplTest {

	@Test
	public void consulterDisponibiliteChambre() {
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		int capacite = 3;
		GererChambresBusinessDIImpl gererChambresBusiness = new GererChambresBusinessDIImpl();
		final List<Chambre> chambres = new ArrayList<Chambre>();
		chambres.add(new Chambre());
		ChambreDao chambreDao = new ChambreDao() {

			@Override
			public List<Chambre> findAll() {
				return chambres;
			}

			@Override
			public List<Chambre> findByCapacite(int capacite) {
				return null;
			}

			@Override
			public Chambre findById(int id) {
				return null;
			}

		};
		gererChambresBusiness.setChambreDao(chambreDao);

		boolean dispoChambre2Personnes30Mai2009 = gererChambresBusiness
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
	}
}
