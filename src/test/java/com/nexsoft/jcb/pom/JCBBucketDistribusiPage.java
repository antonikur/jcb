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

public class JCBBucketDistribusiPage {

	protected WebDriver driver;
	Tools tool = new Tools();

	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleBucketDistribusi;
	
	@FindBy(xpath = "//*[@id=\"data-table-default\"]/tbody/tr[2]/td/ul/li[1]/span[1]")
	private WebElement titlePlus;
	
//	@FindBy(xpath = "//tbody/tr[2]/td[1]/ul[1]/li[1]/span[1]")
//	private WebElement titlePlus;
//	
	@FindBy(xpath = "//td[@class='sorting_1'][normalize-space()='1']")
	private WebElement btnPlus;
	
	@FindBy(xpath = "//span[@class='dtr-data']//i[@class='fa fa-eye']")
	private WebElement btnMata;
	
	@FindBy(xpath = "//select[@name='data-table-default_length']")
	private WebElement showData;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement collapse;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement expand;
	
	@FindBy(xpath = "//a[@class='btn btn-xs btn-icon btn-circle btn-default']")
	private WebElement compress;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement inputSearch;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement buttonNext;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement buttonPrevious;
	
	
	@FindBy(xpath = "//th[@aria-label='No.: activate to sort column descending']")
	private WebElement NoDesc;
	
	@FindBy(xpath = "//th[@aria-label='No.: activate to sort column ascending']")
	private WebElement NoAsc;
	
	@FindBy(xpath = "//table[@id='data-table-default']/thead/tr/th[2]")
	private WebElement NIKAscDesc;
	
	@FindBy(xpath = "//table[@id='data-table-default']/thead/tr/th[3]")
	private WebElement NameAscDesc;
	
	@FindBy(xpath = "//table[@id='data-table-default']/thead/tr/th[4]")
	private WebElement TotalDataAscDesc;
	
	@FindBy(xpath = "//table[@id='data-table-default']/thead/tr/th[5]")
	private WebElement DataVisitAscDesc;
	
	@FindBy(xpath = "//table[@id='data-table-default']/thead/tr/th[6]")
	private WebElement DataUnvisitAscDesc;
	
	@FindBy(xpath = "//a[@class='btn btn-danger m-b-5']")
	private WebElement btnAssignment;
	
	@FindBy(xpath = "//a[@id='mybutton']")
	private WebElement btnPilihData;
	
	@FindBy(xpath = "//h4[@class='panel-title']")
	private WebElement viewData;
	
	//table
	@FindBy(xpath = "//td[normalize-space()][1]")
	private List<WebElement> tableKolomNo;
		
	@FindBy(xpath = "//td[normalize-space()][2]")
	private List<WebElement> tableKolomNIK;
		
	@FindBy(xpath = "//td[normalize-space()][3]")
	private List<WebElement> tableKolomName;
		
	@FindBy(xpath = "//td[normalize-space()][4]")
	private List<WebElement> tableKolomTotalData;
	
	@FindBy(xpath = "//td[normalize-space()][5]")
	private List<WebElement> tableKolomDataVisit;
	
	@FindBy(xpath = "//td[normalize-space()][6]")
	private List<WebElement> tableKolomDataUnvisit;
	
	@FindBy(xpath = "//td[@class='dataTables_empty']")
	private WebElement messageDataTablesEmpty;//No matching records found
	
	@FindBy(name = "data-table-default_length")//10, 25, 50, 100
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//a[normalize-space()]")
	private List<WebElement> listButton;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	@FindBy(xpath = "//a[@onclick=\"Pilih('K1136039', 'RAMA DHANDY')\"]")
	private WebElement pilihUserAssignmentRama;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/div[2]")
	private WebElement panelListTableDis;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div")
	private WebElement panelViewTableDis;
	
	
	public JCBBucketDistribusiPage (WebDriver driver) {
		this.driver = driver;
			
	}
	
	public String getTitleDistribusiPage() {
		return titleBucketDistribusi.getText();
	}
	
	public String getTitlePlusAction() {
		tool.stopForMoment(2000);
		return titlePlus.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBBucketDistribusiPage input_Search(String search) {
		inputSearch.sendKeys(search);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public List<List<WebElement>> getTableDistribusiData(){
		List<List<WebElement>> tableDistribusiData = new ArrayList<>();
		tableDistribusiData.add(tableKolomNo);
		tableDistribusiData.add(tableKolomNIK);
		tableDistribusiData.add(tableKolomName);
		tableDistribusiData.add(tableKolomTotalData);
		tableDistribusiData.add(tableKolomDataVisit);
		tableDistribusiData.add(tableKolomDataUnvisit);
		return tableDistribusiData;
	}
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	public List<WebElement> getColumnNIK(){
		return tableKolomNIK;
	}
	
	public List<WebElement> getColumnName(){
		return tableKolomName;
	}
	
	public List<WebElement> getColumnTotalData(){
		return tableKolomTotalData;
	}
	
	public List<WebElement> getColumnDataVisit(){
		return tableKolomDataVisit;
	}
	
	public List<WebElement> getColumnUnvisit(){
		return tableKolomDataUnvisit;
	}
	
	public JCBBucketDistribusiPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnNoDesc() {
		NoDesc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnNoAsc() {
		NoAsc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnNIKAscDesc() {
		NIKAscDesc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnNameAscDesc() {
		NameAscDesc.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnTotalDataAscDesc() {
		TotalDataAscDesc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnDataVisitAscDesc() {
		DataVisitAscDesc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnDataUnvisitAscDesc() {
		DataUnvisitAscDesc.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnPrevious() {
		buttonPrevious.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnNext() {
		buttonNext.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickButtonPlus(String number) {	
		driver.findElement(By.xpath("//td[@class='sorting_1'][normalize-space()='"+number+"']")).click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnPilihData() {
		btnPilihData.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickBtnPlus() {
		btnPlus.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiDetail clickAndGoToBucketDetailDistribusi() {
		btnMata.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiAssignment clickAndGoToDetailAssignment() {
		btnAssignment.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiPage clickExpand() {
		expand.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickCollapse() {
		collapse.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public JCBBucketDistribusiPage clickCompress() {
		compress.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
	}
	
	public String getTitleExpand() {
		return viewData.getText();
	}
	
	public List<WebElement> getListButtonForNavigate(){
		return listButton;
	}
	
	public WebElement getElementPanelListTableUser() {
		return panelListTableDis;
	}
	
	public WebElement getElementPanelViewDataUser() {
		return panelViewTableDis;
	}
	
}
