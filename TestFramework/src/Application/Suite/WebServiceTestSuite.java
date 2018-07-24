package Application.Suite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Framework.Handler.TestBaseWebService;
import Framework.Utilities.ExcelUtil;
import Framework.Utilities.JsonUtil;


public class WebServiceTestSuite extends TestBaseWebService{


	
	@Test(dataProvider = "test_Data",enabled=false)
	public void Test_Case_1(HashMap<String,String> args)  {
		
	
	}
	
	
	@DataProvider
	public Object[][] test_Data(Method m) {

		return JsonUtil.getTestDataJSON(m.getName(),this.getClass().getSimpleName());

	}
	
	@DataProvider
	public Object[][] test_Data_excel(Method m) throws FileNotFoundException, IOException, ParseException{

		return ExcelUtil.getTestDataExcel(m.getName(),this.getClass().getSimpleName());

	}

}
