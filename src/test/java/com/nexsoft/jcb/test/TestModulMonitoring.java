package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
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
import com.nexsoft.jcb.pom.JCBBucketDistribusiAssignment;
import com.nexsoft.jcb.pom.JCBBucketDistribusiPage;
import com.nexsoft.jcb.pom.JCBBucketValidasiPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBMonitoringPage;

public class TestModulMonitoring {
	protected WebDriver driver;
	protected Tools tool = new Tools();
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
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
	
	public boolean checkSearch(String keyword, List<List<WebElement>> actualMonitoring) {
		boolean isCorrect = false;
		
		if(actualMonitoring.get(0).size() <= 0) {
			isCorrect = true;
		}
		
		for(int i=0; i<actualMonitoring.get(0).size(); i++) {//check data per row if it contains keyword
			
			//assert
				//if no matching keyword found
				if(actualMonitoring.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
					fail("No data found, make sure you use keyword that available");
				}
				//if one of the column have contain data from keyword
				else {
					String[] splitKeyword = keyword.split("");
					
					for (String element : splitKeyword) {
						//if it contain in kota
						System.out.println("hasil list: "+element);
						System.out.println(actualMonitoring.get(0).get(i).getText());
						if(actualMonitoring.get(0).get(i).getText().toLowerCase().contains(element.toLowerCase()) ) {
							isCorrect = true;
							
						} else {
							//if one of the row doesn't contain split keyword then fail
							isCorrect = false;
							break;
						}
					}
				}
			}
		return isCorrect;
	}
	
	
	@Test(priority = 1)
	public void tekan_monitoring_page(){
		String actual = homePage.clickAndGoToMonitoring()
				.getTitleMonitoring().trim();
				
		assertEquals(actual, "Monitoring");
	}
	
