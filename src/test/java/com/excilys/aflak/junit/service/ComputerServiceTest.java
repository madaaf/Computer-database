package com.excilys.aflak.junit.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;
import com.excilys.aflak.utils.TimeConvertor;

public class ComputerServiceTest {
	private ArrayList<Computer> listComputersTest = null;

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.POOLCONNECTIONS.setTest(true);
		listComputersTest = new ArrayList<Computer>();

		listComputersTest.add(ComputerBuilder
				.createDefaultComputer()
				.withId(1L)
				.withName("MacBook Pro 15.4 inch")
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build());

		listComputersTest
				.add(ComputerBuilder
						.createDefaultComputer()
						.withId(2L)
						.withName("CM-2a")
						.withCompany(
								CompanyBuilder.crateDefaultCompany().withId(2L)
										.withName("Thinking Machines").build())
						.build());

		listComputersTest
				.add(ComputerBuilder
						.createDefaultComputer()
						.withId(3L)
						.withName("CM-200")
						.withCompany(
								CompanyBuilder.crateDefaultCompany().withId(2L)
										.withName("Thinking Machines").build())
						.build());

		listComputersTest
				.add(ComputerBuilder
						.createDefaultComputer()
						.withId(4L)
						.withName("CM-5e")
						.withCompany(
								CompanyBuilder.crateDefaultCompany().withId(2L)
										.withName("Thinking Machines").build())
						.build());

		listComputersTest
				.add(ComputerBuilder
						.createDefaultComputer()
						.withId(5L)
						.withName("CM-5")
						.withIntroduced(
								LocalDateTime.of(1991, 01, 01, 00, 00, 00))
						.withCompany(
								CompanyBuilder.crateDefaultCompany().withId(2L)
										.withName("Thinking Machines").build())
						.build());

		listComputersTest.add(ComputerBuilder
				.createDefaultComputer()
				.withId(6L)
				.withName("MacBook Pro")
				.withIntroduced(LocalDateTime.of(2006, 01, 10, 00, 00, 00))
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build());
		ExecuteScript.execute();
	}

	@Test
	public void findOne() {

		Computer computer = ComputerService.SERVICE.findComputer(2);
		Assert.assertEquals(computer, listComputersTest.get(1));
	}

	@Test
	public void getAll() {

		List<Computer> dbComputer = new ArrayList<Computer>();
		dbComputer = ComputerService.SERVICE.getAllComputers();

		Assert.assertArrayEquals(dbComputer.toArray(),
				listComputersTest.toArray());
	}

	@Test
	public void createWithNullDate() {
		// Computer to add
		Computer com = ComputerBuilder
				.createDefaultComputer()
				.withName("MacBook Pro 15.4 inch")
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build();

		// insert the computer in the bdd
		ComputerService.SERVICE.createComputer(com);
		// get the last computer in the bdd
		Computer lastComputer = ComputerService.SERVICE.findComputer(7);
		com.setId(7L);
		Assert.assertEquals(com, lastComputer);
	}

	@Test
	public void createWithFalseDate() {
		// Computer to add
		Computer com = ComputerBuilder
				.createDefaultComputer()
				.withName("MacBook Pro 15.4 inch")
				.withIntroduced(
						TimeConvertor
								.convertStringToLocalDateTime("19-ded-2000"))
				.withDiscontinued(
						TimeConvertor
								.convertStringToLocalDateTime("30-02-2000"))
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build();

		// insert the computer in the bdd
		ComputerService.SERVICE.createComputer(com);
		// get the last computer in the bdd
		Computer lastComputer = ComputerService.SERVICE.findComputer(7);
		// Assert.assertNull(lastComputer);
	}

	@Test
	public void search() {
		// Computer to add
		// Computer to add
		Computer com = ComputerBuilder
				.createDefaultComputer()
				.withName("MacBook Pro 15.4 inch")
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build();

		// insert the computer in the bdd
		ComputerService.SERVICE.getSomeFiltredComputer("cb", null, null, 0, 0);

	}

	@Test
	public void delete() {
		ComputerService.SERVICE.deleteComputer(6);
		Computer com = ComputerService.SERVICE.findComputer(6);
		Assert.assertNull(com);
	}

	@Test
	public void update() {
		Computer com = ComputerBuilder
				.createDefaultComputer()
				.withId(1L)
				.withName("Modified")
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build();
		ComputerService.SERVICE.updateComputer(com);
		Computer com2 = ComputerService.SERVICE.findComputer(1);
		Assert.assertEquals(com, com2);
	}
}
