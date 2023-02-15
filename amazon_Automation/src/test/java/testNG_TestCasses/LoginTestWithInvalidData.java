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
import utils.Utility;

public class LoginTestWithInvalidData 
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
	public void test1_VerifyLoginByPassingInValidEmailAndPassword() throws EncryptedDocumentException, IOException
	{
		TestID = "test1_VerifyLoginByPassingInValidEmailAndPassword";
		log.enterEmailOrMobileNo(Utility.getTestData("LoginTestData", 3, 1));
		log.clickOnContinue();
		log.enterPassword(Utility.getTestData("LoginTestData", 3, 2));
		log.clickOnSignIn();
		String expectedError = "Your password is incorrect";
		Assert.assertEquals(log.getInvalidPasswordErrorMsg(), expectedError);
	}	
	@Test
	public void test2_VerifyLoginByPassingInValidMobileAndPassword() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		TestID = "test2_VerifyLoginByPassingInValidMobileAndPassword";
		log.enterEmailOrMobileNo(Utility.getTestData("LoginTestData", 4, 1));
		log.clickOnContinue();
		log.enterPassword(Utility.getTestData("LoginTestData", 4, 2));
		log.clickOnSignIn();
		String expectedError = "We cannot find an account with that mobile number";
		Assert.assertEquals(log.getInvalidMobileNoErrorMsg() , expectedError);
	}
	
	@AfterMethod
	public void getBackToHomePage(ITestResult result) throws IOException 
	{
		if(ITestResult.FAILURE==result.getStatus()) 
		{
			Utility.captureScreenshot(driver,TestID);
		}
		log.redirectToHomePage();
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
