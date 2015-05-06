package com.excilys.aflak.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import exception.DAOConfigurationException;

@Component
public class ConnectionBdd {
	// POOLCONNECTIONS;
	private static Logger logger = LoggerFactory.getLogger(ConnectionBdd.class);

	@Autowired
	DataSource dataSource;

	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public void closeResultAndStatement(PreparedStatement state,
			ResultSet result) {
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				logger.error("PreparedStatement close failed");
				e.printStackTrace();
				throw new DAOConfigurationException(
						"PreparedStatement close failed");
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				logger.error("ResultSet close failed");
				e.printStackTrace();
				throw new DAOConfigurationException("ResultSet close failed");
			}
		}
	}
}
