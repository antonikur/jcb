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
import com.nexsoft.jcb.pom.JCBHomePage;
import com.nexsoft.jcb.pom.JCBLoginPage;
import com.nexsoft.jcb.pom.JCBMasterAreaPage;
import com.nexsoft.jcb.pom.JCBMasterKotaPage;
import com.nexsoft.jcb.pom.JCBMasterUserPage;

public class TestModulMaster {
	
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
	
	//other tools##############################################################################################################################
	//##############################################################################################################################
	public int getNoLastInLastPage(WebDriver driver) {
		int lastNo = 0;
		JCBMasterUserPage userPage = PageFactory.initElements(driver, JCBMasterUserPage.class);
		List<WebElement> listButton = userPage.getListButtonForNavigate();
		int lastPage = (listButton.size()-2);
		listButton.get(lastPage).click();
		//get no last
		List<WebElement> listNo = userPage.getColumnNo();
		lastNo = Integer.parseInt(listNo.get(listNo.size()-1).getText()) ;
		
		return lastNo;
	}
	
	public boolean checkIsCorrectSearchInUser(String keyword, List<List<WebElement>> actualTableUser) {
		boolean isCorrect = false;
		for(int i=0; i<actualTableUser.get(0).size(); i++) {//check data per row if it contains keyword
			//if no matching keyword found
			if(actualTableUser.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
				fail("No data found, make sure you use keyword that available");
				break;
			}
			//if one of the column have contain data from keyword
			else {
				
				String[] splitKeyword = keyword.trim().split(" ");
				
				for (String element : splitKeyword) {
					//if it contain in no
					if(actualTableUser.get(0).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
							// if it contain in nik
							actualTableUser.get(1).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
							// if it contain in name
							actualTableUser.get(2).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
							//if it contain in privilage
							actualTableUser.get(3).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) ) {

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
	
	public boolean checkIsCorrectSearchInKota(String keyword, List<List<WebElement>> actualTableKota) {
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
		return isCorrect;
	}
	
	public boolean checkIsCorrectSearchInArea(String keyword, List<List<WebElement>> actualTableArea) {
		boolean isCorrect = false;
		for(int i=0; i<actualTableArea.get(0).size(); i++) {//check data per row if it contains keyword
				//if no matching keyword found
				if(actualTableArea.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
					fail("No data found, make sure you use keyword that available");
				}
				//if one of the column have contain data from keyword
				else {
					String[] splitKeyword = keyword.trim().split(" ");
					
					for (String element : splitKeyword) {
						//if it contain in no
						if(actualTableArea.get(0).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
								// if it contain in kode area
								actualTableArea.get(1).get(i).getText().toLowerCase().contains(element.toLowerCase().trim()) || 
								// if it contain in area
								actualTableArea.get(2).get(i).getText().toLowerCase().contains(element.toLowerCase().trim())  ) {
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
		JCBMasterUserPage userPage = PageFactory.initElements(driver, JCBMasterUserPage.class);
		List<WebElement> listButton = userPage.getListButtonForNavigate();
		int lastPage = (listButton.size()-2);
		listButton.get(lastPage).click();
	}
	//##############################################################################################################################
	//##############################################################################################################################
	@Test(priority = 0)
	public void tekan_menu_master_user(){
		String actual = homePage.clickAndGotoMenuMasterUser()
				.getTitleUserPage().trim();
		
		assertEquals(actual, "Data User");
	}
	
	@Test(priority = 1)
	public void input_kolom_search_by_no_di_user(){
		
		String keyword = "9";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
				.inputFieldSearch(keyword)
				.getTableDataUser();
		
		boolean isCorrect = checkIsCorrectSearchInUser(keyword, actualTableUser);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
		

		
	}
	
	@Test(priority = 2)
	public void input_kolom_search_by_nik(){
		
		String keyword = "k1136039";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
				.inputFieldSearch(keyword)
				.getTableDataUser();
		
		boolean isCorrect = checkIsCorrectSearchInUser(keyword, actualTableUser);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 3)
	public void input_kolom_search_by_name(){
		String keyword = "toni";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
				.inputFieldSearch(keyword)
				.getTableDataUser();
		
		boolean isCorrect = checkIsCorrectSearchInUser(keyword, actualTableUser);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 4)
	public void input_kolom_search_by_privilage(){

		String keyword = "md";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
				.inputFieldSearch(keyword)
				.getTableDataUser();
		
		boolean isCorrect = checkIsCorrectSearchInUser(keyword, actualTableUser);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 5)
	public void input_kolom_search_by_nik_dan_tambah_spasi_di_awal_dan_akhir_keyword(){
		String keyword = " k1136039 ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableUser = homePage.clickAndGotoMenuMasterUser()
				.inputFieldSearch(keyword)
				.getTableDataUser();
		
		boolean isCorrect = checkIsCorrectSearchInUser(keyword, actualTableUser);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 6)
	public void kolom_search_kosong_di_user(){
		//first look data size while no search
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		int sizeNoSearch = getNoLastInLastPage(userPage.getDriver());
		
		//then simulate search and make it blank again
		userPage.inputFieldSearch("test");
		//blank the search by using space to trigger it search
		userPage.inputFieldSearch(" ");
		
		//the get again
		int sizeSearchAfterBlankSearch = getNoLastInLastPage(userPage.getDriver());
		
		//search result should be same
		assertTrue(sizeNoSearch == sizeSearchAfterBlankSearch);
	}
	
	@Test(priority = 7)
	public void pilih_show_entries_10_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
				.selectDropdownListEntriesByValue("10")
				.getColumnNo();
				int entriesSize = kolomNo.size();
				
				assertEquals(entriesSize, 10, "kemungkinan karena data tidak mencukupi 10, tolong dicek dan ditambah");
	}
	
	@Test(priority = 8)
	public void pilih_show_entries_25_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
				.selectDropdownListEntriesByValue("25")
				.getColumnNo();
				int entriesSize = kolomNo.size();
				
				assertEquals(entriesSize, 25, "kemungkinan karena data tidak mencukupi 25, tolong dicek dan ditambah");
	}
	
	@Test(priority = 9)
	public void pilih_show_entries_50_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
		.selectDropdownListEntriesByValue("50")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 50, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 10)
	public void pilih_show_entries_100_di_user(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterUser()
		.selectDropdownListEntriesByValue("100")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 100, "kemungkinan karena data tidak mencukupi 100, tolong dicek dan ditambah");
	}
	
	@Test(priority = 11)
	public void tekan_tombol_expand_or_compress_di_user(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();

		//when compress
		int sizeWidthCompress = userPage.getElementPanelViewDataUser().getSize().getWidth();
		System.out.println("compress width: "+sizeWidthCompress);
		
		//click to expand
		userPage.clickBtnExpandCompress();
		
		//when expand
		int sizeWidthExpand = userPage.getElementPanelViewDataUser().getSize().getWidth();
		System.out.println("expand width: "+sizeWidthExpand);
		
		//click to compress
		userPage.clickBtnExpandCompress();
		
		assertTrue(sizeWidthCompress < sizeWidthExpand);
	}
	
	@Test(priority = 12)
	public void tekan_tombol_collapse_or_expand_di_user(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		
		//if displayed then true
		boolean isDiplayed = userPage.getElementPanelListTableUser().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		userPage.clickBtnCollapseExpand();
		
		//if hidden then true
		boolean isHidden = !userPage.getElementPanelListTableUser().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 13)
	public void tekan_tombol_navigasi_previous_halaman_di_user(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		userPage.clickPageNumber("2");
		
		userPage.clickPreviousPage();
		
		int noLast = getNoLastInPage(userPage.getColumnNo());
		assertTrue(noLast == 10);
		
	}
	
	@Test(priority = 14)
	public void tekan_tombol_navigasi_nomor_halaman_di_user(){
		int pageNo = 3;
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		userPage.clickPageNumber(Integer.toString(pageNo));
		
		int noLast = getNoLastInPage(userPage.getColumnNo());
		assertTrue(noLast == (pageNo*10));
		
	}
	
	@Test(priority = 15)
	public void tekan_tombol_navigasi_next_halaman_di_user(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		userPage.clickNextPage();
		
		int noLast = getNoLastInPage(userPage.getColumnNo());
		assertTrue(noLast == 20);
	}
	
	@Test(priority = 16)
	public void tekan_tombol_add_new_user(){
		String titlePopupNewUser = homePage.clickAndGotoMenuMasterUser()
		.clickAddNewUser()
		.getTitlePopupNewUser().trim();
		
		//for logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickBtnCancel();
		
		assertEquals(titlePopupNewUser, "Form Input User");
	}
	
	@Test(priority = 17)
	public void input_data_valid_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//data is increased because data success added
		assertTrue(sizeBeforeAddData < sizeAfterAddData, "Data should be increased because data success created");
	}
	
	@Test(priority = 18)
	public void input_data_nik_invalid_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("&@^$%!#ad#$!134#", "kur nia", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 19)
	public void input_data_name_invalid_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "&@^$%!#ad#$!134#", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 20)
	public void input_data_username_invalid_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "&@^$%!#ad#$!134#", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 21)
	public void data_nik_kosong_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("", "kur nia", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 22)
	public void data_name_kosong_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 23)
	public void data_username_kosong_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 24)
	public void data_password_kosong_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 25)
	public void data_privilage_pilih_default_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "123@!$4ad5", "")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 26)
	public void data_nik_panjang_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1231231231231312313122413413412312312312312312313123131224134134123123123123123123131231312241341341231231231231231231312313122413413412312312312312312313123131224134134123123123123123123131231312241341341231231231231231231312313122413413412312312312312312313123131224134134123123452434", 
						"kur nia", "kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 27)
	public void data_name_panjang_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", 
						"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
						"kur_niawan", "123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 28)
	public void data_username_panjang_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", 
						"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa_aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
						"123@!$4ad5", "4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 29)
	public void data_password_panjang_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", 
						"aaaa@aaaaaaaaaaaaaaa6aaaaaaaaaaaaaaaaaaaaaaa4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa8aaaaaaaaa2aaaaaaaaaa2aaaaaaaaaaaaaaaaaa5aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa$aaaaaaaa9aaaa", 
						"4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 30)
	public void data_nik_di_isi_white_space_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("     ", "kur nia", "kur_niawan", "123@!$4ad5","4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 31)
	public void data_name_di_isi_white_space_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "     ", "kur_niawan", "123@!$4ad5","4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 32)
	public void data_username_di_isi_white_space_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "     ", "123@!$4ad5","4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 33)
	public void data_pass_di_isi_white_space_di_new_user_dan_save(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "     ","4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 34)
	public void input_data_valid_di_new_user_dan_cancel(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "123@!$4ad5","4")
				.clickBtnCancel();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since there is no new data created");
	}
	
	@Test(priority = 35)
	public void input_atau_buat_data_valid_yang_sama_persisi(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(userPage.getDriver());
		
		userPage.clickAddNewUser()
				.inputAllAddUserField("K1234654", "kur nia", "kur_niawan", "123@!$4ad5","4")
				.clickBtnSave();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(userPage.getDriver());
		//should be same sine its was error and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 36)
	public void tekan_tombol_edit_pada_baris_user(){
		String actual = homePage.clickAndGotoMenuMasterUser()
		.clickEditUserByNo("5")
		.getTitlePopupEditUser();
		
		//for logout
		PageFactory.initElements(driver, JCBMasterUserPage.class)
		.clickBtnCancelPopupEdit();
		
		assertEquals(actual, "Form Edit User");
	}
	
	@Test(priority = 37)
	public void tekan_tombol_edit_pada_baris_user_ketika_kotak_view_data_user_di_expand(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		userPage.clickBtnExpandCompress();
		userPage.clickEditUserByIndexWhenExpand("1");
		
		boolean titleIsDisplayed = userPage.getElementTitlePopupEditUser().isDisplayed();
		System.out.println(titleIsDisplayed);
		
		//for logout
		userPage.clickBtnExpandCompress();
		userPage.clickBtnCancelPopupEdit();
		
		assertTrue(titleIsDisplayed, "Popup is not displayed");
	}
	
	@Test(priority = 38)
	public void ubah_data_user_di_pop_up_edit_dan_tekan_save(){
		fail("No field in edit popup user");
	}
	
	@Test(priority = 39)
	public void ubah_data_user_di_pop_up_edit_dan_tekan_cancel_or_silang_di_user(){
		fail("No field in edit popup user");
	}
	
	@Test(priority = 40)
	public void tekan_tombol_delete_pada_baris_user(){
		
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		int sizeBeforeDelete = getNoLastInLastPage(driver);
		
		//delete
		String lastNo = Integer.toString(getNoLastInPage(userPage.getColumnNo()));
		userPage.clickDeleteUserByNo(lastNo);
		
		int sizeAfterDelete = getNoLastInLastPage(driver);
		assertTrue(sizeBeforeDelete > sizeAfterDelete);
	}
	
	@Test(priority = 41)
	public void tekan_tombol_delete_pada_baris_user_ketika_kotak_view_data_user_di_expand(){
		JCBMasterUserPage userPage = homePage.clickAndGotoMenuMasterUser();
		int sizeBeforeDelete = getNoLastInLastPage(userPage.getDriver());
		
		//delete when expand
		int lastNo = getNoLastInPage(userPage.getColumnNo());
		String index = Integer.toString(lastNo % 10);
		//if last no is round 10, then % will make it 0,
		//therefore if its then set index to 10
		if(Integer.parseInt(index) == 0) {
			index = "10";
		}
		
		userPage.clickBtnExpandCompress();
		userPage.clickDeleteUserByIndexWhenExpand(index);
		
		int sizeAfterDelete = getNoLastInLastPage(userPage.getDriver());
				
		//assert
		assertTrue(sizeBeforeDelete > sizeAfterDelete);
		
	}
	
	@Test(priority = 42)
	public void tekan_menu_master_kota(){
		String actual = homePage.clickAndGotoMenuMasterKota()
		.getTitleKotaPage().trim();
		
		
		assertEquals(actual, "Data Kota");
	}
	
	@Test(priority = 43)
	public void input_kolom_search_by_no_di_kota(){
		String keyword = "12";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableKota = homePage.clickAndGotoMenuMasterKota()
		.inputFieldSearch(keyword)
		.getTableDataKota();
		
		boolean isCorrect = checkIsCorrectSearchInKota(keyword, actualTableKota);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 44)
	public void input_kolom_search_by_kota(){
		String keyword = "jakarta";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableKota = homePage.clickAndGotoMenuMasterKota()
		.inputFieldSearch(keyword)
		.getTableDataKota();
		
		boolean isCorrect = checkIsCorrectSearchInKota(keyword, actualTableKota);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 45)
	public void input_kolom_search_by_kota_dan_tambah_spasi_di_awal_dan_akhir_keyword(){
		String keyword = " jakarta ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableKota = homePage.clickAndGotoMenuMasterKota()
		.inputFieldSearch(keyword)
		.getTableDataKota();
		
		boolean isCorrect = checkIsCorrectSearchInKota(keyword, actualTableKota);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 46)
	public void kolom_search_kosong_di_kota(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableKota = homePage.clickAndGotoMenuMasterKota()
		.inputFieldSearch(keyword)
		.getTableDataKota();
		
		boolean isCorrect = checkIsCorrectSearchInKota(keyword, actualTableKota);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 47)
	public void pilih_show_entries_10_di_kota(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterKota()
		.selectDropdownListEntriesByValue("10")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 10, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 48)
	public void pilih_show_entries_25_di_kota(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterKota()
		.selectDropdownListEntriesByValue("25")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 25, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 49)
	public void pilih_show_entries_50_di_kota(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterKota()
		.selectDropdownListEntriesByValue("50")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 50, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 50)
	public void pilih_show_entries_100_di_kota(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterKota()
		.selectDropdownListEntriesByValue("100")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 100, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 51)
	public void tekan_tombol_expand_or_compress_di_kota(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();

		//when compress
		int sizeWidthCompress = kotaPage.getElementPanelViewDataKota().getSize().getWidth();
		System.out.println("compress width: "+sizeWidthCompress);
		
		//click to expand
		kotaPage.clickBtnExpandCompress();
		
		//when expand
		int sizeWidthExpand = kotaPage.getElementPanelViewDataKota().getSize().getWidth();
		System.out.println("expand width: "+sizeWidthExpand);
		
		//click to compress
		kotaPage.clickBtnExpandCompress();
		
		assertTrue(sizeWidthCompress < sizeWidthExpand);
	}
	
	@Test(priority = 52)
	public void tekan_tombol_collapse_or_expand_di_kota(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//if displayed then true
		boolean isDiplayed = kotaPage.getElementPanelListTableKota().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		kotaPage.clickBtnCollapseExpand();
		
		//if hidden then true
		boolean isHidden = !kotaPage.getElementPanelListTableKota().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 53)
	public void tekan_tombol_navigasi_previous_halaman_di_kota(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		kotaPage.clickPageNumber("2");
		
		kotaPage.clickPreviousPage();
		
		int noLast = getNoLastInPage(kotaPage.getColumnNo());
		assertTrue(noLast == 10);
	}
	
	@Test(priority = 54)
	public void tekan_tombol_navigasi_nomor_halaman_di_kota(){
		int pageNo = 3;
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		kotaPage.clickPageNumber(Integer.toString(pageNo));
		
		int noLast = getNoLastInPage(kotaPage.getColumnNo());
		assertTrue(noLast == (pageNo*10));
	}
	
	@Test(priority = 55)
	public void tekan_tombol_navigasi_next_halaman_di_kota(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		kotaPage.clickNextPage();
		
		int noLast = getNoLastInPage(kotaPage.getColumnNo());
		assertTrue(noLast == 20);
	}
	
	
	
	@Test(priority = 56)
	public void tekan_tombol_add_new_kota(){
		String actual = homePage.clickAndGotoMenuMasterKota()
		.clickAddNewKota()
		.getTitlePopupAddKota().trim();
		assertEquals(actual, "Data Kota");
		
	}
	
	@Test(priority = 57)
	public void input_data_kota_valid_dan_save(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("Kota Pekanbaru")
		.clickBtnSavePopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		//should be increased since its was valid and new data created
		assertTrue(sizeBeforeAddData < sizeAfterAddData, "No new data created");
	}
	
	@Test(priority = 58)
	public void input_data_kota_invalid_dan_save(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("kota !@%)#$*af!{13]$")
		.clickBtnSavePopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");		
	}
	
	@Test(priority = 59)
	public void input_data_kota_panjang_dan_save(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("kkkkkkkkkkoooooooootttttttttaaaaaaaaaaaa         ppppppppppppppppeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnnbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaarrrrrrrrrrrrrrrrrrrrrrrrrruuuuuuuuuuuuu")
		.clickBtnSavePopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 60)
	public void data_kota_kosong_dan_save(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("")
		.clickBtnSavePopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 61)
	public void data_kota_di_isi_white_space_dan_save(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("     ")
		.clickBtnSavePopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 62)
	public void input_data_kota_valid_dan_cancel(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(kotaPage.getDriver());
		
		kotaPage.clickAddNewKota()
		.inputFieldPopupAddNewKota("kota pekanbaru")
		.clickBtnCancelPopupAddKota();
		
		//after add
		int sizeAfterAddData = getNoLastInPage(kotaPage.getColumnNo());
		
		//should be same since no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was canceled");
	}
	
	@Test(priority = 63)
	public void tekan_tombol_edit_pada_baris_kota(){
		
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota()
				.clickEditKotaByIndex("1");
		String actual =	kotaPage.getTitlePopupEditKota().trim();
		kotaPage.clickCancelEditKota();
		
		assertEquals(actual, "Form Update Kota");
	}
	
	@Test(priority = 64)
	public void tekan_tombol_edit_pada_baris_kota_ketika_kotak_view_data_kota_di_expand(){
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		kotaPage.clickBtnExpandCompress();
		kotaPage.clickEditKotaByIndexWhenExpand("1");
		
		boolean titleIsDisplayed = kotaPage.getElementTitlePopupEditKota().isDisplayed();
		System.out.println(titleIsDisplayed);
		
		//for logout
		kotaPage.clickBtnExpandCompress();
		kotaPage.clickBtnCancelPopupEdit();
		
		assertTrue(titleIsDisplayed, "Popup is not displayed");
	}
	
	@Test(priority = 65)
	public void ubah_data_kota_di_pop_up_edit_dan_tekan_save(){
		String kota = "Kota Batam";//for search and edit value, use data that haven't reach 100 or 99
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		List<WebElement> resultBefore = kotaPage
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(kota)
		.getColumnNo();
		
		int beforeSize = countSizeNo(resultBefore);
		
		//clear field search
		kotaPage.inputFieldSearch(" ");//need to put space so the search will process
		
		List<WebElement> resultAfter = kotaPage.clickEditKotaByIndex("1")
		.inputFieldPopupEditKota(kota)
		.clickSaveEditKota()
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(kota)
		.getColumnNo();
		
		int afterSize = countSizeNo(resultAfter);
		
		assertTrue(afterSize > beforeSize, "Data not changed");
	}
	
	@Test(priority = 66)
	public void ubah_data_kota_di_pop_up_edit_dan_tekan_cancel_or_silang(){
		String kota = "Kota Batam";//for search and edit value, use data that haven't reach 100 or 99
		JCBMasterKotaPage kotaPage = homePage.clickAndGotoMenuMasterKota();
		
		List<WebElement> resultBefore = kotaPage
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(kota)
		.getColumnNo();
		
		int beforeSize = countSizeNo(resultBefore);
		
		//clear field search
		kotaPage.inputFieldSearch(" ");//need to put space so the search will process
		
		List<WebElement> resultAfter = kotaPage.clickEditKotaByIndex("1")
		.inputFieldPopupEditKota(kota)
		.clickCancelEditKota()
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(kota)
		.getColumnNo();
		
		int afterSize = countSizeNo(resultAfter);
		
		assertTrue(afterSize == beforeSize, "Data is changed");
	}
	
	@Test(priority = 67)
	public void tekan_menu_master_area(){
		String actual = homePage.clickAndGotoMenuMasterArea()
				.getTitleAreaPage().trim();
		
		assertEquals(actual, "Data Area");
	}
	
	@Test(priority = 68)
	public void input_kolom_search_by_no_di_area(){
		String keyword = "70";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableArea= homePage.clickAndGotoMenuMasterArea()
		.inputFieldSearch(keyword)
		.getTableDataArea();
		
		boolean isCorrect = checkIsCorrectSearchInArea(keyword, actualTableArea);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 69)
	public void input_kolom_search_by_kode_area(){
		String keyword = "120";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableArea= homePage.clickAndGotoMenuMasterArea()
		.inputFieldSearch(keyword)
		.getTableDataArea();
		
		boolean isCorrect = checkIsCorrectSearchInArea(keyword, actualTableArea);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 70)
	public void input_kolom_search_by_area(){
		String keyword = "aeon tanjung";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableArea= homePage.clickAndGotoMenuMasterArea()
		.inputFieldSearch(keyword)
		.getTableDataArea();
		
		boolean isCorrect = checkIsCorrectSearchInArea(keyword, actualTableArea);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 71)
	public void input_kolom_search_by_area_dan_tambah_spasi_di_awal_dan_akhir_keyword(){
		String keyword = " aeon tanjung ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableArea= homePage.clickAndGotoMenuMasterArea()
		.inputFieldSearch(keyword)
		.getTableDataArea();
		
		boolean isCorrect = checkIsCorrectSearchInArea(keyword, actualTableArea);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 72)
	public void kolom_search_kosong_di_area(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableArea= homePage.clickAndGotoMenuMasterArea()
		.inputFieldSearch(keyword)
		.getTableDataArea();
		
		boolean isCorrect = checkIsCorrectSearchInArea(keyword, actualTableArea);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 73)
	public void pilih_show_entries_10_di_area(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterArea()
		.selectDropdownListEntriesByValue("10")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 10, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");		
	}
	
	@Test(priority = 74)
	public void pilih_show_entries_25_di_area(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterArea()
		.selectDropdownListEntriesByValue("25")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 25, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 75)
	public void pilih_show_entries_50_di_area(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterArea()
		.selectDropdownListEntriesByValue("50")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 50, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 76)
	public void pilih_show_entries_100_di_area(){
		List<WebElement> kolomNo = homePage.clickAndGotoMenuMasterArea()
		.selectDropdownListEntriesByValue("100")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 100, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 77)
	public void tekan_tombol_expand_or_compress_di_area(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();

		//when compress
		int sizeWidthCompress = areaPage.getElementPanelViewDataArea().getSize().getWidth();
		System.out.println("compress width: "+sizeWidthCompress);
		
		//click to expand
		areaPage.clickBtnExpandCompress();
		
		//when expand
		int sizeWidthExpand = areaPage.getElementPanelViewDataArea().getSize().getWidth();
		System.out.println("expand width: "+sizeWidthExpand);
		
		//click to compress
		areaPage.clickBtnExpandCompress();
		
		assertTrue(sizeWidthCompress < sizeWidthExpand);
	}
	
	@Test(priority = 78)
	public void tekan_tombol_collapse_or_expand_di_area(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//if displayed then true
		boolean isDiplayed = areaPage.getElementPanelListTableArea().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		areaPage.clickBtnCollapseExpand();
		
		//if hidden then true
		boolean isHidden = !areaPage.getElementPanelListTableArea().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 79)
	public void tekan_tombol_navigasi_previous_halaman_di_area(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		areaPage.clickPageNumber("2");
		
		areaPage.clickPreviousPage();
		
		int noLast = getNoLastInPage(areaPage.getColumnNo());
		assertTrue(noLast == 10);
	}
	
	@Test(priority = 80)
	public void tekan_tombol_navigasi_nomor_halaman_di_area(){
		int pageNo = 3;
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		areaPage.clickPageNumber(Integer.toString(pageNo));
		
		int noLast = getNoLastInPage(areaPage.getColumnNo());
		assertTrue(noLast == (pageNo*10));
	}
	
	@Test(priority = 81)
	public void tekan_tombol_navigasi_next_halaman_di_area(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		areaPage.clickNextPage();
		
		int noLast = getNoLastInPage(areaPage.getColumnNo());
		assertTrue(noLast == 20);
	}
	
	@Test(priority = 82)
	public void tekan_tombol_add_new_area(){
		String actual = homePage.clickAndGotoMenuMasterArea()
				.clickBtnAddNewArea()
				.getTitlePopupAddArea().trim();
		assertEquals(actual, "Form Input Area Baru");
	}
	
	@Test(priority = 83)
	public void input_data_area_valid_dan_save(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(areaPage.getDriver());
		
		areaPage.clickBtnAddNewArea()
		.inputFieldPopupNewArea("Ace Software Bekasi Barat")
		.clickSavePopupNewArea();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(areaPage.getDriver());
		
		//should be increased since its was valid and new data created
		assertTrue(sizeBeforeAddData < sizeAfterAddData, "No new data created");
		
	}
	
	@Test(priority = 84)
	public void input_data_area_invalid_dan_save(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(areaPage.getDriver());
		
		areaPage.clickBtnAddNewArea()
		.inputFieldPopupNewArea("Ace !@%)#$* Bekasi af!{13]$")
		.clickSavePopupNewArea();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(areaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 85)
	public void input_data_area_panjang_dan_save(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(areaPage.getDriver());
		
		areaPage.clickBtnAddNewArea()
		.inputFieldPopupNewArea("AAAAAAaaaaaaaaaaaaaaaaaaaaaaccccccccccccceeeeeeeeeeeeeeeeeeeeeeeee SSSSsssssssssooooooooooooooffffffffffffftttttttttttttwwwwwwwwwwaaaaaaaaaaaaaarrrrrrrrrrrrrreeeeeeeeeeeee BBBBBbbbbbbbbbbbbeeeeeekkkkkkkkkaaaaaaaaaaaassssssssssiiiiiiiiiiii BBBBBBbbbbbbbbbbbaaaaaaarrrrrrraaaaaaaaaaaaaaatttttttttttttt")
		.clickSavePopupNewArea();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(areaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 86)
	public void data_area_di_isi_white_space_dan_save(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(areaPage.getDriver());
		
		areaPage.clickBtnAddNewArea()
		.inputFieldPopupNewArea("     ")
		.clickSavePopupNewArea();
		
		//after add
		int sizeAfterAddData = getNoLastInLastPage(areaPage.getDriver());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 87)
	public void input_data_area_valid_dan_cancel(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		//before add
		int sizeBeforeAddData = getNoLastInLastPage(areaPage.getDriver());
		
		areaPage.clickBtnAddNewArea()
		.inputFieldPopupNewArea("Ace Software Bekasi Barat")
		.clickCancelPopupNewArea();
		
		//after add
		int sizeAfterAddData = getNoLastInPage(areaPage.getColumnNo());
		
		//should be same since its was invalid and no new data created
		assertTrue(sizeBeforeAddData == sizeAfterAddData, "Data amount should be same since its was error and no new data created");
	}
	
	@Test(priority = 88)
	public void tekan_tombol_edit_pada_baris_area(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea()
				.clickEditAreaByIndex("1");
		String actual =	areaPage.getTitlePopupEditArea().trim();
		areaPage.clickCancelPopupEditArea();
		
		assertEquals(actual, "Form Update Area");
	}
	
	@Test(priority = 89)
	public void tekan_tombol_edit_pada_baris_area_ketika_kotak_view_data_area_di_expand(){
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		areaPage.clickBtnExpandCompress();
		areaPage.clickEditKotaByIndexWhenExpand("1");
		
		boolean titleIsDisplayed = areaPage.getElementTitlePopupEditArea().isDisplayed();
		System.out.println(titleIsDisplayed);
		
		//for logout
		areaPage.clickBtnExpandCompress();
		areaPage.clickBtnCancelPopupEdit();
		
		assertTrue(titleIsDisplayed, "Popup is not displayed");
	}
	
	@Test(priority = 90)
	public void ubah_data_area_di_pop_up_edit_dan_tekan_save(){
		String area = "Area 51";//for search and edit value, use data that haven't reach 100 or 99
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		List<WebElement> resultBefore = areaPage
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(area)
		.getColumnNo();
		
		int beforeSize = countSizeNo(resultBefore);
		
		//clear field search
		areaPage.inputFieldSearch(" ");//need to put space so the search will process
		
		List<WebElement> resultAfter = areaPage.clickEditAreaByIndex("1")
		.inputFieldPopupEditArea(area)
		.clickSaveEditArea()
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(area)
		.getColumnNo();
		
		int afterSize = countSizeNo(resultAfter);
		
		assertTrue(afterSize > beforeSize, "Data not changed");
	}
	
	@Test(priority = 91)
	public void ubah_data_area_di_pop_up_edit_dan_tekan_cancel_or_silang(){
		String area = "Area 51";//for search and edit value, use data that haven't reach 100 or 99
		JCBMasterAreaPage areaPage = homePage.clickAndGotoMenuMasterArea();
		
		List<WebElement> resultBefore = areaPage
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(area)
		.getColumnNo();
		
		int beforeSize = countSizeNo(resultBefore);
		
		//clear field search
		areaPage.inputFieldSearch(" ");//need to put space so the search will process
		
		List<WebElement> resultAfter = areaPage.clickEditAreaByIndex("1")
		.inputFieldPopupEditArea(area)
		.clickCancelPopupEditArea()
		.selectDropdownListEntriesByValue("100")
		.inputFieldSearch(area)
		.getColumnNo();
		
		int afterSize = countSizeNo(resultAfter);
		
		assertTrue(afterSize == beforeSize, "Data is changed");
	}
	
}
