package CoreAutomation.TestAuto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 * Unit test for simple App.
 */
public class Test_MoBaiHatYoutube {
	
	// global
	static WebDriver driver;
	static String driverPath;

	// changed	
	static String username = "ntlneo";
	static String password = "Docnhat001@";
	static String emailFB = "suzukihzt@gmail.com";
	static String passFB = "Docnhat1";
	static int numberOfLoop = 20;

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
	static By dangnhapBtn = By.xpath("(//*[text()='Đăng nhập'])[1]/..");
	static By emailFbBox = By.xpath("//*[@name='email']");
	static By passFbBox = By.xpath("//*[@name='pass']");
	static By loginFbBtn = By.xpath("//*[@name='login']");
	static By likePageBtn = By.xpath("//*[@aria-label='like button' or @aria-label='nút thích']");
	static By likePostBtn = By.xpath("//a[@data-autoid='autoid_7']");

	@org.junit.Test
	public void youtubeSearchAndPlay() {
		System.out.println("Testbranch");
		System.out.println("\t###### WELCOME TO NTLNEO AUTO-LIKE SCRIPT\t######");
		System.out.println("\t###### SkyPE: ntlneo1\t\t\t\t######");
		System.out.println("\t###### Email: lam.nguyenthanh84@gmail.com\t######");

		// START
		playYoutube();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\t###### END SCRIPT. SEE YA AGAIN !!!\t######");
		driver.quit();

	}

	static void playYoutube() {
		driverPath = "Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		options.setExperimentalOption("useAutomationExtension", false);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().setPosition(new Point(0, 50000));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.youtube.com");
		
		By searchBox = By.xpath("//input[@id='search']");
		By videoTitle1 = By.xpath("(//a[@id='video-title'])[1]");
		By videoScreen = By.xpath("//video");
		By durationVideo = By.xpath("//*[@class='ytp-time-duration']");
		By currentTimeVideo = By.xpath("//*[@class='ytp-time-current']");
		String textSearch1 = "Độ ta không độ nàng";
		String textSearch2 = "When you say nothing at all";
		List<String> listSearch = new ArrayList<String>();
		listSearch.add(textSearch1);
		listSearch.add(textSearch2);
		
		for(int i = 0; i<2; i++) {
			getWebElement(searchBox).clear();
			input(searchBox, listSearch.get(i));		
			getWebElement(searchBox).sendKeys(Keys.ENTER);
			click(videoTitle1);
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//move chuot
			Actions acts = new Actions(driver);
			acts.moveToElement(getWebElement(videoScreen)).moveByOffset(100, 0).moveByOffset(-100, 0).perform();
			
			String durationText = driver.findElement(durationVideo).getText();
			System.out.println("Duration video : " + durationText);
			int minute = Integer.parseInt(durationText.substring(0, 1));
			int second = Integer.parseInt(durationText.substring(2));
			int totalDurationInSecond = minute*60 + second;
			System.out.println("Duration Time in Second : " + totalDurationInSecond);
			int expectedStopTime = totalDurationInSecond*40/100;
			System.out.println("Expected Stop Time in Second : " + expectedStopTime);
			
			try {
				Thread.sleep(Duration.ofSeconds(expectedStopTime).toMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
		
		
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
