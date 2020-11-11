package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_NavigateMenu extends BasePage{
	public Wallet_NavigateMenu(WebDriver driver) {
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

	public String getWalletName() {
		return getElement(walletName_Txt).getText();
	}
	
	public void clickDashboardTab() {
		click(dashboard_Tab);
	}
	
	public void clickTransactionsTab() {
		click(transactions_Tab);
	}

	public void clickSendTab() {
		click(send_Tab);
	}
	
	public void clickMultisigAccountTab() {
		click(multisigAccount_Tab);
	}
	
	public void clickMultisigTransactionTab() {
		click(multisigTransaction_Tab);
	}
}
