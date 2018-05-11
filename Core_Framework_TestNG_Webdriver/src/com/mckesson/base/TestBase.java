package com.mckesson.base;

import java.io.File.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;












import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Factory;

import com.mckesson.util.ErrorUtil;
import com.mckesson.util.Xls_Reader;

public class TestBase {
	public static Logger APP_LOGS=null;
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suite_InpatientRegistration_xls=null;
	public static Xls_Reader suite_PrescriberRegistration_xls=null;
	public static Xls_Reader suite_OutpatientRegistration_xls=null;
	public static boolean isInitalized=false;
	public static boolean isBrowserOpened=false;
	public static Hashtable<String,String> sessionData = new Hashtable<String,String>();

	public static WebDriver driver =null;
	
	
	
	// initializing the Tests
	public void initialize() throws Exception{
		// logs
		if(!isInitalized){
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		// config
		APP_LOGS.debug("Loading Property files");
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//mckesson//config/config.properties");
		CONFIG.load(ip);
			
		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//com//mckesson//config/OR.properties");
		OR.load(ip);
		APP_LOGS.debug("Loaded Property files successfully");
		APP_LOGS.debug("Loading XLS Files");

		// xls file
		suite_InpatientRegistration_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//mckesson//xls//InpatientRegistration Suite.xlsx");
		suite_PrescriberRegistration_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//mckesson//xls//PrescriberRegistration Suite.xlsx");
		suite_OutpatientRegistration_xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com//mckesson//xls//OutpatientRegistration Suite.xlsx");
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\mckesson\\xls\\Suite.xlsx");
		APP_LOGS.debug("Loaded XLS Files successfully");
		isInitalized=true;
		}
		
	
	}
	
	
	// selenium RC/ Webdriver
	// open a browser if its not opened
	public void openBrowser(){
		
	if(!isBrowserOpened){
		if(CONFIG.getProperty("browserType").equals("MOZILLA")){
		 File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		 FirefoxProfile firefoxProfile = new FirefoxProfile();
		 driver= new FirefoxDriver(ffBinary,firefoxProfile);
		}
		else if (CONFIG.getProperty("browserType").equals("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Users\\e37rf8w\\Downloads\\IEDriverServer_x64_2.37.0\\IEDriverServer.exe");
		  driver = new InternetExplorerDriver();
		}
		else if (CONFIG.getProperty("browserType").equals("CHROME")){
			System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
			driver = new ChromeDriver();
		}
			
		
		isBrowserOpened=true;
		String waitTime=CONFIG.getProperty("default_implicitWait");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	}
	
	// close browser
	public void closeBrowser(){
		driver.quit();
		isBrowserOpened=false;
	}
	
	// compare titles
	public boolean compareTitle(String expectedVal){
		try{
			Assert.assertEquals(driver.getTitle() , expectedVal   );
			}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);			
				APP_LOGS.debug("Titles do not match");
				return false;
			}
		return true;
	}
	// compaerStrings
	// compare titles
		public boolean compareNumbers(int expectedVal, int actualValue){
			try{
				Assert.assertEquals(actualValue,expectedVal   );
				}catch(Throwable t){
					ErrorUtil.addVerificationFailure(t);			
					APP_LOGS.debug("Values do not match");
					return false;
				}
			return true;
		}
		
		public boolean checkElementPresence(String xpathKey){
			int count =driver.findElements(By.xpath(OR.getProperty(xpathKey))).size();
			
			try{
			Assert.assertTrue(count>0, "No element present");
			}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);			
				APP_LOGS.debug("No element present");
				return false;
			}
			return true;
		}
		// your own functions
		// getObjectByID(String id)
		
		public WebElement getObject(String xpathKey){
			
			try{
			WebElement x = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
			return x;
			}catch(Throwable t){
				// report error
				ErrorUtil.addVerificationFailure(t);			
				APP_LOGS.debug("Cannot find object with key -- " +xpathKey);
				return null;
			}
			
		}
		
		//Logging in to the Website
		public boolean login(String username, String password){
		
			String username1=CONFIG.getProperty("adminUsername");
			String password1=CONFIG.getProperty("adminPassword");
			
			return true;
		}
		
		public void logout(){
			// logout
		}
	
		
		public void capturescreenshot(String filename) throws IOException{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+filename+".jpg"));
		    

		}
	
	
	
	
	
	
	
	
	
	

}
