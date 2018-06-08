package AutomationTestSuite.keywords;

import java.util.Map;

import Framework.Handler.Parameters;
import Framework.Utilities.ReusableLibrary;

public class Test_Case_firefox_Keywords extends ReusableLibrary {

	public Test_Case_firefox_Keywords(Parameters params, Map<String, String> testdata) {
		super(params, testdata);
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		loadurl("https://www.google.co.in/");
	}
}
