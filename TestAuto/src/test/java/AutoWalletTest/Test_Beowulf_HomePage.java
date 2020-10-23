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
			System.out.println("\tSTARTING TestCase 1\t");		
			bHomePage.click_WalletButton();			
	}
	
	@Test
	public void OpenCreateWalletPage() {
			System.out.println("\tSTARTING TestCase 2\t");		
			bHomePage.click_WalletButton();	
			wHomePage.click_CreateOneLik();		
	}
	

	
	
	



	

	


	




	
	

}
