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
import com.nexsoft.jcb.pom.JCBDataMerchantPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBMasterUserPage;

public class TestModulDataMerchant {
	
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
	
	//other tools
	//#############################################################################################
	//#############################################################################################
	public int getNoLastInLastPageThanGoBackToStart(WebDriver driver) {
		int lastNo = 0;
		JCBDataMerchantPage merchantPage = PageFactory.initElements(driver, JCBDataMerchantPage.class);
		merchantPage.clickBtnLastPage();
		List<WebElement> listNo = merchantPage.getColumnNo();
		lastNo = Integer.parseInt(listNo.get(listNo.size()-1).getText());
		
		//go to start page
		merchantPage.clickBtnFirstPage();
		
		return lastNo;
	}
	
	
	public boolean checkSearchIsCorrect(String keyword, List<List<WebElement>> actualTableMerchant) {
		boolean isCorrect = false;
		//check if there is result from search
		if(actualTableMerchant.get(3).size() <= 0) {
			isCorrect = true;
			System.out.println("No Result");
//			fail("There is no result form keyword, use keyword that have data");
		}
			
		
		for(int i=0; i<actualTableMerchant.get(3).size(); i++) {//check data merchant name per row if it contains keyword
			//if it contain keyword in nama merchant
			if(actualTableMerchant.get(3).get(i).getText().toLowerCase().contains(keyword.toLowerCase()) ) {
				isCorrect = true;
				
			} else {
				//if one of the row doesn't contain split keyword then fail
				isCorrect = false;
				break;
			}
		}
		return isCorrect;
	}
	
	//#############################################################################################
	//#############################################################################################
	
	@Test(priority = 47)
	public void input_search_merchant_name_by_nama_merchant_dan_tekan_search() {
		String keyword = "Shihlin";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 48)
	public void input_search_merchant_name_by_no_dan_tekan_search() {
		
//		System.out.println(getNoLastInLastPage(homePage.clickAndGotoMenuDataMerchant().getDriver()) );
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
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int totalSizeCompress = merchantPage.getPanelViewDataMerchant().getSize().getHeight() + merchantPage.getPanelViewDataMerchant().getSize().getWidth();
		System.out.println("compress size: "+totalSizeCompress);
		
		merchantPage.clickBtnExpandCompress();
		
		int totalSizeExpand = merchantPage.getPanelViewDataMerchant().getSize().getHeight() + merchantPage.getPanelViewDataMerchant().getSize().getWidth();
		System.out.println("Expand size: "+totalSizeExpand);
		
		//compress for logout
		merchantPage.clickBtnExpandCompress();
		
		assertTrue(totalSizeCompress < totalSizeExpand);
	}
	
	@Test(priority = 7)
	public void tekan_tombol_collapse_or_expand(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		boolean panelIsDisplayed = merchantPage.getPanelViewListTableMerchant().isDisplayed();
		System.out.println("is displayed: "+panelIsDisplayed);
		merchantPage.clickBtnCollapseExpand();
		
		//if hidden then true
		boolean panelIsHidden = !merchantPage.getPanelViewListTableMerchant().isDisplayed();
		System.out.println("is hidden: "+panelIsHidden);
		
		assertTrue(panelIsDisplayed && panelIsHidden);
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
		String search = "the duke";
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(search);//before delete, search data first to know how many
		
		int beforeDeleteResult = merchantPage.getColumnNo().size();
		
		merchantPage.deleteDataMerchantByIndex("1");//delete first row from search result
		
		int afterDeleteResult = merchantPage.inputAndClickSearch(search)//search again after delete
		.getColumnNo().size();
		
		assertTrue(beforeDeleteResult > afterDeleteResult, "Data tidak terdelete");
	}
	
	@Test(priority = 14)
	public void tekan_tombol_add_new_merchant(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant()
				.clickBtnAddNewMerchant();
		String actual = merchantPage.getTitlePopupAddMerchant().trim();
		
		merchantPage.clickBtnCancelAddNewMerchant();
		
		assertEquals(actual, "Form Input Merchant Baru");
	}
	
