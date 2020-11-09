package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;
import kotlin.jvm.Synchronized;



public class All_TopMenuPage extends BasePage{
	public All_TopMenuPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	// *********************** LOCATORS ***********************
	private By logoBeowulf_Img = By.xpath("//img[contains(@src,'beowulf-logo')]");
	private By walletMenu_Btn = By.xpath("//*[@class='nav-item'][1]");
	private By servicesMenu_Btn = By.xpath("//*[@class='nav-item'][2]");
	private By settingsMenu_Btn = By.xpath("//*[@class='nav-item'][3]");
	private By createWallet_Btn = By.xpath("//*[@class='nav-item text-danger']");
	private By server_Txt = By.xpath("//*[@class='text-capitalize']");
	

	
	
	
	// *********************** ACTIONS ***********************
	
	public void openWalletSignUpPage() {
		click(createWallet_Btn);
	}
	
	public void openSettingsHomePage() {
		click(settingsMenu_Btn);
	}
	
	public void openServicesHomePage() {
		click(servicesMenu_Btn);
	}
	
	public void openBeowulfHomePage() {
		click(logoBeowulf_Img);
	}
	
	public void openWalletHomePage() {
		click(walletMenu_Btn);
	}
	
	public String getTestEnv() {
		return getElement(server_Txt).getText();		
	}
	

	

}
