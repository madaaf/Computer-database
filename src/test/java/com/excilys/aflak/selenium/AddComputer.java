package com.excilys.aflak.selenium;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
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
import org.openqa.selenium.support.ui.Select;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.ExecuteScript;

public class AddComputer {
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
	public void testAddFullComputer() throws Exception {
		driver.get(baseUrl
				+ "/Computer-database/index?page=0&limit=10&search=a&colomn=computer.introduced&way=ASC");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("soef");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("19-10-1992");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("19-10-2000");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Netronics");
		driver.findElement(By.id("addComputer")).click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/index");

		Computer ref = new Computer(7, "soef", LocalDateTime.of(1992, 10, 19,
				00, 00, 00), LocalDateTime.of(2000, 10, 19, 00, 00, 00),
				new Company(4, "Netronics"));

		Computer com = ServiceComputer.SERVICE.findComputer(7);

		Assert.assertEquals(com, ref);
	}

	@Test
	public void testAddFalseComputer() throws Exception {

		driver.get(baseUrl + "/Computer-database/index");
		driver.findElement(By.id("addComputer")).click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("mada");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("deed");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("dede");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("Netronics");
		driver.findElement(By.id("addComputer")).click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/addComputer");
		Computer com = ServiceComputer.SERVICE.findComputer(7);
		Assert.assertEquals(com.getId(), 0);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		ExecuteScript.execute();
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
