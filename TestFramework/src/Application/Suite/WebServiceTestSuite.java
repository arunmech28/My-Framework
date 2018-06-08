package Application.Suite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.Handler.ParameterAnnotation;
import Framework.Handler.TestBaseWebAutomation;
import Framework.Handler.TestBaseWebService;
import Framework.Utilities.ExcelUtil;
import Framework.Utilities.JsonUtil;


public class WebServiceTestSuite extends TestBaseWebService{


	
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_1(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		
	
	}
	
	
	@DataProvider
	public Object[][] test_Data(Method m) throws FileNotFoundException, IOException, ParseException{

		return JsonUtil.getTestData(m.getName(),this.getClass().getSimpleName());

	}
	
	@DataProvider
	public Object[][] test_Data_excel(Method m) throws FileNotFoundException, IOException, ParseException{

		return ExcelUtil.getTestData(m.getName(),this.getClass().getSimpleName());

	}

}
