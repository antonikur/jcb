package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBDashboardPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulDashboard {
	protected WebDriver driver;
	protected Tools tool = new Tools();
	protected JCBHomePage homePage;
	
	
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
		
		homePage = PageFactory.initElements(PageFactory.initElements(driver, JCBLoginPage.class)
				.inputFieldUsername("antoni")
				.inputFieldPassword("antoni")
				.clickBtnLogin()
				.gotoHomePage()
				.getDriver(), JCBHomePage.class);
	}
	
	@AfterMethod
	public void logout() {
		driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();;
	}
	
	//##################################################################################################################
	//##################################################################################################################
	
	//doesn't calculate last row since its a total
	public int calculateAllInListExceptLast(List<WebElement> list) {
		int total = 0;
		
		for(int i=0; i<list.size()-1; i++) {
			total += Integer.parseInt(list.get(i).getText().replace(",", ""));
		}
		
		return total;
	}
	
	//##################################################################################################################
	//##################################################################################################################
	@Test(priority = 0)
	public void tekan_menu_dashboard(){
		String actual = homePage.clickAndGotoMenuDashboard()
		.getTitle().trim();
		
		
		assertEquals("Dashboard", actual);
	}
	
	@Test(priority = 1)
	public void tekan_tombol_expand_or_compress(){
		JCBDashboardPage dashboardPage = homePage.clickAndGotoMenuDashboard();
		int sizeWidthCompress = dashboardPage.getElementPanelViewSummaryByArea().getSize().getWidth();
		
		dashboardPage.clickBtnExpandCompress();
		
		int sizeWidthExpand = dashboardPage.getElementPanelViewSummaryByArea().getSize().getWidth();
		
		//compress for logout
		dashboardPage.clickBtnExpandCompress();
		
		assertTrue(sizeWidthCompress < sizeWidthExpand);
		
		
	}
	
	@Test(priority = 2)
	public void tekan_tombol_reload(){
		//link add merchant
//		https://dev.ptdika.com/jcb/new/master_merchant
//		https://dev.ptdika.com/jcb/new/master_merchant
		
		//link dash board
//		https://dev.ptdika.com/jcb/new/dashboard
		JCBDashboardPage dashboardPage= homePage.clickAndGotoMenuDashboard();
		
		
		//get first data (plaza indonesia)
		int beforeAdd = Integer.parseInt(dashboardPage.getListColumnJumlahMerchant().get(0).getText());
		System.out.println("before add: "+beforeAdd);
		
		
		//open new tab to data merchant
		String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(By.xpath("//span[normalize-space()='Data Merchant']")).sendKeys(newTab);
		
		//body/div[@id='content']/div[1]
		
		
		
		//goto new tab
		driver.get("https://dev.ptdika.com/jcb/new/master_merchant");
//		driver.manage().window().maximize();
		
		
//		dashboardPage.clickBtnReload();
		
//		int afterAdd = Integer.parseInt(dashboardPage.getListColumnJumlahMerchant().get(0).getText());
		tool.stopForMoment(10000);
		fail("not yet implemented");
	}
	
	@Test(priority = 3)
	public void tekan_tombol_collapse_or_expand(){
		JCBDashboardPage dashboardPage = homePage.clickAndGotoMenuDashboard();
		
		boolean panelIsDisplayed = dashboardPage.getElementPanelTableSummaryByArea().isDisplayed();
		System.out.println("is displayed: "+panelIsDisplayed);
		dashboardPage.clickBtnCollapseExpand();
		
		//if hidden then true
		boolean panelIsHidden = !dashboardPage.getElementPanelTableSummaryByArea().isDisplayed();
		System.out.println("is hidden: "+panelIsHidden);
		
		assertTrue(panelIsDisplayed && panelIsHidden);
		
	}
	
	@Test(priority = 4)
	public void tekan_tombol_detail(){
		String actual = homePage
		.clickAndGotoMenuDashboard()
		.clickDetailAndGotoMonitoring()
		.getTitleMonitoring().trim();
		
		assertEquals(actual, "Monitoring");
	}
	
	@Test(priority = 5)
	public void cek_seluruh_jumlah_total_price_sudah_benar_atau_belum(){
		JCBDashboardPage dashBoardPage = homePage.clickAndGotoMenuDashboard();
		int totalCalcuatePrice = calculateAllInListExceptLast(dashBoardPage.getListColumnJumlahTotalPrice());
		int totalPrice = Integer.parseInt(dashBoardPage.getTextTotalPrice().replace(",", ""));
		
		assertEquals(totalCalcuatePrice, totalPrice);
	}
	
	@Test(priority = 6)
	public void cek_seluruh_jumlah_monthly_price_sudah_benar_atau_belum(){
		JCBDashboardPage dashBoardPage = homePage.clickAndGotoMenuDashboard();
		int totalCalcuatePrice = calculateAllInListExceptLast(dashBoardPage.getListColumnJumlahTotalPrice());
		int monthlyPrice = Integer.parseInt(dashBoardPage.getTextTotalPrice().replace(",", ""));
		
		assertEquals(totalCalcuatePrice, monthlyPrice);
	}
	
	@Test(priority = 7)
	public void cek_seluruh_jumlah_total_unvisit_sudah_benar_atau_belum(){
		JCBDashboardPage dashBoardPage = homePage.clickAndGotoMenuDashboard();
		int totalCalcuateUnvisit = calculateAllInListExceptLast(dashBoardPage.getListColumnJumlahUnvisit());
		int unvisit = Integer.parseInt(dashBoardPage.getTextTotalUnvisit().replace(",", ""));
		
		assertEquals(totalCalcuateUnvisit, unvisit);
	}
	
	@Test(priority = 8)
	public void cek_seluruh_jumlah_total_visit_sudah_benar_atau_belum(){
		JCBDashboardPage dashBoardPage = homePage.clickAndGotoMenuDashboard();
		int totalCalcuateVisit = calculateAllInListExceptLast(dashBoardPage.getListColumnJumlahVisit());
		int monthlyVisit = Integer.parseInt(dashBoardPage.getTextTotalMonthlyVisit().replace(",", ""));
		
		assertEquals(totalCalcuateVisit, monthlyVisit);
	}
	

	

}
