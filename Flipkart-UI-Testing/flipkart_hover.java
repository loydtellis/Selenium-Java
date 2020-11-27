package seleniumTesting;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class flipkart_hover {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	static WebDriver driver;
	int count = 0;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver",
				"F:\\SoftInstall\\Selenium eclipse\\Selenium jars\\chromedriver.exe");
		driver = new ChromeDriver();
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName().toUpperCase();
		String os = caps.getPlatform().toString();
		String version = caps.getVersion().toString();

		/*-------------------Extent Report---------------------------------*/
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Flipkart Automation Report");
		htmlReporter.config().setReportName("Flipkart Automation Test result");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "None");
		extent.setSystemInfo("OS", os);
		extent.setSystemInfo("Browser Name", browserName);
		extent.setSystemInfo("Browser Version", version);
		extent.attachReporter(htmlReporter);

		extent.attachReporter(htmlReporter);

		/*-----------------------------------------------------------------*/

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();// closes all the browser windows and ends the WebDriver session
		extent.flush();// Flush method is used to erase any previous data on the report and create a new report.
	}

	@Test(priority = 1)
	public void login() throws Exception {

		extentTest = extent.createTest("Check Login Functionalities");
		driver.get("https://www.flipkart.com/");
		
		
		/*-----------------Getting data from Excel---------------*/
		File src = new File("F:\\SoftInstall\\Selenium eclipse\\excel\\credentials.xlsx");//Enter path of your Excel sheet.
		FileInputStream fiss = new FileInputStream(src);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet sh1 = wb.getSheetAt(0);
		/*------------------------------------------------------*/
		
		String username = sh1.getRow(0).getCell(0).getStringCellValue();//gets value from Row 1 column A 
		String password = sh1.getRow(0).getCell(1).getStringCellValue();//gets value from Row 1 column B
		//Simultaneously you can get any number of data from Excel and use it as required.
		
		// Below output code is only for reference, you can comment it when not in use!
		System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
		System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
		
		//Entering the username
		driver.findElement(By.cssSelector(".IiD88i:nth-child(1) > .\\_2IX_2-")).click();
		driver.findElement(By.cssSelector(".IiD88i:nth-child(1) > .\\_2IX_2-")).clear();
		driver.findElement(By.cssSelector(".IiD88i:nth-child(1) > .\\_2IX_2-")).sendKeys(username);
		
		//Entering the password
		driver.findElement(By.cssSelector("._3mctLh")).click();
		driver.findElement(By.cssSelector("._3mctLh")).clear();
		driver.findElement(By.cssSelector("._3mctLh")).sendKeys(password);
		
		driver.findElement(By.cssSelector(".\\_3AWRsL")).click(); //clicking on Login
		extentTest.log(Status.PASS, "Login success");

	}
	@Test (priority = 2)
	public void functionalityCheck() throws Exception {
		WebDriverWait wait=new WebDriverWait(driver, 200);
		extentTest = extent.createTest("Checking Functionalities");
		
		//Not a good practice to use Thread.sleep. Try to use Explicit wait as much as possible
		Thread.sleep(500);
		//Action method to move the cursor over specific element.
		WebElement menuOption = driver.findElement(By.xpath(".//div[2]/div/div/span[1][contains(text(),'Electronics')]")); 
		Actions actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();
		
		//Once hovered click on a element
		WebElement selectMenuOption = driver.findElement(By.linkText("Samsung"));
		actions.moveToElement(selectMenuOption).perform();
		selectMenuOption.click();
		Thread.sleep(500);
		extentTest.log(Status.PASS, "Action method passed");
		//wait.until(ExpectedConditions.visibilityOf());
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("VIEW ALL"))));
		driver.findElement(By.linkText("VIEW ALL")).click();//Clicking on view all, to see all products
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".\\_10UF8M:nth-child(4)"))));
		driver.findElement(By.cssSelector(".\\_10UF8M:nth-child(4)")).click();//clicking on Sort By: (high to low)
		
		// scroll down by 500 px. Not required. But why not!
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".\\_2pi5LC:nth-child(4) .\\_4rR01T"))));
		driver.findElement(By.cssSelector(".\\_2pi5LC:nth-child(4) .\\_4rR01T")).click();// click on a product
		
		//window handle to focus on the newly opened window in another tab
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle : allWindowHandles)
		{ 
		driver.switchTo().window(handle);
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".\\_3v1-ww"))));
		driver.findElement(By.cssSelector(".\\_3v1-ww")).click();// click on Add to Cart button
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".\\_2ObVJD"))));
		driver.findElement(By.cssSelector(".\\_2ObVJD")).click();// click on placer order
		extentTest.log(Status.PASS, "all success");
		

	}

	public void getscreenshot(int count) throws Exception {
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile,
				new File("F:\\SoftInstall\\Selenium eclipse\\Selenium SS\\Screenshot" + count + ".png"));
	}

	/*
	 * ------------To invoke fail test---------------
	 * 
	 * @Test public void testFail() { extentTest =extent.createTest("Fail test");
	 * extentTest.log(Status.FAIL, "Test method failed");
	 * Assert.fail("Executing failed Test Method"); }
	 */

	/*
	 * ------------To invoke skip test---------------
	 * 
	 * @Test public void testSkipped() { extentTest
	 * =extent.createTest("Skipped test");
	 * extentTest.log(Status.SKIP,"Test method skipped"); throw new
	 * SkipException("Executing skipped Test Method"); }
	 */
	public String geterrorscreenshot(String methodName) {
		String filename = geterrorscreenshotname(methodName);
		String dir = "F:\\SoftInstall\\Selenium eclipse\\Selenium_error_SS\\";
		// new File(dir).mkdirs();
		String path = dir + filename;
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("************************");
			System.out.println("ss stored at :" + path);
			System.out.println("************************");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String geterrorscreenshotname(String methodName) {
		Date d = new Date();
		String filename = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return filename;

	}

	//aesthetics of Extent report 
	@AfterMethod
	public void afterMethod(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		if (result.getStatus() == ITestResult.FAILURE) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occoured, Click to see Details:"
					+ " </font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
			String path = geterrorscreenshot(result.getMethod().getMethodName());
			try {
				extentTest.fail("<br><font color=red>" + "Screenshot of failure" + "</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				extentTest.fail("Test Failed, cannot attach Screenshot");
			}
			String logText = " Test Method <b>" + methodName + "</b> Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String logText = "<b> Test Method" + methodName + " Successful</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			String logText = "<b> Test Method" + methodName + " Skipped</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);
		}
	}

}
