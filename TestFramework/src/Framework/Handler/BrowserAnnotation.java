package Framework.Handler;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.PARAMETER,ElementType.METHOD})  
public @interface BrowserAnnotation {

	  
		String browser() default "CHROME";  
	  
}
