package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDisplayPage 
{
	@FindBy (xpath = "//input[@id='add-to-cart-button']")
	private WebElement addToCartBtn;
	@FindBy (xpath = "//span[@id='productTitle']")
	private WebElement productName;
	@FindBy (xpath = "//span[text()=' Cart ']")
	private WebElement cart;
	
	public ProductDisplayPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddToCart() 
	{
		addToCartBtn.click();
	}
	public String getProductName() 
	{
		return productName.getText();
	}
	public void clickOnCart() 
	{
		cart.click();
	}

}
