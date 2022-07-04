package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JCBReportPage {
	
protected WebDriver driver;
	
	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement expandBtn;

}
