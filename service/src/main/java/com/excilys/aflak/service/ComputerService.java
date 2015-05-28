package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.aflak.dao.impl.ComputerDAO;
import com.excilys.aflak.model.Computer;

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
	public void create(Computer computer) {
		computer.setIntroduced(computer.getIntroduced());
		computer.setDiscontinued(computer.getDiscontinued());
		dao.create(computer);
	}

	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional
	public void update(Computer computer) {
		dao.update(computer);
	}

	@Transactional(readOnly = true)
	public Computer find(long id) {
		return dao.find(id);
	}

	@Transactional(readOnly = true)
	public List<Computer> list() {
		return dao.list();
	}

	@Transactional(readOnly = true)
	public List<Computer> list(int debut, int nbr) {
		return dao.list(debut, nbr);
	}

	@Transactional(readOnly = true)
	public int count() {
		return dao.count();
	}

	@Transactional(readOnly = true)
	public List<Computer> list(String filtre, String colomn, String way,
			int debut, int limit) {
		return dao.list(Validator.getFilter(filtre),
				Validator.getColomn(colomn), Validator.getWay(way), debut,
				limit);
	}

	@Transactional(readOnly = true)
	public int count(String filtre) {
		return dao.count(Validator.getFilter(filtre));
	}
}
