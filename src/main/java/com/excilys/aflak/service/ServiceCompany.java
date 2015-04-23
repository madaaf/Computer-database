package com.excilys.aflak.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.CompanyDAO;
import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Company;

import exception.DAOException;

public enum ServiceCompany {
	SERVICE;

	public List<Company> getAllCompanies() {
		return CompanyDAO.INSTANCE.list();
	}

	public Company findCompany(long id) {
		return CompanyDAO.INSTANCE.find(id);
	}

	public void deleteCompany(long companyId) {
		Connection connect = ConnectionBdd.POOLCONNECTIONS.getConnection();
		try {
			connect.setAutoCommit(false);
			ComputerDAO.INSTANCE.deleteComputerFromCompany(companyId, connect);
			CompanyDAO.INSTANCE.delete(companyId, connect);
			connect.commit();

		} catch (DAOException | SQLException e) {
			try {
				// ne vas pas commiter
				connect.rollback();
				throw new DAOException("Delation Failed : Rollback" + e);
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Rollback Failed " + e);
			}

		} finally {
			ConnectionBdd.POOLCONNECTIONS.closeConnection(connect, null, null);
		}

	}
}
