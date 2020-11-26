package seleniumTesting;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class GetDataFromExcell {
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
	
	@Test
	public void test() throws Exception {
		driver.get("https://www.google.co.in"); //paste any url 
		
		/*-----------------Getting data from Excel---------------*/
		File src = new File("F:\\SoftInstall\\Selenium eclipse\\excel\\credentials.xlsx"); //Enter path of your Excel sheet.
		FileInputStream fiss = new FileInputStream(src);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fiss);
		XSSFSheet sh1 = wb.getSheetAt(0);
		/*------------------------------------------------------*/
					
		String username= sh1.getRow(0).getCell(0).getStringCellValue();//gets value from Row 1 column A 
		String password =sh1.getRow(0).getCell(1).getStringCellValue();//gets value from Row 1 column B
		//Simultaneously you can get any number of data from Excel and use it as required.
		
		// Below output code is only for reference, you can comment it when not in use!
		System.out.println("Your Username is: "+username);
		System.out.println("Your Password is: "+password);
		
	}
}