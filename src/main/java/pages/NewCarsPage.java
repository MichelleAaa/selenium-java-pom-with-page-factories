package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class NewCarsPage extends BasePage {

	public NewCarsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "Hyundai")
	private WebElement hyundai;

	@FindBy(linkText = "Maruti Suzuki")
	private WebElement maruti;

	@FindBy(linkText = "Toyota")
	private WebElement toyota;

	@FindBy(linkText = "Kia")
	private WebElement kia;

//	On the NewCarsPage there are links for each car types. They would each take us to a different page.
	
	public MarutiCarPage selectMarutiCar() {

		maruti.click();
	
		return new MarutiCarPage(driver);
		
	}

	public void selectToyotaCar() {

		toyota.click();
	}

	public void selectKiaCar() {

		kia.click();
	}

	public void selectHyundaiCar() {

		hyundai.click();
	}
}
