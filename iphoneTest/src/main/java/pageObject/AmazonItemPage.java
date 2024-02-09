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

public class AmazonItemPage extends CommonUtils {


    // Locators
    @FindBy(xpath = "//h1[contains(text(),'About this item')]/following-sibling::ul/li")
    private WebElement aboutThisItemElement;

    @FindBy(xpath = "(//span[@id='submit.add-to-cart']/span/input)[2]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@id='attach-sidesheet-view-cart-button']/span/input")
    private WebElement viewCartButton;

    public AmazonItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDesiredTextPresent(String desiredText) {
        try {
            waitForVisibilityOfElement(aboutThisItemElement, 10);
            takeScreenshot("screenshot");
            return aboutThisItemElement.getText().contains(desiredText);
        } catch (Exception e) {
            Reporter.log("Failed to check if desired text is present: " + e.getMessage());
            return false;
        }
    }

    public void clickAddToCartButton() {
        try {
            waitForElementToBeClickable(addToCartButton, 10);
            takeScreenshot("screenshot");
            addToCartButton.click();
            Reporter.log("Clicked on Add to Cart button");
        } catch (Exception e) {
            Assert.fail("Failed to click on Add to Cart button. Error: " + e.getMessage());
        }

    }

    public void clickViewCartButton() {
        try {
            waitForElementToBeClickable(viewCartButton, 10);
            takeScreenshot("screenshot");
            viewCartButton.click();
            Reporter.log("Clicked on View Cart button");
        } catch (Exception e) {
            Assert.fail("Failed to click on View Cart button. Error: " + e.getMessage());
        }
    }

   
}
