package Framework.Handler;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Parameters {

	private Map<String,ExtentTest> loggermap;
	
	public Map<String, ExtentTest> getLoggermap() {
		return loggermap;
	}

	public void setLoggermap(Map<String, ExtentTest> loggermap) {
		this.loggermap = loggermap;
	}

	private Map<String,WebDriver> drivermap;
	
	public Map<String, WebDriver> getDrivermap() {
		return drivermap;
	}

	public void setDrivermap(Map<String, WebDriver> drivermap) {
		this.drivermap = drivermap;
	}

	private ExtentTest logger;

	public ExtentTest getLogger() {
		return logger;
	}

	public void setLogger(ExtentTest logger) {
		this.logger = logger;
	}

	private ExtentReports extentReports;
	
	public ExtentReports getExtentReports() {
		return extentReports;
	}

	public void setExtentReports(ExtentReports extentReports) {
		this.extentReports = extentReports;
	}

	private Set<?> keys;
		
	public Set<?> getKeys() {
		return keys;
	}

	public void setKeys(Set<?> keys) {
		this.keys = keys;
	}

	private String currentTestSuite;
	
	public String getCurrentTestSuite() {
		return currentTestSuite;
	}

	public void setCurrentTestSuite(String currentTestSuite) {
		this.currentTestSuite = currentTestSuite;
	}

	private String currentTestCase;

	public String getCurrentTestCase() {
		return currentTestCase;
	}

	public void setCurrentTestCase(String currentTestCase) {
		this.currentTestCase = currentTestCase;
	}

	private Properties prop;

	public Properties getProperties() {
		return prop;
	}

	public void setProperties(Properties prop) {
		this.prop = prop;
	}

	
	private static final Parameters parameters = new Parameters();

	private Parameters()
	{
		// To prevent external instantiation of this class
	}

	public static Parameters getInstance()
	{
		return parameters;
	}
}
