package com.excilys.aflak.persistence.dao;

import com.excilys.aflak.model.Company;

import java.util.List;

public interface ICompanyDAO extends ICrudDAO<Company> {
	/**
	 *
	 *
	 * @return <Computer>
	 */

	List<Company> list();

}
