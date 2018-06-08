package Application.Suite;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.Handler.ParameterAnnotation;
import Framework.Handler.TestBaseWebAutomation;
import Framework.Utilities.ExcelUtil;
import Framework.Utilities.JsonUtil;


public class AutomationTestSuite extends TestBaseWebAutomation{


	@ParameterAnnotation(browser="FIREFOX")
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_1(String[] args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		System.out.println("test 1 passed");
		parameters.getDriver().get("http://newtours.demoaut.com/");
		runWithJson(args,parameters);
			
	}
	
	@ParameterAnnotation
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_4(String[] args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		System.out.println("test 2 passed");
		parameters.getDriver().get("https://www.google.co.in/");
		//System.out.println(parameters.getCurrentTestCase());
		runWithJson(args,parameters);
			
	}

	

	@ParameterAnnotation(browser="FIREFOX")
	@Test(dataProvider = "test_Data",enabled=true)
	public void Test_Case_firefox(String[] args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		//System.out.println("test 1 passed");
		//parameters.getDriver().get("http://newtours.demoaut.com/");
		runWithJson(args,parameters);
			
	}
	
	@ParameterAnnotation
	@Test(dataProvider = "test_Data_excel",enabled=true)
	public void Test_Case_chrome(String[] args) {
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		System.out.println(Arrays.toString(args));
		//System.out.println("test 2 passed");
		//parameters.getDriver().get("https://www.google.co.in/");
		//System.out.println(parameters.getCurrentTestCase());
		runWithExcel(args,parameters);
			
	}

	
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_2()  {
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		//startExecution(parameters);
		System.out.println("in test_case_2");
	}
	
	@Test(enabled=false)//(dataProvider = "test_Data",enabled=false)
	public void Test_Case_3() {
		
		System.out.println("in test_case_3");
	}

	@Test(enabled=false)
	public void Test_Case_4() {
		
		 Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader("D:\\Arun\\arunframework\\TestFramework\\AutomationTestSuite\\Test_Case_1.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	        // typecasting obj to JSONObject
	        JSONObject jo = (JSONObject) obj;
		
	        System.out.println(jo.get("username"));
	}

	@DataProvider
	public Object[][] test_Data(Method m){

		return JsonUtil.getTestData(m.getName(),this.getClass().getSimpleName());

	}
	
	@DataProvider
	public Object[][] test_Data_excel(Method m){

		return ExcelUtil.getTestData(m.getName(),this.getClass().getSimpleName());

	}

}
