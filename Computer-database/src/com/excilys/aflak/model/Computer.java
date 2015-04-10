package com.excilys.aflak.model;

import java.sql.Date;

public class Computer {

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company ccompany;

	public Computer() {
	}

	public Computer(int id, String name, Date intriduced, Date discontinued,
			Company ccompagny) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.ccompany = ccompagny;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany_id() {
		return ccompany;
	}

	public void setCompany_id(Company ccompany) {
		this.ccompany = ccompany;
	}

}
