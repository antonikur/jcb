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

public class JCBBucketDistribusiAssignment {

	protected WebDriver driver;
	Tools tool = new Tools();

	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleAssignment;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titlePilihData;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[1]/h4")
	private WebElement titleViewData;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div")
	private WebElement titleSuccess;
	
	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[1]")
	private WebElement titleDataUser;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	@FindBy(xpath = "//a[@id='mybutton']")
	private WebElement btnPilihData;
	
	@FindBy(xpath = "//a[@id='buttonUser']")
	private WebElement btnPilihUser;
	
	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[2]/div/table/tbody/tr[1]/td[5]/a")
	private WebElement pilihUserAssignmentRama;
	
	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[1]/button")
	private WebElement btnX_Assignment;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]")
	private WebElement panelViewTableDis;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]")
	private WebElement panelListTableDis;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div")
	private WebElement panelViewPilihData;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]")
	private WebElement panelListPilihData;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement collapse;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement expand;
	
	@FindBy(xpath = "//a[@class='btn btn-xs btn-icon btn-circle btn-default']")
	private WebElement compress;
	
	@FindBy(xpath = "//label[@for='cssCheckbox1']")
	private WebElement check1;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]/div/table[2]/tbody/tr/td[1]")
	private List<WebElement> tableKolomNo;
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnProcess;
	
	@FindBy(xpath = "//*[@id=\"distribusi\"]/tbody/tr/td[3]/b")
	private WebElement txtDistribusiData;
	
	public JCBBucketDistribusiAssignment (WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTextProcess() {
		return txtDistribusiData.getText();
	}
	
	public String getTitleAssignment() {
		return titleAssignment.getText();
	}
	
	public String getTitlePilihData() {
		return titlePilihData.getText();
	}
	
	public String getTitleViewData() {
		return titleViewData.getText();
	}
	
	public String getTitleSuccess() {
		return titleSuccess.getText();
	}
	
	public String getTitleDataUser() {
		tool.stopForMoment(2000);
		
		return titleDataUser.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBBucketDistribusiAssignment clickPilihUser() {
		tool.stopForMoment(2000);
		btnPilihUser.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}

	
	public JCBBucketDistribusiAssignment clickPilihUserRama() {
		tool.stopForMoment(2000);
		//tool.scroolHorizontalWindows(driver, 100);
		tool.scrollByFindElement(driver, btnPilihUser);
		pilihUserAssignmentRama.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickXPilihUser() {
		tool.stopForMoment(2000);
		btnX_Assignment.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickPilihData() {
		btnPilihData.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public WebElement getElementPanelListTableUser() {
		return panelListTableDis;
	}
	
	public WebElement getElementPanelViewDataUser() {
		return panelViewTableDis;
	}
	
	public WebElement getElementPanelListPilihData() {
		return panelListPilihData;
	}
	
	public WebElement getElementPanelViewPilihData() {
		return panelViewPilihData;
	}
	
	public JCBBucketDistribusiAssignment clickCollapse() {
		collapse.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickExpand() {
		expand.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickCheck1() {
		check1.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickBtnProcess() {
		btnProcess.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment clickCheckBox(String data) {	
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]/div/table[2]/tbody/tr["+data+"]/td[6]")).click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public JCBBucketDistribusiAssignment pilihSurveyor(String no) {	
		driver.findElement(By.xpath("//*[@id=\"modalUser\"]/div/div/div[2]/div/table/tbody/tr["+no+"]/td[5]/a ")).click();
		return PageFactory.initElements(driver, JCBBucketDistribusiAssignment.class);
	}
	
	public List<List<WebElement>> getTableValidasiData(){
		List<List<WebElement>> tableValidasiData = new ArrayList<>();
		tableValidasiData.add(tableKolomNo);
		return tableValidasiData;
	}
	
	public List<WebElement> getColumnNo(){
		return tableKolomNo;
	}
	
	
}
