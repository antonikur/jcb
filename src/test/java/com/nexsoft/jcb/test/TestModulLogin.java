package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulLogin {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	
	protected String adminUsername = "admindika3";
	protected String adminUsernameCaps = "aDmInDIka3";
	
	@BeforeClass
	public void initial() {
		System.setProperty("url", "https://dev.ptdika.com/jcb/new/login");
		System.setProperty("webdriver.chrome.driver", "C:\\Antoni\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(System.getProperty("url"));
		
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void reset() {
		driver.get(System.getProperty("url"));
	}
	
	@AfterClass
	public void driverClose() {
		driver.close();
	}
	
	//#############################################################################################
	//#############################################################################################
	public boolean isAttrRequiredPresent(WebElement element) {
		boolean check = false;
		try {
			String value = element.getAttribute("required");
			if (value != null) {
				check = true;
			}
			return check;
		} catch (Exception e) {
			// TODO: handle exception
			return check = false;
		}
	}
	//#############################################################################################
	//#############################################################################################
	
	@Test(priority = 0)
	public void input_username_tanpa_password_dan_tekan_login(){
		WebElement element = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(adminUsername)
		.clickBtnLogin().getElementFieldPassword();
		
		boolean isRequired = isAttrRequiredPresent(element);
		assertTrue(isRequired);
	}
	
	@Test(priority = 1)
	public void input_password_tanpa_username_dan_tekan_login(){
		WebElement element = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldPassword("d1k4@passw0rd")
		.clickBtnLogin().getElementFieldUsername();
		
		boolean isRequired = isAttrRequiredPresent(element);
		assertTrue(isRequired);
	}
	
	@Test(priority = 2)
	public void input_username_invalid_dan_password_valid_dan_tekan_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername("!#@ $%!#")
		.inputFieldPassword("d1k4@passw0rd")
		.clickBtnLogin()
		.getErrMsgInvalidUsernamePassword().trim();
		
		assertEquals(actual, "User / Password tidak sesuai.");
	}
	
	@Test(priority = 3)
	public void input_username_valid_dan_password_invalid_dan_tekan_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(adminUsername)
		.inputFieldPassword("dummyPass")
		.clickBtnLogin()
		.getErrMsgInvalidUsernamePassword().trim();
		
		assertEquals(actual, "User / Password tidak sesuai.");
	}
	
	@Test(priority = 4)
	public void input_username_panjang_dan_tekan_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
		.inputFieldPassword("dummyPass")
		.clickBtnLogin()
		.getErrMsgInvalidUsernamePassword().trim();
		
		assertEquals(actual, "User / Password tidak sesuai.");
	}
	
	@Test(priority = 5)
	public void input_password_panjang_dan_tekan_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(adminUsername)
		.inputFieldPassword("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
		.clickBtnLogin()
		.getErrMsgInvalidUsernamePassword().trim();
		
		assertEquals(actual, "User / Password tidak sesuai.");
	}
	
	@Test(priority = 6)
	public void input_username_valid_dengan_huruf_besar_kecil_dan_tekan_login(){
		try {
			String actual = PageFactory.initElements(driver, JCBLoginPage.class)
			.inputFieldUsername(adminUsernameCaps)
			.inputFieldPassword("d1k4@passw0rd")
			.clickBtnLogin()
			.getErrMsgInvalidUsernamePassword().trim();
			
			assertEquals(actual, "User / Password tidak sesuai.");
		} catch (Exception e) {
			//if not get error message then its mean login is success
			//therefore the test is fail
			tool.stopForMoment(2000);
			//logout
			driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();
			
			//assert
			fail("Login is success, it was suppose to be fail since the username is invalid");
		}
	}
	
	@Test(priority = 7)
	public void input_password_valid_dengan_huruf_besar_kecil_dan_tekan_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(adminUsername)
		.inputFieldPassword("d1k4@PasSw0Rd")
		.clickBtnLogin()
		.getErrMsgInvalidUsernamePassword().trim();
		
		assertEquals(actual, "User / Password tidak sesuai.");
	}
	
	@Test(priority = 8)
	public void username_dan_password_kosong(){
		JCBLoginPage loginPage = PageFactory.initElements(driver, JCBLoginPage.class)
		.clickBtnLogin();
		
		boolean usernameIsRequired = isAttrRequiredPresent(loginPage.getElementFieldUsername());
		boolean passwordIsRequired = isAttrRequiredPresent(loginPage.getElementFieldPassword());
		assertTrue(usernameIsRequired && passwordIsRequired);
	}
	
	@Test(priority = 9)
	public void input_username_dan_password_valid_sebagai_admin(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(adminUsername)
		.inputFieldPassword("d1k4@passw0rd")
		.clickBtnLogin()
		.gotoHomePage()
		.getTxtInfoUser();
		
		//logout
		PageFactory.initElements(driver, JCBHomePage.class)
		.clickLogoutAndGotoLoginPage();
		
		assertEquals(actual, "Administrator");
	}
	
	@Test(priority = 10)
	public void input_username_dan_password_valid_sebagai_surveyor(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername("antonik")
		.inputFieldPassword("antonik")
		.clickBtnLogin()
		.gotoHomePage()
		.getTxtInfoUser();
		
		//logout
		PageFactory.initElements(driver, JCBHomePage.class)
		.clickLogoutAndGotoLoginPage();
				
		assertEquals(actual, "AntoniK");

	}
	
	@Test(priority = 11)
	public void tekan_tombol_logout_setelah_berhasil_login(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
				.inputFieldUsername(adminUsername)
				.inputFieldPassword("d1k4@passw0rd")
				.clickBtnLogin()
				.gotoHomePage()
				.clickLogoutAndGotoLoginPage()
				.getTitleLogin().trim();
		
		assertEquals(actual, "DIKA | JCB");
	}


}
