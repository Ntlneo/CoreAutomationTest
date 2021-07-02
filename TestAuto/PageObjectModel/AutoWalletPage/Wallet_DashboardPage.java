package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_DashboardPage extends BasePage{
	public Wallet_DashboardPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By walletName_Txt = By.xpath("//*[@class='h3 h3--1 d-inline-block name-wallet']/u");
	private By dashboard_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[1]");
	
	private By publicKey_Txt = By.xpath("//*[@class='mr-1']");
	private By showPrivateKey_Icon = By.xpath("(//*[@class='ml-auto']/a/img)[1]");
	private By exportWalletFile_Icon = By.xpath("(//*[@class='ml-auto']/a/img)[2]");
	private By copyPublicKey_Icon = By.xpath("(//*[@class='ml-auto']/a/img)[3]");
	private By refreshBalance_Icon = By.xpath("(//*[@class='ml-auto']/a/img)[4]");
	
	private By coinBWF_Balance = By.xpath("(//*[@class='h5 black mb-0'])[1]");
	private By coinW_Balance = By.xpath("(//*[@class='h5 black mb-0'])[2]");

	
	
	
	
	
	// *********************** ACTIONS ***********************

	public void clickRefreshBalance() {
		click(refreshBalance_Icon);
	}

	public String getBWF_Balance() {		
		return getElement(coinBWF_Balance).getText();		
	}
	
	public String getW_Balance() {		
		return getElement(coinW_Balance).getText();		
	}
}
