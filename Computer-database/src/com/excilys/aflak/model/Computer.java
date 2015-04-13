package com.excilys.aflak.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

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
		dateIntroduced = convertTimeToString(introduced);
		dateDiscontinued = convertTimeToString(discontinued);
		this.ccompany = ccompagny;
	}

	public Computer(int id, String name, Timestamp introduced,
			Timestamp discontinued, int companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		dateIntroduced = convertTimeToString(introduced);
		dateDiscontinued = convertTimeToString(discontinued);
		this.companyId = companyId;
	}

	public String convertTimeToString(Timestamp time) {
		String timeFormat = null;
		Calendar cal = Calendar.getInstance();
		if (time != null) {
			cal.setTime(new Date(time.getTime()));
			String year = Integer.toString(cal.get(Calendar.YEAR));

			int m = cal.get(Calendar.MONTH) + 1;
			String month = Integer.toString(m);

			if (m < 10) {
				month = "0" + month;
			}

			int d = cal.get(Calendar.DAY_OF_MONTH);
			String day = Integer.toString(d);
			if (d < 10) {
				day = "0" + day;
			}

			timeFormat = new StringBuilder(day).append("/").append(month)
					.append("/").append(year).toString();
		} else {
			timeFormat = " 0 ";
		}
		return timeFormat;
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
