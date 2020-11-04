package AutoWalletTest;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import AutoWalletPage.Wallet_HomePage;
import AutoWalletPage.Wallet_SignUpPage;
import CoreAutomation.BaseAutoPage.BasePage;
import CoreAutomation.BaseAutoTest.BaseTest;


/**
 * Unit test for simple App.
 */
//@TestInstance(Lifecycle.PER_CLASS)
public class Test_Wallet_HomePage extends BaseTest{	
	
	String walletName = "lamwallet9";

	
	// *********************** CODE BELOW ***********************
	
	@Test
	public void SignUpWallet() {	
		bHomePage_Electron.clickWalletButton();
		
		//open Chrome in debug mode
		BasePage.runCmdComand(BasePage.commandOpenChromeInDebug);
		
		//attach ChromeDriver to existing chrome
		initDriverChrome();
		wSignUpPage_Web = new Wallet_SignUpPage(driverChrome);		
		
		wHomePage_Electron.clickCreateOneLink();

//		BasePage.showPopupUntilClickOK();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//switch to lastest tab
		wSignUpPage_Web.switchLatestWindow();
		wSignUpPage_Web.inputWalletName(walletName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wSignUpPage_Web.clickCheckBtn();
		System.out.println("Register success: " + walletName);
		

		
		//close Chrome in debug mode

//		driverChrome.get("google.com");
		
		
		
		
	}




	
	

}
