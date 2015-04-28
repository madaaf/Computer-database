package com.excilys.aflak.junit.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;
import com.excilys.aflak.utils.TimeConvertor;

public class ComputerServiceTest {
	private ArrayList<Computer> listComputersTest = null;

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.POOLCONNECTIONS.setTest(true);
		listComputersTest = new ArrayList<Computer>();

		listComputersTest.add(new Computer(1, "MacBook Pro 15.4 inch", null,
				null, new Company(1, "Apple Inc.")));
		listComputersTest.add(new Computer(2, "CM-2a", null, null, new Company(
				2, "Thinking Machines")));
		listComputersTest.add(new Computer(3, "CM-200", null, null,
				new Company(2, "Thinking Machines")));
		listComputersTest.add(new Computer(4, "CM-5e", null, null, new Company(
				2, "Thinking Machines")));
		listComputersTest
				.add(new Computer(5, "CM-5", LocalDateTime.of(1991, 01, 01, 00,
						00, 00), null, new Company(2, "Thinking Machines")));
		listComputersTest.add(new Computer(6, "MacBook Pro", LocalDateTime.of(
				2006, 01, 10, 00, 00, 00), null, new Company(1, "Apple Inc.")));

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
		Computer com = new Computer(-1, "MacBook Pro 15.4 inch", null, null,
				new Company(1, "Apple Inc."));

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
		Computer com = new Computer(-1, "MacBook Pro 15.4 inch",
				TimeConvertor.convertStringToLocalDateTime("19-ded-2000"),
				TimeConvertor.convertStringToLocalDateTime("30-02-2000"),
				new Company(1, "Apple Inc."));

		// insert the computer in the bdd
		ComputerService.SERVICE.createComputer(com);
		// get the last computer in the bdd
		Computer lastComputer = ComputerService.SERVICE.findComputer(7);
		// Assert.assertNull(lastComputer);
	}

	@Test
	public void search() {
		// Computer to add
		Computer com = new Computer(-1, "MacBook Pro 15.4 inch", null, null,
				new Company(1, "Apple Inc."));

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
		Computer com = new Computer(1, "Modified", null, null, new Company(1,
				"Apple Inc."));
		ComputerService.SERVICE.updateComputer(com);
		Computer com2 = ComputerService.SERVICE.findComputer(1);
		Assert.assertEquals(com, com2);
	}
}
