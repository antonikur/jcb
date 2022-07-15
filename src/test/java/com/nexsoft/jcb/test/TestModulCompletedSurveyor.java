package com.nexsoft.jcb.test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBCompletedPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulCompletedSurveyor {
	protected WebDriver driver;
	protected Tools tools = new Tools();
	protected JCBHomePage homePage;
	


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
							actualTableValidasi.get(2).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) ) {
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
	
	
	@AfterClass
	public void closeDriver() {
		tools.stopForMoment(2000);
		driver.close();
	}
	

	@Test(priority =1)
	public void input_kolom_search_by_merchant_name(){
		
		String keyword = "KFC";
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =2)
	public void input_kolom_search_by_category(){
		
		String keyword = "F&B";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =3)
	public void input_kolom_search_by_address(){
		
		String keyword = "Test Address";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =4)
	public void input_kolom_search_by_PIC_Name(){
		
		String keyword = "USER";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =5)
	public void input_kolom_search_by_PIC_Position(){
		
		String keyword = "Kasir";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =6)
	public void input_kolom_search_by_PIC_Phone_Number(){
		
		String keyword = "8888888888";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =7)
	public void input_kolom_search_by_Merchant_Acc_JCB(){
		
		String keyword = "0";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableCompleted = homePage.clickAndGoToCompleted()
		.input_Search(keyword)
		.getTableCompleted();
		
		boolean isCorrect = checkSearch(keyword, actualTableCompleted);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority =8)
	public void click_View_Photo(){
		JCBCompletedPage completePage = homePage.clickAndGoToCompleted();
		boolean errorMessageElement =  completePage
				.click_View_Photo()
				.getTextErrorViewPhoto().isDisplayed();
				
		completePage.click_Close_View_Photo();
				
		System.out.println(errorMessageElement);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));
		assertTrue(errorMessageElement,"Terjadi error display photo");
	}
	
	@Test(priority =9)
	public void click_View_EDC(){
		JCBCompletedPage completePage = homePage.clickAndGoToCompleted();
		boolean viewEDCText =  completePage
				.click_View_EDC()
				.getTextViewEDC().isDisplayed();
				
		completePage.click_Close_View_EDC();
				
		System.out.println(viewEDCText);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));
		assertTrue(viewEDCText);
	}
	
	@Test(priority =10)
	public void click_Return_and_input_notes_return(){
		
		String note = "Test 123";
		JCBCompletedPage completePage = homePage.clickAndGoToCompleted();
		boolean viewEDCText =  completePage
				.click_return()
				.input_notes_return(note)
				.getTextSuccessReturn().isDisplayed();
				
		System.out.println(viewEDCText);
		tools.scrollByFindElement(driver, driver.findElement(By.xpath("//span[normalize-space()='Logout']")));
		assertTrue(viewEDCText);
	}
	
	
	 
	
	
}
