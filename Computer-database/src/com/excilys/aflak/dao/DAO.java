package com.excilys.aflak.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection connect) {
		this.connect = connect;
	}

	/**
	 * Methode de creation
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

	/**
	 * Methode pour effacer
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);

	/**
	 * Methode de mise a jour
	 * 
	 * @param obj
	 * @return boolean
	 */

	public abstract boolean update(T obj);

	/**
	 * Methode de recherche d('informations
	 * 
	 * @param id
	 * @return T
	 */

	public abstract T find(int id);

	/**
	 * Methode qui liste
	 * 
	 * @return <T>
	 */
	public abstract List<T> list();

}
