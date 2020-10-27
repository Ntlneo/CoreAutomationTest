package AutoWalletTest;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import CoreAutomation.BaseAutoPage.BasePage;
import CoreAutomation.BaseAutoTest.BaseTest;


/**
 * Unit test for simple App.
 */
//@TestInstance(Lifecycle.PER_CLASS)
public class Test_Wallet_HomePage extends BaseTest{	
	
	
	// *********************** CODE BELOW ***********************
	
	@Test
	public void SignUpWallet() {	
		bHomePage.clickWalletButton();
		wHomePage.runCmdComand(wHomePage.commandOpenChromeInDebug);
		wHomePage.showPopup();
		wHomePage.clickCreateOneLink();		
//		wHomePage.switchFromAppToChrome();
		initDriverChrome();
//		driverChrome.get("google.com");
		
		
		
		
	}




	
	

}
