package com.excilys.aflak.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;

public class Mapper {

	public static Computer mapComputer(ResultSet result) {
		Company company = null;
		Computer computer = null;
		try {
			company = new Company(result.getInt("company_id"),
					result.getString("company_name"));
			computer = new Computer(result.getInt("id"),
					result.getString("name"),
					TimeConvertor.convertTimestampToLocalDateTime(result
							.getTimestamp("introduced")),
					TimeConvertor.convertTimestampToLocalDateTime(result
							.getTimestamp("discontinued")), company);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computer;
	}

	public static Company mapCompany(ResultSet result) {
		Company company = null;
		try {
			company = new Company(result.getInt("id"), result.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}
}
