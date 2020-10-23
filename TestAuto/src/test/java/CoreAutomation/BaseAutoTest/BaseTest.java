package CoreAutomation.BaseAutoTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import AutoWalletPage.Beowulf_HomePage;
import AutoWalletPage.Wallet_HomePage;



public class BaseTest {
	ChromeDriverService service;
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
//		initDriverWithSpecificPort();
		initPages();
	}

	@AfterEach
	public void endScript() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		System.out.println("\t### END SCRIPT. SEE YA AGAIN !!!\t###\n");		
		driver.quit();
		
		// only use when start ChromeDriver by ChromeDriverService
//		service.stop();
	}
	
	
	
	// *********************** SUPPORT Functions ***********************
	
	
	private void initDriver() {
		ChromeOptions options = new ChromeOptions();
		String appPath = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
		options.setBinary(appPath);
//        options.addArguments("electronPort=5000");
//        options.addArguments("webpackPort=3000");
//        options.addArguments("accessToken=12345‌​6789");		
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		// hide log of chromedriver and java selenium
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		
		driver = new ChromeDriver(options);
//		driver.manage().window().fullscreen();	//Bug no window found
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("\tDriver Start Success");
	}
	
	private void initDriverWithSpecificPort() {
		ChromeOptions options = new ChromeOptions();
		String appPath = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
		options.setBinary(appPath);
//        options.addArguments("electronPort=5000");
//        options.addArguments("webpackPort=3000");
//        options.addArguments("accessToken=12345‌​6789");		
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
//		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

		
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(driverPath))
				.usingPort(9999)
				.build();
		
		try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		driver = new RemoteWebDriver(service.getUrl(), options);
//		driver = new ChromeDriver(options);
//		driver.manage().window().fullscreen();	//Bug no window found
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("\tDriver Start Success");
	}

	private void initPages() {
		bHomePage = new Beowulf_HomePage(driver);
		wHomePage = new Wallet_HomePage(driver);
	}
}
