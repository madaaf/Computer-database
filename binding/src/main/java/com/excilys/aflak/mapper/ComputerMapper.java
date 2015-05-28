package com.excilys.aflak.mapper;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.Builder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.validator.Date.Pattern;
import com.excilys.aflak.validator.TimeConvertor;

public class ComputerMapper implements Mapper<Computer, ComputerDTO> {

	private final Pattern pattern;

	public ComputerMapper(Pattern pattern) {
		this.pattern = pattern;
	}

	public ComputerMapper() {
		pattern = Pattern.FR;
	}

	@Override
	public Computer fromDto(ComputerDTO o) {
		System.err.println("pattern name " + pattern.name());
		Company company = null;
		if (o.getCompanyId() != 0) {
			company = Builder.builder().id(o.getCompanyId())
					.name(o.getCompanyName()).build();
		}

		Computer computer = Computer.Builder
				.builder()
				.id(o.getId())
				.name(o.getName())
				.introduced(
						TimeConvertor.convertStringToLocalDateTime(
								o.getIntroduced(), pattern))
				.discontinued(
						TimeConvertor.convertStringToLocalDateTime(
								o.getDiscontinued(), pattern)).company(company)
				.build();

		return computer;

	}

	@Override
	public ComputerDTO toDto(Computer o) {
		long idCompany = 0;
		String nameCompany = null;
		if (o.getCompany() != null) {
			idCompany = o.getCompany().getId();
			nameCompany = o.getCompany().getName();
		}

		return new ComputerDTO(o.getId(), o.getName(),
				TimeConvertor.convertLocalDateTimeToString(o.getIntroduced(),
						pattern), TimeConvertor.convertLocalDateTimeToString(
						o.getDiscontinued(), pattern), idCompany, nameCompany);
	}
}
