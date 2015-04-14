package com.excilys.aflak.service;

import java.util.List;

import com.excilys.aflak.dao.CompanyDAO;
import com.excilys.aflak.model.Company;

public class ServiceCompany {

	public static List<Company> getAllCompanies() {
		return CompanyDAO.INSTANCE.list();
	}
}
