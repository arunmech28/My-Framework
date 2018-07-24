package Framework.Handler;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import Framework.Handler.Parameters;



public class Runner {

	private Parameters parameters;

	public Runner(Parameters parameters) {
		this.parameters = parameters;
	}

	public void execute(HashMap<String,String> map)  {

		

		
try {
		Class<?> c = Class.forName(parameters.getCurrentTestSuite()+".keywords."+parameters.getCurrentTestCase()+"_Keywords");
		Method executeComponent = c.getDeclaredMethod("run");
		Constructor<?> cons = c.getDeclaredConstructors()[0];
		Object object = cons.newInstance(parameters,map);
		executeComponent.invoke(object, (Object [])null);
}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException |
		InvocationTargetException | NoSuchMethodException | SecurityException e) {
	
}
	}

	
}
