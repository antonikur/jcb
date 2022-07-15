package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBNewDataPage;
import com.nexsoft.jcb.pom.JCBNewDataVisitPage;

import com.nexsoft.jcb.pom.JCBVisitMerchantUploadFoto;

public class TestFiturNewDataVisitPageSurveyor {
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
	public void edit_data_valid_data_merchant() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
		newDataVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		newDataVis.clickPICDataMerchant("anton");
		tools.stopForMoment(1000);
		newDataVis.clicknoTelpPICDataMerchant("00000");
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListPICEntriesByValue("Kasir");
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListMerchEntriesByValue("2");
		tools.stopForMoment(1000);
		newDataVis.clickBtnSaveDataMerchant();

		tools.stopForMoment();

		String resultEditPIC = newDataVis.getTextPIC();
		String resultEditNoTelpPIC = newDataVis.getTextNoTelpPIC();
		String resultEditPICPos = newDataVis.getTextPICPos();
		String resultMerchAcc = newDataVis.getTextMerchAcc();

		assertEquals("anton".toUpperCase(), resultEditPIC);
		assertEquals("00000", resultEditNoTelpPIC);
		assertEquals("Kasir", resultEditPICPos);
		assertEquals("2", resultMerchAcc);



	}

	@Test(priority = 2)
	public void edit_data_blank_data_merchant() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
		newDataVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		newDataVis.clickPICDataMerchant("");
		tools.stopForMoment(1000);
		newDataVis.clicknoTelpPICDataMerchant("");
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListPICEntriesByValue("Kasir");
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListMerchEntriesByValue("2");
		tools.stopForMoment(1000);
		newDataVis.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isPICReqDisplayed = newDataVis.getPICReq().isDisplayed();
		boolean isNoTelpPICReqDisplayed = newDataVis.getNoTelpPICReq().isDisplayed();

		newDataVis.clickBtnCancelEditDataMerchant();

		tools.stopForMoment(1000);
		tools.screenShoot(driver);

		assertTrue(isPICReqDisplayed && isNoTelpPICReqDisplayed);

	}

	@Test(priority = 3)
	public void edit_data_invalid_data_merchant() {
		String inputPICName = "0856234";
		String inputNoTelpPIC = "Bambang";
		String inputPICPos = "Kasir";
		String inputMerchAcc = "2";
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
		newDataVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		newDataVis.clickPICDataMerchant(inputPICName);
		tools.stopForMoment(1000);
		newDataVis.clicknoTelpPICDataMerchant(inputNoTelpPIC);
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListPICEntriesByValue(inputPICPos);
		tools.stopForMoment(1000);
		newDataVis.selectDropdownListMerchEntriesByValue(inputMerchAcc);
		tools.stopForMoment(1000);
		newDataVis.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isDisplayed = newDataVis.getTextNotifEditSuccess().isDisplayed();
		tools.screenShoot(driver);
		assertTrue(!isDisplayed);
		
	}

	@Test(priority = 4)
	public void add_TID_valid_data() {

		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(2000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		newDataVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListBankEntriesByValue(inputBank);
		newDataVis.inputMID(inputMID);
		newDataVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		newDataVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		newDataVis.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		tools.stopForMoment(5000);
		newDataVis.clickBtnSaveAddTID();
		tools.stopForMoment(25000);
		tools.scrollVerticalWindows(driver, -1000);
		tools.stopForMoment(5000);
		boolean isDisplayed = newDataVis.getTextNotifAddTIDSuccess().isDisplayed();

		assertTrue(isDisplayed);

	}

	@Test(priority = 5)
	public void add_TID_invalid_data_size_file() {

		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\7Mb.jpg";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		newDataVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListBankEntriesByValue(inputBank);
		newDataVis.inputMID(inputMID);
		newDataVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		newDataVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		newDataVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		newDataVis.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Upload error: The file you are attempting to uploa')]")));
		tools.stopForMoment(2000);
		boolean isDisplayed = newDataVis.getTextErrorUploadTIDFoto().isDisplayed();
		
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		newDataVis.clickBtnCancelAddTID();

	}

	@Test(priority = 6)
	public void add_TID_invalid_data_file_xlsx() {

		String inputBank = "BCA";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\nexSOFT SQA.xlsx";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		newDataVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListBankEntriesByValue(inputBank);
		newDataVis.inputMID(inputMID);
		newDataVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		newDataVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		newDataVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		newDataVis.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		
		boolean isDisplayed = newDataVis.getTextErrorUploadTID().isDisplayed();
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		newDataVis.clickBtnCancelAddTID();

	}

	@Test(priority = 7)
	public void add_TID_invalid_data_file_pdf() {

		String inputBank = "CIMB Niaga";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\JMeter.pdf";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		newDataVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListBankEntriesByValue(inputBank);
		newDataVis.inputMID(inputMID);
		newDataVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		newDataVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		newDataVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		newDataVis.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		boolean isDisplayed = newDataVis.getTextErrorUploadTID().isDisplayed();
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		newDataVis.clickBtnCancelAddTID();

	}

	@Test(priority = 8)
	public void collateral_choice() {

		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		String selectedSticker = newDataVis.selectDropdownListCollStickerEntriesByValue(inputSticker);
		String selectedStandLogo = newDataVis.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = newDataVis.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		String selectedCardHolder = newDataVis.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

		tools.stopForMoment(2000);

		assertEquals(inputSticker, selectedSticker);
		assertEquals(inputCardHolder, selectedStandLogo);
		assertEquals(inputOpenClose, selectedOpenClose);
		assertEquals(inputStandLogo, selectedCardHolder);

	}

	@Test(priority = 9, enabled = false)
	public void V_M_Amex__CUP_choice() {

		String inputSticker = "Had";
		String inputStandLogo = "Had";
		String inputOpenClose = "Had";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		String selectedSticker = newDataVis.selectDropdownListVMStickerEntriesByValue(inputSticker);
		String selectedStandLogo = newDataVis.selectDropdownListVMStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = newDataVis.selectDropdownListVMOpenCloseEntriesByValue(inputOpenClose);

		tools.stopForMoment(2000);

		assertEquals(inputSticker, selectedSticker);
		assertEquals(inputStandLogo, selectedStandLogo);
		assertEquals(inputOpenClose, selectedOpenClose);

	}

	@Test(priority = 10)
	public void gimmick_choice() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Gimmick']")));
		tools.stopForMoment(1000);

		String billHolderIsSelected = newDataVis.checkBoxBillHolderGimmick();
		String StickerSheetIsSelected = newDataVis.checkBoxStickerSheet();
		String penIsSelected = newDataVis.checkBoxPen();
		String reservedSignIsSelected = newDataVis.checkBoxReservedSign();

		System.out.println(billHolderIsSelected);
		System.out.println(StickerSheetIsSelected);
		System.out.println(penIsSelected);
		System.out.println(reservedSignIsSelected);

		assertEquals(billHolderIsSelected, "true");
		assertEquals(StickerSheetIsSelected, "true");
		assertEquals(penIsSelected, "true");
		assertEquals(reservedSignIsSelected, "true");

		tools.stopForMoment(2000);

		newDataVis.clickBtnSubmit();

	}

	public void submit_all() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();

		JCBNewDataVisitPage newDataVis = PageFactory.initElements(driver, JCBNewDataVisitPage.class);
