package com.excilys.aflak.service;

import java.util.List;

import com.excilys.aflak.dao.ComputerDAO;
import com.excilys.aflak.model.Computer;

public abstract class ServiceComputer {

	public static boolean createComputer(Computer computer) {
		return ComputerDAO.INSTANCE.create(computer);
	}

	public static boolean deleteComputer(int id) {
		return ComputerDAO.INSTANCE.delete(id);
	}

	public static Computer updateComputer(Computer computer) {
		return ComputerDAO.INSTANCE.update(computer);
	}

	public static Computer findComputer(int id) {
		return ComputerDAO.INSTANCE.find(id);
	}

	public static List<Computer> getAllComputers() {
		return ComputerDAO.INSTANCE.list();
	}

	public static List<Computer> getSomeComputers(int debut, int nbr) {
		return ComputerDAO.INSTANCE.getSomeComputers(debut, nbr);
	}
}
