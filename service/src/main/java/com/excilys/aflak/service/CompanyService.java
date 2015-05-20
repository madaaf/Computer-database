package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.CompanyDAO;
import com.excilys.aflak.model.ComputerDAO;

@Service
public class CompanyService {
	// SERVICE;
	@Autowired
	private CompanyDAO dao;
	@Autowired
	private ComputerDAO daoComputer;

	@Transactional(readOnly = true)
	public List<Company> getAllCompanies() {
		return dao.list();
	}

	@Transactional(readOnly = true)
	public Company findCompany(long id) {
		return dao.find(id);
	}

	@Transactional
	public void deleteCompany(long companyId) {
		daoComputer.deleteComputerFromCompany(companyId);
		dao.delete(companyId);

	}
}
