package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.nexsoft.jcb.other.Tools;

public class JCBReportPage {

	protected WebDriver driver;
	Tools tool = new Tools();

	@FindBy(xpath = "//i[@class='fa fa-expand']")
	private WebElement expandBtn;

	@FindBy(xpath = "//a[2]/i")
	private WebElement collapseBtn;

	@FindBy(xpath = "//select[@id='kategori']")
	private WebElement kategoriBtn;

	@FindBy(xpath = "//input[@id='datepicker-autoClose']")
	private WebElement startDateField;

	@FindBy(xpath = "//input[@id='datepicker-autoClose2']")
	private WebElement endDateField;

	@FindBy(xpath = "//a[@onclick='getReport()']")
	private WebElement processBtn;

	@FindBy(xpath = "//a[@onclick='getReportNew()']")
	private WebElement processNewTemplateBtn;

	@FindBy(xpath = "//div[@id='header']/ul/li/a/b")
	private WebElement profile;

	@FindBy(xpath = "//div[@id='header']/ul/li/div/a")
	private WebElement btnLogout;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement textReportPage;

	public JCBReportPage(WebDriver driver) {
		this.driver = driver;
	}

	public JCBReportPage clickBtnExpand() {
		expandBtn.click();
		return PageFactory.initElements(driver, JCBReportPage.class);

	}

	public JCBReportPage clickBtnCollapse() {
		collapseBtn.click();
		return PageFactory.initElements(driver, JCBReportPage.class);

	}

	public JCBReportPage inputFieldStartDate(String startDate) {
		startDateField.sendKeys(startDate);
		return PageFactory.initElements(driver, JCBReportPage.class);

	}

	public JCBReportPage inputFieldEndDate(String endDate) {
		endDateField.sendKeys(endDate);
		return PageFactory.initElements(driver, JCBReportPage.class);

	}

	public JCBReportPage clickBtnProcess() {
		processBtn.click();
		return PageFactory.initElements(driver, JCBReportPage.class);

	}

	public JCBReportPage clickBtnProcessNewTemplate() {
		processNewTemplateBtn.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBReportPage.class);
	}
	
	public JCBReportPage selectDropdownListEntriesByValue(String value) {
		Select select = new Select(kategoriBtn);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBReportPage.class);
	}
	
	public WebElement getTextReportPage () {
		return textReportPage;
		
	}

}
