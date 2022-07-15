package com.nexsoft.jcb.test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBNewDataOtherConPage;
import com.nexsoft.jcb.pom.JCBNewDataPage;

public class TestFiturNewDataOtherCondition {
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
	
	@AfterClass
	public void closeDriver() {
		tools.stopForMoment(2000);
		driver.close();
	}

	@Test(priority = 1)
	public void selectVisitStatusWithoutInputOtherStatus() {
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnOtherConditionAndGoToOtherConditionPage();

		JCBNewDataOtherConPage newDataOtherCon = PageFactory.initElements(driver, JCBNewDataOtherConPage.class);
		
		newDataOtherCon.selectDropdownListVisitStatusByValue("Merchant Refuse-0");
		
		newDataOtherCon.clickBtnSubmit();
		
		boolean isVisitUpdateSuccessDisplayed = newData.getTextSuccessUpdateVisitStatus().isDisplayed();
		assertTrue(isVisitUpdateSuccessDisplayed);
		
	}
	
	@Test(priority = 2)
	public void selectVisitStatusWithInputOtherStatus() {
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnOtherConditionAndGoToOtherConditionPage();

		JCBNewDataOtherConPage newDataOtherCon = PageFactory.initElements(driver, JCBNewDataOtherConPage.class);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//select[@id='resume']")));
		newDataOtherCon.selectDropdownListVisitStatusByValue("Other-0");
		tools.stopForMoment();
		newDataOtherCon.inputOtherVisitStatus("Test123");
		newDataOtherCon.clickBtnSubmit();
		
		tools.stopForMoment();

		
		
		boolean isVisitUpdateSuccessDisplayed = newData.getTextSuccessUpdateVisitStatus().isDisplayed();
		assertTrue(isVisitUpdateSuccessDisplayed);
		
	}
}
