package CoreAutomation.BaseAutoPage;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import kotlin.jvm.Synchronized;

public class BasePage {
	private WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}	
	
	// *********************** CODE BELOW ***********************
	
	// start from 1
	protected void switchWindow(int orderOfWindow) {
		Set<String> windows = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>();
		listWindow.addAll(windows);
		driver.switchTo().window(listWindow.get(orderOfWindow - 1));
	}

	// DONT USE selenium-java and appium java-client TO AVOID BUG NoClassDefFound
	protected WebElement getElement_AfterFluentWait(By by, int timeoutInSecond, int repeatInSecond) {
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeoutInSecond))
				.pollingEvery(Duration.ofSeconds(repeatInSecond)).ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
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

	protected List<WebElement> getListWebElement(By by) {
		return driver.findElements(by);
	}

	protected WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	protected void click(By by) {
		getWebElement(by).click();
	}

	protected void input(By by, String text) {
		getWebElement(by).sendKeys(text);
	}

	protected void hover(By by) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getWebElement(by)).perform();
	}

	protected void hoverAndClick(By by1, By by2) {
		Actions acts = new Actions(driver);
		acts.moveToElement(getWebElement(by1)).click(getWebElement(by2)).perform();
	}

	protected void doubleClick(By by) {
		Actions acts = new Actions(driver);
		acts.doubleClick(getWebElement(by));
	}
}
