package Framework.Utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Framework.Handler.Parameters;
import Framework.Handler.TestBaseWebAutomation;


public class JsonUtil extends TestBaseWebAutomation{
	
	public static Object[][] getTestData(String fileName,String folderName){
		
		Parameters params = Parameters.getInstance();
		JSONParser parser = new JSONParser();
		
		 JSONArray a = null;
		try {
			a = (JSONArray) parser.parse(new FileReader(params.getProperties().getProperty("TestDataPath")
					 +folderName+"\\"+fileName+".json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 JSONObject object = (JSONObject) a.get(0);
		 Set<?> keyindex = object.keySet();
		 	 
		 Object[][] testdata = new Object[a.size()][keyindex.size()];
		 
		 for(int i=0;i<a.size();i++) {
			 
			 JSONObject obj = (JSONObject) a.get(i);
			 
			 Set<?> keys = obj.keySet();
			 params.setKeys(keys);
			 Iterator<?> itr = keys.iterator();
			 int j=0;
			 
			 while(itr.hasNext()) {
				 
				 testdata[i][j] = (String) obj.get(itr.next());
				 
				 j++;
			 }
		 }
				
		return testdata;
		
	}
	
	public static Set<?> getKeys(String fileName,String folderName) throws FileNotFoundException, IOException, ParseException{
		Parameters params = Parameters.getInstance();
		JSONParser parser = new JSONParser();
		
		 JSONArray a = (JSONArray) parser.parse(new FileReader(params.getProperties().getProperty("TestDataPath")+folderName+"\\"+fileName+".json"));

		 JSONObject object = (JSONObject) a.get(0);
		 Set<?> keyindex = object.keySet();
		
		return keyindex;
		
	}
}
	

		