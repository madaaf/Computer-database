package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.aflak.dao.impl.ComputerDAO;
import com.excilys.aflak.dto.PageRequest;
import com.excilys.aflak.model.Computer;

// singeleton
// variable service => instance de l'enum
// toute les methode non static
// on passe par SERVICE pour acceder au methode
@Service
@Transactional(readOnly = true)
public class ComputerService {
	// SERVICE;
	@Autowired
	private ComputerDAO dao;

	@Transactional(readOnly = false)
	public void create(Computer computer) {
		computer.setIntroduced(computer.getIntroduced());
		computer.setDiscontinued(computer.getDiscontinued());
		dao.create(computer);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = false)
	public void update(Computer computer) {
		dao.update(computer);
	}

	public Computer find(long id) {
		return dao.find(id);
	}

	public List<Computer> list() {
		return dao.list();
	}

	public List<Computer> list(int debut, int nbr) {
		return dao.list(debut, nbr);
	}

	public int count() {
		return dao.count();
	}

	// public List<Computer> list(String filtre, String colomn, String way,
	// int debut, int limit) {
	// return dao.list(Validator.getFilter(filtre),
	// Validator.getColomn(colomn), Validator.getWay(way), debut,
	// limit);
	// }

	public List<Computer> list(PageRequest page) {
		System.err.println("SERVICE");
		System.err.println(page.toString());
		List<Computer> computers = dao.list(page.filter, page.field,
				page.sort.name(), page.pageSize * (page.pageNumber - 1),
				page.pageSize);
		return computers;

		// return dao.list(Validator.getFilter(filtre),
		// Validator.getColomn(colomn), Validator.getWay(way), debut,
		// limit);
	}

	public int count(String filtre) {
		return dao.count(Validator.getFilter(filtre));
	}
}
