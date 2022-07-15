package com.nexsoft.jcb.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class JCBNewDataVisitPageSurveyor {
	protected WebDriver driver;

	@FindBy(xpath = "//strong[normalize-space()='Success!']")
	private WebElement notifEditSuccess;

	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr/td[2]/b")
	private WebElement textArea;

	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr[6]/td[2]/b")
	private WebElement textPICName;

	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr[7]/td[2]/b")
	private WebElement textNoTelpPIC;

	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr[8]/td[2]/b")
	private WebElement textPICPos;

	@FindBy(xpath = "//div[@id='content']/div/div[2]/form/div/div[2]/div/table/tbody/tr[9]/td[2]/b")
	private WebElement textMerchAcc;

	@FindBy(xpath = "//span[normalize-space()='PIC is required']")
	private WebElement textPICReq;

	@FindBy(xpath = "//span[normalize-space()='No Telp PIC is required']")
	private WebElement textNoTelpPICReq;

	@FindBy(xpath = "//span[contains(text(),'Upload error: The file you are attempting to uploa')]")
	private WebElement textUploadErrorTIDFoto;

	@FindBy(xpath = "//form[@id='form_edc']/div[2]/div[7]/div/span")
	private WebElement textUploadErrorTID;

	@FindBy(xpath = "//strong[normalize-space()='Success!']")
	private WebElement textUploadSuccessAddTID;

	@FindBy(xpath = "//i[@class='fa fa-edit']")
	private WebElement editDataMerchBtn;

	@FindBy(xpath = "//input[@name='pic']")
	private WebElement PICDataMerchField;

	@FindBy(xpath = "//input[@name='no_telp_pic']")
	private WebElement TelpPICDataMerchField;

	@FindBy(xpath = "//select[@name='pic_position']")
	private WebElement selectPICDataMerch;

	@FindBy(xpath = "//select[@name='merchant_accept_jcb_card']")
	private WebElement selectMerchantAccDataMerch;

	@FindBy(xpath = "//button[@id='btnSave2']")
	private WebElement saveEditDataMerchBtn;

	@FindBy(xpath = "//div[@id='modal_form_merchant']//span[@aria-hidden='true'][normalize-space()='�']")
	private WebElement cancelEditDataMerchBtn;

	@FindBy(xpath = "//a[@class='btn btn-info btn-xs']")
	private WebElement addTIDBtn;

	@FindBy(xpath = "//select[@name='bank_name']")
	private WebElement fieldBank;

	@FindBy(xpath = "//input[@name='mid']")
	private WebElement fieldMID;

	@FindBy(xpath = "//input[@name='tid']")
	private WebElement fieldTID;

	@FindBy(xpath = "//select[@name='jcb_acceptance']")
	private WebElement fieldJCBAcc;

	@FindBy(xpath = "//select[@name='edc_issue']")
	private WebElement fieldEDCIssue;

	@FindBy(xpath = "//input[@id='file']")
	private WebElement chooseFileBtn;

	@FindBy(xpath = "//button[@id='btnSave2']")
	private WebElement saveEditBtn;

	@FindBy(xpath = "//button[@id='btnSave']")
	private WebElement saveAddTIDBtn;

	@FindBy(xpath = "//div[@id='modal_form']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement cancelTIDBtn;

	@FindBy(xpath = "//select[@name='coll_sticker']")
	private WebElement checkBoxStickerColl;

	@FindBy(xpath = "//select[@name='coll_stand_logo']")
	private WebElement checkBoxStandLogoColl;

	@FindBy(xpath = "//select[@name='coll_open_close']")
	private WebElement checkBoxOpenCloseColl;

	@FindBy(xpath = "//select[@name='coll_card_holder']")
	private WebElement checkBoxCardHolderColl;

	@FindBy(xpath = "//select[@name='op_sticker']")
	private WebElement checkBoxStickerVM;

	@FindBy(xpath = "//select[@name='op_stand_logo']")
	private WebElement checkBoxStandLogoVM;

	@FindBy(xpath = "//select[@name='op_open_close']")
	private WebElement checkBoxOpenCloseVM;

	@FindBy(xpath = "//label[@for='cssCheckbox1']")
	private WebElement billHolderBTN;

	@FindBy(xpath = "//label[@for='cssCheckbox2']")
	private WebElement stickerSheetBtn;

	@FindBy(xpath = "//label[@for='cssCheckbox3']")
	private WebElement penBtn;

	@FindBy(xpath = "//label[@for='cssCheckbox4']")
	private WebElement reserverdSignBtn;

	@FindBy(xpath = "//input[@name='simpan']")
	private WebElement submitBtn;

	@FindBy(xpath = "//div[@id='header']/ul/li/a/b")
	private WebElement profile;

	@FindBy(xpath = "//div[@id='header']/ul/li/div/a")
	private WebElement btnLogout;

	public JCBNewDataVisitPageSurveyor(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getTextNotifAddDataSuccess() {
		return textUploadSuccessAddTID;
	}
	public WebElement getTextNotifAddTIDSuccess() {
		return textUploadSuccessAddTID;
	}

	public WebElement getTextNotifEditSuccess() {
		return notifEditSuccess;
	}

	public WebElement getTextErrorUploadTIDFoto() {
		return textUploadErrorTIDFoto;
	}

	public WebElement getTextErrorUploadTID() {
		return textUploadErrorTID;
	}

	public String getTextArea() {
		return textArea.getText();
	}

	public String getTextPIC() {
		return textPICName.getText();
	}

	public String getTextNoTelpPIC() {
		return textNoTelpPIC.getText();
	}

	public String getTextPICPos() {
		return textPICPos.getText();
	}

	public String getTextMerchAcc() {
		return textMerchAcc.getText();
	}

	public WebElement getPICReq() {
		return textPICReq;
	}

	public WebElement getNoTelpPICReq() {
		return textNoTelpPICReq;
	}

	public JCBNewDataVisitPageSurveyor clickBtnSaveDataMerchant() {
		saveEditBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clickBtnEditDataMerchant() {
		editDataMerchBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clickBtnCancelEditDataMerchant() {
		cancelEditDataMerchBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clickPICDataMerchant(String pic) {
		PICDataMerchField.clear();
		PICDataMerchField.sendKeys(pic);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clicknoTelpPICDataMerchant(String telp) {
		TelpPICDataMerchField.clear();
		TelpPICDataMerchField.sendKeys(telp);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clickSearchField(String pic) {
		PICDataMerchField.sendKeys(pic);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor LogOut() {
		profile.click();
		btnLogout.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBLoginPage gotoLoginPage() {
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}

	public JCBNewDataVisitPageSurveyor selectDropdownListPICEntriesByValue(String value) {
		Select select = new Select(selectPICDataMerch);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor selectDropdownListMerchEntriesByValue(String value) {
		Select select = new Select(selectMerchantAccDataMerch);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor clickBtnAddTID() {
		addTIDBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor selectDropdownListBankEntriesByValue(String value) {
		Select select = new Select(fieldBank);
		select.selectByVisibleText(value);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor inputMID(String MID) {
		fieldMID.sendKeys(MID);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor inputTID(String TID) {
		fieldTID.sendKeys(TID);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor selectDropdownListJCBAccEntriesByValue(String value) {
		Select select = new Select(fieldJCBAcc);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor selectDropdownListEDCIssueEntriesByValue(String value) {
		Select select = new Select(fieldEDCIssue);
		select.selectByValue(value);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor clickBtnChooseFile(String filePath) {
		chooseFileBtn.sendKeys(filePath);
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);
	}

	public JCBNewDataVisitPageSurveyor clickBtnSaveAddTID() {
		saveAddTIDBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public JCBNewDataVisitPageSurveyor clickBtnCancelAddTID() {
		cancelTIDBtn.click();
		return PageFactory.initElements(driver, JCBNewDataVisitPageSurveyor.class);

	}

	public String selectDropdownListCollStickerEntriesByValue(String value) {
		Select select = new Select(checkBoxStickerColl);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();

	}

	public String selectDropdownListCollStandLogoEntriesByValue(String value) {
		Select select = new Select(checkBoxStandLogoColl);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();
	}

	public String selectDropdownListCollOpenCloseEntriesByValue(String value) {
		Select select = new Select(checkBoxOpenCloseColl);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();
	}

	public String selectDropdownListCollNameCardHolderEntriesByValue(String value) {
		Select select = new Select(checkBoxCardHolderColl);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();
	}

	public String checkBoxBillHolderGimmick() {
		if (driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).getAttribute("checked") == null) {
			billHolderBTN.click();
		}

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).getAttribute("checked"));

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).getAttribute("checked"));
		return driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).getAttribute("checked");

	}

	public String checkBoxStickerSheet() {

		if (driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).getAttribute("checked") == null) {
			stickerSheetBtn.click();
		}

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).getAttribute("checked"));

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).getAttribute("checked"));
		return driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).getAttribute("checked");

	}

	public String checkBoxPen() {

		if (driver.findElement(By.xpath("//*[@id=\"cssCheckbox3\"]")).getAttribute("checked") == null) {
			penBtn.click();
		}

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox3\"]")).getAttribute("checked"));

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox3\"]")).getAttribute("checked"));
		return driver.findElement(By.xpath("//*[@id=\"cssCheckbox3\"]")).getAttribute("checked");

	}

	public String checkBoxReservedSign() {
		
		
		
		
		if (driver.findElement(By.xpath("//*[@id=\"cssCheckbox4\"]")).getAttribute("checked") == null) {
			reserverdSignBtn.click();
		}

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox4\"]")).getAttribute("checked"));

		System.out.println(
				"att check: " + driver.findElement(By.xpath("//*[@id=\"cssCheckbox4\"]")).getAttribute("checked"));
		return driver.findElement(By.xpath("//*[@id=\"cssCheckbox4\"]")).getAttribute("checked");


	}
	
	public String selectDropdownListVMStickerEntriesByValue(String value) {
		Select select = new Select(checkBoxStickerVM);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();

	}

	public String selectDropdownListVMStandLogoEntriesByValue(String value) {
		Select select = new Select(checkBoxStandLogoVM);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();
	}

	public String selectDropdownListVMOpenCloseEntriesByValue(String value) {
		Select select = new Select(checkBoxOpenCloseVM);
		select.selectByValue(value);
		return select.getFirstSelectedOption().getText();
	}
	
	public JCBVisitMerchantUploadFoto clickBtnSubmit() {
		submitBtn.click();
		return PageFactory.initElements(driver, JCBVisitMerchantUploadFoto.class);
	}

}
