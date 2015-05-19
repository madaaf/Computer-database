package com.excilys.aflak.junit.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;
import com.excilys.aflak.utils.TimeConvertor;
import com.excilys.aflak.validator.Date.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ComputerServiceTest {
	@Autowired
	private ComputerService service;

	private ArrayList<Computer> listComputersTest = null;

	@Before
	public void setUp() throws Exception {
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

		Computer computer = service.findComputer(2);
		Assert.assertEquals(computer, listComputersTest.get(1));
	}

	@Test
	public void getAll() {

		List<Computer> dbComputer = new ArrayList<Computer>();
		dbComputer = service.getAllComputers();

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
		service.createComputer(com);
		// get the last computer in the bdd
		Computer lastComputer = service.findComputer(7);
		com.setId(7L);
		Assert.assertEquals(com, lastComputer);
	}

	@Test
	public void createWithFalseDate() {
		Locale locale = LocaleContextHolder.getLocale();
		Pattern language = Pattern.map(locale.getLanguage());
		// Computer to add
		Computer com = ComputerBuilder
				.createDefaultComputer()
				.withName("MacBook Pro 15.4 inch")
				.withIntroduced(
						TimeConvertor.convertStringToLocalDateTime(
								"19-ded-2000", language))
				.withDiscontinued(
						TimeConvertor.convertStringToLocalDateTime(
								"30-02-2000", language))
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(1L)
								.withName("Apple Inc.").build()).build();

		// insert the computer in the bdd
		service.createComputer(com);
		// get the last computer in the bdd
		Computer lastComputer = service.findComputer(7);
		// Assert.assertNull(lastComputer);
	}

	@Test
	public void delete() {
		service.deleteComputer(6L);
		Computer com = service.findComputer(6);
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
		service.updateComputer(com);
		Computer com2 = service.findComputer(1L);
		Assert.assertEquals(com, com2);
	}
}
