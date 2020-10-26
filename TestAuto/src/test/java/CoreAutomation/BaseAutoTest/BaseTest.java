package CoreAutomation.BaseAutoTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
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
import CoreAutomation.BaseAutoPage.BasePage;
import DataManager.StringManager;
import DataManager.StringManager;

import org.junit.jupiter.api.TestInfo;



public class BaseTest {
	ChromeDriverService service;
	public WebDriver driver;
	public String driverPath;

	// use for init Pages
	public Beowulf_HomePage bHomePage;
	public Wallet_HomePage wHomePage;
	
	
	// *********************** BEFORE & AFTER ***********************
	
	@BeforeAll
	public void startScript_ONCE() {
		System.out.println("\t###  STARTING SCRIPT  ###");
		initDriver();
		initPages();
	}
	
	@BeforeEach
	public void startScript(TestInfo testInfo) {
//		ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
//		System.out.println("\t###  STARTING SCRIPT  ###");
//		initDriver();
//		initPages();
		
		
		
		
		
		//only use with ChromeService
//		initDriverWithSpecificPort();
		System.out.println("Test File:\t" + testInfo.getTestClass().get().getSimpleName() + ".java");
		System.out.print("Test Case:\t" +	addSpaceBetweenWords_OfTestcaseMethod(testInfo.getTestMethod().get().getName()));
		
		
		
		
	}

	@AfterEach

	
	@AfterAll
	public void endScript_ONCE() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		System.out.println("\n\t###  END SCRIPT. SEE YA AGAIN !!!  ###\n");		
		driver.quit();
		
		//only use with ChromeService
//		service.stop();
	}
	
	
	// *********************** SUPPORT Functions ***********************
	
	
	private void initDriver() {
		ChromeOptions options = new ChromeOptions();
		String appPath_Mainnet = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
		String appPath_Testnet = "C://Program Files (x86)/BeowulfWalletTestnet/BeowulfWalletTestnet.exe";
		options.setBinary(appPath_Testnet);
//		options.setBinary(appPath);
	
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		// hide log of chromedriver and java selenium
//		System.setProperty("webdriver.chrome.silentOutput", "true");
//		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		
		driver = new ChromeDriver(options);
//		driver.manage().window().fullscreen();	//Bug no window found
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		System.out.println("Driver Start Success");
	}
	
	private void initDriverWithSpecificPort() {
		ChromeOptions options = new ChromeOptions();
		String appPath = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
		options.setBinary(appPath);	
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
	
	private String addSpaceBetweenWords_OfTestcaseMethod(String string) {
		StringManager sm = new StringManager();
		return sm.addSpaceBeforeCapitalCharacter(string);
	}
	

}
