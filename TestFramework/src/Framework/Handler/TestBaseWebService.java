package Framework.Handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class TestBaseWebService {
	protected WebServiceParameters parameters;


	@BeforeSuite
	public void setup() throws FileNotFoundException, IOException {
		WebServiceParameters parameters = WebServiceParameters.getInstance();
		parameters.setProperties(loadPropertyFile());
		parameters.setCurrentTestSuite(this.getClass().getSimpleName());
		parameters.setHttpclientmap(new HashMap<String,CloseableHttpClient>());


	}


	private Properties loadPropertyFile() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream("config.properties"));

		return prop;
	}

	@BeforeMethod
	public void startHttpClient(Method m) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		WebServiceParameters parameters = WebServiceParameters.getInstance();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		Map<String,CloseableHttpClient> httpclientmap = parameters.getHttpclientmap();
		httpclientmap.put(m.getName(), httpclient);
		HttpHost proxy = new HttpHost("proxy.cognizant.com", 6050, "http");

        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
	
        parameters.setHttpclientmap(httpclientmap);
        parameters.setRequestconfig(config);
        this.parameters=parameters;

	}

	public void startExecution(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException, IOException, ParseException {
		//Runner runner = new Runner(parameters);
		//runner.execute(args);
	}


}
