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

public class JCBBucketValidasiPage {
	
protected WebDriver driver;
Tools tool = new Tools();

	@FindBy(xpath = "/html/body/a")
	private WebElement btnScroll;

	@FindBy(xpath = "//tbody/tr[1]/td[8]/a[1]/i[1]")
	private WebElement btnMata;

	@FindBy(xpath = "//button[@id='btn-filter']")
	private WebElement btnFilter;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement inputSearchVal;
	
	@FindBy(xpath = "//i[@class='fa fa-search']")
	private WebElement SearchVal;
	
	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement btnSearchVal;
	
	@FindBy(xpath = "//tbody/tr[1]/td[8]/a[1]/i[1]")
	private WebElement action;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnNext;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPrevious;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement btnValidate;
	
	@FindBy(xpath = "//button[@class='btn btn-warning']")
	private WebElement btnReturn;
	
	@FindBy(xpath = "//button[@class='btn btn-warning']")
	private WebElement btnReject;
	
	@FindBy(xpath = "//h4[@class='panel-title']")
	private WebElement titleBucketValidasi;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	//table
	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[1]")
	private List<WebElement> tableKolomNo;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[2]")
	private List<WebElement> tableKolomArea;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[3]")
	private List<WebElement> tableKolomMerchantName;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[4]")
	private List<WebElement> tableKolomAddress;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[5]")
	private List<WebElement> tableKolomOfficer;
	
	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[6]")
	private List<WebElement> tableKolomVisitDate;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[7]")
	private List<WebElement> tableKolomNote;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[8]")
	private List<WebElement> tableKolomAction;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	private WebElement messageDataTablesEmpty;// No matching records found
	
//	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]")
//	private WebElement panelListTableDis;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/div[3]")
	private WebElement panelListTableDis;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement collapse;
	
	@FindBy(name = "area")
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//table[@id='data-worklist']/tbody/tr/td[2]/a/i")
	private WebElement visitBtn;
	
	@FindBy(xpath = "//select[@id='id_area']")
	private WebElement areaBtn;
	
	@FindBy(xpath = "//*[@id=\"data-worklist_paginate\"]/ul/li[3]/a")
	private WebElement btnPageNumber2;
	
	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr/td[2]/b")
	private WebElement textArea;
	
	@FindBy(xpath = "//tr[@class='odd']//td[contains(text(),'Plaza Indonesia')]")
	private WebElement textPI;

	public JCBBucketValidasiPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitleValidasiPage() {
		return titleBucketValidasi.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBBucketValidasiPage input_Search(String search) {
		inputSearchVal.sendKeys(search);
		tool.stopForMoment(2000);
		SearchVal.click();
		tool.stopForMoment(2000);

		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public List<List<WebElement>> getTableValidasiData(){
		List<List<WebElement>> tableValidasiData = new ArrayList<>();
		tableValidasiData.add(tableKolomNo);
		tableValidasiData.add(tableKolomArea);
		tableValidasiData.add(tableKolomMerchantName);
		tableValidasiData.add(tableKolomAddress);
		tableValidasiData.add(tableKolomOfficer);
		tableValidasiData.add(tableKolomVisitDate);
		tableValidasiData.add(tableKolomNote);
		tableValidasiData.add(tableKolomAction);
		return tableValidasiData;
	}
	
	public List<WebElement> getColumnArea(){
		return tableKolomArea;
	}
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	public JCBValidasiDetailVisit clickAndGoToValidasiDetailVisit() {
		btnMata.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public WebElement getElementPanelValidasi() {
		return panelListTableDis;
	}
	
	public JCBBucketValidasiPage clickCollapse() {
		collapse.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketValidasiPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketValidasiPage clickBtnFilter() {
		tool.stopForMoment(2000);
		btnFilter.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketValidasiPage clickBtnVisitAndGoToVisitPage() {
		visitBtn.click();
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		
	}
	
	public String getSelectedOption() {
		Select select = new Select(areaBtn);
		String dapat = select.getFirstSelectedOption().getText();
		System.out.println(dapat);
		return dapat;
	}
	
	public String getTextArea() {
		return textArea.getText();
	}
	
	public String getTextPI() {
		return textArea.getText();
	}
	
	public JCBBucketValidasiPage clickBtnPrevious() {
		btnPrevious.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketValidasiPage clickBtnNext() {
		btnNext.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketValidasiPage clickBtnPageNumber2() {
		btnPageNumber2.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
	public JCBBucketDistribusiPage clickPageNumber(String number) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+number+"']")).click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketValidasiPage clickBtnScroll() {
		btnScroll.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketValidasiPage.class);
	}
	
}
