package com.excilys.aflak.dao.inter;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.excilys.aflak.model.Computer;

public interface IDAOComputer extends IDAOCrud<Computer> {

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

	Connection connect = null;

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
	List<Computer> getSomeFilteredComputer(String filtre, String column,
			String way, int limit, int debut);

	/**
	 * Size of tab filtred
	 * 
	 * @return size of filtred computer
	 */
	int getSizeFilteredComputer(String filtre);

	/**
	 * delete computer with Company id
	 * 
	 */
	void deleteComputerFromCompany(Long companyId);

}
