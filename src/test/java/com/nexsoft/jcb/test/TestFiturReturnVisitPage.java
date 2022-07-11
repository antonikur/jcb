package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
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
import com.nexsoft.jcb.pom.JCBReturnPage;
import com.nexsoft.jcb.pom.JCBReturnVisitPage;
import com.nexsoft.jcb.pom.JCBReturnPage;
import com.nexsoft.jcb.pom.JCBReturnVisitPage;
import com.nexsoft.jcb.pom.JCBVisitMerchantUploadFoto;

public class TestFiturReturnVisitPage {
	
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

	@Test(priority = 1)
	public void edit_data_valid_data_merchant() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);
		returnVisit.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVisit.clickPICDataMerchant("anton");
		tools.stopForMoment(1000);
		returnVisit.clicknoTelpPICDataMerchant("00000");
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListPICEntriesByValue("Kasir");
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListMerchEntriesByValue("2");
		tools.stopForMoment(1000);
		returnVisit.clickBtnSaveDataMerchant();

		tools.stopForMoment();

		String resultEditPIC = returnVisit.getTextPIC();
		String resultEditNoTelpPIC = returnVisit.getTextNoTelpPIC();
		String resultEditPICPos = returnVisit.getTextPICPos();
		String resultMerchAcc = returnVisit.getTextMerchAcc();

		assertEquals("anton".toUpperCase(), resultEditPIC);
		assertEquals("00000", resultEditNoTelpPIC);
		assertEquals("Kasir", resultEditPICPos);
		assertEquals("2", resultMerchAcc);

//		tools.stopForMoment(1000);
//		newDataVis.LogOut();
//		newDataVis.gotoLoginPage();

	}

	@Test(priority = 2, enabled = false)
	public void edit_data_blank_data_merchant() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);
		returnVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVis.clickPICDataMerchant("");
		tools.stopForMoment(1000);
		returnVis.clicknoTelpPICDataMerchant("");
		tools.stopForMoment(1000);
		returnVis.selectDropdownListPICEntriesByValue("Kasir");
		tools.stopForMoment(1000);
		returnVis.selectDropdownListMerchEntriesByValue("2");
		tools.stopForMoment(1000);
		returnVis.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isPICReqDisplayed = returnVis.getPICReq().isDisplayed();
		boolean isNoTelpPICReqDisplayed = returnVis.getNoTelpPICReq().isDisplayed();

		returnVis.clickBtnCancelEditDataMerchant();

		tools.stopForMoment(1000);
		assertTrue(isPICReqDisplayed && isNoTelpPICReqDisplayed);

