package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JCBLoginPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//input[@placeholder='User']")
	private WebElement fieldUsername;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement fieldPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnLogin;
	
	@FindBy(xpath = "//small[normalize-space()='User / Password tidak sesuai.']")
	private WebElement pesanErrorInvalidUserPassword;
	
//	@FindBy(xpath = "")
//	private WebElement promptAlertUserEmpty;
//	
//	@FindBy(xpath = "")
//	private WebElement promptAlertPasswordEmpty;
	
	
	public JCBLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public JCBLoginPage inputFieldUsername(String username) {
		fieldUsername.clear();
		fieldUsername.sendKeys(username);
		return PageFactory.initElements(driver, JCBLoginPage.class);
		
	}
	
	public JCBLoginPage inputFieldPassword(String password) {
		fieldPassword.clear();
		fieldPassword.sendKeys(password);
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBLoginPage clickBtnLogin() {
		btnLogin.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public String getPromptAlertTextFieldEmpty() {
		return driver.switchTo().alert().getText();
	}
	
	public String getErrMsgInvalidUsernamePassword() {
		return pesanErrorInvalidUserPassword.getText();
	}
	
	
	public JCBHomePage gotoHomePage() {
		return PageFactory.initElements(driver, JCBHomePage.class);
	}
	
	
}
