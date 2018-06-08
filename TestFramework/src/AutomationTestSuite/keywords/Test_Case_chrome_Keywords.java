package AutomationTestSuite.keywords;

import java.util.Map;

import Framework.Handler.Parameters;
import Framework.Utilities.ReusableLibrary;

public class Test_Case_chrome_Keywords extends ReusableLibrary{

	public Test_Case_chrome_Keywords(Parameters params, Map<String, String> testdata) {
		super(params, testdata);
		System.out.println(testdata);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		loadurl("http://newtours.demoaut.com/");
	}
}
