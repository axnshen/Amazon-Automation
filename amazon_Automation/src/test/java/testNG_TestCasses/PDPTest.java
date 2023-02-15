package testNG_TestCasses;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import POM_Pages.HomePage;
import POM_Pages.LoginPage;
import POM_Pages.ProductDisplayPage;
import POM_Pages.SearchResultsPage;
import utils.Utility;

public class PDPTest 
{
	WebDriver driver ;
	HomePage home ;
	LoginPage log ;
	SearchResultsPage search;
	ProductDisplayPage pdp;
	SoftAssert sa ;
	String TestID;
	
	@BeforeTest
	public void launchBrowser()
	{		
		System.setProperty("webdriver.edge.driver", "C:\\Selenium_Edge\\msedgedriver.exe");
		driver = new EdgeDriver();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeClass
	public void createPOMObjects() 
	{
		home = new HomePage(driver);
		log = new LoginPage(driver);
		search = new SearchResultsPage(driver); 
		pdp =  new ProductDisplayPage(driver);
		sa = new SoftAssert();
	}
	@BeforeMethod
	public void enterURL() throws EncryptedDocumentException, IOException 
	{
		driver.get("https://www.amazon.in/");
		home.clickOnAccountAndList();
		log.enterEmailOrMobileNo(Utility.getTestData("LoginTestData", 1, 1));
		log.clickOnContinue();
		log.enterPassword(Utility.getTestData("LoginTestData", 1, 2));
		log.clickOnSignIn();
	}	
	@Test
	public void test1_VerifyTheProductTest() throws EncryptedDocumentException, IOException
	{
		TestID = "test1_VerifyTheProductTest";
		home.searchAProduct("gamepad");
		String expectedTitle1 = "Amazon.in : gamepad";
		String actualTitle1 = driver.getTitle();
		sa.assertEquals(actualTitle1, expectedTitle1);
		
		search.clickOnProduct();
		Utility.switchWindows(driver);
		String expectedProductName = "Amkette Evo Gamepad Pro 4 for Android | Instant Play | Bluetooth 4.0 | Extendable Smartphone Clamp | Works with COD, Mobile Legends, BGMI, LOLWR and Many More | (Iphone and Mediatek Devices not Supported) (Black)";
		pdp.getProductName();
		sa.assertEquals(pdp.getProductName(), expectedProductName);
		pdp.clickOnAddToCart();
		home.clickOnCart();
		
		sa.assertAll();
	}	
	@AfterMethod
	public void getBackToHomePage(ITestResult result) throws IOException 
	{
		home.selectSignOutFromAccAndListDropdown();
		if(ITestResult.FAILURE==result.getStatus()) 
		{
			Utility.captureScreenshot(driver,TestID);
		}
	}
	@AfterClass
	public void removeObjects() 
	{
		home=null;
		log=null;
		search = null;
		pdp = null;
	}
	@AfterTest
	public void quitBrowser() 
	{
		driver.quit();
		driver=null;
		System.gc();  //garbage collector
	}

}
