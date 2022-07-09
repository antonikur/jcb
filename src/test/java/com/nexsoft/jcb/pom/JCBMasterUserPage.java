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

public class JCBMasterUserPage {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewUser;
	
	//navigation
	@FindBy(name = "data-table-default_length")//10, 25, 50, 100
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	@FindBy(xpath = "//a[normalize-space()]")
	private List<WebElement> listButton;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleMasterUser;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement btnExpandCompress;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement btnCollapseExpand;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement messageNewDataSuccess;

	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/div/div[2]")
	private WebElement panelListTableUser;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/div")
	private WebElement panelViewDataUser;
	
	//delete message
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement messageDeleteDataSuccess;
	
	//new user
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement titlePopupAddNewUser;
	
	@FindBy(xpath = "//input[@name='nik']")
	private WebElement fieldNIKAddNewPopup;
	
	@FindBy(xpath = "//input[@name='name']")
	private WebElement fieldNameAddNewPopup;
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement fieldUsernameAddNewPopup;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement fieldPasswordAddNewPopup;
	
	@FindBy(xpath = "//select[@name='privilege']")//2, 3, 4, 5, 6
	private WebElement dropdownListPrivilageAddNewPopup;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSave;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnCancel;
	
	
//	@FindBy(xpath = "//a[normalize-space()='2']")
//	private WebElement btnPageNavNumber;
	
	//edit
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")
	private WebElement titlePopupEditUser;
	
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCancelPopupEditUser;
	
	//table
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomNIK;
	
	@FindBy(xpath = "//td[normalize-space()][3]")
	private List<WebElement> tableKolomName;
	
	@FindBy(xpath = "//td[normalize-space()][4]")
	private List<WebElement> tableKolomPrivilage;
	
	@FindBy(xpath = "//td[@class='dataTables_empty']")
	private WebElement messageDataTablesEmpty;//No matching records found
	
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	public JCBMasterUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public JCBMasterUserPage clickAddNewUser() {
		tool.scrollVerticalWindows(driver, -1000);
		btnAddNewUser.click();
		tool.stopForMoment();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public String getTitleUserPage() {
		return titleMasterUser.getText();
	}
	
	//add new pop up
	public JCBMasterUserPage inputAllAddUserField(String nik, String name, String username, String password, String privilege) {
		Select select = new Select(dropdownListPrivilageAddNewPopup);
		fieldNIKAddNewPopup.sendKeys(nik);
		fieldNameAddNewPopup.sendKeys(name);
		fieldUsernameAddNewPopup.sendKeys(username);
		fieldPasswordAddNewPopup.sendKeys(password);
		select.selectByValue(privilege);
		
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickBtnSave() {
		btnSave.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickBtnCancel() {
		btnCancel.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	//page navigate
	public JCBMasterUserPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickNextPage() {
		btnPageNavNext.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickPreviousPage() {
		btnPageNavPrev.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public List<WebElement> getListButtonForNavigate(){
		return listButton;
	}
	
	public JCBMasterUserPage inputFieldSearch(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	//edit user
	public JCBMasterUserPage clickEditUserByNo(String no) {
		//button to view edit and delete
		driver.findElement(By.xpath("//td[normalize-space()='"+no+"']")).click();		
		
		//search index
		int index = Integer.parseInt(no)%10;
		
		//button edit
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+ (index+1) +"]/td/ul/li/span[2]/a[1]/i")).click();
		
		tool.stopForMoment();
		
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickEditUserByIndexWhenExpand(String index) {
		//table[@id='data-table-default']/tbody/tr[1]/td[5]/a/i
		//table[@id='data-table-default']/tbody/tr[2]/td[5]/a/i
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+index+"]/td[5]/a/i")).click();
		
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickPlusForExpandActionByNoInUser(String noUser) {
		driver.findElement(By.xpath("//td[normalize-space()='"+noUser+"']")).click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickDeleteUserByNo(String no) {
		//button to view edit and delete
		driver.findElement(By.xpath("//td[normalize-space()='"+no+"']")).click();
		
		//search index
		int index = Integer.parseInt(no)%10;
		
		//if last No at 10 the % will make index to 0, 
		//therefore we will set it to 10
		if(index == 0) {
			index = 10;
		}
		
		//button delete
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+(index+1)+"]/td/ul/li/span[2]/a[2]/i")).click();
		driver.switchTo().alert().accept();
		
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickDeleteUserByIndexWhenExpand(String index) {
		//table[@id='data-table-default']/tbody/tr[1]/td[5]/a[2]/i
		//table[@id='data-table-default']/tbody/tr[2]/td[5]/a[2]/i
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+index+"]/td[5]/a[2]/i")).click();
		driver.switchTo().alert().accept();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public List<List<WebElement>> getTableDataUser(){
		List<List<WebElement>> tableUser = new ArrayList<>();
		tableUser.add(tableKolomNo);
		tableUser.add(tableKolomNIK);
		tableUser.add(tableKolomName);
		tableUser.add(tableKolomPrivilage);
		return tableUser;
	}
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	public String getMessageDataTableEmpty() {
		return messageDataTablesEmpty.getText();
	}
	
	public String getTitlePopupNewUser() {
		return titlePopupAddNewUser.getText();
	}
	
	public String getTitlePopupEditUser() {
		return titlePopupEditUser.getText();
	}
	
	public String getMessageAddNewSuccess() {
		return messageNewDataSuccess.getText();
	}
	
	public JCBMasterUserPage clickBtnCancelPopupEdit() {
		btnCancelPopupEditUser.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public String getMessageDeleteSuccess() {
		return messageDeleteDataSuccess.getText();
	}
	
	public JCBMasterUserPage clickBtnCollapseExpand() {
		btnCollapseExpand.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickBtnExpandCompress() {
		btnExpandCompress.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public WebElement getElementPanelViewDataUser() {
		return panelViewDataUser;
	}
	
	public WebElement getElementPanelListTableUser() {
		return panelListTableUser;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement getElementTitlePopupEditUser() {
		return titlePopupEditUser;
	}
}
