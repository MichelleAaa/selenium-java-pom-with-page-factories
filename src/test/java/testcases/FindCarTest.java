package testcases;

import org.testng.annotations.Test;
import pages.HomePage;

import base.BaseTest;

public class FindCarTest extends BaseTest {

	
	@Test
	public void findCarTest() {
		
		HomePage home = new HomePage(driver); // The driver is coming from BaseTest.
		home.findNewCar().selectHyundaiCar();//We call the selectHyndaiCar method, which will cause it to click on the Hyundai option, which brings us to a new page.
//		you could have multiple test cases, for each of the selectcartype methods.
		
		Thread.sleep(2000);
		
	}
}
