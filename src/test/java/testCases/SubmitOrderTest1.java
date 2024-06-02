package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseStructure.baseTestFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.Landing_Page;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue1;

public class SubmitOrderTest1 extends baseTestFile {

	String productName = "ZARA COAT 3";

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		/*
		 * //(1)Accessing Landing Page methods... Landing_Page landingPage = new
		 * Landing_Page(driver);
		 * landingPage.loginApplication("vikrant123@gmail.com","Shiv@12345");
		 * 
		 * //(2)Accessing Product Catalogue Methods ProductCatalogue1 productCatalogue =
		 * new ProductCatalogue1(driver); List<WebElement> products =
		 * productCatalogue.getProductList();
		 * productCatalogue.addProductToCart(productName);
		 * productCatalogue.goToCartPage();
		 */

		// Alternate of 1 and 2

		ProductCatalogue1 productCatalogue = landingPage.loginApplication("vikrant123@gmail.com", "Shiv@12345");
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

		// Accessing Cart Page Methods.
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match); // validation cannot go in page object file it stay with test file.
		CheckOutPage checkout = cartPage.goToCheckOut();
		checkout.selectCountry("india");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 300)"); // if the element is on bottom.

		Thread.sleep(3000);
		ConfirmationPage confirmationPage = checkout.submitOrder();

		String confirmText = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitNewOrder(String email, String password, String productName) throws IOException, InterruptedException {

		ProductCatalogue1 productCatalogue = landingPage.loginApplication(email, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

		// Accessing Cart Page Methods.
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match); // validation cannot go in page object file it stay with test file.
		CheckOutPage checkout = cartPage.goToCheckOut();
		checkout.selectCountry("india");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 300)"); // if the element is on bottom.

		Thread.sleep(3000);
		ConfirmationPage confirmationPage = checkout.submitOrder();

		String confirmText = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue1 productCatalogue = landingPage.loginApplication("vikrant123@gmail.com", "Shiv@12345");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "vikrant123@gmail.com", "Shiv@12345", "ZARA COAT 3" },
				{ "anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };
	}

}
