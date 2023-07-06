package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;


public class BaseTest {
	
	public WebDriver driver;
	public Logger log = Logger.getLogger(BaseTest.class.getName());
	public Properties Config = new Properties();
	public FileInputStream fis;
	public ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\users-for-way2automation-test.xlsx");
	public WebDriverWait wait;
	static WebElement dropdown;
	

	@BeforeMethod
	public void setUp() {
		
			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j2.properties");
			
			
//			Load Config:
			try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\Config2.properties");
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
			Config.load(fis);
			log.info("Config Properaties Loaded.");
			} catch(IOException e) {
				e.printStackTrace();
			}
		
//			Launch Browser:
			if(Config.getProperty("browser").equals("chrome")) {
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				log.info("Chrome Browser Launched");
			} else if(Config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Browser Launched");
			} 
			
			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigating to the URL : " + Config.getProperty("testsiteurl"));
			DbManager.setMysqlDbConnection();
			log.info("Database connection established");
//			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
			wait = new WebDriverWait(driver, Config.getProperty("explicit.wait"));//5 seconds
			
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		log.info("Test Execution Completed.");
	}
	
//	fileName and captureScreenshot method are from the ScreenshotUtil file. We moved it here because now our driver is no longer static.
	public String srcfileName;
	
	public void captureScreenshot() {

		Date d = new Date();
		
		srcfileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\target\\reports\\" + srcfileName));
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + srcfileName));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	This will capture a screenshot only for that specific element:
	public static void captureElementScreenshot(WebElement element) {

		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshot\\" + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
