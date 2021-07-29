package OtherAutoProjects;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Ztest {
	
	static public String nameOfLogFolder = "LogTest";
	static public String nameOfLogFile = "Log";
	static LogTestManager log = new LogTestManager();
	
	static String coin_Default = "Fan8";
	static String url_Default;
	static String url1_PhuongCao = "https://teamcity.quickom.com/proxy1.txt";
	static String url2_PhuongCao = "https://teamcity.quickom.com/proxy2.txt";
//	static String url3_PhuongCao = "https://teamcity.quickom.com/proxy3.txt";
//	static String url4_PhuongCao = "https://teamcity.quickom.com/proxy4.txt";
//	static String url5_PhuongCao = "https://teamcity.quickom.com/proxy5.txt";
	static List<String> listURL_Default = new ArrayList<>();

	static Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();	

	public static void main(String[] args) {
		log.createLogFile(nameOfLogFile, nameOfLogFolder);

		listURL_Default = getListUrl_Default(url1_PhuongCao, url2_PhuongCao);

		// forTestonly();
		try {

			if (null != args[0] && args[0].contains("http")) {
				url_Default = args[0];
				listURL_Default.clear();
				listURL_Default.add(url_Default);
			} else {
				coin_Default = args[0];
			}
//		}

		} catch (ArrayIndexOutOfBoundsException e) {

		} finally {
			setDefault_Coin_ListUrl(coin_Default, listURL_Default);
			
			log.printToLogFileAndConsole("\tRuning test with values:");
			log.printToLogFileAndConsole("\tCoin: " + coin_Default + " --- Proxy Server: " + listURL_Default);

//			System.out.println("\tRuning test with values:");
//			System.out.println("\tCoin: " + coin_Default + " --- Proxy Server: " + listURL_Default);

			runTest(coin_Default, listURL_Default);
		}
	}

	static void setDefault_Coin_ListUrl(String coin, List<String> listUrl) {
		coin_Default = coin;
		listURL_Default = listUrl;
	}

	static List<String> getListUrl_Default(String... urls) {
		List<String> localList = new ArrayList<>();
		for (String url : urls) {
			localList.add(url);
		}
		return localList;
	}

	static void runTest(String coin, List<String> listUrl) {

//		int maxRound = 10;
		int round = 1;
		while (true) {
			try {
				log.printToLogFileAndConsole("######### Round : " + round + " #########");
//				System.out.println("######### Round : " + round + " #########");

				// 1:CoinMarketCap - 2:Google - 3:Fan8-aDat
				cmc.testOpenFan8_FromMultiProxy(1, coin, listUrl);
//				cmc.testOpenFan8_FromMultiProxy(2, coin, listUrl);
//				cmc.testOpenFan8_FromMultiProxy(3, coin, listUrl);
				round++;

			} catch (Exception e) {
				log.printToLogFileAndConsole("### Round error : " + round);
//				System.out.println("### Round error : " + round);
//				e.printStackTrace();
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
