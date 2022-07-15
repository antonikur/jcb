package com.nexsoft.jcb.other;

import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;
import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.util.Calendar;
//import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.testng.Reporter;

//import io.appium.java_client.TouchAction;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.touch.offset.PointOption;

public class Tools {

	
	//Web tools ###########################################################
	public void stopForMoment() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void stopForMoment(int miliSec) {
		try {
			Thread.sleep(miliSec);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void scrollVerticalWindows(WebDriver driver, int y) {
		//positive y for going down, negative y for going up
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+y+")", "");
	}
	
	public void scrollByFindElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		stopForMoment(500);
	}
	
	public String screenShoot(WebDriver driver) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "C:\\selenium\\TestNG\\screenshoot\\Error_" + waktu + ".PNG";
		File screenshoot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshoot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String photoLog = "<img src='file://" + namaFile + "'height=\"350\" width=\"792\"/>";
		Reporter.log(photoLog);
		
		return namaFile;
	}
	
	//Android tools #########################################################
//	public void useTimeoutForSeconds(AndroidDriver driver, int seconds) {
//		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
//	}
//	
//	
//	public void screenShotOnElement(WebElement element) {
//		try {
//			File SrcFile = element.getScreenshotAs(OutputType.FILE);
//			String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//			File DestFile = new File("C:/Antoni/ScreenshotElement"+waktu+".png");
//			Files.copy(SrcFile.toPath(),DestFile.toPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void screenShotAllScreenAndroid(AndroidDriver driver) {
//		try {
//			File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//			FileUtils.copyFile(file, new File("C:/Antoni/Screenshot_Screen"+waktu+".jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void performTap(AndroidDriver driver, PointOption point) {
//		TouchAction touch = new TouchAction(driver);
//		touch.tap(point).perform();
//	}
//	
//	public void performSwipe(AndroidDriver driver, int fromX, int fromY, int toX, int toY) {
//		TouchAction dateSwipe = new TouchAction(driver);
//		dateSwipe.press(PointOption.point(fromX, fromY)).
//		waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).
//		moveTo(PointOption.point(toX, toY)).release().perform();
//	}
	
}
