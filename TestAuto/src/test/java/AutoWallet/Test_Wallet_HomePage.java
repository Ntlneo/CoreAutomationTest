package AutoWallet;

import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.util.Pair;
import org.apache.logging.log4j.core.util.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.devtools.browser.Browser;
//import org.openqa.selenium.devtools.browser.model.PermissionType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutoWallet.Beowulf_HomePage;
import CoreAutomation.BaseAutoTest.BaseTest;
import DataManager.ExcelManager_Map;


/**
 * Unit test for simple App.
 */
public class Test_Wallet_HomePage extends BaseTest{
	
	// global


	

	
	//Login wallet
	By selectWallet_Box = By.xpath("//*[@data-vv-name='wallet_selection']");
//	By createWallet_Option = By.xpath("//span[text()='Create Wallet']");
	By createWallet_Option = By.xpath("//*[@class='el-scrollbar']//span");
	By createOne_Link = By.xpath("//*[@class='text-danger']");
	
	//Create wallet
	By walletName_Box = By.id("walletName");
	By walletPassword_Box = By.id("passwordInput");
	By walletConfirmPassword_Box = By.id("confirmPasswordInput");
	By submit_Btn = By.xpath("//*[@type='submit']");
	
	
	
	
	
	// *********************** RUN TEST AUTO ***********************


	@Test
	public static void OpenWallet() {
			// Before Test
			System.out.println("\t*** STARTING TestCase 1\t***");			
			// Star Test			
			
			Beowulf_HomePage.getInstance().;
	}
	
	@Test
	public void Test_LoginWallet() {
		System.out.println("\t*** STARTING TestCase 2\t***");
		click(pwalletBtn);
		click(createOne_Link);		
		input(walletName_Box, "lamwallet1");
		input(walletPassword_Box, "12345678");
		input(walletConfirmPassword_Box, "12345678");
		click(submit_Btn);
		// After Test
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	



	
	
	// *********************** BEFORE & AFTER ***********************
	


	// *********************** TASKS ***********************
	
	


	




	
	

}
