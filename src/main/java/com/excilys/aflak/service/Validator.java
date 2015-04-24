package com.excilys.aflak.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private final static String regExInteger = "[0-9]+";
	private final static String dateFrFormat = "(O?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)[0-9][0-9])";
	private static Pattern pattern;
	private static Matcher matcher;
	private static final List<String> listColomns = new ArrayList<String>(
			Arrays.asList("computer.id", "computer.name",
					"computer.introduced", "computer.discontinued",
					"company.name"));

	private static final List<String> listWays = new ArrayList<String>(
			Arrays.asList("ASC", "DESC"));

	public static boolean isInteger(String param) {
		pattern = Pattern.compile(regExInteger);
		matcher = pattern.matcher(param);
		return matcher.matches();
	}

	public static boolean isDateFormatFr(String date) {
		pattern = Pattern.compile(dateFrFormat);
		matcher = pattern.matcher(date);
		return matcher.matches();
	}

	public static String getFilter(String filter) {
		if (filter == null || filter.equals(null) || filter.isEmpty()) {
			return "";
		}
		return filter;
	}

	public static String getColomn(String colomn) {
		if (listColomns.contains(colomn)) {
			return colomn;
		} else {
			return "computer.id";
		}
	}

	public static String getWay(String way) {
		if (listWays.contains(way)) {
			return way;
		} else {
			return "ASC";
		}
	}
}
