package com.excilys.aflak.utils;

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

}
