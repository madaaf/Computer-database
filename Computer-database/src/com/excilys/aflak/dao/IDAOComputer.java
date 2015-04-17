package com.excilys.aflak.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.aflak.model.Computer;

public interface IDAOComputer {

	Connection connect = null;

	/**
	 * Methode de creation
	 * 
	 * @param obj
	 * @return boolean
	 */
	boolean create(Computer obj);

	/**
	 * Methode pour effacer
	 * 
	 * @param id
	 * @return boolean
	 */
	boolean delete(int id);

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

	Computer find(int id);

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

}
