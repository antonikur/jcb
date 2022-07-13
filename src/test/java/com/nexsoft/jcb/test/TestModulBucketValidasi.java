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
import com.nexsoft.jcb.pom.JCBBucketDistribusiDetail;
import com.nexsoft.jcb.pom.JCBBucketDistribusiPage;
import com.nexsoft.jcb.pom.JCBBucketValidasiPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBValidasiDetailVisit;


public class TestModulBucketValidasi {
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
	
	@AfterClass
	public void closeDriver() {
		driver.close();
	}
	
	public int getNoLastInLastPage(WebDriver driver) {
		int lastNo = 0;
		JCBBucketDistribusiPage distribusi = PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
		List<WebElement> listButton = distribusi.getListButtonForNavigate();
		int lastPage = (listButton.size()-2);
		listButton.get(lastPage).click();
		//get no last
		List<WebElement> listNo = distribusi.getColumnNo();
		lastNo = Integer.parseInt(listNo.get(listNo.size()-1).getText()) ;
		
		return lastNo;
	}
	
	public int countSizeNo(List<WebElement> listNo) {
		int size = 0;
		if(listNo.get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
			size = 0;
			System.out.println("result before size is "+listNo.size());
			System.out.println("result value is "+listNo.get(0).getText());
		} else {
			size = listNo.size();
		}
		return size;
	}
	
	public int getNoLastInPage(List<WebElement> listNo) {
		int noLastInPage = 0;
		if(listNo.get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
			noLastInPage = 0;
			System.out.println("result before size is "+listNo.size());
			System.out.println("result value is "+listNo.get(0).getText());
		} else {
			noLastInPage = Integer.parseInt(listNo.get(listNo.size()-1).getText());
		}
		
		return noLastInPage;
	}
	
	public void goToLastPage(WebDriver driver) {
		JCBBucketDistribusiPage distribusi = PageFactory.initElements(driver, JCBBucketDistribusiPage.class);
		List<WebElement> listButton = distribusi.getListButtonForNavigate();
		int lastPage = (listButton.size()-2);
		listButton.get(lastPage).click();
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
	
	@Test(priority = 1)
	public void tekan_bucket_dan_bucket_validasi() {
		String actual = homePage.clickAndGoToBucketValidasi().getTitleValidasiPage().trim();

		assertTrue(actual.contains("Data Validation"));
	}
	
	@Test(priority = 2, enabled = true)
	public void filter_all_area(){
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		homePage.clickAndGoToBucketValidasi().clickBtnFilter()
		.selectDropdownListEntriesByValue("all");
		validasi.clickBtnFilter();
		tool.stopForMoment(1000);
		
		
		List<WebElement> lstArea = validasi.getColumnArea();
		boolean isCorrect = false;
		
			if(lstArea.size()>0) {
				isCorrect=true;
				
			}
			else {
				isCorrect=false;
			}
		
		assertTrue(isCorrect, "Filter is not workings");
		
  }
	
	@Test(priority = 2, enabled = true)
	public void filter_area(){
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		homePage.clickAndGoToBucketValidasi().clickBtnFilter()
		.selectDropdownListEntriesByValue("77");
		validasi.clickBtnFilter();
		tool.stopForMoment(1000);
		List<WebElement> lstArea = validasi.getColumnArea();
		boolean isCorrect = false;
		
		for(WebElement element:lstArea) {
			if(element.getText().contains("Grand Indonesia")) {
				isCorrect=true;
				
			}
			else {
				isCorrect=false;
				break;
			}
			
		}
		
		//screen shoot
		tool.screenShoot(driver);
			
		assertTrue(isCorrect, "Filter is not workings");
		
  }
	
	@Test(priority = 4, enabled = true)
	public void fungsi_button_filter(){
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		homePage.clickAndGoToBucketValidasi().clickBtnFilter()
		.selectDropdownListEntriesByValue("76");
		validasi.clickBtnFilter();
		tool.stopForMoment(1000);
		List<WebElement> lstArea = validasi.getColumnArea();
		boolean isCorrect = false;
		
		for(WebElement element:lstArea) {
			if(element.getText().contains("Grand Indonesia")) {
				isCorrect=true;
				
			}
			else {
				isCorrect=false;
				break;
			}
			
		}
		
		//screen shoot
		tool.screenShoot(driver);
				
		assertTrue(isCorrect, "Filter is not workings");
		
  }
	
	@Test(priority = 5, enabled = true)
	public void tekan_tombol_collapse_or_expand_validasi_data(){
		JCBBucketValidasiPage validasi = homePage.clickAndGoToBucketValidasi();
		tool.stopForMoment(2000);
		//if displayed then true
		boolean isDiplayed = validasi.getElementPanelValidasi().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		tool.stopForMoment(2000);
		validasi.clickCollapse();
		
		//if hidden then true
		boolean isHidden = !validasi.getElementPanelValidasi().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 6, enabled = true)
	public void fungsi_btn_next(){
		JCBBucketValidasiPage validasi = homePage.clickAndGoToBucketValidasi();
		validasi.clickBtnNext();
		
		int noLast = getNoLastInPage(validasi.getColumnNo());
		assertTrue(noLast <= 20);
	}
	
	@Test(priority = 7, enabled = true)
	public void fungsi_btn_previous(){
		JCBBucketValidasiPage validasi = homePage.clickAndGoToBucketValidasi();
		validasi.clickBtnNext();
		validasi.clickBtnPrevious();
		
		int noLast = getNoLastInPage(validasi.getColumnNo());
		assertTrue(noLast == 10);
	}
	
	@Test(priority = 8, enabled = true)
	public void fungsi_button_2() {
		JCBBucketValidasiPage validasi = homePage.clickAndGoToBucketValidasi();
//		validasi.clickPageNumber("2");
		validasi.clickBtnPageNumber2();
		int noLast = getNoLastInPage(validasi.getColumnNo());
		assertTrue(noLast <= 20);
  }
	
	@Test(priority = 9)
	public void input_kolom_search_by_no(){
		String keyword = "3";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}	
		
	@Test(priority = 10)
	public void input_kolom_search_by_area(){
		String keyword = "Grand Indonesia";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}	
	
	@Test(priority = 11)
	public void input_kolom_search_by_merchant_name(){
		
		String keyword = "The Duke";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
		
	
	@Test(priority = 12)
	public void input_kolom_search_by_address(){
		
		String keyword = "Jl. M.H. Thamrin No.1";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 13)
	public void input_kolom_search_by_officer(){
		
		String keyword = "MUHAMAD IDAM";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 14)
	public void input_kolom_search_by_visit_date(){

		String keyword = "2021-12-21 09:23:48";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 15)
	public void input_kolom_search_by_note(){
		String keyword = "1A";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
		
	@Test(priority = 16)
	public void input_kolom_search_by_huruf_random(){
		String keyword = "eryeghdbaj";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}	
	
	@Test(priority = 17)
	public void input_kolom_search_by_simbol(){
		String keyword = "@@@@";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 18)
	public void input_kolom_search_menggunakan_spasi(){
		String keyword = " ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}	
	
	@Test(priority = 19)
	public void input_kolom_search_tanpa_isi(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableValidasi = homePage.clickAndGoToBucketValidasi()
		.input_Search(keyword)
		.getTableValidasiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableValidasi );
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 20)
	public void fungsi_button_mata(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit()
				.getTitleDetailVisit().trim();
		
		assertTrue(actual.contains("Detail Visit"));
		
  }
	@Test(priority = 21, enabled = true)
	public void cek_popup_foto_depan_toko(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("1").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("Foto Merchant"));

	}
	
	@Test(priority = 22, enabled = true)
	public void cek_popup_foto_struk_edc(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("2").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("Foto Struk"));

	}
	
	@Test(priority = 23, enabled = true)
	public void cek_popup_spot_meja_kasir_before(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("3").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("Before"));

	}
	
