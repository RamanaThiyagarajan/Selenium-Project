package SeleniumJava.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.pageobjects.CartPage;
import SeleniumJava.pageobjects.CheckoutPage;
import SeleniumJava.pageobjects.ConfirmationPage;
import SeleniumJava.pageobjects.OrderPage;
import SeleniumJava.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest{
	
	

	String productName= "ZARA COAT 3";
	@Test(dataProvider= "getData", groups={"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub        
		//Used in CI/CD Integration
		
		ProductCatalogue productCatalogue = Lp.Loginapplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProducttoCart(input.get("products"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(input.get("products"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.SelectCountry("india");
		ConfirmationPage confirmationMessage = checkoutPage.submitOrder();

		String confirmMessage = confirmationMessage.getConfirmationmessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue = Lp.Loginapplication("vramana791@gmail.com", "Ramana@2397");
		OrderPage orderPage= productCatalogue.goToOrderPage();
		
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[] getData() throws IOException {
			
		List<HashMap<String, String>> data = getJsontoMap("//src//test//java//SeleniumJava//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "vramana791@gmail.com");
//	map.put("password", "Ramana@2397");
//	map.put("products", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("products", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{"vramana791@gmail.com", "Ramana@2397","ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
//	}
	

}