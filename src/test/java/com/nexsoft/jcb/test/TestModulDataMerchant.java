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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nexsoft.jcb.other.Tools;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulDataMerchant {
	
	protected WebDriver driver;
//	protected WebDriverWait wait;
	protected Tools tool;
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
	
	@Test(priority = 46)
	public void tekan_menu_data_merchant(){
		String actual = homePage.clickAndGotoMenuDataMerchant()
		.getTitleDataMerchantPage().trim();
		
		assertEquals(actual, "Data Merchant");
	}
	
	@Test(priority = 47)
	public void input_search_merchant_name_by_nama_merchant_dan_tekan_search() {
		
		
		////////////////////////
		String keyword = "jakarta";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableKota = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		/////////////////////
		boolean isCorrect = false;
		
		
		for(int i=0; i<actualTableKota.get(0).size(); i++) {//check data per row if it contains keyword
		
		//assert
			//if no matching keyword found
			if(actualTableKota.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
				fail("No data found, make sure you use keyword that available");
			}
			//if one of the column have contain data from keyword
			else {
				String[] splitKeyword = keyword.trim().split(" ");
				
				for (String element : splitKeyword) {
					//if it contain in no
					if(actualTableKota.get(0).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
							// if it contain in kota
							actualTableKota.get(1).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) ) {
						isCorrect = true;
						
					} else {
						//if one of the row doesn't contain split keyword then fail
						isCorrect = false;
						break;
					}
				}
			}
		}
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
		
//		String keyword = "toni";//input keyword that must have result/data
//		
//		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
//		.inputFieldSearch(keyword)
//		.getTableDataUser();
//		boolean isCorrect = false;
//		
//		for(int i=0; i<actualTableUser.get(0).size(); i++) {//check data per row if it contains keyword
//		
//		//assert
//			//if no matching keyword found
//			if(actualTableUser.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
//				
//				fail("No data found, make sure you use keyword that available");
//			}
//			//if one of the column have contain data from keyword
//			else {
//					//if it contain in no
//				if(actualTableUser.get(0).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
//						// if it contain in nik
//						actualTableUser.get(1).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
//						// if it contain in name
//						actualTableUser.get(2).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
//						//if it contain in privilage
//						actualTableUser.get(3).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) ) { 
//					isCorrect = true;
//				} else {
//					//if one of the row doesn't contain keyword then fail
//					isCorrect = false;
//					break;
//				}
//			}
//		}
//		
//		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 48)
	public void input_search_merchant_name_by_no_dan_tekan_search() {
		
	}
	
	@Test(priority = 49)
	public void input_search_merchant_name_by_area_dan_tekan_search() {
		
	}
	
	@Test(priority = 50)
	public void input_search_merchant_name_by_batch_dan_tekan_search() {
		
	}
	
	
	@Test(priority = 0)
	public void input_search_merchant_name_by_address_dan_tekan_search(){
		
	}
	
	@Test(priority = 1)
	public void input_search_merchant_name_by_category_dan_tekan_search(){

	}
	
	@Test(priority = 2)
	public void input_search_merchant_name_by_officer_dan_tekan_search(){

	}
	
	@Test(priority = 3)
	public void input_search_merchant_name_by_status_visit_dan_tekan_search(){

	}
	
	@Test(priority = 4)
	public void input_search_merchant_name_by_nama_merchant_dengan_spasi_di_awal_dan_akhir_kemudian_tekan_search(){

	}
	
	@Test(priority = 5)
	public void kolom_search_kosong(){

	}
	
	@Test(priority = 6)
	public void tekan_tombol_expand_or_compress(){

	}
	
	@Test(priority = 7)
	public void tekan_tombol_collapse_or_expand(){

	}
	
	@Test(priority = 8)
	public void tekan_tombol_navigasi_previous_halaman(){

	}
	
	@Test(priority = 9)
	public void tekan_tombol_navigasi_nomor_halaman(){

	}
	
	@Test(priority = 10)
	public void tekan_tombol_navigasi_next_halaman(){

	}
	
	@Test(priority = 11)
	public void tekan_tombol_navigasi_first_halaman(){

	}
	
	@Test(priority = 12)
	public void tekan_tombol_navigasi_last_halaman(){

	}
	
	@Test(priority = 13)
	public void tekan_tombol_delete_di_dari_data_merchant(){

	}
	
	@Test(priority = 14)
	public void tekan_tombol_add_new_merchant(){

	}
	
	@Test(priority = 15)
	public void input_data_valid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 16)
	public void input_batch_invalid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 17)
	public void input_merchant_name_invalid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 18)
	public void input_address_invalid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 19)
	public void input_address_by_floor_invalid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 20)
	public void input_category_invalid_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 21)
	public void data_batch_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 22)
	public void data_kota_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 23)
	public void data_area_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 24)
	public void data_merchant_name_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 25)
	public void data_address_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 26)
	public void data_address_by_floor_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 27)
	public void data_category_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 28)
	public void data_users_kosong_or_tidak_pilih_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 29)
	public void data_batch_panjang_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 30)
	public void data_merchant_name_panjang_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 31)
	public void data_address_panjang_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 32)
	public void data_address_by_floor_panjang_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 33)
	public void data_category_panjang_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 34)
	public void input_data_valid_di_new_merchant_dan_cancel_or_silang(){

	}
	
	@Test(priority = 35)
	public void data_batch_di_isi_white_space_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 36)
	public void data_merchant_name_di_isi_white_space_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 37)
	public void data_address_di_isi_white_space_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 38)
	public void data_address_by_floor_di_isi_white_space_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 39)
	public void data_category_di_isi_white_space_di_new_merchant_dan_save(){

	}
	
	@Test(priority = 40)
	public void tekan_tombol_download_template(){

	}
	
	@Test(priority = 41)
	public void tekan_tombol_upload_data(){

	}
	
	@Test(priority = 42)
	public void tekan_choose_file_dan_pilih_file_dengan_format_valid_kemudian_pilih_process(){

	}
	
	@Test(priority = 43)
	public void tekan_choose_file_dan_pilih_file_degan_format_invalid_kemudian_pilih_process(){

	}
	
	@Test(priority = 44)
	public void tekan_choose_file_dan_pilih_file_dengan_format_valid_kemudian_pilih_cancel_di_popup_upload(){

	}
	
	@Test(priority = 45)
	public void tekan_process_tanpa_pilih_file(){

	}
	

}
