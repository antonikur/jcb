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
import com.nexsoft.jcb.pom.JCBDataMerchantPage;
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;

public class TestModulDataMerchant {
	
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
	
	@AfterClass
	public void driverClose() {
		driver.close();
	}
	
	//other tools
	//#############################################################################################
	//#############################################################################################
	public int getNoLastInLastPage(WebDriver driver) {
		int lastNo = 0;
		JCBDataMerchantPage merchantPage = PageFactory.initElements(driver, JCBDataMerchantPage.class);
		merchantPage.clickBtnLastPage();
		List<WebElement> listNo = merchantPage.getColumnNo();
		lastNo = Integer.parseInt(listNo.get(listNo.size()-1).getText());
		return lastNo;
	}
	
	public int getNoLastInCurrentPage(List<WebElement> listNo) {
		int lastNo = 0;
		lastNo = Integer.parseInt(listNo.get(listNo.size()-1).getText());
		return lastNo;
	}
	
	public int getNoFirstInCurrentPage(List<WebElement> listNo) {
		int firstNo = 0;
		
		firstNo = Integer.parseInt(listNo.get(0).getText());
		
		return firstNo;
	}
	
	public boolean checkSearchIsCorrect(String keyword, List<List<WebElement>> actualTableMerchant) {
		boolean isCorrect = false;
		//check if there is result from search
		if(actualTableMerchant.get(3).size() <= 0) {
			isCorrect = true;
			System.out.println("No Result");
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
	public void tekan_menu_data_merchant(){
		String actual = homePage.clickAndGotoMenuDataMerchant()
		.getTitleDataMerchantPage().trim();
		
		assertEquals(actual, "Data Merchant");
	}
	
	@Test(priority = 1)
	public void input_search_merchant_name_by_nama_merchant_dan_tekan_search() {
		String keyword = "Shihlin";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 2)
	public void input_search_merchant_name_by_no_dan_tekan_search() {
		String keyword = "30";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 3)
	public void input_search_merchant_name_by_area_dan_tekan_search() {
		String keyword = "pelaza senayan";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 4)
	public void input_search_merchant_name_by_batch_dan_tekan_search() {
		String keyword = "november 2021";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	
	@Test(priority = 5)
	public void input_search_merchant_name_by_address_dan_tekan_search(){
		String keyword = "metro pondok indah";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 6)
	public void input_search_merchant_name_by_category_dan_tekan_search(){
		String keyword = "grocery";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 7)
	public void input_search_merchant_name_by_officer_dan_tekan_search(){
		String keyword = "K1133611";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 8)
	public void input_search_merchant_name_by_status_visit_dan_tekan_search(){
		String keyword = "visited";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 9)
	public void input_search_merchant_name_by_nama_merchant_dengan_spasi_di_awal_dan_akhir_kemudian_tekan_search(){
		String keyword = " shihlin ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 10)
	public void kolom_search_kosong(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableMerchant = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(keyword)
		.getTableDataMerchant();
		
		boolean isCorrect = checkSearchIsCorrect(keyword, actualTableMerchant);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 11)
	public void tekan_tombol_expand_or_compress(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int compressWidth = merchantPage.getPanelViewDataMerchant().getSize().getWidth();
		System.out.println("Compress size: "+compressWidth);
		
		merchantPage.clickBtnExpandCompress();
		
		int expandWidth = merchantPage.getPanelViewDataMerchant().getSize().getWidth();
		System.out.println("Expand size: "+expandWidth);
		
		//compress for logout
		merchantPage.clickBtnExpandCompress();
		
		assertTrue(compressWidth < expandWidth);
	}
	
	@Test(priority = 12)
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
	
	@Test(priority = 13)
	public void tekan_tombol_navigasi_previous_halaman(){
		String page = "2";
		List<WebElement> listNo = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnNumberPage(page)
		.clickBtnPrevPage()
		.getColumnNo();
		int lastNo = getNoLastInCurrentPage(listNo);
		assertTrue(lastNo > 0 && lastNo < 11);
	}
	
	@Test(priority = 14)
	public void tekan_tombol_navigasi_nomor_halaman(){
		String page = "3";
		List<WebElement> listNo = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnNumberPage(page)
		.getColumnNo();
		int lastNo = getNoLastInCurrentPage(listNo);
		assertTrue(lastNo > ( (Integer.parseInt(page)-1) *10) && lastNo <= (Integer.parseInt(page)*10) );
	}
	
	@Test(priority = 15)
	public void tekan_tombol_navigasi_next_halaman(){
		List<WebElement> listNo = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnNextPage()
		.getColumnNo();
		int lastNo = getNoLastInCurrentPage(listNo);
		assertTrue(lastNo > 10 && lastNo <= 20);
	}
	
	@Test(priority = 16)
	public void tekan_tombol_navigasi_first_halaman(){
		List<WebElement> listNo = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnLastPage()
		.clickBtnFirstPage()
		.getColumnNo();
		
		int lastNo = getNoLastInCurrentPage(listNo);
		
		assertTrue(lastNo > 0 && lastNo <= 10);
	}
	
	@Test(priority = 17)
	public void tekan_tombol_navigasi_last_halaman(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant()
		.clickBtnLastPage();
		
		List<WebElement> listNo = merchantPage.getColumnNo();
		List<WebElement> listPage = merchantPage.getListButtonForNavigate();
		
		//get last page
		int lastPage = Integer.parseInt(listPage.get(listPage.size()-1).getText());
		System.out.println("last page is: "+lastPage);
		
		int lastNo = getNoLastInCurrentPage(listNo);
		
		assertTrue(lastNo > ( (lastPage-1) * 10)&& lastNo <= (lastPage * 10));
	}
	
	@Test(priority = 18)
	public void tekan_tombol_delete_di_dari_data_merchant(){
		String search = "the duke";//using search to reduce accident to other test since this data is created in this test
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant()
		.inputAndClickSearch(search);//before delete, search data first to know how many
		
		int beforeDeleteResult = merchantPage.getColumnNo().size();
		
		merchantPage.deleteDataMerchantByIndex("1");//delete first row from search result
		
		int afterDeleteResult = merchantPage.inputAndClickSearch(search)//search again after delete
		.getColumnNo().size();
		
		assertTrue(beforeDeleteResult > afterDeleteResult, "Data tidak terdelete");
	}
	
	@Test(priority = 19)
	public void tekan_tombol_add_new_merchant(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant()
				.clickBtnAddNewMerchant();
		String actual = merchantPage.getTitlePopupAddMerchant().trim();
		
		merchantPage.clickBtnCancelAddNewMerchant();
		
		assertEquals(actual, "Form Input Merchant Baru");
	}
	
	@Test(priority = 20)
	public void input_data_valid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd < afterAdd);
	}
	
	@Test(priority = 21)
	public void input_batch_invalid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"No@34vm 1902", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 22)
	public void input_merchant_name_invalid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"!@%)#$*af!{13]$", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 23)
	public void input_address_invalid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"!@%)#$*af!{13]$", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 24)
	public void input_address_by_floor_invalid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"!@%)#$*af!{13]$", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 25)
	public void input_category_invalid_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"!@%)#$*af!{13]$", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 26)
	public void data_batch_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementBatch());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 27)
	public void data_kota_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"-- Pilih --", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 28)
	public void data_area_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"-- Pilih --", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 29)
	public void data_merchant_name_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 30)
	public void data_address_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 31)
	public void data_address_by_floor_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 32)
	public void data_category_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 33)
	public void data_users_kosong_or_tidak_pilih_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"-- Pilih --")
		.clickBtnSaveAddNewMerchant();
		boolean elementAttReqPresent = isAttrRequiredPresent(merchantPage.getElementKota());
		
		assertTrue(elementAttReqPresent);
		merchantPage.clickBtnCancelAddNewMerchant();
	}
	
	@Test(priority = 34)
	public void data_batch_panjang_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//size before add
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"NNnnnnnnnnnnnnnnnnnnnnnoooooooooooooooovvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeemmmmmmmmmmmmmmmmmmmmbbbbbbbbbbbbbbeeeeeeeeeeeeeeeerrrrrrrrrrrrrrr 222222222222222220000000000000000000002222222222222222222222222221111111111111111111111111111111", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//size after add
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 35)
	public void data_merchant_name_panjang_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//size before add
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"TTTTTTTTTTTTTTTTTTTTTTTTTtttttttttttttttttttthhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhheeeeeeeeeeeeeeeeeeeeeeeeeee DDDDDDDDDDDDDDDDDDDDdddddddddddddddddddddduuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuukkkkkkkkkkkkkkkkkkkkkkkkkkkkkkeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//size after add
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 36)
	public void data_address_panjang_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//size before add
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJjjjjjjjjjjjjjjjjjjjjjjjjjjjllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll...................              SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSsssssssssssssssssssssssssssssssssssssssssssssuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuddddddddddddddddddddddddddddddddddddddddddddddddddddddddddiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiirrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//size after add
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 37)
	public void data_address_by_floor_panjang_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//size before add
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff    Lllllllllllllllllllllllllllllllllllllaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnttttttttttttttttttttttttaaaaaaaaaaaaaaaaaaaaaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii    222222222222222222222222222222222222222222222222222222222222222222", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//size after add
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 38)
	public void data_category_panjang_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//size before add
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCccccccccccccccccaaaaaaaaaaaaaaaaaaaaaattttttttttttttttttttttttttttttteeeeeeeeeeeeeeeeeegggggggggggggggggoooooooooooooooooorrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyy               FFFFFFFFFFFFFFFFFFFFFnnnnnnnnnnnnnnnnnnnnnnnnBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		//size after add
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 39)
	public void input_data_valid_di_new_merchant_dan_cancel_or_silang(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		//get how many data from search result before add data
		int beforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		System.out.println("before add: "+beforeAdd);
		
		//add new data valid
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnCancelAddNewMerchant();
		
		//get how many data from search result after add data
		int afterAdd = getNoLastInCurrentPage(merchantPage.getColumnNo());
		System.out.println("after add: "+afterAdd);
		
		//assert
		assertTrue(beforeAdd == afterAdd);
	}
	
	@Test(priority = 40)
	public void data_batch_di_isi_white_space_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"    ", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 41)
	public void data_merchant_name_di_isi_white_space_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"     ", 
				"jl. sudirman", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 42)
	public void data_address_di_isi_white_space_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"     ", 
				"f2", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 43)
	public void data_address_by_floor_di_isi_white_space_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"     ", 
				"fnb", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 44)
	public void data_category_di_isi_white_space_di_new_merchant_dan_save(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		int sizeBeforeAdd = getNoLastInLastPage(merchantPage.getDriver());
		
		//test add
		merchantPage.clickBtnAddNewMerchant()
		.inputAllFieldAddNewMerchant(
				"November 2021", 
				"JAKARTA", 
				"Plaza Indonesia", 
				"The Duke", 
				"jl. sudirman", 
				"f2", 
				"     ", 
				"Achmad Faizal")
		.clickBtnSaveAddNewMerchant();
		
		
		int sizeAfterAdd = getNoLastInLastPage(merchantPage.getDriver());
		assertTrue(sizeBeforeAdd == sizeAfterAdd, "Data is created, should be not since it suppose to be error");
	}
	
	@Test(priority = 45)
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
	
	@Test(priority = 46)
	public void tekan_tombol_upload_data(){
		JCBDataMerchantPage merchantPage = homePage.clickAndGotoMenuDataMerchant();
		
		String actual = merchantPage
		.clickBtnUploadData()
		.getTitlePopupUpload().trim();
		
		merchantPage.clickBtnCancelPopupUpload();
		
		assertEquals(actual, "Form Upload");
	}
	
	@Test(priority = 47)
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
	
	@Test(priority = 48)
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
	
	@Test(priority = 49)
	public void tekan_choose_file_dan_pilih_file_dengan_format_valid_kemudian_pilih_cancel_di_popup_upload(){
		
		boolean isDisplayed = homePage.clickAndGotoMenuDataMerchant()
				.clickBtnUploadData()
				.selectFilePopupUpload("C:\\\\Antoni\\\\selenium\\\\MOCK_DATA_ABSENSI.xlsx")
				.clickBtnCancelPopupUpload()
				.checkTitlePopupUpload();
		assertTrue(isDisplayed == false);
	}
	
	@Test(priority = 50)
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
