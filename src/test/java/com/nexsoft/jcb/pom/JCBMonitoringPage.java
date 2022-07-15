package com.nexsoft.jcb.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nexsoft.jcb.other.Tools;

public class JCBMonitoringPage {
	
protected WebDriver driver;
Tools tool = new Tools();
	
	@FindBy(xpath = "/html/body/a")
	private WebElement btnScroll;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement btnSearch;
	
//	@FindBy(xpath = "//b[normalize-space()='Yogyakarta']//i[@class='fa fa-plus']")
//	private WebElement btnPlus;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement btnExpand;
	
	@FindBy(xpath = "//a[@class='btn btn-xs btn-icon btn-circle btn-default']")
	private WebElement btnCompress;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement btnCollapse;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleMonitoring;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	
	@FindBy(xpath = "//*[@id=\"data-monitoring\"]/tbody/tr/td[1]")
	private List<WebElement> tableKolomKota2;
	
	@FindBy(xpath = "//table[@id='data-monitoring']/tbody/tr/td/b")
	private List<WebElement> tableKolomKota;

	@FindBy(xpath = "//th[normalize-space()='Total Data']")
	private List<WebElement> tableKolomTotalData;

	@FindBy(xpath = "//th[normalize-space()='Total visit']")
	private List<WebElement> tableKolomTotalVisit;

	@FindBy(xpath = "//th[normalize-space()='Price']")
	private List<WebElement> tableKolomPrice;
	
//	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div")
//	private WebElement panelListTableMon;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]")
	private WebElement panelViewTableMon;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]")
	private WebElement panelListTableMon;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement expand;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement collapse;
	
//	@FindBy(xpath = "//*[@id=\"data-monitoring\"]/tbody/tr[5]/td[1]/b/")
//	private WebElement btnPlus;
	
	@FindBy(xpath = "//*[@id=\"data-monitoring\"]/tbody/tr[2]/td[1]")
	private WebElement detailKota;
	
	@FindBy(xpath = "//*[@id=\"data-monitoring\"]/tbody/tr/td[1]")
	private List<WebElement> kolomKota;
	
	public JCBMonitoringPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public List<WebElement> getListKolomKota(){
		return kolomKota;
	}
	
	public String getTitleMonitoring() {
		return titleMonitoring.getText();
	}
	
	public JCBMonitoringPage inputFieldSearch(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		btnSearch.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public WebElement getElementPanelListMonitor() {
		return panelListTableMon;
	}
	
	public WebElement getElementPanelViewMonitor() {
		return panelViewTableMon;
	}
	
	public JCBMonitoringPage input_Search(String search) {
		fieldSearch.clear();
		fieldSearch.sendKeys(search);
		tool.stopForMoment(15000);
		btnSearch.click();
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public List<List<WebElement>> getTableMonitoring(){
		List<List<WebElement>> tableMonitoring = new ArrayList<>();
		tableMonitoring.add(tableKolomKota);
		tableMonitoring.add(tableKolomTotalData);
		tableMonitoring.add(tableKolomTotalVisit);
		tableMonitoring.add(tableKolomPrice);
		return tableMonitoring;
	}
	
	public List<List<WebElement>> getTableMonitoring2(){
		List<List<WebElement>> tableMonitoring = new ArrayList<>();
		tableMonitoring.add(tableKolomKota2);
		tableMonitoring.add(tableKolomTotalData);
		tableMonitoring.add(tableKolomTotalVisit);
		tableMonitoring.add(tableKolomPrice);
		return tableMonitoring;
	}
	
	public List<WebElement> getColumnKota(){
		return tableKolomKota;
	}
	
	public JCBMonitoringPage clickBtnPlus(String kota) {	
		driver.findElement(By.xpath("//b[normalize-space()='"+kota+"']//i[@class='fa fa-plus']")).click();
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBMonitoringPage clickBtnMinus(String kota) {	
		driver.findElement(By.xpath("//b[normalize-space()='"+kota+"']//a[@class='btn btn-warning btn-icon btn-circle btn-sm']")).click();
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBMonitoringPage clickExpand() {
		expand.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBMonitoringPage clickCollapse() {
		collapse.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBMonitoringPage clickBtnScroll() {
		btnScroll.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
}
