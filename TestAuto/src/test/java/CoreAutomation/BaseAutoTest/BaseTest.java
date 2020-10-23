package CoreAutomation.BaseAutoTest;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import AutoWalletPage.Beowulf_HomePage;
import AutoWalletPage.Wallet_HomePage;



public class BaseTest {	
	public WebDriver driver;
	public String driverPath;

	// use for init Pages
	public Beowulf_HomePage bHomePage;
	public Wallet_HomePage wHomePage;
	
	
	// *********************** BEFORE & AFTER ***********************
	@BeforeEach
	public void startScript() {
//		ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
		System.out.println("\t### STARTING SCRIPT \t###");
		initDriver();		
		initPages();
	}

	@AfterEach
	public void endScript() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\t### END SCRIPT. SEE YA AGAIN !!!\t###");
		driver.quit();
	}
	
	
	
	// *********************** SUPPORT Functions ***********************
	
	
	private void initDriver() {
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
		System.out.println("\tDRIVER START SUCCESSFUL");
	}

	private void initPages() {
		bHomePage = new Beowulf_HomePage(driver);
		wHomePage = new Wallet_HomePage(driver);
	}
}