	@Test(priority = 24, enabled = true)
	public void cek_popup_spot_meja_kasir_after(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("4").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("After"));

	}
	
	@Test(priority = 25, enabled = true)
	public void cek_popup_spot_pintu_masuk_before(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("5").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("Before"));

	}
	
	
	@Test(priority = 26, enabled = true)
	public void cek_popup_spot_pintu_masuk_after(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("6").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("After"));

	}
	
	@Test(priority = 27, enabled = true)
	public void cek_popup_other_principal(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickPopUpFoto("7").getPopUpTitle().trim();
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		tool.stopForMoment(2000);

		detailvisit.clickBtnCancelPopup();
		
		assertTrue(actual.contains("Principal"));

	}
	
	@Test(priority = 28, enabled = true)
	public void menekan_button_validate(){
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);

		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickValidate().getValidateForm().trim();
		tool.stopForMoment(2000);

		detailvisit.clickCancelVal();
		
		assertTrue(actual.contains("Validate Form"));
  }
	
	@Test(priority = 29, enabled = true)
	public void validasi_menekan_submit_validate(){
		String notes = "Tes Submit Validate";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		
		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickValidate().clickSubmitVal();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);
  }
	
	@Test(priority = 30)
	public void validasi_menekan_cancel_validate(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickValidate().clickCancelVal()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
  }
	
	@Test(priority = 31)
	public void validasi_menekan_btnX_validate(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickValidate().clickBtnXVal()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
  }
	
	
	@Test(priority = 32, enabled = true)
	public void validasi_menekan_btn_return(){
		
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);

		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReturn().getReturnForm().trim();
		tool.stopForMoment(2000);

		detailvisit.cancelReturn();
		
		assertTrue(actual.contains("return Form"));	
  }
	
	@Test(priority = 33, enabled = true)
	public void return_detail_validasi_dengan_isi_kombinasi(){
		String notes = "kurang lengkap 1234567890 !@#$%^&*()";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		
		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReturn().input_notes(notes).submitReturn();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);

  }
	
	@Test(priority = 34, enabled = true)
	public void return_detail_validasi_dengan_isi_alfabet(){
		String notes = "kurang lengkap";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReturn().input_notes(notes).submitReturn();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);
  }
	
	@Test(priority = 35, enabled = true)
	public void return_detail_validasi_dengan_isi_numerik(){
		String notes = "1234567";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReturn().input_notes(notes).submitReturn();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);

  }
	@Test(priority = 36, enabled = true)
	public void return_detail_validasi_dengan_isi_simbol(){
		String notes = "-";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReturn().input_notes(notes).submitReturn();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);
  }
	
	@Test(priority = 37, enabled = true)
	public void return_detail_validasi_tanpa_isi_notes(){
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		boolean status = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReturn().submitReturn().checkMesageErrNotesBlankReturnDisplay();
		
		tool.stopForMoment(2000);
		
		detailvisit.clickbtnXReturn();
		
		assertTrue(status);
  }
	
	@Test(priority = 38, enabled = false)
	public void validasi_menekan_submit_return(){
		String notes = "Tes Submit Return";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReturn().input_notes(notes).submitReturn();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);
  }
	
	@Test(priority = 39)
	public void validasi_menekan_cancel_return(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReturn().cancelReturn()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
  }
	
	@Test(priority = 40, enabled = true)
	public void validasi_menekan_btnX_return(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReturn().clickbtnXReturn()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
  }
	
	@Test(priority = 41, enabled = true)
	public void validasi_menekan_btn_reject(){
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);

		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReject().getRejectForm().trim();
		tool.stopForMoment(2000);

		detailvisit.cancelReject();
		
		assertTrue(actual.contains("reject Form"));	
  }
	
	@Test(priority = 42, enabled = true)
	public void reject_detail_validasi_dengan_isi_kombinasi(){
		String notes = "kurang lengkap 1234567890 !@#$%^&*()";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		
		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReject().input_notes(notes).submitReject();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);

  }
	
	@Test(priority = 43, enabled = true)
	public void reject_detail_validasi_dengan_isi_alfabet(){
		String notes = "data tidak valid";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		
		int beforeRejectSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeRejectSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReject().input_notes(notes).submitReject();
		
		int afterRejectSize = validasi.getColumnNo().size();
		System.out.println(afterRejectSize);

		tool.stopForMoment(2000);
		assertTrue(beforeRejectSize > afterRejectSize);

  }
	
	@Test(priority = 44, enabled = true)
	public void reject_detail_validasi_dengan_isi_numerik(){
		String notes = "1234567";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeRejectSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeRejectSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReject().input_notes(notes).submitReject();
		
		int afterRejectSize = validasi.getColumnNo().size();
		System.out.println(afterRejectSize);

		tool.stopForMoment(2000);
		assertTrue(beforeRejectSize > afterRejectSize);

  }
	
	@Test(priority = 45, enabled = true)
	public void reject_detail_validasi_dengan_isi_simbol(){
		String notes = "-";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeRejectSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeRejectSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReject().input_notes(notes).submitReject();
		
		int afterRejectSize = validasi.getColumnNo().size();
		System.out.println(afterRejectSize);

		tool.stopForMoment(2000);
		assertTrue(beforeRejectSize > afterRejectSize);

  }
	
	@Test(priority = 46, enabled = true)
	public void reject_detail_validasi_tanpa_isi_notes(){
		JCBValidasiDetailVisit detailvisit = PageFactory.initElements(driver, JCBValidasiDetailVisit.class);
		boolean status = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit()
				.clickReject().submitReject().checkMesageErrNotesBlankReturnDisplay();
		
		tool.stopForMoment(2000);
		
		detailvisit.clickbtnXReject();
		
		assertTrue(status);
  }
	
	@Test(priority = 47, enabled = false)
	public void validasi_menekan_submit_reject(){
		String notes = "Tes Submit Reject";
		
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);

		int beforeReturnSize = homePage.clickAndGoToBucketValidasi().getColumnNo().size();
		System.out.println(beforeReturnSize);
		
		validasi.clickAndGoToValidasiDetailVisit().clickReject().input_notes(notes).submitReject();
		
		int afterReturnSize = validasi.getColumnNo().size();
		System.out.println(afterReturnSize);

		tool.stopForMoment(2000);
		assertTrue(beforeReturnSize > afterReturnSize);
  }
	
	@Test(priority = 48, enabled = true)
	public void validasi_menekan_cancel_reject(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReject().cancelReject()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
  }
	
	@Test(priority = 49, enabled = true)
	public void validasi_menekan_btnX_reject(){
		String actual = homePage.clickAndGoToBucketValidasi().clickAndGoToValidasiDetailVisit().clickReject().clickbtnXReject()
				.getTitleDetailVisit().trim();
		
		tool.stopForMoment(2000);
		
		assertTrue(actual.contains("Detail Visit"));
	}
	
	@Test(priority = 50, enabled = true)
	public void validasi_menekan_btn_scroll(){
		JCBBucketValidasiPage validasi = PageFactory.initElements(driver, JCBBucketValidasiPage.class);
		homePage.clickAndGoToBucketValidasi();
		
		tool.stopForMoment(1000);
		tool.scroolVerticalWindows(driver, 300);
		validasi.clickBtnScroll();
		
		String actual = validasi.getTitleValidasiPage().trim();

		assertTrue(actual.contains("Data Validation"));
  }
	
	
}
