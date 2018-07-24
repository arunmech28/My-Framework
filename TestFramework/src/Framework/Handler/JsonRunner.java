package Framework.Handler;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.parser.ParseException;

import Framework.Handler.Parameters;
import Framework.Utilities.JsonUtil;

@Deprecated
public class JsonRunner {

	private Parameters parameters;

	public JsonRunner(Parameters parameters) {
		this.parameters = parameters;
	}

	public void execute(String[] args)  {

		/*
		Class<?> c = Class.forName(parameters.getProperties().getProperty("KeywordsPath")+
				parameters.getCurrentTestCase()+"_Keywords.class");*/

		/*Class<?> c = Class.forName("D:\\Arun\\arunframework\\TestFramework\\bin\\application\\keywords"+"."+"Test_Case_1_Keywords");*/

		/*Class<?>[] paramString = new Class[args.length];
		for(int i=0;i<args.length;i++) {
			paramString[i] = String.class;
		}*/

		Map<String, String> map = new HashMap<String,String>();
		Set<?> keys = null;
		try {
			keys = JsonUtil.getKeys(parameters.getCurrentTestCase(), parameters.getCurrentTestSuite());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<?> itr = keys.iterator();
		int n=0;
		while(itr.hasNext()) {
			map.put((String) itr.next(), args[n]);
			n++;
		}
		Object[] obj = new Object[args.length];
		for(int i=0;i<args.length;i++) {
			obj[i] = args[i];
		}
try {
		Class<?> c = Class.forName(parameters.getCurrentTestSuite()+".keywords."+parameters.getCurrentTestCase()+"_Keywords");
		Method executeComponent = c.getDeclaredMethod("run");
		Constructor<?> cons = c.getDeclaredConstructors()[0];
		Object object = cons.newInstance(parameters,map);
		executeComponent.invoke(object, (Object [])null);
}
catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException |
		InvocationTargetException | NoSuchMethodException | SecurityException e) {
	
}
}

	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
