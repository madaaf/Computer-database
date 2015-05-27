package com.excilys.aflak.service;

import com.excilys.aflak.model.Computer;
import com.excilys.aflak.persistence.dao.impl.ComputerDAO;
import com.excilys.aflak.persistence.dto.PageRequest;
import com.excilys.aflak.service.validator.ComputerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
		System.err.println(computer.toString());
		System.err.println(dao.toString());
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

	//FIXME remove. Pagerequest does the job
	public List<Computer> list(int debut, int nbr) {
		return dao.list(debut, nbr);
	}


	public int count() {
		return dao.count();
	}

	//TODO Here we should return a Page<Computer> with the current count IN THE SAME TRANSACTION
	public List<Computer> list(PageRequest request) {
		// TODO
		// Page<Computer> p = new Page<>();
		// p.setElements(dao.list(request));
		// p.setTotalCount(dao.count(request.filter));
		// return p;
		return dao.list(request);

	}

	public int count(String filtre) {
		return dao.count(ComputerValidator.validateFilter(filtre));
	}
}
