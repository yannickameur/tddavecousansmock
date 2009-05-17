package fr.valtech.tdd.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import fr.valtech.tdd.dao.ChambreDao;
import fr.valtech.tdd.model.Chambre;

public class ConsulterDisponibiliteChambreBusinessImplEMTest {

	@Test
	public void consulterDisponibiliteChambreTest() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2009, 5, 30);
		Date nuitee = calendar.getTime();
		int capacite = 3;
		ConsulterDisponibiliteChambreBusinessImplEM consulterDisponibiliteChambreBusinessImpl = new ConsulterDisponibiliteChambreBusinessImplEM();
		ChambreDao chambreDao = EasyMock.createMock(ChambreDao.class);
		List<Chambre> chambresOK = new ArrayList<Chambre>();
		chambresOK.add(new Chambre());
		EasyMock.expect(chambreDao.findByCapacite(capacite)).andReturn(
				chambresOK);
		EasyMock.replay(chambreDao);
		consulterDisponibiliteChambreBusinessImpl.setChambreDao(chambreDao);

		boolean dispoChambre2Personnes30Mai2009 = consulterDisponibiliteChambreBusinessImpl
				.consulterDisponibiliteChambre(capacite, nuitee);
		Assert.assertTrue(dispoChambre2Personnes30Mai2009);
		EasyMock.verify(chambreDao);
	}

}
