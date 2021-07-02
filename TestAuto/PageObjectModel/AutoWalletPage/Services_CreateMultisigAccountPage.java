package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Services_CreateMultisigAccountPage extends BasePage{
	public Services_CreateMultisigAccountPage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************

	private By accName_Box = By.name("account_name");
	private By cosigner1_Box = By.name("cosigner0");
	private By cosigner2_Box = By.name("cosigner1");
	private By addCosigner_Btn = By.xpath("//*[contains(@class,'add-cosigner')]");
	private By minimumSignature_Box = By.name("min_sign");
	private By passwordWallet_Box = By.id("sendPasswordInput");
	private By create_Btn = By.xpath("//*[@type='submit']");
	
	
	
	// *********************** ACTIONS ***********************
	
	public void inputAccountName(String accName) {
		input(accName_Box, accName);	
	}
	
	public void inputCosigner1(String cosignerName) {
		input(cosigner1_Box, cosignerName);	
	}
	
	public void inputCosigner2(String cosignerName) {
		input(cosigner2_Box, cosignerName);	
	}
	
	public void clickAddCosigner() {
		click(addCosigner_Btn);	
	}
	
	public void inputMinimumSignature(String minSign) {
		input(minimumSignature_Box, minSign);	
	}
	
	public void inputPasswordWallet(String password) {
		input(passwordWallet_Box, password);	
	}
	
	public void clickCreateButton() {
		click(create_Btn);	
	}
}
