package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JCBDataMerchantPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement btnSearch;
	
	@FindBy(xpath = "//a[@class='btn btn-success m-b-5']")
	private WebElement btnDownloadTemplate;
	
	@FindBy(xpath = "//a[normalize-space()='Upload Data']")
	private WebElement btnUploadData;
	
	//add new merchant
	@FindBy(xpath = "//a[normalize-space()='Add New Merchant']")
	private WebElement btnAddNewMerchant;
	
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")
	private WebElement titlePopupAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='batch']")
	private WebElement fieldBatchPopupAddNewMerchant;
	
	@FindBy(name = "")
	private WebElement dropdownListKotaAddNewMerchant;
	
	@FindBy(name = "")
	private WebElement dropdownListAreaAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='merchant_name']")
	private WebElement fieldMerchantNameAddNewMerchant;
	
	@FindBy(xpath = "//textarea[@name='address']")
	private WebElement fieldAddressAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='address_by_floor']")
	private WebElement fieldAddressByFloorAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='categori']")
	private WebElement fieldCategoryAddNewMerchant;
	
	@FindBy(name = "")
	private WebElement dropdownListUserAddNewMerchant;
	
	@FindBy(xpath = "(//button[@type='submit'])[5]")
	private WebElement btnSaveAddNewMerchant;
	
	@FindBy(xpath = "(//button[@type='submit'])[4]")
	private WebElement btnCancelAddNewMerchant;
	
	//upload file
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement titlePopupUpload;
	
//	@FindBy(name = "excel")
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	private WebElement selectFilePopupUpload;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnCancelPopupUpload;
	
	@FindBy(xpath = "(//button[@type='submit'])[3]")
	private WebElement btnProcessPopupUpload;
	
	//navigate
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnNextPage;
	
	@FindBy(xpath = "//a[normalize-space()='Last']")
	private WebElement btnLastPage;
	
	@FindBy(xpath = "//a[normalize-space()='First']")
	private WebElement btnFirstPage;
	
	@FindBy(xpath = "//a[normalize-space()='Prev']")
	private WebElement btnPrevPage;
	
	
	//delete example xpath
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr/td[9]/a/i
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[9]/a/i
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[3]/td[9]/a/i
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;

	
	
	
	
	
	
	public JCBDataMerchantPage(WebDriver driver){
		this.driver = driver;
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
}
