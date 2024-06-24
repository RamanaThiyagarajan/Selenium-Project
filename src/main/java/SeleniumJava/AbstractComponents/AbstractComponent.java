package SeleniumJava.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumJava.pageobjects.CartPage;
import SeleniumJava.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {

		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForTheElementToAppear(By findby) {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitForTheElementToAppear(WebElement findby) {

		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void waitForTheElementToDisappear() throws InterruptedException {

		Thread.sleep(1000);
//		 WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		 Wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
