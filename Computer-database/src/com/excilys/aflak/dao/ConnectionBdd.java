package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBdd {
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	private static Connection connect;

	// methode qui retourne une instace et la créer si elle n'existe pas
	public static Connection getInstance() {
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
		return connect;
	}

}
