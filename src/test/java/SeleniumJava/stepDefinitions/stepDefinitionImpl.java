package SeleniumJava.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.pageobjects.CartPage;
import SeleniumJava.pageobjects.CheckoutPage;
import SeleniumJava.pageobjects.ConfirmationPage;
import SeleniumJava.pageobjects.LandingPage;
import SeleniumJava.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
     
	public LandingPage Lp;
	public ProductCatalogue productCatalogue;
	ConfirmationPage confirmationMessage;
	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {

		Lp= launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue = Lp.Loginapplication(username,password);
		
	}
	
	 @When("^I add (.+) to Cart$")
	 public void I_add_product_to_Cart(String productName) throws InterruptedException {
		 List<WebElement> products = productCatalogue.getProductsList();
			productCatalogue.addProducttoCart(productName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_order(String productName) {
		 
		 CartPage cartPage = productCatalogue.goToCartPage();

			boolean match = cartPage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goToCheckout();
			checkoutPage.SelectCountry("india");
			confirmationMessage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationpage(String string) {
		 String confirmMessage = confirmationMessage.getConfirmationmessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String strArg1) throws Throwable {
	   
	    	Assert.assertEquals(strArg1, Lp.getErrorMessage());
	    	driver.close();
	 }
}
