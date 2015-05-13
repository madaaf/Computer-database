package com.excilys.aflak.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;

public class ComputerDTOValidator implements Validator {
	private List<Company> listCompany = new ArrayList<Company>();

	private static Logger logger = LoggerFactory
			.getLogger(ComputerDTOValidator.class);

	public ComputerDTOValidator(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

	@Override
	public boolean supports(Class<?> c) {
		return ComputerDTO.class.equals(c);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ComputerDTO computerDTO = (ComputerDTO) o;
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		logger.debug(Integer.toString(listCompany.size()));
		if (!listCompany.contains(computerDTO.getCompanyId())
				&& computerDTO.getCompanyId() != 0) {
			errors.rejectValue("invalid Company Id", "invalid Company Id");
		}
		//
		// System.err.print(computerDTO.getIntroduced());
		// if (!computerDTO.getIntroduced().isEmpty()) {
		// try {
		// DateFormatter dt = new DateFormatter("dd-MM-yyyy");
		// if (dt.parse(computerDTO.getIntroduced(), Locale.FRENCH) == null) {
		// System.err.print(dt.parse(computerDTO.getIntroduced(),
		// Locale.FRENCH));
		// System.err.print("INVALID DATE");
		// errors.rejectValue("invalid date", "invalid date");
		//
		// }
		// } catch (ParseException e) {
		// e.printStackTrace();
		// throw new RuntimeException();
		// }
		// }

	}
}
