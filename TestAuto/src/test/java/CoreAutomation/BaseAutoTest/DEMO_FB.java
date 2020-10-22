package CoreAutomation.BaseAutoTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class DEMO_FB {

	public static final int DEFAULT_TIMEOUT = 15;// 10 secs ;

	//Data test
	public static String strUsername = "fancythiezhang@gmail.com";
	public static String strPassword = "tudangnhap12345";
	public static String strSearch = "Hoc tieng anh";
	public static String strComment = "Wowwww, it's amazing";
	public static int scrollDownTimes = 5;
	public static int scrollUpTimes = 3;
	public static int scrollDownFewTimes = 4;
	

	public AppiumDriver driver;


	public WebElement we;
	public MobileElement me;
	
	
	
	//FB elements - language = ENG
	
	//Splash
//	By etcBtn = By.xpath("(//*[contains(@class,'android.widget.Button')])[1]");
	By etcBtn = By.xpath("(//*[@class='android.widget.Button'])[1]");
	By loginAnotherAccBtn = By.xpath("(//*[@class='android.widget.Button'])[2]");
	By registerBtn = By.xpath("(//*[@class='android.widget.Button'])[3]");	
	
	//Login
	By usernameFBTb = MobileBy.AccessibilityId("Username");
	By passwordFBTb = MobileBy.AccessibilityId("Password");
	By loginBtn = MobileBy.AccessibilityId("Log In");
	
	//Save login
	By okSaveAccBtn = By.xpath("(//*[@class='android.widget.Button'])[2]");
	
	//Home
	By avatarIcon = MobileBy.AccessibilityId("Go to profile");
	By makePostTb = MobileBy.AccessibilityId("Make a post on Facebook");
	By seachBtn = MobileBy.AccessibilityId("Search Facebook");
	By messengerBtn = MobileBy.AccessibilityId("Messaging");
//	By moreMenuBtn = By.xpath("//*[@content-desc='Messaging']/following::*[contains(@content-desc,'Menu')]");
	By moreMenuBtn = By.xpath("//android.view.View[contains(@content-desc,'Menu')]");
	
	By logoutBtn = By.xpath("//*[contains(@content-desc,'Log Out')]");
	
	
	//Search
	By searchTb = By.xpath("//*[@class='android.widget.EditText']");
	By postsMenuBtn = MobileBy.AccessibilityId("Posts");
	By likeBtn = By.xpath("(//*[contains(@content-desc,'Like button') and contains(@text,'Like')])[1]"
			+ " | (//*[contains(@content-desc,'Like button') and contains(@text,'Like')])[2]");
	By commentBtn = By.xpath("(//*[contains(@content-desc,'Comment Button') and contains(@text,'Comment')])[1]"
			+ " | (//*[contains(@content-desc,'Comment Button') and contains(@text,'Comment')])[2]");
	By commentTb = By.xpath("//*[@class='android.widget.EditText']");
	By commentSendBtn = MobileBy.AccessibilityId("Send");	
	
	
	//Script noti
	String scriptStart = "Starting script DEMO autoFB ......";
	String driverStart = "\nCreated driver successfully";
	String notLoginFB = "\nYou're currently not yet login FB";
	String sciptEnd = "\nEnded script DEMO autoFB";
	
	//Scenario test: 
	String step1 = "1. Open FB";
	String step2 = "2. Input username and password";
	String step3 = "3. Login FB";
	String step4 = "4. Click Search";
	String step5 = "5. Input text 'Hoc tieng anh' and search";
	String step6 = "6. Scroll down 5 times";
	String step7 = "7. Scroll up 3 times";
	String step8 = "8. Click Menu 'Posts'";
	String step9 = "9. Scroll down few time";
	String step10 = "10. Click like btn";
	String step11 = "11. Click comment btn";
	String step12 = "12. Input then send a message 'Wowwww, it's amazing'";
	
	@Test
	public void demoFB() {		
		System.out.println(step1);
		
		// Try to logout if logged in FB already
		try {
			logoutIfLoggedIn();
		} catch (Exception e) {
			System.out.println(notLoginFB);
		}
		
		loginFB();
		
		click(seachBtn);
		System.out.println(step4);
		
		inputThenEnter(searchTb, strSearch);		
		System.out.println(step5);
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		scrollDown(scrollDownTimes);
		System.out.println(step6);
		
		scrollUp(scrollUpTimes);
		System.out.println(step7);
		
		
		click(postsMenuBtn);
		System.out.println(step8);
		
		scrollDown(scrollDownFewTimes);
		System.out.println(step9);
		
		click(likeBtn);
		System.out.println(step10);
		
		click(commentBtn);
		System.out.println(step11);
		
		input(commentTb, strComment);
		click(commentSendBtn);
		System.out.println(step12);
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	//MIX
	
	public void logoutIfLoggedIn() {
		if (isElementDisplay(loginAnotherAccBtn) && isElementDisplay(makePostTb)) {

			// click 2 time to show MoreMenu button
			try {
				click(moreMenuBtn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				click(moreMenuBtn);
			}

			// wait menu load
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			scrollDown(1);
			click(logoutBtn);
		}
	}	
	
	
	public void loginFB() {
		click(loginAnotherAccBtn);
		input(usernameFBTb, strUsername);
		input(passwordFBTb, strPassword);
		System.out.println(step2);		
		
		click(loginBtn);		
		click(okSaveAccBtn);
		verifyCurrentPageisHomePage();		
	}
	
	public void verifyCurrentPageisHomePage() {
		if (isElementDisplay(avatarIcon) && isElementDisplay(makePostTb)) {
			System.out.println(step3);
		}
	}
	
	/**
	* Original Coordinator: TOP LEFT CORNER<p>
	* x : horizontal<p> .Right is +
	* y : vertical<p> .Down is +
	* percent: 0 - 100
	*/ 
	public void scroll(int xPercent_START, int yPercent_START, int xPercent_END, int yPercent_END, int times) {
		int sizeX = driver.manage().window().getSize().getWidth();
		int sizeY = driver.manage().window().getSize().getHeight();		
//		System.out.println("Size: " + sizeX + "\t" + sizeY);
		TouchAction ta = new AndroidTouchAction(driver);
		for (int i = 1; i <= times; i++) {
			ta.longPress(new PointOption().point(sizeX * xPercent_START/100, sizeY * yPercent_START/100))
			.moveTo(new PointOption().point(sizeX * xPercent_END/100 , sizeY * yPercent_END/100))
			.release()
			.perform();
			System.out.println("Scroll lan " + (i));
		}
		
	}	
	
	public void scrollUp(int times) {
		scroll(50, 20, 50, 80, times);
	}
	
	public void scrollDown(int times) {
		scroll(50, 80, 50, 20, times);
	}
	
	public boolean isElementDisplay(By by) {
		return driver.findElement(by).isDisplayed();
	}
	
	public void click(By by) {
		driver.findElement(by).click();
	}
	
	public void inputThenEnter(By by,String text) {
		we = getWebElement(by);
		we.clear();		
		we.sendKeys(text);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));		
	}
	
	public void input(By by,String text) {
		we = getWebElement(by);
		we.clear();
		we.sendKeys(text);
	}

	public WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	@Before
	public void before() {
		System.out.println(scriptStart);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Note8");		
		capabilities.setCapability("automationName", "UiAutomator2");

		capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.facebook.katana");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.facebook.katana.LoginActivity");
//		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.facebook.katana.app.FacebookSplashScreenActivity");

		//for run NOX only
//        capabilities.setCapability("udid", "127.0.0.1:62001");
		
		//Auto accept all permissions of app. Invalid if noReset = true
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
//		capabilities.setCapability("autoGrantPermissions", "true");
		
		//Should use in Inspector Appium
//		capabilities.setCapability("noReset", "true");
		
		//changed DEVICE language when and after run script auto to avoid bug of different accs FB
		capabilities.setCapability("language", "en");
		capabilities.setCapability("locale", "us");
		
		//To enable to press Enter on Android virtual keyboard
//		capabilities.setCapability("unicodeKeyboard", "true");                                     
//		capabilities.setCapability("resetKeyboard", "true");


		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
			System.out.println(driverStart);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


	@After
	public void after() {
		driver.quit();
		System.out.println(sciptEnd);
	}
}