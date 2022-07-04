package com.nexsoft.jcb.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulLogin {
	protected WebDriver driver;
	protected Tools tool;
	
	
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
	
	@Test(priority = 0)
	public void input_username_tanpa_password_dan_tekan_login(){
		
	}
	
	@Test(priority = 1)
	public void input_password_tanpa_username_dan_tekan_login(){

	}
	
	@Test(priority = 2)
	public void input_username_invalid_dan_password_valid_dan_tekan_login(){

	}
	
	@Test(priority = 3)
	public void input_username_valid_dan_password_invalid_dan_tekan_login(){

	}
	
	@Test(priority = 4)
	public void input_username_panjang_dan_tekan_login(){

	}
	
	@Test(priority = 5)
	public void input_password_panjang_dan_tekan_login(){

	}
	
	@Test(priority = 6)
	public void input_username_valid_dengan_huruf_besar_kecil_dan_tekan_login(){

	}
	
	@Test(priority = 7)
	public void input_password_valid_dengan_huruf_besar_kecil_dan_tekan_login(){

	}
	
	@Test(priority = 8)
	public void username_dan_password_kosong(){

	}
	
	@Test(priority = 9)
	public void input_username_dan_password_valid_sebagai_admin(){
		PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername(null)
		.inputFieldPassword(null)
		.clickBtnLogin()
		.gotoHomePage()
		;
	}
	
	@Test(priority = 10)
	public void input_username_dan_password_valid_sebagai_surveyor(){

	}
	
	@Test(priority = 11)
	public void tekan_tombol_logout_setelah_berhasil_login(){

	}


}
