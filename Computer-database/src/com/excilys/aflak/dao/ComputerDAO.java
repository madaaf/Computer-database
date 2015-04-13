package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.model.Company;
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Statement state = SdzConnection.getInstance().createStatement();
			result = state.executeUpdate("DELETE FROM computer WHERE id = "
					+ id);
			// return 1 if ok, 0 id not
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		Company company = new Company();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id WHERE computer.id ="
									+ id);
			if (result.first()) {
				company = new Company(result.getInt("company_id"),
						result.getString("company_name"));
				computer = new Computer(result.getInt("id"),
						result.getString("name"),
						result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"), company);
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
		Company company = new Company();
		try {

			Statement state = SdzConnection.getInstance().createStatement();
			ResultSet result = state
					.executeQuery("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id");

			while (result.next()) {
				company = new Company(result.getInt("company_id"),
						result.getString("company_name"));
				computer = new Computer(result.getInt("id"),
						result.getString("name"),
						result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"), company);
				listComputer.add(computer);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listComputer;
	}
}
