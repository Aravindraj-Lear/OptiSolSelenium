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

public class AmazonCartPage extends CommonUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']/span")
    private WebElement subtotalAmountElement;

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteButton;

    public AmazonCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSubtotalAmount() {
    	try {
            waitForVisibilityOfElement(subtotalAmountElement, 10);
            takeScreenshot("screenshot");
            return subtotalAmountElement.getText().trim();
        } catch (Exception e) {
            Reporter.log("Failed to get subtotal amount: " + e.getMessage());
            return "";
        }
    }

    public void clickDeleteButton() {
        try {
            waitForElementToBeClickable(deleteButton, 10);
            deleteButton.click();
            takeScreenshot("screenshot");
            Reporter.log("Clicked on Delete button");
        } catch (Exception e) {
            Assert.fail("Failed to click on Delete button. Error: " + e.getMessage());
        }
    }
}
