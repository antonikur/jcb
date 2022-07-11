package com.nexsoft.jcb.test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBReturnOtherConPage;

import com.nexsoft.jcb.pom.JCBReturnPage;

public class TestFiturReturnOtherCondition {
	WebDriver driver;
	protected Tools tools = new Tools();

	@BeforeClass
	public void initial() {
		System.setProperty("url", "https://dev.ptdika.com/jcb/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get(System.getProperty("url"));

		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void reset() {
		driver.get(System.getProperty("url"));
	}

	@AfterMethod
	public void logout() {
		tools.stopForMoment(5000);
		driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();
		;

	}

	@Test(priority = 1)
	public void selectVisitStatusWithoutInputOtherStatus() {
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnOtherConditionAndGoToOtherConditionPage();

		JCBReturnOtherConPage returnOtherCon = PageFactory.initElements(driver, JCBReturnOtherConPage.class);

		returnOtherCon.selectDropdownListVisitStatusByValue("Merchant Refuse-0");

		returnOtherCon.clickBtnSubmit();

		boolean isVisitUpdateSuccessDisplayed = returnPage.getTextSuccessUpdateVisitStatus().isDisplayed();
		assertTrue(isVisitUpdateSuccessDisplayed);

	}

	@Test(priority = 2)
	public void selectVisitStatusWithInputOtherStatus() {
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnOtherConditionAndGoToOtherConditionPage();

		JCBReturnOtherConPage returnOtherCon = PageFactory.initElements(driver, JCBReturnOtherConPage.class);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//select[@id='resume']")));
		returnOtherCon.selectDropdownListVisitStatusByValue("Other-0");
		tools.stopForMoment();
		returnOtherCon.inputOtherVisitStatus("Test123");
		returnOtherCon.clickBtnSubmit();

		boolean isVisitUpdateSuccessDisplayed = returnPage.getTextSuccessUpdateVisitStatus().isDisplayed();
		assertTrue(isVisitUpdateSuccessDisplayed);

	}
}
