package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class Landing_Page extends AbstractComponent {

	WebDriver driver;

	public Landing_Page(WebDriver driver) {
		super(driver);
		// Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// (1) Simple Methods without any other object accesing.
	/*
	 * public void loginApplication(String email, String password) {
	 * userEmail.sendKeys(email); passwordEle.sendKeys(password); submit.click(); }
	 */
	// (2) After login Application it will trigger to Product Catalogue Object Here.
	public ProductCatalogue1 loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue1 productCatalogue = new ProductCatalogue1(driver);
		return productCatalogue;
	}

	public void goTo1() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
