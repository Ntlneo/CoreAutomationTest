package CoreAutomation.BaseAutoTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import AutoWallet.Beowulf_HomePage;
import AutoWallet.Wallet_HomePage;



public class BaseTest {
	WebDriver driver;
	WebDriver driver2;
	String driverPath;
	
	//Pages Test
//	Beowulf_HomePage beowulf_HomePage;	
//	Wallet_HomePage wallet_HomePage;
	
//	public void initialPages() {
//		beowulf_HomePage = new Beowulf_HomePage();
//		wallet_HomePage = new Wallet_HomePage();
//	}
	
	// *********************** BEFORE & AFTER ***********************
	@Before
	public void startScript() {
//		ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
//		initialPages();
		startDriver();
		
	}

	@After
	public void sayGoodBye() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\t### END SCRIPT. SEE YA AGAIN !!!\t###");
		driver.quit();
	}
	
	
	
	// *********************** SUPPORT Functions ***********************
	
	
	public void startDriver() {
		ChromeOptions options = new ChromeOptions();
        String appPath = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
        options.setBinary(appPath);
//        options.addArguments("electron-port=5000");
//        options.addArguments("webpack-port=3000");
//        options.addArguments("access-token=12345‌​6789");		
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(options);
//		driver.manage().window().fullscreen();	//Bug no window found
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("DRIVER START SUCCESSFUL");
	}

	
}
