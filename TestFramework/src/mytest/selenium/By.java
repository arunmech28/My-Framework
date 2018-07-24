package mytest.selenium;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;

import mytest.internal.FindsByNgModel;

public abstract class By extends org.openqa.selenium.By {

	public static By ngmodel(String id)
	  {
	    if (id == null) {
	      throw new IllegalArgumentException(
	        "Cannot find elements with a null id attribute.");
	    }
	    
	    	    return new Byngmodel(id);
	  }
	
	public static class Byngmodel
    extends By
    implements Serializable
  {
    private static final long serialVersionUID = 5341968046120372169L;
    private final String id;
    
    public Byngmodel(String id)
    {
      this.id = id;
    }
    
    public WebElement findElement(SearchContext context)
    {
      if ((context instanceof FindsByNgModel)) {
        return ((FindsByNgModel)context).findElementByNgModel(this.id);
      }
      return ((FindsByXPath)context).findElementByXPath(".//*[@ng-model = '" + this.id + 
        "']");
    }
    
    public String toString()
    {
      return "By.id: " + this.id;
    }

	@Override
	public List<WebElement> findElements(SearchContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
	public static By ngclick(String id)
	  {
	    if (id == null) {
	      throw new IllegalArgumentException(
	        "Cannot find elements with a null id attribute.");
	    }
	    
	    	    return new Byngclick(id);
	  }
	
	public static class Byngclick
  extends By
  implements Serializable
{
  private static final long serialVersionUID = 5341968046120372169L;
  private final String id;
  
  public Byngclick(String id)
  {
    this.id = id;
  }
  
  public WebElement findElement(SearchContext context)
  {
    if ((context instanceof FindsByNgModel)) {
      return ((FindsByNgModel)context).findElementByNgModel(this.id);
    }
    return ((FindsByXPath)context).findElementByXPath(".//*[@ng-click = '" + this.id + 
      "']");
  }
  
  public String toString()
  {
    return "By.id: " + this.id;
  }

	@Override
	public List<WebElement> findElements(SearchContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}

	
	
}