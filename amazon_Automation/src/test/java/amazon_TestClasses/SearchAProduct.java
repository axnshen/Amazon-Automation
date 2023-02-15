package amazon_TestClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import POM_Pages.CartPage;
import POM_Pages.HomePage;
import POM_Pages.ProductDisplayPage;
import POM_Pages.SearchResultsPage;

public class SearchAProduct 
{
	static SearchResultsPage sp;
	static HomePage home;
	static ProductDisplayPage pdp;
	static CartPage cp;
	static WebDriver driver;
	
	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Selenium_Edge\\msedgedriver.exe");
		
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		home = new HomePage(driver);
		sp = new SearchResultsPage(driver);
		home.searchAProduct("gaming laptop");
		sp.clickOnProduct();
		String product1 = pdp.getProductName();
		System.out.println("product name 1"+product1);
		pdp.clickOnAddToCart();
		pdp.clickOnCart();
		String product2 = cp.getProductName();
		System.out.println("product name 2"+product2);
		
		
		
		
		
		
		
		
	}
	

}
