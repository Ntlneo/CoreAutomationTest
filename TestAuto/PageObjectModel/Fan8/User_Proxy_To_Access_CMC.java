package Fan8;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;
import kotlin.jvm.Synchronized;



public class User_Proxy_To_Access_CMC extends BasePage{
	public User_Proxy_To_Access_CMC(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	// *********************** LOCATORS ***********************
//	private By logoBeowulf_Img = By.xpath("//img[contains(@src,'beowulf-logo')]");
//	private By walletMenu_Btn = By.xpath("(//*[@class='nav-item'])[1]");
//	private By servicesMenu_Btn = By.xpath("(//*[@class='nav-item'])[2]");
//	private By settingsMenu_Btn = By.xpath("(//*[@class='nav-item'])[3]");
//
//	private By createWallet_Btn = By.xpath("//*[@class='nav-item text-danger']");
//	private By server_Txt = By.xpath("//*[@class='text-capitalize']");
//	private By logout_Btn = By.xpath("//*[@class='navbar-nav align-items-md-center']");
	
	By valueCountryCode = By.xpath("//li[3]/div[2]");
	
	// *********************** ACTIONS ***********************
	
	public void checkIPWhenUsingProxy() {
		String webIP = "http://ifconfig.io/";
		openURL(webIP);
		String countryCode = getElement(valueCountryCode).getText();
		if(!countryCode.equalsIgnoreCase("VN")) {
			System.out.println("Change proxy success. The new IP is " + countryCode);
		}
	}


	

}
