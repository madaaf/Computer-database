package com.excilys.aflak.persistence.dao;

import com.excilys.aflak.model.Computer;
import com.excilys.aflak.persistence.dto.PageRequest;

import java.util.List;

public interface IComputerDAO extends ICrudDAO<Computer> {


	/**
	 * READ - list object
	 *
	 * @return List object createdcomputer.name
	 */
	public List<Computer> list();

	/**
	 *
	 * @return List<Computer>
	 */

	List<Computer> list(int debut, int nbr);

	/**
	 * @return size Table of Computer
	 */

	int count();

	/**
	 * Filtred company or computer name
	 *
	 * @param request
	 * @return Table of Computer
	 */
	List<Computer> list(PageRequest request);

	/**
	 * Size of tab filtred
	 *
	 * @return size of filtred computer
	 */
	int count(String filtre);

	/**
	 * delete computer with Company id
	 *
	 */
	void deleteComputerFromCompany(Long companyId);

}
