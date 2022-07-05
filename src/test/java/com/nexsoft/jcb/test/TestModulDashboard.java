package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBDashboardPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBMonitoringPage;

public class TestModulDashboard {
	protected WebDriver driver;
//	protected WebDriverWait wait;
	protected Tools tool;
	protected JCBHomePage homePage;
	
	
	@BeforeClass
	public void initial() {
		System.setProperty("url", "https://dev.ptdika.com/jcb/new/login");
		System.setProperty("webdriver.chrome.driver", "C:\\Antoni\\chromedriver.exe");
		driver = new ChromeDriver();
//		wait = new WebDriverWait(driver, 20);
		driver.get(System.getProperty("url"));
		//wait.until(ExpectedConditions.presenceOfElementLocated(addItem));
		
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void reset() {
		driver.get(System.getProperty("url"));
		
	}
	
	@Test(priority = 0)
	public void tekan_menu_dashboard(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername("antoni")
		.inputFieldPassword("antoni")
		.clickBtnLogin()
		.gotoHomePage()
		.clickAndGotoMenuDashboard()
		.getTitle().trim();
		
		//logout
		PageFactory.initElements(driver, JCBDashboardPage.class)
		.clickLogoutAndGotoLogin();
		
		assertEquals("Dashboard", actual);
	}
	
	@Test(priority = 1)
	public void tekan_tombol_expand_or_compress(){

	}
	
	@Test(priority = 2)
	public void tekan_tombol_reload(){

	}
	
	@Test(priority = 3)
	public void tekan_tombol_collapse_or_expand(){

	}
	
	@Test(priority = 4)
	public void tekan_tombol_detail(){
		String actual = PageFactory.initElements(driver, JCBLoginPage.class)
		.inputFieldUsername("antoni")
		.inputFieldPassword("antoni")
		.clickBtnLogin()
		.gotoHomePage()
		.clickAndGotoMenuDashboard()
		.clickDetailAndGotoMonitoring()
		.getTitleMonitoring().trim();
		
		//logout
		PageFactory.initElements(driver, JCBMonitoringPage.class)
		.clickLogoutAndGotoLogin();
		
		
		assertEquals(actual, "Monitoring");
	}
	
	@Test(priority = 5)
	public void cek_seluruh_jumlah_total_price_sudah_benar_atau_belum(){
		
	}
	
	@Test(priority = 6)
	public void cek_seluruh_jumlah_monthly_price_sudah_benar_atau_belum(){
		
	}
	
	@Test(priority = 7)
	public void cek_seluruh_jumlah_total_unvisit_sudah_benar_atau_belum(){

	}
	
	@Test(priority = 8)
	public void cek_seluruh_jumlah_total_visit_sudah_benar_atau_belum(){

	}
	

	

}
