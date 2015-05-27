package com.excilys.aflak.mapper;

import com.excilys.aflak.binding.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.Builder;
import com.excilys.aflak.validator.Date.Pattern;
import com.excilys.aflak.validator.TimeConvertor;

public class ComputerMapper implements Mapper<Computer, ComputerDTO> {

  private final Pattern pattern;

  public ComputerMapper(Pattern pattern) {
    this.pattern = pattern;
  }

  public Computer fromDto(ComputerDTO cdto) {
    System.err.println("pattern name " + pattern.name());
    Company company = null;
    if (cdto.getCompanyId() != 0) {
      company = Company.Builder.builder()
          .id(cdto.getCompanyId())
          .name(cdto.getCompanyName()).build();
    }
    Computer computer = Builder
        .builder()
        .id(cdto.getId())
        .name(cdto.getName())
        .introduced(TimeConvertor.convertStringToLocalDateTime(cdto.getIntroduced(), pattern))
        .discontinued(TimeConvertor.convertStringToLocalDateTime(cdto.getDiscontinued(), pattern))
        .company(company).build();

    return computer;

  }

  public ComputerDTO toDto(Computer computer) {
    long idCompany = 0;
    String nameCompany = null;
    if (computer.getCompany() != null) {
      idCompany = computer.getCompany().getId();
      nameCompany = computer.getCompany().getName();
    }

    return new ComputerDTO(computer.getId(), computer.getName(),
        TimeConvertor.convertLocalDateTimeToString(computer.getIntroduced(), pattern),
        TimeConvertor.convertLocalDateTimeToString(computer.getDiscontinued(), pattern),
        idCompany,nameCompany);
  }
}
