package Application.Suite;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import mytest.selenium.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Framework.Handler.BrowserAnnotation;
import Framework.Handler.TestBaseWebAutomation;
import Framework.Utilities.ExcelUtil;
import Framework.Utilities.JsonUtil;


public class AutomationTestSuite extends TestBaseWebAutomation{
	
	@BrowserAnnotation(browser="CHROME")
	@Test()
	public void Test_AngularJs(){
		
		WebDriver driver = parameters.getDrivermap().get(new Exception().getStackTrace()[0].getMethodName());
		driver.get("https://angularjs.org/");
		//driver.findElement(TestBy.ngmodel("yourName")).sendKeys("Arun");
		
		driver.findElement(By.ngmodel("yourName")).sendKeys("Arun");
		driver.findElement(By.ngclick("plnkr.open($event)")).click();
				
	}
	
	
	@BrowserAnnotation(browser="CHROME")
	@Test(dataProvider = "test_Data_Json",enabled=false)
	public void Test_Case_JsonMap(HashMap<String,String> args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		
		System.out.println(args);
		run(args,parameters);
		
	}

	@BrowserAnnotation(browser="CHROME")
	@Test(dataProvider = "test_Data_Json",enabled=false)
	public void Test_Case_1(HashMap<String,String> args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		run(args,parameters);
			
	}
	
	@BrowserAnnotation
	@Test(dataProvider = "test_Data_Json",enabled=false)
	public void Test_Case_4(HashMap<String,String> args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		System.out.println("test 2 passed");
		
		//System.out.println(parameters.getCurrentTestCase());
		//run(args,parameters);
			
	}

	

	@BrowserAnnotation(browser="FIREFOX")
	@Test(dataProvider = "test_Data_Json",enabled=false)
	public void Test_Case_firefox(HashMap<String,String> args){
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		//System.out.println("test 1 passed");
		//parameters.getDriver().get("http://newtours.demoaut.com/");
		//run(args,parameters);
			
	}
	
	@BrowserAnnotation
	@Test(dataProvider = "test_Data_excel",enabled=false)
	public void Test_Case_chrome(HashMap<String,String> args) {
		
		parameters.setCurrentTestCase(new Exception().getStackTrace()[0].getMethodName());
		System.out.println(args);
		//System.out.println("test 2 passed");
		//parameters.getDriver().get("https://www.google.co.in/");
		//System.out.println(parameters.getCurrentTestCase());
		//run(args,parameters);
			
	}

	
	@Test(dataProvider = "test_Data_Json",enabled=false)
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

		return ExcelUtil.getTestDataExcel(m.getName(),this.getClass().getSimpleName());

	}
	

	@DataProvider
	public Object[][] test_Data_Json(Method m){

		return JsonUtil.getTestDataJSON(m.getName(),this.getClass().getSimpleName());

	}

}
