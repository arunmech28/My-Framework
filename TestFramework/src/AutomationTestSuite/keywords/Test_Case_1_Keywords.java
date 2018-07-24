package AutomationTestSuite.keywords;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Map;
import org.openqa.selenium.By;
import Framework.Handler.Parameters;
import Framework.Utilities.ReusableLibrary;



public class Test_Case_1_Keywords extends ReusableLibrary {
	
	public Test_Case_1_Keywords(Parameters params, Map<String, String> testdata) {
		super(params, testdata);
		
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//Login
		
		//justTest("LoginPage.username");
		loadurl("https://spstest.mckesson.com/MTpharma-HCP/");
		//newTest("LoginPage.username1");
		enterTxt("LoginPage.username");
		enterTxt("LoginPage.password");
		click("LoginPage.signin");
		
		//Enroll Patient
		click("PatientPage.patientTab");
		waitFor(2000);
		click("PatientPage.enrollNewPatient");
		
		click(By.xpath("//label[contains(text(),'"+getValue("RequestService")+"')]/preceding-sibling::input"));
		enterTxt("PatientPage.firstName");
		enterTxt("PatientPage.lastName");
		selectDropdown("PatientPage.gender");	
		enterTxt("PatientPage.address1");
		enterTxt("PatientPage.city");
		selectDropdown("PatientPage.state");
		enterTxt("PatientPage.zipcode");
		enterDOB("PatientPage.patientDOB");
		
		enterTxt("PatientPage.cellPhone");
		click("PatientPage.next");
		click("PatientPage.popupCancel");
		
		scroll();
		//Insurance info
		click("PatientPage.fullbenifitInvestigation");
		click("PatientPage.specialDistributor");
		//PI info
		enterTxt("PatientPage.primaryInsuranceName");
		enterTxt("PatientPage.cardHolderName");
		enterTxt("PatientPage.cardHolderRelation");
		enterTxt("PatientPage.insuranceCompanyPhone");
		enterTxt("PatientPage.policyNumber");
		enterTxt("PatientPage.groupNumber");
		click("PatientPage.insuranceInfo_next");
		click("PatientPage.patientFinancialInfo_next");
		
		//Prescriber Info
		selectDropdown("PatientPage.practiceLocation");
		selectDropdown("PatientPage.healthCareProvider");
		click("PatientPage.selectProviderContact");
		click("PatientPage.hcpAddress");
		click("PatientPage.hcpPhone");
		click("PatientPage.hcpFax");
		click("PatientPage.hcp_next");
		selectDropdown("PatientPage.officeContactName");
		click("PatientPage.prescriberInfo_next");
		
		//Prescription Info  
		click("PatientPage.starterDose");
		click("PatientPage.prescriptionInfo_next");
		
		//Preferred Site of Infusion
		click("PatientPage.primaryInfusionSiteLocation");
		click(By.xpath("//label[text()='Monday']/preceding-sibling::div/div/following-sibling::div"));
		click("PatientPage.clinicOrOther");
		click("PatientPage.preferredSiteInfusion_next");
		
		//provider declaration
		enterTxt("PatientPage.providerSignature");
		enterDOB("PatientPage.signatureDate");
		click("PatientPage.providerDeclaration_next");
		waitFor(6000);
		
		scroll();
		//complete enrollment
		selectDropdown("PatientPage.attachDoc");
		click("PatientPage.attachFile");
		StringSelection ss = new StringSelection("C:/Users/538768/Desktop/enrollment.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		click("PatientPage.completeEnrollmentSubmit");
		
		
	}

	


}
