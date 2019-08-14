package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage {
	
	private WebDriver driver;
	
	By checkOut = By.id("checkOut");
	By addressLine1 = By.id("address1");
	By addressLine2 = By.id("address2");
	By city = By.id("city");
	By state = By.id("state");
	By zipCode = By.id("zipCode");
	By phoneNumber = By.id("phone");
	
	By creditCardNumber = By.id("creditCard");
	By securityCode = By.id("securityCode");
	By expirationDate = By.id("expiration");
	By email = By.id("email");
	By submitButton = By.id("submit");
	
	By warningMessage = By.xpath("//h2[class='message']");
	
	By cardItemList = By.id("cardItemList");

	public CheckOutPage(WebDriver driver)	
	{
		this.driver = driver;
	}
	
	public WebElement getCheckOutButton()
	{
		return driver.findElement(checkOut);
	}
	
	public WebElement getAddressLine1()
	{
		return driver.findElement(addressLine1);
	}
	
	public WebElement getAddressLine2()
	{
		return driver.findElement(addressLine2);
	}

	public WebElement getCity()
	{
		return driver.findElement(city);
	}
	
	public WebElement getState()
	{
		return driver.findElement(state);
	}
	
	public WebElement getZipCode()
	{
		return driver.findElement(zipCode);
	}
	
	public WebElement getPhoneNumber()
	{
		return driver.findElement(phoneNumber);
	}
	
	public WebElement getCreditCardNumber()
	{
		return driver.findElement(creditCardNumber);
	}

	public WebElement getSecurityCode()
	{
		return driver.findElement(securityCode);
	}
	
	public WebElement getExpirationDate()
	{
		return driver.findElement(expirationDate);
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getSubmitButton()
	{
		return driver.findElement(submitButton);
	}
	
	public WebElement getWarningMessage()
	{
		return driver.findElement(warningMessage);
	}
	
	public void completeCheckOut(String addressLine1, String addressLine2, String city, String state, String zipCode, String phoneNumber, 
			String email, String creditCardNumber, String securityCode, String expirationDate)
	{
		getCheckOutButton().click();
		getAddressLine1().sendKeys(addressLine1);
		getAddressLine2().sendKeys(addressLine2);
		getCity().sendKeys(city);
		getState().sendKeys(state);
		getZipCode().sendKeys(zipCode);
		getPhoneNumber().sendKeys(phoneNumber);
		getEmail().sendKeys(email);
		getCreditCardNumber().sendKeys(creditCardNumber);
		getSecurityCode().sendKeys(securityCode);
		getExpirationDate().sendKeys(expirationDate);
		getSubmitButton().click();
	}
	
	public List<WebElement> getItemsInCard()
	{
		return driver.findElements(cardItemList);
		
	}
	
}
