package base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// We are testing multiple car pages, such as Toyota, Hyundai, etc. But all of these car pages have common functionalities. So we are adding them here, to the CarBase:
public class CarBase {

	WebDriver driver;

	public CarBase(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[2]/div/h1")
	public WebElement carTitle;
 
	public String getCarTitle() {

		return carTitle.getText();
	}

	@FindBy(xpath = "//ul/li/div/div[1]/div/div[1]/span[1]")
	public List<WebElement> carPrice;

	@FindBy(xpath = "//div[1]/ul/li/div/div[1]/div/a/h2")
	public List<WebElement> carNames;

	public ArrayList<String> list = new ArrayList<String>();

	public void getCarNameAndPrice() {

		for (int i = 0; i < carNames.size(); i++) {

			String text = carNames.get(i).getText() + "  price is:  " + carPrice.get(i).getText();
			list.add(text);
			// System.out.println(text);

		}

		for (String li : list) {

			System.out.println(li);
		}

	}

	public List<String> getCarNameAndPriceList() {

		for (int i = 0; i < carNames.size(); i++) {

			String text = carNames.get(i).getText() + "  price is:  " + carPrice.get(i).getText();
			list.add(text);
			// System.out.println(text);

		}

		for (String li : list) {

			System.out.println(li);
		}
		
		return list;

	}

	public void updateCarPriceInFile(String fileName) {

		String info = fileName + " car brand name and price\n";
		File file = new File(fileName);
		try {

			FileWriter fw = new FileWriter(file, true);

			// if you want to write the linesperator ("\n) as they are in the txt you should
			// use the following Code:

			fw.write(info);
			String lineSeparator = System.getProperty("line.separator");

			// instead you could only use:

			for (int i = 0; i < list.size(); i++) {
				fw.write(list.get(i));
				fw.write(lineSeparator);
			}

			fw.flush();
			fw.close();
			FileUtils.copyFile(file, new File(".//carprice//" + fileName + ".txt"));
			FileUtils.deleteQuietly(file);
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
