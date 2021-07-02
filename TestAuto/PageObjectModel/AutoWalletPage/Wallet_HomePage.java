package AutoWalletPage;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_HomePage extends BasePage{
	public Wallet_HomePage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************
	
	private By beowulfBlockchainWallet_Txt = By.xpath("//*[@class='h1 text-center h-saraburn text-danger']");	
	private By selectWallet_Box = By.xpath("//*[@data-vv-name='wallet_selection']");
	private By passwordWallet_Box = By.id("passwordInput");
	private By accDropdownList_Option = By.xpath("//*[@class='el-scrollbar']//li");
	private By deleteIconDropdownList_Option = By.xpath("//*[@data-icon='times-circle']");
	private By createWallet_Option = By.xpath("//*[@class='el-scrollbar']//span");	
	private By login_Btn = By.xpath("//*[@type='submit']");
	private By importPrivateKey_Btn = By.xpath("//*[@class='btn']");
	private By importWalletFile_Btn = By.xpath("//*[@class='btn']/following-sibling::button");	
	
	// *********************** ACTIONS ***********************

	
	public Boolean isWalletHomePageDisplay() {
		return (isElementDisplayed(login_Btn) && isElementDisplayed(importPrivateKey_Btn));
	}

	public void selectWallet(int orderOfWalletAcc) {
		click(selectWallet_Box);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		getListElement(accDropdownList_Option).get(orderOfWalletAcc).click();		
		getListElement_AfterFluentWait_Default(accDropdownList_Option).get(orderOfWalletAcc).click();
		
	}
	
	public void inputWalletPassword(String walletPassword) {
		input(passwordWallet_Box, walletPassword);
	}
	
	public void clickLoginButton() {		
		click(login_Btn);		
	}
	
	public void clickImportPrivateKey() {
		click(importPrivateKey_Btn);
	}
	
	public void clickImportWalletFile() {
		click(importWalletFile_Btn);
	}
}
