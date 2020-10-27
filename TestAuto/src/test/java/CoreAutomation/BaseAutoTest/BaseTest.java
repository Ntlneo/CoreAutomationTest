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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import AutoWalletPage.Beowulf_HomePage;
import AutoWalletPage.Wallet_HomePage;
import AutoWalletPage.Wallet_SignUpPage;
import CoreAutomation.BaseAutoPage.BasePage;
import DataManager.StringManager;
import DataManager.StringManager;

import org.junit.jupiter.api.TestInfo;



public class BaseTest {
	public ChromeDriverService service;
	public WebDriver driverElectron;
	public WebDriver driverChrome;
	
	private String driverChromeForWebPath = "Drivers/WebChromeDriver/chromedriver.exe";
	private String driverChromeForElectronPath = "Drivers/ElectronChromeDriver/chromedriver.exe";
	private String appPath_Mainnet = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
	private String appPath_Testnet = "C://Program Files (x86)/BeowulfWalletTestnet/BeowulfWalletTestnet.exe";

	// use for init Pages
	public Beowulf_HomePage bHomePage;
	public Wallet_HomePage wHomePage;
	public Wallet_SignUpPage wSignUpPage;
	
	private void initPages() {
		bHomePage = new Beowulf_HomePage(driverElectron);
		wHomePage = new Wallet_HomePage(driverElectron);
		wSignUpPage = new Wallet_SignUpPage(driverChrome);
	}
	
	// *********************** BEFORE & AFTER ***********************
	
//	@BeforeAll
//	public void startScript_ONCE() {
//		System.out.println("\t###  STARTING SCRIPT  ###");
//		initDriver();
//		initPages();
//	}
	
//	@AfterAll
//	public void endScript_ONCE() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
//		System.out.println("\n\t###  END SCRIPT. SEE YA AGAIN !!!  ###\n");		
//		driver.quit();
//		
//		//only use with ChromeService
////		service.stop();
//	}
	
	@BeforeEach
	public void startScript(TestInfo testInfo) {
//		ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
		System.out.println("\t###  STARTING SCRIPT  ###");
		initDriverElectron();
		initPages();		
//		initDriverChrome();
		//only use with ChromeService
//		initDriverWithSpecificPort();
		
		System.out.println("Test File:\t" + testInfo.getTestClass().get().getSimpleName() + ".java");
		System.out.println("Test Case:\t" +	addSpaceBetweenWords_OfTestcaseMethod(testInfo.getTestMethod().get().getName()));

	}

	@AfterEach
	public void endScript() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		System.out.println("\t###  END SCRIPT. SEE YA AGAIN !!!  ###\n");		
		if (null !=driverElectron) {
			driverElectron.quit();			
		}
		
		if (null != driverChrome) {
			driverChrome.quit();
		}
		
		
		
		
		//only use with ChromeService
//		service.stop();
	}
	

	
	
	// *********************** SUPPORT Functions ***********************
	
	
	public void initDriverChrome() {
		System.setProperty("webdriver.chrome.driver", driverChromeForWebPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-debugging-address = 127.0.0.1");
//		options.addArguments("--remote-debugging-port = 9222");
//		options.setExperimentalOption("debuggerAddress", "localhost:9222");
		
		driverChrome = new ChromeDriver(options);
//		driver.manage().window().fullscreen();	//Bug no window found
//		driverChrome = new ChromeDriver();
		driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driverChrome.get("Google.com");
		System.out.println("Driver Chrome Start Success");
		System.out.println(driverChrome.getTitle());
		driverChrome.get("google.com");
		
	}
	
	private void initDriverElectron() {
		ChromeOptions options = new ChromeOptions();
		options.setBinary(appPath_Testnet);
	
		
		System.setProperty("webdriver.chrome.driver", driverChromeForElectronPath);
		
		// hide log of chromedriver and java selenium
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		
		driverElectron = new ChromeDriver(options);
		driverElectron.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		driver.manage().window().fullscreen();	//Bug no window found
		JavascriptExecutor js = (JavascriptExecutor) driverElectron;  
		js.executeScript("require('electron').remote.BrowserWindow.getFocusedWindow().maximize();");		

		System.out.println("Pre-Test: Driver Electron Start Success");
	}
	
	private void initDriverElectronWithSpecificPort() {
		ChromeOptions options = new ChromeOptions();

		options.setBinary(appPath_Testnet);	

		System.setProperty("webdriver.chrome.driver", driverChromeForElectronPath);
//		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

		
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(driverChromeForWebPath))
				.usingPort(9999)
				.build();
		
		try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		driverElectron = new RemoteWebDriver(service.getUrl(), options);
		driverElectron.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		driver.manage().window().fullscreen();	//Bug no window found
		JavascriptExecutor js = (JavascriptExecutor) driverElectron;  
		js.executeScript("require('electron').remote.BrowserWindow.getFocusedWindow().maximize();");
		
		System.out.println("\tDriver Start Success");
	}


	
	private String addSpaceBetweenWords_OfTestcaseMethod(String string) {
		StringManager sm = new StringManager();
		return sm.addSpaceBeforeCapitalCharacter(string);
	}
	

}
