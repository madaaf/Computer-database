package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		Company company = new Company();
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
}
