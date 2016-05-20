package com.fastudent.shopby;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fastudent.login.TestSuiteBase;
import com.fastudent.util.TestUtil;

public class Homepage extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(suite_shopby_xls, this.getClass().getSimpleName())) {
		//	APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(suite_shopby_xls, this.getClass().getSimpleName());
}
	
	
	
	
	@Test
	public void ShopByBrand() throws InterruptedException {
		
		
		// test the runmode of current dataset
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y")) {
			skip = true;
			throw new SkipException("Runmode for test set data set to no " + count);
		}

		
		//openBrowser();
		WebDriver driver=new FirefoxDriver();
		
		driver.get("http://www.fastudent.com/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		
		
		
		//give total number of brands under the shop by
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("SHOP BY")).click();
		
		driver.findElement(By.linkText("SHOP BY"));
		driver.findElements(By.xpath(".//*[@id='amshopby-page-container']/div[1]/h1"));
		driver.findElements(By.xpath(".//*[@id='amshopby-page-container']/div[3]/div[1]/dl[1]/dt")).size();
		WebElement preschool = driver.findElement(By.className("content"));
		int no_of_imgs_colloge = preschool.findElements(By.tagName("dt")).size();
	
		System.out.println("Number of Brands under the shop by avialable ---->" + no_of_imgs_colloge);
	
		System.out.println("title-------->"+driver.getTitle());
		
		
		
	}
	
	
	
	
	
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(suite_shopby_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(suite_shopby_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(suite_shopby_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(suite_shopby_xls, "Test Cases",
					TestUtil.getRowNum(suite_shopby_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_shopby_xls, "Test Cases",
					TestUtil.getRowNum(suite_shopby_xls, this.getClass().getSimpleName()), "FAIL");

	}
}
