package com.excilys.aflak.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.ExecuteScript;

public class ComputerDAOTest {
	private ArrayList<Computer> listComputersTest = null;

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.TEST = true;
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
		ExecuteScript.execute();
	}

	@Test
	public void findOne() {

		Computer computer = ComputerDAO.INSTANCE.find(2);
		Assert.assertEquals(computer, listComputersTest.get(1));
	}

	@Test
	public void getAll() {

		List<Computer> dbComputer = new ArrayList<Computer>();
		dbComputer = ComputerDAO.INSTANCE.list();

		Assert.assertArrayEquals(dbComputer.toArray(),
				listComputersTest.toArray());
	}

	@Test
	public void create() {
		// Computer to add
		Computer com = new Computer(-1, "MacBook Pro 15.4 inch", null, null,
				new Company(1, "Apple Inc."));

		// insert the computer in the bdd
		ComputerDAO.INSTANCE.create(com);
		// get the last computer in the bdd
		Computer lastComputer = ComputerDAO.INSTANCE.find(7);
		com.setId(7);
		Assert.assertEquals(com, lastComputer);
	}

	@Test
	public void delete() {
		ComputerDAO.INSTANCE.delete(6);
		Computer com = ComputerDAO.INSTANCE.find(6);
		Assert.assertEquals(0, com.getId());
	}

	@Test
	public void update() {
		Computer com = new Computer(1, "Modified", null, null, new Company(1,
				"Apple Inc."));
		ComputerDAO.INSTANCE.update(com);
		Computer com2 = ComputerDAO.INSTANCE.find(1);
		Assert.assertEquals(com, com2);
	}
}
