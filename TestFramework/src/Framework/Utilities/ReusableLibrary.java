package Framework.Utilities;

import java.lang.reflect.Field;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.Handler.Parameters;


public class ReusableLibrary  {

	protected WebDriver driver; 
	private Parameters params;
	protected Map<String,String> testdata; 

	public ReusableLibrary(Parameters params,Map<String,String> testdata) {
		this.params = params;
		this.driver = params.getDriver();
		this.testdata = testdata;
		
	}

	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
			js.executeScript("scroll(0, 250);");
		
	}
	
	public void newTest(String val) {
	
		String[] val1 = val.split("\\.");
		Class myClass = null;
		 Field myField =null;
		 By byval = null;
		try{
			myClass = Class.forName(params.getCurrentTestSuite()+".Pages."+val1[0]);
			myField = myClass.getDeclaredField(val1[1]);
			byval = (By) myField.get(null);
		}
		catch(ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			
		}
		
		
		driver.findElement(byval).sendKeys(getValue(val1[1]));
	}
	
	public void justTest(String val) {
		
		String[] val1 = val.split("\\.");
		System.out.println(val+"count"+val1.length);
		 Class myClass = null;
		
		try {
			myClass = Class.forName(params.getCurrentTestSuite()+".Pages."+val1[0]);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Field myField =null;
		    try {
				myField = myClass.getDeclaredField(val1[1]);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    try {
		    	By byval = (By) myField.get(null);
				System.out.println(byval);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//driver.findElement(By.id("id")).click();
	}
	
	public String getValue(String key) {
		
		if(!testdata.containsKey(key)) {
			throw new FrameworkException("No data found in Input TestData");
		}
		return testdata.get(key);
	}
	public void click(By by) {
		waitUntilElementEnabled(by,30);
		driver.findElement(by).click();
		waitFor(1000);
	}

	public void loadurl(String string) {
		driver.get(string);
		driver.manage().window().maximize();

	}
	
	public void enterDOB(By by, String value) {
		waitUntilElementEnabled(by,20);
		driver.findElement(by).click();
		driver.findElement(by).clear();
		waitFor(2000);
		driver.findElement(by).sendKeys(getValue(value));
		driver.findElement(by).sendKeys(Keys.TAB);
		
		waitFor(1000);
	}
	
	public void enterTxt(By by,String val) {
		waitUntilElementEnabled(by,30);
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(getValue(val));
		waitFor(1000);
	}

	public void selectDropdown(By by, String value) {
		waitFor(5000);
		
		Select select = new Select(driver.findElement(by));
		
		select.selectByVisibleText(getValue(value));
		waitFor(1000);
		
	}

	public Boolean objectExists(By by) {
		if(driver.findElements(by).size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean isTextPresent(String textPattern)
	{
		if(driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}


	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
		.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
		.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitUntilElementEnabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
		.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
		.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
	}

	
}
