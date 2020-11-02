package CoreAutomation.BaseAutoTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.browser.Browser;
//import org.openqa.selenium.devtools.browser.model.PermissionType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import DataManager.EmailManager;

/**
 * Unit test for simple App.
 */
public class AutoLike_CoinGecko {

	// global
	static WebDriver driver;
	static String driverPath;

	// changed
	static int numberOfAcc = 10;
	static int lengthOfUsername = 6;
	
	//cookie register as suzukihzt@gmail.com
	static String cookie_hCaptchaPage = "https://dashboard.hcaptcha.com/welcome_accessibility";
	static String cookie_session = "7021a4a1-1a04-4fe8-9acd-1e670ccf4b03";
	static String cookie__cfduid = "d45ad1fab2804986ba4b9e0086ecf8adf1600941513";
	static String gmailHost_ToGetCookie = "imap.gmail.com";
//	static String gmailUser_ToGetCookie = "suzukihzt@gmail.com";
//	static String gmailPass_ToGetCookie = "Docnhat1";
	static String gmailUser_ToGetCookie = "lamnguyeneditor@gmail.com";
	static String gmailPass_ToGetCookie = "Xzsawq8487!@#";
	
	
	
	
	static String pathToExcelFile = "DataTest/DataCoinGecko.xlsx";
	static String sheetName = "CoinGecko";
	static String cgHomePage = "https://www.coingecko.com/en";
	static String cgBwfPage = "https://www.coingecko.com/en/coins/beowulf";
	
	public String emailRegister_Prefix;
	public String emailRegister_Suffix = "@mailinator.com";
	public String emailRegister;
	public String passwordRegister = "12345678";

	static List<String> listGeneratedEmailPrefix = new ArrayList<String>();
	static List<String> listGeneratedEmail = new ArrayList<String>();
	
	public List<Pair<String, String>> listPairEmail = new ArrayList<Pair<String,String>>();

	// locators
	static By signUpMenuBtn = By.xpath("//a[@data-target='#signUpModal']");
//	static By emailBox = By.xpath("//input[@id='user_email']");
	
	//popup signup
	static By emailBox = By.id("user_email");
	static By passBox = By.id("user_password");
	static By checkBox1 = By.id("tos_agreement");
	static By checkBox2 = By.id("subscribe_newsletter");
	static By signUpBtn = By.id("sign-up-button");
	
	//frame captcha
	static By captchaFrame = By.xpath("//*[contains(@title,'widget containing checkbox for hCaptcha security challenge')]");
	static By captchaCheckBox = By.id("checkbox");
	
	static By captchaFrame_Main = By.xpath("//*[contains(@title,'Main content of the hCaptcha challenge')]");
	static By helpBtn = By.xpath("//*[@class='help button']");
	static By accessibilityOption = By.xpath("//*[@class='option button']");	//1st option
	static By cookieLink = By.xpath("//*[contains(text(),'Retrieve accessibility cookie')]");
	static By emailCaptchaAccess = By.id("email");
	static By submitBtn = By.xpath("//*[@data-cy='button-submit']");
	// use: suzukihzt@gmail.com
	
	static String email_Signin = "https://accounts.google.com/signin";
	static By gg_emailBox = By.name("identifier");
	static By gg_NextBtn = By.xpath("//*[text()='Next']");
	
	static By setCookieBtn = By.xpath("//*[@data-cy='setAccessibilityCookie']");	
	
	
	
	//bwf page
	static By starIcon = By.xpath("//i[@data-coin-id='12586']");
	
	//mailinator email
	static String linkMailinator_part1 = "https://www.mailinator.com/v3/index.jsp?zone=public&query=";
	static String linkMailinator_part2 = "#/#inboxpane";
	static String linkMailinator; // = linkMailinator_part1 + emailRegister_Prefix + linkMailinator_part2;
	static By sender1stEmail = By.xpath("((//*[@id='inboxpane']//tr[contains(@id,row_lam)])[2]/td[@class='ng-binding'])[2]");
	static By senderEmail_CoinGecko = By.xpath("//*[contains(text(),'CoinGecko')]");
	static By titleEmail_CoinGecko = By.xpath("//*[contains(text(),'Confirmation instructions')]");
	static By listRowEmail = By.xpath("//*[@ng-repeat='email in emails']");
	static By frameEmail_Mailinator = By.xpath("//*[@id='msg_body']");
	static By confirmAccBtn = By.xpath("(//*[@target='_other'])[2]");
	
	
	//redirect from Mailinator to new tab login Coingecko
	static By emailBox_Login = By.id("user_email");
	static By passBox_Login = By.id("user_password");	
	static By loginBtn_Login = By.name("commit");
	
	

	// *********************** RUN TEST AUTO ***********************

	// Before Script
//	ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);