	@Test(priority = 2, enabled = true)
	public void input_kolom_search_by_kota(){
		String keyword = "yogyakarta";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 3, enabled = true)
	public void input_kolom_search_by_TotalData(){
		String keyword = "50";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 4, enabled = true)
	public void input_kolom_search_by_TotalVisit(){
		String keyword = "3";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 5, enabled = true)
	public void input_kolom_search_by_TotalVisit2(){
		String keyword = "1822";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 6, enabled = true)
	public void input_kolom_search_by_Price(){
		String keyword = "7000";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 7, enabled = true)
	public void input_kolom_search_by_potongan_kata_3_huruf(){
		String keyword = "bua";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(1000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
			
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 8, enabled = true)
	public void input_kolom_search_by_simbol(){
		String keyword = "@@@@";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 9, enabled = true)
	public void input_kolom_search_by_spasi(){
		String keyword = " ";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 10, enabled = true)
	public void input_kolom_search_by_tanpa_isi(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	
	@Test(priority = 11, enabled = true)
	public void input_kolom_search_by_potongan_kata_5_huruf(){
		String keyword = "ambua";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 12, enabled = true)
	public void input_kolom_search_by_potongan_kata_5_huruf_dengan_spasi(){
		String keyword = "si tim";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 13, enabled = true)
	public void input_kolom_search_by_potongan_kata_4_huruf(){
		String keyword = "yaka";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 14, enabled = true)
	public void input_kolom_search_by_potongan_detail_dari_jakarta(){
		String keyword = "Skybridge Lantai 3";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}	
	
	@Test(priority = 15, enabled = true)
	public void input_kolom_search_by_detail_dari_jakarta2(){
		String keyword = "Lantai 4";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 16, enabled = true)
	public void input_kolom_search_by_potongan_detail_dari_jakarta3(){
		String keyword = "f2";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	
	@Test(priority = 17, enabled = true)
	public void input_kolom_search_by_potongan_detail_dari_yogyakarta(){
		String keyword = "2G";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);

		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 18, enabled = true)
	public void input_kolom_search_by_potongan_detail_dari_bintaro(){
		String keyword = "24";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
		
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 19, enabled = true)
	public void input_kolom_search_by_potongan_detail_dari_bekasitimur_dkk(){
		String keyword = "lt1";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring()
		.inputFieldSearch(keyword)
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
				
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 20)
	public void memeriksa_btn_expand_compress_monitoring() {
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		tool.stopForMoment(15000);

		// when compress
		WebElement dimensionCompress = monitoring.getElementPanelViewMonitor();

		// press button compress expand
		int totalDimensionSizeCompress = (dimensionCompress.getSize().getHeight()
				+ dimensionCompress.getSize().getWidth());

		// when expand
		WebElement dimensionExpand = monitoring.clickExpand().getElementPanelViewMonitor();
		int totalDimensionSizeExpand = (dimensionExpand.getSize().getHeight() + dimensionExpand.getSize().getWidth());

		monitoring.clickExpand();

		assertTrue(totalDimensionSizeCompress < totalDimensionSizeExpand);

	}
	
	@Test(priority = 21, enabled = true)
	public void search_saat_expand_by_kota(){
		String keyword = "Yogyakarta";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 22, enabled = true)
	public void search_saat_expand_by_TotalData(){
		String keyword = "50";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 23, enabled = true)
	public void search_saat_expand_by_potongan_kata_4_huruf(){
		String keyword = "yaka";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 24, enabled = true)
	public void search_saat_expand_by_total_visit(){
		String keyword = "72677";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 25, enabled = true)
	public void search_saat_expand_by_price(){
		String keyword = "177335350";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 26, enabled = true)
	public void search_saat_expand_by_potongan_kata_3_huruf(){
		String keyword = "uta";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 27, enabled = true)
	public void search_saat_expand_by_simbol(){
		String keyword = "@@@@";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 28, enabled = true)
	public void search_saat_expand_menggunakan_spasi(){
		String keyword = " ";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		//screen shoot
		tool.screenShoot(driver);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 29, enabled = true)
	public void search_saat_expand_tanpa_isi(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 31, enabled = true)
	public void search_saat_expand_by_potongan_kata_5_huruf(){
		String keyword = "ambua";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 32, enabled = true)
	public void search_saat_expand_by_potongan_kata_5_huruf_dengan_spasi(){
		String keyword = "si tim";//input keyword that must have result/data
		
		List<List<WebElement>> actualMonitoring = homePage.clickAndGoToMonitoring().clickExpand()
		.inputFieldSearch(keyword).clickExpand()
		.getTableMonitoring();
		
		tool.stopForMoment(2000);
	
		boolean isCorrect = checkSearch(keyword, actualMonitoring);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 33)
	public void tekan_tombol_collapse_or_expand_monitoring(){
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		tool.stopForMoment(1500);
		//if displayed then true
		boolean isDiplayed = monitoring.getElementPanelListMonitor().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		tool.stopForMoment(2000);
		monitoring.clickCollapse();
		tool.stopForMoment(2000);

		//if hidden then true
		boolean isHidden = !monitoring.getElementPanelListMonitor().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 34, enabled = true)
	public void tekan_btn_plus(){
		
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		
		//size column kota before click plus
		driver.findElement(By.xpath("//*[@id=\"data-monitoring\"]/tbody/tr/td[1]"));
		boolean detailBeforeIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		//click plus
		monitoring.clickBtnPlus("Yogyakarta");
		tool.stopForMoment(1000);
		//get size column after click plus
		boolean detailAfterIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		
		assertTrue((detailBeforeIsDisplayed == false) && 
				(detailAfterIsDisplayed == true));
	}
	
	@Test(priority = 35, enabled = true)
	public void tekan_btn_minus(){
		
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		
		//size column kota before click plus
		driver.findElement(By.xpath("//*[@id=\"data-monitoring\"]/tbody/tr/td[1]"));
		//click plus
		monitoring.clickBtnPlus("Yogyakarta");
		tool.stopForMoment(1000);
		//get size column after click plus
		boolean detailAfterIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		
		monitoring.clickBtnMinus("Yogyakarta");
		tool.stopForMoment(1000);

		boolean detailBeforeIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();

		assertTrue((detailBeforeIsDisplayed == false) && 
				(detailAfterIsDisplayed == true));
	}
	
	@Test(priority = 34, enabled = true)
	public void tekan_btn_plus_di_2_kota(){
		
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		
		//size column kota before click plus
		driver.findElement(By.xpath("//*[@id=\"data-monitoring\"]/tbody/tr/td[1]"));
		boolean detailBeforeIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		//click plus
		monitoring.clickBtnPlus("Yogyakarta");
		monitoring.clickBtnPlus("Atambua");
		
		tool.stopForMoment(1000);
		//get size column after click plus
		boolean detailAfterIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		
		assertTrue((detailBeforeIsDisplayed == false) && 
				(detailAfterIsDisplayed == true));
	}
	
	@Test(priority = 34, enabled = true)
	public void tekan_btn_plus_di_3_kota(){
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		
		//size column kota before click plus
		driver.findElement(By.xpath("//*[@id=\"data-monitoring\"]/tbody/tr/td[1]"));
		boolean detailBeforeIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		//click plus
		monitoring.clickBtnPlus("Yogyakarta");
		monitoring.clickBtnPlus("Atambua");
		monitoring.clickBtnPlus("Bekasi Timur");
		tool.stopForMoment(1000);
		//get size column after click plus
		boolean detailAfterIsDisplayed = monitoring.getListKolomKota().get(1).isDisplayed();
		
		assertTrue((detailBeforeIsDisplayed == false) && 
				(detailAfterIsDisplayed == true));
	}
	
	@Test(priority = 35, enabled = true)
	public void validasi_menekan_btn_scroll(){
		JCBMonitoringPage monitoring = PageFactory.initElements(driver, JCBMonitoringPage.class);
		homePage.clickAndGoToMonitoring();
		
		tool.stopForMoment(1000);
		tool.scroolVerticalWindows(driver, 300);
		monitoring.clickBtnScroll();	
		
		String actual = monitoring.getTitleMonitoring().trim();

		assertTrue(actual.contains("Monitoring"));
		
  }
	
	@Test(priority = 36, enabled = true)
	public void  validasi_menekan_btn_scroll_saat_plus(){
		JCBMonitoringPage monitoring = homePage.clickAndGoToMonitoring();
		
		monitoring.clickBtnPlus("Yogyakarta");
		
		tool.stopForMoment(2000);
		
		tool.scroolVerticalWindows(driver, 100);
		monitoring.clickBtnScroll();
		tool.stopForMoment(1000);

		String actual = monitoring.getTitleMonitoring().trim();

		assertTrue(actual.contains("Monitoring"));		
  }
}
