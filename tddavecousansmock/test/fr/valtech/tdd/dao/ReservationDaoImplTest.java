package fr.valtech.tdd.dao;

import org.junit.Assert;
import org.junit.Test;

public class ReservationDaoImplTest {

	@Test
	public void insererReservation() {
		ReservationDao reservationDao = new ReservationDaoImpl();
		Assert.assertEquals(0, reservationDao.insererReservation(null, null));
	}
}
