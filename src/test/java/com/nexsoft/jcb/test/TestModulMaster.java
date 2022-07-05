package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

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
import com.nexsoft.jcb.pom.JCBMasterUserPage;

public class TestModulMaster {
	
	protected WebDriver driver;
//	protected WebDriverWait wait;
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
	
	
	@Test(priority = 0)
	public void tekan_menu_master_user(){
		String actual = homePage.clickAndGotoMenuMasterUser()
				.getTitleUserPage().trim();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertEquals(actual, "Data User");
	}
	
	@Test(priority = 1)
	public void input_kolom_search_by_no_di_user(){
		
	}
	
	@Test(priority = 2)
	public void input_kolom_search_by_nik(){

	}
	
	@Test(priority = 3)
	public void input_kolom_search_by_name(){
		String keyword = "toni";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
		.inputFieldSearch(keyword)
		.getTableDataUser();
		boolean isCorrect = false;
		
		for(int i=0; i<actualTableUser.get(0).size(); i++) {//check data per row if it contains keyword
		
		//assert
			//if no matching keyword found
			if(actualTableUser.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
				//logout
				PageFactory.initElements(driver, JCBMasterUserPage.class)
				.clickLogoutAndGotoLogin();
				
				fail("No data found, make sure you use keyword that available");
			}
			//if one of the column have contain data from keyword
			else {
					//if it contain in no
				if(actualTableUser.get(0).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						// if it contain in nik
						actualTableUser.get(1).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						// if it contain in name
						actualTableUser.get(2).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						//if it contain in privilage
						actualTableUser.get(3).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) ) { 
					isCorrect = true;
				} else {
					//if one of the row doesn't contain keyword then fail
					isCorrect = false;
					break;
				}
			}
		}
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 4)
	public void input_kolom_search_by_privilage(){

	}
	
	@Test(priority = 5)
	public void input_kolom_search_by_nik_dan_tambah_spasi_di_awal_dan_akhir_keyword(){

	}
	
	@Test(priority = 6)
	public void kolom_search_kosong_di_user(){

	}
	
	@Test(priority = 7)
	public void pilih_show_entries_10_di_user(){

	}
	
	@Test(priority = 8)
	public void pilih_show_entries_25_di_user(){

	}
	
	@Test(priority = 9)
	public void pilih_show_entries_50_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
		.selectDropdownListEntriesByValue("50")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertEquals(entriesSize, 50, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 10)
	public void pilih_show_entries_100_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
		.selectDropdownListEntriesByValue("100")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertEquals(entriesSize, 100, "kemungkinan karena data tidak mencukupi 100, tolong dicek dan ditambah");
	}
	
	@Test(priority = 11)
	public void tekan_tombol_expand_or_compress_di_user(){

	}
	
	@Test(priority = 12)
	public void tekan_tombol_collapse_or_expand_di_user(){

	}
	
	@Test(priority = 13)
	public void tekan_tombol_navigasi_previous_halaman_di_user(){

	}
	
	@Test(priority = 14)
	public void tekan_tombol_navigasi_nomor_halaman_di_user(){

	}
	
	@Test(priority = 15)
	public void tekan_tombol_navigasi_next_halaman_di_user(){

	}
	
	@Test(priority = 16)
	public void tekan_tombol_add_new_user(){
		String titlePopupNewUser = homePage.clickAndGotoMenuMasterUser()
		.clickAddNewUser()
		.getTitlePopupNewUser().trim();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickBtnCancel()
		.clickLogoutAndGotoLogin();
		
		assertEquals(titlePopupNewUser, "Form Input User");
	}
	
	@Test(priority = 17)
	public void input_data_valid_di_new_user_dan_save(){
		String actual = homePage.clickAndGotoMenuMasterUser()
		.clickAddNewUser()
		.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "123@!$4ad5", "4")
		.clickBtnSave()
		.getMessageAddNewSuccess();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertTrue(actual.contains("Data berhasil disimpan."));
