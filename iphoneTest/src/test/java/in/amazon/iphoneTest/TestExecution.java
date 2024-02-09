package in.amazon.iphoneTest;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.AmazonCartPage;
import pageObject.AmazonHomePage;
import pageObject.AmazonItemPage;
import pageObject.AmazonSearchResultPage;
import utils.CommonUtils;
import utils.ExcelTestData;
import utils.ExcelUtils;

public class TestExecution extends CommonUtils {

	@Test(dataProvider = "testData")
	public void shopping(ExcelTestData testData) {
		
		Reporter.log("Test Case: Buying in Amazon");
		Reporter.log("============================================");
		Reporter.log("1.Navigate to the page https://www.amazon.in/.");
		Reporter.log("2. Utilize the search box to look for \"iPhone 13 (128GB) - Pink\" by reading the value from an Excel sheet.");
		Reporter.log("3. Assert that the displayed item title is \"iPhone 13 (128GB) - Pink\".");
		Reporter.log("4. Select the item \"iPhone 13 (128GB) - Pink\" then scroll down to About this item section ,then verify if the partial text matches \"15 cm (6.1-inch) Super Retina XDR display\".");
		Reporter.log("5. Add the item \"iPhone 13 (128GB) - Pink\" to the cart and validate whether the same item name and price are displayed.");
		Reporter.log("6. Confirm that the subtotal is equal to the price of 52,999.00 by reading the value from an Excel sheet.");
		Reporter.log("7. Delete the added item from the cart and verify whether the subtotal is now \"0.00\".");
		Reporter.log("8. Capture screenshots throughout the process and generate a report according to your preferences.");
		Reporter.log("============================================");
		Reporter.log(" ");
		
		AmazonHomePage amazonHomePage = new AmazonHomePage(driver);
		AmazonSearchResultPage amazonSearchResultPage = new AmazonSearchResultPage(driver);
		AmazonItemPage amazonItemPage = new AmazonItemPage(driver);
		AmazonCartPage amazonCartPage = new AmazonCartPage(driver);
		
		// Enter search text and click search button
		amazonHomePage.enterSearchText(testData.getSearchItem());
		amazonHomePage.clickSearchButton();
		
		// Verify iPhone Pink is present
		amazonSearchResultPage.verifyItemIsPresent("Apple iPhone 13 (128GB) - Pink");

		// Click on iPhone Pink
		amazonSearchResultPage.clickOnIphonePink();

		// Switch to the child window
		amazonSearchResultPage.switchToChildWindow();

		// Scroll to the middle of the desktop
		amazonItemPage.scrollToMiddleOfDesktop();

		// Check if the desired text is present
		boolean containsDesiredText = amazonItemPage.isDesiredTextPresent("15 cm (6.1-inch) Super Retina XDR display");
		Assert.assertTrue(containsDesiredText);
		Reporter.log("Verified '15 cm (6.1-inch) Super Retina XDR display' is present ");

		// Click the "Add to Cart" button
		amazonItemPage.clickAddToCartButton();

		// Click the "View Cart" button
		amazonItemPage.clickViewCartButton();

		// Verify the subtotal amount
		String amountString = amazonCartPage.getSubtotalAmount();
		Assert.assertEquals(amountString, testData.getSubtotal());
		Reporter.log("Verified Subtotal Amount equals to " + testData.getSubtotal());

		// Delete the item from the cart
		amazonCartPage.clickDeleteButton();

		// Verify the subtotal amount after deletion
		String amountString_AfterDelete = amazonCartPage.getSubtotalAmount();
		Assert.assertEquals(amountString_AfterDelete, "0.00");
		Reporter.log("Verified Subtotal Amount equals to 0.00");
		Reporter.log("");
		Reporter.log("Screenshots are saved in screenshots folder");
	}

}
