package com.excilys.aflak.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	private final static String regExInteger = "[0-9]+";
	private static Pattern pattern;
	private static Matcher matcher;

	public static boolean isInteger(String param) {
		pattern = Pattern.compile(regExInteger);
		matcher = pattern.matcher(param);
		return matcher.matches();
	}
}
