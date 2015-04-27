package com.excilys.aflak.service;

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
		Boolean isDeleted;
		try {

			ConnectionBdd.POOLCONNECTIONS.startTransaction();
			ComputerDAO.INSTANCE.deleteComputerFromCompany(companyId);
			isDeleted = CompanyDAO.INSTANCE.delete(companyId);
			ConnectionBdd.POOLCONNECTIONS.commit();
			if (isDeleted) {
				System.out.println("Votre companie a bien été supprimée");
			} else {
				System.out.println("Aucune companie n'a été supprimé");
			}

		} catch (DAOException e) {
			// ne vas pas commiter
			try {
				ConnectionBdd.POOLCONNECTIONS.rollback();
			} catch (Exception e2) {
				e2.getStackTrace();
			}

			e.getStackTrace();

		} finally {
			ConnectionBdd.POOLCONNECTIONS.closeConnection();
		}

	}
}
