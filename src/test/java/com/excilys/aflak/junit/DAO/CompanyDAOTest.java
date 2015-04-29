package com.excilys.aflak.junit.DAO;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.CompanyDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.utils.ExecuteScript;

public class CompanyDAOTest {
	List<Company> listCompanies = null;

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.POOLCONNECTIONS.setTest(true);
		listCompanies = new ArrayList<Company>();
		listCompanies.add(CompanyBuilder.crateDefaultCompany().withId(1L)
				.withName("Apple Inc.").build());

		listCompanies.add(CompanyBuilder.crateDefaultCompany().withId(2L)
				.withName("Thinking Machines").build());

		listCompanies.add(CompanyBuilder.crateDefaultCompany().withId(3L)
				.withName("RCA").build());

		listCompanies.add(CompanyBuilder.crateDefaultCompany().withId(4L)
				.withName("Netronics").build());

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
