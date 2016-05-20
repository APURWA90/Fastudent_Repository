package com.fastudent.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;




import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.fastudent.util.ErrorUtil;
import com.fastudent.util.Xls_Reader;

public class TestBase {
	//public static Logger APP_LOGS=null;
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suite_shopby_xls=null;
	public static Xls_Reader suite_login_xls=null;
	public static Xls_Reader suite_orderplacing_xls=null;
	public static Xls_Reader suite_addproduct_xls=null;
	
	public static boolean isInitalized=false;
	public static boolean isBrowserOpened=false;
	public static Hashtable<String,String> sessionData = new Hashtable<String,String>();

	public static WebDriver driver =null;
	
	
	
	// initializing the Tests
	public void initialize() throws Exception{
		// logs
		if(!isInitalized){
	//	APP_LOGS = Logger.getLogger("devpinoyLogger");
		// config
		//APP_LOGS.debug("Loading Property files");
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//fastudent//config/config.properties");
		CONFIG.load(ip);
			
		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//fastudent//config/OR.properties");
		OR.load(ip);
		//APP_LOGS.debug("Loaded Property files successfully");
		//APP_LOGS.debug("Loading XLS Files");

		// xls file
		suite_shopby_xls= new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\fastudent\\xls\\Shop By.xlsx");
		suite_login_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//fastudent//xls//Login.xlsx");
		suite_addproduct_xls= new Xls_Reader(System.getProperty("user.dir")+"//src//com//fastudent//xls//Add Product.xlsx");
		suite_orderplacing_xls= new Xls_Reader(System.getProperty("user.dir")+"//src//com//fastudent//xls//Order Placing.xlsx");
		
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//fastudent//xls//Suite.xlsx");
		
		System.out.println((System.getProperty("user.dir")+"//src//com//fastudent//xls//Shop By.xlsx"));
		//APP_LOGS.debug("Loaded XLS Files successfully");
		isInitalized=true;
		}
		
	
	}
	// selenium RC/ Webdriver
	// open a browser if its not opened
	public void openBrowser(){
	if(!isBrowserOpened){
		if(CONFIG.getProperty("browserType").equals("MOZILLA"))
		     driver = new FirefoxDriver();
		else if (CONFIG.getProperty("browserType").equals("IE"))
			 driver = new InternetExplorerDriver();
		else if (CONFIG.getProperty("browserType").equals("CHROME"))
			 driver = new ChromeDriver();
		
	isBrowserOpened=true;
		String waitTime=CONFIG.getProperty("default_implicitWait");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
	}

	}
	
	// close browser
	public void closeBrowser(){
		driver.quit();
		isBrowserOpened=false;
	}
	
	// compare titles
	
	// compaerStrings
	// compare titles
		
	
	
	
	
	
	
	
	
	

}
