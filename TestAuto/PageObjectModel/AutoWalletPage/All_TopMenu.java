package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;
import kotlin.jvm.Synchronized;



public class All_TopMenu extends BasePage{
	public All_TopMenu(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	// *********************** LOCATORS ***********************
	private By logoBeowulf_Img = By.xpath("//img[contains(@src,'beowulf-logo')]");
	private By walletMenu_Btn = By.xpath("(//*[@class='nav-item'])[1]");
	private By servicesMenu_Btn = By.xpath("(//*[@class='nav-item'])[2]");
	private By settingsMenu_Btn = By.xpath("(//*[@class='nav-item'])[3]");

	private By createWallet_Btn = By.xpath("//*[@class='nav-item text-danger']");
	private By server_Txt = By.xpath("//*[@class='text-capitalize']");
	private By logout_Btn = By.xpath("//*[@class='navbar-nav align-items-md-center']");
	
	
	
	// *********************** ACTIONS ***********************
	
	public void clickLogoutButton() {
		click(logout_Btn);
	}
	
	public void clickCreateWalletButton() {
		click(createWallet_Btn);
	}
	
	public void clickSettingsButton() {
		click(settingsMenu_Btn);
	}
	
	public void clickServicesButton() {
		click(servicesMenu_Btn);
	}
	
	public void clickLogoBeowulf() {
		click(logoBeowulf_Img);
	}
	
	public void clickWalletButton() {
		click(walletMenu_Btn);
	}
	
	public String getTestEnv() {
		return getElement(server_Txt).getText();		
	}
	

	

}
