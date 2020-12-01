package seleniumTesting;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import static org.testng.Assert.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



public class FlipkartUI_Tesing {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	WebDriver driver;
	int count=0;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public void beforeClass() {
			
		/*-------------------Extent Report---------------------------------*/
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Flipkart Automation Report");
		htmlReporter.config().setReportName("Flipkart Automation Test result");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Let's GO!!!");
		extent.setSystemInfo("OS", "Windows");
		extent.attachReporter(htmlReporter);
		/*-----------------------------------------------------------------*/
		
		
		System.setProperty("webdriver.chrome.driver",
				"F:\\SoftInstall\\Selenium eclipse\\Selenium jars\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extent.flush();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			 extentTest =extent.createTest("Fail test");
			 extentTest.log(Status.FAIL, "Test method failed");
			 Assert.fail("Executing failed Test Method"); 
			fail(verificationErrorString);
		}
	}

	@Test
	public void testPass() throws Exception {
		
		extentTest =extent.createTest("Login test");
		driver.get("https://www.flipkart.com/");
		count++;
		getscreenshot(count);
		File src = new File("F:\\SoftInstall\\Selenium eclipse\\excel\\credentials.xlsx");
		FileInputStream fiss = new FileInputStream(src);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet sh1 = wb.getSheetAt(0);
					
		String username= sh1.getRow(0).getCell(0).getStringCellValue();
		String password =sh1.getRow(0).getCell(1).getStringCellValue();
		System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
		System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
		
		driver.findElement(By.xpath("//input[contains(@type, 'text')  and contains(@class,'_2IX_2')]")).click();
		driver.findElement(By.xpath("//input[contains(@type, 'text')  and contains(@class,'_2IX_2')]")).clear();		
		driver.findElement(By.xpath("//input[contains(@type, 'text')  and contains(@class,'_2IX_2')]")).sendKeys(username);
	
		
		driver.findElement(By.xpath("//input[contains(@type, 'password')  and contains(@class,'_2IX_2')]")).click();
		driver.findElement(By.xpath("//input[contains(@type, 'password')  and contains(@class,'_2IX_2')]")).clear();
		driver.findElement(By.xpath("//input[contains(@type, 'password')  and contains(@class,'_2IX_2')]")).sendKeys(password);
		
		driver.findElement(By.xpath("//button/span[contains(text(), 'Login')]")).click();
		
		extentTest.log(Status.PASS, "Login success");
		extentTest =extent.createTest("Random Test");
		//driver.findElement(By.xpath(""));
		driver.findElement(By.xpath("//div/input[contains(@name,'q') and @placeholder='Search for products, brands and more']")).click();
		driver.findElement(By.xpath("//div/input[contains(@name,'q') and @placeholder='Search for products, brands and more']"));
		driver.findElement(By.xpath("//div/input[contains(@name,'q') and @placeholder='Search for products, brands and more']")).sendKeys("Samsung", Keys.ENTER);
		
		
		extentTest.log(Status.PASS, "Search Model success");
	
		String a=  driver.findElement(By.xpath("//div[contains(text(),'Samsung Galaxy F41 (Fusion Blue, 128 GB)')]")).getText();
		System.out.println(a);
		driver.findElement(By.xpath("//div/a[contains(@href,'/samsung-galaxy-f41-fusion-blue-128-gb/p/itm4769d0667cdf9')]")).click();
	
		
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle : allWindowHandles)
		{
		driver.switchTo().window(handle);
		}
		
		driver.findElement(By.cssSelector(".\\_3v1-ww")).click();
		extentTest.log(Status.PASS, "Go to cart button tested successfully");
		String aa=driver.findElement(By.xpath("//a[contains(text(),'Samsung Galaxy F41 (Fusion Blue, 128 GB)')]	")).getText();
		System.out.println(aa);
		
		if (a.equals(aa)) {
			driver.findElement(By.xpath("//button/span[contains(text(),'Place Order')]")).click();
			extentTest.log(Status.PASS, "Place Order button tested successfully");
		}
		else {
			afterClass();
		}
	}
	
	public void getscreenshot(int count) throws Exception{
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File("F:\\SoftInstall\\Selenium eclipse\\Selenium SS\\Screenshot"+count+".png"));
	}
	
	
	/* ------------To invoke fail test---------------
	 * @Test 
	 * public void testFail() { 
	 * extentTest =extent.createTest("Fail test");
	 * extentTest.log(Status.FAIL, "Test method failed");
	 * Assert.fail("Executing failed Test Method"); }
	 */
	
	/*  ------------To invoke skip test---------------
	 * @Test public void testSkipped() { 
	 * extentTest =extent.createTest("Skipped test"); 
	 * extentTest.log(Status.SKIP,"Test method skipped"); 
	 * throw new SkipException("Executing skipped Test Method"); }
	 */
	
	@AfterMethod
	public void afterMethod() {
	}

}
