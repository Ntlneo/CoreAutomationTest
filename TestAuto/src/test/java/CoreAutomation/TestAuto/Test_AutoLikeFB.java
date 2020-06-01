package CoreAutomation.TestAuto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 * Unit test for simple App.
 */
public class Test_AutoLikeFB {
	
	// global
	static WebDriver driver;
	static String driverPath;

	// changed
	static String lastName = "Nguyen";
	static String firstName = "My";	
	static String emailGG = "my.thao212000@gmail.com";
	static String passGG = "Docnhat001@";
	static String confirmPassGG = "Docnhat001@";
	static int numberOfLoop = 50;

	// SignUp page
	static By lastNameBox = By.xpath("//input[@id='lastName']");
	static By firstNameBox = By.xpath("//input[@id='firstName']");
	static By emailBox = By.xpath("//input[@id='username']");
	static By passwordBox = By.xpath("//input[@name='Passwd']");
	static By confirmPasswordBox = By.xpath("//input[@name='ConfirmPasswd']");
	static By nextBtn = By.xpath("//div[@id='accountDetailsNext']");
	

	// new window with pagelike
	static By dayBox = By.xpath("(//*[contains(text(),'Đăng nhập')])[1]/..");
	

	@org.junit.Test
	public void TestAutoCreateGoogleAcc() {
		System.out.println("Testbranch");
		System.out.println("\t###### WELCOME TO NTLNEO AUTO-CREATE GG ACC SCRIPT\t######");
		System.out.println("\t###### SkyPE: ntlneo1\t\t\t\t######");
		System.out.println("\t###### Email: lam.nguyenthanh84@gmail.com\t######");

		// START
		startGoogleAcc();
		signupGoogle();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\t###### END SCRIPT. SEE YA AGAIN !!!\t######");
		driver.quit();

	}

	// *********************** MIX ***********************
	
	static void signupGoogle() {
		input(lastNameBox, lastName);
		input(firstNameBox, firstName);
		input(emailBox, emailGG);
		input(passwordBox, passGG);
		input(confirmPasswordBox, confirmPassGG);
		click(nextBtn);
		
		
	}

	static void startGoogleAcc() {
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
		driver.get("https://accounts.google.com/signup");
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
