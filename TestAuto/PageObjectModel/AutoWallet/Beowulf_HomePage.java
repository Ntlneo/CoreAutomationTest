package AutoWallet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import kotlin.jvm.Synchronized;



public class Beowulf_HomePage extends BasePage{
	//Home page
	public By walletBtn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']");
	
	static private Beowulf_HomePage instance = null;
	
	@Synchronized
	public static Beowulf_HomePage getInstance() {		
		if(null == instance) {
			instance = new Beowulf_HomePage();
		}		
		return instance;
	}
	
	private Beowulf_HomePage() {		
		super();
	}




	public void clickWalletButton() {
		
	}
}
