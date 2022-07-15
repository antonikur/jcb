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
import com.nexsoft.jcb.pom.JCBRejectPage;
import com.nexsoft.jcb.pom.JCBRejectVisitPage;
import com.nexsoft.jcb.pom.JCBReturnVisitPage;
import com.nexsoft.jcb.pom.JCBVisitMerchantUploadFoto;

public class TestFiturRejectVisitPageSurveyor {
	
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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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



	}

	@Test(priority = 2)
	public void edit_data_blank_data_merchant() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(2000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		tools.stopForMoment(25000);
		tools.scrollVerticalWindows(driver, -1000);
		tools.stopForMoment();

		boolean isDisplayed = returnVis.getTextNotifAddTIDSuccess().isDisplayed();

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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Upload error: The file you are attempting to uploa')]")));
		tools.stopForMoment(2000);
		boolean isDisplayed = returnVis.getTextErrorUploadTIDFoto().isDisplayed();
		
		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		boolean isDisplayed = returnVis.getTextErrorUploadTID().isDisplayed();
		
		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();
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
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		
		boolean isDisplayed = returnVis.getTextErrorUploadTID().isDisplayed();

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVis.clickBtnCancelAddTID();

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
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();

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

	@Test(priority = 9)
	public void V_M_Amex__CUP_choice() {

		String inputSticker = "Had";
		String inputStandLogo = "Had";
		String inputOpenClose = "Had";

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();

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

	@Test(priority = 10)
	public void gimmick_choice() {

		JCBLoginPage login = PageFactory.initElements(driver, JCBLoginPage.class);
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();

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
		JCBHomePage home = login.inputFieldUsername("admindika2").inputFieldPassword("d1k4@passw0rd").clickBtnLogin()
				.gotoHomePage();

		home.clickMenuWorklist();
		tools.stopForMoment(1000);
		JCBRejectPage rejectPage = home.clickAndGotoMenuReject();
		tools.stopForMoment(1000);
		rejectPage.clickBtnVisitAndGoToVisitPage();

		JCBRejectVisitPage rejectVis = PageFactory.initElements(driver, JCBRejectVisitPage.class);
//=============================================================================
//		edit data
		rejectVis.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		rejectVis.clickPICDataMerchant("anton");

		rejectVis.clicknoTelpPICDataMerchant("00000");

		rejectVis.selectDropdownListPICEntriesByValue("Kasir");

		rejectVis.selectDropdownListMerchEntriesByValue("2");

		rejectVis.clickBtnSaveDataMerchant();
		tools.stopForMoment();
//		add TID
		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";

		rejectVis.clickBtnAddTID();
		tools.stopForMoment(2000);
		rejectVis.selectDropdownListBankEntriesByValue(inputBank);
		rejectVis.inputMID(inputMID);
		rejectVis.inputTID(inputTID);
		tools.stopForMoment(2000);
		rejectVis.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		rejectVis.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		rejectVis.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		rejectVis.clickBtnSaveAddTID();
		tools.stopForMoment();

//		Collateral JCB
		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		rejectVis.selectDropdownListCollStickerEntriesByValue(inputSticker);
		rejectVis.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		rejectVis.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		rejectVis.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

//		V/M/Amex/CUP

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		rejectVis.selectDropdownListVMStickerEntriesByValue("Had");
		rejectVis.selectDropdownListVMStandLogoEntriesByValue("Had");
		rejectVis.selectDropdownListVMOpenCloseEntriesByValue("Had");

		rejectVis.clickBtnSubmit();

	}

	@Test(priority = 11)
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

		boolean clickFinishBtnIsDisplayed;
		
		try {
			uploadFoto.getBtnFinish().isDisplayed();
			clickFinishBtnIsDisplayed = true;
			System.out.println("Tidak error");
		} catch(Exception e) {
			clickFinishBtnIsDisplayed = false;
			System.out.println("error");
		}
		System.out.println(clickFinishBtnIsDisplayed);
		
		assertTrue(uploadPhotoMerchantFailed && uploadFotoSemuaStrukEDCIsFailed
				&& uploadPhotoCollJCB1BeforeIsFailed && uploadPhotoCollJCB1AfterIsFailed
				&& uploadPhotoCollJCB2BeforeIsFailed && uploadPhotoCollJCB2AfterIsFailed
				&& uploadFotoCollOtherPrincipalBtnIsFailed && clickFinishBtnIsDisplayed);
		
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));
	}
}
