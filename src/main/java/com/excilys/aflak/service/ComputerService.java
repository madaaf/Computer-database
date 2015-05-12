package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.Validator;

// singeleton
// variable service => instance de l'enum
// toute les methode non static
// on passe par SERVICE pour acceder au methode
@Service
public class ComputerService {
	// SERVICE;
	@Autowired
	private ComputerDAO dao;

	public long createComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
		return dao.create(computer);
	}

	public boolean deleteComputer(Long id) {
		return dao.delete(id);
	}

	public boolean updateComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
		return dao.update(computer);
	}

	public Computer findComputer(long id) {
		return dao.find(id);
	}

	public List<Computer> getAllComputers() {
		return dao.list();
	}

	public List<Computer> getSomeComputers(int debut, int nbr) {
		return dao.getSomeComputers(debut, nbr);
	}

	public int getSizeTabComputers() {
		return dao.getSizeTabComputers();
	}

	public List<Computer> getSomeFiltredComputer(String filtre, String colomn,
			String way, int debut, int limit) {
		return dao.getSomeFilteredComputer(Validator.getFilter(filtre),
				Validator.getColomn(colomn), Validator.getWay(way), debut,
				limit);
	}

	public int getSizeFiltredComputer(String filtre) {
		return dao.getSizeFilteredComputer(Validator.getFilter(filtre));
	}
}
