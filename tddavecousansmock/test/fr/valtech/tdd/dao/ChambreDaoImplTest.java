package fr.valtech.tdd.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import fr.valtech.tdd.helper.DataBaseHelper;
import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.EnumCategorie;
import fr.valtech.tdd.model.Reservation;

/**
 * 
 * Attention ! il faut initialiser la base !!
 */
public class ChambreDaoImplTest {
	@Test
	public void factory() {
		new ChambreDaoImpl();
		new ChambreDaoImpl();
	}

	@Test
	public void findAll() {
		ChambreDao chambreDao = new ChambreDaoImpl();
		List<Chambre> chambres = chambreDao.findAll();
		Assert.assertEquals(4, chambres.size());
	}

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
	public void reserverChambreTest() {
		DataBaseHelper.initBase();
		ChambreDao chambreDao = new ChambreDaoImpl();

		Date nuitee = DateHelper.createDate(2009, 5, 30);
		Reservation reservation = new Reservation(nuitee);
		reservation.setChambre(new Chambre(6));
		int reserverChambre = chambreDao.reserverChambre(reservation);
		Assert.assertEquals(6, reserverChambre);
		Assert.assertEquals(chambreDao.findById(6).getReservations().get(0),
				reservation);

	}

	@After
	public void tearDown() {
	}
}
