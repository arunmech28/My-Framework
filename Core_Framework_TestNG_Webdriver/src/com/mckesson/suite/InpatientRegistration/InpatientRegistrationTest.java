package com.mckesson.suite.InpatientRegistration;

import static org.testng.AssertJUnit.assertEquals;
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

public class InpatientRegistrationTest extends TestSuiteBase {
	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	// Runmode of test case in a suite
	
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(suite_InpatientRegistration_xls,this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(suite_InpatientRegistration_xls, this.getClass().getSimpleName());
	}
	
	
	@Test(dataProvider = "getTestData3", threadPoolSize = 20)
	public void OutpatientRegistration(String username,String password,String Inpatient_First_Name9,String Inpatient_Last_Name9,String Inpatient_Email_Address9,String Inpatient_UserID9,String Inpatient_Password9,String Inpatient_Verify_Password9,String Inpatient_Phone_Number9,String Inpatient_Fax_Number9,String Inpatient_Pharmacy_Name9,String Inpatient_Pharmacy_Zip_Code9,String dea9,String spln9,String Inpatient_addressLine9,String Inpatient_addressLine10,String Inpatient_City9,String Inpatient_stateCode9,String Inpatient_pharmacyPhoneNumber9,String Inpatient_dijit_form_ValidationTextBox_09,String Inpatient_JavaDateTextBox_09) throws IOException{
		APP_LOGS.debug("Starting the Inpatient Registration  test");
		
		openBrowser();
	    driver.get(CONFIG.getProperty("testSiteName"));
	    getObject("txtbox_username").sendKeys(username);
	    getObject("txtbox_password").sendKeys(password);
	    getObject("btn_login").click();
	    /*getObject("Create_My_Account").click();
		APP_LOGS.debug("Entering the Inpatientregistration parameters");
		
		driver.findElement(By.cssSelector("#createAccountDiv > button.pinkButtonFinger")).click();
		getObject("Inpatient_First_Name9").sendKeys(Inpatient_First_Name9);
		getObject("Inpatient_Last_Name9").sendKeys(Inpatient_Last_Name9);
		getObject("Inpatient_Email_Address9").sendKeys(Inpatient_Email_Address9);
		getObject("Inpatient_UserID9").sendKeys(Inpatient_UserID9);
		getObject("Inpatient_Password9").sendKeys(Inpatient_Password9);
		getObject("Inpatient_Verify_Password9").sendKeys(Inpatient_Verify_Password9);
		driver.findElement(By.cssSelector("#createAccountDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.id("pharmacy")).click();
	    driver.findElement(By.cssSelector("#continueDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.id("inpatientauthorizedpharmacist")).click();
	    driver.findElement(By.cssSelector("#confirmDiv > button.pinkButtonFinger")).click();
	    acceptNextAlert = false;
	    driver.findElement(By.xpath("//button[@onclick='dijit.byId(cpId).successCallback();']")).click();
	   // assertEquals("", closeAlertAndGetItsText());
	    driver.findElement(By.xpath("//div[@id='widget_professionalCredentialCode']/div/div")).click();
	    getObject("Inpatient_Phone_Number9").sendKeys(Inpatient_Phone_Number9);
	    getObject("Inpatient_Fax_Number9").sendKeys(Inpatient_Fax_Number9);
	    driver.findElement(By.xpath("//*[@id='widget_preferredContactMethodCode']/div/div[1]")).click();
	    driver.findElement(By.xpath("//*[@id='preferredContactMethodCode_popup1']")).click();
	    getObject("Inpatient_Pharmacy_Name9").sendKeys(Inpatient_Pharmacy_Name9);
	    getObject("Inpatient_Pharmacy_Zip_Code9").sendKeys(Inpatient_Pharmacy_Zip_Code9);
	    getObject("dea9").sendKeys(dea9);
	   
	    driver.findElement(By.id("addDeaButton")).click();
	    getObject("spln9").sendKeys(spln9);
	   
	    driver.findElement(By.id("addSplnButton")).click();
	    driver.findElement(By.cssSelector("td > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("button.pinkButtonFinger")).click();
	    getObject("Inpatient_addressLine9").sendKeys(Inpatient_addressLine9);
	    getObject("Inpatient_addressLine10").sendKeys(Inpatient_addressLine10);
	    getObject("Inpatient_City9").sendKeys(Inpatient_City9);
	    getObject("Inpatient_stateCode9").sendKeys(Inpatient_stateCode9);
	    getObject("Inpatient_pharmacyPhoneNumber9").sendKeys(Inpatient_pharmacyPhoneNumber9);
	    
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
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueButtonDiv > button.pinkButtonFinger")).click();
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
	    getObject("Inpatient_dijit_form_ValidationTextBox_09").sendKeys(Inpatient_dijit_form_ValidationTextBox_09);
	    getObject("Inpatient_JavaDateTextBox_09").sendKeys(Inpatient_JavaDateTextBox_09);
	    driver.findElement(By.cssSelector("#submitAttestationDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.xpath("//button[@onclick='dijit.byId(cpId).closeDialog();']")).click();
	    driver.findElement(By.id("attestation")).click();
	    driver.findElement(By.cssSelector("#submitAttestationDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.cssSelector("#continueDiv > button.pinkButtonFinger")).click();
	    driver.findElement(By.id("enrollmentLink")).click();*/
	    
	closeBrowser();
	}
	
	
	//@AfterMethod 
	private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		 /* private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
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
	public Object[][] getTestData3(){

		Object[][] data = new Object[2][2];
		data=TestUtil.getData(suite_InpatientRegistration_xls, this.getClass().getSimpleName());
		
		return TestUtil.getData(suite_InpatientRegistration_xls, this.getClass().getSimpleName());
	}

	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(suite_InpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(suite_InpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(suite_InpatientRegistration_xls, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(suite_InpatientRegistration_xls, "Test Cases", TestUtil.getRowNum(suite_InpatientRegistration_xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_InpatientRegistration_xls, "Test Cases", TestUtil.getRowNum(suite_InpatientRegistration_xls,this.getClass().getSimpleName()), "FAIL");
	
	}
	
	
}
