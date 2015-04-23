package com.excilys.aflak.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.CompanyDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.utils.ExecuteScript;

//import com.excilys.aflak.dao.CompanyDAO;

public class CompanyDAOTest {
	List<Company> listCompanies = null;

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.POOLCONNECTIONS.TEST = true;
		listCompanies = new ArrayList<Company>();
		listCompanies.add(new Company(1, "Apple Inc."));
		listCompanies.add(new Company(2, "Thinking Machines"));
		listCompanies.add(new Company(3, "RCA"));
		listCompanies.add(new Company(4, "Netronics"));
		ExecuteScript.execute();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void list() {
		List<Company> bddCompany = new ArrayList<Company>();
		bddCompany = CompanyDAO.INSTANCE.list();
		Assert.assertArrayEquals(bddCompany.toArray(), listCompanies.toArray());

	}

}
