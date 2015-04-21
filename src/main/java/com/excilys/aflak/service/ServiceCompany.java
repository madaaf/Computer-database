package com.excilys.aflak.service;

import java.util.List;

import com.excilys.aflak.dao.model.CompanyDAO;
import com.excilys.aflak.model.Company;

public enum ServiceCompany {
	SERVICE;

	public List<Company> getAllCompanies() {
		return CompanyDAO.INSTANCE.list();
	}

	public Company findCompany(int id) {
		return CompanyDAO.INSTANCE.find(id);
	}
}
