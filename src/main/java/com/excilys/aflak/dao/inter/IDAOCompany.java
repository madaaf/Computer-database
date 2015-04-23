package com.excilys.aflak.dao.inter;

import java.sql.Connection;
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

	Company find(long id);

	/**
	 * 
	 */
	void delete(long id, Connection connect);

	void delete(long id);
}
