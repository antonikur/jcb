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
import com.nexsoft.jcb.pom.JCBReturnPage;
import com.nexsoft.jcb.pom.JCBReturnVisitPage;
import com.nexsoft.jcb.pom.JCBVisitMerchantUploadFoto;

public class TestFiturReturnVisitPageSurveyor {
	
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

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);
		returnVisit.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVisit.clickPICDataMerchant("");
		tools.stopForMoment(1000);
		returnVisit.clicknoTelpPICDataMerchant("");
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListPICEntriesByValue("Kasir");
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListMerchEntriesByValue("2");
		tools.stopForMoment(1000);
		returnVisit.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isPICReqDisplayed = returnVisit.getPICReq().isDisplayed();
		boolean isNoTelpPICReqDisplayed = returnVisit.getNoTelpPICReq().isDisplayed();

		returnVisit.clickBtnCancelEditDataMerchant();

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

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);
		returnVisit.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVisit.clickPICDataMerchant(inputPICName);
		tools.stopForMoment(1000);
		returnVisit.clicknoTelpPICDataMerchant(inputNoTelpPIC);
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListPICEntriesByValue(inputPICPos);
		tools.stopForMoment(1000);
		returnVisit.selectDropdownListMerchEntriesByValue(inputMerchAcc);
		tools.stopForMoment(1000);
		returnVisit.clickBtnSaveDataMerchant();
		tools.stopForMoment(2000);

		boolean isDisplayed = returnVisit.getTextNotifEditSuccess().isDisplayed();
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
		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVisit.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListBankEntriesByValue(inputBank);
		returnVisit.inputMID(inputMID);
		returnVisit.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVisit.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVisit.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		tools.stopForMoment(5000);
		returnVisit.clickBtnSaveAddTID();
		tools.stopForMoment(25000);
		tools.scrollVerticalWindows(driver, -1000);
		tools.stopForMoment(5000);
		boolean isDisplayed = returnVisit.getTextNotifAddTIDSuccess().isDisplayed();

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
		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVisit.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListBankEntriesByValue(inputBank);
		returnVisit.inputMID(inputMID);
		returnVisit.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVisit.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVisit.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVisit.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Upload error: The file you are attempting to uploa')]")));
		tools.stopForMoment(2000);
		boolean isDisplayed = returnVisit.getTextErrorUploadTIDFoto().isDisplayed();
		
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVisit.clickBtnCancelAddTID();

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
		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVisit.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListBankEntriesByValue(inputBank);
		returnVisit.inputMID(inputMID);
		returnVisit.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVisit.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVisit.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVisit.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		
		boolean isDisplayed = returnVisit.getTextErrorUploadTID().isDisplayed();
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVisit.clickBtnCancelAddTID();

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
		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		returnVisit.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListBankEntriesByValue(inputBank);
		returnVisit.inputMID(inputMID);
		returnVisit.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVisit.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVisit.clickBtnChooseFile(inputFile);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVisit.clickBtnSaveAddTID();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form_edc']/div[2]/div[7]/div/span")));
		tools.stopForMoment(2000);
		boolean isDisplayed = returnVisit.getTextErrorUploadTID().isDisplayed();
		tools.screenShoot(driver);

		assertTrue(isDisplayed);
		tools.stopForMoment(2000);

		returnVisit.clickBtnCancelAddTID();

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

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		String selectedSticker = returnVisit.selectDropdownListCollStickerEntriesByValue(inputSticker);
		String selectedStandLogo = returnVisit.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = returnVisit.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		String selectedCardHolder = returnVisit.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

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
		JCBNewDataPage newData = home.clickAndGotoMenuNewData();
		tools.stopForMoment(1000);
		newData.clickBtnVisitAndGoToVisitPage();

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		String selectedSticker = returnVisit.selectDropdownListVMStickerEntriesByValue(inputSticker);
		String selectedStandLogo = returnVisit.selectDropdownListVMStandLogoEntriesByValue(inputStandLogo);
		String selectedOpenClose = returnVisit.selectDropdownListVMOpenCloseEntriesByValue(inputOpenClose);

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

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Gimmick']")));
		tools.stopForMoment(1000);

		String billHolderIsSelected = returnVisit.checkBoxBillHolderGimmick();
		String StickerSheetIsSelected = returnVisit.checkBoxStickerSheet();
		String penIsSelected = returnVisit.checkBoxPen();
		String reservedSignIsSelected = returnVisit.checkBoxReservedSign();

		System.out.println(billHolderIsSelected);
		System.out.println(StickerSheetIsSelected);
		System.out.println(penIsSelected);
		System.out.println(reservedSignIsSelected);

		assertEquals(billHolderIsSelected, "true");
		assertEquals(StickerSheetIsSelected, "true");
		assertEquals(penIsSelected, "true");
		assertEquals(reservedSignIsSelected, "true");

		tools.stopForMoment(2000);

		returnVisit.clickBtnSubmit();

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

		JCBReturnVisitPage returnVisit = PageFactory.initElements(driver, JCBReturnVisitPage.class);
//=============================================================================
//		edit data
		returnVisit.clickBtnEditDataMerchant();
		tools.stopForMoment(1000);
		returnVisit.clickPICDataMerchant("anton");

		returnVisit.clicknoTelpPICDataMerchant("00000");

		returnVisit.selectDropdownListPICEntriesByValue("Kasir");

		returnVisit.selectDropdownListMerchEntriesByValue("2");

		returnVisit.clickBtnSaveDataMerchant();
		tools.stopForMoment();
//		add TID
		String inputBank = "BRI";
		String inputMID = "123123";
		String inputTID = "111111";
		String inputJCB = "2";
		String inputEDC = "Broken EDC";
		String inputFile = "C:\\Users\\NEXSOFT\\Downloads\\kuda.png";

		returnVisit.clickBtnAddTID();
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListBankEntriesByValue(inputBank);
		returnVisit.inputMID(inputMID);
		returnVisit.inputTID(inputTID);
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListJCBAccEntriesByValue(inputJCB);
		tools.stopForMoment();
		returnVisit.selectDropdownListEDCIssueEntriesByValue(inputEDC);
		tools.stopForMoment();
		returnVisit.clickBtnChooseFile(inputFile);

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//button[@id='btnSave']")));
		returnVisit.clickBtnSaveAddTID();
		tools.stopForMoment(10000);

//		Collateral JCB
		String inputSticker = "None";
		String inputStandLogo = "None";
		String inputOpenClose = "None";
		String inputCardHolder = "None";

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='Collateral JCB']")));
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListCollStickerEntriesByValue(inputSticker);
		returnVisit.selectDropdownListCollStandLogoEntriesByValue(inputStandLogo);
		returnVisit.selectDropdownListCollOpenCloseEntriesByValue(inputOpenClose);
		returnVisit.selectDropdownListCollNameCardHolderEntriesByValue(inputCardHolder);

//		V/M/Amex/CUP

		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//h4[normalize-space()='V/M/Amex/CUP']")));
		tools.stopForMoment(2000);
		returnVisit.selectDropdownListVMStickerEntriesByValue("Had");
		returnVisit.selectDropdownListVMStandLogoEntriesByValue("Had");
		returnVisit.selectDropdownListVMOpenCloseEntriesByValue("Had");

		returnVisit.clickBtnSubmit();

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
		
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));

	}
}
