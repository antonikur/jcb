package com.nexsoft.jcb.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.nexsoft.jcb.other.Tools;

import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;

public class JCBValidasiDetailVisit {

	protected WebDriver driver;
	Tools tool = new Tools();
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleDetailVisit;
	
	@FindBy(xpath = "//span[normalize-space()='Notes is required']")
	private WebElement mesageErrNotesBlankReturn;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	@FindBy(xpath = "//textarea[@name='notes']")
	private WebElement notesReturn;
	
	@FindBy(xpath = "//textarea[@name='notes']")
	private WebElement notesReject;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement menuValidate;
	
	@FindBy(xpath = "//button[@class='btn btn-warning']")
	private WebElement menuReturn;
	
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	private WebElement menuReject;

	@FindBy(xpath = "//button[@id='btnSave1']")
	private WebElement submitValidateForm;
	
	@FindBy(xpath = "//button[@id='btnSave2']")
	private WebElement submitReturnForm;
	
	@FindBy(xpath = "//button[@id='btnSave2']")
	private WebElement submitRejectForm;
	
	@FindBy(xpath = "//div[@id='modal_form1']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement cancelValidateForm;
	
//	@FindBy(xpath = "//*[@id=\"modal_form1\"]/div/div/div[3]/button[2]")
//	private WebElement cancelValidateForm;
	
	@FindBy(xpath = "//div[@id='modal_form2']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement cancelReturnForm;
	
	@FindBy(xpath = "//div[@id='modal_form2']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement cancelRejectForm;
	
	@FindBy(xpath = "//div[@id='modal_form1']//button[@aria-label='Close']")
	private WebElement btnXValidateForm;
	
	@FindBy(xpath = "//*[@id=\"modal_form2\"]/div/div/div[1]/button/span")
	private WebElement btnXReturnForm;
	
	@FindBy(xpath = "//*[@id=\"modal_form2\"]/div/div/div[1]/button/span")
	private WebElement btnXRejectForm;
	
	@FindBy(xpath = "//*[@id=\"modal_form1\"]/div/div/div[1]/h4")
	private WebElement textValidateForm;
	
	@FindBy(xpath = "//*[@id=\"modal_form2\"]/div/div/div[1]/h4")
	private WebElement textReturnForm;
	
	@FindBy(xpath = "//*[@id=\"modal_form2\"]/div/div/div[1]/h4")
	private WebElement textRejectForm;
	
	@FindBy(xpath = "//*[@id=\"myModalLabel\"]")
	private WebElement popUpTitle;
	
	@FindBy(xpath = "//*[@id=\"modalFoto\"]/div/div/div[1]/button")
	private WebElement btnCancelPopup;
	
	public JCBValidasiDetailVisit (WebDriver driver) {
		this.driver = driver;
	}
	
	public JCBLoginPage clickBtnCancelPopup() {
		btnCancelPopup.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public String getTitleDetailVisit() {
		return titleDetailVisit.getText();
	}
	
	public String getPopUpTitle() {
		return popUpTitle.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBValidasiDetailVisit clickValidate() {
		tool.scroolVerticalWindows(driver, 100);
		menuValidate.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickSubmitVal() {
		submitValidateForm.click();
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickCancelVal() {
		cancelValidateForm.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickBtnXVal() {
		btnXValidateForm.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit input_NotesReturn(String notes) {
		notesReturn.sendKeys(notes);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickReturn() {
		tool.scroolVerticalWindows(driver, 100);
		menuReturn.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit cancelReturn() {
		cancelReturnForm.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit submitReturn() {
		submitReturnForm.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickReject() {
		tool.scroolVerticalWindows(driver, 100);
		menuReject.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit cancelReject() {
		cancelRejectForm.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit submitReject() {
		submitRejectForm.click();
		tool.stopForMoment(1000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public boolean checkMesageErrNotesBlankReturnDisplay() {
		tool.stopForMoment(2000);
		return mesageErrNotesBlankReturn.isDisplayed();
	}
	
	public String getValidateForm() {
		return textValidateForm.getText();
	}
	
	public String getReturnForm() {
		return textReturnForm.getText();
	}
	
	public String getRejectForm() {
		return textRejectForm.getText();
	}
	
	public JCBValidasiDetailVisit clickbtnXReturn() {
		btnXReturnForm.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit clickbtnXReject() {
		btnXRejectForm.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
	public JCBValidasiDetailVisit input_notes(String notes) {
		notesReturn.sendKeys(notes);
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
	
//	public JCBBucketDistribusiPage clickPopUpFoto(String number) {	
//		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div[2]/div/table/tbody/tr[3]/td['"+number+"'] ")).click();
//		tool.stopForMoment(2000);
//		return PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
//	}
	
	public JCBValidasiDetailVisit clickPopUpFoto(String number) {	
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div[2]/div/table/tbody/tr[3]/td["+number+"]/a/img")).click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
	}
}
