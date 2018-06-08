package Framework.Handler;

import java.lang.annotation.*;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.PARAMETER,ElementType.METHOD})  
public @interface ParameterAnnotation {

	  
		String browser() default "CHROME";  
	  
}
