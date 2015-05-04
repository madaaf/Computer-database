package com.excilys.aflak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.CompanyDAO;
import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Company;

import exception.DAOException;

@Repository
public class CompanyService {
	// SERVICE;
	@Autowired
	private CompanyDAO dao;
	@Autowired
	private ComputerDAO daoComputer;

	public List<Company> getAllCompanies() {
		return dao.list();
	}

	public Company findCompany(long id) {
		return dao.find(id);
	}

	public void deleteCompany(long companyId) {
		Boolean isDeleted;
		try {

			ConnectionBdd.POOLCONNECTIONS.startTransaction();
			daoComputer.deleteComputerFromCompany(companyId);
			isDeleted = dao.delete(companyId);
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
