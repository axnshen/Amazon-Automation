package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility 
{
	
	public static String getTestData(String sheetName, int row, int cell) throws EncryptedDocumentException, IOException 
	{
		String data; 
		
		String path  = "C:\\Users\\anshe\\eclipse-workspace\\amazon_Automation\\src\\main\\resources\\TestData\\AmazonTestData.xlsx";
		
		FileInputStream file = new FileInputStream(path);
		
		Workbook book = WorkbookFactory.create(file);
		try
		{
			data = book.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		}
		catch(IllegalStateException ise) 
		{
			double value = book.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
			long num  = (long) value ;
			data = Long.toString(num);
		}
		
		return data;
	}
	
	public static void captureScreenshot(WebDriver driver,String testID) throws IOException
	{
		TakesScreenshot ss = (TakesScreenshot)driver;
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH mm ss");
		Date date = new Date();
		String d = sdf.format(date);
	    File source = ss.getScreenshotAs(OutputType.FILE);
	    File path = new File("C:\\Users\\anshe\\eclipse-workspace\\amazon_Automation\\src\\main\\resources\\FailedScreenshot DATE"+testID+" TIME "+d+".png");
	    FileHandler.copy(source, path);
	}

	public static void switchWindows(WebDriver driver)
	{
		Set <String> s = driver.getWindowHandles();
		for(String i : s) 
		{
			driver.switchTo().window(i);
		}
		
	}
}
