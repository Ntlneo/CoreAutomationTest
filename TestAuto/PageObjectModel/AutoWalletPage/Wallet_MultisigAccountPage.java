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
	private By importAccount_Btn = By.xpath("(//*[@class='text'])[3]");
	

	
	
	// *********************** ACTIONS ***********************
	
	public void clickRefreshButton() {
		click(refresh_Icon);		
	}
	
	public void clickCreateMultisigAccount() {
		click(createMultisigAccount_Btn);		
	}
	
	public void clickImportAccountButton() {
		click(importAccount_Btn);		
	}
}
