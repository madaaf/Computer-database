package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionBdd {
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static String urlTest = "jdbc:mysql://localhost:3306/computer-database-db-test?zeroDateTimeBehavior=convertToNull";
	private final static String user = "admincdb";
	private final static String password = "qwerty1234";
	public static boolean TEST = false;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection((TEST ? urlTest : url), user,
					password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return connect;
	}

	public static void closeConnection(Connection connect,
			PreparedStatement state, ResultSet result) {
		try {
			if (connect != null)
				connect.close();
			if (result != null)
				result.close();
			if (state != null)
				state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
