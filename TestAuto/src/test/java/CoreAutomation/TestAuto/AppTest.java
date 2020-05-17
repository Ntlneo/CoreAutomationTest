package CoreAutomation.TestAuto;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 

{
	
	public WebDriver driver;
	public String driverPath;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	@org.junit.Test
    public void TestAutoLike()
    {
    	driverPath = "Drivers/chromedriver.exe";
    	System.setProperty("webdriver.chrome.driver", driverPath);
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
//    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	driver.get("https://www.like4like.org");
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * @return the suite of tests being tested
     */

}
