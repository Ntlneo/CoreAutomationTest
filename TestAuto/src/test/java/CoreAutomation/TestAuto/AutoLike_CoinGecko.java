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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataManager.ExcelManager_Map;

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
	
	
	static String pathToExcelFile = "DataTest/DataCoinGecko.xlsx";
	static String sheetName = "CoinGecko";
	static String cgHomePage = "https://www.coingecko.com/en";
	static String cgBwfPage = "https://www.coingecko.com/en/coins/beowulf";
	
	static String emailRegister_Prefix;
	static String emailRegister_Suffix = "@mailinator.com";
	static String emailRegister;
	static String passwordRegister = "12345678";

	static List<String> listGeneratedEmailPrefix = new ArrayList<String>();
	static List<String> listGeneratedEmail = new ArrayList<String>();
//	static int numberOfLoop = 60;

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
	static By captchaFrame = By.xpath("//*[@title='widget containing checkbox for hCaptcha security challenge']");
	static By captchaCheckBox = By.id("checkbox");
	static By helpBtn = By.xpath("//*[@class='help button']");
	static By accessibilityOption = By.xpath("//*[@class='option button']");	//1st option
	static By cookieLink = By.xpath("//*[contains(text(),'Retrieve accessibility cookie')]");

	
	
	
	
	
	//bwf page
	static By starIcon = By.xpath("//i[@data-coin-id='12586']");
	
	//mailinator email
	static String linkMailinator_part1 = "https://www.mailinator.com/v3/index.jsp?zone=public&query=";
	static String linkMailinator_part2 = "#/#inboxpane";
	static String linkMailinator = linkMailinator_part1 + emailRegister_Prefix + linkMailinator_part2;
	static By sender1stEmail = By.xpath("((//*[@id='inboxpane']//tr[contains(@id,row_lam)])[2]/td[@class='ng-binding'])[2]");
	static By senderEmail_CoinGecko = By.xpath("//*[contains(text(),'CoinGecko')]");
	static By titleEmail_CoinGecko = By.xpath("//*[contains(text(),'Confirmation instructions')]");
	static By listRowEmail = By.xpath("//*[@ng-repeat='email in emails']");	
	static By confirmAccBtn = By.xpath("(//*[@target='_other'])[2]");
	
	//redirect from Mailinator to login Coingecko
	static By loginBtn = By.name("commit");
	
	

	// *********************** RUN TEST AUTO ***********************

	// Before Script
//	ExcelManager_Map excel = new ExcelManager_Map(pathToExcelFile);

	// AUTO SCRIPT
	@org.junit.Test
	public void Test_CoinGecko() {

		// Before Test
		System.out.println("\t*** STARTING SCRIPT\t***");
		setUpListAccRegister(numberOfAcc,lengthOfUsername);
		for (String acc : listGeneratedEmail) {
			System.out.println(acc);
		}
		
		// Star Test
		startDriver();
		
		for (String email : listGeneratedEmail) {
			registerAcc_CoinGecko(email, passwordRegister);
			verifyAccAndLogin_InMailinator();
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
	}
	
	static void verifyAccAndLogin_InMailinator() {
		openURL(linkMailinator);
		getElement_ByFluentWait(titleEmail_CoinGecko, 180, 5).click();
		click(confirmAccBtn);
		click(loginBtn);		
	}
	
	static void registerAcc_CoinGecko(String email, String password) {
		click(signUpMenuBtn);
		input(emailBox, email);
		input(passBox, password);
		click(checkBox1);
		click(checkBox2);
		driver.switchTo().frame(driver.findElement(captchaFrame));
		click(captchaCheckBox);
		driver.switchTo().defaultContent();
		click(signUpBtn);		
	}
	
	static void setUpListAccRegister(int numberOfAcc, int lengthOfUsername) {
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
			} else {
				System.out.println("Email random is duplicated : " + emailRegister);
			}

		} while (listGeneratedEmailPrefix.size() < numberOfAcc);

		
		
	}
