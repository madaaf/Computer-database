package com.excilys.aflak.selenium;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;
import com.excilys.aflak.utils.Mapper;

public class SearchComputer {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExecuteScript.execute();
	}

	@Test
	public void testSearchComputer() throws Exception {
		driver.get(baseUrl + "/Computer-database/index");
		driver.findElement(By.id("searchbox")).clear();
		driver.findElement(By.id("searchbox")).sendKeys("cm");
		driver.findElement(By.id("searchsubmit")).click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/index?search=cm");
		List<ComputerDTO> listFiltred = new ArrayList<ComputerDTO>();

		for (Computer computer : ComputerService.SERVICE
				.getSomeFiltredComputer("cm", null, null, 0, 10)) {
			listFiltred.add(Mapper.computerToComputerDTO(computer));
		}

		Assert.assertEquals(listFiltred.size(), 4);
		Assert.assertEquals(listFiltred.get(0).getId(), 2);
		Assert.assertEquals(listFiltred.get(1).getId(), 3);
		Assert.assertEquals(listFiltred.get(2).getId(), 4);
		Assert.assertEquals(listFiltred.get(3).getId(), 5);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
