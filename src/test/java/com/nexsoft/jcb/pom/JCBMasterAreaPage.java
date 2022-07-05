package com.nexsoft.jcb.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBMasterAreaPage {
	protected WebDriver driver;
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewArea;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	//navigate
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	@FindBy(name = "data-table-default_length")
	private WebElement dropdownListEntries;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	//new area
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement titlePopupNewArea;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSavePopupNewArea;
	
	@FindBy(xpath = "(//button[@type='submit']")
	private WebElement btnCancelPopupNewArea;
	
	@FindBy(xpath = "//input[@name='area']")
	private WebElement fieldPopupNewArea;
	
	//edit area
	@FindBy(xpath = "(//input[@name='area'])[2]")
	private WebElement fieldPopupEditArea;
	
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")
	private WebElement titlePopupEditArea;
	
	@FindBy(xpath = "(//button[@type='submit'])[3]")
	private WebElement btnSavePopupEditArea;
	
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCancelPopupEditArea;
	
	public JCBMasterAreaPage(WebDriver driver){
		this.driver = driver;
	}
	
	//page navigate
	public JCBMasterAreaPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	public JCBMasterAreaPage clickEditAreaByIndex(String index) {
		driver.findElement(By.xpath("//td[normalize-space()='"+index+"']")).click();
		
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+Integer.parseInt(index)+1+"]/td/ul/li/span[2]/a/i")).click();
		return PageFactory.initElements(driver, JCBMasterAreaPage.class);
	}
	
	
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	
	
}
