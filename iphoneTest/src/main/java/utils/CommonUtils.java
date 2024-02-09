package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class CommonUtils extends BaseClass {

    public WebElement waitForVisibilityOfElement(WebElement object, int Timeinsecs) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeinsecs));
        return wait.until(ExpectedConditions.visibilityOf(object));
    }

    public WebElement waitForElementToBeClickable(WebElement object, int Timeinsecs) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeinsecs));
        return wait.until(ExpectedConditions.elementToBeClickable(object));
    }
    
    public void switchToChildWindow() {
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();
        driver.switchTo().window(childWindow);
    }
    
    public void scrollToMiddleOfDesktop() {
        long viewportHeight = (long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + (viewportHeight / 2) + ")");
    }
    
    public void takeScreenshot(String filenamePrefix) {
        // Convert WebDriver object to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capture screenshot as byte array
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

        // Generate timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        // Specify the filename with timestamp
        String filename = filenamePrefix + "_" + timestamp + ".png";

        // Specify the path to save the screenshot
        Path destinationPath = Paths.get(screenshotsFolderPath, filename);

        try {
            // Write the byte array to a file
            Files.write(destinationPath, screenshotBytes);
            System.out.println("Screenshot saved: " + destinationPath);
        } catch (Exception e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

}
