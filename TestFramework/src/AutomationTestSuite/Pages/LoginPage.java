package AutomationTestSuite.Pages;

import org.openqa.selenium.By;

public class LoginPage {

	
	public static By username = By.xpath("//input[@id='authentication:username']"); 
	public static By password = By.xpath("//input[@id='authentication:password']"); 
	public static By signin = By.xpath("//button[@id='authentication:signIn']");
	public static By register = By.xpath("//a[text()='Register']");
	public static By forgotusername = By.xpath("//a[text()='Forgot Username?']");
	public static By forgotpassword = By.xpath("//a[text()='Forgot Password?']");
}
