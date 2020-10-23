package AutoWalletPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CoreAutomation.BaseAutoPage.BasePage;
import kotlin.jvm.Synchronized;



public class Beowulf_HomePage extends BasePage{
	public Beowulf_HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	// *********************** LOCATORS ***********************
	private By bWalletBtn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']");
//	private By wrongbWalletBtn = By.xpath("//*[@class='btn btn-beowulf btn-danger btn-block']zzz");
	
	
	// *********************** ACTIONS ***********************
	public void click_WalletButton() {
		click(bWalletBtn);
	}
	
//	public void wrong_click_WalletButton() {
//		click(wrongbWalletBtn);
//	}
}
