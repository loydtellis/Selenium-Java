package test.maven;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup_basic {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    
    /*--------------Use the below code if you want to use Chrome driver--------------------*/
    //import org.openqa.selenium.chrome.ChromeDriver;  //paste this in import section
    //System.setProperty("webdriver.chrome.driver","your chromedriver.exe path\\chromedriver.exe");
	//driver = new ChromeDriver();
    /*-------------------------------------------------------------------------------------*/
    
    /*--------------Use the below code if you want to use Internet explorer driver--------------------*/
    //import org.openqa.selenium.ie.InternetExplorerDriver; //paste this in import section
    //System.setProperty("webdriver.ie.driver", "your IEDriverServer.exe path\\IEDriverServer.exe");  
    //driver=new InternetExplorerDriver(); 
    /*-------------------------------------------------------------------------------------*/
    
    
    driver.manage().window().maximize(); //maximize driver window to the max screen resolution 
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
    //above code comes into play when an element that is being searched for is not available, 
    //then the implicit wait is triggered and that element is polled up to 30sec
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    //Enter your Test code here
	  
  }
  //@AfterClass will be executed after all the test methods of a current class have been completed
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit(); //closes all the browser windows and ends the WebDriver session
    //driver.close(); //closes the current browser window on which the focus is set
    
    //Below set of codes are designed to fail the test suite if the verificationErrors StringBuffer has been populated by any preceding test. 
    //It also quits the current WebDriver instance beforehand.
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
