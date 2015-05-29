package com.excilys.aflak.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.aflak.client.IClientService;
import com.excilys.aflak.client.impl.ClientCompanyService;
import com.excilys.aflak.client.impl.ClientComputerService;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.Builder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.validator.Date.Pattern;
import com.excilys.aflak.validator.TimeConvertor;

@Transactional
public class Menu {
	public static ClassPathXmlApplicationContext context;

	private static List<Computer> listComputer = new ArrayList<Computer>();
	private static List<Company> listCompany = new ArrayList<Company>();

	private static Scanner sc = new Scanner(System.in);

	private static String choice;
	private static String input;
	private static String name;

	private static LocalDateTime dateFormated = null;
	private static LocalDateTime introduced = null;
	private static LocalDateTime discontinued = null;

	private static Long idCompany = -1L;
	private static Long idComputer = -1L;

	private static boolean isInteger = true;
	private static boolean isDateFr;

	@Autowired
	private static IClientService<Computer> clientComputer;
	@Autowired
	private static IClientService<Company> clientCompany;

	static {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		clientComputer = context.getBean(ClientComputerService.class);
		clientCompany = context.getBean(ClientCompanyService.class);
	}

	public static void main(String[] args) {

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
		System.out.println("7-Delete a company");
		choice = sc.nextLine();
	}

	public static Computer createAndUpdateComputer(Long computerId) {
		// demande le nom de l'ordinateur
		System.out.println(" Enter the name of your computer : ");
		name = sc.nextLine();
		// demande la date "introduced"
		introduced = DisplayDate("introduced");
		// demande la date "discontinued"
		discontinued = DisplayDate("discontinued");
		// demande l'id de la companie
		System.out.println("Enter the id of its company : ");
		input = sc.nextLine();
		// verifie si l'id correspond à un chiffre

		Computer com = null;
		if (isInteger) {
			idCompany = Long.parseLong(input);

			Company c = Builder.builder().id(2L).build();
			// new Company(2, null);
			com = Computer.Builder.builder().id(computerId).name(name)
					.introduced(introduced).discontinued(discontinued)
					.company(c).build();
			// new Computer(computerId, name, introduced, discontinued, c);
		}
		return com;
	}

	// demande la date
	// si l'utilisateur ne rentre rien, passez à l'étape suivante
	// s'il rentre un format incorrect, posez lui la question une
	// nouvelle fois
	// renvoyez la date en format Timestamp
	public static LocalDateTime DisplayDate(String name) {
		do {
			System.out.println(" Enter the date of " + name
					+ " (dd-mm-yyyy)  : ");
			input = sc.nextLine();

			if (input.isEmpty()) {
				dateFormated = null;
			} else if (isDateFr) {
				dateFormated = TimeConvertor.convertStringToLocalDateTime(
						input, Pattern.FR);
			} else {
				System.err.println(" You have to respect the format's date");
			}

		} while (!input.isEmpty() && !isDateFr);
		return dateFormated;
	}

	public static void selectMenu() {

		switch (choice) {
		case "1":
			int debut = 0;
			int nbr = 10;
			System.out.println("List of computers : ");
			// listComputer = computerService.list(debut, nbr);

			listComputer = clientComputer.list();
			for (int i = 0; i < listComputer.size(); i++) {
				System.out.println(listComputer.get(i).toString());
			}

			System.out.println("n pour suivant, q pour quitter");
			input = sc.nextLine();
			do {
				switch (input) {
				case "n":
					debut++;
					listComputer = clientComputer.list();
					// listComputer = computerService.list(debut * nbr, nbr);
					for (int i = 0; i < listComputer.size(); i++) {
						System.out.println(listComputer.get(i).toString());
					}
					System.out.println("n pour suivant, q pour quitter");
					break;
				}
				input = sc.nextLine();
			} while (input.equals("n"));

			break;

		case "2":
			System.out.println("List of companies : ");
			listCompany = clientCompany.list();
			for (int i = 0; i < listCompany.size(); i++) {
				System.out.println(listCompany.get(i).toString());
			}
			break;

		case "3":
			System.out.println(" Enter the id of your computer :");
			input = sc.nextLine();

			Computer computer = null;
			if (isInteger) {
				computer = clientComputer.find(Long.parseLong(input));
			} else {
				System.out.println("You have to enter a number");
				break;
			}
			System.out.println("Details : \n " + computer.toString());
			break;
		case "4":
			Computer com = createAndUpdateComputer((-1L));
			if (com != null) {
				clientComputer.create(com);
				System.out.println("Your computer is created : "
						+ com.toString());
			} else {
				System.out.println("Your computer is empty");
			}
			break;

		case "5":
			System.out
					.println(" Enter the id of the computer you want to update : ");
			input = sc.nextLine();
			if (!input.isEmpty()) {

				if (isInteger) {
					idComputer = Long.parseLong(input);
				} else {
					System.err.println("\nEnter a number");
					break;
				}
			}

			Computer comp = createAndUpdateComputer(idComputer);
			if (comp != null) {
				clientComputer.update(comp);
			}

			break;
		case "6":

			System.out
					.println(" Enter the id of the computer you want to delete : ");
			input = sc.nextLine();

			if (isInteger) {
				idComputer = Long.parseLong(input);

				clientComputer.delete(idComputer);

				break;
			} else {
				System.err.println("\nYou have to enter a number");
				break;
			}
		case "7":
			System.out
					.println("Enter the id of the company you want to delete :");
			input = sc.nextLine();

			if (isInteger) {
				idCompany = Long.parseLong(input);
				clientCompany.delete(idCompany);
			} else {
				System.err.println("\nYou have to enter a number");
				break;
			}

			break;
		default:
			System.err.println("\nError : Try again");
			break;
		}
		printMenu();
		selectMenu();
	}
}
