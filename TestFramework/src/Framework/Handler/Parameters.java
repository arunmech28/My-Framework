package Framework.Handler;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Parameters {


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

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
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
