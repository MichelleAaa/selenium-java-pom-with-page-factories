package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="[@id=\"cw-top-navbar\"]/li[1]/div[1]/span[1]")
	public WebElement newCars;
	
	@FindBy(xpath="//*[@id=\"cw-top-navbar\"]/li[1]/div[2]/div/div[1]/div[1]/ul/li[1]/a")
	public WebElement findNewCars;
	
	public NewCarsPage findNewCar() {
		
		new Actions(driver).moveToElement(newCars).perform();
		findNewCars.click();//here we pass on the driver element so we can move to an element. .perform() is what causes the action to occur. After performing the action we are able to click.(This is being used to hover over a navbar, which opens to expose the findNewCars element. Then we click on it.)
		return new NewCarsPage(driver);
	}
}
