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

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import CoreAutomation.BaseAutoPage.BasePage;
import DataManager.StringManager;
//import Fan8_CMC.User_Proxy_To_Access_CMC;
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

	
	
	
	// *********************** BEFORE & AFTER ***********************	

	
	@BeforeEach
	public void startScript(TestInfo testInfo) {
//		ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
		System.out.println("\t###  STARTING SCRIPT  ###");
		initChromeWithProxy();
//		initPages();		

		
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
//			wSignUpPage_Web.closeAllWindow();			
//			System.out.println("Đã close last window");
			driverChrome.quit();	//not Work			
		}	
		
		if (null != driverElectron) {
			System.out.println("Đang quit Electron");
			driverElectron.quit();			
		}		
	
		
		if ( null != service) {
			service.stop();
		}
		

	}
	

	
	
	// *********************** SUPPORT Functions ***********************
	
	String proxyhttp = "82.165.105.48:80";
	public void initChromeWithProxy() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setHttpProxy(proxyhttp);
//		proxy.setSslProxy("https_proxy-url:port");
		chromeOptions.setCapability("proxy", proxy); 
		driverChrome = new ChromeDriver(chromeOptions);
	}
	
	
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
