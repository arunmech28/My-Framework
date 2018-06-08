package Framework.Handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Framework.Handler.Parameters;

public abstract class TestBaseWebAutomation {

	protected Parameters parameters;


	@BeforeSuite
	public void setup() throws FileNotFoundException, IOException {
		Parameters parameters = Parameters.getInstance();
		Properties prop = loadPropertyFile(parameters);
		parameters.setCurrentTestSuite(this.getClass().getSimpleName());
		parameters.setProperties(prop);
		parameters.setDrivermap(new HashMap<String,WebDriver>());
		parameters.setLoggermap(new HashMap<String,ExtentTest>());
	}




	@BeforeTest
	public void reportsetup() {
		Parameters parameters = Parameters.getInstance();
		ExtentReports extent = new ExtentReports (System.getProperty("user.dir") +"\\Extent-report\\STMExtentReport.html", true);
		extent.addSystemInfo("Application Name", "Arun Test")
		.addSystemInfo("Environment", "Arun_PC")
		.addSystemInfo("Tester", "Arun");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		parameters.setExtentReports(extent);                

	}

	@BeforeMethod
	public void driverstartup(Method m) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Parameters parameters = Parameters.getInstance();
		WebDriver driver;
		Map<String,WebDriver> drivermap;
		Class<?> c = Class.forName("Application.Suite."+m.getDeclaringClass().getSimpleName());
		Constructor<?> ctor = c.getConstructor();
		Object object = ctor.newInstance();
	
		Method mem = object.getClass().getMethod(m.getName(), java.lang.String[].class);

		if(mem.isAnnotationPresent(ParameterAnnotation.class)) {


			ParameterAnnotation anno=mem.getAnnotation(ParameterAnnotation.class);
			System.out.println("browser is "+anno.browser());


			switch(anno.browser().toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", parameters.getProperties().getProperty("ChromeDriverPath"));
				driver = new ChromeDriver();
				drivermap =parameters.getDrivermap();
				drivermap.put(m.getName(), driver);
				parameters.setDrivermap(drivermap);
				//parameters.setDriver(driver);
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", parameters.getProperties().getProperty("GeckoDriverPath"));
				driver = new FirefoxDriver();
				drivermap =parameters.getDrivermap();
				drivermap.put(m.getName(), driver);
				parameters.setDrivermap(drivermap);
				break;

			}
		}
		else {
			System.setProperty("webdriver.chrome.driver", parameters.getProperties().getProperty("ChromeDriverPath"));
			driver = new ChromeDriver();
			drivermap =parameters.getDrivermap();
			drivermap.put(m.getName(), driver);
			parameters.setDrivermap(drivermap);
		}
		ExtentReports extent = parameters.getExtentReports();
		ExtentTest logger = extent.startTest(m.getName());
		Map<String,ExtentTest> loggermap = parameters.getLoggermap();
		//parameters.setCurrentTestCase(m.getName());
		loggermap.put(m.getName(), logger);
		parameters.setLoggermap(loggermap);
		//parameters.setLogger(logger);
		this.parameters = parameters;



	}



	@AfterMethod
	public void getResult(ITestResult result,Method m){
		Parameters parameters = Parameters.getInstance();
		ExtentReports extent = parameters.getExtentReports();
		ExtentTest logger = parameters.getLoggermap().get(m.getName());
		//Map<String,ExtentTest> loggermap = parameters.getLoggermap();
		

		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		else {
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
		
		

	}

	@AfterTest
	public void endReport() throws InterruptedException{
		// writing everything to document
		//flush() - to write or update test information to your report. 
		Parameters parameters = Parameters.getInstance();
		ExtentReports extent = parameters.getExtentReports(); 
		ExtentTest logger = parameters.getLogger();
		extent.endTest(logger);
		extent.flush();
		//Call close() at the very end of your session to clear all resources. 
		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		//Once this method is called, calling any Extent method will throw an error.
		//close() - To close all the operation
		Thread.sleep(5000);
		extent.close();


	}

	private Properties loadPropertyFile(Parameters parameters) throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		return prop;

	}

	public void runWithJson(String[] args,Parameters params){
		JsonRunner runner = new JsonRunner(params);
		runner.execute(args);
	}
	
	public void runWithExcel(String[] args,Parameters params) {
		ExcelRunner runner = new ExcelRunner(params);
		runner.execute(args);
	}

	/*public void startExecution(Parameters params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		Runner runner = new Runner(params);
		runner.execute();
	}*/

}
