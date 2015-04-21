package com.excilys.aflak.dao.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeConvertorDAO {
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
}
