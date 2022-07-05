package com.nexsoft.jcb.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBMasterKotaPage {

	protected WebDriver driver;
	
	@FindBy(xpath = "//a[@class='btn btn-info']")
	private WebElement btnAddNewKota;
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement fieldSearch;
	
	@FindBy(xpath = "//a[normalize-space()='Previous']")
	private WebElement btnPageNavPrev;
	
	@FindBy(xpath = "//a[normalize-space()='Next']")
	private WebElement btnPageNavNext;
	
	@FindBy(name = "data-table-default_length")
	private WebElement dropdownListEntries;
	
	//add new kota
	@FindBy(xpath = "//h4[@id='myModalLabel']")//Form Input Kota
	private WebElement titleKotaPopupAddNewKota;
	
	@FindBy(xpath = "//input[@name='city']")
	private WebElement fieldKotaPopupAddNewKota;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement btnSavePopupAddNewKota;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnCancelPopupAddNewKota;
	
	//edit kota
	@FindBy(xpath = "(//input[@name='city'])[2]")
	private WebElement fieldPopupEditKota;
	
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCancelPopupEditKota;
	
	@FindBy(xpath = "(//button[@type='submit'])[3]")
	private WebElement btnSavePopupEditKota;
	
	@FindBy(xpath = "(//h4[@id='myModalLabel'])[2]")//Form Update Kota
	private WebElement titlePopupEditKota;
	
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	public JCBMasterKotaPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//page navigate
	public JCBMasterKotaPage clickPageNumber(String page) {	
		driver.findElement(By.xpath("//a[normalize-space()='"+page+"']")).click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(dropdownListEntries);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	public JCBMasterKotaPage clickAddNewKota() {
		btnAddNewKota.click();
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	
	public JCBMasterKotaPage clickEditKotaByIndex(String index) {
		driver.findElement(By.xpath("//td[normalize-space()='"+index+"']")).click();
		
		driver.findElement(By.xpath("//table[@id='data-table-default']/tbody/tr["+(Integer.parseInt(index)+1)+"]/td/ul/li/span[2]/a/i")).click();
		
		return PageFactory.initElements(driver, JCBMasterKotaPage.class);
	}
	
	
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
}
