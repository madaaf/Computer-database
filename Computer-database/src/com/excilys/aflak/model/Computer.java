package com.excilys.aflak.model;

import java.sql.Timestamp;

import com.excilys.aflak.utils.Regex;

public class Computer {

	private int id;
	private String name;
	private Timestamp introduced;
	private String dateIntroduced;
	private Timestamp discontinued;
	private String dateDiscontinued;
	private Company ccompany;
	private int companyId;

	public Computer() {
	}

	public Computer(int id, String name, Timestamp introduced,
			Timestamp discontinued, Company ccompagny) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		dateIntroduced = Regex.convertTimestampToString(introduced);
		dateDiscontinued = Regex.convertTimestampToString(discontinued);
		this.ccompany = ccompagny;
	}

	public Computer(int id, String name, Timestamp introduced,
			Timestamp discontinued, int companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		dateIntroduced = Regex.convertTimestampToString(introduced);
		dateDiscontinued = Regex.convertTimestampToString(discontinued);
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

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) {
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
