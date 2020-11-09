package CoreAutomation.BaseAutoTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
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

import AutoWalletPage.All_TopMenuPage;
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
	public int portDebugChrome = 6789;
	
	private String path_DriverChromeForWeb = "Drivers/WebChromeDriver/chromedriver.exe";
	private String path_DriverChromeForElectron = "Drivers/ElectronChromeDriver/chromedriver.exe";
	private String path_AppMainnet = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
	private String path_AppTestnet = "C://Program Files (x86)/BeowulfWalletTestnet/BeowulfWalletTestnet.exe";

	// use for init Pages
	public All_TopMenuPage aTopMenu_Electron;
	public Beowulf_HomePage bHomePage_Electron;
	public Wallet_HomePage wHomePage_Electron;	
	public Wallet_SignUpPage wSignUpPage_Electron;
	public Wallet_SignUpPage wSignUpPage_Web;
	private void initPages() {
		aTopMenu_Electron = new All_TopMenuPage(driverElectron);
		bHomePage_Electron = new Beowulf_HomePage(driverElectron);
		wHomePage_Electron = new Wallet_HomePage(driverElectron);
		wSignUpPage_Electron = new Wallet_SignUpPage(driverElectron);
//		wSignUpPage_Web = new Wallet_SignUpPage(driverChrome);
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
		initDriverElectron(path_AppMainnet);
		initPages();		

		
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
		if (null != driverChrome) {
			System.out.println("Đang quit Chrome");
			wSignUpPage_Web.closeAllWindow();			
//			System.out.println("Đã close last window");
			driverChrome.quit();	//not Work			
		}	
		
		if (null != driverElectron) {
			System.out.println("Đang quit Electron");
			driverElectron.quit();			
		}
		
	
		
		if ( service != null ) {
			service.stop();
		}
		
		
		
		//only use with ChromeService
//		service.stop();
	}
	

	
	
	// *********************** SUPPORT Functions ***********************
	
	public void initServiceChromeDriver_WithSpecificPort(int port) {
		ChromeOptions options = new ChromeOptions();
//	    options.setExperimentalOption("debuggerAddress", "127.0.0.1:9999");
//	    options.addArguments("--remote-debugging-port=1557");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "false");
//		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(path_DriverChromeForWeb))
				.usingPort(port)
				.build();
		
		try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		driverChrome = new RemoteWebDriver(service.getUrl(), options);
		driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		driverChrome.manage().window().fullscreen();
		
		System.out.println("Pre-Test: CHROME Driver SERVICE Start Success");
	}
	
	public void initDriverChrome() {
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);
		System.setProperty("webdriver.chrome.silentOutput", "false");;
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("debuggerAddress", "localhost:" + portDebugChrome);
		
		driverChrome = new ChromeDriver(options);
		driverChrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Pre-Test: CHROME Driver Start Success");
		
	}
	
	private void initDriverElectron(String pathAppEnvironment) {
		ChromeOptions options = new ChromeOptions();
		options.setBinary(pathAppEnvironment);
	
		
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForElectron);
		
		// hide log of chromedriver and java selenium
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		
		driverElectron = new ChromeDriver(options);
		driverElectron.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
//		driver.manage().window().fullscreen();	//Bug no window found
		JavascriptExecutor js = (JavascriptExecutor) driverElectron;  
		js.executeScript("require('electron').remote.BrowserWindow.getFocusedWindow().maximize();");		

		System.out.println("Pre-Test: ELECTRON Driver Start Success");
	}
	
	private void initDriverElectron_WithSpecificPort() {
		ChromeOptions options = new ChromeOptions();

		options.setBinary(path_AppTestnet);	

		System.setProperty("webdriver.chrome.driver", path_DriverChromeForElectron);
//		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

		
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(path_DriverChromeForWeb))
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
