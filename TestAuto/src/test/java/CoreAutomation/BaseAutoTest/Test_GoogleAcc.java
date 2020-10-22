package CoreAutomation.BaseAutoTest;

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
import org.openqa.selenium.support.ui.Select;

/**
 * Unit test for simple App.
 */
public class Test_GoogleAcc {
	
	// global
	static WebDriver driver;
	static String driverPath;

	// changed
	static String urlGoogleSignUp = "https://accounts.google.com/signup";
	static String lastName = "Nguyen";
	static String firstName = "My";	
	static String emailGG = "my.thao212000@gmail.com";	//duy.bao11102000
	static String passGG = "Docnhat001@";
	static String confirmPassGG = "Docnhat001@";
	
	static String urlXacMinhsdt = "https://accounts.google.com/signup/v2/webgradsidvphone";
	static String sdt = "915553900"; //use in case 'xac minh sdt' page display
	
	static String day = "21";
	static String month = "2";
	static String year = "2000";
	static String gender = "Female";
	static int numberOfLoop = 50;

	// Acc Details page
	static By lastNameBox = By.xpath("//input[@id='lastName']");
	static By firstNameBox = By.xpath("//input[@id='firstName']");
	static By emailBox = By.xpath("//input[@id='username']");
	static By passwordBox = By.xpath("//input[@name='Passwd']");
	static By confirmPasswordBox = By.xpath("//input[@name='ConfirmPasswd']");
	static By nextBtn1 = By.xpath("//div[@id='accountDetailsNext']");
	
	// Xac minh sdt page
	static By sdtBox = By.xpath("//input[@id='phoneNumberId']");
	static By sdtNextBtn = By.xpath("//div[@class='U26fgb O0WRkf zZhnYe e3Duub C0oVfc']");
	
	// Personal Details page
	static By dayBox = By.xpath("//input[@id='day']");
	static By monthSelect = By.xpath("//select[@id='month']"); //index 1
	static By yearBox = By.xpath("//input[@id='year']");
	static By genderSelect = By.xpath("//select[@id='gender']"); //index 1
	static By nextBtn2 = By.xpath("//div[@id='personalDetailsNext']");
	
	//TOS page
	static By agreeToSBtn = By.xpath("//div[@id='termsofserviceNext']");

	@org.junit.Test
	public void TestAutoCreateGoogleAcc() {
		System.out.println("Testbranch");
		System.out.println("\t###### WELCOME TO NTLNEO AUTO-CREATE GG ACC SCRIPT\t######");
		System.out.println("\t###### SkyPE: ntlneo1\t\t\t\t######");
		System.out.println("\t###### Email: lam.nguyenthanh84@gmail.com\t######");

		// START
		startGoogleSignUpPage();
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
		click(nextBtn1);
		
		if(driver.getCurrentUrl().contains(urlXacMinhsdt)) {
			input(sdtBox, sdt);
			click(sdtNextBtn);
		}
		
		input(dayBox, day);
		Select seMonth = new Select(driver.findElement(monthSelect));
		seMonth.selectByIndex(1);
		input(yearBox, year);
		Select seGender = new Select(driver.findElement(genderSelect));
		seGender.selectByIndex(1);
		click(nextBtn2);
		
	}

	static void startGoogleSignUpPage() {
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
		driver.get(urlGoogleSignUp);
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
