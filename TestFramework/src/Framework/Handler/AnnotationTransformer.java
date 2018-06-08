package Framework.Handler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
 
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
 
public class AnnotationTransformer implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
 
	/*@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}*/
}