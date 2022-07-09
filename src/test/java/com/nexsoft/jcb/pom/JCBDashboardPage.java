package com.nexsoft.jcb.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nexsoft.jcb.other.Tools;

public class JCBDashboardPage {

	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleDashboard;
	
	@FindBy(xpath = "//a[normalize-space()='Detail']")
	private WebElement btnDetail;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement btnExpandCompress;
	
	@FindBy(xpath = "//i[@class='fa fa-redo']")
	private WebElement btnReload;
	
	@FindBy(xpath = "//i[@class='fa fa-minus']")
	private WebElement btnCollapseExpand;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	
	//panel for expand/compress and collapse/expand
	//*[@id=\"content\"]/div[2]/div/div
	//*[@id="content"]/div[2]/div
	//*[@id="content"]/div[2]/div/div
	@FindBy(xpath = "//*[@id=\"content\"]/div[2]/div/div")
	private WebElement panelSummaryByArea;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[2]/div/div/div[2]")
	private WebElement panelTableSummaryByArea;
	
	
	
	public JCBDashboardPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle() {
		return titleDashboard.getText();
	}
	
	public JCBMonitoringPage clickDetailAndGotoMonitoring() {
		tool.scrollByFindElement(driver, btnDetail);
		btnDetail.click();
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	//panel 
	public WebElement getElementPanelViewSummaryByArea() {
		return panelSummaryByArea;
	}
	
	public WebElement getElementPanelTableSummaryByArea() {
		return panelTableSummaryByArea;
	}
	
	//column table, footer result also included in the end of the list
					 //div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[2]
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]")
	private List<WebElement> listKolomJumlahMerchant;
	
					 //div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[3]
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr/td[3]")
	private List<WebElement> listKolomJumlahVisit;
	
					 //div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[4]
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr/td[4]")
	private List<WebElement> listKolomJumlahUnvisit;
	
					 //div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr/td[5]
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr/td[5]")
	private List<WebElement> listKolomTotalPrice;
	
	//column footer
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[130]/td[2]")
	private WebElement resultFooterJumlahMerchant;
	
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[130]/td[3]")
	private WebElement resultFooterJumlahVisit;
	
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[130]/td[4]")
	private WebElement resultFooterJumlahUnvisit;
	
	@FindBy(xpath = "//div[@id='content']/div[2]/div/div/div[2]/div/table/tbody/tr[130]/td[5]")
	private WebElement resultFooterTotalPrice;
	
	//panel result header
	@FindBy(xpath = "//div[@id='content']/div/div[1]/div/div[2]/p")
	private WebElement monthlyVisitHeader;
	
	@FindBy(xpath = "//div[@id='content']/div/div[2]/div/div[2]/p")
	private WebElement unvisitHeader;
	
	@FindBy(xpath = "//div[@id='content']/div/div[3]/div/div[2]/p")
	private WebElement monthlyPriceHeader;
	
	@FindBy(xpath = "//div[@id='content']/div/div[4]/div/div[2]/p")
	private WebElement totalPriceHeader;
	
	
	//button expand collapse reload
	public JCBDashboardPage clickBtnExpandCompress() {
		btnExpandCompress.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBDashboardPage.class);
	}
	
	public JCBDashboardPage clickBtnCollapseExpand() {
		btnCollapseExpand.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBDashboardPage.class);
	}
	
	public JCBDashboardPage clickBtnReload() {
		btnReload.click();
		return PageFactory.initElements(driver, JCBDashboardPage.class);
	}
	
	//get data list
	public List<WebElement> getListColumnJumlahMerchant(){
		return listKolomJumlahMerchant;
	}
	
	public List<WebElement> getListColumnJumlahVisit(){
		return listKolomJumlahVisit;
	}
	
	public List<WebElement> getListColumnJumlahUnvisit(){
		return listKolomJumlahUnvisit;
	}
	
	public List<WebElement> getListColumnJumlahTotalPrice(){
		return listKolomTotalPrice;
	}
	
	//get data header total
	public String getTextTotalMonthlyVisit() {
		return monthlyVisitHeader.getText();
	}
	
	public String getTextTotalUnvisit() {
		return unvisitHeader.getText();
	}
	
	public String getTextTotalMonthlyPrice() {
		return monthlyPriceHeader.getText();
	}
	
	public String getTextTotalPrice() {
		return totalPriceHeader.getText();
	}
	
	
}
