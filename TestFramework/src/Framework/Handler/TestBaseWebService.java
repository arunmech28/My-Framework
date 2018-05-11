package Framework.Handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class TestBaseWebService {
Parameters parameters = Parameters.getInstance();
	
	
	@BeforeSuite
	public void setup() throws FileNotFoundException, IOException {
		loadPropertyFile();
		parameters.setCurrentTestSuite(this.getClass().getSimpleName());
		
		
	}


	private void loadPropertyFile() throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		parameters.setProperties(prop);
		
	}
	
	@BeforeMethod
	public void driverstartup(Method m) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
	
		System.setProperty("webdriver.chrome.driver", "D:\\Arun\\drivers\\chromedriver_2_35.exe");
		WebDriver driver = new ChromeDriver();
		parameters.setCurrentTestCase(m.getName());
		parameters.setDriver(driver);
		
	}
	
	public void startExecution(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		Runner runner = new Runner(parameters);
		runner.execute(args);
	}
	

}