//	
//	static void loginFB_OnNewTab(String username, String pasword) {
//		openNewTab_thenSwitch();
//		driver.get(homePage_FB);
//		input(emailFbBox_NewTab, username);
//		input(passFbBox_NewTab, pasword);
//		click(loginFbBtn_NewTab);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		driver.close();
//		switchWindow(0);
//	}
//
//	static void logoutFB_thenReturnLikeListPage() {
//		openNewTab_thenSwitch();
//		driver.get(homePage_FB);
//		click(accountMenu);
//		click(logoutBtn);
//		driver.close();
//		switchWindow(0);
//		refreshCurrentPage();
//	}
//
//	static void checkBonusPage() {
//		Set<String> windows = driver.getWindowHandles();
//		for (String window : windows) {
//			driver.switchTo().window(window);
//			String currentURL = driver.getCurrentUrl();
//			if (currentURL.contains("bonus-page")) {
//				System.out.println("***** STOP AUTO-LIKE since \'Active members Bonus\' page displayed *****");
//				fail("\nASSERT FAIL ***** STOP AUTO-LIKE since \'Active members Bonus\' page's displayed *****");
//			}
//		}
//		switchWindow(0);
//	}
//
//	static void doLoopLike(String username_FB, String password_FB) {
//		int count = 0;
//		int numberOfLikeBtn = getListWebElement(listLikeBtn).size();
//		String firstWindow = driver.getWindowHandle();
//		String strSearch = "')]/descendant";
//		String strNewLikeBtn;		
//		By NewLikeBtn;
//		String strCurrentScore;
//
//		
//		for (int i = 0; i < numberOfLikeBtn; i++) {
//			if (count < numberOfLoop) {
//				checkBonusPage();
//				strNewLikeBtn = strLikeBtn.replace(strSearch, i + strSearch);
//				NewLikeBtn = By.xpath(strNewLikeBtn);
//				
//				strCurrentScore = getWebElement(currentScore).getText();				
//				System.out.print("@@@ Current Score: " + strCurrentScore + "\t");
//				
//				if (i == 0 && count == 0) {
//					loginFB_OnNewTab(username_FB, password_FB);
//				}				
//				
//				//fix error can't print Bonus page by wait 3s
//				try {
//					click(NewLikeBtn);
//				} catch (Exception e2) {
//					try {
//						System.out.println("\n\t--> Web's just reloaded... So, The script's reset to click 1st Like btn again");
//						Thread.sleep(3000);
//						//set newLikeBtn at 1st place again
//						//then click Likebtn again
//						i = 0;
//						strNewLikeBtn = strLikeBtn.replace(strSearch, i + strSearch);
//						NewLikeBtn = By.xpath(strNewLikeBtn);
//						click(NewLikeBtn);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					checkBonusPage();
//				}
//				
//				switchWindow(1);
//				
//				try {
//					click(likePageBtn);
//					count += 1;
//					System.out.println("### CLICKED LIKE Button : " + count);
//				} catch (Exception e) {
//					try {
//						click(likePostBtn);
//						count += 1;
//						System.out.println("### CLICKED LIKE Button : " + count);
//					} catch (Exception x) {
//						try {
//							click(likeVideoBtn);
//							count += 1;
//							System.out.println("### CLICKED LIKE Button : " + count);
//						} catch (Exception y) {
//							String currentURL_newWindow = getCurrentURL();
//							count += 1;
//							System.out.println("### CLICKED LIKE Button : " + count + " --> Missed!!!");
//							System.out.println("\t--> Please manually ReCheck URL: " + currentURL_newWindow);
//						}
//					}
//				} finally {
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					driver.close();
//
//				}
//				driver.switchTo().window(firstWindow);
//					
//				if (i == 13) {
//					i = -1;
//				}
//			} else {
//				break;
//			}
//		}
//	}
//
//	static void openFbLike() {
//		hoverAndClick(smExchangeBtn, fbLikesBtn);
//		checkBonusPage();
//	}
//
//	static void doLogin(String username_Like4Like, String password_Like4Like) {
//		click(loginBtn);
//		input(usernameBox, username_Like4Like);
//		input(passwordBox, password_Like4Like);
//		click(submitBtn);
//	}

	static void startDriver() {
		driverPath = "Drivers/chromedriver.exe";
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
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.manage().addCookie(new Cookie("session", cookie_session));
		
		openURL(cookie_hCaptchaPage);
		driver.manage().addCookie(new Cookie("__cfduid", cookie__cfduid));		
		
		openURL(cgHomePage);
	}

	static void switchWindow(int orderOfWindow) {
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver.switchTo().window(listWindow.get(orderOfWindow));
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
	
	static WebElement getElement_ByFluentWait(By by, int timeoutInSecond, int repeatInSecond) {
		Wait wait = new FluentWait(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(getWebElement(by))); 
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
