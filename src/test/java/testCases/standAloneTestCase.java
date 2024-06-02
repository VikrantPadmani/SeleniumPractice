package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.Landing_Page;
import pageObjects.ProductCatalogue1;


public class standAloneTestCase {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
	
		Landing_Page landingPage = new Landing_Page(driver);
		landingPage.goTo1();
		landingPage.loginApplication("vikrant123@gmail.com", "Shiv@12345");
		
		ProductCatalogue1 productCatalogue = new ProductCatalogue1(driver);
		List<WebElement> producs = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		
		/*
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
				
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String confirmText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."), match);
		*/
		
		
	}

}
