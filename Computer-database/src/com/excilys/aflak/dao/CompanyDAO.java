package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.utils.Mapper;

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
				company = Mapper.mapCompany(result);
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
	public Company find(int id) {
		Connection connect = ConnectionBdd.getConnection();
		ResultSet result = null;
		PreparedStatement state = null;
		Company company = null;
		try {
			state = connect
					.prepareStatement("SELECT * from company WHERE id = ?");

			if (id > 0) {
				state.setInt(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}

			result = state.executeQuery();
			if (result.first()) {
				company = Mapper.mapCompany(result);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionBdd.closeConnection(connect, state, result);
		}
		return company;
	}
}
