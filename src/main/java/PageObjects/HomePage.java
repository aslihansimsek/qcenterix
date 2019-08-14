package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public WebDriver driver;
	
	By orderSummary = By.xpath("//h2[class='warning']");
	By actualMessage = By.xpath("//h2[class='message']");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebElement getOrderSummary()
	{
		return driver.findElement(orderSummary);
	}
	
	public WebElement getActualMessage()
	{
		return driver.findElement(actualMessage);
	}
}


