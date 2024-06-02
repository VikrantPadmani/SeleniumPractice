package stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import baseStructure.baseTestFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.Landing_Page;
import pageObjects.ProductCatalogue1;

public class StepDefinationImpl extends baseTestFile {

	public Landing_Page landingPage;
	public ProductCatalogue1 productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")

	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match); // validation cannot go in page object file it stay with test file.
		CheckOutPage checkout = cartPage.goToCheckOut();
		checkout.selectCountry("india");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 300)"); // if the element is on bottom.

		Thread.sleep(3000);
		confirmationPage = checkout.submitOrder();
	}

	@Then("{string} message is displayed on confirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmText = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void something_message_displayed_errorMessage(String string1) {
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
	}

}
