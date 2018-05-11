package AutomationTestSuite.Pages;

import org.openqa.selenium.By;

public class PatientPage {


	public static By patientTab = By.xpath("//a[contains(text(),'Patients')]");
	public static By enrollNewPatient = By.xpath("//button[@id='patientForm:j_idt263']"); 
	public static By claimDenialAssistance = By.xpath("//input[@id='j_idt56:0']");
	public static By benefitsInvestigation = By.xpath("//input[@id='j_idt56:1']");
	public static By oopProgramAssistance = By.xpath("//input[@id='j_idt56:2']");


	//Patient Information

	public static By firstName = By.xpath("//input[@id='patientFirstname']");
	public static By middleName = By.xpath("//input[@id='patientmiddleName']");
	public static By lastName = By.xpath("//input[@id='patientLastname']");
	public static By gender = By.xpath("//select[@id='gender']");
	public static By address1 = By.xpath("//input[@id='patientAddressOne']");
	public static By address2 = By.xpath("//input[@id='patientAddressTwo']");
	public static By patientDOB = By.xpath("//input[@id='PatientDOB_input']");

	public static By city = By.xpath("//input[@id='City']");
	public static By state = By.xpath("//select[@id='patientState']");
	public static By zipcode = By.xpath("//input[@id='patientZip']");
	public static By homePhone = By.xpath("//input[@id='homePhone']");
	public static By workPhone = By.xpath("//input[@id='patientWorkPhone']");
	public static By cellPhone = By.xpath("//input[@id='patientCellPhone']");

	//best time to contact
	public static By morning = By.xpath("//label[text()=' Morning']/preceding-sibling::input");
	public static By afternoon = By.xpath("//label[text()=' Afternoon']/preceding-sibling::input");
	public static By evening = By.xpath("//label[text()=' Evening']/preceding-sibling::input");

	public static By next = By.xpath("//a[@id='j_idt103']");	

	public static By popupCancel = By.xpath("//button[@id='j_idt112']");


	//Insurance information
	public static By fullbenifitInvestigation = By.xpath("//div[@id='fullbenifitInvestigation']");
	public static By specialDistributor = By.xpath("//div[@id='specialDistributor']/div/following-sibling::div");
	//public static By specialPharmacyPrescription = By.xpath("//div[@id='specialPharmacyPrescription']");
	//public static By homeInfusion = By.xpath("//div[@id='homeInfusion']");
	public static By patientHasNoInsurance = By.xpath("//input[@id='j_idt119:0']");

	public static By primaryInsuranceName = By.xpath("//input[@id='primPlanName']");
	public static By cardHolderName = By.xpath("//input[@id='cardHolderName']");
	public static By cardHolderRelation = By.xpath("//input[@id='cardHolderRelation']");
	public static By insuranceCompanyPhone = By.xpath("//input[@id='primPhone']");
	public static By policyNumber = By.xpath("//input[@id='primPolicyNumber']");
	public static By groupNumber = By.xpath("//input[@id='primGroupNumber']");


	public static By insuranceInfo_next = By.xpath("//a[@id='j_idt159']");
	public static By patientFinancialInfo_next = By.xpath("//a[@id='j_idt184']");

	//Prescriber information
	public static By practiceLocation = By.xpath("//select[@id='PracticeLocation']");
	public static By healthCareProvider = By.xpath("//select[@id='HealthCareProvider']");//Xavier, George
	public static By selectProviderContact = By.xpath("//a[@id='j_idt188']");
	public static By hcpAddress = By.xpath("//input[@id='hcpDetails:j_idt386:0:j_idt389:0']");
	public static By hcpPhone = By.xpath("//input[@id='hcpDetails:j_idt395:0:j_idt398:0']");
	public static By hcpFax = By.xpath("//input[@id='hcpDetails:j_idt404:0:j_idt407:0']");
	public static By hcp_next = By.xpath("//a[@id='hcpDetails:j_idt413']");
	public static By officeContactName = By.xpath("//select[@id='physicianOfficeContactPerson']");
	public static By prescriberInfo_next = By.xpath("//a[@id='j_idt237']");

	//Prescription information
	public static By starterDose = By.xpath("//input[@id='j_idt277:0']");
	public static By prescriptionInfo_next = By.xpath("//a[@id='j_idt285']");

	//Preferred site of Infusion
	public static By primaryInfusionSiteLocation = By.xpath("//input[@id='j_idt289:0']");
	public static By clinicOrOther = By.xpath("//input[@id='j_idt302:2']");
	public static By preferredSiteInfusion_next = By.xpath("//a[@id='j_idt362']");

	//Provider Declaration
	public static By providerSignature = By.xpath("//input[@id='physicianSignature']");
	public static By signatureDate = By.xpath("//input[@id='physicianInfoDate_input']");
	public static By providerDeclaration_next = By.xpath("//a[@id='j_idt378']");

	//complete enrollment
	public static By attachDoc = By.xpath("//select[@id='Attach']");
	public static By attachFile = By.xpath("//*[@id='browseId']/div[1]/span");
	//public static By attachFile = By.xpath("//input[@id='browseId_input']/preceding-sibling::span[@id='browseId_label']");
	public static By completeEnrollmentSubmit = By.xpath("//a[@id='j_idt88']");













}
