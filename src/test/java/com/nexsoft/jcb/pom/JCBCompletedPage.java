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

//	@FindBy(xpath = "//*[@id=\\\"data-worklist\\\"]/tbody/tr/td[2]/b[1]")
//	private List<WebElement> tableKolomMerchantName;
	
	@FindBy(xpath = "//*[@id=\"data-worklist\"]/tbody/tr/td[2]")
	private List<WebElement> tableKolomMerchantName;
	
	
	public JCBCompletedPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<List<WebElement>> getTableCompleted(){
		List<List<WebElement>> tableCompleted = new ArrayList<>();
		tableCompleted.add(tableKolomMerchantName);
		return tableCompleted;
	}
	
	public List<WebElement> getColumnMerchantName(){
		return tableKolomMerchantName;
	}
	
	public JCBCompletedPage input_Search(String search) {
		inputSearch.sendKeys(search);
		tool.stopForMoment(2000);
		btnSearch.click();
		tool.stopForMoment(2000);

		return PageFactory.initElements(driver, JCBCompletedPage.class);
	}
}

