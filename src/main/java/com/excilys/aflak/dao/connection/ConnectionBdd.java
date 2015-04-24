package com.excilys.aflak.dao.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import exception.DAOConfigurationException;

public enum ConnectionBdd {
	POOLCONNECTIONS;

	private String url;
	private String urlTest = "jdbc:mysql://localhost:3306/computer-database-db-test?zeroDateTimeBehavior=convertToNull";
	private String user;
	private String password;
	private boolean TEST = false;
	private BoneCP connectionPool;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readProperties() throws FileNotFoundException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream fichierProperties = classLoader
				.getResourceAsStream("connection.properties");
		if (fichierProperties == null) {
			throw new FileNotFoundException("Fichier non trouvé");
		}
		try {
			properties.load(fichierProperties);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");

		} catch (IOException e) {
			throw new FileNotFoundException("Fichier non loader");
		}
	}

	public void setTest(boolean TEST) {
		this.TEST = TEST;
		changePool();
	}

	public void changePool() {
		try {

			BoneCPConfig config = new BoneCPConfig();
			/* Mise en place de l'url, du nom et du mot de passe */
			if (TEST) {
				config.setJdbcUrl(urlTest);
			} else {
				config.setJdbcUrl(url);
			}
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

	private ConnectionBdd() {
		try {
			readProperties();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		changePool();
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
