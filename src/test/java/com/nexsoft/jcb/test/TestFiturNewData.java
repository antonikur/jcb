package com.nexsoft.jcb.test;

import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBNewDataPage;
import com.nexsoft.jcb.pom.JCBReportPage;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

public class TestFiturNewData {
	
	protected WebDriver driver;
	
	public void stopForMoment(int miliSec) {
		try {
			Thread.sleep(miliSec);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	
 
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
	
	@Test(priority = 1)
	public void cek_fitur_filter_newData() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickMenuWorklist();
		home.clickAndGotoMenuNewData();
		JCBNewDataPage newData = PageFactory.initElements(driver, JCBNewDataPage.class);
		newData.clickBtnFilter();
		newData.selectDropdownListEntriesByValue("76");
		newData.clickBtnFilter();
		
		stopForMoment(2000);
		String actualString = driver.findElement(By.xpath("//h4[normalize-space()='DOMINO']")).getText();
		assertTrue(actualString.contains("DOMINO"));
		stopForMoment(2000);
		
		
		newData.LogOut();
		newData.gotoLoginPage();
	}
	
	@Test(priority = 1)
	public void cek_fitur_search_newData() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickMenuWorklist();
		home.clickAndGotoMenuNewData();
		JCBNewDataPage newData = PageFactory.initElements(driver, JCBNewDataPage.class);
		stopForMoment(2000);
		newData.clickSearchField("Domino");
		newData.clickBtnSearch();
		
		
		
		newData.LogOut();
		newData.gotoLoginPage();
	}
	
	
	

}
