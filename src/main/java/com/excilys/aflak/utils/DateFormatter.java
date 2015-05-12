package com.excilys.aflak.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
	private String pattern = "dd-MM-yyyy";

	public DateFormatter(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String print(Date formatted, Locale locale) {

		return getDateFormat(locale).format(formatted);
	}

	@Override
	public Date parse(String formatted, Locale locale) throws ParseException {
		return getDateFormat(locale).parse(formatted);
	}

	protected DateFormat getDateFormat(Locale locale) {
		DateFormat dateFormat = new SimpleDateFormat(this.pattern, locale);
		dateFormat.setLenient(false);
		return dateFormat;
	}

	public static void main(String[] args) {
		DateFormatter dt = new DateFormatter("dd-MM-yyyy");
		try {
			System.out.println(dt.parse("31-02-1992", Locale.FRENCH));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
