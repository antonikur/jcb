package com.nexsoft.jcb.test;

import static org.testng.Assert.assertFalse;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.WideForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import com.nexsoft.jcb.pom.JCBReportPage;

public class TestModulReport {

	protected WebDriver driver;
	protected Tools tools = new Tools();
	protected String driverHandle;

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
		driverHandle = driver.getWindowHandle();
	}

	@AfterMethod
	public void logout() {
//		tools.stopForMoment(5000);
//		driver.switchTo().window(driver.getWindowHandle());
		tools.stopForMoment(2000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));
		driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();
	}

	@AfterClass
	public void closeDriver() {
		tools.stopForMoment(2000);
		driver.close();
	}

	public void close_second_tab() {


		/// ============
		System.out.println(driverHandle);
		System.out.println(driver.getWindowHandle());
		driver.switchTo().window(driverHandle);
		
	}

	@Test(priority = 0)
	public void click_menu_report_dari_halaman_utama() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		tools.stopForMoment(1000);
		login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);

		boolean isDisplayed = report.getTextReportPage().isDisplayed();
		assertTrue(isDisplayed);
//		logout();
	}

	@Test(priority = 1)
	public void input_valid_data_report_master_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		tools.stopForMoment(1000);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.selectDropdownListEntriesByValue("1");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		close_second_tab();
		tools.stopForMoment(5000);
		assertTrue(fileName.contains("laporan"), "Data tidak ada/tidak sesuai");
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 2)
	public void input_valid_data_report_master_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		tools.stopForMoment(1000);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.selectDropdownListEntriesByValue("1");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		close_second_tab();
		tools.stopForMoment(2000);
		assertTrue(fileName.contains("laporan"), "Data tidak ada/tidak sesuai");
		
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 3)
	public void input_valid_data_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		tools.stopForMoment(1000);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.selectDropdownListEntriesByValue("2");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();

		assertTrue(fileName.contains("report_merchant_isue"), "Data tidak ada/tidak sesuai");
		close_second_tab();
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 4)
	public void input_valid_data_report_merchant_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.selectDropdownListEntriesByValue("2");
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();

		close_second_tab();
		assertTrue(fileName.contains("report_merchant_isue"), "Data tidak ada/tidak sesuai");
		
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 5)
	public void input_invalid_data_tanggal_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
//		report.selectDropdownListEntriesByValue("0");
		report.inputFieldStartDate("2022-08-08");
		report.inputFieldEndDate("2022-01-10");
		report.clickBtnProcess();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = true;
		}
		tools.screenShoot(driver);
		close_second_tab();
		assertFalse(cek);
		

//		assertFalse(fileName.contains("report_merchant_isue"), "Data berhasil di download");
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 6)
	public void input_invalid_data_report_merchant_click_process() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcess();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = true;
		}
		tools.screenShoot(driver);

		close_second_tab();
		assertFalse(cek);
		
		tools.stopForMoment(1000);
//		logout();
	}

	@Test(priority = 7)
	public void input_invalid_data_report_merchant_click_process_new_temp() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin().gotoHomePage();
		JCBHomePage home = PageFactory.initElements(driver, JCBHomePage.class);
		home.clickAndGotoMenuReport();
		JCBReportPage report = PageFactory.initElements(driver, JCBReportPage.class);
		tools.stopForMoment(1000);
		report.inputFieldStartDate("2022-06-20");
		report.inputFieldEndDate("2022-06-30");
		report.clickBtnProcessNewTemplate();
		tools.stopForMoment();

		String downloadPath = "C:\\Users\\NEXSOFT\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		boolean cek = true;
		if (fileName.contains("report_merchant_isue")) {
			cek = true;
		}
		tools.screenShoot(driver);

		close_second_tab();
		assertFalse(cek);
		
		tools.stopForMoment(1000);
	}

}
