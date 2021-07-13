package OtherAutoProjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Lists;

public class Test_Pump_Fan8_InCMC {
	


	
	public Process process;
	
	WebDriver driver;
	String path_DriverChrome_Window = "Drivers/WebChromeDriver/chromedriver.exe";
	String path_DriverChrome_Linux = "Drivers/WebChromeDriver_Linux/chromedriver";
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
	String webCMC_About = "https://coinmarketcap.com/about/";

	// Fan8 from Google
	String webGoogle = "https://www.google.com/";
	String webGoogleWithSearch = "https://www.google.com/search?q=";
	String webTokenFan8 = "https://token.fan8.club";

	String keyworld_ToSearchInWeb = "coinmarketcap";

	String webDefiatoTesting = "https://testing.defiato.com/";
	String email = "defitest1@mailinator.com";
	String pass = "12345678";

//	String path_FileProxy = "DataTest/httpproxies.txt";
	List<String> listProxy_Origin = new ArrayList<>();
	List<String> listProxy_DidRun = new ArrayList<>();
	List<String> listProxy_New = new ArrayList<>();
	List<String> listProxy_Remain = new ArrayList<>();

	int countTotalSuccess = 0;

	// To set Stop Time at CMC and TokenFan8 pages
//	List<Integer> list_RandomSecond_StopAtCMC = Lists.newArrayList(30, 60);
//	List<Integer> list_RandomSecond_StopAtTokenFan8 = Lists.newArrayList(30, 60);
	List<Integer> list_RandomSecond_StopAtCMC = Lists.newArrayList(0, 0);
	List<Integer> list_RandomSecond_StopAtTokenFan8 = Lists.newArrayList(0, 0);	

	// ------------RUNTEST------------------------------------------------------------------------
	
	// run case:
	// - Go to Fan8, From CMC search
	// - Go to Fan8, From Google Search
	// - Go to CMC, From Token Fan8	
	public void testOpenFan8_FromMultiProxy(int numb, String coin, List<String> listUrl) {
		printRunningCaseTest(numb, coin);
//		Ztest.log.printToLogFileAndConsole();

		try {

			// List Proxy now is sure no Restarting
			// get list Proxy mới phải clear list cũ trc
//			listProxy_Origin.clear();
////			listProxy_Origin = getListProxy_FromListURL(listUrl);
//			listProxy_Origin = getListProxy_FromFile();
//			if (listProxy_Origin.size() > 0) {
//				Collections.shuffle(listProxy_Origin);
//				printListProxy(listProxy_Origin);
//			} else {
//				Ztest.log.printToLogFileAndConsole("No proxy on servers now. Trying to get proxy after 30s...");
//				sleep(30);
//			}
//
//			// nếu đã từng chay thì remove những proxy đã chạy
//			if (listProxy_DidRun.size() > 0) {
//				Collection collection_ofListDidRun = new ArrayList(listProxy_DidRun);
//				listProxy_Origin.removeAll(collection_ofListDidRun);
//				listProxy_DidRun.clear();
//
//			}

//			for (int i = 0; i < listProxy_Origin.size(); i++) {
			for (int i = 0; i < 10; i++) {
				Ztest.log.printToLogFileAndConsole("\r");
//				initChromeWithProxyHTTP(listProxy_Origin.get(i));
				initChromeNoProxy();
				int countProxy = i + 1;
//				Ztest.log.printToLogFileAndConsole("Using proxy " + countProxy + ":\t" + listProxy_Origin.get(i));

				String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent");
				Ztest.log.printToLogFileAndConsole("UserAgent:\t" + userAgent);

				try {

					// Can't run 3 cases at the same time

					// Run case 1 and 2 : Must open this
					case1_2_OpenFan8_FromCMC_AndFromGG(numb, coin);

					// Run case 3
//					selectCaseTest(numb, coin);

					listProxy_DidRun.add(listProxy_Origin.get(i));
					driver.quit();

				} catch (Exception e) {					
					driver.quit();
					
//					if (isRestarting_WhenGetListProxyAgain_FromListURL(listUrl)) {
//						Ztest.log.printToLogFileAndConsole("A server is in RESTARTING status. Restarting script...");
//						sleep(30);
//						testOpenFan8_FromMultiProxy(numb, coin, listUrl);
//					} else {
//						Ztest.log.printToLogFileAndConsole(
//								"Something went wrong (proxy die slow, can't click element...) but no RESTARTING status in servers. Continue to the next Proxy.");
//					}
				}
			}
			listProxy_Origin.clear();
		} catch (Exception e) {
			driver.quit();
			Ztest.log.printToLogFileAndConsole("Something went wrong. Restarting script...");
			sleep(30);
			testOpenFan8_FromMultiProxy(numb, coin, listUrl);
		}
	}

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
			Ztest.log.printToLogFileAndConsole("Can't get proxy AGAIN from URLs:" + listURL);
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

