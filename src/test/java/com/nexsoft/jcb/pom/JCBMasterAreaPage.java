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

public class JCBMasterAreaPage {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewArea;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titlePageArea;
	
	//navigate
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	@FindBy(name = "data-table-default_length")
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	//new area
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement titlePopupNewArea;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSavePopupNewArea;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnCancelPopupNewArea;
	
	@FindBy(xpath = "//input[@name='area']")
	private WebElement fieldPopupNewArea;
	
	
	//edit area
	@FindBy(xpath = "(//input[@name='area'])[2]")
	private WebElement fieldPopupEditArea;
	
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")
	private WebElement titlePopupEditArea;
	
	@FindBy(xpath = "(//button[@type='submit'])[3]")
	private WebElement btnSavePopupEditArea;
	
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCancelPopupEditArea;
	
	//table
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomKodeArea;
	
	@FindBy(xpath = "//td[normalize-space()][3]")
	private List<WebElement> tableKolomArea;
	
	//panel
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/div/div[2]")
	private WebElement panelListTableArea;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[3]/div")
	private WebElement panelViewDataArea;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement btnExpandCompress;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement btnCollapseExpand;
	
	//message
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement messageAddArea;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement messageEditArea;
	
	public JCBMasterAreaPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	//page navigate
	public JCBMasterAreaPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterUserPage clickNextPage() {
		btnPageNavNext.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickPreviousPage() {
		btnPageNavPrev.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterAreaPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage inputFieldSearch(String keyword) {
		fieldSearch.clear();
		fieldSearch.sendKeys(keyword);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public String getTitleAreaPage() {
		return titlePageArea.getText();
	}
	
	//add
	public JCBMasterAreaPage clickBtnAddNewArea() {
		btnAddNewArea.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickCancelPopupNewArea() {
		btnCancelPopupNewArea.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage inputFieldPopupNewArea(String area) {
		fieldPopupNewArea.sendKeys(area);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickSavePopupNewArea() {
		btnSavePopupNewArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public String getMessageAddSuccess() {
		return messageAddArea.getText();
	}
	
	public String getTitlePopupAddArea() {
		return titlePopupNewArea.getText();
	}

	
	//edit
	public JCBMasterAreaPage clickEditAreaByIndex(String index) {
		driver.findElement(By.xpath("//td[normalize-space()='"+index+"']")).click();
		tool.stopForMoment(1000);
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+(Integer.parseInt(index)+1)+"]/td/ul/li/span[2]/a/i")).click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickSavePopupEditArea() {
		btnSavePopupEditArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickCancelPopupEditArea() {
		btnCancelPopupEditArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage inputFieldPopupEditArea(String area) {
		fieldPopupEditArea.clear();
		fieldPopupEditArea.sendKeys(area);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickSaveEditArea() {
		btnSavePopupEditArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public String getTitlePopupEditArea() {
		return titlePopupEditArea.getText();
	}
	
	//table data
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	public List<List<WebElement>> getTableDataArea(){
		List<List<WebElement>> tableUser = new ArrayList<>();
		tableUser.add(tableKolomNo);
		tableUser.add(tableKolomKodeArea);
		tableUser.add(tableKolomArea);
		return tableUser;
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	//panel
	public WebElement getElementPanelViewDataArea() {
		return panelViewDataArea;
	}
	
	public WebElement getElementPanelListTableArea() {
		return panelListTableArea;
	}
	
	public JCBMasterAreaPage clickBtnCollapseExpand() {
		btnCollapseExpand.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickBtnExpandCompress() {
		btnExpandCompress.click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	//click edit when expand
	public JCBMasterAreaPage clickEditKotaByIndexWhenExpand(String index) {
		//table[@id='data-table-default']/tbody/tr[1]/td[4]/a/i
		//table[@id='data-table-default']/tbody/tr[2]/td[4]/a/i
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+index+"]/td[4]/a/i")).click();
		
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public WebElement getElementTitlePopupEditArea() {
		return titlePopupEditArea;
	}
	
	public JCBMasterAreaPage clickBtnCancelPopupEdit() {
		btnCancelPopupEditArea.click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
}
