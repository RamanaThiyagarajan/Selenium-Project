package SeleniumJava.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.pageobjects.CartPage;
import SeleniumJava.pageobjects.ProductCatalogue;
import SeleniumJava.TestComponents.*;

public class ErrorValidations extends BaseTest {
	
	
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void ErrorMessageValidation() {
		
		 Lp.Loginapplication("vramana791@gmail.com", "Ramana@297");
		Assert.assertEquals("Incorrect email password.", Lp.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
String productName= "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = Lp.Loginapplication("vramana791@gmail.com", "Ramana@2397");

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProducttoCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
	
}
