package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean create(Computer computer) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement state = ConnectionBdd
					.getInstance()
					.prepareStatement(
							"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)");
			state.setString(1, computer.getName());
			state.setTimestamp(2, computer.getIntroduced());
			state.setTimestamp(3, computer.getDiscontinued());
			state.setInt(4, computer.getCompanyId());
			int resultat = state.executeUpdate();
			if (resultat == 1) {
				System.out.println("Your computer is created");
				return true;
			} else {
				System.err.println("Your computer is not created");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Conection failed");
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Statement state = ConnectionBdd.getInstance().createStatement();
			result = state.executeUpdate("DELETE FROM computer WHERE id = "
					+ id);
			if (result == 0) {
				System.err.println(" This ID doesn't exist in the bdd");
				return false;
			}

			return true;
		} catch (Exception e) {
			System.err.println("Connection failed");
			return false;
		}

	}

	@Override
	public Computer update(Computer computer) {
		try {
			StringBuilder query = new StringBuilder("UPDATE computer SET");
			Boolean haveBefore = false;

			if (!(computer.getName() == null)) {
				query.append(" name = '").append(computer.getName())
						.append("'");
				haveBefore = true;
			}

			if (!(computer.getIntroduced() == null)) {
				if (haveBefore)
					query.append(",");
				query.append(" introduced = '")
						.append(computer.getIntroduced()).append("'");
				haveBefore = true;
			}
			if (!(computer.getDiscontinued() == null)) {
				if (haveBefore)
					query.append(",");
				query.append(" discontinued = '")
						.append(computer.getDiscontinued()).append("'");
				haveBefore = true;
			}

			query.append(" where id = ").append(computer.getId());

			Statement state = ConnectionBdd.getInstance().createStatement();
			int result = state.executeUpdate(query.toString());

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
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

			Statement state = ConnectionBdd.getInstance().createStatement();
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
