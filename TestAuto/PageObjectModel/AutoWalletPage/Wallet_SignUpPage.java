package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_SignUpPage extends BasePage{
	public Wallet_SignUpPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	//Web
	private By walletName_Box = By.id("walletName");
	private By check_Btn = By.xpath("//*[@type='submit']");
	
	
	// App
//	private By newBeowulfWallet_Txt = By.xpath("//*[@class='h1 text-center h-saraburn font-weight-normal']");
//	private By walletName_Box = By.id("walletName");
//	private By walletPassword_Box = By.id("passwordInput");
//	private By walletConfirmPassword_Box = By.id("confirmPasswordInput");
//	private By submit_Btn = By.xpath("//*[@type='submit']");
	
	//make sure internet
//	private By goingCreateWallet_Txt = By.xpath("//*[@class='h3 h-saraburn text-center font-weight-normal']/following-sibling::div[1]");
//	private By makeSureInternet_Txt = By.xpath("//*[@class='h3 h-saraburn text-center font-weight-normal']/following-sibling::div[2]");
//	private By back_Btn = By.xpath("//*[@class='btn']");
//	private By submitConfirmation_Btn = By.xpath("//*[@class='btn active']");
	
	//captcha need to bypass --> display a popup and wait until captcha is resolved manually
	
	
	
	
	
	
	// *********************** ACTIONS ***********************

	public void inputWalletName(String walletName) {
		input(walletName_Box, walletName);
	}

	public void clickCheckBtn() {		
		click(check_Btn);		
	}
	

}
