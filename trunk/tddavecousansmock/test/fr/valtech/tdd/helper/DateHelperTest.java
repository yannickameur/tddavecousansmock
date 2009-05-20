package fr.valtech.tdd.helper;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

public class DateHelperTest {

	@Test
	public void createDate() {
		Date date = DateHelper.createDate(2100, 1, 1);
		assertNotNull(date);
	}

}
