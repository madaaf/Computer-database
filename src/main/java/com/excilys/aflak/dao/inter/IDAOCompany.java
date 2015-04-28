package com.excilys.aflak.dao.inter;

import java.util.List;

import com.excilys.aflak.model.Company;

public interface IDAOCompany {
	/**
	 * Methode qui liste
	 * 
	 * @return <Computer>
	 */
	List<Company> list();

	/**
	 * @param id
	 * @return company
	 */

	Company find(Long id);

	/**
	 * @param id
	 * @return boolean
	 */

	boolean delete(Long id);
}
