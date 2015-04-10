package com.excilys.aflak.ui;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		System.out.println("1- List computers");
		System.out.println("2- List companies");
		System.out.println("3- Show computer details");
		System.out.println("4- Create a computer");
		System.out.println("5- Update a computer");
		System.out.println("6- Delete a computer");

		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		System.out.println("Vous avez saisis " + choice);

	}
}
