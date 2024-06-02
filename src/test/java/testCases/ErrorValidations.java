package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseStructure.Retry;
import baseStructure.baseTestFile;

public class ErrorValidations extends baseTestFile {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void submitOrder() throws IOException, InterruptedException {

		landingPage.loginApplication("vikrant123@gmail.com", "Shiv@123456");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
//	@Test(groups = {"SecondErrorHandling"})
//	public void submitOrder2() throws IOException, InterruptedException {
//
//		landingPage.loginApplication("vikrant@gmail.com", "Shiv@123456");
//		landingPage.getErrorMessage();
//		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
//
//	}


}
