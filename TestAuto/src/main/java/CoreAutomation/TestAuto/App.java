package CoreAutomation.TestAuto;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Hello world!
 *
 */
public class App 
{
	//global
	static WebDriver driver;
	static String driverPath;
	
	//local
	static String username = "ntlneo";
	static String password = "Docnhat001@";
	
	//locators
	static By loginBtn = By.xpath("//a[contains(@title,'Login')]");
	static By usernameBox = By.xpath("//input[contains(@id,'username')]");
	static By passwordBox = By.xpath("//input[contains(@id,'password')]");
	static By submitBtn = By.xpath("//a[@class='form-button']");
	
	static By smExchangeBtn = By.xpath("//a[contains(text(),'Social Media Exchange')]");
	static By fbLikesBtn = By.xpath("//a[contains(@title,'Facebook Likes')]");
	
	static String strLikeBtn = "//td[contains(@id,'task')]/descendant::span[contains(@id,'likebutton')]/a";
	static By numberOfLikeBtn = By.xpath("//td[contains(@id,'task')]");
	
	static By dangnhapBtn = By.xpath("(//*[text()='Đăng nhập'])[1]");
	static By likePageBtn = By.xpath("(//*[text()='Thích' or text()='Like'])[1]");
//	static By likePageBtn = By.xpath("//div[@class='_59k _2rgt _1j-f _2rgt' and text()='Thích' or text()='Like']/preceding-sibling::*/img");
	
	
	//PLAY
    public static void main( String[] args )
    {
    	
        System.out.println( "WELCOME TO MY AUTO-LIKE SCRIPT" );
        
        startAutoLike();
        doLogin();
        openFbLike();
        doLoopLike();
        
    	try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        driver.quit();
    }
	
    //*********************** MIX ***********************
	
    
    static void doLoopLike() {
    	for (int i = 0; i < getListWebElement(numberOfLikeBtn).size(); i++) {
    		String strSearch = "')]/descendant";    		
    		String strNewLikeBtn = strLikeBtn.replace(strSearch,i + strSearch);
    		By NewLikeBtn =  By.xpath(strNewLikeBtn);
    		click(NewLikeBtn);
    		
    		String firstWindow  = driver.getWindowHandle();
    		System.out.println("Cửa sổ đầu tiên là: " + firstWindow);
    		
    		Set<String> windows = driver.getWindowHandles();
    		
    		for (String window : windows) {    			
				driver.switchTo().window(window);
				System.out.println("Các Cửa sổ sau là: " + window);
				click(likePageBtn);
	    		driver.close();
			}
    		
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		driver.switchTo().window(firstWindow);
    	}
    }
    
    static void openFbLike() {
    	hoverAndClick(smExchangeBtn, fbLikesBtn);    	
    }
    
	static void doLogin() {
		click(loginBtn);
		input(usernameBox, username);
		input(passwordBox, password);
		click(submitBtn);
	}
    
    static void startAutoLike() {
    	driverPath = "Drivers/chromedriver.exe";
    	System.setProperty("webdriver.chrome.driver", driverPath);
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    	
    	driver.get("https://www.like4like.org");    	
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
