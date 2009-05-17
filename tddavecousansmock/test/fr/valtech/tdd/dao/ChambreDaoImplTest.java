package fr.valtech.tdd.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import fr.valtech.tdd.enums.EnumCategorie;
import fr.valtech.tdd.model.Chambre;

public class ChambreDaoImplTest {

	@Test
	public void findByIdTest() {
		ChambreDao chambreDao = new ChambreDaoImpl();
		int id = 3;
		Chambre chambre = chambreDao.findById(id);
		Assert.assertNotNull(chambre);
		Assert.assertEquals(3, chambre.getId());
		Assert.assertEquals(102, chambre.getNumero());
		Assert.assertEquals("Chambre standard pour couple", chambre
				.getDescription());
		Assert.assertEquals(2, chambre.getCapacite());
		Assert.assertEquals(EnumCategorie.STANDARD, chambre.getCategorie());

	}

	@Test
	public void findByCapaciteTest() {
		ChambreDao chambreDao = new ChambreDaoImpl();
		int capacite = 2;
		List<Chambre> chambres = chambreDao.findByCapacite(capacite);
		Assert.assertNotNull(chambres);
		Assert.assertEquals(3, chambres.get(0).getId());
		Assert.assertEquals(102, chambres.get(0).getNumero());
		Assert.assertEquals(2, chambres.get(0).getCapacite());

	}

	@After
	public void tearDown() {
	}
}
