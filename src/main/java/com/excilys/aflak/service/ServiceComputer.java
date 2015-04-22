package com.excilys.aflak.service;

import java.util.List;

import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Computer;

// singeleton
// variable service => instance de l'enum
// toute les methode non static
// on passe par SERVICE pour acceder au methode

public enum ServiceComputer {
	SERVICE;

	public long createComputer(Computer computer) {
		return ComputerDAO.INSTANCE.create(computer);
	}

	public boolean deleteComputer(long id) {
		return ComputerDAO.INSTANCE.delete(id);
	}

	public Computer updateComputer(Computer computer) {
		return ComputerDAO.INSTANCE.update(computer);
	}

	public Computer findComputer(long id) {
		return ComputerDAO.INSTANCE.find(id);
	}

	public List<Computer> getAllComputers() {
		return ComputerDAO.INSTANCE.list();
	}

	public List<Computer> getSomeComputers(int debut, int nbr) {
		return ComputerDAO.INSTANCE.getSomeComputers(debut, nbr);
	}

	public int getSizeTabComputers() {
		return ComputerDAO.INSTANCE.getSizeTabCommputers();
	}

	public List<Computer> getSomeFiltredComputer(String filtre, int limit,
			int debut) {
		return ComputerDAO.INSTANCE
				.getSomeFiltredComputer(filtre, limit, debut);
	}

	public int getSizeFiltredComputer(String filtre) {
		return ComputerDAO.INSTANCE.getSizeFiltredComputer(filtre);
	}
}
