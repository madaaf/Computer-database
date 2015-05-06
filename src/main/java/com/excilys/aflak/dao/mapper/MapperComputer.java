package com.excilys.aflak.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.aflak.dao.utils.TimeConvertorDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;

import exception.DAOException;

public class MapperComputer implements RowMapper<Computer> {

	private static Logger logger = LoggerFactory
			.getLogger(MapperComputer.class);

	@Override
	public Computer mapRow(ResultSet result, int arg1) throws SQLException {

		return mapComputer(result);
	}

	public Computer mapComputer(ResultSet result) {
		Computer computer = null;
		try {
			Company company = CompanyBuilder.crateDefaultCompany()
					.withId(result.getLong("company_id"))
					.withName(result.getString("company_name")).build();

			computer = ComputerBuilder
					.createDefaultComputer()
					.withId(result.getLong("id"))
					.withName(result.getString("name"))
					.withIntroduced(
							TimeConvertorDAO
									.convertTimestampToLocalDateTime(result
											.getTimestamp("introduced")))
					.withDiscontinued(
							TimeConvertorDAO
									.convertTimestampToLocalDateTime(result
											.getTimestamp("discontinued")))
					.withCompany(company).build();

		} catch (SQLException e) {
			logger.error("map computer failed");
			throw new DAOException("map computer failed");
		}

		return computer;
	}

}
