package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_NavigatePage extends BasePage{
	public Wallet_NavigatePage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By walletName_Txt = By.xpath("//*[@class='h3 h3--1 d-inline-block name-wallet']/u");
	private By dashboard_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[1]");
	private By transactions_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[2]");
	private By send_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[3]");
	private By multisigAccount_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[4]");
	private By multisigTransaction_Tab = By.xpath("//*[@class='nav nav-second-points horizontal']/a[5]");	
	
	
	// *********************** ACTIONS ***********************

	public void openDashboardTab() {
		click(dashboard_Tab);
	}
	
	public void openTransactionsTab() {
		click(transactions_Tab);
	}

	public void openSendTab() {
		click(send_Tab);
	}
	
	public void openMultisigAccountTab() {
		click(multisigAccount_Tab);
	}
	
	public void openMultisigTransactionTab() {
		click(multisigTransaction_Tab);
	}
}
