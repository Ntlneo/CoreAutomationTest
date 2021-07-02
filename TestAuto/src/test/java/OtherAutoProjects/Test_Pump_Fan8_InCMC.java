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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.chrome.ChromeOptions;

public class Test_Pump_Fan8_InCMC {
	WebDriver driver;
	String path_DriverChromeForWeb = "Drivers/WebChromeDriver/chromedriver.exe";
	String proxyhttp = "http://191.101.39.131";
	String port = "80";
	String urlPubProxyToGetProxy = "http://pubproxy.com/api/proxy?limit=10&last_check=1&format=txt&type=http&https=false";

	String url1_PhuongCao = "https://teamcity.quickom.com/proxy1.txt";
	String url2_PhuongCao = "https://teamcity.quickom.com/proxy2.txt";
	String url3_PhuongCao = "https://teamcity.quickom.com/proxy3.txt";
	String url4_PhuongCao = "https://teamcity.quickom.com/proxy4.txt";
	String url5_PhuongCao = "https://teamcity.quickom.com/proxy5.txt";
	List<String> listURL_PhuongCao = new ArrayList<>(
			Arrays.asList(url1_PhuongCao, url2_PhuongCao, url3_PhuongCao, url4_PhuongCao, url5_PhuongCao));

	String webCheckIP = "http://ifconfig.io/";
	String webGetProxy = "https://free-proxy-list.net";

	// Fan8 from CMC
	String webCMC = "https://coinmarketcap.com/";
	String webCMC_fan8 = "https://coinmarketcap.com/currencies/fan8/";

	// Fan8 from Google
	String webGoogle = "https://www.google.com/";
	String webGoogleWithSearch = "https://www.google.com/search?q=";
	String searchKey_Fan8CMC = "fan8 coinmarketcap";

	String webDefiatoTesting = "https://testing.defiato.com/";
	String email = "defitest1@mailinator.com";
	String pass = "12345678";

	String coinSearch = "Fan8";

	String path_FileProxy = "DataTest/httpproxies.txt";
	List<String> listProxy_Global = new ArrayList<>();

	// ------------RUNTEST------------------------------------------------------------------------

