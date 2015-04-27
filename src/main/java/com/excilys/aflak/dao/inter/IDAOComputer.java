package com.excilys.aflak.dao.inter;

import java.sql.Connection;
import java.util.List;

import com.excilys.aflak.model.Computer;

public interface IDAOComputer {

	Connection connect = null;

	/**
	 * Methode de creation
	 * 
	 * @param obj
	 * @return id computer created
	 */
	long create(Computer obj);

	/**
	 * Methode pour effacer
	 * 
	 * @param id
	 * @return boolean
	 */
	boolean delete(long id);

	/**
	 * Methode de mise a jour
	 * 
	 * @param obj
	 * @return boolean
	 */

	Computer update(Computer obj);

	/**
	 * Methode de recherche d('informations
	 * 
	 * @param id
	 * @return Computer
	 */

	Computer find(long id);

	/**
	 * Methode qui liste
	 * 
	 * @return List<Computer>
	 */
	List<Computer> list();

	/**
	 * 
	 * @return List<Computer>
	 */

	List<Computer> getSomeComputers(int debut, int nbr);

	/**
	 * @return size Table of Computer
	 */

	int getSizeTabCommputers();

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
	void deleteComputerFromCompany(long companyId);

}
