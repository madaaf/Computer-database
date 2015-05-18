package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void createComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
		System.err.println(computer.toString());
		System.err.println(dao.toString());
		dao.create(computer);
	}

	@Transactional
	public void deleteComputer(Long id) {
		dao.delete(id);
	}

	@Transactional
	public void updateComputer(Computer computer) {
		computer.setIntroduced(Validator.localDateTimeValidFormat(computer
				.getIntroduced()));
		computer.setDiscontinued(Validator.localDateTimeValidFormat(computer
				.getDiscontinued()));
		dao.update(computer);
	}

	@Transactional(readOnly = true)
	public Computer findComputer(long id) {
		return dao.find(id);
	}

	@Transactional(readOnly = true)
	public List<Computer> getAllComputers() {
		return dao.list();
	}

	@Transactional(readOnly = true)
	public List<Computer> getSomeComputers(int debut, int nbr) {
		return dao.getSomeComputers(debut, nbr);
	}

	@Transactional(readOnly = true)
	public int getSizeTabComputers() {
		return dao.getSizeTabComputers();
	}

	@Transactional(readOnly = true)
	public List<Computer> getSomeFiltredComputer(String filtre, String colomn,
			String way, int debut, int limit) {
		return dao.getSomeFilteredComputer(Validator.getFilter(filtre),
				Validator.getColomn(colomn), Validator.getWay(way), debut,
				limit);
	}

	@Transactional(readOnly = true)
	public int getSizeFiltredComputer(String filtre) {
		return dao.getSizeFilteredComputer(Validator.getFilter(filtre));
	}
}
