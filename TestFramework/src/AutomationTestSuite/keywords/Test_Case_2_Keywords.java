package AutomationTestSuite.keywords;

import org.openqa.selenium.WebDriver;

import Framework.Utilities.ReusableLibrary;



public class Test_Case_2_Keywords extends ReusableLibrary {






	public Test_Case_2_Keywords(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void scenario(String firstName,String lastName) {

		System.out.println(firstName +" "+ lastName);
		
	}




}
