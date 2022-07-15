package com.nexsoft.jcb.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.nexsoft.jcb.other.Tools;

public class JCBDataMerchantPage {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titlePageDataMerchant;
	
	//search
	//this is new merchant popup title xpath also title popup upload
	//h4[@id='myModalLabel']
	
	@FindBy(xpath = "//input[@placeholder='Search Merchant Name']")
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
	
	@FindBy(xpath = "//*[@id=\"modalForm\"]/div/div/div[2]/form/fieldset/div/div/input")
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
	
	@FindBy(xpath = "//a[normalize-space()]")
	private List<WebElement> listButton;
	
	
	//Table data merchant
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomArea;
	
	@FindBy(xpath = "//td[normalize-space()][3]")
	private List<WebElement> tableKolomBatch;
	
	@FindBy(xpath = "//td[normalize-space()][4]")
	private List<WebElement> tableKolomNamaMerchant;
	
	@FindBy(xpath = "//td[normalize-space()][5]")
	private List<WebElement> tableKolomAddress;
	
	@FindBy(xpath = "//td[normalize-space()][6]")
	private List<WebElement> tableKolomCategory;
	
	@FindBy(xpath = "//td[normalize-space()][7]")
	private List<WebElement> tableKolomOfficer;
	
	@FindBy(xpath = "//td[normalize-space()][8]")
	private List<WebElement> tableKolomStatusVisit;
	
	//message
	@FindBy(xpath = "//div[@class='alert alert-success fade show']")
	private WebElement messsageAddNewMerchantSuccess;
	
	@FindBy(xpath = "//p[contains(text(),'The filetype you are attempting to upload is not a')]")
	private WebElement messageErrorUploadFile;
	
	@FindBy(xpath = "//p[normalize-space()='The page you requested was not found.']")
	private WebElement messageError404;
	
	@FindBy(xpath = "//p[normalize-space()='You did not select a file to upload.']")
	private WebElement messageErrorNoFileSelected;
	
	
	//panel
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement btnExpandCompress;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement btnCollapseExpand;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement panelExpandCompress;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div/div[2]")
	private WebElement panelCollapseExpand;
	
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
	
	public String getMessageAddNewMerchantSuccess() {
		return messsageAddNewMerchantSuccess.getText();
	}
	
	public WebElement getElementMessageAddMerchantSuccess() {
		return messsageAddNewMerchantSuccess;
	}
	
	public boolean getStatusNullMessageAddMerchantSuccess() {
		return messsageAddNewMerchantSuccess == null;
	}
	
	public String getMessageErrorUpload() {
		return messageErrorUploadFile.getText();
	}
	
	public String getMessageError404() {
		return messageError404.getText();
	}
	
	public String getMessageErrorFileNotSelected() {
		return messageErrorNoFileSelected.getText();
	}
	
	//search
	public JCBDataMerchantPage inputAndClickSearch(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		btnSearch.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	//navigate
	public JCBDataMerchantPage clickBtnLastPage() {
		btnLastPage.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	public JCBDataMerchantPage clickBtnFirstPage() {
		btnFirstPage.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnNextPage() {
		btnNextPage.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnPrevPage() {
		btnPrevPage.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnNumberPage(String pageNo) {
		driver.findElement(By.xpath("//a[normalize-space()='"+pageNo+"']")).click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	
	//add new
	public JCBDataMerchantPage clickBtnAddNewMerchant() {
		btnAddNewMerchant.click();
		tool.stopForMoment(1500);
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
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public String getTitlePopupAddMerchant() {
		return titlePopupAddNewMerchant.getText();
	}
	
	public boolean checkTitlePopupAddMerchant() {
		return titlePopupAddNewMerchant.isDisplayed();
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
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	//download
	public JCBDataMerchantPage clickBtnDownload() {
		btnDownloadTemplate.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	//upload
	public JCBDataMerchantPage clickBtnUploadData() {
		btnUploadData.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnProcessPopupUpload() {
		btnProcessPopupUpload.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnCancelPopupUpload() {
		btnCancelPopupUpload.click();
		tool.stopForMoment(1500);
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
	
	public boolean checkTitlePopupUpload() {
		return titlePopupUpload.isDisplayed();
	}
	
	//delete
	public JCBDataMerchantPage deleteDataMerchantByIndex(String index) {
		//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr[3]/td[9]/a/i
		driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/div[2]/div/table/tbody/tr["+index+"]/td[9]/a/i")).click();
		driver.switchTo().alert().accept();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	//panel
	public WebElement getPanelViewDataMerchant() {
		return panelExpandCompress;
	}
	
	public WebElement getPanelViewListTableMerchant() {
		return panelCollapseExpand;
	}
	
	public JCBDataMerchantPage clickBtnExpandCompress() {
		btnExpandCompress.click();
		return PageFactory.initElements(driver, JCBDataMerchantPage.class);
	}
	
	public JCBDataMerchantPage clickBtnCollapseExpand() {
		btnCollapseExpand.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBDataMerchantPage.class); 
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public List<WebElement> getListButtonForNavigate(){
		return listButton;
	}
	
	//get element field add merchant
	public WebElement getElementBatch() {
		return fieldBatchPopupAddNewMerchant;
	}
	
	public WebElement getElementKota() {
		return dropdownListKotaAddNewMerchant;
	}
	
	public WebElement getElementArea() {
		return dropdownListAreaAddNewMerchant;
	}
	
	public WebElement getElementMerchantName() {
		return fieldMerchantNameAddNewMerchant;
	}
	
	public WebElement getElementAddress() {
		return fieldAddressAddNewMerchant;
	}
	
	public WebElement getElementAddByFloor() {
		return fieldAddressByFloorAddNewMerchant;
	}
	
	public WebElement getElementCategory() {
		return fieldCategoryAddNewMerchant;
	}
	
	public WebElement getElementUser() {
		return dropdownListUserAddNewMerchant;
	}
	
	
	
	////
	
	
}
