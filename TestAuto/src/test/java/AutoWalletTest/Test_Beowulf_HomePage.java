package AutoWalletTest;


import org.junit.jupiter.api.Test;

import CoreAutomation.BaseAutoTest.BaseTest;


/**
 * Unit test for simple App.
 */
public class Test_Beowulf_HomePage extends BaseTest{


	// *********************** CODE BELOW ***********************
	
	@Test
	public void OpenWalletHomePage() {					
			bHomePage.click_WalletButton();			
	}
	
	@Test
	public void OpenCreateWalletPage() {		
			bHomePage.click_WalletButton();	
			wHomePage.click_CreateOneLik();		
	}
	

	
	
	



	

	


	




	
	

}
