package com.excilys.aflak.validator;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.aflak.utils.TimeConvertor;
import com.excilys.aflak.validator.Date.Pattern;

public class DateValidator implements ConstraintValidator<Date, String> {
	private final org.apache.commons.validator.routines.DateValidator validator = org.apache.commons.validator.routines.DateValidator
			.getInstance();
	// pattern = com.excilys.aflak.validator.Date.Pattern;
	private Pattern dateFormat;

	@Override
	public void initialize(Date annotation) {
	}

	@Override
	public boolean isValid(String dateToValidate,
			ConstraintValidatorContext arg1) {
		Locale locale = LocaleContextHolder.getLocale();
		Pattern language = Pattern.map(locale.getLanguage());
		dateFormat = language;
		if (dateToValidate == null || dateToValidate.trim().isEmpty()) {
			return true;
		}

		if (!dateToValidate.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}"))
			return false;

		if (validator.isValid(dateToValidate, dateFormat.toString())) {
			LocalDateTime ldt = TimeConvertor.convertStringToLocalDateTime(
					dateToValidate, dateFormat);
			// sql
			if (ldt.getYear() < 1970 || ldt.getYear() >= 2038)
				return false;
			return true;
		} else {
			return false;
		}
	}

	public boolean isValid(String date, Pattern pattern) {
		if (date == null || date.trim().isEmpty()) {
			return true;
		}
		return validator.isValid(date, pattern.toString());
	}

}
