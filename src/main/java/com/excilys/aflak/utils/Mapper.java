package com.excilys.aflak.utils;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.validator.Date.Pattern;

public class Mapper {

	public static Computer computerDTOToComputer(ComputerDTO cdto,
			Pattern pattern) {
		System.err.println("pattern name " + pattern.name());
		Company company = null;
		if (cdto.getCompanyId() != 0) {
			company = CompanyBuilder.crateDefaultCompany()
					.withId(cdto.getCompanyId())
					.withName(cdto.getCompanyName()).build();
		}
		Computer computer = ComputerBuilder
				.createDefaultComputer()
				.withId(cdto.getId())
				.withName(cdto.getName())
				.withIntroduced(
						TimeConvertor.convertStringToLocalDateTime(
								cdto.getIntroduced(), pattern))
				.withDiscontinued(
						TimeConvertor.convertStringToLocalDateTime(
								cdto.getDiscontinued(), pattern))
				.withCompany(company).build();

		return computer;

	}

	public static ComputerDTO computerToComputerDTO(Computer computer,
			Pattern pattern) {
		long idCompany = 0;
		String nameCompany = null;
		if (computer.getCompany() != null) {
			idCompany = computer.getCompany().getId();
			nameCompany = computer.getCompany().getName();
		}

		return new ComputerDTO(computer.getId(), computer.getName(),
				TimeConvertor.convertLocalDateTimeToString(
						computer.getIntroduced(), pattern),
				TimeConvertor.convertLocalDateTimeToString(
						computer.getDiscontinued(), pattern), idCompany,
				nameCompany);

	}
}
