package com.excilys.aflak.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class TimeConvertor {

	public static LocalDateTime convertTimestampToLocalDateTime(
			Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		} else {
			return timestamp.toLocalDateTime();
		}
	}

	public static Timestamp convertLocalDateTimeToTimestamp(LocalDateTime ldt) {
		if (ldt == null) {
			return null;
		} else {
			return Timestamp.valueOf(ldt);
		}
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

			timeFormat = new StringBuilder(day).append("-").append(month)
					.append("-").append(year).toString();
		} else {
			timeFormat = " 0 ";
		}
		return timeFormat;
	}

	public static Timestamp convertStringToTimestamp(String date) {
		Date dateStamp;
		Timestamp timeFormated = null;
		try {
			dateStamp = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			long time = dateStamp.getTime();
			timeFormated = new Timestamp(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeFormated;
	}

	public static LocalDateTime convertStringToLocalDateTime(String date) {
		Timestamp ts = convertStringToTimestamp(date);
		LocalDateTime ldt = convertTimestampToLocalDateTime(ts);
		return ldt;
	}

	public static String convertLocalDateTimeToString(LocalDateTime ldt) {
		if (ldt == null) {
			return null;
		} else {
			Timestamp ts = convertLocalDateTimeToTimestamp(ldt);
			return convertTimestampToString(ts);
		}
	}

}
