package com.nexsoft.jcb.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
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

public class TestModulBucketDistribusi {
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
	
	public boolean checkSearch(String keyword, List<List<WebElement>> actualTableDistribusiData) {
		boolean isCorrect = false;
		
		if(actualTableDistribusiData.get(0).size() <= 0) {
			isCorrect = true;
		}
		
		for(int i=0; i<actualTableDistribusiData.get(0).size(); i++) {//check data per row if it contains keyword
			//assert
			//if no matching keyword found
			if(actualTableDistribusiData.get(0).get(0).getText().trim().equalsIgnoreCase("No matching records found")) {
				fail("No data found, make sure you use keyword that available");
			}
			//if one of the column have contain data from keyword
			else {
					//if it contain in no
				if(actualTableDistribusiData.get(0).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						// if it contain in nik
						actualTableDistribusiData.get(1).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						// if it contain in name
						actualTableDistribusiData.get(2).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) || 
						// if it contain in total data
						actualTableDistribusiData.get(3).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) ||
						// if it contain in data visit
						actualTableDistribusiData.get(4).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) ||
						//if it contain in data unvisit
						actualTableDistribusiData.get(5).get(i).getText().toLowerCase().contains(keyword.toLowerCase().trim()) ) { 
					isCorrect = true;
				} else {
					//if one of the row doesn't contain keyword then fail
					isCorrect = false;
					break;
				}
			}
		}
		return isCorrect;
	}		
			
	@Test(priority = 1)
	public void tekan_button_bucket(){
		String actual = homePage.clickMenuBucket()
				.getTxtDistribusi().trim();
		
		assertTrue(actual.contains("Distribusi"));

	}
	
	@Test(priority = 2)
	public void tekan_bucket_dan_bucket_distribusi(){
		String actual = homePage.clickAndGoToBucketDistribusi()
				.getTitleDistribusiPage().trim();
		
		assertEquals(actual, "Distribusi Data");
	}
	
	
	@Test(priority = 3)
	public void pilih_show_entries_25_di_distribusi_data(){
		List<WebElement> kolomNo = homePage.clickAndGoToBucketDistribusi()
		.selectDropdownListEntriesByValue("25")
		.getColumnNo();
		int entriesSize = kolomNo.size();

		assertEquals(entriesSize, 25, "kemungkinan karena data tidak mencukupi 25, tolong dicek dan ditambah");
	}
	
	@Test(priority = 4)
	public void pilih_show_entries_10_di_distribusi_data(){
		List<WebElement> kolomNo = homePage.clickAndGoToBucketDistribusi()
		.selectDropdownListEntriesByValue("10")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 10, "kemungkinan karena data tidak mencukupi 10, tolong dicek dan ditambah");
	}
	
	@Test(priority = 5)
	public void pilih_show_entries_50_di_distribusi_data(){
		List<WebElement> kolomNo = homePage.clickAndGoToBucketDistribusi()
		.selectDropdownListEntriesByValue("50")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 50, "kemungkinan karena data tidak mencukupi 50, tolong dicek dan ditambah");
	}
	
	@Test(priority = 6)
	public void pilih_show_entries_100_di_distribusi_data(){
		List<WebElement> kolomNo = homePage.clickAndGoToBucketDistribusi()
		.selectDropdownListEntriesByValue("100")
		.getColumnNo();
		int entriesSize = kolomNo.size();
		
		assertEquals(entriesSize, 100, "kemungkinan karena data tidak mencukupi 100, tolong dicek dan ditambah");
	}
	
	@Test(priority = 7)
	public void filter_Desc_Untuk_No() {

		List<WebElement> listNo = homePage.clickAndGoToBucketDistribusi().clickBtnNoDesc().getColumnNo();

		boolean isDesc = false;

		int prevNo = 0;
		for (WebElement element : listNo) {
			if (prevNo == 0) {
				prevNo = Integer.parseInt(element.getText());
				System.out.println("prevNo is: " + prevNo);
				// skip first
				continue;
			} else {
				if (prevNo > Integer.parseInt(element.getText())) {
					isDesc = true;
					prevNo = Integer.parseInt(element.getText());
					System.out.println("prevNo is: " + prevNo);
				} else {
					isDesc = false;
					break;
				}
			}
		}
		assertTrue(isDesc);
	}
		
	@Test(priority = 8)
	public void filter_Asc_Untuk_No() {
		List<WebElement> list = homePage.clickAndGoToBucketDistribusi().getColumnNo();
		boolean isAsc = false;

		int prevNo = 0;
		for (WebElement element : list) {
			if (prevNo == 0) {
				prevNo = Integer.parseInt(element.getText());
				continue;
			} else {
				if (prevNo < Integer.parseInt(element.getText())) {
					isAsc = true;
					prevNo = Integer.parseInt(element.getText());
					System.out.println("prevNo = " + prevNo);
				} else {
					isAsc = false;
					break;
				}
			}
		}
		assertTrue(isAsc);

	}
	
	@Test(priority = 9)
	public void filter_Asc_Untuk_NIK() {
			
			JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnNIKAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnNIK();
			
			boolean isAsc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  <= element.getText().compareTo(prev)) {
						isAsc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isAsc = false;
						break;
					}
				}
			}
			assertTrue(isAsc);
	
  }
	
	@Test(priority = 10)
	public void filter_Desc_Untuk_NIK() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnNIKAscDesc()
				.clickBtnNIKAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnNIK();
			
			boolean isDesc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  >= element.getText().compareTo(prev)) {
						isDesc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isDesc = false;
						break;
					}
				}
			}
			assertTrue(isDesc);
  }
	
	@Test(priority = 11)
	public void filter_Asc_Untuk_Name() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnNameAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnName();
			
			boolean isAsc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  <= element.getText().compareTo(prev)) {
						isAsc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isAsc = false;
						break;
					}
				}
			}
			assertTrue(isAsc);
  }
	
	@Test(priority = 12)
	public void filter_Desc_Untuk_Name() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnNameAscDesc()
				.clickBtnNameAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnName();
			
			boolean isDesc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					System.out.println(prev.toLowerCase().compareTo(element.getText().toLowerCase())  );
					System.out.println(element.getText().toLowerCase().compareTo(prev.toLowerCase()));
					System.out.println(prev.toLowerCase());
					System.out.println(element.getText().toLowerCase());
					if(prev.toLowerCase().compareTo(element.getText().toLowerCase()) >= element.getText().toLowerCase().compareTo(prev.toLowerCase())) {
//					if(0 <= prev.compareTo(element.getText()) ) {
						isDesc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isDesc = false;
						break;
					}
				}
			}
			assertTrue(isDesc);
  }
	
	@Test(priority = 13)
	public void filter_Asc_Untuk_Total_Data() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnTotalDataAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnTotalData();
			
			boolean isAsc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  <= element.getText().compareTo(prev)) {
						isAsc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isAsc = false;
						break;
					}
				}
			}
			assertTrue(isAsc);
  }
	
	@Test(priority = 14, enabled = true)
	public void filter_Desc_Untuk_Total_Data() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnTotalDataAscDesc()
				.clickBtnTotalDataAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnTotalData();
			
			boolean isDesc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace < 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(Integer.parseInt(prev) >= Integer.parseInt(element.getText()) ) {
						isDesc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isDesc = false;
						break;
					}
				}
			}
			assertTrue(isDesc);
  }
	
	@Test(priority = 15)
	public void filter_Asc_Untuk_Data_Visit() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnDataVisitAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnDataVisit();
			
			boolean isAsc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  <= element.getText().compareTo(prev)) {
						isAsc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isAsc = false;
						break;
					}
				}
			}
			assertTrue(isAsc);
  }
	
	@Test(priority = 16)
	public void filter_Desc_Untuk_Data_Visit() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi()
				.clickBtnDataVisitAscDesc();
	
	int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnDataVisit();
			
			boolean isDesc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  >= element.getText().compareTo(prev)) {
						isDesc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isDesc = false;
						break;
					}
				}
			}
			assertTrue(isDesc);
  }
	
	@Test(priority = 17)
	public void filter_Asc_Untuk_Data_Unvisit() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi().clickExpand()
				.clickBtnDataUnvisitAscDesc();
		
		tool.stopForMoment(3000);
	
		int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnUnvisit();
			
			boolean isAsc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("first prev is: "+prev);
					//skip first
					continue;
				} else {
						if(Integer.parseInt(prev) >= Integer.parseInt(element.getText()) ) {
							isAsc = true;
							prev = element.getText();
							System.out.println("prev is: "+prev);
					}
					else {
						isAsc = false;
						break;
					}
				}
			}
			distribusiPage.clickCompress();

			assertTrue(isAsc);
  }
	
	@Test(priority = 18)
	public void filter_Desc_Untuk_Data_Unvisit() {
		JCBBucketDistribusiPage distribusiPage = homePage.clickAndGoToBucketDistribusi().clickExpand()
				.clickBtnDataUnvisitAscDesc()
				.clickBtnDataUnvisitAscDesc();
		
		tool.stopForMoment(3000);
			
		int whiteSpace = 10 - distribusiPage.getColumnUnvisit().size();
		List<WebElement> list = distribusiPage.getColumnUnvisit();
			
			boolean isDesc = false;
			
			String prev = "";
			for(WebElement element:list) {
				if(whiteSpace > 0) {
					whiteSpace--;
					continue;
				}
				
				if(prev.length() == 0) {
					prev = element.getText();
					System.out.println("prev is: "+prev);
					//skip first
					continue;
				} else {
					if(prev.compareTo(element.getText())  >= element.getText().compareTo(prev)) {
						isDesc = true;
						prev = element.getText();
						System.out.println("prev is: "+prev);
					}
					else {
						isDesc = false;
						break;
					}
				}
			}
			
			distribusiPage.clickCompress();
			assertTrue(isDesc);
  }
	
	@Test(priority = 19)
	public void fungsi_button_next() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickBtnNext();
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 20);
		
  }
	
	@Test(priority = 20)
	public void fungsi_previous() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickBtnNext();
		distribusi.clickBtnPrevious();
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 10);
  }
	
	@Test(priority = 21)
	public void fungsi_button_1() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("1");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 10);
  }
	
	@Test(priority = 22)
	public void fungsi_button_2() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("2");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 20);
  }
	
	@Test(priority = 23)
	public void fungsi_button_3() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("3");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 30);
  }
	
	@Test(priority = 24)
	public void fungsi_button_4() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("4");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 40);
  }
	
	@Test(priority = 25)
	public void fungsi_button_5() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("5");
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 50);
  }
	
	@Test(priority = 26)
	public void fungsi_button_6() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("5");
		distribusi.clickPageNumber("6");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 60);
  }
	
	@Test(priority = 27)
	public void fungsi_button_7() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("5");
		distribusi.clickBtnNext();
		distribusi.clickPageNumber("7");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 70);
  }
	
	@Test(priority = 28)
	public void fungsi_button_8() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("5");
		distribusi.clickBtnNext();
		distribusi.clickBtnNext();
		distribusi.clickPageNumber("8");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 80);
  }
	
	@Test(priority = 29)
	public void fungsi_button_9() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("13");
		distribusi.clickPageNumber("9");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 90);
  }
	
	@Test(priority = 30)
	public void fungsi_button_10() {
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		distribusi.clickPageNumber("13");
		distribusi.clickPageNumber("10");
		
		int noLast = getNoLastInPage(distribusi.getColumnNo());
		assertTrue(noLast == 100);
  }
	
	@Test(priority = 31)
	public void fungsi_button_plus_pada_setiap_nomer_1(){
		String actual = homePage.clickAndGoToBucketDistribusi()
				.clickButtonPlus("1").getTitlePlusAction().trim();
		
		assertTrue(actual.contains("Unvisit"));
  }
	
	@Test(priority = 32)
	public void fungsi_button_mata(){
		String actual = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("2").clickAndGoToBucketDetailDistribusi()
				.getTitleDetailDistribusi().trim();
		
		assertEquals(actual, "Detail Distribusi Pilih Data");	
  }
	
	@Test(priority = 33, enabled = true)
	public void memeriksa_button_pilih_data_detail(){
		String actual = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("3").clickAndGoToBucketDetailDistribusi().clickPilihDataDetail()
				.getTitleDetailPilihData().trim();
		
		assertTrue(actual.contains("Detail Distribusi Pilih User"));
  }
	
	@Test(priority = 34, enabled = true)
	public void memeriksa_checkbox_all(){
		JCBBucketDistribusiDetail disDetailPage = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi().clickPilihDataDetail()
				.clickCheckboxAll();
		
		boolean isSelected = false;
		
		//*[@id="cssCheckbox1"]
		for(int i=0; i< disDetailPage.getListKolomNo().size(); i++ ) {
			isSelected = driver.findElement(By.xpath("//*[@id=\"cssCheckbox"+ (i+1) +"\"]")).isSelected();
			System.out.println("check box ke "+ (i+1) +" is selected: "+isSelected);
			if (isSelected == false) {
				break;
			}
		}
		assertTrue(isSelected, "One of the check box is not selected");
  }
	
	@Test(priority = 35, enabled = true)
	public void memeriksa_checkbox_1(){
		JCBBucketDistribusiDetail disDetailPage = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi().clickPilihDataDetail()
				.checkboxMerchant("1");
		
		boolean isSelected = false;
		
		//*[@id="cssCheckbox1"]
		for(int i=0; i< disDetailPage.getListKolomNo().size(); i++ ) {
			isSelected = driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).isSelected();
			System.out.println("check box ke "+ (i+1) +" is selected: "+isSelected);
			if (isSelected == false) {
				break;
			}
		}
		assertTrue(isSelected, "One of the check box is not selected");
  }
	
	@Test(priority = 36, enabled = true)
	public void memeriksa_checkbox_2(){
		JCBBucketDistribusiDetail disDetailPage = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi().clickPilihDataDetail()
		.checkboxMerchant("1").checkboxMerchant("2");

		boolean isSelected = false;
		boolean isSelected2 = false;
		
			isSelected = driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).isSelected();
			isSelected2 = driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).isSelected();

		assertTrue(isSelected && isSelected2, "One of the check box is not selected");
  }
	
	
	@Test(priority = 37, enabled = true)
	public void memeriksa_button_pilih_user_dan_button_x(){		
		//JCBBucketDistribusiDetail distdetail = homePage.clickAndGoToBucketDistribusi().clickAndGoToBucketDetailDistribusi();
		JCBBucketDistribusiDetail disdetail = PageFactory.initElements(driver, JCBBucketDistribusiDetail.class);

		String actual = 
				homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi()
				.clickPilihDataDetail()
				.checkboxMerchant("1").clickPilihUser()
				.getTitleDataUser().trim();
				tool.stopForMoment(2000);
		disdetail.btnX_pilihUser();
		
		assertTrue(actual.contains("Data Users"));
  }
	
	
	@Test(priority = 38, enabled = true)
	public void memilih_user_yang_dituju(){
		String actual = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi()
				.clickPilihDataDetail()
				.checkboxMerchant("1").clickPilihUser().pilihSurveyor("1").getTitleDistribusiData().trim();
				
		assertTrue(actual.contains("Distribusi Data Kepada"));
  }
	
	@Test(priority = 39, enabled = true)
	public void button_x_di_pilih_user(){		
		String actual = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi()
				.clickPilihDataDetail()
				.checkboxMerchant("1").clickPilihUser().btnX_pilihUser().getTitleDetailPilihData().trim();
				
		assertTrue(actual.contains("Detail Distribusi Pilih User"));
  }
	
	@Test(priority = 40, enabled = true)
	public void memeriksa_fungsi_button_process(){
		String actual = homePage.clickAndGoToBucketDistribusi().clickButtonPlus("6").clickAndGoToBucketDetailDistribusi()
				.clickPilihDataDetail()
				.checkboxMerchant("1").clickPilihUser().pilihSurveyor("1").clickBtnProcess().getTitleSuccess().trim();

		assertTrue(actual.contains("Success!"));
  }
	
	@Test(priority = 41)
	public void input_kolom_search_by_no(){
		String keyword = "101";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 42)
	public void input_kolom_search_by_nik(){
		String keyword = "K1136039";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 43)
	public void input_kolom_search_by_name_huruf_kapital(){
		String keyword = "RAMA DHANDY";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 44)
	public void input_kolom_search_by_name(){
		String keyword = "rama";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 45)
	public void input_kolom_search_by_huruf_random(){
		String keyword = "eryeghdbaj";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 46)
	public void input_kolom_search_by_simbol(){
		String keyword = "@@@@";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 47)
	public void input_kolom_search_by_total_data(){
		String keyword = "332";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 48)
	public void input_kolom_search_by_data_visit(){
		String keyword = "321";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 49)
	public void input_kolom_search_by_data_unvisit(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "5";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);

		distribusi.clickCompress();
	
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 50)
	public void input_kolom_search_with_spasi(){
		String keyword = " ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 51)
	public void input_kolom_search_tanpa_isi(){
		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 52)
	public void memeriksa_btn_expand_compress_distribusi_data(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		
		// when compress
		WebElement dimensionCompress = distribusi.getElementPanelViewDataUser();

		// press button compress expand
		int totalDimensionSizeCompress = (dimensionCompress.getSize().getHeight()
				+ dimensionCompress.getSize().getWidth());

		// when expand
		WebElement dimensionExpand = distribusi.clickExpand().getElementPanelViewDataUser();
		int totalDimensionSizeExpand = (dimensionExpand.getSize().getHeight() + dimensionExpand.getSize().getWidth());

		distribusi.clickExpand();

		assertTrue(totalDimensionSizeCompress < totalDimensionSizeExpand);
  }
	
	@Test(priority = 53)
	public void input_search_saat_expand_by_no(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "101";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 54)
	public void input_search_saat_expand_by_nik(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "K1136039";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();

		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 55)
	public void input_search_saat_expand_by_name(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "rama";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();

		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 56)
	public void input_search_saat_expand_by_simbol(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "@@@@";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
	
		distribusi.clickCompress();

		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 57)
	public void input_search_saat_expand_by_total_data(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "328";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();

		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 58)
	public void input_search_saat_expand_menggunakan_spasi(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = " ";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();
		
		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	
	@Test(priority = 59)
	public void input_search_saat_expand_tanpa_isi(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();

		String keyword = "";//input keyword that must have result/data
		
		List<List<WebElement>> actualTableDistribusiData = homePage.clickAndGoToBucketDistribusi().clickExpand()
		.input_Search(keyword)
		.getTableDistribusiData();
		
		boolean isCorrect = checkSearch(keyword, actualTableDistribusiData);
		
		distribusi.clickCompress();

		assertTrue(isCorrect, "One of the row doesn't contain data that match keyword");
	}
	@Test(priority = 60)
	public void tekan_tombol_collapse_or_expand_distribusi_data(){
		JCBBucketDistribusiPage distribusi = homePage.clickAndGoToBucketDistribusi();
		
		//if displayed then true
		boolean isDiplayed = distribusi.getElementPanelListTableUser().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		tool.stopForMoment(1000);
		distribusi.clickCollapse();
		
		//if hidden then true
		boolean isHidden = !distribusi.getElementPanelListTableUser().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}

	@Test(priority = 61)
	public void fungsi_button_assignment(){
	String actual = homePage.clickAndGoToBucketDistribusi()
	.clickAndGoToDetailAssignment().getTitleAssignment().trim();
	
	assertEquals(actual, "Data No Assignment Pilih Data");
	
  }
	
	@Test(priority = 62)
	public void fungsi_pilih_data(){
	String actual = homePage.clickAndGoToBucketDistribusi()
	.clickAndGoToDetailAssignment().clickPilihData().getTitlePilihData().trim();
	
	assertTrue(actual.contains("Data No Assignment"));
	
  }
	
	@Test(priority = 63, enabled = true)
	public void memilih_1_data_assign(){
		
	JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment().clickPilihData().clickCheckBox("1");
	
	boolean isSelected = false;
	
	for(int i=0; i< assignment.getColumnNo().size(); i++ ) {
		isSelected = driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).isSelected();
		System.out.println("check box ke "+ (i+1) +" is selected: "+isSelected);
		if (isSelected == false) {
			break;
		}
	}
	assertTrue(isSelected, "One of the check box is not selected");
	
  }
	
	@Test(priority = 64, enabled = true)
	public void memilih_2_data_assign(){
		JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment().clickPilihData()
				.clickCheckBox("1")
				.clickCheckBox("2");

				boolean isSelected = false;
				boolean isSelected2 = false;
				
					isSelected = driver.findElement(By.xpath("//*[@id=\"cssCheckbox1\"]")).isSelected();
					isSelected2 = driver.findElement(By.xpath("//*[@id=\"cssCheckbox2\"]")).isSelected();

				assertTrue(isSelected && isSelected2, "One of the check box is not selected");
		  }
	
	@Test(priority = 65)
	public void memeriksa_btn_expand_compress_assignment() {
		JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment();

		// when compress
		WebElement dimensionCompress = assignment.getElementPanelViewDataUser();

		// press button compress expand
		int totalDimensionSizeCompress = (dimensionCompress.getSize().getHeight()
				+ dimensionCompress.getSize().getWidth());

		// when expand
		WebElement dimensionExpand = assignment.clickExpand().getElementPanelViewDataUser();
		int totalDimensionSizeExpand = (dimensionExpand.getSize().getHeight() + dimensionExpand.getSize().getWidth());

		assignment.clickExpand();

		assertTrue(totalDimensionSizeCompress < totalDimensionSizeExpand);

	}
	
	@Test(priority = 66)
	public void tekan_tombol_collapse_or_expand_assignment(){
		JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment();
		tool.stopForMoment(2000);
		//if displayed then true
		boolean isDiplayed = assignment.getElementPanelListTableUser().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		tool.stopForMoment(2000);
		assignment.clickCollapse();
		
		//if hidden then true
		boolean isHidden = !assignment.getElementPanelListTableUser().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
	@Test(priority = 67, enabled = true)
	public void fungsi_pilih_user() {
		JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment();

		String actual = homePage.clickAndGoToBucketDistribusi()
		.clickAndGoToDetailAssignment().clickPilihData().clickCheckBox("2").clickPilihUser().getTitleDataUser().trim();
		
		assignment.clickXPilihUser();
	
		assertTrue(actual.contains("Data Users"));
	
  }
	
	@Test(priority = 68)
	public void button_x_pilih_user_(){
	String actual = homePage.clickAndGoToBucketDistribusi()
			.clickAndGoToDetailAssignment().clickPilihData().clickCheckBox("1").clickPilihUser().clickXPilihUser().getTitlePilihData().trim();
			
	assertTrue(actual.contains("Data No Assignment"));

  }
	
	@Test(priority = 69)
	public void memilih_user_yang_akan_di_assign(){
		String actual = homePage.clickAndGoToBucketDistribusi()
				.clickAndGoToDetailAssignment().clickPilihData().clickCheckBox("1").clickPilihUser().pilihSurveyor("1").getTextProcess().trim();
				
		assertTrue(actual.contains("Distribusi Data"));
  }
	
	@Test(priority = 70)
	public void fungsi_button_process(){
	String actual = homePage.clickAndGoToBucketDistribusi()
	.clickAndGoToDetailAssignment().clickPilihData().clickCheck1().clickPilihUser().clickPilihUserRama().clickBtnProcess().getTitleSuccess().trim();
	
	tool.stopForMoment(3000);

	assertTrue(actual.contains("Success!"));
		
//		JCBBucketDistribusiAssignment assignment = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment();
//
//		int beforeRejectSize = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment().getColumnNo().size();
//		System.out.println(beforeRejectSize);
//	
//		assignment.clickPilihData().clickCheckBox("1").clickPilihUser().pilihSurveyor("1").clickBtnProcess();
//	
//		int afterRejectSize = assignment.getColumnNo().size();
//		System.out.println(afterRejectSize);
//
//		tool.stopForMoment(2000);
//		assertTrue(beforeRejectSize > afterRejectSize);

  }
	
	@Test(priority = 71, enabled = true)
	public void memeriksa_btn_expand_compress_pilih_data() {
		JCBBucketDistribusiAssignment pilihdata = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment()
				.clickPilihData();

		// when compress
		WebElement dimensionCompress = pilihdata.getElementPanelViewPilihData();

		// press button compress expand
		int totalDimensionSizeCompress = (dimensionCompress.getSize().getHeight()
				+ dimensionCompress.getSize().getWidth());

		// when expand
		WebElement dimensionExpand = pilihdata.clickExpand().getElementPanelViewPilihData();
		int totalDimensionSizeExpand = (dimensionExpand.getSize().getHeight() + dimensionExpand.getSize().getWidth());

		pilihdata.clickExpand();

		assertTrue(totalDimensionSizeCompress < totalDimensionSizeExpand);

	}
	
	@Test(priority = 72, enabled = true)
	public void tekan_tombol_collapse_or_expand_pilih_data(){
		JCBBucketDistribusiAssignment pilihdata = homePage.clickAndGoToBucketDistribusi().clickAndGoToDetailAssignment().clickPilihData();
		tool.stopForMoment(2000);
		//if displayed then true
		boolean isDiplayed = pilihdata.getElementPanelListTableUser().isDisplayed();
		System.out.println("is displayed: "+isDiplayed);
		
		//click collapse expand
		tool.stopForMoment(2000);
		pilihdata.clickCollapse();
		
		//if hidden then true
		boolean isHidden = !pilihdata.getElementPanelListTableUser().isDisplayed();
		System.out.println("is hidden: "+isHidden);
		
		assertTrue(isDiplayed && isHidden);
	}
	
}
  
