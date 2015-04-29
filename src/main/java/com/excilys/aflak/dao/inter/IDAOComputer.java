package com.excilys.aflak.dao.inter;

import java.sql.Connection;
import java.util.List;

import com.excilys.aflak.model.Computer;

public interface IDAOComputer extends IDAOCrud<Computer> {

	Connection connect = null;

	/**
	 * READ - list object
	 * 
	 * @return List object created
	 */
	public List<Computer> list();

	/**
	 * 
	 * @return List<Computer>
	 */

	List<Computer> getSomeComputers(int debut, int nbr);

	/**
	 * @return size Table of Computer
	 */

	int getSizeTabComputers();

	/**
	 * Filtred company or computer name
	 * 
	 * @param filtre
	 *            , order by, limit, debut
	 * @return Table of Computer
	 */
	List<Computer> getSomeFiltredComputer(String filtre, String colomun,
			String way, int limit, int debut);

	/**
	 * Size of tab filtred
	 * 
	 * @return size of filtred computer
	 */
	int getSizeFiltredComputer(String filtre);

	/**
	 * delete computer with Company id
	 * 
	 */
	void deleteComputerFromCompany(Long companyId);

}
