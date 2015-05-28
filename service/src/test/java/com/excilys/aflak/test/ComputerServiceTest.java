package com.excilys.aflak.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContextTest.xml")
public class ComputerServiceTest {
	@Autowired
	private ComputerService service;
	private ArrayList<Computer> listComputersTest = null;

	@Before
	public void setUp() throws Exception {
		listComputersTest = new ArrayList<Computer>();
		listComputersTest.add(Computer.Builder
				.builder()
				.id(1L)
				.name("MacBook Pro 15.4 inch")
				.company(
						Company.Builder.builder().id(1L).name("Apple Inc.")
								.build()).build());
		listComputersTest.add(Computer.Builder
				.builder()
				.id(2L)
				.name("CM-2a")
				.company(
						Builder.builder().id(2L).name("Thinking Machines")
								.build()).build());
		listComputersTest.add(Computer.Builder
				.builder()
				.id(3L)
				.name("CM-200")
				.company(
						Builder.builder().id(2L).name("Thinking Machines")
								.build()).build());
		listComputersTest.add(Computer.Builder
				.builder()
				.id(4L)
				.name("CM-5e")
				.company(
						Company.Builder.builder().id(2L)
								.name("Thinking Machines").build()).build());
		listComputersTest.add(Computer.Builder
				.builder()
				.id(5L)
				.name("CM-5")
				.introduced(LocalDateTime.of(1991, 01, 01, 00, 00, 00))
				.company(
						Company.Builder.builder().id(2L)
								.name("Thinking Machines").build()).build());
		listComputersTest.add(Computer.Builder
				.builder()
				.id(6L)
				.name("MacBook Pro")
				.introduced(LocalDateTime.of(2006, 01, 10, 00, 00, 00))
				.company(
						Company.Builder.builder().id(1L).name("Apple Inc.")
								.build()).build());
		ExecuteScript.execute();
	}

	@Test
	public void findOne() {
		Computer computer = service.find(2);
		Assert.assertEquals(computer, listComputersTest.get(1));
	}

	@Test
	public void getAll() {
		List<Computer> dbComputer = new ArrayList<Computer>();
		dbComputer = service.list();
		Assert.assertArrayEquals(dbComputer.toArray(),
				listComputersTest.toArray());
	}

	@Test
	public void createWithNullDate() {
		// Computer to add
		Computer com = Computer.Builder
				.builder()
				.name("MacBook Pro 15.4 inch")
				.company(
						Company.Builder.builder().id(1L).name("Apple Inc.")
								.build()).build();
		// insert the computer in the bdd
		service.create(com);
		// get the last computer in the bdd
		Computer lastComputer = service.find(7);
		com.setId(7L);
		Assert.assertEquals(com, lastComputer);
	}

	@Test
	public void createWithFalseDate() {
		/*
		 * Locale locale = LocaleContextHolder.getLocale(); Pattern language =
		 * Pattern.map(locale.getLanguage()); // Computer to add Computer com =
		 * ComputerBuilder .createDefaultComputer()
		 * .withName("MacBook Pro 15.4 inch") .withIntroduced(
		 * TimeConvertor.convertStringToLocalDateTime( "19-ded-2000", language))
		 * .withDiscontinued( TimeConvertor.convertStringToLocalDateTime(
		 * "30-02-2000", language)) .withCompany(
		 * CompanyBuilder.crateDefaultCompany().withId(1L)
		 * .withName("Apple Inc.").build()).build(); // insert the computer in
		 * the bdd service.createComputer(com); // get the last computer in the
		 * bdd Computer lastComputer = service.findComputer(7);
		 * Assert.assertNull(lastComputer);
		 */
	}

	@Test
	public void delete() {
		service.delete(6L);
		Computer com = service.find(6);
		Assert.assertNull(com);
	}

	@Test
	public void update() {
		Computer com = Computer.Builder
				.builder()
				.id(1L)
				.name("Modified")
				.company(
						Company.Builder.builder().id(1L).name("Apple Inc.")
								.build()).build();
		service.update(com);
		Computer com2 = service.find(1L);
		Assert.assertEquals(com, com2);
	}
}