	// AUTO SCRIPT
	// Before run script, must get cookie of hCaptcha via https://dashboard.hcaptcha.com/welcome_accessibility
	@Test
	public void Test_CoinGecko() {

		// Before Test
		System.out.println("\t*** STARTING SCRIPT\t***");
		setUpListAccRegister(numberOfAcc,lengthOfUsername);
		for (String acc : listGeneratedEmail) {
			System.out.println(acc);
		}
		
		// Star Test
		startDriver();
		
		// listPairEmail(Prefix-Email, email)
		for (int i = 0 ; i < listPairEmail.size(); i++) {			
			registerAcc_WithoutClickSignUp_CoinGecko(listPairEmail.get(i).getSecond().toString(), passwordRegister);
			if(i == 0) {
				driver.switchTo().defaultContent();
				byPassHcaptcha();
				
				driver.switchTo().defaultContent();
				setCookie_FromGmail_ForThisChrome();
				
				driver.switchTo().defaultContent();
				System.out.println("Trying to register again");
				openURL(cgHomePage);				
				registerAcc_WithoutClickSignUp_CoinGecko(listPairEmail.get(i).getSecond().toString(), passwordRegister);
				
				driver.switchTo().defaultContent();
				click(signUpBtn);
				System.out.println("SignUp CoinGecko success");
			}else {
				click(signUpBtn);
				System.out.println("SignUp CoinGecko success");
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			verifyAcc_InMailinator(listPairEmail.get(i).getFirst().toString());
			
			loginNewTab_CoinGecko(listPairEmail.get(i).getSecond().toString(), passwordRegister);			
			
			clickStarBWF_CoinGecko();
		}
		
		// After Test
		sayGoodBye();

		
	}

	// After Script
	public void sayGoodBye() {
		System.out.println("\t### END SCRIPT. SEE YA AGAIN !!!\t###");
		driver.quit();
	}

	// *********************** TASKS ***********************
	
	static void clickStarBWF_CoinGecko() {
		openURL(cgBwfPage);
		click(starIcon);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void loginNewTab_CoinGecko(String email, String password) {
		switchWindow(4);
		input(emailBox_Login, email);	
		input(passBox_Login, password);
		click(loginBtn_Login);
	}
	
	static void verifyAcc_InMailinator(String emailRegister_Prefix) {
		// Verify acc
		linkMailinator = linkMailinator_part1 + emailRegister_Prefix + linkMailinator_part2;
		openURL(linkMailinator);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getElement_ByFluentWait(titleEmail_CoinGecko, 180, 5).click();
		
		//switch to email frame
		driver.switchTo().frame(getElement_ByFluentWait(frameEmail_Mailinator, 10, 1));
		click(confirmAccBtn);		
	}
	
	static void registerAcc_WithoutClickSignUp_CoinGecko(String email, String password) {	
		click(signUpMenuBtn);
		input(emailBox, email);
		input(passBox, password);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		click(checkBox1);
		click(checkBox2);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().frame(getElement_ByFluentWait(captchaFrame, 10, 1));
		click(captchaCheckBox);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
//		click(signUpBtn);		
	}
	
	static void byPassHcaptcha() {
		
		// switch back to default window then switch to new frame
		driver.switchTo().frame(getWebElement(captchaFrame_Main));		
		click(helpBtn);		
		click(accessibilityOption);
		click(cookieLink);		
		
		// switch back to default window then switch to new Window
		driver.switchTo().defaultContent();
		checkNumberOfWindow();
		switchWindow(2);		
		input(emailCaptchaAccess, gmailUser_ToGetCookie);
		click(submitBtn);
//		driver.switchTo().defaultContent();
		
		// wait 60s to email come then access gmail to set cookie via link in email
		System.out.println("Begin waiting 60s for the setCookie email");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	static void setCookie_FromGmail_ForThisChrome() {
		System.out.println("Trying to access Gmail via IMAP");
		EmailManager mail = new EmailManager(gmailHost_ToGetCookie, gmailUser_ToGetCookie, gmailPass_ToGetCookie);
		String emailSubjectKeyword = "using hCaptcha Accessibility";
		String emailLinkKeyword = "accounts.hcaptcha.com/verify_email";
		String emailLink=null;
		try {
			emailLink = mail.getLink_FromAnEmail("hCaptcha", emailSubjectKeyword, emailLinkKeyword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		openNewTab_thenSwitch();
		openURL(emailLink);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Trying to click setCookie Button");
		click(setCookieBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.close();
	}
	
	static void checkNumberOfWindow() {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			System.out.println(window);
		}
	}
	
	void setUpListAccRegister(int numberOfAcc, int lengthOfUsername) {
		do {
//			listGeneratedEmail.clear();
//			listGeneratedEmailPrefix.clear();
			
			emailRegister_Prefix = RandomStringUtils.randomAlphanumeric(lengthOfUsername);
			emailRegister = emailRegister_Prefix + emailRegister_Suffix;
//				System.out.println("Email random is created : " + emailRegister);

			if (!listGeneratedEmailPrefix.contains(emailRegister_Prefix)) {
				listGeneratedEmailPrefix.add(emailRegister_Prefix);
				listGeneratedEmail.add(emailRegister);
//					System.out.println("Email random is added to List : " + emailRegister);
				Pair pairEmail = new Pair<String, String>(emailRegister_Prefix, emailRegister);
				listPairEmail.add(pairEmail);				 
			} else {
				System.out.println("Email random is duplicated : " + emailRegister);
			}
		} while (listGeneratedEmailPrefix.size() < numberOfAcc);		
	}

	static void startDriver() {
		driverPath = "Drivers/WebChromeDriver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2); // disable browser noti
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		
//		openURL(cookie_hCaptchaPage);
//		driver.manage().addCookie(new Cookie("__cfduid", cookie__cfduid));		
		
		openURL(cgHomePage);
	}
	
	// start from 1
	static void switchWindow(int orderOfWindow) {
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver.switchTo().window(listWindow.get(orderOfWindow-1));
	}

	// *********************** BASE TEST ***********************

	//DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
//	static WebElement waitElementClickable(By by,int durationInSecond) {
//		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSecond));
//		return wait.until(ExpectedConditions.elementToBeClickable(by));
//	}
//	
//	//DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
//	static WebElement waitElementVisible(By by,int durationInSecond) {
//		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSecond));
//		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//	}
	
	static void clickByJavaScript(By byXpath) {
		WebElement element = driver.findElement(byXpath);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	static WebElement getElement_ByFluentWait(By by, int timeoutInSecond, int repeatInSecond) {
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
