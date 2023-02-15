package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage
{
	@FindBy (xpath = "//input[@id='ap_customer_name']")
	private WebElement yourName;
	@FindBy (xpath = "//input[@id='ap_phone_number']")
	private WebElement yourContact;
	@FindBy (xpath = "//input[@id='ap_email']")
	private WebElement yourEmail;
	@FindBy (xpath = "//input[@id='ap_password']")
	private WebElement yourPassword;
	@FindBy (xpath = "//input[@id='continue']")
	private WebElement continueBtn;
	
	public SignUpPage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	public void enterName(String name) 
	{
		yourName.sendKeys(name);
	}
	public void enterContact(String contact) 
	{
		yourContact.sendKeys(contact);
	}
	public void enterEmail(String email) 
	{
		yourEmail.sendKeys(email);
	}
	public void enterPassword(String password) 
	{
		yourPassword.sendKeys(password);
	}
	public void clickOnContinue() 
	{
		continueBtn.click();
	}
}
