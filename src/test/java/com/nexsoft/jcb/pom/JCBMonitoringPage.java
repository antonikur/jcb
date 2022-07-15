package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JCBMonitoringPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleMonitoring;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	public JCBMonitoringPage(WebDriver driver) {
		this.driver = driver;
	}
		
	public String getTitleMonitoring() {
		return titleMonitoring.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
}
