package com.nexsoft.jcb.test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBValidasiDetailVisit;

public class TestModulCompleted {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	protected JCBHomePage homePage;
	protected JCBValidasiDetailVisit DVisit;


	@BeforeClass
	public void initial() {
		System.setProperty("url", "https://dev.ptdika.com/jcb/new/login");
		System.setProperty("webdriver.chrome.driver", "D:\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void reset() {
		driver.get(System.getProperty("url"));
		
		homePage = PageFactory.initElements(PageFactory.initElements(driver, JCBLoginPage.class)
				.inputFieldUsername("admindika2")
				.inputFieldPassword("d1k4@passw0rd")
				.clickBtnLogin()
				.gotoHomePage()
				.getDriver(), JCBHomePage.class);
	}
	
	@AfterMethod
	public void logout() {
		driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();
	}
	

	public boolean checkSearch (String keyword, List<List<WebElement>> actualTableValidasi) {
		boolean isCorrect = false;
		
		if(actualTableValidasi.get(0).size() <= 0 || 
			actualTableValidasi.get(0).get(0).getText().contains("No matching records") ) {
			isCorrect = true;
			return isCorrect;
		}
		
		for(int i=0; i<actualTableValidasi.get(0).size(); i++) {//check data per row if it contains keyword
			//assert
			//if no matching keyword found
			if(actualTableValidasi.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
				fail("No data found, make sure you use keyword that available");
			}
			//if one of the column have contain data from keyword
			else {
				String[] splitKeyword = keyword.trim().split(" ");
				for (String element : splitKeyword) {
					//if it contain in no
					if(actualTableValidasi.get(0).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
							// if it contain in merchant name
							actualTableValidasi.get(1).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) ) {
						isCorrect = true;
						
				} else {
					//if one of the row doesn't contain keyword then fail
					isCorrect = false;
					break;
				}
			}
		}
	}
		return isCorrect;
	}
	
	@Test(priority =1)
	public void input_kolom_search_by_merchant_name(){
		
		String keyword = "KFC";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =1)
	public void input_kolom_search_by_kategori(){
		
		String keyword = "F&B";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =1)
	public void input_kolom_search_by_address(){
		
		String keyword = "Asia Afrika";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
}
