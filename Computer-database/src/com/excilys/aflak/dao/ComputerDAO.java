package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.model.Computer;

public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"SELECT * FROM computer WHERE id =" + id);
			if (result.first()) {
				computer = new Computer(id, result.getString("name"), null,
						null, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return computer;
	}

	@Override
	public List<Computer> list() {
		List<Computer> listComputer = new ArrayList<Computer>();
		Computer computer = new Computer();
		try {

			Statement state = SdzConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("select * from computer");
			ResultSetMetaData resultMeta = result.getMetaData();

			int k = 0;

			while (result.next()) {
				System.out.print(k + " ");
				k++;

				computer = new Computer(result.getInt("id"),
						result.getString("name"), null, null, null);
				/*
				 * for (int i = 1; i <= resultMeta.getColumnCount(); i++) { if
				 * (result.getObject(i) != null) {
				 * 
				 * System.out.print("\t" + result.getObject(i).toString() +
				 * "\t |"); } else { System.out.print("\t" + null + "\t |"); }
				 * 
				 * }
				 */
				listComputer.add(computer);
				System.out.println(computer.getId() + " " + computer.getName());
				System.out.println("SIZAE " + listComputer.size());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listComputer;
	}
}
