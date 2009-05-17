package fr.valtech.tdd.business;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class ConsulterDisponibiliteChambreBusinessImplTest {

	@Test
	public void consulterDisponibiliteChambreTest() {
		ConsulterDisponibiliteChambreBusinessImpl consulterDisponibiliteChambreBusinessImpl = new ConsulterDisponibiliteChambreBusinessImpl();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 5, 30);
		Date nuitee = calendar.getTime();
		int capacite = 2;
		boolean dispoChambre2Personnes30Mai2009 = consulterDisponibiliteChambreBusinessImpl
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
	}

}
