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
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
	String urlPhuongCao = "http://34.80.20.3:3128/";
	
	
	String webCheckIP = "http://ifconfig.io/";
	String webGetProxy = "https://free-proxy-list.net";
	
	//Fan8 from CMC
	String webCMC = "https://coinmarketcap.com/";
	String webCMC_fan8 = "https://coinmarketcap.com/currencies/fan8/";
	
	//Fan8 from Google
	String webGoogle = "https://www.google.com/";
	String webGoogleWithSearch = "https://www.google.com/search?q=";
	String searchKey_Fan8CMC = "fan8 coinmarketcap";
	

	String webDefiatoTesting = "https://testing.defiato.com/";
	String email = "defitest1@mailinator.com";
	String pass = "12345678";

	String coinSearch = "Fan8";

	String path_FileProxy = "DataTest/httpproxies.txt";
	List<String> listProxy = new ArrayList<>();

	// ------------RUNTEST------------------------------------------------------------------------

	
	
	public void testOpenFan8CMC_Direct() throws IOException, InterruptedException {
//		getListProxyFromURL();
		getListProxyFromFile();
		int count = 0;

		for (String proxy : listProxy) {
			initChromeWithProxyHTTP(proxy);
			System.out.println("Using proxy: "+proxy);
			// String webIP = "https://whatismyipaddress.com/";
			// driver.get(webIP);
			// Thread.sleep(5000);
			
			try {
				
				driver.get(webCMC_fan8);

				int number = 0;
				do {
					System.out.println(driver.getCurrentUrl());
					Thread.sleep(5000);
					number++;

				} while (!driver.getCurrentUrl().contains("/currencies/fan8") && number < 12);
				
				if (number < 12) {
					count += 1;
					System.out.println("Count Success" + count + " : " + proxy);
				}

			}catch (Exception e) {
				// TODO: handle exception
			}
			driver.close();
		}
	}
	
	public void testOpenCMC() throws IOException, InterruptedException {
		getListProxyFromURL();
		
		int count = 0;

		for (String proxy : listProxy) {
			initChromeWithProxyHTTP(proxy);
			System.out.println("Using proxy: "+proxy);
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

			}catch (Exception e) {
				// TODO: handle exception
			}
			driver.close();
		}
	}

	
	public void testLoginDefiatoTesting() throws IOException, InterruptedException {
		getListProxyFromFile();
		int count = 0;
		for (String proxy : listProxy) {
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
			}catch (Exception e) {
				// TODO: handle exception
			}
			

			driver.close();
		}
	}
	
	
	
	//------------------------------------------------------------------
	
	@Test
	public void openFan8FromGoogle() {
		initChromeNoProxy();		
		driver.get(webGoogleWithSearch + searchKey_Fan8CMC);
		
		By by1stSearchResult = By.xpath("(//*[@class='LC20lb DKV0Md'])[1]");
		click(by1stSearchResult);
		//*[@class='LC20lb DKV0Md']		
		
		By symbolFan8 = By.xpath("//*[@class = 'nameSymbol___1arQV']");
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testChangeProxy() throws IOException {
		getListProxyFromFile();
		initChromeWithProxyHTTPS(listProxy.get(1));
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
	
	public void getListProxyFromURL() {
		try {
			listProxy.clear();
//			URL url = new URL(urlPubProxyToGetProxy);
			URL url = new URL(urlPhuongCao);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream responseStream = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
			String line;
			while ((line = rd.readLine()) != null) {
				listProxy.add(line);			
//				System.out.println("List Proxy: " + line);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String string : listProxy) {
			System.out.println("List Proxy: " + string);
		}
		
	}
	
	// using 1
	public void getListProxyFromFile() throws IOException {
		File file1 = new File(path_FileProxy);
		InputStream in = new FileInputStream(file1);
		BOMInputStream bomInputStream = new BOMInputStream(in, false);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bomInputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			listProxy.add(line);
		}
		System.out.println("Success add Proxy");

	}

	// using 2
	public void initChromeWithProxyHTTP(String httpProxy) {
		System.setProperty("webdriver.chrome.driver", path_DriverChromeForWeb);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions chromeOptions = new ChromeOptions();
//		Proxy proxy = new Proxy();
//		proxy.setAutodetect(false);
//		proxy.setHttpProxy(httpProxy);
//		chromeOptions.setCapability("proxy", proxy);

		chromeOptions.addArguments("--proxy-server=" + httpProxy);

		// will make CMC bug
//		chromeOptions.addArguments("start-maximized");

		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().fullscreen();
		System.out.println("Pre-Test: CHROME Driver Start Success\r");
	}

	// ssl proxy
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

	//The syntax of ScrollBy() methods is :
	//executeScript("window.scrollBy(x-pixels,y-pixels)");
	//x-pixels : horizontal, left if number is positive, right if number is negative
	//y-pixels : vertical, down if number is positive, up if number is in negative
    	
	


	// This will scroll the web page till end.
    public void scrollUpToTopPage() throws AWTException {    	
//    	String jsScript = "window.scrollTo(0, -document.body.scrollHeight)";
    	String jsScript = "window.scrollTo(0, document.body.scrollTop)"; 
    	executeByJavaScript(jsScript);
    }
	
	// This will scroll the web page till end.
    public void scrollDownToEndPage() throws AWTException {    	
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
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView();",webElement );
    }
	
	
	// horizontal scroll right
    public void scrollRight_ByPixel(int negative) {    	
    	executeByJavaScript("window.scrollBy(" + negative + ",0)" );
    }
    
	// horizontal scroll left
    public void scrollLeft_ByPixel(int positive) {    	
    	executeByJavaScript("window.scrollBy(" + positive + ",0)" );
    }
    
	// vertical scroll up 
    public void scrollUp_ByPixel(int negative) {    	
    	executeByJavaScript("window.scrollBy(0," + negative + ")" );
    }
    
	// vertical scroll down 
    public void scrollDownPixel(int positive) {
    	executeByJavaScript("window.scrollBy(0," + positive + ")" );
    	
//    	executeByJavaScript("window.scrollBy(0,250)");
//    	executeByJavaScript("scroll(0, 250);");
//    	Robot robot = new Robot();
//    	robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//    	robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }
	
    public void executeByJavaScript(String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor)driver;  
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
