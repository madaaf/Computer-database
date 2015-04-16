package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.TimeConvertor;

public enum ComputerDAO implements IDAOComputer {

	INSTANCE;
	private static int compteur = 0;
	private static final int LIMIT = 10;

	@Override
	public boolean create(Computer computer) {
		PreparedStatement state = null;
		Connection connect = ConnectionBdd.getConnection();
		try {
			state = connect
					.prepareStatement("INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)");
			state.setString(1, computer.getName());
			state.setTimestamp(2, TimeConvertor
					.convertLocalDateTimeToTimestamp(computer.getIntroduced()));
			state.setTimestamp(3,
					TimeConvertor.convertLocalDateTimeToTimestamp(computer
							.getDiscontinued()));
			if (computer.getCompany().getId() == (-1)) {
				state.setNull(4, Types.BIGINT);
			} else {
				state.setInt(4, (computer.getCompany().getId()));
			}

			int resultat = state.executeUpdate();
			if (resultat == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionBdd.closeConnection(connect, state, null);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		try {
			state = connect
					.prepareStatement("DELETE FROM computer WHERE id = ?");
			if (id > 0) {
				state.setInt(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}
			result = state.executeUpdate();
			if (result == 0) {
				throw new DAOException("This ID doesn't exist in the bdd");
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Connection Failed");

		} finally {
			ConnectionBdd.closeConnection(connect, state, null);
		}

	}

	@Override
	public Computer update(Computer computer) {
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;

		try {
			state = connect
					.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");

			state.setString(1, computer.getName());
			state.setTimestamp(2, TimeConvertor
					.convertLocalDateTimeToTimestamp(computer.getIntroduced()));
			state.setTimestamp(3,
					TimeConvertor.convertLocalDateTimeToTimestamp(computer
							.getDiscontinued()));
			if (computer.getCompany().getId() > 0) {
				state.setInt(4, computer.getCompany().getId());
			} else {
				state.setNull(4, Types.BIGINT);
			}

			if (computer.getId() > 0) {
				state.setInt(5, computer.getId());
			} else {
				state.setNull(5, Types.BIGINT);
			}

			int result = state.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, null);
		}
		return null;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		Company company;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id WHERE computer.id = ?");
			if (id > 0) {
				state.setInt(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}

			result = state.executeQuery();

			if (result.first()) {
				computer = Mapper.mapComputer(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}
		// TODO Auto-generated method stub
		return computer;
	}

	@Override
	public List<Computer> list() {
		List<Computer> listComputer = new ArrayList<Computer>();
		Computer computer = new Computer();
		Company company;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {

			state = connect
					.prepareStatement("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id");

			result = state.executeQuery();
			while (result.next()) {
				computer = Mapper.mapComputer(result);
				listComputer.add(computer);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}

		return listComputer;
	}

	@Override
	public List<Computer> getSomeComputers() {
		List<Computer> list = new ArrayList<Computer>();
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		Computer computer = new Computer();
		Company company;
		try {
			state = connect
					.prepareStatement("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id LIMIT "
							+ LIMIT + " OFFSET " + compteur * LIMIT);
			result = state.executeQuery();
			while (result.next()) {
				computer = Mapper.mapComputer(result);
				list.add(computer);
			}
			compteur++;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}

		return list;
	}

}