//		assertEquals(actual, "Data berhasil disimpan.");
	}
	
	@Test(priority = 18)
	public void input_data_nik_invalid_di_new_user_dan_save(){

	}
	
	@Test(priority = 19)
	public void input_data_name_invalid_di_new_user_dan_save(){

	}
	
	@Test(priority = 20)
	public void input_data_username_invalid_di_new_user_dan_save(){

	}
	
	@Test(priority = 21)
	public void data_nik_kosong_di_new_user_dan_save(){

	}
	
	@Test(priority = 22)
	public void data_name_kosong_di_new_user_dan_save(){

	}
	
	@Test(priority = 23)
	public void data_username_kosong_di_new_user_dan_save(){

	}
	
	@Test(priority = 24)
	public void data_password_kosong_di_new_user_dan_save(){

	}
	
	@Test(priority = 25)
	public void data_privilage_pilih_default_di_new_user_dan_save(){

	}
	
	@Test(priority = 26)
	public void data_nik_panjang_di_new_user_dan_save(){

	}
	
	@Test(priority = 27)
	public void data_name_panjang_di_new_user_dan_save(){

	}
	
	@Test(priority = 28)
	public void data_username_panjang_di_new_user_dan_save(){

	}
	
	@Test(priority = 29)
	public void data_password_panjang_di_new_user_dan_save(){

	}
	
	@Test(priority = 30)
	public void data_nik_di_isi_white_space_di_new_user_dan_save(){

	}
	
	@Test(priority = 31)
	public void data_name_di_isi_white_space_di_new_user_dan_save(){

	}
	
	@Test(priority = 32)
	public void data_username_di_isi_white_space_di_new_user_dan_save(){

	}
	
	@Test(priority = 33)
	public void data_pass_di_isi_white_space_di_new_user_dan_save(){

	}
	
	@Test(priority = 34)
	public void input_data_valid_di_new_user_dan_cancel(){

	}
	
	@Test(priority = 35)
	public void input_atau_buat_data_valid_yang_sama_persisi(){

	}
	
	@Test(priority = 36)
	public void tekan_tombol_edit_pada_baris_user(){
		String actual = homePage.clickAndGotoMenuMasterUser()
		.clickEditUserByNo("5")
		.getTitlePopupEditUser();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickBtnCancelPopupEdit()
		.clickLogoutAndGotoLogin();
		
		assertEquals(actual, "Form Edit User");
	}
	
	@Test(priority = 37)
	public void tekan_tombol_edit_pada_baris_user_ketika_kotak_view_data_user_di_expand(){

	}
	
	@Test(priority = 38)
	public void ubah_data_user_di_pop_up_edit_dan_tekan_save(){

	}
	
	@Test(priority = 39)
	public void ubah_data_user_di_pop_up_edit_dan_tekan_cancel_or_silang_di_user(){

	}
	
	@Test(priority = 40)
	public void tekan_tombol_delete_pada_baris_user(){
		String actual = homePage.clickAndGotoMenuMasterUser()
		.selectDropdownListEntriesByValue("100")
		.clickDeleteUserByNo("100")
		.getMessageDeleteSuccess();
		
		//logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickLogoutAndGotoLogin();
		
		assertTrue(actual.contains("Data berhasil dihapus."));
	}
	
	@Test(priority = 41)
	public void tekan_tombol_delete_pada_baris_user_ketika_kotak_view_data_user_di_expand(){

	}
	
	@Test(priority = 42)
	public void tekan_menu_master_kota(){

	}
	
	@Test(priority = 43)
	public void input_kolom_search_by_no_di_kota(){

	}
	
	@Test(priority = 44)
	public void input_kolom_search_by_kota(){

	}
	
	@Test(priority = 45)
	public void input_kolom_search_by_kota_dan_tambah_spasi_di_awal_dan_akhir_keyword(){

	}
	
	@Test(priority = 46)
	public void kolom_search_kosong_di_kota(){

	}
	
	@Test(priority = 47)
	public void pilih_show_entries_10_di_kota(){

	}
	
	@Test(priority = 48)
	public void pilih_show_entries_25_di_kota(){

	}
	
	@Test(priority = 49)
	public void pilih_show_entries_50_di_kota(){

	}
	
	@Test(priority = 50)
	public void pilih_show_entries_100_di_kota(){

	}
	
	@Test(priority = 51)
	public void tekan_tombol_expand_or_compress_di_kota(){

	}
	
	@Test(priority = 52)
	public void tekan_tombol_collapse_or_expand_di_kota(){

	}
	
	@Test(priority = 53)
	public void tekan_tombol_navigasi_previous_halaman_di_kota(){

	}
	
	@Test(priority = 54)
	public void tekan_tombol_navigasi_nomor_halaman_di_kota(){

	}
	
	@Test(priority = 55)
	public void tekan_tombol_navigasi_next_halaman_di_kota(){

	}
	
	@Test(priority = 56)
	public void tekan_tombol_add_new_kota(){

	}
	
	@Test(priority = 57)
	public void input_data_kota_valid_dan_save(){

	}
	
	@Test(priority = 58)
	public void input_data_kota_invalid_dan_save(){

	}
	
	@Test(priority = 59)
	public void input_data_kota_panjang_dan_save(){

	}
	
	@Test(priority = 60)
	public void data_kota_kosong_dan_save(){

	}
	
	@Test(priority = 61)
	public void data_kota_di_isi_white_space_dan_save(){

	}
	
	@Test(priority = 62)
	public void input_data_kota_valid_dan_cancel(){

	}
	
	@Test(priority = 63)
	public void tekan_tombol_edit_pada_baris_kota(){

	}
	
	@Test(priority = 64)
	public void tekan_tombol_edit_pada_baris_kota_ketika_kotak_view_data_kota_di_expand(){

	}
	
	@Test(priority = 65)
	public void ubah_data_kota_di_pop_up_edit_dan_tekan_save(){

	}
	
	@Test(priority = 66)
	public void ubah_data_kota_di_pop_up_edit_dan_tekan_cancel_or_silang(){

	}
	
	@Test(priority = 67)
	public void tekan_menu_master_area(){

	}
	
	@Test(priority = 68)
	public void input_kolom_search_by_no_di_area(){

	}
	
	@Test(priority = 69)
	public void input_kolom_search_by_kode_area(){

	}
	
	@Test(priority = 70)
	public void input_kolom_search_by_area(){

	}
	
	@Test(priority = 71)
	public void input_kolom_search_by_area_dan_tambah_spasi_di_awal_dan_akhir_keyword(){

	}
	
	@Test(priority = 72)
	public void kolom_search_kosong_di_area(){

	}
	
	@Test(priority = 73)
	public void pilih_show_entries_10_di_area(){

	}
	
	@Test(priority = 74)
	public void pilih_show_entries_25_di_area(){

	}
	
	@Test(priority = 75)
	public void pilih_show_entries_50_di_area(){

	}
	
	@Test(priority = 76)
	public void pilih_show_entries_100_di_area(){

	}
	
	@Test(priority = 77)
	public void tekan_tombol_expand_or_compress_di_area(){

	}
	
	@Test(priority = 78)
	public void tekan_tombol_collapse_or_expand_di_area(){

	}
	
	@Test(priority = 79)
	public void tekan_tombol_navigasi_previous_halaman_di_area(){

	}
	
	@Test(priority = 80)
	public void tekan_tombol_navigasi_nomor_halaman_di_area(){

	}
	
	@Test(priority = 81)
	public void tekan_tombol_navigasi_next_halaman_di_area(){

	}
	
	@Test(priority = 82)
	public void tekan_tombol_add_new_area(){

	}
	
	@Test(priority = 83)
	public void input_data_area_valid_dan_save(){

	}
	
	@Test(priority = 84)
	public void input_data_area_invalid_dan_save(){

	}
	
	@Test(priority = 85)
	public void input_data_area_panjang_dan_save(){

	}
	
	@Test(priority = 86)
	public void data_area_di_isi_white_space_dan_save(){

	}
	
	@Test(priority = 87)
	public void input_data_area_valid_dan_cancel(){

	}
	
	@Test(priority = 88)
	public void tekan_tombol_edit_pada_baris_area(){

	}
	
	@Test(priority = 89)
	public void tekan_tombol_edit_pada_baris_area_ketika_kotak_view_data_area_di_expand(){

	}
	
	@Test(priority = 90)
	public void ubah_data_area_di_pop_up_edit_dan_tekan_save(){

	}
	
	@Test(priority = 91)
	public void ubah_data_area_di_pop_up_edit_dan_tekan_cancel_or_silang(){

	}
	

}
