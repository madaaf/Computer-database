package com.excilys.aflak.ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.aflak.dao.CompanyDAO;
import com.excilys.aflak.dao.ComputerDAO;
import com.excilys.aflak.dao.ConnectionBdd;
import com.excilys.aflak.dao.DAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.Regex;

public class Menu {

	private static DAO<Computer> computerDao;
	private static DAO<Company> companyDao;
	private static List<Computer> listComputer = new ArrayList<Computer>();
	private static List<Company> listCompany = new ArrayList<Company>();

	private static Scanner sc = new Scanner(System.in);

	private static String choice;
	private static String input;
	private static String name;

	private static Timestamp dateFormated = null;
	private static Timestamp introduced = null;
	private static Timestamp discontinued = null;

	private static int idCompany = -1;
	private static int idComputer = -1;

	private static boolean isInteger;
	private static boolean isDateFr;

	public static void main(String[] args) {
		computerDao = new ComputerDAO(ConnectionBdd.getInstance());
		companyDao = new CompanyDAO(ConnectionBdd.getInstance());
		printMenu();
		selectMenu();
	}

	public static void printMenu() {
		System.out.println("\n1- List computers");
		System.out.println("2- List companies");
		System.out.println("3- Show computer details");
		System.out.println("4- Create a computer");
		System.out.println("5- Update a computer");
		System.out.println("6- Delete a computer");
		choice = sc.nextLine();
	}

	public static Computer createAndUpdateComputer(int computerId) {
		// demande le nom de l'ordinateur
		System.out.println(" Enter the name of your computer : ");
		name = sc.nextLine();
		// demande la date "introduced"
		introduced = DisplayDate();
		// demande la date "discontinued"
		discontinued = DisplayDate();
		// demande l'id de la companie
		System.out.println("Enter the id of its company : ");
		input = sc.nextLine();
		// verifie si l'id correspond à un chiffre
		isInteger = Regex.isInteger(input);
		Computer com = null;
		if (isInteger) {
			idCompany = Integer.parseInt(input);
		}
		com = new Computer(computerId, name, introduced, discontinued,
				idCompany);
		return com;
	}

	// demande la date
	// si l'utilisateur ne rentre rien, passez à l'étape suivante
	// s'il rentre un format incorrect, posez lui la question une
	// nouvelle fois
	// renvoyez la date en format Timestamp
	public static Timestamp DisplayDate() {
		do {
			System.out
					.println(" Enter the date of introduced (dd-mm-yyyy)  : ");
			input = sc.nextLine();
			isDateFr = Regex.isDateFormatFr(input);

			if (input.isEmpty()) {
				dateFormated = null;
			} else if (isDateFr) {
				dateFormated = Regex.convertStringToTimestamp(input);
			} else {
				System.err.println(" You have to respect the format's date");
			}

		} while (!input.isEmpty() && !isDateFr);
		return dateFormated;
	}

	public static void selectMenu() {

		switch (choice) {
		case "1":
			System.out.println("List of computers : ");
			listComputer = computerDao.list();

			for (int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}

			break;
		case "2":
			System.out.println("List of companies : ");
			listCompany = companyDao.list();
			for (int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			break;

		case "3":
			System.out.println(" Enter the id of your computer :");
			input = sc.nextLine();
			isInteger = Regex.isInteger(input);
			Computer computer = new Computer();
			if (isInteger) {
				computer = computerDao.find(Integer.parseInt(input));
			} else {
				System.out.println("You have to enter a number");
				break;
			}
			System.out.println("Details : \n " + computer.toString());
			break;
		case "4":
			Computer com = createAndUpdateComputer(-1);
			if (com != null) {
				computerDao.create(com);
			}
			break;

		case "5":
			System.out
					.println(" Enter the id of the computer you want to update : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {
				isInteger = Regex.isInteger(input);
				if (isInteger) {
					idComputer = Integer.parseInt(input);
				} else {
					System.err.println("\nEnter a number");
					break;
				}
			}

			Computer comp = createAndUpdateComputer(idComputer);
			if (comp != null) {
				computerDao.update(comp);
			}

			break;
		case "6":

			System.out
					.println(" Enter the id of the computer you want to delete : ");
			input = sc.nextLine();
			isInteger = Regex.isInteger(input);
			if (isInteger) {
				idComputer = Integer.parseInt(input);
				boolean isDeleted = computerDao.delete(idComputer);
				if (isDeleted == true) {
					System.out.println("your computer is deleted");
				}
				break;
			} else {
				System.err.println("\nYou have to enter a number");
				break;
			}

		default:
			System.err.println("\nError : Try again");
			break;
		}
		printMenu();
		selectMenu();
	}
}
