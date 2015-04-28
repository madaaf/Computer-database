package com.excilys.aflak.service;

import java.util.List;

import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.Validator;

// singeleton
// variable service => instance de l'enum
// toute les methode non static
// on passe par SERVICE pour acceder au methode

public enum ComputerService {
	SERVICE;

	public long createComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
		return ComputerDAO.INSTANCE.create(computer);
	}

	public boolean deleteComputer(long id) {
		return ComputerDAO.INSTANCE.delete(id);
	}

	public Computer updateComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
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
		return ComputerDAO.INSTANCE.getSizeTabComputers();
	}

	public List<Computer> getSomeFiltredComputer(String filtre, String colomn,
			String way, int debut, int limit) {
		return ComputerDAO.INSTANCE.getSomeFiltredComputer(
				Validator.getFilter(filtre), Validator.getColomn(colomn),
				Validator.getWay(way), debut, limit);
	}

	public int getSizeFiltredComputer(String filtre) {
		return ComputerDAO.INSTANCE.getSizeFiltredComputer(Validator
				.getFilter(filtre));
	}
}
