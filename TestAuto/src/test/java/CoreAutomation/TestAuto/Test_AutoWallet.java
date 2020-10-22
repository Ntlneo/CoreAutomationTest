package CoreAutomation.TestAuto;

import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.util.Pair;
import org.apache.logging.log4j.core.util.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.browser.Browser;
//import org.openqa.selenium.devtools.browser.model.PermissionType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataManager.ExcelManager_Map;

/**
 * Unit test for simple App.
 */
public class Test_AutoWallet {

	// global
	static WebDriver driver;
	static String driverPath;

//	// changed
//	static int numberOfAcc = 10;
//	static int lengthOfUsername = 6;
//	
//	//cookie register as suzukihzt@gmail.com
//	static String cookie_hCaptchaPage = "https://dashboard.hcaptcha.com/welcome_accessibility";
//	static String cookie_session = "7021a4a1-1a04-4fe8-9acd-1e670ccf4b03";
//	static String cookie__cfduid = "d45ad1fab2804986ba4b9e0086ecf8adf1600941513";
//	static String gmail_ToGetCookie = "suzukihzt@gmail.com";
//	static String passGmail_ToGetCookie = "Docnhat1";

//	
//	static String pathToExcelFile = "DataTest/DataCoinGecko.xlsx";
//	static String sheetName = "CoinGecko";
//	static String cgHomePage = "https://www.coingecko.com/en";
//	static String cgBwfPage = "https://www.coingecko.com/en/coins/beowulf";
//	
//	public String emailRegister_Prefix;
//	public String emailRegister_Suffix = "@mailinator.com";
//	public String emailRegister;
//	public String passwordRegister = "12345678";
//
//	static List<String> listGeneratedEmailPrefix = new ArrayList<String>();
//	static List<String> listGeneratedEmail = new ArrayList<String>();
//	
//	public List<Pair<String, String>> listPairEmail = new ArrayList<Pair<String,String>>();
//
//	// locators
//	static By signUpMenuBtn = By.xpath("//a[@data-target='#signUpModal']");
////	static By emailBox = By.xpath("//input[@id='user_email']");
//	
//	//popup signup
//	static By emailBox = By.id("user_email");
//	static By passBox = By.id("user_password");
//	static By checkBox1 = By.id("tos_agreement");
//	static By checkBox2 = By.id("subscribe_newsletter");
//	static By signUpBtn = By.id("sign-up-button");
//	
//	//frame captcha
//	static By captchaFrame = By.xpath("//*[contains(@title,'widget containing checkbox for hCaptcha security challenge')]");
//	static By captchaCheckBox = By.id("checkbox");
//	
//	static By captchaFrame_Main = By.xpath("//*[contains(@title,'Main content of the hCaptcha challenge')]");
//	static By helpBtn = By.xpath("//*[@class='help button']");
//	static By accessibilityOption = By.xpath("//*[@class='option button']");	//1st option
//	static By cookieLink = By.xpath("//*[contains(text(),'Retrieve accessibility cookie')]");
//	static By emailCaptchaAccess = By.id("email");
//	static By submitBtn = By.xpath("//*[@data-cy='button-submit']");
//	// use: suzukihzt@gmail.com
//	
//	static String email_Signin = "https://accounts.google.com/signin";
//	static By gg_emailBox = By.name("identifier");
//	static By gg_NextBtn = By.xpath("//*[text()='Next']");

//	//mailinator email
//	static String linkMailinator_part1 = "https://www.mailinator.com/v3/index.jsp?zone=public&query=";
//	static String linkMailinator_part2 = "#/#inboxpane";
//	static String linkMailinator; // = linkMailinator_part1 + emailRegister_Prefix + linkMailinator_part2;
//	static By sender1stEmail = By.xpath("((//*[@id='inboxpane']//tr[contains(@id,row_lam)])[2]/td[@class='ng-binding'])[2]");
//	static By senderEmail_CoinGecko = By.xpath("//*[contains(text(),'CoinGecko')]");
//	static By titleEmail_CoinGecko = By.xpath("//*[contains(text(),'Confirmation instructions')]");
//	static By listRowEmail = By.xpath("//*[@ng-repeat='email in emails']");	
//	static By confirmAccBtn = By.xpath("(//*[@target='_other'])[2]");
//	
//	//redirect from Mailinator to login Coingecko
//	static By loginBtn = By.name("commit");
//	
//	

	
	
