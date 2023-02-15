package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	@FindBy (xpath = "//input[@id='ap_email']")
	private WebElement emailOrMobileNo;
	@FindBy (xpath = "//input[@id='continue']")
	private WebElement continueBtn;
	@FindBy (xpath = "//input[@id='ap_password']")
	private WebElement password;
	@FindBy (xpath = "//input[@id='signInSubmit']")
	private WebElement signInBtn;
	@FindBy (xpath = "//a[@id='createAccountSubmit']")
	private WebElement createAccountBtn;
	@FindBy (xpath = "//a[@id='auth-fpp-link-bottom']")
	private WebElement forgotPasswordLink;
	@FindBy (xpath = "//input[@type='checkbox']")
	private WebElement keepMeSignedInCheckBox;
	@FindBy (xpath = "//span[@class='a-list-item']")
	private WebElement invalidCredentialsErrorMsg;
	@FindBy (xpath = "//a[@class='a-link-nav-icon']")
	private WebElement amazonLogo;
	
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailOrMobileNo(String emailOrmobile) 
	{
		emailOrMobileNo.sendKeys(emailOrmobile);
	}
	public void enterPassword(String pass) 
	{
		password.sendKeys(pass);
	}
	public void clickOnContinue() 
	{
		continueBtn.click();
	}
	public void clickOnSignIn() 
	{
		signInBtn.click();
	}
	public String getInvalidPasswordErrorMsg()
	{
		return invalidCredentialsErrorMsg.getText();
	}
	public void redirectToHomePage() 
	{
		amazonLogo.click();
	}
	public String getInvalidMobileNoErrorMsg() 
	{
		return invalidCredentialsErrorMsg.getText();
	}

}