	// FOR test with Defiator	
	public void testLoginDefiatoTesting() {
//		getListProxy_FromFile();
		List<String> listlocal = new ArrayList<>();
		listlocal = getListProxy_FromURL("https://teamcity.quickom.com/proxy2.txt");
		printListProxy(listlocal);
		int count = 0;
		for (String proxy : listlocal) {
			initChromeWithProxyHTTP(proxy);
			Ztest.log.printToLogFileAndConsole("Running with proxy:/t" + proxy);
//			driver.manage().window().maximize();
			// String webIP = "https://whatismyipaddress.com/";
			// driver.get(webIP);
			// Thread.sleep(5000);

			try {
				driver.get(webDefiatoTesting);
				Ztest.log.printToLogFileAndConsole("Chrome opened Defiato web");
				
				//For mini web
				By byMenuBtn = By.xpath("//button[@aria-label='Toggle navigation']");
				click(byMenuBtn);
				By bySignIn = By.xpath("(//a[@href='/signin'])[1]");
				
				//For maxi web
//				By bySignIn = By.xpath("(//a[@href='/signin'])[2]");
				click(bySignIn);

				By byEmail = By.xpath("//input[@name='email']");
				By byPass = By.xpath("//input[@name='password']");
				By bySignInBtn = By.xpath("//button[@type='submit']");
				input(byEmail, email);
				input(byPass, pass);
				click(bySignInBtn);
				sleep(3);
				int number = 0;
				do {
					Ztest.log.printToLogFileAndConsole(driver.getCurrentUrl());
					sleep(1);
					Ztest.log.printToLogFileAndConsole(number);
					number++;					
				} while (!driver.getCurrentUrl().contains("/account/details") && number < 10);

				// count if success
				if (number < 10) {
					count += 1;
					Ztest.log.printToLogFileAndConsole(driver.getCurrentUrl());
					Ztest.log.printToLogFileAndConsole("Count " + count);
					
				}
				driver.quit();
			} catch (Exception e) {
				// TODO: handle exception
				driver.quit();
				testLoginDefiatoTesting();
			}

//			driver.quit();
		}
	}

	// --------------ACTIONS IN CASE
	// TEST----------------------------------------------------

	public void scrollTo_OnCMClink() {
		By byFan8OnCMC = By.xpath("//a[@title='CMC']");
		scrollDownUntilSeeElement(1000, byFan8OnCMC);
	}

	public void scrollTo_Fan8PlatformTxt() {
		By byFan8Platform = By.xpath("//div[@class='title-3 mb-2']");
		scrollDownUntilSeeElement(1000, byFan8Platform);
	}

	public void scrollTo_About() {
		By byAboutOnCMC = By.xpath("//a[@href='/about/']");
		scrollDownUntilSeeElement(1000, byAboutOnCMC);
	}

	public void clickAbout_OnCMC() {
		By byAboutOnCMC = By.xpath("//a[@href='/about/']");
		click(byAboutOnCMC);
	}

	public void clickOnCMC_Link() {
		By byFan8OnCMC = By.xpath("(//a[@title='CMC'])[1]");
		click(byFan8OnCMC);
	}

	public void clickTokenFan8Page() {
		By byTokenFan8 = By.xpath("//a[contains(@class,'button___2MvNi')]");
		click(byTokenFan8);
//		sleep(3);
	}

	public void clickDextools_Pair() {
		By byDextools = By.xpath("//a[@title='dextools']");
		click(byDextools);
//		sleep(3);
	}

