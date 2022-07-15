package com.nexsoft.jcb.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class JCBReturnPageSurveyor {

	protected WebDriver driver;

	@FindBy(xpath = "//*[@id=\"id_area\"]")
	private WebElement areaBtn;

	@FindBy(xpath = "//button[@id='btn-filter']")
	private WebElement filterBtn;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchField;

	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement searchBtn;

	@FindBy(xpath = "//table[@id='data-worklist']/tbody/tr/td[2]/a")
	private WebElement visitBtn;

	@FindBy(xpath = "//table[@id='data-worklist']/tbody/tr/td[2]/a[2]")
	private WebElement otherConditionBtn;

	@FindBy(xpath = "//div[@id='header']/ul/li/a/b")
	private WebElement profile;

	@FindBy(xpath = "//div[@id='header']/ul/li/div/a")
	private WebElement btnLogout;

	@FindBy(xpath = "//tbody/tr/td[1]/div[1]/h4[1]")
	private List<WebElement> tableColumnName;

	@FindBy(xpath = "//strong[normalize-space()='Success!']")
	private WebElement textSuccessUploadPhoto;

	public JCBReturnPageSurveyor(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getTextSuccessUploadPhoto() {
		return textSuccessUploadPhoto;
	}

	public WebElement getTextSuccessUpdateVisitStatus() {
		return textSuccessUploadPhoto;
	}

	public JCBReturnPageSurveyor clickBtnFilter() {
		filterBtn.click();
		return PageFactory.initElements(driver, JCBReturnPageSurveyor.class);

	}

	public JCBReturnPageSurveyor clickSearchField(String search) {
		searchField.sendKeys(search);
		return PageFactory.initElements(driver, JCBReturnPageSurveyor.class);

	}

	public JCBReturnPageSurveyor clickBtnSearch() {
		searchBtn.click();
		return PageFactory.initElements(driver, JCBReturnPageSurveyor.class);

	}

	public JCBReturnVisitPage clickBtnVisitAndGoToVisitPage() {
		visitBtn.click();
		return PageFactory.initElements(driver, JCBReturnVisitPage.class);

	}

	public JCBReturnOtherConPage clickBtnOtherConditionAndGoToOtherConditionPage() {
		otherConditionBtn.click();
		return PageFactory.initElements(driver, JCBReturnOtherConPage.class);

	}

	public JCBReturnPageSurveyor LogOut() {
		profile.click();
		btnLogout.click();
		return PageFactory.initElements(driver, JCBReturnPageSurveyor.class);
	}

	public JCBLoginPage gotoLoginPage() {
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}

	public JCBReturnPageSurveyor selectDropdownListEntriesByValue(String value) {
		Select select = new Select(areaBtn);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBReturnPageSurveyor.class);
	}

	public String getSelectedOption() {
		Select select = new Select(areaBtn);
		String dapat = select.getFirstSelectedOption().getText();
		System.out.println(dapat);
		return dapat;
	}

	public List<WebElement> getListTableColumnName() {
		return tableColumnName;
	}

}
