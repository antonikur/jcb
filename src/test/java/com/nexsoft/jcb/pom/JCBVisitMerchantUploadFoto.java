package com.nexsoft.jcb.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nexsoft.jcb.other.Tools;

public class JCBVisitMerchantUploadFoto {

	WebDriver driver;
	protected Tools tools = new Tools();

	// tbody/tr[6]/td[3]/a[1]/button[1]
	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement uploadFotoDepanMerchantBtn;

	@FindBy(xpath = "//input[@id='file']")
	private WebElement chooseFileBtn;

	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement uploadFotoSemuaStrukEDCBtn;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement uploadFotoCollJCB1BeforeBtn;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	private WebElement uploadFotoCollJCB1AfterBtn;

	@FindBy(xpath = "(//button[@type='button'])[6]")
	private WebElement uploadFotoCollJCB2BeforeBtn;

	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement uploadFotoCollJCB2AfterBtn;

	@FindBy(xpath = "(//button[@type='button'])[8]")
	private WebElement uploadFotoCollOtherPrincipalBtn;

	@FindBy(xpath = "//button[@id='btnSave']")
	private WebElement saveBtn;

	@FindBy(xpath = "//button[@id='btnFinish']")
	private WebElement finishBtn;

	@FindBy(xpath = "//strong[normalize-space()='Success!']")
	private WebElement textSuccessUploadPhoto;

	@FindBy(xpath = "//p[@id='error']")
	private WebElement textErrorUploadPhoto;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	private WebElement cancelBtn;

	public JCBVisitMerchantUploadFoto(WebDriver driver) {
		this.driver = driver;
	}

	public JCBVisitMerchantUploadFoto clickBtnCancel() {
		cancelBtn.click();
		tools.stopForMoment();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);

	}

	public JCBNewDataPage clickBtnFinish() {
		finishBtn.click();
		tools.stopForMoment();
		return PageFactory.initElements(driver, JCBNewDataPage.class);
	}

	public WebElement getTextSuccessUploadPhoto() {
		return textSuccessUploadPhoto;
	}
	
	public WebElement getTextErrorUploadPhoto() {
		return textErrorUploadPhoto;
	}
	public WebElement getBtnFinish() {
		return finishBtn;
	}

	public JCBVisitMerchantUploadFoto uploadPhotoFotoDepanMerchant(String filepath) {

		uploadFotoDepanMerchantBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadFotoSemuaStrukEDCBtn(String filepath) {

		uploadFotoSemuaStrukEDCBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadPhotoCollJCB1Before(String filepath) {

		uploadFotoCollJCB1BeforeBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadPhotoCollJCB1After(String filepath) {

		uploadFotoCollJCB1AfterBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadPhotoCollJCB2Before(String filepath) {

		uploadFotoCollJCB2BeforeBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadPhotoCollJCB2After(String filepath) {

		uploadFotoCollJCB2AfterBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

	public JCBVisitMerchantUploadFoto uploadFotoCollOtherPrincipalBtn(String filepath) {

		uploadFotoCollOtherPrincipalBtn.click();
		tools.stopForMoment();
		chooseFileBtn.sendKeys(filepath);
		saveBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

}
