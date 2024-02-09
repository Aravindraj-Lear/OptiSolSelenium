package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class BaseClass {
	
	public static WebDriver driver;
	String currentWorkingDir = System.getProperty("user.dir");
	public static String screenshotsFolderPath;
	
	@BeforeTest
	public void ScreenshotFolderSetup() {
		// Generate folder path for screenshots with timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        screenshotsFolderPath = "screenshots/" + timestamp;

        // Create the folder to store screenshots
        new File(screenshotsFolderPath).mkdirs();
	}
	
	@BeforeMethod
	public void LaunchBrowser() {

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		Reporter.log("Browser Launched");

	}

	 @AfterMethod
     public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            Reporter.log("Browser closed");
	        }
     }

	
	@DataProvider(name = "testData")
	public Object[] testData() {
		String excelFilePath = currentWorkingDir + "/TestData/ExcelTestData.xlsx";
		String sheetName = "Search";
		return ExcelUtils.readTestData(excelFilePath, sheetName);
	}
	
}
