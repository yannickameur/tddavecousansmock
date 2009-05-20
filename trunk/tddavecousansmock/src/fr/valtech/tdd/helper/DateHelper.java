package fr.valtech.tdd.helper;

import java.util.Calendar;
import java.util.Date;

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

}
