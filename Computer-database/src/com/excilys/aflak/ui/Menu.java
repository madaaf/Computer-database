package com.excilys.aflak.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.aflak.dao.CompanyDAO;
import com.excilys.aflak.dao.ComputerDAO;
import com.excilys.aflak.dao.DAO;
import com.excilys.aflak.dao.SdzConnection;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;

public class Menu {

	private static int choice;
	private static DAO<Computer> computerDao;
	private static DAO<Company> companyDao;
	private static Scanner sc = new Scanner(System.in);
	private static List<Computer> listComputer = new ArrayList<Computer>();
	private static List<Company> listCompany = new ArrayList<Company>();

	public static void printMenu() {
		System.out.println("1- List computers");
		System.out.println("2- List companies");
		System.out.println("3- Show computer details");
		System.out.println("4- Create a computer");
		System.out.println("5- Update a computer");
		System.out.println("6- Delete a computer");
		choice = Integer.parseInt(sc.nextLine());
	}

	public static void selectMenu() {
		switch (choice) {
		case 1:
			System.out.println("List of computers : ");
			listComputer = computerDao.list();

			for (int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}

			break;
		case 2:
			System.out.println("List of companies : ");
			listCompany = companyDao.list();
			for (int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			break;
		case 3:
			System.out.println(" Enter the id of your computer :");
			int idComputer = Integer.parseInt(sc.nextLine());
			Computer computer = new Computer();
			computer = computerDao.find(idComputer);
			System.out.println("Details : \n " + computer.toString());
			break;
		case 4:
			System.out.println(" Enter the name of your computer : ");
			String name = sc.nextLine();
			System.out.println(" Enter");
			break;
		case 5:
			break;
		case 6:
			System.out.println(" Enter the new name of your computer : ");
			System.out
					.println(" Enter the id of the computer you want to delete : ");
			int idC = Integer.parseInt(sc.nextLine());
			Boolean isDeleted = computerDao.delete(idC);
			if (isDeleted == true) {
				System.out.println("your computer is deleted");
			} else {
				System.out.println("it is doesn't work");
			}
			break;
		default:
			System.out.println("default");
			break;
		}
		printMenu();
		selectMenu();
	}

	public static void main(String[] args) {
		computerDao = new ComputerDAO(SdzConnection.getInstance());
		companyDao = new CompanyDAO(SdzConnection.getInstance());
		printMenu();
		selectMenu();

	}
}
