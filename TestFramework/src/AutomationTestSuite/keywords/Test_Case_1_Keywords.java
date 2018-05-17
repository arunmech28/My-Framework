package AutomationTestSuite.keywords;

import java.awt.AWTException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import AutomationTestSuite.Pages.LoginPage;
import AutomationTestSuite.Pages.PatientPage;
import Framework.Handler.Parameters;
import Framework.Handler.TestCaseRun;
import Framework.Utilities.ReusableLibrary;



public class Test_Case_1_Keywords extends ReusableLibrary implements TestCaseRun {


	public Test_Case_1_Keywords(Parameters params, Map<String, String> testdata) {
		super(params, testdata);
		
	}

	@Override
	public void run() throws AWTException {
		//Login
		
		justTest("LoginPage.username");
		loadurl("https://spstest.mckesson.com/MTpharma-HCP/");
		//newTest("LoginPage.username1");
		enterTxt(LoginPage.username, "username1");
		enterTxt(LoginPage.password, "password");
		click(LoginPage.signin);
		
		//Enroll Patient
		click(PatientPage.patientTab);
		waitFor(2000);
		click(PatientPage.enrollNewPatient);
		
		click(By.xpath("//label[contains(text(),'"+getValue("RequestService")+"')]/preceding-sibling::input"));
		enterTxt(PatientPage.firstName, "firstName");
		enterTxt(PatientPage.lastName, "lastName");
		selectDropdown(PatientPage.gender, "gender");	
		enterTxt(PatientPage.address1, "address1");
		enterTxt(PatientPage.city, "city");
		selectDropdown(PatientPage.state, "state");
		enterTxt(PatientPage.zipcode, "zipcode");
		enterDOB(PatientPage.patientDOB, "patientDOB");
		
		enterTxt(PatientPage.cellPhone, "cellPhone");
		click(PatientPage.next);
		click(PatientPage.popupCancel);
		
		scroll();
		//Insurance info
		click(PatientPage.fullbenifitInvestigation);
		click(PatientPage.specialDistributor);
		//PI info
		enterTxt(PatientPage.primaryInsuranceName, "primaryInsuranceName");
		enterTxt(PatientPage.cardHolderName, "cardHolderName");
		enterTxt(PatientPage.cardHolderRelation, "cardHolderRelation");
		enterTxt(PatientPage.insuranceCompanyPhone, "insuranceCompanyPhone");
		enterTxt(PatientPage.policyNumber, "policyNumber");
		enterTxt(PatientPage.groupNumber, "groupNumber");
		click(PatientPage.insuranceInfo_next);
		click(PatientPage.patientFinancialInfo_next);
		
		//Prescriber Info
		selectDropdown(PatientPage.practiceLocation, "practiceLocation");
		selectDropdown(PatientPage.healthCareProvider, "healthCareProvider");
		click(PatientPage.selectProviderContact);
		click(PatientPage.hcpAddress);
		click(PatientPage.hcpPhone);
		click(PatientPage.hcpFax);
		click(PatientPage.hcp_next);
		selectDropdown(PatientPage.officeContactName, "healthCareProvider");
		click(PatientPage.prescriberInfo_next);
		
		//Prescription Info  
		click(PatientPage.starterDose);
		click(PatientPage.prescriptionInfo_next);
		
		//Preferred Site of Infusion
		click(PatientPage.primaryInfusionSiteLocation);
		click(By.xpath("//label[text()='Monday']/preceding-sibling::div/div/following-sibling::div"));
		click(PatientPage.clinicOrOther);
		click(PatientPage.preferredSiteInfusion_next);
		
		//provider declaration
		enterTxt(PatientPage.providerSignature, "providerSignature");
		enterDOB(PatientPage.signatureDate, "signatureDate");
		click(PatientPage.providerDeclaration_next);
		waitFor(6000);
		
		scroll();
		//complete enrollment
		/*selectDropdown(PatientPage.attachDoc,"attach");
		click(PatientPage.attachFile);
		StringSelection ss = new StringSelection("C:/Users/538768/Desktop/enrollment.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);*/
		
		click(PatientPage.completeEnrollmentSubmit);
		
	}

	


}
