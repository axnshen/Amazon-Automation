package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage 
{
	@FindBy (xpath = "(//div[@class='rush-component'])[1]")
	private WebElement firstProduct;
	
	public SearchResultsPage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	public void clickOnProduct() 
	{
		firstProduct.click();
	}
	

}
