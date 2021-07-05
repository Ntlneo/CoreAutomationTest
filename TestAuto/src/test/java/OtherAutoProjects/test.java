package OtherAutoProjects;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class test {
	static String coin_Default = "Fan8";
	static String url_Default = "Default Servers of Phuong Cao";		
	static String url1_PhuongCao = "https://teamcity.quickom.com/proxy1.txt";
	static String url2_PhuongCao = "https://teamcity.quickom.com/proxy2.txt";
//	static String url3_PhuongCao = "https://teamcity.quickom.com/proxy3.txt";
//	static String url4_PhuongCao = "https://teamcity.quickom.com/proxy4.txt";
//	static String url5_PhuongCao = "https://teamcity.quickom.com/proxy5.txt";
	static List<String> listURL_Default = new ArrayList<>();

	public static void main(String[] args) {
		setDefault_Coin_Url(coin_Default, url_Default);
		listURL_Default = getListUrl_Default(url1_PhuongCao, url2_PhuongCao);
		
		//		forTestonly();		
		try {
//			if (null != args[0] && null != args[1]) {
//				coinToTest = args[0];
//				proxyUserInput = args[1];
//				listURL_PhuongCao.clear();
//				listURL_PhuongCao.add(proxyUserInput);
//			} else if (null != args[1]) {
				if (args[0].contains("http")) {
					url_Default = args[0];
					listURL_Default.clear();
					listURL_Default.add(url_Default);
				} else {
					coin_Default = args[0];
				}

		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("ArrayIndexOutOfBoundsException caught");
//			System.out.println("\tYou're currently Run test with values:");
//			System.out.println("\tCoin: " + coinToTest + "- Proxy: " + proxyUserInput);
		} finally {
			System.out.println("\tRuning test with values:");
			System.out.println("\tCoin: " + coin_Default + " --- Proxy: " + url_Default + "\n");
			runTest();
		}
	}
	
	static void setDefault_Coin_Url(String coin, String url) {
		coin_Default = coin;
		url_Default = url;		
	}
	
	static List<String> getListUrl_Default(String... urls) {
		List<String> localList = new ArrayList<>();
		for (String url : urls) {
			localList.add(url);
		}
		return localList;
	}
	
	static void runTest() {
		Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
		int maxRound = 10;

		int round = 1;
		while (true) {
			try {
				System.out.println("######### Round : " + round + " #########\n");

				// 1:CoinMarketCap - 2:Google 
				cmc.testOpenFan8_FromMultiProxy(1, coin_Default, listURL_Default);
				cmc.testOpenFan8_FromMultiProxy(2, coin_Default, listURL_Default);
				round++;

			} catch (Exception e) {
				System.out.println("### Round error : " + round);
				e.printStackTrace();
			}
		}
}

//	static void forTestonly() {
//		cmc.initChromeNoProxy();
//		cmc.driver.get("https://coinmarketcap.com/zh-tw/currencies/fan8/");
//		cmc.closeCMCPolicyBanner();
//		cmc.clickOverview();
//		cmc.scrollBotAndTopPage();		
//		cmc.scrollToVoteBanner();
//		cmc.clickRateGood();
////		cmc.sleep(20);
//		System.out.println("END");
//		cmc.driver.quit();
//	}

	



}
