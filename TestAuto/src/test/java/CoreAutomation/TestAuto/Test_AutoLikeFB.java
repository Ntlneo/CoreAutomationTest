package CoreAutomation.TestAuto;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.browser.Browser;
import org.openqa.selenium.devtools.browser.model.PermissionType;
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
	static String homePage_Like4Like = "https://www.like4like.org";
	static String homePage_FB = "https://www.facebook.com";
	static String likeListPage_Like4Like = "https://www.like4like.org/free-facebook-likes.php";
	static int numberOfLoop = 2;

	// locators
	static By loginBtn = By.xpath("//a[contains(@title,'Login')]");
	static By usernameBox = By.xpath("//input[contains(@id,'username')]");
	static By passwordBox = By.xpath("//input[contains(@id,'password')]");
	static By submitBtn = By.xpath("//a[@class='form-button']");

	static By smExchangeBtn = By.xpath("//a[contains(text(),'Social Media Exchange')]");
	static By fbLikesBtn = By.xpath("//a[contains(@title,'Facebook Likes')]");

	static String strLikeBtn = "//td[contains(@id,'task')]/descendant::span[contains(@id,'likebutton')]/a";
	static By listLikeBtn = By.xpath("//td[contains(@id,'task')]");

	// new FB window with pagelike
	static By dangnhapBtn = By.xpath(
			"(//*[contains(text(),'Đăng nhập') or contains(text(),'Log in') or contains(text(),'Login') or contains(text(),'Log In')]/ancestor::a)[1]");			
	static By emailFbBox = By.xpath("//*[@name='email']");
	static By passFbBox = By.xpath("//*[@name='pass']");
	static By loginFbBtn = By.xpath("//*[@name='login']");
	static By likePageBtn = By.xpath(
			"//*[@aria-label='like button' or @aria-label='Like button' or @aria-label='nút thích' or @aria-label='Nút thích']");
	static By likePostBtn = By.xpath("//a[@data-autoid='autoid_7']");
	static By likeVideoBtn = By.xpath("//a[@data-autoid='autoid_6']");
	
	// FB to logout	
	static By accountMenu = By.xpath("//*[@class='l9j0dhe7 j83agx80']/div[1]");
	static By logoutBtn = By.xpath("//*[text()='Đăng xuất' or text()='Log Out']");
	
	
	// *********************** RUN TEST AUTO ***********************
	
	// Before Test
	ExcelManager excel = new ExcelManager();

	// Start Test
	@org.junit.Test
	public void TestAutoLike() {
		System.out.println("\t###### WELCOME TO NTLNEO AUTO-LIKE SCRIPT\t######");
		System.out.println("\t###### SkyPE: ntlneo1\t\t\t\t######");
		System.out.println("\t###### Email: lam.nguyenthanh84@gmail.com\t######");

		// START
		startAutoLike();

		for (int i = 0; i < excel.listAcc_Like4Like.size(); i++) {
			for (String key1 : excel.listAcc_Like4Like.get(i).keySet()) {
				doLogin(key1, excel.listAcc_Like4Like.get(i).get(key1));

				openFbLike();

				for (int j = 0; j < excel.listAcc_FB.size(); j++) {
					for (String key2 : excel.listAcc_FB.get(j).keySet()) {
						doLoopLike(key2, excel.listAcc_FB.get(j).get(key2));						
						logoutFB_thenReturnLikeListPage();			
					}
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		
		sayGoodBye();
	}

	// After Test
	public void sayGoodBye() {
		System.out.println("\t###### END SCRIPT. SEE YA AGAIN !!!\t######");
		driver.quit();
	}
	
	// *********************** TASKS ***********************

	static void logoutFB_thenReturnLikeListPage() {
		openNewTab_thenSwitch();
		driver.get(homePage_FB);
		
		try {
			click(accountMenu);
		} catch (Exception e) {
			click(accountMenu);
		}
		click(logoutBtn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get(likeListPage_Like4Like);
	}
	
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

		System.out.println("Using acc FB : " + username_FB);
		for (int i = 0; i < numberOfLikeBtn; i++) {
			if (count < numberOfLoop) {
				checkBonusPage();
				String strSearch = "')]/descendant";
				String strNewLikeBtn = strLikeBtn.replace(strSearch, i + strSearch);
				By NewLikeBtn = By.xpath(strNewLikeBtn);
				click(NewLikeBtn);
				checkBonusPage();
				
//				String firstWindow = driver.getWindowHandle();
				switchWindow(1);
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
				switchWindow(0);
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
		driver.get(homePage_Like4Like);		
	}
	
	static void switchWindow(int orderOfWindow) {		
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver.switchTo().window(listWindow.get(orderOfWindow));		
	}
	
	
	// *********************** BASE TEST ***********************
	
	static void refreshCurrentPage() {		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.F5);
	}
	
	//not work
	static void openNewTab_thenSwitch() {		
		driver.switchTo().newWindow(WindowType.TAB);
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
