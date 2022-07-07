package com.nexsoft.jcb.pom;

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
	
	public JCBDashboardPage(WebDriver driver){
		this.driver = driver;
	}
	
	public String getTitle() {
		return titleDashboard.getText();
	}
	
	public JCBMonitoringPage clickDetailAndGotoMonitoring() {
		tool.scrollByFindElement(driver, btnDetail);
		return PageFactory.initElements(driver, JCBMonitoringPage.class);
	}
	
}
