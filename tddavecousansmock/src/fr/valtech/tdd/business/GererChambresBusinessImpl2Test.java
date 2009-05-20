package fr.valtech.tdd.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;
import fr.valtech.tdd.model.Reservation;

public class GererChambresBusinessImpl2Test {

	@Before
	public void setUp() throws Exception {
		// TODO DBHELPER
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validerUtilisationCache() {
		GererChambresBusinessImpl2 chambresBusiness = new GererChambresBusinessImpl2();
		ChambreDao chambreDao = EasyMock.createMock(ChambreDao.class);
		chambresBusiness.setChambreDao(chambreDao);
		EasyMock.expect(chambreDao.findAll()).andReturn(
				new ArrayList<Chambre>());

		EasyMock.replay(chambreDao);
		assertFalse(chambresBusiness.consulterDisponibiliteChambre(3,
				new Date()));
		assertFalse(chambresBusiness.consulterDisponibiliteChambre(3,
				new Date()));
		EasyMock.verify(chambreDao);

	}

	@Test
	public final void testConsulterDisponibiliteChambre() {
		GererChambresBusinessImpl2 chambresBusiness = new GererChambresBusinessImpl2();
		List<Chambre> chambres = new ArrayList<Chambre>();
		Chambre chambre = new Chambre();
		chambre.setCapacite(3);
		chambre.getReservations().add(
				new Reservation(DateHelper.createDate(2009, 5, 30)));
		chambres.add(chambre);
		ChambreDao chambreDao = EasyMock.createMock(ChambreDao.class);
		EasyMock.expect(chambreDao.findAll()).andReturn(chambres);
		EasyMock.replay(chambreDao);

		chambresBusiness.setChambreDao(chambreDao);
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		assertFalse(chambresBusiness.consulterDisponibiliteChambre(3, nuitee));
	}

	@Test
	public final void testConsulterDisponibiliteChambreOK() {
		GererChambresBusinessImpl2 chambresBusiness = new GererChambresBusinessImpl2();
		ChambreDao chambreDao = new ChambreDao() {

			@Override
			public List<Chambre> findAll() {
				List<Chambre> chambres = new ArrayList<Chambre>();
				Chambre chambre = new Chambre();
				chambre.setCapacite(3);
				chambre.getReservations().add(
						new Reservation(DateHelper.createDate(2009, 5, 28)));
				chambres.add(chambre);
				return chambres;
			}

			@Override
			public Chambre findById(int id) {
				return null;
			}

			@Override
			public int reserverChambre(Reservation reservation) {
				return 0;
			}

		};
		chambresBusiness.setChambreDao(chambreDao);
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		assertTrue(chambresBusiness.consulterDisponibiliteChambre(3, nuitee));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testConsulterDisponibiliteChambreNull() {
		GererChambresBusiness chambresBusiness = new GererChambresBusinessImpl2();

		assertTrue(chambresBusiness.consulterDisponibiliteChambre(3, null));
	}

}
