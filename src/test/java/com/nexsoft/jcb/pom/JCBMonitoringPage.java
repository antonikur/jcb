package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JCBMonitoringPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleMonitoring;
	
	public JCBMonitoringPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitleMonitoring() {
		return titleMonitoring.getText();
	}
}
