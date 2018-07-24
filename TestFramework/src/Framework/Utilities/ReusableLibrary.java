package Framework.Utilities;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.Handler.Parameters;


public class ReusableLibrary  {

	protected WebDriver driver; 
	protected Map<String,String> testdata;
	protected Parameters params;

	public ReusableLibrary(Parameters params,Map<String,String> testdata) {
		//this.driver = params.getDriver();
		Map<String,WebDriver> drivermap = params.getDrivermap();
		this.driver = drivermap.get(params.getCurrentTestCase());
		this.testdata = testdata;
		this.params = params;
	}

	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;


		js.executeScript("scroll(0, 250);");

	}


	public String getValue(String key) {

		if(!testdata.containsKey(key)) {
			throw new FrameworkException("No data found in Input JSON");
		}
		return testdata.get(key);
	}
	public void click(String value) {
		By byval = getByValue(value);
		waitUntilElementEnabled(byval,30);
		driver.findElement(byval).click();
		waitFor(1000);
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

	public void enterDOB(String value) {
		By byval = getByValue(value);
		String data_key = getData(value);
		waitUntilElementEnabled(byval, 20);
		driver.findElement(byval).click();
		driver.findElement(byval).clear();
		waitFor(2000);
		driver.findElement(byval).sendKeys(getValue(data_key));
		driver.findElement(byval).sendKeys(Keys.TAB);

		waitFor(1000);
	}

	public void enterTxt(String value) {

		By byval = getByValue(value);
		String data_key = getData(value);

		waitUntilElementEnabled(byval,30);
		driver.findElement(byval).clear();
		driver.findElement(byval).sendKeys(getValue(data_key));
		waitFor(1000);
	}


	public By getByValue(String value) {
		String[] val1 = value.split("\\.");
		Class<?> myClass = null;
		Field myField =null;
		By byval = null;
		try{
			myClass = Class.forName(params.getCurrentTestSuite()+".Pages."+val1[0]);
			myField = myClass.getDeclaredField(val1[1]);
			byval = (By) myField.get(null);
		}
		catch(ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new FrameworkException("Problem in getting the xpath from the Pages");
		}


		return byval;
	}

	public String getData(String value) {
		String[] val1 = value.split("\\.");

		return val1[1];
	}

	public void selectDropdown(String value) {
		By byval = getByValue(value);
		String data_key = getData(value);
		waitFor(5000);

		Select select = new Select(driver.findElement(byval));

		List<WebElement> options = select.getOptions();
		while(options.size()==0) {
			waitFor(4000);
		}
		select.selectByVisibleText(getValue(data_key));
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


	public void testing() {
		new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.id("123"))
				);
	}

	

}
