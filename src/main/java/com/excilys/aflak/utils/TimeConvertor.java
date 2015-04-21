package com.excilys.aflak.utils;

import java.time.LocalDateTime;

public class TimeConvertor {

	// date format : dd-mm-yyyy
	public static LocalDateTime convertStringToLocalDateTime(String date) {
		if (Regex.isDateFormatFr(date)) {
			String[] parts = date.split("-");
			String day = parts[0]; // 004
			String month = parts[1];
			String year = parts[2];
			return LocalDateTime.of(Integer.parseInt(year),
					Integer.parseInt(month), Integer.parseInt(day), 0, 0);
		}
		return null;
	}

	public static String convertLocalDateTimeToString(LocalDateTime ldt) {
		if (ldt == null) {
			return null;
		} else {
			int day = ldt.getDayOfMonth();
			int month = ldt.getMonthValue();
			int year = ldt.getYear();
			return new StringBuilder().append(day).append("-").append(month)
					.append("-").append(year).toString();
		}
	}

}
