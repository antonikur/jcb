package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBAddNewDataPageSurveyor {

	WebDriver driver;

	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[1]/div/select")
	private WebElement dropDownListKota;
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[2]/div/select")
	private WebElement dropDownListArea;
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[3]/div/select")
	private WebElement dropDownListBatch;

	@FindBy(xpath = "// input[@name='merchant_name']")
	private WebElement fieldMerchantName;

	@FindBy(xpath = "// textarea[@name='address']")
	private WebElement fieldAddress;
	
	@FindBy(xpath = "// input[@name='address_by_floor']")
	private WebElement fieldAddressByFloor;

	@FindBy(xpath = "// input[@name='category']")
	private WebElement fieldCategory;

	@FindBy(xpath = "// input[@name='pic']")
	private WebElement fieldPIC;

	@FindBy(xpath = "// input[@name='no_telp_pic']")
	private WebElement fieldNoTelpPIC;
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[10]/div/select")
	private WebElement dropDownListPosition;
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[11]/div/select")
	private WebElement dropDownListMerchantAccJCBCard;
	
	@FindBy(xpath = "//select[@name='business_issue']")
	private WebElement dropDownListBusinessIssue;
	
	@FindBy(xpath = "//input[@id='other_issue']")
	private WebElement fieldInputReason;
	
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[13]/div/button[1]")
	private WebElement submitBtn;
	
	@FindBy(xpath = "// *[@id=\"content\"]/div/div[2]/div/div[2]/form/div[13]/div/button[2]")
	private WebElement cancelBtn;
	
	public JCBAddNewDataPageSurveyor (WebDriver driver) {
		this.driver = driver;
	
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListKotaEntriesByValue(String value) {
		Select select = new Select(dropDownListKota);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListAreaEntriesByValue(String value) {
		Select select = new Select(dropDownListArea);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListBatchEntriesByValue(String value) {
		Select select = new Select(dropDownListBatch);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputMerchantName(String name) {
		fieldMerchantName.sendKeys(name);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}

	public JCBAddNewDataPageSurveyor inputAddress(String address) {
		fieldAddress.sendKeys(address);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputAddressByFloor(String addressByFloor) {
		fieldAddressByFloor.sendKeys(addressByFloor);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputCategory(String category) {
		fieldCategory.sendKeys(category);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputPIC(String PIC) {
		fieldPIC.sendKeys(PIC);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputNoTelpPIC(String noTelpPIC) {
		fieldNoTelpPIC.sendKeys(noTelpPIC);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListPositionEntriesByValue(String value) {
		Select select = new Select(dropDownListPosition);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListMerchantAccJCBCardEntriesByValue(String value) {
		Select select = new Select(dropDownListMerchantAccJCBCard);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor selectDropdownListBusinessIssueEntriesByText(String value) {
		Select select = new Select(dropDownListBusinessIssue);
		select.selectByVisibleText(value);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor inputReason(String reason) {
		fieldInputReason.sendKeys(reason);
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBAddNewDataPageSurveyor clickBtnCancel() {
		cancelBtn.click();
		return PageFactory.initElements(driver, JCBAddNewDataPageSurveyor.class);
	}
	
	public JCBNewDataVisitPage clickBtnSubmit() {
		submitBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPage.class);
	}
	
	
	
	
	

}
