package com.excilys.aflak;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utils.ExecuteScript;

import com.excilys.aflak.dao.impl.CompanyDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Company.Builder;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistenceContextTest.xml")
public class CompanyDAOTest {
	List<Company> listCompanies = null;
	@Autowired
	private CompanyDAO dao;

	@Before
	public void setUp() throws Exception {
		listCompanies = new ArrayList<Company>();
		listCompanies.add(Builder.builder().id(1L).name("Apple Inc.").build());
		listCompanies.add(Builder.builder().id(2L).name("Thinking Machines")
				.build());
		listCompanies.add(Builder.builder().id(3L).name("RCA").build());
		listCompanies.add(Builder.builder().id(4L).name("Netronics").build());
		ExecuteScript.execute();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void list() {
		List<Company> bddCompany = new ArrayList<Company>();
		bddCompany = dao.list();
		Assert.assertArrayEquals(bddCompany.toArray(), listCompanies.toArray());
	}

	@Test
	public void find() {
		Company comp = dao.find(1L);
		Assert.assertEquals(comp, listCompanies.get(0));
	}
}