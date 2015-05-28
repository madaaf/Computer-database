package com.excilys.aflak.dao;

import java.util.HashMap;
import java.util.List;

import com.excilys.aflak.model.Computer;

public interface IComputerDAO extends ICrudDAO<Computer> {

	public static String getColumn(String s) {
		HashMap<String, String> column = new HashMap<>();
		column.put("computer.name", "computer.name");
		column.put("computer.introduced", "computer.introduced");
		column.put("computer.discontinued", "computer.discontinued");
		column.put("company.name", "company.name");
		column.put("computer.id", "computer.id");
		if (column.containsKey(s)) {
			return column.get(s);
		}
		return column.get("computer.id");
	}

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
	 * @param filtre
	 *            , order by, limit, debut
	 * @return Table of Computer
	 */
	List<Computer> list(String filtre, String column, String way, int debut,
			int limit);

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
