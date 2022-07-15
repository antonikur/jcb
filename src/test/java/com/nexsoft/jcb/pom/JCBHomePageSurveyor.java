package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JCBHomePageSurveyor {
	protected WebDriver driver;

	@FindBy(xpath = "//span[normalize-space()='Dashboard']")
	private WebElement menuDashboard;

	@FindBy(xpath = "//span[normalize-space()='Master']")
	private WebElement menuMaster;

	@FindBy(xpath = "//a[normalize-space()='User']")
	private WebElement menuMasterUser;

	@FindBy(xpath = "//a[normalize-space()='Kota']")
	private WebElement menuMasterKota;

	@FindBy(xpath = "//a[normalize-space()='Area']")
	private WebElement menuMasterArea;

	@FindBy(xpath = "//span[normalize-space()='Data Merchant']")
	private WebElement menuDataMerchant;

	@FindBy(xpath = "//span[normalize-space()='Bucket']")
	private WebElement menuBucket;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/distribusi']")
	private WebElement menuBucketDistribusi;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/validation']")
	private WebElement menuBucketValidasi;

	@FindBy(xpath = "//span[normalize-space()='Monitoring']")
	private WebElement menuMonitoring;

	@FindBy(xpath = "//span[normalize-space()='Report']")
	private WebElement menuReport;

	@FindBy(xpath = "//span[normalize-space()='Worklist']")
	private WebElement menuWorklist;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/worklist']")
	private WebElement menuWorklistNewData;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/worklist/return']")
	private WebElement menuWorklistReturn;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/worklist/reject']")
	private WebElement menuWorklistReject;

	@FindBy(xpath = "//a[normalize-space()='Add New Data']")
	private WebElement menuWorklistAddNewData;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/request']")
	private WebElement menuWaitingValidation;

	@FindBy(xpath = "//a[@href='https://dev.ptdika.com/jcb/new/completed']")
	private WebElement menuCompleted;

	@FindBy(xpath = "//span[normalize-space()='Session']")
	private WebElement menuSession;

	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;

	@FindBy(xpath = "//b[normalize-space()='Selamat datang, Administrator']")
	private WebElement txtWelcome;

	@FindBy(xpath = "//div[@class='info']")
	private WebElement txtInfoUser;

	public JCBHomePageSurveyor(WebDriver driver) {
		this.driver = driver;
	}

	public JCBDashboardPage clickAndGotoMenuDashboard() {
		menuDashboard.click();
		return PageFactory.initElements(driver, JCBDashboardPage.class);
	}

	public JCBMasterUserPage clickAndGotoMenuMasterUser() {
		menuMaster.click();
		menuMasterUser.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}

	public JCBMasterKotaPage clickAndGotoMenuMasterKota() {
		menuMaster.click();
		menuMasterKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}

	public JCBMasterAreaPage clickAndGotoMenuMasterArea() {
		menuMaster.click();
		menuMasterArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}

	public JCBDataMerchantPage clickAndGotoMenuDataMerchant() {
		menuDataMerchant.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}

	public String getTxtInfoUser() {
		return txtInfoUser.getText();
	}

	public JCBReportPage clickAndGotoMenuReport() {
		menuReport.click();
		return PageFactory.initElements(driver, JCBReportPage.class);
	}

	public JCBHomePageSurveyor clickMenuWorklist() {
		menuWorklist.click();
		return PageFactory.initElements(driver, JCBHomePageSurveyor.class);
	}
	
	public JCBNewDataPage clickAndGotoMenuNewData() {
		menuWorklistNewData.click();
		return PageFactory.initElements(driver, JCBNewDataPage.class);
	}
	
	public JCBReturnPage clickAndGotoMenuReturn() {
		menuWorklistReturn.click();
		return PageFactory.initElements(driver, JCBReturnPage.class);
	}
	
	public JCBRejectPage clickAndGotoMenuReject() {
		menuWorklistReject.click();
		return PageFactory.initElements(driver, JCBRejectPage.class);
	}
	
	public JCBAddNewDataPage clickAndGotoMenuAddNewData() {
		menuWorklistAddNewData.click();
		return PageFactory.initElements(driver, JCBAddNewDataPage.class);
	}
	
	public JCBRejectPage clickAndGotoMenuCompleted() {
		menuCompleted.click();
		return PageFactory.initElements(driver, JCBRejectPage.class);
	}
	
	
	

}
