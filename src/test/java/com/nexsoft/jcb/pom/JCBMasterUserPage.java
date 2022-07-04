package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBMasterUserPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewUser;
	
	@FindBy(name = "data-table-default_length")//10, 25, 50, 100
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(name = "nik")
	private WebElement fieldNIKAddNewPopup;
	
	@FindBy(name = "name")
	private WebElement fieldNameAddNewPopup;
	
	@FindBy(name = "username")
	private WebElement fieldUsernameAddNewPopup;
	
	@FindBy(name = "password")
	private WebElement fieldPasswordAddNewPopup;
	
	@FindBy(name = "privilege")
	private WebElement dropdownListPrivilageAddNewPopup;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSave;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnCancel;
	
	
	@FindBy(xpath = "//a[normalize-space()='2']")
	private WebElement btnPageNavNumber;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	public JCBMasterUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public JCBMasterUserPage clickAddNewUser() {
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage inputAllAddUserField(String nik, String name, String username, String password, String privilege) {
		Select select = new Select(dropdownListPrivilageAddNewPopup);
		fieldNIKAddNewPopup.sendKeys(nik);
		fieldNameAddNewPopup.sendKeys(name);
		fieldUsernameAddNewPopup.sendKeys(username);
		fieldPasswordAddNewPopup.sendKeys(password);
		select.selectByValue(privilege);
		
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickBtnSave() {
		btnSave.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	public JCBMasterUserPage clickBtnCancel() {
		btnCancel.click();
		return PageFactory.initElements(driver, JCBMasterUserPage.class);
	}
	
	
	
	
}
