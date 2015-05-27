package com.excilys.aflak.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.Builder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;

//import com.excilys.aflak.dao.CompanyDAO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContextTest.xml")
public class CompanyServiceTest {
	List<Company> listCompanies = null;
	@Autowired
	private CompanyService service;
	@Autowired
	private ComputerService computerService;

	@Before
	public void setUp() throws Exception {
		listCompanies = new ArrayList<Company>();
		listCompanies = new ArrayList<Company>();
		listCompanies.add(Builder.crateDefaultCompany().withId(1L)
				.withName("Apple Inc.").build());
		listCompanies.add(Builder.crateDefaultCompany().withId(2L)
				.withName("Thinking Machines").build());
		listCompanies.add(Builder.crateDefaultCompany().withId(3L)
				.withName("RCA").build());
		listCompanies.add(Builder.crateDefaultCompany().withId(4L)
				.withName("Netronics").build());
		ExecuteScript.execute();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void list() {
		List<Company> bddCompany = new ArrayList<Company>();
		bddCompany = service.getAllCompanies();
		Assert.assertArrayEquals(bddCompany.toArray(), listCompanies.toArray());
	}

	@Test
	public void deleteCompany() {
		// checked if the company is removed
		service.deleteCompany(1);
		Company company = service.findCompany(1);
		Assert.assertNull(company);
		// checked if the transaction works
		// and the computers associated with the company id are removed
		Computer comp1 = computerService.find(1);
		Assert.assertNull(comp1);
		Computer comp2 = computerService.find(6);
		Assert.assertNull(comp2);
	}
}