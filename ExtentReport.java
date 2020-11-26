package seleniumTesting;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.SkipException;

public class ExtentReport {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public void beforeClass() {

		/*-------------- Extent Test code Snippet-------------------*/
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test result");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Self");
		extent.setSystemInfo("OS", "Windows");
		extent.attachReporter(htmlReporter);
		/*----------------------------------------------------------*/

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
		extent.flush();// Flush method is used to erase any previous data on the report and create a new report.
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			extentTest = extent.createTest("Fail test");
			extentTest.log(Status.FAIL, "Test method failed");
			Assert.fail("Executing failed Test Method");
			fail(verificationErrorString);
		}
	}

	@Test
	public void testPass() {
		driver.get("https://www.google.co.in");
		extentTest = extent.createTest("Successful test");
		extentTest.log(Status.PASS, "Test method success");
	}

	/*
	 * ------------To invoke fail test---------------
	 * 
	 * @Test public void testFail() {
	 * extentTest =extent.createTest("Fail test");
	 * extentTest.log(Status.FAIL, "Test method failed");
	 * Assert.fail("Executing failed Test Method"); }
	 */

	/*
	 * ------------To invoke skip test---------------
	 * 
	 * @Test public void testSkipped() { 
	 * extentTest =extent.createTest("Skipped test");
	 * extentTest.log(Status.SKIP,"Test method skipped"); 
	 * throw new SkipException("Executing skipped Test Method"); }
	 */

	@AfterMethod
	public void afterMethod() {
	}

}
