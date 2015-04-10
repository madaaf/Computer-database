package com.excilys.aflak.dao;

import java.util.List;

import com.excilys.aflak.model.Computer;

public class Connect {
	public static void main(String[] args) {
		DAO<Computer> computerDao = new ComputerDAO(SdzConnection.getInstance());
		Computer c = computerDao.find(86);
		List<Computer> lc = computerDao.list();
		System.out.print("LCV " + lc.size());
		// System.out.println("ELEVE NUM " + c.getId() + " " + c.getName());
		/*
		 * try {
		 * 
		 * 
		 * 
		 * Statement state = SdzConnection.getInstance().createStatement();
		 * ResultSet result = state.executeQuery("select * from company");
		 * 
		 * ResultSetMetaData resultMeta = result.getMetaData();
		 * 
		 * System.out .println(
		 * "\n******************************************************************************************************"
		 * ); // on affiche le nom des colonnes for (int i = 1; i <=
		 * resultMeta.getColumnCount(); i++) { System.out.print("\t" +
		 * resultMeta.getColumnName(i).toUpperCase() + "\t *"); }
		 * 
		 * System.out .println(
		 * "\n******************************************************************************************************"
		 * ); while (result.next()) { for (int i = 1; i <=
		 * resultMeta.getColumnCount(); i++) { if (result.getObject(i) != null)
		 * { System.out.print("\t" + result.getObject(i).toString() + "\t |"); }
		 * else { System.out.print("\t" + null + "\t |"); }for (int i = 1; i <=
		 * 
		 * }
		 * 
		 * System.out .println(
		 * "\n******************************************************************************************************"
		 * ); }
		 * 
		 * result.close(); state.close(); } catch (Exception e) { // TODO:
		 * handle exception e.printStackTrace(); }
		 */
	}

}
