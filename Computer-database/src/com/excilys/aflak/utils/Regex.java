package com.excilys.aflak.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	private final static String regExInteger = "[0-9]+";
	private static final String FORMAT_DATE_FR = "[0-9]{2}-[0-9]{2}-[0-9]{4}";

	private static Pattern pattern;
	private static Matcher matcher;

	public static boolean isInteger(String param) {
		pattern = Pattern.compile(regExInteger);
		matcher = pattern.matcher(param);
		return matcher.matches();
	}

	public static boolean isDateFormatFr(String date) {
		pattern = Pattern.compile(FORMAT_DATE_FR);
		matcher = pattern.matcher(date);
		return matcher.matches();

	}

	public static String convertTimestampToString(Timestamp time) {
		String timeFormat = null;
		Calendar cal = Calendar.getInstance();
		if (time != null) {
			cal.setTime(new Date(time.getTime()));
			String year = Integer.toString(cal.get(Calendar.YEAR));

			int m = cal.get(Calendar.MONTH) + 1;
			String month = Integer.toString(m);

			if (m < 10) {
				month = "0" + month;
			}

			int d = cal.get(Calendar.DAY_OF_MONTH);
			String day = Integer.toString(d);
			if (d < 10) {
				day = "0" + day;
			}

			timeFormat = new StringBuilder(day).append("/").append(month)
					.append("/").append(year).toString();
		} else {
			timeFormat = " 0 ";
		}
		return timeFormat;
	}

}
