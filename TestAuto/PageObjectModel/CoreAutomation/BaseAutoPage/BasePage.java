package CoreAutomation.BaseAutoPage;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class BasePage {
	private WebDriver driver;
	static public Process process;
	static public String commandOpenChromeInDebug = "cmd /c start chrome --remote-debugging-port=6789";
//	static public String commandCloseChromeInDebug = "cmd /c kill chrome --remote-debugging-port=6789";	//not work
//	static public String commandCloseChromeInDebug = "cmd /c taskkill /F /IM \"chrome.exe\" /T";	//force close chrome --> chrome ask restore when relaunch
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	// *********************** CODE BELOW ***********************
	// DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
	
	
	
	// *********************** PUBLIC Function	
	static public void showPopupUntilClickOK() {
		JFrame jf=new JFrame();
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "Please verify captcha manually then click OK", "WAITING TO VERIFY CAPTCHA",JOptionPane.INFORMATION_MESSAGE);
//		JOptionPane jop = new JOptionPane();
//		jop.showMessageDialog(jf, "Please verify captcha manually then click OK", "WAITING TO VERIFY CAPTCHA",jop.INFORMATION_MESSAGE);
	}
	
	public void openNewTab_thenCloseOldTabs() {
		driver.switchTo().newWindow(WindowType.TAB);
		String currentWindow = driver.getWindowHandle();
//		System.out.println("CURRENT New Tab: " + currentWindow);
		Set<String> windowSet = driver.getWindowHandles();
		for (String window : windowSet) {
			if(!window.equals(currentWindow)) {
//				System.out.println("Other Window: " + window);
				driver.switchTo().window(window).close();
//				System.out.println("CLOSED : " + window);
			}
			else {							
				driver = driver.switchTo().window(window);	
//				System.out.println("SWITCHED New Tab : " + window);	
			}
		}
	}
	
	public void closeAllWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Current Tab: " + currentWindow);
		System.out.println("Number of tabs: " + windows.size());
		for (String window : windows) {			
			driver = driver.switchTo().window(window);
			driver.close();
			System.out.println(window);
		}		
	}
	
	public void switchLatestWindow() {
		Set<String> windows = driver.getWindowHandles();
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Tab: " + currentWindow);
		System.out.println("Number of tabs: " + windows.size());
		for (String window : windows) {						
			driver = driver.switchTo().window(window);
			System.out.println(window);
		}		
	}
	
	// start from 1
	public void switchWindow(int orderOfWindow) {
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver = driver.switchTo().window(listWindow.get(orderOfWindow - 1));
	}
	
	public int getNumberOfWindow() {
		return driver.getWindowHandles().size();
	}	
	
	static public void runCmdComand(String command) {		
		try
		{
		    process = Runtime.getRuntime().exec(command);
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	static public void addLog(Boolean conditionTrue, String messageFail) {		
		if (conditionTrue) {
			System.out.println("\t\t--> PASSED");
		}else {
			System.out.println("\t\t--> FAILED : " + messageFail);
			assertTrue(false, messageFail );
		}
	}
	
	
	// *********************** PROTECTED Function	
	
	protected void tryToClickManyTime_UntilFailTextDisappear(int limitTimes, By elementToClick, int intervalInSecond, By failTextElement, String failText) {
		int tryTimes = 0;
		do {			
			click(elementToClick);
			tryTimes += 1;
			System.out.println("TRIED CLICK: " + tryTimes);
			try {
				Thread.sleep(Duration.ofSeconds(intervalInSecond).toMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (getElement_AfterFluentWait(failTextElement, 10, 1).getText().contentEquals(failText) && tryTimes < limitTimes);
	}
	
	protected void tryToClickManyTime_UntilSuccessTextAppear(int limitTimes, By elementToClick, int intervalInSecond, By successTextElement, String successText) {
		int tryTimes = 0;
		do {			
			click(elementToClick);
			tryTimes += 1;
			System.out.println("TRIED CLICK: " + tryTimes);
			try {
				Thread.sleep(Duration.ofSeconds(intervalInSecond).toMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!getElement_AfterFluentWait(successTextElement, 10, 1).getText().contentEquals(successText) && tryTimes < limitTimes);
	}
	
	protected void tryToClickManyTime(int limitTimes, By elementToClick, int intervalInSecond) {
		int tryTimes = 0;
		do {			
			click(elementToClick);
			tryTimes += 1;
			System.out.println("TRIED CLICK: " + tryTimes);
			try {
				Thread.sleep(Duration.ofSeconds(intervalInSecond).toMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (tryTimes < limitTimes);
	}	
	
	protected void tryToClickAgain_IfFail(int limitTimes, By elementToClick, int intervalInSecond) {
		int tryTimes = 1;
		for (int i = 0; i < limitTimes; i++) {
			try {
				click(elementToClick);
				tryTimes += i;
				System.out.println("TRIED CLICK: " + tryTimes);
				Thread.sleep(Duration.ofSeconds(intervalInSecond).toMillis());
			} catch (Exception e) {
				click(elementToClick);
				tryTimes += 1;
				System.out.println("TRIED CLICK: " + tryTimes);
			}
		}		
	}	
	
	protected void executeByJavaScript(String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor)driver;  
		js.executeScript(javaScript);		
	}

	
	protected void clickByJavaScript(String xpathString) {
		WebElement element = driver.findElement(By.xpath(xpathString));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	protected Boolean isElementDisplayed(By by) {
		return getElement(by).isDisplayed();
	}
	
	protected Boolean isElementVisibleThenDisplayed(By by) {
		return getElement_AfterFluentWait_Default(by).isDisplayed();
	}
	
	protected WebElement getElement_AfterFluentWait(By by, int timeoutInSecond, int repeatInSecond) {
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeoutInSecond))
				.pollingEvery(Duration.ofSeconds(repeatInSecond)).ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	//Default fluent wait 15s timeout - 1s pooling
	protected WebElement getElement_AfterFluentWait_Default(By by) {
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	//Default fluent wait 15s timeout - 1s pooling
	protected List<WebElement> getListElement_AfterFluentWait_Default(By by) {
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	protected void refreshCurrentPage() {
//				driver.findElement(By.cssSelector("body")).sendKeys(Keys.F5);
		driver.navigate().refresh();
	}

	// DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
	protected void openNewTab_thenSwitch() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	protected void openURL(String url) {
		driver.get(url);
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected List<WebElement> getListElement(By by) {
		return driver.findElements(by);
	}

	protected WebElement getElement(By by) {
		return driver.findElement(by);
	}

	protected void click(By by) {
		getElement(by).click();
	}

	protected void input(By by, String text) {
		getElement(by).sendKeys(text);
	}

	protected void hover(By by) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getElement(by)).perform();
	}

	protected void hoverAndClick(By by1, By by2) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getElement(by1)).click(getElement(by2)).perform();
	}

	protected void doubleClick(By by) {
		Actions acts = new Actions(driver);
		acts.doubleClick(getElement(by));
	}
}
