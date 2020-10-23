package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_HomePage extends BasePage{
	public Wallet_HomePage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************
	//Login wallet
	private By selectWallet_Box = By.xpath("//*[@data-vv-name='wallet_selection']");
//	By createWallet_Option = By.xpath("//span[text()='Create Wallet']");
	private By createWallet_Option = By.xpath("//*[@class='el-scrollbar']//span");
	private By createOne_Link = By.xpath("//*[@class='text-danger']");
	
	//Create wallet
	private By walletName_Box = By.id("walletName");
	private By walletPassword_Box = By.id("passwordInput");
	private By walletConfirmPassword_Box = By.id("confirmPasswordInput");
	private By submit_Btn = By.xpath("//*[@type='submit']");
	
	
	
	
	
	// *********************** ACTIONS ***********************
	
//	@Test
//	public void Test_LoginWallet() {
//		System.out.println("\t*** STARTING TestCase 2\t***");
//		click(pwalletBtn);
//		click(createOne_Link);		
//		input(walletName_Box, "lamwallet1");
//		input(walletPassword_Box, "12345678");
//		input(walletConfirmPassword_Box, "12345678");
//		click(submit_Btn);
//		// After Test
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}



	public void click_CreateOneLik() {		
		click(createOne_Link);		
	}
	
	
}
