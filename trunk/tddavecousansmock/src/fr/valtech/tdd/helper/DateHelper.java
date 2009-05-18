package fr.valtech.tdd.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateHelper {
	public static final String DATE_PATTERN = "ddMMyyyy";

	// Jours de la semaine en base
	public static final char LUNDI = '1';
	public static final char MARDI = '2';
	public static final char MERCREDI = '3';
	public static final char JEUDI = '4';
	public static final char VENDREDI = '5';
	public static final char SAMEDI = '6';
	public static final char DIMANCHE = '7';

	private DateHelper() {
		// pas d'implementation necessaire
	}

	public static Date createDate(int year, int month, int day) {
		return createCalendar(year, month, day, 0, 0, 0, 0).getTime();
	}

	private static Calendar createCalendar(int year, int month, int day,
			int hour, int minutes, int seconds, int milliseconds) {

		Calendar calendar = createEmptyUTCCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // janvier = 0 dans JDK
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		calendar.set(Calendar.MILLISECOND, milliseconds);

		return calendar;
	}

	private static Calendar createEmptyUTCCalendar() {
		Calendar calendar = Calendar.getInstance();
		// remet toutes les valeurs a 0 (sauf la TimeZone, je pense)
		calendar.clear();
		return calendar;
	}

	public static Date stringToDate(String sDate) {
		try {
			return new SimpleDateFormat(DATE_PATTERN).parse(sDate);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Date addDays(Date date, int nbDays) {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		// Initialise a la date et l'heure courante.
		calendar.setTime(date);
		calendar.add(Calendar.DATE, nbDays);
		return calendar.getTime();
	}

	public static Date subDays(Date date, int nbDays) {
		return addDays(date, -Math.abs(nbDays));
	}

	public static String dateToString(Date date) {
		return new SimpleDateFormat(DATE_PATTERN).format(date);
	}

	/**
	 * Checks if date1 equals date2 Why this method ? because we often use
	 * Timestamps instead of Date
	 * 
	 * @param date1
	 * @param date2
	 * @return true if equals, false otherwise
	 */
	public static boolean areEquals(Date date1, Date date2) {
		if (date1 == date2) {
			return true;
		} else if (date1 != null && date2 != null) {
			return date1.getTime() == date2.getTime();
		}
		return false;
	}

	/**
	 * Checks if date1 is after date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isGreaterThan(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException();
		}
		GregorianCalendar calendar1 = new java.util.GregorianCalendar();
		calendar1.setTime(date1);
		GregorianCalendar calendar2 = new java.util.GregorianCalendar();
		calendar2.setTime(date2);
		return calendar1.after(calendar2);

	}

	public static boolean isGreaterThanOrEquals(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException();
		}

		GregorianCalendar calendar1 = new java.util.GregorianCalendar();
		calendar1.setTime(date1);
		GregorianCalendar calendar2 = new java.util.GregorianCalendar();
		calendar2.setTime(date2);
		return calendar1.after(calendar2) || calendar1.equals(calendar2);
	}

	public static boolean dateIncludedInRange(Date date, Date dateMin,
			Date dateMax) {
		if (dateMin == null || dateMax == null || date == null) {
			throw new IllegalArgumentException();
		}

		if (DateHelper.isGreaterThanOrEquals(date, dateMin)
				&& DateHelper.isGreaterThanOrEquals(dateMax, date)) {
			return true;
		}
		return false;
	}

	public static boolean dateStrictlyIncludedInRange(Date date, Date dateMin,
			Date dateMax) {
		if (dateMin == null || dateMax == null || date == null) {
			throw new IllegalArgumentException();
		}

		if (DateHelper.isGreaterThan(date, dateMin)
				&& DateHelper.isGreaterThan(dateMax, date)) {
			return true;
		}
		return false;
	}

	public static int getDayOfMonth(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date setDayOfMonth(Date date, int dayOfMonth) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		return calendar.getTime();
	}

}
