package testNG_TestCasses;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
import utils.Utility;


public class LoginTestWithValidData 
{
	WebDriver driver ;
	HomePage home ;
	LoginPage log ;
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
		sa = new SoftAssert();
	}
	@BeforeMethod
	public void enterURL() 
	{
		driver.get("https://www.amazon.in/");
		home.clickOnAccountAndList();
	}
	@Test
	public void test1_VerifyLoginByPassingValidEmailAndPassword() throws EncryptedDocumentException, IOException
	{
		TestID = "test1_VerifyLoginByPassingValidEmailAndPassword";
		log.enterEmailOrMobileNo(Utility.getTestData("LoginTestData", 1, 1));
		log.clickOnContinue();
		log.enterPassword(Utility.getTestData("LoginTestData", 1, 2));
		log.clickOnSignIn();
		String expectedTitle = "Amazon Sign In";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}	
	@Test
	public void test2_VerifyLoginByPassingValidMobileAndPassword() throws EncryptedDocumentException, IOException 
	{
		TestID = "test2_VerifyLoginByPassingValidMobileAndPassword";
		log.enterEmailOrMobileNo(Utility.getTestData("LoginTestData", 2, 1));
		log.clickOnContinue();
		log.enterPassword(Utility.getTestData("LoginTestData", 2, 2));
		log.clickOnSignIn();
		String expectedTitle = "Amazon Sign In";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
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
	}
	@AfterTest
	public void quitBrowser() 
	{
		driver.close();
		driver=null;
		System.gc();  //garbage collector
	}

}
