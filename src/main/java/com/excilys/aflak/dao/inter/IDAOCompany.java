package com.excilys.aflak.dao.inter;

import java.util.List;

import com.excilys.aflak.model.Company;

public interface IDAOCompany extends IDAOCrud<Company> {
	/**
	 * Methode qui liste
	 * 
	 * @return <Computer>
	 */

	List<Company> list();

}
