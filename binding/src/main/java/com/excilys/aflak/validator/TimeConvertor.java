package com.excilys.aflak.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.excilys.aflak.validator.Date.Pattern;

public class TimeConvertor {

	// date format : dd-mm-yyyy
	// pattern : com.excilys.aflak.validator.Date.Pattern
	public static LocalDateTime convertStringToLocalDateTime(String date,
			Pattern dateFormat) {
		System.err.println("PATTERN RIEN " + dateFormat);
		if (date == null || date.trim().isEmpty()
				|| !(new DateValidator().isValid(date, dateFormat))) {
			return null;
		}
		// java8
		return LocalDateTime.of(
				LocalDate.parse(date,
						DateTimeFormatter.ofPattern(dateFormat.toString())),
				LocalTime.of(0, 0));

	}

	public static String convertLocalDateTimeToString(LocalDateTime ldt,
			Pattern pattern) {
		if (ldt == null) {
			return null;
		} else {
			return ldt.format(DateTimeFormatter.ofPattern(pattern.toString()));
			/*
			 * System.err.println("ldt " + ldt.toString()); int day =
			 * ldt.getDayOfMonth(); int month = ldt.getMonthValue(); int year =
			 * ldt.getYear(); return new
			 * StringBuilder().append(day).append("-").append(month)
			 * .append("-").append(year).toString();
			 */
		}
	}

}
