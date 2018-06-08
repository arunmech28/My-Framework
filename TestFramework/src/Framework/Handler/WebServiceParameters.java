package Framework.Handler;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class WebServiceParameters {

	private Map<String,ExtentTest> loggermap;
	
	public Map<String, ExtentTest> getLoggermap() {
		return loggermap;
	}

	public void setLoggermap(Map<String, ExtentTest> loggermap) {
		this.loggermap = loggermap;
	}
	
	private RequestConfig requestconfig;

	public RequestConfig getRequestconfig() {
		return requestconfig;
	}

	public void setRequestconfig(RequestConfig requestconfig) {
		this.requestconfig = requestconfig;
	}

	private Map<String,CloseableHttpClient> httpclientmap;
	
	
	public Map<String, CloseableHttpClient> getHttpclientmap() {
		return httpclientmap;
	}

	public void setHttpclientmap(Map<String, CloseableHttpClient> httpclientmap) {
		this.httpclientmap = httpclientmap;
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

	
	private static final WebServiceParameters webserviceparameters = new WebServiceParameters();

	private WebServiceParameters()
	{
		// To prevent external instantiation of this class
	}

	public static WebServiceParameters getInstance()
	{
		return webserviceparameters;
	}
}
