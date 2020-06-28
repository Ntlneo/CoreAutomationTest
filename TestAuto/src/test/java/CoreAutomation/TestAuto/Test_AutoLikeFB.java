package CoreAutomation.TestAuto;

import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import DataManager.ExcelManager;

/**
 * Unit test for simple App.
 */
public class Test_AutoLikeFB {

	// global
	static WebDriver driver;
	static String driverPath;
	
	// changed
//	static String username_Like4Like;
//	static String password_Like4Like;
//	static String username_FB;
//	static String password_FB;
	
//	static String username_Like4Like = "ntlneo";
//	static String password_Like4Like = "Docnhat001@";
//	static String emailFB = "an.thanh282000@gmail.com";
//	static String passFB = "Docnhat001@";
//	static String emailFB = "suzukihzt@gmail.com";
//	static String passFB = "Docnhat1";
//	static String emailFB = "phungtest04@gmail.com";
//	static String passFB = "phung123";
//	static String emailFB = "lamnguyeneditor@gmail.com";
//	static String passFB = "Docnhat001@";
//	static String emailFB = "lamnguyeneditor1@gmail.com";
//	static String passFB = "Docnhat001@";
//	static String emailFB = "lam.nguyenthanh841@gmail.com";
//	static String passFB = "Docnhat001@";
	static int numberOfLoop = 100;

	// locators
	static By loginBtn = By.xpath("//a[contains(@title,'Login')]");
	static By usernameBox = By.xpath("//input[contains(@id,'username')]");
	static By passwordBox = By.xpath("//input[contains(@id,'password')]");
	static By submitBtn = By.xpath("//a[@class='form-button']");

	static By smExchangeBtn = By.xpath("//a[contains(text(),'Social Media Exchange')]");
	static By fbLikesBtn = By.xpath("//a[contains(@title,'Facebook Likes')]");

	static String strLikeBtn = "//td[contains(@id,'task')]/descendant::span[contains(@id,'likebutton')]/a";
	static By listLikeBtn = By.xpath("//td[contains(@id,'task')]");

	// new window with pagelike
	///Regex cho Login-Log in-Log In: Log[\s]?in/i.test('Log In')
	// xpath 2.0: //tagA[@attrA[matches(., 'VAL\d')]]
//	static By dangnhapBtn = By.xpath("(//*[text()[matches(.,'Log[\s]?in/i')] or contains(text(),'Đăng nhập')]/ancestor::a)[1]");
	static By dangnhapBtn = By.xpath(
			"(//*[contains(text(),'Đăng nhập') or contains(text(),'Log in') or contains(text(),'Login') or contains(text(),'Log In')]/ancestor::a)[1]");			
	static By emailFbBox = By.xpath("//*[@name='email']");
	static By passFbBox = By.xpath("//*[@name='pass']");
	static By loginFbBtn = By.xpath("//*[@name='login']");
	static By likePageBtn = By.xpath(
			"//*[@aria-label='like button' or @aria-label='Like button' or @aria-label='nút thích' or @aria-label='Nút thích']");
	static By likePostBtn = By.xpath("//a[@data-autoid='autoid_7']");
	static By likeVideoBtn = By.xpath("//a[@data-autoid='autoid_6']");
	
	ExcelManager excel = new ExcelManager();
	
	@org.junit.Test
	public void TestAutoLike() {		
		System.out.println("\t###### WELCOME TO NTLNEO AUTO-LIKE SCRIPT\t######");
		System.out.println("\t###### SkyPE: ntlneo1\t\t\t\t######");
		System.out.println("\t###### Email: lam.nguyenthanh84@gmail.com\t######");

		// START
		startAutoLike();
				
		for (int i = 0; i < excel.listUsername_Like4Like.size(); i++) {
			doLogin(excel.listUsername_Like4Like.get(i), excel.listPassword_Like4Like.get(i));
			
			openFbLike();
			
			doLoopLike(excel.listUsername_FB.get(i), excel.listPassword_FB.get(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			System.out.println("\t###### END SCRIPT. SEE YA AGAIN !!!\t######");
			driver.quit();			
		}
		
		
		
		



	}

	// *********************** MIX ***********************

	static void checkBonusPage() {
		String currentURL = driver.getCurrentUrl();
		if (currentURL.contains("bonus-page")) {
			System.out.println("***** STOP AUTO-LIKE since \'Active members Bonus\' page displayed *****");
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				driver.switchTo().window(window);
				driver.close();
			}			
			fail("\n***** STOP AUTO-LIKE since \'Active members Bonus\' page's displayed *****");
		}
	}

	static void doLoopLike(String username_FB, String password_FB) {
		int count = 0;
		int numberOfLikeBtn = getListWebElement(listLikeBtn).size();

		
		for (int i = 0; i < numberOfLikeBtn; i++) {
			if (count < numberOfLoop) {
				checkBonusPage();
				String strSearch = "')]/descendant";
				String strNewLikeBtn = strLikeBtn.replace(strSearch, i + strSearch);
				By NewLikeBtn = By.xpath(strNewLikeBtn);
				click(NewLikeBtn);
				checkBonusPage();
				String firstWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for (String window : windows) {
					if (!firstWindow.equals(window))
						driver.switchTo().window(window);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (i == 0 && count == 0) {
					try {
						click(dangnhapBtn);
					} catch (Exception e) {
						click(dangnhapBtn);
					}
					input(emailFbBox, username_FB);
					input(passFbBox, password_FB);
					click(loginFbBtn);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					click(likePageBtn);
					count += 1;
					System.out.println("###### CLICKED LIKE Button : " + count);
					Thread.sleep(1000);
				} catch (Exception e) {
					try {
						click(likePostBtn);
						count += 1;
						System.out.println("###### CLICKED LIKE Button : " + count);
						Thread.sleep(1000);
					} catch (Exception x) {
						try {
							click(likeVideoBtn);
							count += 1;
							System.out.println("###### CLICKED LIKE Button : " + count);
							Thread.sleep(1000);
						} catch (Exception y) {
							String currentURL = driver.getCurrentUrl();
							count += 1;
							System.out.println("###### CLICKED LIKE Button : " + count + " --> Missed !");
							System.out.println("\t--> Please manually ReCheck URL: " + currentURL);
						}
					}
				} finally {
					driver.close();
				}
				driver.switchTo().window(firstWindow);
				if (i == 13) {
					i = -1;
				}
			} else {
				break;
			}
		}
	}

	static void openFbLike() {
		hoverAndClick(smExchangeBtn, fbLikesBtn);		
		checkBonusPage();
	}

	static void doLogin(String username_Like4Like, String password_Like4Like) {		
		click(loginBtn);
		input(usernameBox, username_Like4Like);
		input(passwordBox, password_Like4Like);
		click(submitBtn);
	}

	static void startAutoLike() {
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
//		driver.manage().window().setPosition(new Point(0, 50000));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.like4like.org");		
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
