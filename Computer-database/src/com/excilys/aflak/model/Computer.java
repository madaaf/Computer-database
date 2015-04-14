package com.excilys.aflak.model;

import java.time.LocalDateTime;

import com.excilys.aflak.utils.TimeConvertor;

public class Computer {

	private int id;
	private String name;
	private LocalDateTime introduced;
	private String dateIntroduced;
	private LocalDateTime discontinued;
	private String dateDiscontinued;
	private Company ccompany;
	private int companyId;

	public Computer() {
	}

	public Computer(int id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, Company ccompagny) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		dateIntroduced = TimeConvertor.convertLocalDateTimeToString(introduced);
		dateDiscontinued = TimeConvertor
				.convertLocalDateTimeToString(discontinued);
		this.ccompany = ccompagny;
	}

	public Computer(int id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, int companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		dateIntroduced = TimeConvertor.convertLocalDateTimeToString(introduced);
		dateDiscontinued = TimeConvertor
				.convertLocalDateTimeToString(discontinued);
		this.companyId = companyId;
	}

	public String getDateIntroduced() {
		return dateIntroduced;
	}

	public void setDateIntroduced(String dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return ccompany;
	}

	public void setCompany(Company ccompany) {
		this.ccompany = ccompany;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {

		return new StringBuilder(" ").append(id).append("\t|\t").append(name)
				.append("\t|\t ").append(dateIntroduced).append("\t|\t")
				.append(dateDiscontinued).append("\t|\t")
				.append(ccompany.getId()).append("\t|\t")
				.append(ccompany.getName()).toString();
	}
}
