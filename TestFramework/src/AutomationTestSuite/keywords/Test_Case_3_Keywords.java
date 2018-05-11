package AutomationTestSuite.keywords;


import java.util.Map;

import org.openqa.selenium.WebDriver;

import Framework.Utilities.ReusableLibrary;



public class Test_Case_3_Keywords extends ReusableLibrary {




	public Test_Case_3_Keywords(WebDriver driver) {
		super(driver);
		
	}

	public void scenario(Map<String,String> map ) {

		loadurl("https://www.google.co.in/");
		System.out.println(map);
		//enterTxt(AutomationTestSuite.Pages.LoginPage.username,args[0]);
	}




}
