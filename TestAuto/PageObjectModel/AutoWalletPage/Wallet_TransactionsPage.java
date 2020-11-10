package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_TransactionsPage extends BasePage{
	public Wallet_TransactionsPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By walletName_Txt = By.xpath("//*[@class='h3 h3--1 d-inline-block name-wallet']/u");
	private By dashboard_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[1]");

	
	
	
	
	
	// *********************** ACTIONS ***********************


}
