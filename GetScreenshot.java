package seleniumTesting;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Note: Uncomment the comments starting from '//**' if you wish to capture multiple Screenshots
public class GetScreenshot {

	public WebDriver driver;
	// **int count = 0;  //Multiple screenshots

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver",
				"F:\\SoftInstall\\Selenium eclipse\\Selenium jars\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize(); // maximize driver window to the max screen resolution
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// above code comes into play when an element that is being searched for is not available,
		// then the implicit wait is triggered and that element is polled up to 30sec
	}

	@AfterClass
	public void afterClass() {
		driver.quit();// closes all the browser windows and ends the WebDriver session
	}

	@Test(priority = 1)
	public void login() throws Exception {

		driver.get("https://www.google.co.in/");
		// **count++;  //Multiple screenshots
		// **getscreenshot(count);  //Multiple screenshots
		getscreenshot();   //for single screenshots
	}
	
	//**public void getscreenshot(int count) throws Exception  //Multiple screenshots
	public void getscreenshot() throws Exception {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//** FileUtils.copyFile(srcfile,new File("F:\\SoftInstall\\Selenium eclipse\\Selenium SS\\Screenshot" + count + ".png"));  //Multiple screenshots
		
		//for single screenshot
		FileUtils.copyFile(srcfile,new File("F:\\SoftInstall\\Selenium eclipse\\Selenium SS\\Screenshot.png")); //enter your path you wish to store the screenshot
	}
}
