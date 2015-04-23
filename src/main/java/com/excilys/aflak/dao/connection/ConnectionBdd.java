package com.excilys.aflak.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import exception.DAOConfigurationException;

public enum ConnectionBdd {
	POOLCONNECTIONS;

	private String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private String urlTest = "jdbc:mysql://localhost:3306/computer-database-db-test?zeroDateTimeBehavior=convertToNull";
	private String user = "admincdb";
	private String password = "qwerty1234";
	public boolean TEST = false;
	private BoneCP connectionPool;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ConnectionBdd() {
		try {
			BoneCPConfig config = new BoneCPConfig();
			/* Mise en place de l'url, du nom et du mot de passe */
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			// db started after the server
			config.setLazyInit(true);
			/* parametrage de la taille de la pool */
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			/* Création du pool à partir de la configuration, via l'objet BoneCP */
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new DAOConfigurationException(
					"erreur de configuration du pool de connexions" + e);
		}
	}

	public Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closeConnection(Connection connect, PreparedStatement state,
			ResultSet result) {
		try {
			if (result != null)
				result.close();
			if (state != null)
				state.close();
			if (connect != null)
				connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
