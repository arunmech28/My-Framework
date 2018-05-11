package com.mckesson.suite.OutpatientRegistration;


import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.mckesson.util.ErrorUtil;
import com.mckesson.util.TestUtil;

public class OutpatientRegistrationTest extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	private boolean acceptNextAlert = true;
	String Screenshot1;
	private StringBuffer verificationErrors = new StringBuffer();
	// Runmode of test case in a suite
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(suite_OutpatientRegistration_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(suite_OutpatientRegistration_xls, this.getClass().getSimpleName());
	}
	
	
	@Test(dataProvider = "getTestData")
	public void OutpatientRegistration(String FirstName,String LastName,String EmailAddress,String UserID,String Password,String VerifyPassword,String Outpatient_Phone_Number,String Outpatient_Fax_Number,String Outpatient_Pharmacy_Zip_Code,String Outpatient_National_Provider_Identifier,String Outpatient_NCPDP,String Outpatient_DEA_Registration_Number,String Outpatient_Pharmacy_Name,String Outpatient_Street_Address1,String Outpatient_City,String Outpatient_State,String Outpatient_Zip_Code,String Outpatient_pharmacyPhoneNumber,String Outpatinet_Signature,String  Outpatient_Date ) throws IOException{
		APP_LOGS.debug("Starting the Outpatient Registration  test");
		openBrowser();
	    driver.get(CONFIG.getProperty("testSiteName"));
		getObject("Create_My_Account").click();
		APP_LOGS.debug("Entering the Outpatientregistration parameters");
		
		getObject("Outpatient_First_Name1").sendKeys(FirstName);
		getObject("Outpatient_Last_Name1").sendKeys(LastName);
		getObject("Outpatient_Email_Address1").sendKeys(EmailAddress);
		getObject("Outpatient_Already_enrolled_No1").click();
		getObject("Outpatient_User_ID1").sendKeys(UserID);
		getObject("Outpatient_Password1").sendKeys(Password);
		getObject("Outpatient_Verify_Password1").sendKeys(VerifyPassword);
		getObject("Outpatient_Create_My_Account1").click();
		getObject("Outpatient_Pharmacy1").click();
		getObject("Outpatient_Continue5").click();
		//getObject("Outpatient_Authorized_Pharmacist1").click();
		//getObject("Outpatient_Continue1").click();
		//driver.findElement(By.cssSelector("#createAccountDiv > button.pinkButtonFinger")).click();
	    //driver.findElement(By.id("pharmacy")).click();
		capturescreenshot(Screenshot1);
		driver.findElement(By.id("outpatientauthorizedpharmacist")).click();
	    driver.findElement(By.cssSelector("#confirmDiv > button.pinkButtonFinger")).click();
		acceptNextAlert = false;
	    driver.findElement(By.xpath("//button[@onclick='dijit.byId(cpId).successCallback();']")).click();
	    getObject("Outpatient_Phone_Number2").sendKeys(Outpatient_Phone_Number);
	    getObject("Outpatient_Fax_Number2").sendKeys(Outpatient_Fax_Number);
	    
	    driver.findElement(By.xpath("//*[@id='widget_preferredContactMethodCode']/div/div[1]")).click();
	    driver.findElement(By.xpath("//*[@id='preferredContactMethodCode_popup1']")).click();
	    //driver.findElement(By.xpath("//div[@id='widget_preferredContactMethodCode']/div/div/div")).click();
	    getObject("Outpatient_Pharmacy_Zip_Code2").sendKeys(Outpatient_Pharmacy_Zip_Code);
	    getObject("Outpatient_National_Provider_Identifier2").sendKeys(Outpatient_National_Provider_Identifier);
	    driver.findElement(By.id("addNpiButton")).click();
	    //capturescreenshot(Screenshot1);
	    getObject("Outpatient_NCPDP2").sendKeys(Outpatient_NCPDP);
	    driver.findElement(By.id("addNcpdpButton")).click();
	    getObject("Outpatient_DEA_Registration_Number2").sendKeys(Outpatient_DEA_Registration_Number);
	    driver.findElement(By.id("addDeaButton")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    getObject("Outpatient_Pharmacy_Name2").sendKeys(Outpatient_Pharmacy_Name);
	    //capturescreenshot(Screenshot1);
	   
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    getObject("Outpatient_Street_Address5").sendKeys(Outpatient_Street_Address1);
	    getObject("Outpatient_City3").sendKeys(Outpatient_City);
	    getObject("Outpatient_State3").sendKeys(Outpatient_State);
	    getObject("Outpatient_Zip_Code3").sendKeys(Outpatient_Zip_Code);
	    getObject("Outpatient_pharmacyPhoneNumber3").sendKeys(Outpatient_pharmacyPhoneNumber);
	    //capturescreenshot(Screenshot1);
	   
	    driver.findElement(By.cssSelector("#enrollmentSubmitDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    //capturescreenshot(Screenshot1);
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	   // capturescreenshot(Screenshot1);
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.id("kaButton")).click();
	    driver.findElement(By.id("renderer.answer66601")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66606")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66612")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66614")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66619")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66623")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66626")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66631")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66636")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66640")).click();
	    driver.findElement(By.id("renderer.nextButton")).click();
	    driver.findElement(By.id("renderer.answer66642")).click();
	    driver.findElement(By.id("renderer.submitButton")).click();
	    driver.findElement(By.id("continueButton")).click();
	    getObject("Outpatinet_Signature3").sendKeys(Outpatinet_Signature);
	    getObject("Outpatient_Date3").sendKeys(Outpatient_Date);
	    driver.findElement(By.cssSelector("#submitAttestationDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.xpath("//button[@onclick='dijit.byId(cpId).closeDialog();']")).click();
	    driver.findElement(By.id("attestation")).click();
	    driver.findElement(By.cssSelector("#submitAttestationDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.id("enrollmentLink")).click();
		
		 
		closeBrowser();
	}
	
	@AfterMethod 
	/*private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }*/

		 /* private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }*/

		 /* private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }*/

	@DataProvider
	public Object[][] getTestData(){

		return TestUtil.getData(suite_OutpatientRegistration_xls, this.getClass().getSimpleName());
	}

	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(suite_OutpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(suite_OutpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(suite_OutpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(suite_OutpatientRegistration_xls, "Test Cases", TestUtil.getRowNum(suite_OutpatientRegistration_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_OutpatientRegistration_xls, "Test Cases", TestUtil.getRowNum(suite_OutpatientRegistration_xls,this.getClass().getSimpleName()), "FAIL");
	
	}
	
	
}