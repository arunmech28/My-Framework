package mytest.selenium;

public class TestBy {

	public static org.openqa.selenium.By ngmodel(String id) {
		
		
		return org.openqa.selenium.By.xpath(".//*[(@ng-model='"+id+"')]");
		
	}
	
}