	//Home page
	By walletBtn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']");
	
	//Login wallet
	By selectWallet_Box = By.xpath("//*[@data-vv-name='wallet_selection']");
//	By createWallet_Option = By.xpath("//span[text()='Create Wallet']");
	By createWallet_Option = By.xpath("//*[@class='el-scrollbar']//span");
	By createOne_Link = By.xpath("//*[@class='text-danger']");
	
	//Create wallet
	By walletName_Box = By.id("walletName");
	By walletPassword_Box = By.id("passwordInput");
	By walletConfirmPassword_Box = By.id("confirmPasswordInput");
	By submit_Btn = By.xpath("//*[@type='submit']");
	
	
	
	
	
	// *********************** RUN TEST AUTO ***********************

	@Test
	public void Test_LoginWallet() {
		System.out.println("\t*** STARTING TestCase 2\t***");
		click(walletBtn);
		click(createOne_Link);		
		input(walletName_Box, "lamwallet1");
		input(walletPassword_Box, "12345678");
		input(walletConfirmPassword_Box, "12345678");
		click(submit_Btn);
		// After Test
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	


	// AUTO SCRIPT
	@Test
	public void Test_HomeWallet() {
			// Before Test
			System.out.println("\t*** STARTING TestCase 1\t***");

			
			// Star Test			
			click(walletBtn);
			
			
			
			
			// After Test
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		

		
	}
	
	// Before Script
//	ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);
	@Before
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

	// After Script
	@After
	public void sayGoodBye() {
		System.out.println("\t### END SCRIPT. SEE YA AGAIN !!!\t###");
		driver.quit();
	}

	// *********************** TASKS ***********************
	
	


	


<<<<<<< HEAD
	// *********************** BASE TEST ***********************
=======
        
//        options.merge(capabilities);
        String appPath = "C://Program Files (x86)/BeowulfWallet/BeowulfWallet.exe";
        options.setBinary(appPath);
//        options.addArguments("electron-port=5000");
//        options.addArguments("webpack-port=3000");
//        options.addArguments("access-token=12345‌​6789");
        
//        options.setCapability("chromeOptions", options);
		
		
		
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
//		System.setProperty("webdriver.chrome.silentOutput", "true");
//		
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//		options.setExperimentalOption("useAutomationExtension", false);
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("credentials_enable_service", false);
//		prefs.put("profile.password_manager_enabled", false);
//		prefs.put("profile.default_content_setting_values.notifications", 2); // disable browser noti
//		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
>>>>>>> refs/heads/CoinGecko

	
	
	// start from 1
	static void switchWindow(int orderOfWindow) {
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver.switchTo().window(listWindow.get(orderOfWindow-1));
	}
	
	//DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound	
	static WebElement getElement_AfterFluentWait(By by, int timeoutInSecond, int repeatInSecond) {
		Wait wait = new FluentWait(driver)
				.withTimeout(Duration.ofSeconds(timeoutInSecond))
				.pollingEvery(Duration.ofSeconds(repeatInSecond))
				.ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by)); 
	}


	static void refreshCurrentPage() {
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.F5);
		driver.navigate().refresh();
	}

	//DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
	static void openNewTab_thenSwitch() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	static void openURL(String url) {
		driver.get(url);
	}
	
	static String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	static List<WebElement> getListWebElement(By by) {
		return driver.findElements(by);
	}

	static WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	static void click(By by) {
		getWebElement(by).click();
	}

	static void input(By by, String text) {
		getWebElement(by).sendKeys(text);
	}

	static void hover(By by) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getWebElement(by)).perform();
	}

	static void hoverAndClick(By by1, By by2) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getWebElement(by1)).click(getWebElement(by2)).perform();
	}

	static void doubleClick(By by) {
		Actions acts = new Actions(driver);
		acts.doubleClick(getWebElement(by));
	}

}
