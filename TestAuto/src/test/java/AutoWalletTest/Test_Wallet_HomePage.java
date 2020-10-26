package AutoWalletTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import CoreAutomation.BaseAutoTest.BaseTest;


/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class Test_Wallet_HomePage extends BaseTest{	
	
	
	// *********************** CODE BELOW ***********************
	
	@Test
	public void RegisternWallet() {	
		bHomePage.clickWalletButton();
//		bHomePage.wrong_click_WalletButton();
	}




	
	

}
