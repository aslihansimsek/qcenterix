package QCentrixAutomation.EcommerceWebsite;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.CheckOutPage;
import PageObjects.HomePage;
import resources.BaseMethods;

public class TestCases extends BaseMethods{
	
	public CheckOutPage cpo;
	WebDriverWait wait;
	HomePage hpo;
	
	String successMessage = "Your order is complete!";
	String warningMessage = "Please fill out all the required fields";
	String warningMessageInvalidCard = "Please enter a valid card number.";
	String warningMessageInvalidEmail = "Please enter a valid email address.";
	
	String addressLine1 = "123 Main Street";
	String addressLine2 = "Unit 100";
	String city = "San Diego";
	String state = "CA";
	String zipCode = "92101";
	String phoneNumber = "6195557755";
	String email ="abc@gmail.com";
	String creditCardNumber = "12345678910111";
	String securityCode = "123";
	String expirationDate = "08/2023";

	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		
		wait = new WebDriverWait(driver,10);
		
		cpo = new CheckOutPage(driver);
		Assert.assertTrue(cpo.getItemsInCard().size() > 0, "Shopping Cart is empty!!!");
	}

	@Test(description = "Enter valid input in all text boxes and validate the success message", enabled = true)
	public void validateTextBoxes() 
	{
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);
		
		hpo = new HomePage(driver);
		
		wait.until(ExpectedConditions.visibilityOf(hpo.getOrderSummary()));

		Assert.assertEquals(hpo.getActualMessage().getText(), successMessage);
		
	}
	
	@Test(description = "Leave email field empty, fill out the rest with valid input", enabled = true)
	public void validateEmail()
	{
		email = ""; 
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);

		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessage);
	}
	
	@Test(description = "Leave card number field empty, fill out the rest with valid input", enabled = true)
	public void validateCardNumber()
	{
		
		creditCardNumber = ""; 
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);

		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessage);
	}
	
	@Test(description = "Leave both email and card number fields empty, fill out the rest with valid input", enabled = true)
	public void validateEmailandCardNumber()
	{
		email = "";
		creditCardNumber = "";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);

		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessage);
	}
	
	@Test(description = "Leave phone number field empty, fill out the rest with valid input", enabled = true)
	public void validateSuccessWithoutPhoneNumber()
	{
		phoneNumber = "";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);
		
		hpo = new HomePage(driver);
		
		wait.until(ExpectedConditions.visibilityOf(hpo.getOrderSummary()));

		Assert.assertEquals(hpo.getActualMessage().getText(), successMessage);
	}
	
	@Test(description = "Leave zip code and phone number fields empty, fill out the rest with valid input", enabled = true)
	public void validateUnsuccessfulCheckOutWithoutZipCode()
	{
		zipCode = "";
		phoneNumber = "";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);

		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessage);
	}
	
	//TODO repeat the above test case for all fields
	
	@Test(description = "Enter a 13 digit card number", enabled = true)
	public void validateInvalidCardNumber()
	{
		// Assuming card number must be 14 digit long, only numbers

		creditCardNumber = "1234567891012";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);
		
		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessageInvalidCard);
		
	}
	
	@Test(description = "Enter letters in card number field", enabled = true)
	public void lettersInCardNumberField()
	{
		creditCardNumber = "abcdefghijklm";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);
		
		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessageInvalidCard);
	}
	
	@Test(description = "Enter an invalid email address", enabled = true)
	public void invalidEmailAddress()
	{
		email = "abc";
		
		cpo.completeCheckOut(addressLine1, addressLine2, city, state, zipCode, phoneNumber, email, creditCardNumber, securityCode, expirationDate);
		
		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessageInvalidCard);
		
		Assert.assertEquals(cpo.getWarningMessage().getText(), warningMessageInvalidEmail);
	}

	
	@AfterTest
	public void teardown()
	{
		driver.close();
		driver = null;
	}
}