	@Test(priority = 15)
	public void input_data_valid_di_new_merchant_dan_save(){
		String search = "the duke";//search for testing
		
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = merchantPage.inputAndClickSearch(search)
		.getColumnNo().size();
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant("November 2021", "JAKARTA", "Plaza Indonesia", "The Duke", "jl. sudirman", "f2", "fnb", "Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
//		//........................
//		String message = merchantPage.getMessageAddNewMerchantSuccess();
//		System.out.println("show message : "+message);
//		WebElement element = merchantPage.getElementMessageAddMerchantSuccess();
//		System.out.println("displayed: "+element.isDisplayed());
//		System.out.println("size: "+element.getSize());
//		//........................
		
		//get how many data from search result after add data
		int afterAdd = merchantPage.inputAndClickSearch(search)
				.getColumnNo().size();
		
		//assert
		assertTrue(beforeAdd < afterAdd);
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
		try {
			String message = homePage.clickAndGotoMenuDataMerchant()
			.clickBtnDownload()
			.getMessageError404();
			System.out.println("Error: "+message);
		} catch (Exception e) {
			// TODO: handle exception
			//no error message found
			return;
		}
		
		//go back because the error in new page
		driver.navigate().back();
		
		fail("Message error found");
	}
	
	@Test(priority = 41)
	public void tekan_tombol_upload_data(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		String actual = merchantPage
		.clickBtnUploadData()
		.getTitlePopupUpload().trim();
		
		merchantPage.clickBtnCancelPopupUpload();
		
		assertEquals(actual, "Form Upload");
	}
	
	@Test(priority = 42)
	public void tekan_choose_file_dan_pilih_file_dengan_format_valid_kemudian_pilih_process(){
		try {
			String message = homePage.clickAndGotoMenuDataMerchant()
			.clickBtnUploadData()
			.selectFilePopupUpload("C:\\Antoni\\selenium\\MOCK_DATA_ABSENSI.xlsx")
			.clickBtnProcessPopupUpload()
			.getMessageErrorUpload();
			
			System.out.println("Error: "+message);
		} catch (Exception e) {
			// TODO: handle exception
			//no error message found
			return;
		}
		//go back because the error in new page
		driver.navigate().back();
		
		fail("There is error message");
	}
	
	@Test(priority = 43)
	public void tekan_choose_file_dan_pilih_file_degan_format_invalid_kemudian_pilih_process(){
		
		String message = homePage.clickAndGotoMenuDataMerchant()
				.clickBtnUploadData()
				.selectFilePopupUpload("C:\\Antoni\\selenium\\HelloWorld.side")
				.clickBtnProcessPopupUpload()
				.getMessageErrorUpload().trim();
		
		assertEquals(message, "The filetype you are attempting to upload is not allowed.");
		
		//go back to previous page
		driver.navigate().back();
	}
	
	@Test(priority = 44)
	public void tekan_choose_file_dan_pilih_file_dengan_format_valid_kemudian_pilih_cancel_di_popup_upload(){
		
		boolean isDisplayed = homePage.clickAndGotoMenuDataMerchant()
				.clickBtnUploadData()
				.selectFilePopupUpload("C:\\\\Antoni\\\\selenium\\\\MOCK_DATA_ABSENSI.xlsx")
				.clickBtnCancelPopupUpload()
				.checkTitlePopupUpload();
		assertTrue(isDisplayed == false);
	}
	
	@Test(priority = 45)
	public void tekan_process_tanpa_pilih_file(){
		String message = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnUploadData()
		.clickBtnProcessPopupUpload()
		.getMessageErrorFileNotSelected().trim();
		
		assertEquals(message, "You did not select a file to upload.");
		
		//go back to previous page
		driver.navigate().back();
	}
	

}
