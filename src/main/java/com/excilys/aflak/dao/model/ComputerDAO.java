package com.excilys.aflak.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.connection.DAOException;
import com.excilys.aflak.dao.inter.IDAOComputer;
import com.excilys.aflak.dao.mapper.MapperDAO;
import com.excilys.aflak.dao.utils.TimeConvertorDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;

public enum ComputerDAO implements IDAOComputer {

	INSTANCE;
	private int compteur = 0;
	private static final int LIMIT = 10;

	@Override
	public long create(Computer computer) {
		PreparedStatement state = null;
		Connection connect = ConnectionBdd.getConnection();
		ResultSet set = null;
		long idComputer;
		try {
			state = connect
					.prepareStatement(
							"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)",
							state.RETURN_GENERATED_KEYS);
			state.setString(1, computer.getName());
			state.setTimestamp(2, TimeConvertorDAO
					.convertLocalDateTimeToTimestamp(computer.getIntroduced()));
			state.setTimestamp(3,
					TimeConvertorDAO.convertLocalDateTimeToTimestamp(computer
							.getDiscontinued()));
			if (computer.getCompany().getId() == (-1)) {
				state.setNull(4, Types.BIGINT);
			} else {
				state.setLong(4, (computer.getCompany().getId()));
			}

			state.executeUpdate();
			set = state.getGeneratedKeys();
			set.next();
			// retourn l'id du computer
			idComputer = set.getLong(1);

		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);

		} finally {
			ConnectionBdd.closeConnection(connect, state, set);
		}
		return idComputer;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		try {
			state = connect
					.prepareStatement("DELETE FROM computer WHERE id = ?");
			if (id > 0) {
				state.setLong(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}
			result = state.executeUpdate();
			if (result == 0) {
				throw new DAOException("This ID doesn't exist in the bdd");
			}
			return true;
		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);

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
			state.setTimestamp(2, TimeConvertorDAO
					.convertLocalDateTimeToTimestamp(computer.getIntroduced()));
			state.setTimestamp(3,
					TimeConvertorDAO.convertLocalDateTimeToTimestamp(computer
							.getDiscontinued()));
			if (computer.getCompany().getId() > 0) {
				state.setLong(4, computer.getCompany().getId());
			} else {
				state.setNull(4, Types.BIGINT);
			}

			if (computer.getId() > 0) {
				state.setLong(5, computer.getId());
			} else {
				state.setNull(5, Types.BIGINT);
			}

			int result = state.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, null);
		}
		return null;
	}

	@Override
	public Computer find(long id) {
		Computer computer = new Computer();
		Company company;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = connect
					.prepareStatement("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id WHERE computer.id = ?");
			if (id > 0) {
				state.setLong(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}

			result = state.executeQuery();

			if (result.first()) {
				computer = MapperDAO.mapComputer(result);
			}
		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
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
				computer = MapperDAO.mapComputer(result);
				listComputer.add(computer);

			}

		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}

		return listComputer;
	}

	@Override
	public List<Computer> getSomeComputers(int debut, int nbr) {
		List<Computer> list = new ArrayList<Computer>();
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		Computer computer = new Computer();
		try {
			state = connect
					.prepareStatement("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id LIMIT "
							+ nbr + " OFFSET " + debut);
			result = state.executeQuery();
			while (result.next()) {
				computer = MapperDAO.mapComputer(result);
				list.add(computer);
			}
			compteur++;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}

		return list;
	}

	@Override
	public int getSizeTabCommputers() {
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		int size = 0;
		try {
			state = connect.prepareStatement("select COUNT(*) from computer");
			result = state.executeQuery();
			if (result.next()) {
				size = result.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, null);
		}
		return size;
	}

	@Override
	public List<Computer> getSomeFiltredComputer(String filtre, String colomn,
			int debut, int limit) {
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		List<Computer> list = new ArrayList<Computer>();
		Computer computer = new Computer();
		try {
			state = connect
					.prepareStatement("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on computer.company_id = company.id WHERE computer.name like ? or company.name like ? ORDER BY "
							+ colomn + " DESC LIMIT ? OFFSET ?");
			filtre = "%" + filtre + "%";
			state.setString(1, filtre);
			state.setString(2, filtre);
			state.setInt(3, limit);
			state.setInt(4, debut);
			System.out
					.println("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on computer.company_id = company.id WHERE computer.name like "
							+ filtre
							+ " or company.name like "
							+ filtre
							+ " ORDER BY "
							+ colomn
							+ " LIMIT "
							+ limit
							+ " OFFSET ?" + debut);
			System.out.println(filtre + " " + colomn + " " + limit + " "
					+ debut);
			result = state.executeQuery();
			while (result.next()) {
				computer = MapperDAO.mapComputer(result);
				System.out.println(computer.getName());
				list.add(computer);
			}
		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}
		return list;
	}

	@Override
	public int getSizeFiltredComputer(String filtre) {
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		int size = 0;
		try {
			state = connect
					.prepareStatement("SELECT  COUNT(*) from computer left join company on computer.company_id = company.id WHERE computer.name like ? or company.name like ? ");
			filtre = "%" + filtre + "%";
			state.setString(1, filtre);
			state.setString(2, filtre);
			result = state.executeQuery();
			while (result.next()) {
				size = result.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("Connection Failed " + e);
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}
		return size;
	}

}
