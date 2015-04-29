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

import com.excilys.aflak.model.Company.CompanyBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;

public class EditComputer {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		ExecuteScript.execute();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testEditRightComputer() throws Exception {
		driver.get(baseUrl + "/Computer-database/index");
		driver.findElement(
				By.xpath("//a[contains(text(),'1  MacBook Pro 15.4 inch')]"))
				.click();
		driver.findElement(By.id("computerName")).clear();
		driver.findElement(By.id("computerName")).sendKeys("MacBook Pro 15.4");
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("19-10-1993");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("19-10-2000");
		new Select(driver.findElement(By.id("companyId")))
				.selectByVisibleText("RCA");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/index");

		Computer comp = ComputerService.SERVICE.findComputer(1);
		Computer ref = ComputerBuilder
				.createDefaultComputer()
				.withId(1L)
				.withName("MacBook Pro 15.4")
				.withIntroduced(LocalDateTime.of(1993, 10, 19, 00, 00, 00))
				.withDiscontinued(LocalDateTime.of(2000, 10, 19, 00, 00, 00))
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(3L)
								.withName("RCA").build()).build();

		Assert.assertEquals(comp, ref);
	}

	@Test
	public void testEditFalseComputer() throws Exception {
		driver.get(baseUrl + "/Computer-database/index");
		driver.findElement(By.xpath("//a[contains(text(),'3  CM-200')]"))
				.click();
		driver.findElement(By.id("introduced")).clear();
		driver.findElement(By.id("introduced")).sendKeys("dzdezd");
		driver.findElement(By.id("discontinued")).clear();
		driver.findElement(By.id("discontinued")).sendKeys("z");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/editComputer?id=3");

		Computer comp = ComputerService.SERVICE.findComputer(3);
		Computer ref = ComputerBuilder
				.createDefaultComputer()
				.withId(3L)
				.withName("CM-200")
				.withCompany(
						CompanyBuilder.crateDefaultCompany().withId(2L)
								.withName("Thinking Machines").build()).build();

		Assert.assertEquals(comp, ref);

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
