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

public class JCBBucketDistribusiDetail {

	protected WebDriver driver;
	Tools tool = new Tools();
	
	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnProcess;

	@FindBy(xpath = "//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]/div/table[2]/tbody/tr/td[1]")
	private List<WebElement> listKolomNo;
	
	@FindBy(xpath = "//*[@id=\"distribusi\"]/tbody/tr/td[3]/b")
	private WebElement titleDistribusiData;
	
	@FindBy(xpath = "//h1[@class='page-header']")
	private WebElement titleBucketDetailDistribusi;
	
	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement menuLogout;
	
	@FindBy(xpath = "//a[@id='mybutton']")
	private WebElement pilihDataDetail;
	
	@FindBy(xpath = "//div[@id='checkAll']/label")
	private WebElement checkboxAll;
	
	//*[@id="cssCheckbox1"]
	@FindBy(xpath = "//div[@id='divcheck_1']/div/label")
	private WebElement checkbox1;
	
	@FindBy(xpath = "//a[@id='buttonUser']")
	private WebElement pilihUser;
	
	@FindBy(xpath = "//h4[@id='myModalLabel']")
	private WebElement titlePilihData;
	
	@FindBy(xpath = "//*[@id=\"content\"]/h1")
	private WebElement titleDetailPilihData;

	@FindBy(xpath = "//*[@id=\"myModalLabel\"]")
	private WebElement titlePilihUser;
	
	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[1]/button")
	private WebElement btnXuser;
	
//	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[1]/button")
//	private WebElement btnX_datausers;
	
	@FindBy(xpath = "//*[@id=\"modalUser\"]/div/div/div[1]")
	private WebElement titleDataUser;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div")
	private WebElement titleSuccess;
	
	public JCBBucketDistribusiDetail (WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitleDetailDistribusi() {
		return titleBucketDetailDistribusi.getText();
	}
	
	public String getTitleDistribusiData() {
		return titleDistribusiData.getText();
	}
	
	public JCBLoginPage clickLogoutAndGotoLogin() {
		menuLogout.click();
		return PageFactory.initElements(driver, JCBLoginPage.class);
	}
	
	public JCBBucketDistribusiDetail clickPilihDataDetail() {
		pilihDataDetail.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiDetail clickCheckboxAll() {
		checkboxAll.click();
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiDetail clickCheckbox1() {
		checkbox1.click();
		tool.stopForMoment(2000);
		
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiDetail clickPilihUser() {
		pilihUser.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiDetail btnX_pilihUser() {
		btnXuser.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
//	public JCBBucketDistribusiDetail btnX_dataUsers() {
//		btnX_datausers.click();
//		tool.stopForMoment(2000);
//		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
//	}
	
	public String getTitlePilihData() {
		return titlePilihData.getText();
	}
	
	public String getTitleDetailPilihData() {
		return titleDetailPilihData.getText();
	}
	
	public String getTitlePilihUser() {
		return titlePilihUser.getText();
	}
	
	public JCBBucketDistribusiDetail checkboxMerchant(String no) {	
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/form/div/div[2]/div/table[2]/tbody/tr["+no+"]/td[8]")).click();
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public String getTitleDataUser() {
		tool.stopForMoment(2000);
		return titleDataUser.getText();
	}
	
	public JCBBucketDistribusiDetail pilihSurveyor(String no) {	
		driver.findElement(By.xpath("//*[@id=\"modalUser\"]/div/div/div[2]/div/table/tbody/tr["+no+"]/td[5]/a ")).click();
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public JCBBucketDistribusiDetail clickBtnProcess() {
		btnProcess.click();
		tool.stopForMoment(2000);
		return PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);
	}
	
	public String getTitleSuccess() {
		return titleSuccess.getText();
	}
	
	public List<WebElement> getListKolomNo(){
		return listKolomNo;
	}
}
