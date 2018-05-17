package Framework.Utilities;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.util.IOUtils;

public class TestDataUtil {

	
	public static void main(String[] args) {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpHost proxy = new HttpHost("proxy.cognizant.com", 6050, "http");

        RequestConfig config = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        HttpGet httpget = new HttpGet("http://restapi.demoqa.com/utilities/weather/city/Hyderabad");
        httpget.setConfig(config);
		CloseableHttpResponse response;
		
		try {
			response = httpclient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			String responseval = entity.toString();
			StringBuilder resString = new StringBuilder(responseval);
			//resString.append(str)

			if (entity != null) {

			  InputStream instream = entity.getContent();
			  
			  BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
			  byte[] bytes = IOUtils.toByteArray(instream);

			  String res = new String(bytes, "UTF-8");

			  instream.close();

			  System.out.println(res);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
