package amazon_TestClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import POM_Pages.HomePage;


public class Login_Test 
{
	private WebDriver driver;
	private HomePage home;
	private LoginPage login;
	
	
	@Parameters("browserName")
	@BeforeTest
	public void launchBrowser(String browser)
	{
		
		if(browser.equals("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Chrome\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if(browser.equals("Firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium_Firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if(browser.equals("Edge")) 
		{
			System.setProperty("webdriver.edge.driver", "C:\\Selenium_Edge\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeClass
	public void createPOMObjects() 
	{
		 text = new FB_CreatePage(driver);
		 home = new FB_Homepage(driver);
		
	}
	@BeforeMethod
	public void enterURL() 
	{
		driver.get("https://www.facebook.com/");
		
	}
	@Test
	public void test1_ValidateLoginByPassingValidCredentials()
	{
		
		home.enterEmailOrPhone("ankitnshende@gmail.com");
		home.enterPassword("Ankit@123");
		home.clickOnLogin();
		
		String currentTitle=driver.getTitle();
		System.out.println(currentTitle);
		String expectedTitle="Facebook � log in or sign up";
		Assert.assertEquals(currentTitle,expectedTitle,"test1_ValidateLoginByPassingValidCredentials is verified");
		System.out.println("test1_ValidateLoginByPassingValidCredentials");
	}
	@AfterMethod
	public void getBackToHomePage() 
	{
		driver.navigate().back();
	}
	@AfterClass
	public void removeObjects() 
	{
		home=null;
		text=null;
	}
	@AfterTest
	public void quitBrowser() 
	{
		driver.close();
		driver=null;
		System.gc();  //garbage collector
	}
}
