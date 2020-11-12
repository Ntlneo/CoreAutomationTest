package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_TransactionsPage extends BasePage{
	public Wallet_TransactionsPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By refresh_Icon = By.xpath("(//*[contains(@src,'refresh')])[2]");
	private By transactionId_Txt = By.xpath("//*[contains(@class,'block-action overflow-auto')]//div[@class='media-body']/div");
	private By transactionType_Btn = By.xpath("//*[contains(@class,'block-action overflow-auto')]//div[@class='threedoc']");
	private By transactionFromAddress_Txt = By.xpath("//*[contains(@class,'block-action overflow-auto')]//div[@class='from']/div");
	private By transactionAction_Txt = By.xpath("//*[contains(@class,'block-action overflow-auto')]//div[@class='ml-2 mr-2 text-smaller-medium']");
	private By transactionToAddress_Txt = By.xpath("//*[contains(@class,'block-action overflow-auto')]//div[@class='to']/div");
	
	private By transactionDetails_Object_Dropdown = By.xpath("//*[contains(@id,'collapse0')]//span[@class='json-formatter-constructor-name']");
	private By valueList_OfTransactionDetails = By.xpath("//*[contains(@id,'collapse0')]//span[@class='json-formatter-string']");
	private By transactionDetails_ValueRow1_Txt = By.xpath("(//*[contains(@id,'collapse0')]//span[@class='json-formatter-string'])[1]");
	private By transactionDetails_ValueRow2_Txt = By.xpath("(//*[contains(@id,'collapse0')]//span[@class='json-formatter-string'])[2]");
	private By transactionDetails_ValueRow3_Txt = By.xpath("(//*[contains(@id,'collapse0')]//span[@class='json-formatter-string'])[3]");
	private By transactionDetails_ValueRow4_Txt = By.xpath("(//*[contains(@id,'collapse0')]//span[@class='json-formatter-string'])[4]");
	
	
	
	// *********************** ACTIONS ***********************

	public String getRowValue_OfTransactionDetails(int rowNumber) {
		return getListElement(valueList_OfTransactionDetails).get(rowNumber).getText();
	}
	
	public String getValueRow4_OfTransactionDetails() {
		return getElement(transactionDetails_ValueRow4_Txt).getText();
	}
	
	public String getValueRow3_OfTransactionDetails() {
		return getElement(transactionDetails_ValueRow3_Txt).getText();
	}
	
	public String getValueRow2_OfTransactionDetails() {
		return getElement(transactionDetails_ValueRow2_Txt).getText();
	}
	
	public String getValueRow1_OfTransactionDetails() {
		return getElement(transactionDetails_ValueRow1_Txt).getText();
	}
	
	public void clickObjectDropdownMenu() {
		click(transactionDetails_Object_Dropdown);
	}
		
	public void clickRefreshButton() {
		click(refresh_Icon);
	}
	
	public void clickTransactionId() {
		click(transactionId_Txt);
	}
	
	public String getTransactionId() {
		return getElement(refresh_Icon).getText();
	}
	
	public void clickTransactionTypeButton() {
		click(transactionType_Btn);
	}
	
	public String getTransactionType() {
		return getElement(transactionType_Btn).getText();
	}
	
	public void clickFromAddress() {
		click(transactionFromAddress_Txt);
	}
	
	public void clickToAddress() {
		click(transactionToAddress_Txt);
	}
	
	public String getTransactionSummary() {
		return getElement(transactionFromAddress_Txt).getText() + " "
				+ getElement(transactionAction_Txt).getText() + " "
				+ getElement(transactionToAddress_Txt).getText();
	}
}
