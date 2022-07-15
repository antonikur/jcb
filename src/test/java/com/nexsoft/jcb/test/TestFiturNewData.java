package com.nexsoft.jcb.test;

import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBNewDataPage;
import org.testng.annotations.BeforeMethod;
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

public class TestFiturNewData {

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
	}
	@AfterClass
	public void closeDriver() {
		driver.close();
	}

	@Test(priority = 1)
	public void cek_fitur_filter_newData() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);

		home.clickMenuWorklist();
		home.clickAndGotoMenuNewData();
		JCBNewDataPage newData = PageFactory.initElements(driver, JCBNewDataPage.class);
		newData.clickBtnFilter();
		tools.stopForMoment(2000);
		newData.selectDropdownListEntriesByValue("76");
		tools.stopForMoment(2000);
		newData.clickBtnFilter();
		String selected = newData.getSelectedOption();

		tools.stopForMoment(2000);
		String textArea = newData.clickBtnVisitAndGoToVisitPage().getTextArea();

		

		assertTrue(selected.contains(textArea));
	}

	@Test(priority = 2)
	public void cek_fitur_search_newData() {
		String keyword = "domino";
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		Tools tools = PageFactory.initElements(driver, Tools.class);
		home.clickMenuWorklist();
		home.clickAndGotoMenuNewData();
		JCBNewDataPage newData = PageFactory.initElements(driver, JCBNewDataPage.class);
		tools.stopForMoment(2000);
		newData.clickSearchField(keyword);
		newData.clickBtnSearch();
		tools.stopForMoment(1000);

		boolean isCorrect = false;

		List<WebElement> columnName = newData.getListTableColumnName();
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