//=============================================================================
//		edit data
		newDataVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		newDataVis.clickPICDataMerchant("anton");

		newDataVis.clicknoTelpPICDataMerchant("00000");

		newDataVis.selectDropdownListPICEntriesByValue("Kasir");

		newDataVis.selectDropdownListMerchEntriesByValue("2");

		newDataVis.clickBtnSaveDataMerchant();
		tools.stopForMoment();
//		add TID
		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";

		newDataVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListBankEntriesByValue(inputBank);
		newDataVis.inputMID(inputMID);
		newDataVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		newDataVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		newDataVis.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		newDataVis.clickBtnSaveAddTID();
		tools.stopForMoment(10000);

//		Collateral JCB
		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListCollStickerEntriesByValue(inputSticker);
		newDataVis.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		newDataVis.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		newDataVis.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

//		V/M/Amex/CUP

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		newDataVis.selectDropdownListVMStickerEntriesByValue("Had");
		newDataVis.selectDropdownListVMStandLogoEntriesByValue("Had");
		newDataVis.selectDropdownListVMOpenCloseEntriesByValue("Had");

		newDataVis.clickBtnSubmit();

	}

	@Test(priority =11)
	public void upload_photo_visit_merchant() {
		submit_all();

		String filepath = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";
		JCBVisitMerchantUploadFoto uploadFoto = PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);

		uploadFoto.uploadPhotoFotoDepanMerchant(filepath);
		tools.stopForMoment();
		boolean uploadPhotoMerchantIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoMerchantIsSuccess);

		uploadFoto.uploadFotoSemuaStrukEDCBtn(filepath);
		tools.stopForMoment();
		boolean uploadFotoSemuaStrukEDCIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadFotoSemuaStrukEDCIsSuccess);

		uploadFoto.uploadPhotoCollJCB1Before(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB1BeforeIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB1BeforeIsSuccess);

		uploadFoto.uploadPhotoCollJCB1After(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB1AfterIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB1AfterIsSuccess);

		uploadFoto.uploadPhotoCollJCB2Before(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB2BeforeIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB2BeforeIsSuccess);

		uploadFoto.uploadPhotoCollJCB2After(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB2AfterIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB2AfterIsSuccess);

		uploadFoto.uploadFotoCollOtherPrincipalBtn(filepath);
		tools.stopForMoment();
		boolean uploadFotoCollOtherPrincipalBtnIsSuccess = uploadFoto.getTextSuccessUploadPhoto().isDisplayed();
		System.out.println(uploadFotoCollOtherPrincipalBtnIsSuccess);

		boolean clickFinishBtnIsSuccess = uploadFoto.clickBtnFinish().getTextSuccessUploadPhoto().isDisplayed();

		assertTrue(uploadPhotoMerchantIsSuccess && uploadFotoSemuaStrukEDCIsSuccess
				&& uploadPhotoCollJCB1BeforeIsSuccess && uploadPhotoCollJCB1AfterIsSuccess
				&& uploadPhotoCollJCB2BeforeIsSuccess && uploadPhotoCollJCB2AfterIsSuccess
				&& uploadFotoCollOtherPrincipalBtnIsSuccess && clickFinishBtnIsSuccess);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));

	}

	@Test(priority = 12)
	public void upload_photo_visit_merchant_invalid_data() {
		submit_all();

		String filepath = "C:\\Users\\NEXSOFT\\Downloads\\JMeter.pdf";
		JCBVisitMerchantUploadFoto uploadFoto = PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);

		uploadFoto.uploadPhotoFotoDepanMerchant(filepath);
		tools.stopForMoment();
		boolean uploadPhotoMerchantFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoMerchantFailed);
		tools.stopForMoment();
		uploadFoto.clickBtnCancel();
		tools.scrollVerticalWindows(driver, 100);

		uploadFoto.uploadFotoSemuaStrukEDCBtn(filepath);
		tools.stopForMoment();
		boolean uploadFotoSemuaStrukEDCIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadFotoSemuaStrukEDCIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();

		uploadFoto.uploadPhotoCollJCB1Before(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB1BeforeIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB1BeforeIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();

		uploadFoto.uploadPhotoCollJCB1After(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB1AfterIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB1AfterIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();

		uploadFoto.uploadPhotoCollJCB2Before(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB2BeforeIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB2BeforeIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();

		uploadFoto.uploadPhotoCollJCB2After(filepath);
		tools.stopForMoment();
		boolean uploadPhotoCollJCB2AfterIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadPhotoCollJCB2AfterIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();

		uploadFoto.uploadFotoCollOtherPrincipalBtn(filepath);
		tools.stopForMoment();
		boolean uploadFotoCollOtherPrincipalBtnIsFailed = uploadFoto.getTextErrorUploadPhoto().isDisplayed();
		System.out.println(uploadFotoCollOtherPrincipalBtnIsFailed);
		tools.stopForMoment(1000);
		uploadFoto.clickBtnCancel();
		tools.screenShoot(driver);
		boolean clickFinishBtnIsDisplayed;

		try {
			uploadFoto.getBtnFinish().isDisplayed();
			clickFinishBtnIsDisplayed = true;
			System.out.println("Tidak error");
		} catch (Exception e) {
			clickFinishBtnIsDisplayed = false;
			System.out.println("error");
		}
		System.out.println(clickFinishBtnIsDisplayed);
		
		tools.screenShoot(driver);

		assertTrue(uploadPhotoMerchantFailed && uploadFotoSemuaStrukEDCIsFailed && uploadPhotoCollJCB1BeforeIsFailed
				&& uploadPhotoCollJCB1AfterIsFailed && uploadPhotoCollJCB2BeforeIsFailed
				&& uploadPhotoCollJCB2AfterIsFailed && uploadFotoCollOtherPrincipalBtnIsFailed
				&& !clickFinishBtnIsDisplayed);

	}

}
