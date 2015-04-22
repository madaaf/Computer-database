package com.excilys.aflak.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.inter.IDAOCompany;
import com.excilys.aflak.dao.mapper.MapperDAO;
import com.excilys.aflak.model.Company;

public enum CompanyDAO implements IDAOCompany {
	// toute les methode en static
	// singleton
	INSTANCE; // static and final

	@Override
	public List<Company> list() {
		List<Company> listCompany = new ArrayList<Company>();
		Company company;
		Connection connect = ConnectionBdd.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {

			state = connect.prepareStatement("select * from company");
			result = state.executeQuery();

			while (result.next()) {
				company = MapperDAO.mapCompany(result);
				listCompany.add(company);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}

		return listCompany;
	}

	@Override
	public Company find(long id) {
		Connection connect = ConnectionBdd.getConnection();
		ResultSet result = null;
		PreparedStatement state = null;
		Company company = null;
		try {
			state = connect
					.prepareStatement("SELECT * from company WHERE id = ?");

			if (id > 0) {
				state.setLong(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}

			result = state.executeQuery();
			if (result.first()) {
				company = MapperDAO.mapCompany(result);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}
		return company;
	}
}
