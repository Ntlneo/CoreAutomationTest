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
	private By testNet_Txt = By.xpath("//*[@class='text-capitalize mr-2']");
	private By manageBeowulfWallet_Txt = By.xpath("//*[@class='h3 h-saraburn text-center font-weight-normal']");
	private By wallet_Btn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']");
	
	
	
	// *********************** ACTIONS ***********************
	
	public Boolean isBeowulfHomePage() {
		return isElementDisplayed(manageBeowulfWallet_Txt) && isElementDisplayed(wallet_Btn);
	}
	
	public String getTestEnv() {
		return getWebElement(testNet_Txt).getText();		
	}
	
	public void clickWalletButton() {
		click(wallet_Btn);
	}
	

}
