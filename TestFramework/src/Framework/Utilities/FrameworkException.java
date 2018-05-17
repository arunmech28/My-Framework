package Framework.Utilities;

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException  {

	
	public String errorName = "Error";
	
	
	public FrameworkException(String errorDescription)
	{
		super(errorDescription);
	}
}
