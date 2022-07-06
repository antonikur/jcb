package com.nexsoft.jcb.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBDataMerchantPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titlePageDataMerchant;
	
	//search
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement btnSearch;
	
	//file
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
	
	@FindBy(name = "city")
	private WebElement dropdownListKotaAddNewMerchant;
	
	@FindBy(name = "area")
	private WebElement dropdownListAreaAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='merchant_name']")
	private WebElement fieldMerchantNameAddNewMerchant;
	
	@FindBy(xpath = "//textarea[@name='address']")
	private WebElement fieldAddressAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='address_by_floor']")
	private WebElement fieldAddressByFloorAddNewMerchant;
	
	@FindBy(xpath = "//input[@name='categori']")
	private WebElement fieldCategoryAddNewMerchant;
	
	@FindBy(name = "users")
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
	
	
	//Table data merchant
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomArea;
	
	@FindBy(xpath = "//td[normalize-space()][3]")
	private List<WebElement> tableKolomBatch;
	
	@FindBy(xpath = "//td[normalize-space()][4]")
	private List<WebElement> tableKolomNamaMerchant;
	
	@FindBy(xpath = "//td[normalize-space()]51]")
	private List<WebElement> tableKolomAddress;
	
	@FindBy(xpath = "//td[normalize-space()][6]")
	private List<WebElement> tableKolomCategory;
	
	@FindBy(xpath = "//td[normalize-space()][7]")
	private List<WebElement> tableKolomOfficer;
	
	@FindBy(xpath = "//td[normalize-space()][8]")
	private List<WebElement> tableKolomStatusVisit;
	
	
	
	//delete example xpath
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[ ]/td[9]/a/i
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[9]/a/i
	//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[3]/td[9]/a/i
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;

	//other
	public String getTitleDataMerchantPage() {
		return titlePageDataMerchant.getText();
	}
	
	//search
	public JCBDataMerchantPage inputAndClickSearch(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		btnSearch.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	//add new
	public JCBDataMerchantPage clickBtnAddNewMerchant() {
		btnAddNewMerchant.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage inputAllFieldAddNewMerchant(String batch, String kota, String area, String merchantName, String address, String addressByFloor, String category, String users) {
		Select selectKota = new Select(dropdownListKotaAddNewMerchant);
		Select selectArea = new Select(dropdownListAreaAddNewMerchant);
		Select selectUser = new Select(dropdownListUserAddNewMerchant);
		fieldBatchPopupAddNewMerchant.sendKeys(batch);
		selectKota.selectByVisibleText(kota);
		selectArea.selectByVisibleText(area);
		fieldMerchantNameAddNewMerchant.sendKeys(merchantName);
		fieldAddressAddNewMerchant.sendKeys(address);
		fieldAddressByFloorAddNewMerchant.sendKeys(addressByFloor);
		fieldCategoryAddNewMerchant.sendKeys(category);
		selectUser.selectByVisibleText(users);
		
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnSaveAddNewMerchant() {
		btnSaveAddNewMerchant.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnCancelAddNewMerchant() {
		btnCancelAddNewMerchant.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public String getTitlePopupAddMerchant() {
		return titlePopupAddNewMerchant.getText();
	}
	
	//delete
	public JCBDataMerchantPage clickDeleteBySelectIndex(String index) {
		
		driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr["+index+"]/td[9]/a/i")).click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	//table
	public List<List<WebElement>> getTableDataMerchant(){
		List<List<WebElement>> tableDataMerchant = new ArrayList<>();
		tableDataMerchant.add(tableKolomNo);
		tableDataMerchant.add(tableKolomArea);
		tableDataMerchant.add(tableKolomBatch);
		tableDataMerchant.add(tableKolomNamaMerchant);
		tableDataMerchant.add(tableKolomAddress);
		tableDataMerchant.add(tableKolomCategory);
		tableDataMerchant.add(tableKolomOfficer);
		tableDataMerchant.add(tableKolomStatusVisit);
		
		return tableDataMerchant;
	}
	
	//download
	public JCBDataMerchantPage clickBtnDownload() {
		btnDownloadTemplate.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	//upload
	public JCBDataMerchantPage clickBtnUploadData() {
		btnUploadData.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnProcessPopupUpload() {
		btnProcessPopupUpload.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnCancelPopupUpload() {
		btnCancelPopupUpload.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage selectFilePopupUpload(String filePath) {
		selectFilePopupUpload.sendKeys(filePath);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage(WebDriver driver){
		this.driver = driver;
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public String getTitlePopupUpload() {
		return titlePopupUpload.getText();
	}
}
