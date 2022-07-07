package com.nexsoft.jcb.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.pom.JCBDashboardPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBReportPage;

import net.bytebuddy.implementation.Implementation.Context.Disabled;

public class TestModulReport {

	protected WebDriver driver;

	private File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public void delay(int inInt) {
		try {
			Thread.sleep(inInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void initial() {
		System.setProperty("url", "https://dev.ptdika.com/jcb/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
//		wait = new WebDriverWait(driver, 20);
		driver.get(System.getProperty("url"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(addItem));

		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void reset() {
		driver.get(System.getProperty("url"));
	}

	@Test(priority = 1)
	public void input_valid_data_report_master_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		delay(1000);
		login.inputFieldUsername("titan").inputFieldPassword("titan").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.selectDropdownListEntriesByValue("1");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		assertTrue(fileName.contains("laporan"), "Data tidak ada/tidak sesuai");
		delay(1000);
		report.LogOut().gotoLoginPage();

	}

	@Test(priority = 2)
	public void input_valid_data_report_master_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		delay(1000);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.selectDropdownListEntriesByValue("1");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		assertTrue(fileName.contains("report_merchant_isue"), "Data tidak ada/tidak sesuai");
		delay(1000);
		report.LogOut().gotoLoginPage();

	}

	@Test(priority = 3)
	public void input_valid_data_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		delay(1000);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.selectDropdownListEntriesByValue("2");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		assertTrue(fileName.contains("laporan"), "Data tidak ada/tidak sesuai");
		delay(1000);
		report.LogOut().gotoLoginPage();

	}

	@Test(priority = 4)
	public void input_valid_data_report_merchant_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.selectDropdownListEntriesByValue("2");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		assertTrue(fileName.contains("report_merchant_isue"), "Data tidak ada/tidak sesuai");
		delay(1000);
		report.LogOut().gotoLoginPage();

	}

	@Test(priority = 5)
	public void input_invalid_data_tanggal_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
//		report.selectDropdownListEntriesByValue("0");
		report.inputFieldStartDate("2022-08-08");
		report.inputFieldEndDate("2022-01-10");
		report.clickBtnProcess();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = false;
		}
		assertFalse(cek);
//		assertFalse(fileName.contains("report_merchant_isue"), "Data berhasil di download");
		delay(1000);

		report.LogOut().gotoLoginPage();
		
	}

	@Test(priority = 6)
	public void input_invalid_data_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = false;
		}
		assertFalse(cek);
		delay(1000);

		report.LogOut().gotoLoginPage();

	}

	@Test(priority = 7)
	public void input_invalid_data_report_merchant_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		delay(1000);
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = false;
		}
		assertFalse(cek);
		delay(1000);

		report.LogOut().gotoLoginPage();

	}

}
