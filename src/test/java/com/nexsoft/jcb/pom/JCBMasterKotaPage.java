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

public class JCBMasterKotaPage {

	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewKota;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	@FindBy(name = "data-table-default_length")
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleKotaPage;
	
	//add new kota
	@FindBy(xpath = "//h4[@id='myModalLabel']")//Form Input Kota
	private WebElement titleKotaPopupAddNewKota;
	
	@FindBy(xpath = "//input[@name='city']")
	private WebElement fieldKotaPopupAddNewKota;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSavePopupAddNewKota;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnCancelPopupAddNewKota;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/div")
	private WebElement messageAddKotaComplete;
	
	
	//edit kota
	@FindBy(xpath = "(//input[@name='city'])[2]")
	private WebElement fieldPopupEditKota;
	
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCancelPopupEditKota;
	
	@FindBy(xpath = "(//button[@type='submit'])[3]")
	private WebElement btnSavePopupEditKota;
	
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")//Form Update Kota
	private WebElement titlePopupEditKota;
	
	@FindBy(xpath = "//*[@id=\\\"content\\\"]/div[1]/div[2]/div")
	private WebElement messageEditKotaComplete;
	
	//table
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomKota;

	
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	public JCBMasterKotaPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//page navigate
	public JCBMasterKotaPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public String getTitleKotaPage() {
		return titleKotaPage.getText();
	}
	
	//add kota
	public JCBMasterKotaPage clickAddNewKota() {
		btnAddNewKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public String getTitlePopupAddKota() {
		return titleKotaPopupAddNewKota.getText();
	}
	
	public JCBMasterKotaPage inputFieldPopupAddNewKota(String kota) {
		fieldKotaPopupAddNewKota.sendKeys(kota);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage clickBtnSavePopupAddKota() {
		btnSavePopupAddNewKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public String getMessageAddKotaComplete() {
		return messageAddKotaComplete.getText();
	}
	
	//edit kota
	public JCBMasterKotaPage clickEditKotaByIndex(String index) {
		driver.findElement(By.xpath("//td[normalize-space()='"+index+"']")).click();
		
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+(Integer.parseInt(index)+1)+"]/td/ul/li/span[2]/a/i")).click();
		tool.stopForMoment(1500);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage inputFieldPopupEditKota(String kota) {
		fieldPopupEditKota.clear();
		fieldPopupEditKota.sendKeys(kota);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public String getTitlePopupEditKota() {
		return titlePopupEditKota.getText();
	}
	
	public JCBMasterKotaPage clickSaveEditKota() {
		btnSavePopupEditKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage clickCancelEditKota() {
		btnCancelPopupEditKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	
	
	public List<List<WebElement>> getTableDataKota(){
		List<List<WebElement>> tableUser = new ArrayList<>();
		tableUser.add(tableKolomNo);
		tableUser.add(tableKolomKota);
		return tableUser;
	}
	
	public JCBMasterKotaPage inputFieldSearch(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
}
