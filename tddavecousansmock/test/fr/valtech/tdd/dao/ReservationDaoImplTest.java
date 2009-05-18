package fr.valtech.tdd.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import fr.valtech.tdd.helper.DateHelper;
import fr.valtech.tdd.model.Chambre;

public class ReservationDaoImplTest {

	@Test
	public void chercherChambresLibres() {
		ReservationDao reservationDao = new ReservationDaoImpl();
		Date nuitee = DateHelper.createDate(2009, 5, 30);
		List<Chambre> chambresLibresLe30Mai2009 = reservationDao
				.chercherChambresLibres(nuitee);

		Assert.assertEquals(2, chambresLibresLe30Mai2009.size());
		Assert.assertEquals(new Chambre(3, 103), chambresLibresLe30Mai2009
				.get(0));
		Assert.assertEquals(new Chambre(4, 104), chambresLibresLe30Mai2009
				.get(1));

	}
}
