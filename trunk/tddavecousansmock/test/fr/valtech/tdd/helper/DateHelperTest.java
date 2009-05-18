package fr.valtech.tdd.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateHelperTest {

	@Test
	public void stringToDate() {
		Date date = DateHelper.stringToDate("12092007");
		assertNotNull(date);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringToDate_Error() {
		Date date = DateHelper.stringToDate("12//////092007");
		assertNotNull(date);
	}

	@Test
	public void addDays() {
		Date date = DateHelper.addDays(DateHelper.stringToDate("12092007"), 2);
		assertNotNull(date);
		assertEquals(DateHelper.stringToDate("14092007"), date);
	}

	@Test
	public void subDays() {
		Date date = DateHelper.subDays(DateHelper.stringToDate("12092007"), -2);
		assertNotNull(date);
		assertEquals(DateHelper.stringToDate("10092007"), date);
	}

	@Test
	public void dateToString() {
		String date = DateHelper.dateToString(DateHelper
				.stringToDate("12092007"));
		assertNotNull(date);
		assertEquals("12092007", date);
	}

	@Test
	public void createDate() {
		Date date = DateHelper.createDate(2100, 1, 1);
		assertNotNull(date);
		assertEquals("01012100", DateHelper.dateToString(date));
	}

	@Test
	public void compareTwoDates() {
		Date date1 = DateHelper.createDate(2100, 1, 1);
		Date date2 = DateHelper.createDate(2000, 1, 1);

		assertEquals(true, DateHelper.isGreaterThan(date1, date2));
	}

	@Test
	public void testTwoDatesAreEquals() {
		Timestamp timeStamp = new Timestamp(DateHelper.createDate(2008, 12, 12)
				.getTime());

		// (Timestamp) DateHelper.createDate(2008, 12, 12);
		Date date = DateHelper.createDate(2008, 12, 12);
		assertTrue(DateHelper.areEquals(timeStamp, date));
	}

	@Test
	public void compareTwoDatesGreaterThanOrEquals() {
		Date date1 = DateHelper.createDate(2100, 1, 1);
		Date date2 = DateHelper.createDate(2000, 1, 1);

		assertTrue(DateHelper.isGreaterThanOrEquals(date1, date2));
		assertFalse(DateHelper.isGreaterThanOrEquals(date2, date1));

		date2 = date1;
		assertTrue(DateHelper.isGreaterThanOrEquals(date1, date2));
	}

	@Test
	public void verifieDateEstDansIntervalle() {
		Date dateATester = DateHelper.createDate(2008, 12, 12);
		Date datePasDansIntervalle = DateHelper.createDate(2008, 12, 15);

		Date dateIntervalleMin = DateHelper.createDate(2008, 12, 10);
		Date dateIntervalleMax = DateHelper.createDate(2008, 12, 14);

		assertTrue("date pas incluse dans intervalle", DateHelper
				.dateIncludedInRange(dateATester, dateIntervalleMin,
						dateIntervalleMax));
		assertFalse("date pas incluse dans intervalle", DateHelper
				.dateIncludedInRange(datePasDansIntervalle, dateIntervalleMin,
						dateIntervalleMax));
	}

	@Test
	public void verifieDateEstDansIntervalleStrict() {
		Date dateATester = DateHelper.createDate(2008, 12, 12);
		Date datePasDansIntervalle = DateHelper.createDate(2008, 12, 15);

		Date dateIntervalleMin = DateHelper.createDate(2008, 12, 10);
		Date dateIntervalleMax = DateHelper.createDate(2008, 12, 15);

		assertTrue("date pas incluse dans intervalle", DateHelper
				.dateStrictlyIncludedInRange(dateATester, dateIntervalleMin,
						dateIntervalleMax));
		assertFalse("date pas incluse dans intervalle", DateHelper
				.dateStrictlyIncludedInRange(datePasDansIntervalle,
						dateIntervalleMin, dateIntervalleMax));
	}

	@Test
	public void areEquals() {
		Assert.assertTrue(DateHelper.areEquals(null, null));
	}

	@Test
	public void areEquals_WithNull() {
		Assert.assertFalse(DateHelper.areEquals(null, new Date()));
		Assert.assertFalse(DateHelper.areEquals(new Date(), null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void isGreaterThan() {
		DateHelper.isGreaterThan(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void isGreaterThanOrEquals() {
		DateHelper.isGreaterThanOrEquals(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void dateIncludedInRange() {
		DateHelper.dateIncludedInRange(null, null, null);
	}

	@Test
	public void getDayOfMonth() {
		Date date = DateHelper.createDate(2008, 12, 10);
		Assert.assertEquals(DateHelper.getDayOfMonth(date), 10);
	}

	@Test
	public void setDayOfMonth() {
		Date date = DateHelper.createDate(2008, 2, 10);
		Assert.assertEquals(DateHelper.setDayOfMonth(date, 1), DateHelper
				.createDate(2008, 2, 1));
	}

}
