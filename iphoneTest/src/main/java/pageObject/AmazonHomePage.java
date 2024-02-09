package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AmazonHomePage extends CommonUtils {
  
	// Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String searchText) {
        try {
            waitForVisibilityOfElement(searchBox, 10);
            searchBox.sendKeys(searchText);
            takeScreenshot("screenshot");
            Reporter.log("Entered search text: " + searchText);
        } catch (Exception e) {
            Assert.fail("Failed to enter search text: " + searchText + ". Error: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            waitForElementToBeClickable(searchButton, 10);
            takeScreenshot("screenshot");
            searchButton.click();
            Reporter.log("Clicked on search button");
        } catch (Exception e) {
            Assert.fail("Failed to click search button. Error: " + e.getMessage());
        }
    }

}
