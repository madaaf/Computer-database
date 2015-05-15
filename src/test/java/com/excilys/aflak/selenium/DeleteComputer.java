package com.excilys.aflak.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.ExecuteScript;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class DeleteComputer {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Autowired
	private ComputerService serviceComputer;

	@Before
	public void setUp() throws Exception {
		ExecuteScript.execute();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDeleteComputer() throws Exception {
		driver.get(baseUrl + "/Computer-database/index");
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[6]")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(closeAlertAndGetItsText()
				.matches(
						"^Are you sure you want to delete the selected computers[\\s\\S]$"));
		Assert.assertEquals(driver.getCurrentUrl(),
				"http://localhost:8080/Computer-database/index?deletedSuccess");
		Computer com = serviceComputer.findComputer(6);
		Assert.assertNull(com);
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
