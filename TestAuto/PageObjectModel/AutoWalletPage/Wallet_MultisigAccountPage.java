package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_MultisigAccountPage extends BasePage{
	public Wallet_MultisigAccountPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By refresh_Icon = By.xpath("(//*[contains(@src,'refresh')])[3]");
	private By createMultisigAccount_Btn = By.xpath("(//*[@class='text'])[2]");

	
	
	// *********************** ACTIONS ***********************
	
	public void clickNext() {
		click(next_Btn);
		
	}
}
