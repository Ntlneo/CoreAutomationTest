package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;
import kotlin.jvm.Synchronized;



public class Beowulf_HomePage extends BasePage{
	public Beowulf_HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	// *********************** LOCATORS ***********************

	private By beowulfBlockChainTitle_Txt = By.xpath("//*[@class='h1 text-center h-saraburn text-danger mb-0']");
	private By logoWallet_Img = By.xpath("//img[contains(@src,'wallet')]");
	private By wallet_Btn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']");
	
	
	
	// *********************** ACTIONS ***********************
		
	public String getTitle() {
		return getWebElement(beowulfBlockChainTitle_Txt).getText();		
	}
	
	public boolean isWalletLogoDisplay() {
		return getWebElement(logoWallet_Img).isDisplayed();
	}
	
	public void clickWalletButton() {
		click(wallet_Btn);
	}
}