	// click ReCaptcha if exist
	public void clickReCaptchaCheckbox_IfExist() {

		// try to click 2 times
		try {
			By byReCaptchaBox = By.xpath("//div[@class='recaptcha-checkbox-border']");
			waitUntilElementClickable(byReCaptchaBox, 3).click();
			By byReCaptchaText = By.xpath("//label[@id='recaptcha-anchor-label']");
			waitUntilElementClickable(byReCaptchaText, 3).click();			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clickCookiePopup_IfExist() {
		try {
			// for noImage
			By byAgree = By.xpath("(//input[@type='submit'])[1] | (//div[@class='jyfHyd'])[2]");
			waitUntilElementClickable(byAgree, 3).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void closeCMCPolicyBanner() {
		By byPolicyBanner = By.xpath("//div[@class='cmc-cookie-policy-banner__close']");
		click(byPolicyBanner);
	}

	public void clickMarket() {
		By byMarket = By.xpath("//a[contains(@class,'x0o17e-0')][2]");
		click(byMarket);
	}

	public void clickOverview() {
		By byOverview = By.xpath("//a[contains(@class,'x0o17e-0')][1]");
		click(byOverview);
//		sleep(2);
	}

	public void scrollToViewAllMarkets() {
		By byAllMarkets = By.xpath("(//a[contains(@class,'sc-10p9viq-4')])");
		scrollDownUntilSeeElement(1000, byAllMarkets);
	}

	public void scrollToVoteBanner() {
		By byVoteBanner = By.xpath("//div[contains(@class,'pqmllm-0')]");
		scrollDownUntilSeeElement(1000, byVoteBanner);
	}

	// Must scroll to show Vote banne before click Rate GOOD
	public void clickRateGood() {
		By byVoteGood = By.xpath("(//button[contains(@class,'iv7acg-0')])[1]");
		click(byVoteGood);
//		sleep(2);
	}

	public void clickPancakeSwap_Pair() {
		By byPairMarket = By.xpath("//a[contains(@class,'qh9zi5')]");
		click(byPairMarket);
//		sleep(3);
	}

	public void scrollBotAndTopPage() {
		scrollDownToEndPage();
		sleep(3);
		scrollUpToTopPage();
		sleep(3);
	}

	public void printRunningCaseTest(int numb, String coin) {
		switch (numb) {
		case 1:
			Ztest.log.printToLogFileAndConsole("### Running: Open " + coin.toUpperCase() + " page from Coinmarket Cap");
			break;
		case 2:
			Ztest.log.printToLogFileAndConsole("### Running: Open " + coin.toUpperCase() + " page from Google Search");
			break;
		case 3:
			Ztest.log.printToLogFileAndConsole("### Running: Open " + "Coinmarketcap".toUpperCase() + " page from Token Fan8");
			break;
		default:
			break;
		}
	}

	public void selectCaseTest(int numb, String coin) {
		switch (numb) {
		case 0:
//			case0_OpenCoinPage_FromCMC_AndGoogle(coin);
			break;
		case 1:
			case1_OpenCoinPage_FromSearchCMC(coin);
			break;
		case 2:
			case2_OpenCoinPage_FromGoogle(coin);
			break;
		case 3:
			case3_OpenCMCPage_FromTokenFan8(coin);

		default:
			break;
		}
	}

	public void case3_OpenCMCPage_FromTokenFan8(String coin) {
		driver.get(webTokenFan8);
//		sleep(3);

//		scrollTo_OnCMClink();
		scrollTo_Fan8PlatformTxt();
		clickOnCMC_Link();

		switchWindow(2);
		sleep(3);

		closeCMCPolicyBanner();
		clickMarket();
		scrollDown_ByPixel(400);

		// ko dc scroll to end vi load nhiu data
//		scrollDownToEndPage();		
//		scrollTo_About();		
//		sleep(2);		
//		clickAbout_OnCMC();

		sleep(3);
		if(driver.getCurrentUrl().contains("currencies/fan8/markets")) {
			Ztest.log.printToLogFileAndConsole("Verified link:\t" + driver.getCurrentUrl());
			countTotalSuccess ++;
			Ztest.log.printToLogFileAndConsole("Count Success:\t" + countTotalSuccess);
		}else {
			Ztest.log.printToLogFileAndConsole("URL is invalid. This page is not https://coinmarketcap.com/currencies/fan8/markets/");
		}
		
	}

	public void case1_2_OpenFan8_FromCMC_AndFromGG(int numb, String coin) {
		selectCaseTest(numb, coin);

		// must close to avoid can't click buttons under it
		closeCMCPolicyBanner();

		// Only overview can click Rate Good
		clickOverview();
		sleep(3);

//		scrollBotAndTopPage();

		clickTokenFan8Page();
		Ztest.log.printToLogFileAndConsole("TokenFan8 web:\t");
//		sleepRandom(list_RandomSecond_StopAtTokenFan8.get(0), list_RandomSecond_StopAtTokenFan8.get(1));

		switchWindow(1);

		scrollToVoteBanner();

		clickRateGood();
		sleep(2);

		scrollToViewAllMarkets();
		sleep(2);

		clickPancakeSwap_Pair();
		sleep(5);
		switchWindow(1);
		sleep(2);

//		clickDextools_Pair();
//		sleep(5);
//		switchWindow(1);
//		sleep(2);

		int timeInSecond = 0;
		// check url fan8 CMC appears
		if (coin.equalsIgnoreCase("fan8")) {

			// verify Fan8 link when wait < 10s
			do {
				Ztest.log.printToLogFileAndConsole("Verified link:\t" + driver.getCurrentUrl());
				sleep(1);
				timeInSecond++;
			} while (!driver.getCurrentUrl().contains("/currencies/fan8") && timeInSecond < 10);

			// Count success if wait < 10s
			if (timeInSecond < 10) {
				countTotalSuccess += 1;
				Ztest.log.printToLogFileAndConsole("Count Success:\t" + countTotalSuccess);

			}
		} else {
			countTotalSuccess += 1;
			Ztest.log.printToLogFileAndConsole("Count Success:\t" + countTotalSuccess);

		}
	}

	public void case2_OpenCoinPage_FromGoogle(String coin) {
		driver.get(webGoogleWithSearch + coin + " " + keyworld_ToSearchInWeb);

		// click ReCaptcha
		clickReCaptchaCheckbox_IfExist();

		// close Cookie popup
		clickCookiePopup_IfExist();

		// for both mini + maxi web
//		By by1stSearchResult = By.xpath("(//*[@class='LC20lb DKV0Md'])[1]");
		
		// for Noimage
		By by1stSearchResult = By.xpath("(//*[@class= 'zBAuLc' or @class='LC20lb DKV0Md'])[1]");
		click(by1stSearchResult);
		Ztest.log.printToLogFileAndConsole("CoinmarketCap:\t");
//		sleepRandom(list_RandomSecond_StopAtCMC.get(0), list_RandomSecond_StopAtCMC.get(1));

		// will wait later
//		By symbolFan8 = By.xpath("//*[@class = 'nameSymbol___1arQV']");
	}

	public void case1_OpenCoinPage_FromSearchCMC(String coin) {
		driver.get(webCMC);
		Ztest.log.printToLogFileAndConsole("CoinmarketCap:\t");
//		sleepRandom(list_RandomSecond_StopAtCMC.get(0), list_RandomSecond_StopAtCMC.get(1));

		// for maxi web
//		By search = By.xpath("//div[contains(@class,'sc-266vnq-1')]");
//		By inputFan8 = By.xpath("//input[contains(@class,'bzyaeu-3')]");

		// for not mini web
		By search = By.xpath("(//*[@class='sc-16r8icm-0 fNFbRb'])[1]");
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
////		Ztest.log.printToLogFileAndConsole("The new IP is " + countryCode);
//		if (!countryCode.equalsIgnoreCase("VN")) {
//			Ztest.log.printToLogFileAndConsole("Change proxy success:");
//			Ztest.log.printToLogFileAndConsole("\tThe new IP: " + proxyhttp);
//			System.out.print("\tCountry Code: " + countryCode);
//		}
//
//	}

	// using 1
	public void printListProxy(List<String> list) {
		Ztest.log.printToLogFileAndConsole("List Proxy");
//		Ztest.log.printToLogFileAndConsole("List Proxy");
		int countProxy = 0;
		for (String proxy : list) {
			countProxy += 1;
			Ztest.log.printToLogFileAndConsole("Proxy " + countProxy + ":\t" + proxy);
//			Ztest.log.printToLogFileAndConsole("Proxy " + countProxy + ":\t" + proxy);
		}
	}

	// using 1
	// Make sure no restarting in List
	public List<String> getListProxy_FromListURL(List<String> listURL) {
		List<String> listLocal = new ArrayList<>();
		try {
			for (int i = 0; i < listURL.size(); i++) {
				URL url = new URL(listURL.get(i));
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				InputStream responseStream = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
				String line;
				while ((line = rd.readLine()) != null) {
					if (!line.equalsIgnoreCase("restarting")) {
						listLocal.add(line);
					} else {
						break;
					}
				}
//				listURL_Total.add(listLocal);
				connection.disconnect();
			}
//			connection.disconnect();
		} catch (Exception e) {
			Ztest.log.printToLogFileAndConsole("Unable to get list Proxy now, maybe servers died.");
		}
		return listLocal;
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

	// using 1
	public List<String> getListProxy_FromURL(String URL) {
		List<String> listLocal = new ArrayList<>();
		try {

			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream responseStream = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
			String line;
			while ((line = rd.readLine()) != null) {
				if (!line.equalsIgnoreCase("restarting")) {
					listLocal.add(line);
				} else {
					break;
				}
			}
//				listURL_Total.add(listLocal);
			connection.disconnect();

//			connection.disconnect();
		} catch (Exception e) {
			Ztest.log.printToLogFileAndConsole("Unable to get list Proxy now, maybe servers died.");
		}
		return listLocal;
	}

	// using 1
	public List<String> getListProxy_FromFile() {
		List<String> listLocal = new ArrayList<>();
		String path_FileProxy = "DataTest/httpproxies.txt";
		File file1 = new File(path_FileProxy);
		
		try {
			InputStream in = new FileInputStream(file1);
			BOMInputStream bomInputStream = new BOMInputStream(in, false);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bomInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				listLocal.add(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Ztest.log.printToLogFileAndConsole("Unable to get list Proxy now, maybe servers died.");
		}
		
//		Ztest.log.printToLogFileAndConsole("Success add Proxy");
		return listLocal;
	}

	// using 1
	public void initChromeWithProxyHTTP(String httpProxy) {
//		Ztest.log.printToLogFileAndConsole();
//		System.setProperty("webdriver.chrome.driver", path_DriverChrome_Window);

		// hide log of chromedriver and java selenium
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		ChromeOptions chromeOptions = new ChromeOptions();

		// run without browser
//		chromeOptions.addArguments("headless");

		// make no display : Chrome ia automated... .
//		chromeOptions.addArguments("disable-infobars");

		// set size browser
		chromeOptions.addArguments("window-size=800,1000");

		chromeOptions.addArguments("user-agent=" + getRandomString());
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		chromeOptions.setExperimentalOption("useAutomationExtension", false);

		// a little weaker than
		// System.setProperty("webdriver.chrome.silentOutput","true");
//		chromeOptions.setLogLevel(ChromeDriverLogLevel.OFF);

		Map<String, Object> prefs = new HashMap<String, Object>();

		// make web not load image
		prefs.put("profile.managed_default_content_settings.images", 2);

		// maybe this is for mobile appium
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

		
		if (Platform.getCurrent().is(Platform.WINDOWS)) {
//			driverPath = "Driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path_DriverChrome_Window);
			driver = new ChromeDriver(chromeOptions);
			Ztest.log.printToLogFileAndConsole("Platform:\t" + Platform.getCurrent());
//			String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent");
//			Ztest.log.printToLogFileAndConsole("UserAgent:\t" + userAgent);
		} else {
//	    	driverPath = "Driver/chromedriver-linux";
	    	System.setProperty("webdriver.chrome.driver", path_DriverChrome_Linux);
	    	driver = new ChromeDriver(chromeOptions);
	    	Ztest.log.printToLogFileAndConsole("Platform:\t" + Platform.getCurrent());
//	    	String userAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent");
//			Ztest.log.printToLogFileAndConsole("UserAgent:\t" + userAgent);
		}
		
//		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

//		driver.manage().window().minimize();
//		Ztest.log.printToLogFileAndConsole("\rPre-Test: CHROME Driver Start Success");
	}

	// ssl proxy : not using
	public void initChromeWithProxyHTTPS(String httpsproxy) {
		System.setProperty("webdriver.chrome.driver", path_DriverChrome_Window);
		System.setProperty("webdriver.chrome.silentOutput", "false");
		ChromeOptions chromeOptions = new ChromeOptions();
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setSslProxy(httpsproxy);
		chromeOptions.setCapability("proxy", proxy);
		driver = new ChromeDriver(chromeOptions);
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
//		driver.manage().window().fullscreen();
		Ztest.log.printToLogFileAndConsole("Pre-Test: CHROME Driver Start Success");
	}

	public void initChromeNoProxy() {
		System.setProperty("webdriver.chrome.driver", path_DriverChrome_Window);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		ChromeOptions chromeOptions = new ChromeOptions();
		
		chromeOptions.addArguments("window-size=800,1000");

		chromeOptions.addArguments("user-agent=" + getRandomString());
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		Map<String, Object> prefs = new HashMap<String, Object>();

		// maybe this is for mobile appium
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2); // disable browser noti
		chromeOptions.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		Ztest.log.printToLogFileAndConsole("Pre-Test: CHROME Driver Started Success");
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

	public void scrollDownUntilSeeElement(int positive, By byElementToFind) {
		for (int i = 0; i < 10; i++) {
			try {
				scrollDown_ByPixel(positive);
				sleep(2);
				if (driver.findElement(byElementToFind).isDisplayed()) {
//					Ztest.log.printToLogFileAndConsole("Đã scroll và thấy element");
					break;
				}
			} catch (Exception e) {

			}
		}
	}

	// This will scroll the web page till end.
	public void scrollUpToTopPage() {
//    	String jsScript = "window.scrollTo(0, -document.body.scrollHeight)";
		String jsScript = "window.scrollTo(0, document.body.scrollTop)";
		javaScriptRunner(jsScript);
	}

	// This will scroll the web page till end.
	public void scrollDownToEndPage() {
		String jsScript = "window.scrollTo(0, document.body.scrollHeight)";
		javaScriptRunner(jsScript);

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
		javaScriptRunner("window.scrollBy(" + negative + ",0)");
	}

	// horizontal scroll left
	public void scrollLeft_ByPixel(int positive) {
		javaScriptRunner("window.scrollBy(" + positive + ",0)");
	}

	// vertical scroll up
	public void scrollUp_ByPixel(int negative) {
		javaScriptRunner("window.scrollBy(0," + negative + ")");
	}

	// vertical scroll down
	public void scrollDown_ByPixel(int positive) {
		javaScriptRunner("window.scrollBy(0," + positive + ")");

//    	executeByJavaScript("window.scrollBy(0,250)");
//    	executeByJavaScript("scroll(0, 250);");
//    	Robot robot = new Robot();
//    	robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//    	robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	public void javaScriptRunner(String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
	}

	public String javaScriptRunner_ToString(String javaScript) {
		String s;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		s = js.executeScript(javaScript).toString();
		return s;
	}

	public void input(By by, String text) {
		getWebElement(by).sendKeys(text);
	}

	public void click(By by) {
		getWebElement(by).click();	
//		Ztest.log.printToLogFileAndConsole("Đã thấy element và Click xong");
	}

	public WebElement getWebElement(By by) {
		return driver.findElement(by);
	}

	public int sleepRandom(int begin_inSecond, int end_inSecond) {
		int x = RandomUtils.nextInt(begin_inSecond, end_inSecond + 1);
		Ztest.log.printToLogFileAndConsole("Waiting " + x + "s...");
		sleep(x);
		return x;
	}

	public String getRandomString() {
		String x = RandomStringUtils.randomAlphanumeric(10);
		return x;
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

	public void runCmdComand(String command) {
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// for run any Testcase
	@Test
	public void forRunTest() {
//		test a = new test();
		testLoginDefiatoTesting();
//		testOpenFan8_FromMultiProxy(2, a.coin_Default, a.getListUrl_Default(a.url1_PhuongCao, a.url2_PhuongCao));
	}

}