//		newDataVis.LogOut();
//		newDataVis.gotoLoginPage();

	}

	@Test(priority = 3, enabled = false)
	public void edit_data_invalid_data_merchant() {
		String inputPICName = "0856234";
		String inputNoTelpPIC = "Bambang";
		String inputPICPos = "Kasir";
		String inputMerchAcc = "2";
		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);
		returnVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVis.clickPICDataMerchant(inputPICName);
		tools.stopForMoment(1000);
		returnVis.clicknoTelpPICDataMerchant(inputNoTelpPIC);
		tools.stopForMoment(1000);
		returnVis.selectDropdownListPICEntriesByValue(inputPICPos);
		tools.stopForMoment(1000);
		returnVis.selectDropdownListMerchEntriesByValue(inputMerchAcc);
		tools.stopForMoment(1000);
		returnVis.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isDisplayed = returnVis.getTextNotifEditSuccess().isDisplayed();

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
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(2000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVis.selectDropdownListBankEntriesByValue(inputBank);
		returnVis.inputMID(inputMID);
		returnVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVis.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVis.clickBtnSaveAddTID();
		tools.stopForMoment();
		tools.scrollVerticalWindows(driver, -1000);

		boolean isDisplayed = returnVis.getTextNotifAddTIDSuccess().isDisplayed();

		assertTrue(isDisplayed);

	}

	@Test(priority = 5, enabled = false)
	public void add_TID_invalid_data_size_file() {

		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\bigFoto.jpg";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVis.selectDropdownListBankEntriesByValue(inputBank);
		returnVis.inputMID(inputMID);
		returnVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVis.clickBtnSaveAddTID();
		tools.stopForMoment(20000);
		boolean isDisplayed = returnVis.getTextErrorUploadTIDFoto().isDisplayed();

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

	}

	@Test(priority = 6, enabled = false)
	public void add_TID_invalid_data_file_xlsx() {

		String inputBank = "BCA";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\nexSOFT SQA.xlsx";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVis.selectDropdownListBankEntriesByValue(inputBank);
		returnVis.inputMID(inputMID);
		returnVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVis.clickBtnSaveAddTID();
		tools.stopForMoment(20000);
		boolean isDisplayed = returnVis.getTextErrorUploadTID().isDisplayed();

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

	}

	@Test(priority = 7, enabled = false)
	public void add_TID_invalid_data_file_pdf() {

		String inputBank = "CIMB Niaga";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\JMeter.pdf";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();
		tools.stopForMoment(1000);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//a[@class='btn btn-info btn-xs']")));
		tools.scrollVerticalWindows(driver, -100);
		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVis.selectDropdownListBankEntriesByValue(inputBank);
		returnVis.inputMID(inputMID);
		returnVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVis.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVis.clickBtnSaveAddTID();
		tools.stopForMoment(20000);
		boolean isDisplayed = returnVis.getTextErrorUploadTID().isDisplayed();

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

	}

	@Test(priority = 7)
	public void collateral_choice() {

		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		String selectedSticker = returnVis.selectDropdownListCollStickerEntriesByValue(inputSticker);
		String selectedStandLogo = returnVis.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = returnVis.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		String selectedCardHolder = returnVis.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

		tools.stopForMoment(2000);

		assertEquals(inputSticker, selectedSticker);
		assertEquals(inputCardHolder, selectedStandLogo);
		assertEquals(inputOpenClose, selectedOpenClose);
		assertEquals(inputStandLogo, selectedCardHolder);

	}

	@Test(priority = 7)
	public void V_M_Amex__CUP_choice() {

		String inputSticker = "Had";
		String inputStandLogo = "Had";
		String inputOpenClose = "Had";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		String selectedSticker = returnVis.selectDropdownListVMStickerEntriesByValue(inputSticker);
		String selectedStandLogo = returnVis.selectDropdownListVMStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = returnVis.selectDropdownListVMOpenCloseEntriesByValue(inputOpenClose);

		tools.stopForMoment(2000);

		assertEquals(inputSticker, selectedSticker);
		assertEquals(inputStandLogo, selectedStandLogo);
		assertEquals(inputOpenClose, selectedOpenClose);

	}

	@Test(priority = 9)
	public void gimmick_choice() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Gimmick']")));
		tools.stopForMoment(1000);

		String billHolderIsSelected = returnVis.checkBoxBillHolderGimmick();
		String StickerSheetIsSelected = returnVis.checkBoxStickerSheet();
		String penIsSelected = returnVis.checkBoxPen();
		String reservedSignIsSelected = returnVis.checkBoxReservedSign();

		System.out.println(billHolderIsSelected);
		System.out.println(StickerSheetIsSelected);
		System.out.println(penIsSelected);
		System.out.println(reservedSignIsSelected);

		assertEquals(billHolderIsSelected, "true");
		assertEquals(StickerSheetIsSelected, "true");
		assertEquals(penIsSelected, "true");
		assertEquals(reservedSignIsSelected, "true");

		tools.stopForMoment(2000);

		returnVis.clickBtnSubmit();

	}
	
	public void submit_all() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika3").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBReturnPage returnPage = home.clickAndGotoMenuReturn();
		tools.stopForMoment(1000);
		returnPage.clickBtnVisitAndGoToVisitPage();

		JCBReturnVisitPage returnVis = PageFactory.initElements(driver, JCBReturnVisitPage.class);
//=============================================================================
//		edit data
		returnVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVis.clickPICDataMerchant("anton");

		returnVis.clicknoTelpPICDataMerchant("00000");

		returnVis.selectDropdownListPICEntriesByValue("Kasir");

		returnVis.selectDropdownListMerchEntriesByValue("2");

		returnVis.clickBtnSaveDataMerchant();
		tools.stopForMoment();
//		add TID
		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";

		returnVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVis.selectDropdownListBankEntriesByValue(inputBank);
		returnVis.inputMID(inputMID);
		returnVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVis.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVis.clickBtnSaveAddTID();
		tools.stopForMoment();

//		Collateral JCB
		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		returnVis.selectDropdownListCollStickerEntriesByValue(inputSticker);
		returnVis.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		returnVis.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		returnVis.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

//		V/M/Amex/CUP

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		returnVis.selectDropdownListVMStickerEntriesByValue("Had");
		returnVis.selectDropdownListVMStandLogoEntriesByValue("Had");
		returnVis.selectDropdownListVMOpenCloseEntriesByValue("Had");

		returnVis.clickBtnSubmit();

	}

	@Test(priority = 9)
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

	}
	
	@Test(priority = 9)
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

		boolean clickFinishBtnIsDisplayed;
		
		try {
			uploadFoto.getBtnFinish().isDisplayed();
			clickFinishBtnIsDisplayed = true;
			System.out.println("gak error");
		} catch(Exception e) {
			clickFinishBtnIsDisplayed = false;
			System.out.println("error");
		}
		System.out.println(clickFinishBtnIsDisplayed);
		
		assertTrue(uploadPhotoMerchantFailed && uploadFotoSemuaStrukEDCIsFailed
				&& uploadPhotoCollJCB1BeforeIsFailed && uploadPhotoCollJCB1AfterIsFailed
				&& uploadPhotoCollJCB2BeforeIsFailed && uploadPhotoCollJCB2AfterIsFailed
				&& uploadFotoCollOtherPrincipalBtnIsFailed && !clickFinishBtnIsDisplayed);

	}
}
