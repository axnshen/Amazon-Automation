package POM_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	public Actions move;
	@FindBy (xpath = "//span[text()='Account & Lists']")
	private WebElement accountAndList;
	@FindBy (xpath = "//a[text()='Start here.']")
	private WebElement startHere;
	@FindBy (xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchBox;
	@FindBy (xpath = "//input[@id='nav-search-submit-button']")
	private WebElement searchBtn;
	@FindBy (xpath = "//a[@id='nav-cart']")
	private WebElement yourCart;
	@FindBy (xpath = "//a[@id='nav-item-signout']")
	private WebElement signOut;
	
	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
		move = new Actions(driver);
	}
	
	public void selectStartHereFromAccAndListDropdown() 
	{
		move.moveToElement(accountAndList).moveToElement(startHere).click().build().perform();
	}
	public void selectSignOutFromAccAndListDropdown() 
	{
		move.moveToElement(accountAndList).moveToElement(signOut).click().build().perform();
	}
	public void clickOnAccountAndList() 
	{
		accountAndList.click();
	}
	public void searchAProduct(String product) 
	{
		searchBox.sendKeys(product);
		searchBtn.click();
	}
	public void clickOnCart() 
	{
		yourCart.click();
	}

}
