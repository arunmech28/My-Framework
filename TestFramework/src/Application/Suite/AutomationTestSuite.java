package Application.Suite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import Framework.Handler.TestBaseWebAutomation;
import Framework.Utilities.JsonUtil;


public class AutomationTestSuite extends TestBaseWebAutomation{


	@Test(dataProvider = "test_Data",enabled=true)
	public void Test_Case_1(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		
		startExecution(args);
			
	}

	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_2(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		
		startExecution(args);
	}
	
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_3(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		
		startExecution(args);
	}

	@Test(enabled=false)
	public void Test_Case_4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		
		 Object obj = new JSONParser().parse(new FileReader("D:\\Arun\\arunframework\\TestFramework\\AutomationTestSuite\\Test_Case_1.json"));
         
	        // typecasting obj to JSONObject
	        JSONObject jo = (JSONObject) obj;
		
	        System.out.println(jo.get("username"));
	}

	@DataProvider
	public Object[][] test_Data(Method m) throws FileNotFoundException, IOException, ParseException{

		return JsonUtil.getTestData(m.getName(),this.getClass().getSimpleName());

	}

}
