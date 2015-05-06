package com.excilys.aflak.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.CompanyBuilder;

import exception.DAOException;

public class MapperCompany implements RowMapper<Company> {

	private static Logger logger = LoggerFactory.getLogger(MapperCompany.class);

	@Override
	public Company mapRow(ResultSet result, int arg1) throws SQLException {

		return mapCompany(result);
	}

	public static Company mapCompany(ResultSet result) {
		Company company = null;
		try {

			company = CompanyBuilder.crateDefaultCompany()
					.withId(result.getLong("id"))
					.withName(result.getString("name")).build();
		} catch (SQLException e) {
			logger.error("map company faild");
			throw new DAOException("map company failed");
		}
		return company;
	}

}
