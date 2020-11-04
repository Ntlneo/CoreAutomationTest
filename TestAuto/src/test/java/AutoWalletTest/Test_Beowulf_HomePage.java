package AutoWalletTest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import CoreAutomation.BaseAutoTest.BaseTest;


/**
 * Unit test for simple App.
 */
//@TestInstance(Lifecycle.PER_CLASS)
public class Test_Beowulf_HomePage extends BaseTest{


	// *********************** CODE BELOW ***********************
	
	@Test
	public void OpenWalletHomePage() {					
			bHomePage_Electron.clickWalletButton();
			wHomePage_Electron.verifyWalletHomePageDisplay();
	}
	

	

	

	
	
	



	

	


	




	
	

}
