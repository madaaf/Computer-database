package com.excilys.aflak.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class ConnectionBdd {
	// POOLCONNECTIONS;

	@Autowired
	DataSource dataSource;

	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public void closeConnection() throws SQLException {
		Connection c = getConnection();
		c.close();
	}
}
