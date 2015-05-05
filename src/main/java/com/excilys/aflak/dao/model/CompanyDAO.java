package com.excilys.aflak.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.inter.IDAOCompany;
import com.excilys.aflak.dao.mapper.MapperDAO;
import com.excilys.aflak.model.Company;

import exception.DAOException;

@Repository
public class CompanyDAO implements IDAOCompany {
	// toute les methode en static
	// singleton
	// INSTANCE; // static and final
	@Autowired
	private ConnectionBdd bdd;

	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	@Override
	public List<Company> list() {
		List<Company> listCompany = new ArrayList<Company>();
		Company company;
		Connection connect = bdd.getConnection();
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
			logger.error("gets list company failed");
			throw new DAOException("get list company Failed " + e);
		}

		return listCompany;
	}

	@Override
	public Company find(Long id) {
		Connection connect = bdd.getConnection();
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
			logger.error("find company failed");
			throw new DAOException("find company Failed " + e);
		}
		return company;
	}

	@Override
	public boolean delete(Long id) {
		Connection connect = bdd.getConnection();
		PreparedStatement state = null;
		int result = 0;
		try {
			state = connect
					.prepareStatement("DELETE FROM company WHERE id = ?");
			if (id > 0) {
				state.setLong(1, id);
			} else {
				state.setNull(1, Types.BIGINT);
			}
			result = state.executeUpdate();
			System.out.println(result);

		} catch (Exception e) {
			logger.error("delete company failed");
			throw new DAOException("delete company Failed " + e);
		}
		if (result == 1)
			return true;
		return false;
	}

}
