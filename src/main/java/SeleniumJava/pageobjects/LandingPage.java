package SeleniumJava.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumJava.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement username = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;

	public ProductCatalogue Loginapplication(String email, String password) {

		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getErrorMessage() {
		waitForTheElementToAppear(errormessage);
		String errorMessage = errormessage.getText();
		return errorMessage;
	}
	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

}
