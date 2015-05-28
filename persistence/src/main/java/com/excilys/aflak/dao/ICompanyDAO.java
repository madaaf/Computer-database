package com.excilys.aflak.dao;

import java.util.List;

import com.excilys.aflak.model.Company;

public interface ICompanyDAO extends ICrudDAO<Company> {
	/**
	 * Methode qui liste
	 * 
	 * @return <Computer>
	 */

	List<Company> list();

}
