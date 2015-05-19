package com.excilys.aflak.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.aflak.controller.dto.Page;
import com.excilys.aflak.dao.inter.IDAOComputer;

public class PageValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(PageValidator.class);

	@Override
	public boolean supports(Class<?> c) {
		return Page.class.equals(c);
	}

	// errors in BindingResult
	@Override
	public void validate(Object o, Errors errors) {

		logger.error("VALIDATE");
		Page page = (Page) o;

		if (page.getLimit() < 0) {
			errors.rejectValue("limit", "negativeLmit");
		}

		if (!page.getColomn().equals(IDAOComputer.getColumn(page.getColomn()))) {
			IDAOComputer.getColumn(page.getColomn());
		}

		if (page.getInvalidArgument() != null) {
			errors.rejectValue("invalidArgument", "invalidArgument");
		}

	}
}
