package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_SendPage extends BasePage{
	public Wallet_SendPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By to_Box = By.id("toAddress");
	private By amount_Box = By.id("amountInput");
	private By amountUnit_Dropdown = By.name("amount_unit");
	private By coinUnitList_option = By.xpath("//*[@class='no-underline coin']");
	private By memo_Box = By.id("memoInput");
	private By fee_Box = By.id("feeInput");
	private By feeCoinUnit = By.xpath("//*[@class='input-group-text transparent']");
	private By next_Btn = By.xpath("//*[@type='submit']");	
	
	
	// *********************** ACTIONS ***********************
	
	public void inputTo(String toAddress) {
		input(to_Box, toAddress);
	}
	
	public void inputAmount(String amount) {
		input(to_Box, amount);
	}
	
	public void selectCoinUnit(int orderOfCoin) {
		click(amountUnit_Dropdown);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		getListElement_AfterFluentWait_Default(coinUnitList_option).get(orderOfCoin - 1);
	}
	
	public void inputMemo(String textMemo) {
		input(memo_Box, textMemo);
	}
	
	public void inputFee(String textFee) {
		input(fee_Box, textFee);
	}
	
	public String getFeeCoinUnit() {
		return getElement(feeCoinUnit).getText();
	}
	
	public void clickNext() {
		click(next_Btn);
		
	}
}
