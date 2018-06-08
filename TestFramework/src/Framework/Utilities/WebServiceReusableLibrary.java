package Framework.Utilities;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import Framework.Handler.WebServiceParameters;


public class WebServiceReusableLibrary  {

	protected Map<String,String> testdata; 
	protected WebServiceParameters params;

	public WebServiceReusableLibrary(WebServiceParameters params,Map<String,String> testdata) {
		//this.driver = params.getDriver();
		this.params = params;
		this.testdata = testdata;

	}

	public String getValue(String key) {

		if(!testdata.containsKey(key)) {
			throw new FrameworkException("No data found in Input JSON");
		}
		return testdata.get(key);
	}

	public void getEntity() {

		CloseableHttpClient httpclient = params.getHttpclientmap().get(params.getCurrentTestCase());
		HttpGet httpget = new HttpGet("http://restapi.demoqa.com/utilities/weather/city/Hyderabad");
		httpget.setConfig(params.getRequestconfig());
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}