package com.fastudent.addproduct;

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

public class Vocational extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	// static boolean pass=false;
	static boolean fail = false;
	static boolean skip = false;
	static boolean isTestPass = true;

	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip() {

		if (!TestUtil.isTestCaseRunnable(suite_addproduct_xls, this.getClass().getSimpleName())) {
		//	APP_LOGS.debug("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// logs
			throw new SkipException("Skipping Test Case" + this.getClass().getSimpleName() + " as runmode set to NO");// reports
		}
		// load the runmodes off the tests
		runmodes = TestUtil.getDataSetRunmodes(suite_addproduct_xls, this.getClass().getSimpleName());
}
	
	
	
	
	@Test
	public void AddProduct() throws InterruptedException {
		
		
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
		//login
driver.findElement(By.xpath(".//*[@id='head-top-nav']/li[2]")).click();
		
		driver.findElement(By.xpath(".//*[@id='head-top-nav']/li[2]/ul/li[3]/a")).click();
		
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("apurwa.singh@eduvisiontech.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456789");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		
		
		//click on vocational category and then click on product 
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div[1]/div[2]/div/div/div/div/ul/li[6]/a")).click();
		  driver.findElements(By.xpath(".//*[@id='amshopby-page-container']/div[1]/h1"));
		
		//CLICK ON PRODUCT LINK
		driver.findElement(By.xpath(".//*[@id='amshopby-page-container']/div[3]/ul/li[5]/div/div[2]/div[3]/a[2]")).click();
		//click on buy now button
	
		System.out.println("hi");
		
		driver.findElement(By.xpath(".//*[@id='finish_and_checkout']")).click();
		System.out.println("hello");
	
		
		
		
		//get shooping cart heading name
		String Message=driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div[2]/div[1]/h1")).getText();
		System.out.println("shopping cart page heading name====>"+ Message);
		//click on proceed to checkout button
		driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/ul/li/button")).click();
		//give expected deilevery date
		String Message1=driver.findElement(By.xpath(".//*[@id='aw-onestepcheckout-shipping-method']/dl/dd/ul/li")).getText();
		System.out.println("expected deilvery date ====>"+ Message1);
		driver.findElement(By.xpath(".//*[@id='p_method_cashondelivery']")).click();
		//enter coupon code
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("coupon_code")).sendKeys("FSTZEROCOD");
		
		
		
		
	}
	
	
	
	
	
	
	
	@AfterMethod
	public void reportDataSetResult() {
		if (skip)
			TestUtil.reportDataSetResult(suite_addproduct_xls, this.getClass().getSimpleName(), count + 2, "SKIP");
		else if (fail) {
			isTestPass = false;
			TestUtil.reportDataSetResult(suite_addproduct_xls, this.getClass().getSimpleName(), count + 2, "FAIL");
		} else
			TestUtil.reportDataSetResult(suite_addproduct_xls, this.getClass().getSimpleName(), count + 2, "PASS");

		skip = false;
		fail = false;

	}

	@AfterTest
	public void reportTestResult() {
		if (isTestPass)
			TestUtil.reportDataSetResult(suite_addproduct_xls, "Test Cases",
					TestUtil.getRowNum(suite_addproduct_xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_addproduct_xls, "Test Cases",
					TestUtil.getRowNum(suite_addproduct_xls, this.getClass().getSimpleName()), "FAIL");

	}

}
