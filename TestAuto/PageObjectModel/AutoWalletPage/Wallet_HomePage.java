package AutoWalletPage;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;

public class Wallet_HomePage extends BasePage{
	public Wallet_HomePage(WebDriver driver) {
		super(driver);
	}

	// *********************** LOCATORS ***********************
	
	private By alreadyBeowulfWallet_Txt = By.xpath("//*[@class='h1 text-center h-saraburn font-weight-normal']");	
	private By selectWallet_Box = By.xpath("//*[@data-vv-name='wallet_selection']");
	private By createWallet_Option = By.xpath("//*[@class='el-scrollbar']//span");	
	private By login_Btn = By.xpath("//*[@type='submit']");
	private By importPrivateKey_Btn = By.xpath("//*[@class='btn']");
	private By importWalletFile_Btn = By.xpath("//*[@class='btn']/following-sibling::button");
	private By createOne_Link = By.xpath("//*[@class='anchor text-danger']");

	
	
	
	
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

	

	public void verifyWalletHomePageDisplay() {
		addLog(isWalletHomePageDisplay(), "Wallet HomePage doesn't displayed");
	}
	
	public Boolean isWalletHomePageDisplay() {
		return (isElementDisplayed(alreadyBeowulfWallet_Txt) && isElementDisplayed(createOne_Link));
	}

	public void clickCreateOneLink() {		
		click(createOne_Link);		
	}
	
	
}
