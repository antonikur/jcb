package com.nexsoft.jcb.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nexsoft.jcb.other.Tools;

public class JCBCompletedPage {

	protected WebDriver driver;
	Tools tool = new Tools();

	@FindBy(xpath = "//input[@type='search']")
	private WebElement inputSearch;

	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement btnSearch;

	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[2]")
	private List<WebElement> tableKolomMerchantName;

	@FindBy(xpath = "//tbody/tr[1]/td[1]/div[1]/a[1]")
	private WebElement btnViewPhoto;

	@FindBy(xpath = "//tbody/tr[1]/td[1]/div[1]/a[2]")
	private WebElement btnViewEDC;
	
	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr[1]/td[1]/div/a[3]")
	private WebElement btnReturn;

	@FindBy(xpath = "//*[@id=\"pop\"]/table/tbody/tr[1]/td[2]/img")
	private WebElement textErrorViewPhoto;

	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnCloseViewPhoto;

	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement textEDC;

	@FindBy(xpath = "//textarea[@name='notes']")
	private WebElement fieldReturnNotes;

	@FindBy(xpath = "//button[@id='btnSave2']")
	private WebElement btnSubmitReturnNotes;

	@FindBy(xpath = "//strong[normalize-space()='Success!']")
	private WebElement textSuccessReturn;

	public JCBCompletedPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<List<WebElement>> getTableCompleted() {
		List<List<WebElement>> tableCompleted = new ArrayList<>();
		tableCompleted.add(tableKolomMerchantName);
		return tableCompleted;
	}

	public List<WebElement> getColumnMerchantName() {
		return tableKolomMerchantName;
	}

	public JCBCompletedPage input_Search(String search) {
		inputSearch.sendKeys(search);
		tool.stopForMoment(2000);
		btnSearch.click();
		tool.stopForMoment(2000);

		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public JCBCompletedPage click_View_Photo() {

		btnViewPhoto.click();
		tool.stopForMoment();
		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public JCBCompletedPage click_Close_View_Photo() {

		btnCloseViewPhoto.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public JCBCompletedPage click_View_EDC() {

		btnViewEDC.click();
		tool.stopForMoment();
		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public JCBCompletedPage click_Close_View_EDC() {

		btnCloseViewPhoto.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public WebElement getTextErrorViewPhoto() {
		return textErrorViewPhoto;
	}

	public WebElement getTextViewEDC() {
		return textEDC;
	}

	public JCBCompletedPage click_return() {

		btnReturn.click();
		tool.stopForMoment(2000);

		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public JCBCompletedPage input_notes_return(String notes) {
		fieldReturnNotes.sendKeys(notes);
		btnSubmitReturnNotes.click();
		tool.stopForMoment();
		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}

	public WebElement getTextSuccessReturn() {
		return textSuccessReturn;
	}

}
