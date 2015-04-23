package com.excilys.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.excilys.aflak.dao.connection.ConnectionBdd;
import com.excilys.aflak.dao.model.ComputerDAO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.utils.ExecuteScript;

public class DeleteComputer {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		ConnectionBdd.POOLCONNECTIONS.setTest(true);
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExecuteScript.execute();
	}

	@Test
	public void testDeleteComputer() throws Exception {
		driver.get(baseUrl + "/Computer-database/index?page=0");
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.name("cb")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/index?deletedSuccess");
		ComputerDAO.INSTANCE.delete(6);
		Computer com = ComputerDAO.INSTANCE.find(6);
		Assert.assertEquals(0, com.getId());
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
