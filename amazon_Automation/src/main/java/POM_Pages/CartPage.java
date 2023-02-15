package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage 
{
	@FindBy (xpath = "//div[@id='sc-active-cart']//div[2]//div[2]//a//span[2]")
	private WebElement productName;
	
	public CartPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getProductName() 
	{
		return productName.getText();
	}
}
