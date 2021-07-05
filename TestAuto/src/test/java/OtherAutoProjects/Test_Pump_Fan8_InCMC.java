package OtherAutoProjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Pump_Fan8_InCMC {
	WebDriver driver;
	String path_DriverChromeForWeb = "Drivers/WebChromeDriver/chromedriver.exe";
//	String proxyhttp = "http://191.101.39.131";
//	String port = "80";
//	String urlPubProxyToGetProxy = "http://pubproxy.com/api/proxy?limit=10&last_check=1&format=txt&type=http&https=false";

	List<List<String>> listURL_Total = new ArrayList<>();
	String url1;
	String url2;
//	String url3;
//	String url4;
//	String url5;

	String webCheckIP = "http://ifconfig.io/";
	String webGetProxy = "https://free-proxy-list.net";

	// Fan8 from CMC
	String webCMC = "https://coinmarketcap.com/";
	String webCMC_fan8 = "https://coinmarketcap.com/currencies/fan8/";

	// Fan8 from Google
	String webGoogle = "https://www.google.com/";
	String webGoogleWithSearch = "https://www.google.com/search?q=";
//	String coinSearch = "Fan8";
	String webSearchCMC = "coinmarketcap";

	String webDefiatoTesting = "https://testing.defiato.com/";
	String email = "defitest1@mailinator.com";
	String pass = "12345678";

//	String path_FileProxy = "DataTest/httpproxies.txt";
	List<String> listProxy = new ArrayList<>();

	int countTotalSuccess = 0;

	// ------------RUNTEST------------------------------------------------------------------------

	@Test
	public void testOpenFan8_FromMultiProxy(int numb, String coin, List<String> listUrl) {
		printRunningCaseTest(numb, coin);
		System.out.println();

		try {
			// List Proxy now is sure no Restarting
			listProxy = getListProxy_FromListURL(listUrl);
			printListProxy(listProxy);

			
			for (int i=0; i < listProxy.size(); i++) {				
				initChromeWithProxyHTTP(listProxy.get(i));
				int countProxy = i + 1;
				System.out.println("Using proxy " + countProxy + ":\t " + listProxy.get(i));
				
				try {
					selectCaseTest(numb, coin);

					// must close to avoid can't click buttons under it
					closeCMCPolicyBanner();

					// Only overview can click Rate Good
					clickOverview();

					scrollBotAndTopPage();
//						srcollToCoinAtEndPage();

					scrollToVoteBanner();
					clickRateGood();

					clickPairMarket();

					int timeInSecond = 0;
					// check url fan8 CMC appears
					if (coin.equalsIgnoreCase("fan8")) {
						
						// verify Fan8 link when wait < 10s
						do {
							System.out.println("Verified link:   " + driver.getCurrentUrl());
							sleep(1);
							timeInSecond++;
						} while (!driver.getCurrentUrl().contains("/currencies/fan8") && timeInSecond < 10);

						// Count success if wait < 10s
						if (timeInSecond < 10) {
							countTotalSuccess += 1;							
							System.out.println("Count Success:\t " + countTotalSuccess);
							driver.quit();
						}
					} else {
						countTotalSuccess += 1;
						System.out.println("Count Success:\t " + countTotalSuccess);
						driver.quit();
					}

				} catch (Exception e) {
					driver.quit();
					if (isRestarting_WhenGetListProxyAgain_FromListURL(listUrl)) {
						System.out.println("A server is in RESTARTING status. Restarting script...");
						sleep(30);
						testOpenFan8_FromMultiProxy(numb, coin, listUrl);					
					}					
				}
			}
//			} else {
//				sleep(30);				
//				testOpenFan8_FromMultiProxy(numb, coin, listUrl);

//			}
		} catch (Exception e) {
			System.out.println("Something went wrong. Restarting script...");
			sleep(30);
			testOpenFan8_FromMultiProxy(numb, coin, listUrl);
		}
	}

//	public void setListUrlPhuongCao() {
//		if(listURL_PhuongCao.size() <= 0) {
//			listURL_PhuongCao.add(url1_PhuongCao);
//			listURL_PhuongCao.add(url2_PhuongCao);
//			listURL_PhuongCao.add(url3_PhuongCao);
//			listURL_PhuongCao.add(url4_PhuongCao);
//			listURL_PhuongCao.add(url5_PhuongCao);
//		}
//	}

	// for case: Co restart trong so cac url
	public boolean isRestarting_WhenGetListProxyAgain_FromListURL(List<String> listURL) {
		boolean bo = false;
		try {
			for (String link : listURL) {
				URL url = new URL(link);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				InputStream responseStream = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
				String line;
				while ((line = rd.readLine()) != null) {
					if (line.equalsIgnoreCase("restarting")) {
						bo = true;
						break;
					}
				}
				connection.disconnect();
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Can't get proxy AGAIN from URLs:" + listURL);
		}
		return bo;
	}

	public boolean isRestarting_InListProxyGlobal(List<String> list) {
//		List<String> listLocal = new ArrayList<>();
//		listLocal = getListProxy_FromURL();
		if (list != null && list.size() > 0 && list.get(0).equalsIgnoreCase("restarting")) {
			return true;
		} else {
			return false;
		}
	}

//	public void testOpenCMC() throws IOException, InterruptedException {
//		getListProxy_FromURL();
//
//		int count = 0;
//
//		for (String proxy : listProxy_Global) {
//			initChromeWithProxyHTTP(proxy);
//			System.out.println("Using proxy: " + proxy);
//			// String webIP = "https://whatismyipaddress.com/";
//			// driver.get(webIP);
//			// Thread.sleep(5000);
//
//			try {
//
//				driver.get(webCMC);
//
////				By search = By.xpath("//div[@class='sc-16r8icm-0 fNFbRb']");
//				By search = By.xpath("(//div[@class='sc-1xvlii-0 dQjfsE']/*[@class='sc-16r8icm-0 fNFbRb'])[1]");
//
//				click(search);
////				System.out.println("Da94 click search");
//				By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");
////				input(inputFan8, coinSearch);
//				driver.findElement(inputFan8).sendKeys(coinSearch, Keys.RETURN);
//
//				int number = 0;
//				do {
//					System.out.println(driver.getCurrentUrl());
//					Thread.sleep(5000);
//					number++;
//
//				} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);
//
//				if (number < 12) {
//					count += 1;
//					System.out.println("Count " + count + " : " + proxy);
//				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			driver.close();
//		}
//	}

	// FOR test with Defiator
//	public void testLoginDefiatoTesting() throws IOException, InterruptedException {
//		getListProxy_FromFile();
//		int count = 0;
//		for (String proxy : listProxy) {
//			initChromeWithProxyHTTP(proxy);
//			// String webIP = "https://whatismyipaddress.com/";
//			// driver.get(webIP);
//			// Thread.sleep(5000);
//
//			try {
//				driver.get(webDefiatoTesting);
//				By bySignIn = By.xpath("(//a[@href='/signin'])[2]");
//				click(bySignIn);
//
//				By byEmail = By.xpath("//input[@name='email']");
//				By byPass = By.xpath("//input[@name='password']");
//				By bySignInBtn = By.xpath("//button[@type='submit']");
//				input(byEmail, email);
//				input(byPass, pass);
//				click(bySignInBtn);
//				int number = 0;
//				do {
//					System.out.println(driver.getCurrentUrl());
//					Thread.sleep(5000);
//					number++;
//
//				} while (!driver.getCurrentUrl().contains("/account/details") && number < 12);
//
////				click(search);
////				System.out.println("Da94 click search");
////				By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");
////				input(inputFan8, coinSearch);
////				driver.findElement(inputFan8).sendKeys(coinSearch,Keys.RETURN);
//
//				// count if success
//				if (number < 12) {
//					count += 1;
//					System.out.println("Count " + count + " : " + proxy);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//			driver.close();
//		}
//	}

	// --------------ACTIONS IN CASE
	// TEST----------------------------------------------------

	// click ReCaptcha if exist
	public void clickReCaptchaCheckbox_IfExist() {

		// try to click 2 times
		try {
			By byReCaptchaText = By.xpath("//label[@id='recaptcha-anchor-label']");
			waitUntilElementClickable(byReCaptchaText, 3).click();
//			
		} catch (Exception e) {
			// TODO: handle exception
			By byReCaptchaBox = By.xpath("//div[@class='recaptcha-checkbox-border']");
			waitUntilElementClickable(byReCaptchaBox, 3).click();
		}
	}

	public void clickCookiePopup_IfExist() {
		try {
			By byAgree = By.xpath("(//div[@class='jyfHyd'])[2]");
			waitUntilElementClickable(byAgree, 3).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void srcollToCoinAtEndPage() {
		By byCoinAtTheEndpage = By.xpath("//div[contains(@class,'sc-14a9h6a-0')]");
		scrollDownUntilSeeElement(500, byCoinAtTheEndpage);
	}

	public void closeCMCPolicyBanner() {
		By byPolicyBanner = By.xpath("//div[@class='cmc-cookie-policy-banner__close']");
		click(byPolicyBanner);
	}

	public void clickOverview() {
//		By byStatsBeforeOverview = By.xpath("//div[contains(@class,'statsSupplyBlock')]");
//		By bySocialTab = By.xpath("//div[contains(@class,'cmc-tab-social__body')]");
//		By byTabSwitcher = By.xpath("//div[contains(@class,'container routeSwitcher')]");
		By byOverview = By.xpath("//a[contains(@class,'x0o17e-0')][1]");
		click(byOverview);
		sleep(2);
	}

	public void scrollToVoteBanner() {
		By byVoteBanner = By.xpath("//div[contains(@class,'pqmllm-0')]");
		scrollDownUntilSeeElement(1000, byVoteBanner);
	}

	// Must scroll to show Vote banne before click Rate GOOD
	public void clickRateGood() {
		By byVoteGood = By.xpath("(//button[contains(@class,'iv7acg-0')])[1]");
		click(byVoteGood);
		sleep(2);
	}

	public void clickPairMarket() {
		By byPairMarket = By.xpath("//a[contains(@class,'qh9zi5')]");
		click(byPairMarket);
		sleep(3);
	}

	public void scrollBotAndTopPage() {
		scrollDownToEndPage();
		sleep(2);
		scrollUpToTopPage();
	}

//	public void scrollToVolumeFan8() {
//		By iconCoinVolume = By.xpath("//img[@alt='converter-coin-logo']");
//		scrollToElementInView(iconCoinVolume);
//	}

	public void printRunningCaseTest(int numb, String coin) {
		switch (numb) {
		case 1:
			System.out.println("### Running: Open " + coin.toUpperCase() + " page from Google Search");
			break;
		case 2:
			System.out.println("### Running: Open " + coin.toUpperCase() + " page from Coinmarket Cap");
			break;

		default:
			break;
		}
	}

	public void selectCaseTest(int numb, String coin) {
		switch (numb) {
		case 0:
			case0_OpenCoinPage_FromCMC_AndGoogle(coin);
			break;
		case 1:
			case1_OpenCoinPage_FromSearchCMC(coin);
			break;
		case 2:
			case2_OpenCoinPage_FromGoogle(coin);
			break;

		default:
			break;
		}
	}

	public void case0_OpenCoinPage_FromCMC_AndGoogle(String coin) {

	}

	public void case2_OpenCoinPage_FromGoogle(String coin) {
		driver.get(webGoogleWithSearch + coin + " " + webSearchCMC);

		// close Cookie popup
		clickCookiePopup_IfExist();

		// click ReCaptcha
		clickReCaptchaCheckbox_IfExist();

		// for both mini + maxi web
		By by1stSearchResult = By.xpath("(//*[@class='LC20lb DKV0Md'])[1]");
		click(by1stSearchResult);
		sleep(3);

		// will wait later
//		By symbolFan8 = By.xpath("//*[@class = 'nameSymbol___1arQV']");
	}

	public void case1_OpenCoinPage_FromSearchCMC(String coin) {
		driver.get(webCMC);

		// for maxi web
//		By search = By.xpath("//div[contains(@class,'sc-266vnq-1')]");
//		By inputFan8 = By.xpath("//input[contains(@class,'bzyaeu-3')]");

		// for not mini web
		By search = By.xpath("(//div[@class='sc-1xvlii-0 dQjfsE']/*[@class='sc-16r8icm-0 fNFbRb'])[1]");
		By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");

		click(search);
		driver.findElement(inputFan8).sendKeys(coin, Keys.RETURN);
		sleep(3);
	}

	// not using
//	public void testChangeProxy() throws IOException {
//		getListProxy_FromFile();
//		initChromeWithProxyHTTPS(listProxy_Global.get(1));
//		By valueCountryCode = By.xpath("//li[3]/div[2]");
//		String webIP = "http://ifconfig.io/";
//		driver.get(webIP);
//		;
//		String countryCode = driver.findElement(valueCountryCode).getText();
////		System.out.println("The new IP is " + countryCode);
//		if (!countryCode.equalsIgnoreCase("VN")) {
//			System.out.println("Change proxy success:");
//			System.out.println("\tThe new IP: " + proxyhttp);
//			System.out.print("\tCountry Code: " + countryCode);
//		}
//
//	}

	public void printListProxy(List<String> list) {
		System.out.println("List Proxy");
		int countProxy = 0;
		for (String proxy : list) {
			countProxy += 1;
			System.out.println("Proxy " + countProxy + ":\t" + proxy);
		}
	}

	// using 1
	// Make sure no restarting in List
	public List<String> getListProxy_FromListURL(List<String> listURL) {
		List<String> listURL_ToGetProxy = new ArrayList<>();
		try {
			for (int i = 0; i < listURL.size(); i++) {
				URL url = new URL(listURL.get(i));
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				InputStream responseStream = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
				String line;
				while ((line = rd.readLine()) != null) {
					if (!line.equalsIgnoreCase("restarting")) {
						listURL_ToGetProxy.add(line);
					} else {
						break;
					}
				}
				listURL_Total.add(listURL_ToGetProxy);
				connection.disconnect();
			}
//			connection.disconnect();
		} catch (Exception e) {
			System.out.println("Unable to get list Proxy now");
		}
		return listURL_ToGetProxy;
	}

	public boolean isProxyInURL(String proxy, List<List<String>> listURLToTal) {
		boolean bo = false;
		List<String> listLocal;
		for (int i = 0; i < listURLToTal.size(); i++) {
			for (int j = 0; j < listURLToTal.get(i).size(); j++) {
				if (proxy.equalsIgnoreCase(listURLToTal.get(i).get(j))) {
					bo = true;
				}
			}
		}
		return bo;
	}

	public List<String> getNewListProxy_AfterDeleteRestartingProxy() {
		List<String> localList = new ArrayList<String>();

		return localList;
	}

	// using 1
//	public List<String> getListProxy_FromURL() {
//		List<String> listLocal = new ArrayList<>();
//		try {
//			listLocal.clear();
////			URL url = new URL(urlPubProxyToGetProxy);
//			URL url = new URL(url1_PhuongCao);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			InputStream responseStream = connection.getInputStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
//			String line;
//			while ((line = rd.readLine()) != null) {
//				listLocal.add(line);
////				System.out.println("List Proxy: " + line);
//
//			}
//			connection.disconnect();
//
//		} catch (IOException e) {
////			e.printStackTrace();
//		}
//
//		return listLocal;
//
//	}

//	// using 1
//	public void getListProxy_FromFile() throws IOException {
//		File file1 = new File(path_FileProxy);
//		InputStream in = new FileInputStream(file1);
//		BOMInputStream bomInputStream = new BOMInputStream(in, false);
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bomInputStream));
//		String line;
//		while ((line = bufferedReader.readLine()) != null) {
//			listProxy.add(line);
//		}
//		System.out.println("Success add Proxy");
//	}

	// using 2
	public void initChromeWithProxyHTTP(String httpProxy) {
		System.out.println();
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);

		// hide log of chromedriver and java selenium
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		ChromeOptions chromeOptions = new ChromeOptions();

		// run without browser
//		chromeOptions.addArguments("headless");

		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		// a little weaker than System.setProperty("webdriver.chrome.silentOutput",
		// "true");
//		chromeOptions.setLogLevel(ChromeDriverLogLevel.OFF);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2); // disable browser noti
		chromeOptions.setExperimentalOption("prefs", prefs);
//		Proxy proxy = new Proxy();
//		proxy.setAutodetect(false);
//		proxy.setHttpProxy(httpProxy);
//		chromeOptions.setCapability("proxy", proxy);

		chromeOptions.addArguments("--proxy-server=" + httpProxy);

//		chromeOptions.addArguments("start-maximized");
//		chromeOptions.addArguments("start-minimized");

		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

//		driver.manage().window().minimize();
//		System.out.println("\rPre-Test: CHROME Driver Start Success");
	}

	// ssl proxy : not using
	public void initChromeWithProxyHTTPS(String httpsproxy) {
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);
		System.setProperty("webdriver.chrome.silentOutput", "false");
		ChromeOptions chromeOptions = new ChromeOptions();
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setSslProxy(httpsproxy);
		chromeOptions.setCapability("proxy", proxy);
		driver = new ChromeDriver(chromeOptions);
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().fullscreen();
		System.out.println("Pre-Test: CHROME Driver Start Success\r");
	}

	public void initChromeNoProxy() {
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);
		System.setProperty("webdriver.chrome.silentOutput", "false");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		System.out.println("Pre-Test: CHROME Driver Start Success\r");
	}

	// ------------HANDLE_USER
	// ACTIONS------------------------------------------------------------------------

	public void moveToElement(By by) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(by)).perform();
	}

	// The syntax of ScrollBy() methods is :
	// executeScript("window.scrollBy(x-pixels,y-pixels)");
	// x-pixels : horizontal, left if number is positive, right if number is
	// negative
	// y-pixels : vertical, down if number is positive, up if number is in negative
	// -y
	// x 0 -x
	// y

	public void scrollDownUntilSeeElement(int positive, By by) {
		try {
			do {
				scrollDown_ByPixel(positive);
				sleep(2);
			} while (!driver.findElement(by).isDisplayed());
		} catch (Exception e) {
			scrollDownUntilSeeElement(positive, by);
		}
	}

	// This will scroll the web page till end.
	public void scrollUpToTopPage() {
//    	String jsScript = "window.scrollTo(0, -document.body.scrollHeight)";
		String jsScript = "window.scrollTo(0, document.body.scrollTop)";
		executeByJavaScript(jsScript);
	}

	// This will scroll the web page till end.
	public void scrollDownToEndPage() {
		String jsScript = "window.scrollTo(0, document.body.scrollHeight)";
		executeByJavaScript(jsScript);

//    	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
//    	Robot robot = new Robot();
//    	robot.keyPress(KeyEvent.VK_CONTROL);
//    	robot.keyPress(KeyEvent.VK_END);
//    	robot.keyRelease(KeyEvent.VK_END);
//    	robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	// This will scroll the page till the element is found
	// scrollIntoView(false): Bot , true = top
	public void scrollToElement(By by) {
		WebElement webElement = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", webElement);
	}

	// This will scroll the page to Element already displayed in DOM
	// scrollIntoView(false): Bot , true = top
	public void scrollToElementInView(By by) {
		WebElement webElement = waitUntilElementClickable(by, 3);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", webElement);
	}

	// horizontal scroll right
	public void scrollRight_ByPixel(int negative) {
		executeByJavaScript("window.scrollBy(" + negative + ",0)");
	}

	// horizontal scroll left
	public void scrollLeft_ByPixel(int positive) {
		executeByJavaScript("window.scrollBy(" + positive + ",0)");
	}

	// vertical scroll up
	public void scrollUp_ByPixel(int negative) {
		executeByJavaScript("window.scrollBy(0," + negative + ")");
	}

	// vertical scroll down
	public void scrollDown_ByPixel(int positive) {
		executeByJavaScript("window.scrollBy(0," + positive + ")");

//    	executeByJavaScript("window.scrollBy(0,250)");
//    	executeByJavaScript("scroll(0, 250);");
//    	Robot robot = new Robot();
//    	robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//    	robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public void executeByJavaScript(String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
	}

	public void input(By by, String text) {
		getWebElement(by).sendKeys(text);
	}

	public void click(By by) {
		getWebElement(by).click();
	}

	public WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	public void sleep(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement waitUntilElementClickable(By by, int second) {
		// WebDriverWait is a subclass of FluentWait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
