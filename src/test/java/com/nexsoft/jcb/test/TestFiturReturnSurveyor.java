package com.nexsoft.jcb.test;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.nexsoft.jcb.pom.JCBReturnPage;

public class TestFiturReturnSurveyor {

	protected WebDriver driver;
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
	public void cek_fitur_filter_return() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		home.clickAndGotoMenuReturn();
		JCBReturnPage ret = PageFactory.initElements(driver, JCBReturnPage.class);

		ret.clickBtnFilter();
		tools.stopForMoment(2000);
		ret.selectDropdownListEntriesByValue("76");
		tools.stopForMoment(2000);
		ret.clickBtnFilter();
		String selected = ret.getSelectedOption();

		tools.stopForMoment(2000);
		String textArea = ret.clickBtnVisitAndGoToVisitPage().getTextArea();


		assertTrue(selected.contains(textArea));
	}

	@Test(priority = 2)
	public void cek_fitur_search_return() {
		String keyword = "domino";
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		Tools tools = PageFactory.initElements(driver, Tools.class);
		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		home.clickAndGotoMenuReturn();
		JCBReturnPage ret = PageFactory.initElements(driver, JCBReturnPage.class);
		tools.stopForMoment(2000);
		ret.clickSearchField(keyword);
		tools.stopForMoment(1000);
		ret.clickBtnSearch();
		tools.stopForMoment(1000);
		boolean isCorrect = false;

		List<WebElement> columnName = ret.getListTableColumnName();
		for (int i = 0; i < columnName.size(); i++) {
			if (columnName.get(i).getText().toLowerCase().contains(keyword.toLowerCase())) {

				isCorrect = true;

			} else {
				isCorrect = false;
				break;
			}

		}
		
		assertTrue(isCorrect);

	}

}
