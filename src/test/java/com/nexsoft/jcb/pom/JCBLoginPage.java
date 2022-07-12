package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nexsoft.jcb.other.Tools;

public class JCBLoginPage {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	@FindBy(xpath = "//input[@placeholder='User']")
	private WebElement fieldUsername;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement fieldPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnLogin;
	
	@FindBy(xpath = "//small[normalize-space()='User / Password tidak sesuai.']")
	private WebElement pesanErrorInvalidUserPassword;
	
	@FindBy(id = "myModalLabel")
	private WebElement titlePopUpEditUser;
	
//	@FindBy(xpath = "")
//	private WebElement promptAlertUserEmpty;
//	
//	@FindBy(xpath = "")
//	private WebElement promptAlertPasswordEmpty;
	
	@FindBy(xpath = "//b[normalize-space()='DIKA | JCB']")
	private WebElement titleLoginPage;
	
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
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public String getTitleLogin() {
		return titleLoginPage.getText();
	}
	
	public String getPromptAlertTextFieldEmpty() {
		return driver.switchTo().alert().getText();
	}
	
	public String getErrMsgInvalidUsernamePassword() {
		tool.stopForMoment(2000);
		return pesanErrorInvalidUserPassword.getText();
	}
	
	
	public JCBHomePage gotoHomePage() {
		return PageFactory.initElements(driver, JCBHomePage.class);
	}
	
	public WebElement getElementFieldUsername() {
		return fieldUsername;
	}
	
	public WebElement getElementFieldPassword() {
		return fieldPassword;
	}
	
}
