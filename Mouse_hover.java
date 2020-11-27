package seleniumTesting;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Mouse_hover {
	WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

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
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail("Executing failed Test Method");
			fail(verificationErrorString);
		}
	}

	@Test(priority = 1)
	public void functionalityCheck() throws Exception {
		driver.get("https://www.myntra.com/");

		/*
		 * Thread.sleep is not a good practice to be used . Try to use Explicit wait as
		 * much as possible. Thats because, In case the element is found much before the
		 * specified duration, the script will still wait for the time duration to
		 * elapse, thereby increasing the execution time of the script.
		 */
		Thread.sleep(500);

		// Action method to move the cursor over specific element.
		WebElement menuOption = driver.findElement(By.cssSelector(".desktop-navContent:nth-child(1) .desktop-main"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();

		// Once hovered click on a element
		WebElement selectMenuOption = driver.findElement(By.linkText("Casual Shirts"));
		actions.moveToElement(selectMenuOption).perform();
		selectMenuOption.click();
		Thread.sleep(500);
	}

}
