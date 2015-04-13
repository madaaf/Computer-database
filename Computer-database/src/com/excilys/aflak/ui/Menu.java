package com.excilys.aflak.ui;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.aflak.dao.CompanyDAO;
import com.excilys.aflak.dao.ComputerDAO;
import com.excilys.aflak.dao.DAO;
import com.excilys.aflak.dao.SdzConnection;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.Regex;

public class Menu {

	private static String choice;
	private static DAO<Computer> computerDao;
	private static DAO<Company> companyDao;
	private static Scanner sc = new Scanner(System.in);
	private static List<Computer> listComputer = new ArrayList<Computer>();
	private static List<Company> listCompany = new ArrayList<Company>();

	public static void main(String[] args) {
		computerDao = new ComputerDAO(SdzConnection.getInstance());
		companyDao = new CompanyDAO(SdzConnection.getInstance());
		printMenu();
		selectMenu();
	}

	public static void printMenu() {
		System.out.println("1- List computers");
		System.out.println("2- List companies");
		System.out.println("3- Show computer details");
		System.out.println("4- Create a computer");
		System.out.println("5- Update a computer");
		System.out.println("6- Delete a computer");
		choice = sc.nextLine();
	}

	public static Timestamp displayTimeFormat(String date) {
		Date dateStamp;
		Timestamp timeFormated = null;
		try {
			dateStamp = new SimpleDateFormat("dd-MM-yyy").parse(date);
			long time = dateStamp.getTime();
			timeFormated = new Timestamp(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeFormated;
	}

	public static void selectMenu() {
		String name;
		Timestamp introduced;
		int idCompany = -1;
		int idComputer = -1;
		Timestamp discontinued;
		String input;
		boolean isInteger;

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
			System.out.println(" Enter the name of your computer : ");
			name = sc.nextLine();

			System.out
					.println(" Enter the date of introduced (dd-mm-yyyy)  : ");
			introduced = displayTimeFormat(sc.nextLine());

			System.out
					.println(" Enter the date of discontinued (dd-mm-yyyy)  : ");
			discontinued = displayTimeFormat(sc.nextLine());

			System.out.println(" Enter the id of its company : ");
			input = sc.nextLine();
			isInteger = Regex.isInteger(input);
			if (isInteger) {
				idComputer = Integer.parseInt(input);
				Computer com = new Computer(-1, name, introduced, discontinued,
						idComputer);
				computerDao.create(com);
			} else {
				System.out.println("You have to enter a number");
				break;
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
					System.out.println("Enter a number");
					break;
				}
			} else {
				idComputer = -1;
			}

			System.out.println(" Enter the new name of your computer : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {
				name = input;
			} else {
				name = null;
			}

			System.out
					.println(" Enter the date of introduced (dd-mm-yyyy)  : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {
				introduced = displayTimeFormat(input);
			} else {
				introduced = null;
			}

			System.out
					.println(" Enter the date of discontinued (dd-mm-yyyy)  : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {
				discontinued = displayTimeFormat(input);
			} else {
				discontinued = null;
			}

			System.out.println(" Enter the id of its company : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {
				isInteger = Regex.isInteger(input);
				if (isInteger) {
					idCompany = Integer.parseInt(input);
				} else {
					System.out.println("you have to enter a number");
					break;
				}

			} else {
				idCompany = -1;
			}

			Computer c = new Computer(idComputer, name, introduced,
					discontinued, null);

			computerDao.update(c);

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
				} else {
					System.out.println("it is doesn't work");
				}
				break;
			} else {
				System.out.println("You have to enter a number");
				break;
			}

		default:
			System.out.println("Error : Try again");
			break;
		}
		printMenu();
		selectMenu();
	}
}
