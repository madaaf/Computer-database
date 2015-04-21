package com.excilys.aflak.utils;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;

public class Mapper {

	public static Computer computerDTOToComputer(ComputerDTO cdto) {
		Company company = new Company((int) cdto.getCompanyId(),
				cdto.getCompanyName());
		return new Computer(
				(int) cdto.getId(),
				cdto.getName(),
				TimeConvertor.convertStringToLocalDateTime(cdto.getIntroduced()),
				TimeConvertor.convertStringToLocalDateTime(cdto
						.getDiscontinued()), company);

	}

	public static ComputerDTO computerToComputerDTO(Computer computer) {
		return new ComputerDTO(computer.getId(), computer.getName(),
				TimeConvertor.convertLocalDateTimeToString(computer
						.getIntroduced()),
				TimeConvertor.convertLocalDateTimeToString(computer
						.getDiscontinued()), computer.getCompany().getId(),
				computer.getCompany().getName());

	}
}
