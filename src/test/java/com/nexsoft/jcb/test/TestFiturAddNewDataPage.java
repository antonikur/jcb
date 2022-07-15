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
import com.nexsoft.jcb.pom.JCBAddNewDataPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBNewDataVisitPage;

public class TestFiturAddNewDataPage {

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
	}
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}

	@Test(priority = 1)
	public void add_New_Data_with_valid_data1() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "DOMINO";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "Titan";
		String noTelpPIC = "0856666";
		String position = "Owner";
		String merchantAcc = "1";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();

		assertTrue(isAddNewDataSuccessDisplayed);

	}

	@Test(priority = 2)
	public void add_New_Data_with_valid_data2() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "Domino";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "Titan";
		String noTelpPIC = "0856666";
		String position = "Owner";
		String merchantAcc = "2";
		String businessIssue = "MDR";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);
		addNewData.selectDropdownListBusinessIssueEntriesByText(businessIssue);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();

		assertTrue(isAddNewDataSuccessDisplayed);

	}

	@Test(priority = 3)
	public void add_New_Data_with_valid_data3() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "Domino";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "Titan";
		String noTelpPIC = "08676767";
		String position = "Owner";
		String merchantAcc = "2";
		String businessIssue = "Others";
		String reason = "Test 123";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);
		addNewData.selectDropdownListBusinessIssueEntriesByText(businessIssue);
		tools.stopForMoment(1000);
		addNewData.inputReason(reason);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();

		assertTrue(isAddNewDataSuccessDisplayed);

	}

	@Test(priority = 3)
	public void add_New_Data_with_valid_data4() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "Domino";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "Titan";
		String noTelpPIC = "08676767";
		String position = "Owner";
		String merchantAcc = "3";
		String businessIssue = "MDR";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);
		addNewData.selectDropdownListBusinessIssueEntriesByText(businessIssue);
		tools.stopForMoment(1000);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();

		assertTrue(isAddNewDataSuccessDisplayed);

	}

	@Test(priority = 5)
	public void add_New_Data_with_invalid_data1() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "*))*&%$$&+*@&^#$^@";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "Amin";
		String noTelpPIC = "08676767";
		String position = "Owner";
		String merchantAcc = "1";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
		tools.screenShoot(driver);
		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();
		tools.screenShoot(driver);

		assertTrue(!isAddNewDataSuccessDisplayed,
				"Seharusnya data tidak berhasil disimpan,karena data inputan tidak valid(tidak ada validasi untuk field input Merchant Name)");

	}

	@Test(priority = 6)
	public void add_New_Data_with_invalid_data2() {
		String kota = "JAKARTA";
		String area = "76";
		String batch = "November 2021";
		String merchant = "Domino";
		String address = "Jalan Scientia";
		String addressByFloor = "G1";
		String category = "F&B";
		String PIC = "0856666";
		String noTelpPIC = "Titan";
		String position = "Owner";
		String merchantAcc = "1";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBAddNewDataPage addNewData = home.clickAndGotoMenuAddNewData();
		tools.stopForMoment(1000);

		addNewData.selectDropdownListKotaEntriesByValue(kota);
		addNewData.selectDropdownListAreaEntriesByValue(area);
		addNewData.selectDropdownListBatchEntriesByValue(batch);
		addNewData.inputMerchantName(merchant);
		addNewData.inputAddress(address);
		addNewData.inputAddressByFloor(addressByFloor);
		addNewData.inputCategory(category);
		addNewData.inputPIC(PIC);
		addNewData.inputNoTelpPIC(noTelpPIC);
		addNewData.selectDropdownListPositionEntriesByValue(position);
		addNewData.selectDropdownListMerchantAccJCBCardEntriesByValue(merchantAcc);

		addNewData.clickBtnSubmit();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
		
		boolean isAddNewDataSuccessDisplayed = newDataVis.getTextNotifAddDataSuccess().isDisplayed();
		tools.screenShoot(driver);
		assertTrue(!isAddNewDataSuccessDisplayed,
				"Seharusnya data tidak berhasil disimpan,karena data inputan tidak valid(tidak ada validasi untuk field input PIC dan No Telp PIC)");

	}

}