	public void testOpenFan8_FromGoogle_Multi() throws InterruptedException {
		System.out.println("### Running: Open Fan8 page from Google Search");
		try {
			listProxy_Global = getListProxy_FromListURL();
			printListProxy(listProxy_Global);

			if (!isRestarting()) {
				int countSuccess = 0;
				for (String proxy : listProxy_Global) {
					int countProxy = countSuccess + 1;
					initChromeWithProxyHTTP(proxy);
					System.out.println("Using proxy " + countProxy + ":\t" + proxy);

					try {
						openFan8_FromGoogle();
						scrollBotAndTopPage();
						scrollToVolumeFan8();
						// check url fan8 CMC appears
						int number = 0;
						do {
							System.out.println(driver.getCurrentUrl());
							Thread.sleep(5000);
							number++;

						} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);

						// Count if wait < 60s
						if (number < 12) {
							countSuccess += 1;
							System.out.println("Count Success " + countSuccess + ":\t" + proxy);
							driver.quit();
						}

					} catch (Exception e) {
						driver.quit();
						if (isRestarting_InURL(url1_PhuongCao)) {
							Thread.sleep(30000);
							testOpenFan8_FromGoogle_Multi();
						} else if (isRestarting_InURL(url2_PhuongCao)) {
							Thread.sleep(30000);
							testOpenFan8_FromGoogle_Multi();
						}
						// testOpenFan8CMC_FromSearchCMC();
					}

				}
			} else {
				Thread.sleep(30000);
				testOpenFan8_FromGoogle_Multi();

			}
		} catch (Exception e) {
			Thread.sleep(30000);
			testOpenFan8_FromGoogle_Multi();
		}
	}
	
	public void testOpenFan8_FromSearchCMC_Multi() throws InterruptedException {
		System.out.println("### Running: Open Fan8 page from Coinmarket Cap");
		try {
			listProxy_Global = getListProxy_FromListURL();
			printListProxy(listProxy_Global);

			if (!isRestarting()) {
				int countSuccess = 0;
				for (String proxy : listProxy_Global) {
					int countProxy = countSuccess + 1;
					initChromeWithProxyHTTP(proxy);
					System.out.println("Using proxy " + countProxy + ":\t" + proxy);

					try {
						openFan8_FromSearchCMC();
						scrollBotAndTopPage();
						scrollToVolumeFan8();
						// check url fan8 CMC appears
						int number = 0;
						do {
							System.out.println(driver.getCurrentUrl());
							Thread.sleep(5000);
							number++;

						} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);

						// Count if wait < 60s
						if (number < 12) {
							countSuccess += 1;
							System.out.println("Count Success " + countSuccess + ":\t" + proxy);
							driver.quit();
						}

					} catch (Exception e) {
						driver.quit();
						if (isRestarting_InURL(url1_PhuongCao)) {
							Thread.sleep(30000);
							testOpenFan8_FromSearchCMC_Multi();
						} else if (isRestarting_InURL(url2_PhuongCao)) {
							Thread.sleep(30000);
							testOpenFan8_FromSearchCMC_Multi();
						}
						// testOpenFan8CMC_FromSearchCMC();
					}

				}
			} else {
				Thread.sleep(30000);
				testOpenFan8_FromSearchCMC_Multi();

			}
		} catch (Exception e) {
			Thread.sleep(30000);
			testOpenFan8_FromSearchCMC_Multi();
		}
	}

	public void testOpenFan8_FromGoogle() throws InterruptedException {
		System.out.println("### Running: Open Fan8 page from Google Search");
		try {
			listProxy_Global = getListProxy_FromURL();
			printListProxy(listProxy_Global);

			if (!isRestarting()) {
				int countSuccess = 0;
				for (String proxy : listProxy_Global) {
					int countProxy = countSuccess + 1;
					initChromeWithProxyHTTP(proxy);
					System.out.println("Using proxy " + countProxy + ":\t" + proxy);

					try {
						openFan8_FromGoogle();
						scrollBotAndTopPage();
						scrollToVolumeFan8();
						// check url fan8 CMC appears
						int number = 0;
						do {
							System.out.println(driver.getCurrentUrl());
							Thread.sleep(5000);
							number++;

						} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);

						// Count if wait < 60s
						if (number < 12) {
							countSuccess += 1;
							System.out.println("Count Success " + countSuccess + ":\t" + proxy);
							driver.quit();
						}

					} catch (Exception e) {
						driver.quit();
						if (isRestarting()) {
							Thread.sleep(30000);
							testOpenFan8_FromGoogle();
						}
						// testOpenFan8CMC_FromSearchCMC();
					}

				}
			} else {
				Thread.sleep(30000);
				testOpenFan8_FromGoogle();

			}
		} catch (Exception e) {
			Thread.sleep(30000);
			testOpenFan8_FromGoogle();
		}
	}

	@Test
	public void testOpenFan8_FromSearchCMC() throws InterruptedException {
		System.out.println("### Running: Open Fan8 page from Coinmarket Cap");
		try {
			listProxy_Global = getListProxy_FromURL();
			printListProxy(listProxy_Global);

			if (!isRestarting()) {
				int countSuccess = 0;
				for (String proxy : listProxy_Global) {
					int countProxy = countSuccess + 1;
					initChromeWithProxyHTTP(proxy);
					System.out.println("Using proxy " + countProxy + ":\t" + proxy);

					try {
						openFan8_FromSearchCMC();
						scrollBotAndTopPage();
						scrollToVolumeFan8();
						// check url fan8 CMC appears
						int number = 0;
						do {
							System.out.println(driver.getCurrentUrl());
							Thread.sleep(5000);
							number++;

						} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);

						// Count if wait < 60s
						if (number < 12) {
							countSuccess += 1;
							System.out.println("Count Success " + countSuccess + ":\t" + proxy);
							driver.quit();
						}

					} catch (Exception e) {
						driver.quit();
						if (isRestarting()) {
							Thread.sleep(30000);
							testOpenFan8_FromSearchCMC();
						}
						// testOpenFan8CMC_FromSearchCMC();
					}

				}
			} else {
				Thread.sleep(30000);
				testOpenFan8_FromSearchCMC();

			}
		} catch (Exception e) {
			Thread.sleep(30000);
			testOpenFan8_FromSearchCMC();
		}
	}

	public boolean isRestarting_InURL(String link) throws Exception {
//		List<String> listLocal = new ArrayList<>();
//		listLocal = getListProxy_FromURL();

		URL url = new URL(link);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream responseStream = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
		String line;
		while ((line = rd.readLine()) != null) {
			if (line.equalsIgnoreCase("restarting")) {
				break;
			} else {
				return false;
			}
		}
		return true;

	}

	public boolean isRestarting() {
		List<String> listLocal = new ArrayList<>();
		listLocal = getListProxy_FromURL();
		if (listProxy_Global != null && listProxy_Global.size() > 0
				&& listProxy_Global.get(0).equalsIgnoreCase("restarting")) {
			return true;
		} else {
			return false;
		}
	}

	public void testOpenCMC() throws IOException, InterruptedException {
		getListProxy_FromURL();

		int count = 0;

		for (String proxy : listProxy_Global) {
			initChromeWithProxyHTTP(proxy);
			System.out.println("Using proxy: " + proxy);
			// String webIP = "https://whatismyipaddress.com/";
			// driver.get(webIP);
			// Thread.sleep(5000);

			try {

				driver.get(webCMC);

//				By search = By.xpath("//div[@class='sc-16r8icm-0 fNFbRb']");
				By search = By.xpath("(//div[@class='sc-1xvlii-0 dQjfsE']/*[@class='sc-16r8icm-0 fNFbRb'])[1]");

				click(search);
//				System.out.println("Da94 click search");
				By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");
//				input(inputFan8, coinSearch);
				driver.findElement(inputFan8).sendKeys(coinSearch, Keys.RETURN);

				int number = 0;
				do {
					System.out.println(driver.getCurrentUrl());
					Thread.sleep(5000);
					number++;

				} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);

				if (number < 12) {
					count += 1;
					System.out.println("Count " + count + " : " + proxy);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			driver.close();
		}
	}

	public void testLoginDefiatoTesting() throws IOException, InterruptedException {
		getListProxy_FromFile();
		int count = 0;
		for (String proxy : listProxy_Global) {
			initChromeWithProxyHTTP(proxy);
			// String webIP = "https://whatismyipaddress.com/";
			// driver.get(webIP);
			// Thread.sleep(5000);

			try {
				driver.get(webDefiatoTesting);
				By bySignIn = By.xpath("(//a[@href='/signin'])[2]");
				click(bySignIn);

				By byEmail = By.xpath("//input[@name='email']");
				By byPass = By.xpath("//input[@name='password']");
				By bySignInBtn = By.xpath("//button[@type='submit']");
				input(byEmail, email);
				input(byPass, pass);
				click(bySignInBtn);
				int number = 0;
				do {
					System.out.println(driver.getCurrentUrl());
					Thread.sleep(5000);
					number++;

				} while (!driver.getCurrentUrl().contains("/account/details") && number < 12);

//				click(search);
//				System.out.println("Da94 click search");
//				By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");
//				input(inputFan8, coinSearch);
//				driver.findElement(inputFan8).sendKeys(coinSearch,Keys.RETURN);

				// count if success
				if (number < 12) {
					count += 1;
					System.out.println("Count " + count + " : " + proxy);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			driver.close();
		}
	}

	// ------------------------------------------------------------------
	public void scrollBotAndTopPage() throws InterruptedException {
		scrollDownToEndPage();
		Thread.sleep(3000);
		scrollUpToTopPage();
	}

	public void scrollToVolumeFan8() throws InterruptedException {
		By iconCoinVolume = By.xpath("//img[@alt='converter-coin-logo']");
		scrollToElementVisible(iconCoinVolume);
	}

	public void openFan8_FromGoogle() throws InterruptedException {
//		initChromeNoProxy();		
		driver.get(webGoogleWithSearch + searchKey_Fan8CMC);
		Thread.sleep(5000);
		By by1stSearchResult = By.xpath("(//*[@class='LC20lb DKV0Md'])[1]");
		click(by1stSearchResult);
		// *[@class='LC20lb DKV0Md']

		// will wait later
//		By symbolFan8 = By.xpath("//*[@class = 'nameSymbol___1arQV']");	
		Thread.sleep(5000);

	}

	public void openFan8_FromSearchCMC() throws InterruptedException {

		driver.get(webCMC);
		By search = By.xpath("//div[contains(@class,'sc-266vnq-1')]");
		By inputFan8 = By.xpath("//input[contains(@class,'bzyaeu-3')]");
		// for not maximize web
//		By search = By.xpath("(//div[@class='sc-1xvlii-0 dQjfsE']/*[@class='sc-16r8icm-0 fNFbRb'])[1]");
//		By inputFan8 = By.xpath("//input[@class='bzyaeu-3 exjgFJ']");

		click(search);
		driver.findElement(inputFan8).sendKeys(coinSearch, Keys.RETURN);
		Thread.sleep(5000);
	}

	// not using
	public void testChangeProxy() throws IOException {
		getListProxy_FromFile();
		initChromeWithProxyHTTPS(listProxy_Global.get(1));
		By valueCountryCode = By.xpath("//li[3]/div[2]");
		String webIP = "http://ifconfig.io/";
		driver.get(webIP);
		;
		String countryCode = driver.findElement(valueCountryCode).getText();
//		System.out.println("The new IP is " + countryCode);
		if (!countryCode.equalsIgnoreCase("VN")) {
			System.out.println("Change proxy success:");
			System.out.println("\tThe new IP: " + proxyhttp);
			System.out.print("\tCountry Code: " + countryCode);
		}

	}

	public void printListProxy(List<String> list) {
		System.out.println("List Proxy");
		int countProxy = 0;
		for (String proxy : list) {
			countProxy += 1;
			System.out.println("Proxy " + countProxy + ":\t" + proxy);
		}
	}

//	public String getTextResultFromURL(String url) {
//		URL url = new URL(link);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		InputStream responseStream = connection.getInputStream();
//		BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
//		String line;
//		while ((line = rd.readLine()) != null) {
//			return line;
//		}
//	}

	public List<String> getListProxy_FromListURL() throws Exception {
		List<String> listLocal = new ArrayList<>();
//		List<String> listURL_ToGetProxy = new ArrayList<>();
		try {

			listLocal.clear();
			for (String link : listURL_PhuongCao) {
				URL url = new URL(link);
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
			}
//			connection.disconnect();
		} catch (Exception e) {

		}
		return listLocal;
	}

	// using 1
	public List<String> getListProxy_FromURL() {
		List<String> listLocal = new ArrayList<>();
		try {
			listLocal.clear();
//			URL url = new URL(urlPubProxyToGetProxy);
			URL url = new URL(url1_PhuongCao);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream responseStream = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
			String line;
			while ((line = rd.readLine()) != null) {
				listLocal.add(line);
//				System.out.println("List Proxy: " + line);

			}
			connection.disconnect();

		} catch (IOException e) {
//			e.printStackTrace();
		}

		return listLocal;

	}

	// using 1
	public void getListProxy_FromFile() throws IOException {
		File file1 = new File(path_FileProxy);
		InputStream in = new FileInputStream(file1);
		BOMInputStream bomInputStream = new BOMInputStream(in, false);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bomInputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			listProxy_Global.add(line);
		}
		System.out.println("Success add Proxy");

	}

	// using 2
	public void initChromeWithProxyHTTP(String httpProxy) {
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "enable-logging" });
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
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

		chromeOptions.addArguments("start-maximized");

		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.manage().deleteAllCookies();
		
//		driver.manage().window().maximize();
//		driver.manage().window().fullscreen();
		System.out.println("Pre-Test: CHROME Driver Start Success\r");
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
		driver.manage().window().maximize();
		System.out.println("Pre-Test: CHROME Driver Start Success\r");
	}

	// ------------HANDLE_ACTIONS------------------------------------------------------------------------

	// The syntax of ScrollBy() methods is :
	// executeScript("window.scrollBy(x-pixels,y-pixels)");
	// x-pixels : horizontal, left if number is positive, right if number is
	// negative
	// y-pixels : vertical, down if number is positive, up if number is in negative
	// -y
	// x 0 -x
	// y

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
	public void scrollToElementVisible(By by) {
		WebElement webElement = getWebElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", webElement);
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
	public void scrollDownPixel(int positive) {
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
}
