package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBReportPageSurveyor {

	protected WebDriver driver;

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

	public JCBReportPageSurveyor(WebDriver driver) {
		this.driver = driver;
	}

	public JCBReportPageSurveyor clickBtnExpand() {
		expandBtn.click();
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);

	}

	public JCBReportPageSurveyor clickBtnCollapse() {
		collapseBtn.click();
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);

	}

	public JCBReportPageSurveyor inputFieldStartDate(String startDate) {
		startDateField.sendKeys(startDate);
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);

	}

	public JCBReportPageSurveyor inputFieldEndDate(String endDate) {
		endDateField.sendKeys(endDate);
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);

	}

	public JCBReportPageSurveyor clickBtnProcess() {
		processBtn.click();
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);

	}

	public JCBReportPageSurveyor clickBtnProcessNewTemplate() {
		processNewTemplateBtn.click();
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);
	}

	public JCBReportPageSurveyor LogOut() {
		profile.click();
		btnLogout.click();
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);
	}

	public JCBLoginPage gotoLoginPage() {
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}

	public JCBReportPageSurveyor selectDropdownListEntriesByValue(String value) {
		Select select = new Select(kategoriBtn);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBReportPageSurveyor.class);
	}

}
