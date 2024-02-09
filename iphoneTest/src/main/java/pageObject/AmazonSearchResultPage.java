package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import utils.CommonUtils;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class AmazonSearchResultPage extends CommonUtils {

    // Locators
    @FindBy(xpath = "//span[normalize-space()='Apple iPhone 13 (128GB) - Pink']")
    private WebElement iphonePinkElement;

    public AmazonSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnIphonePink() {
        try {
            waitForElementToBeClickable(iphonePinkElement, 10);
            takeScreenshot("screenshot");
            iphonePinkElement.click();
            Reporter.log("Clicked on iPhone Pink");
        } catch (Exception e) {
            Assert.fail("Failed to click on iPhone Pink. Error: " + e.getMessage());
        }
    }
    
    public void verifyItemIsPresent(String itemName) {
    	
        try {
            waitForElementToBeClickable(iphonePinkElement, 10);
            takeScreenshot("screenshot");
            Assert.assertEquals(iphonePinkElement.getText(), itemName);
            Reporter.log(itemName + " is present");
        } catch (Exception e) {
            Assert.fail(itemName + " not present Error: " + e.getMessage());
        }

    }

  
}
