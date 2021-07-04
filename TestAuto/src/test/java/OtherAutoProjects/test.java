package OtherAutoProjects;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;

public class test {
	static Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
	static String proxyUserInput = "Default Servers of Phuong Cao";
	static String coinToTest = "Fan8";

	public static void main(String[] args) {

		try {
//			if (null != args[0] && null != args[1]) {
//				coinToTest = args[0];
//				proxyUserInput = args[1];
//				cmc.listURL_PhuongCao.clear();
//				cmc.listURL_PhuongCao.add(proxyUserInput);
//			} else if (null != args[1]) {
				if (args[0].contains("http")) {
					proxyUserInput = args[0];
					cmc.listURL_PhuongCao.clear();
					cmc.listURL_PhuongCao.add(proxyUserInput);
				} else {
					coinToTest = args[0];
				}

//			}
//			proxyUserInput = args[0];
//			cmc.coinSearch = coinToTest;
//			cmc.listURL_PhuongCao.clear();
//			cmc.listURL_PhuongCao.add(proxyUserInput);
//			runTest();
		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("ArrayIndexOutOfBoundsException caught");
//			System.out.println("\tYou're currently Run test with values:");
//			System.out.println("\tCoin: " + coinToTest + "- Proxy: " + proxyUserInput);
		} finally {
			System.out.println("\tRuning test with values:");
			System.out.println("\tCoin: " + coinToTest + " --- Proxy: " + proxyUserInput + "\n");
			runTest();
		}

//		forTestonly();
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

	static void runTest() {
//			Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
		int maxRound = 10;

		int round = 1;
		while (true) {
			try {
				System.out.println("######### Round : " + round + " #########\n");
//					cmc.testOpenFan8_FromGoogle();
//					cmc.testOpenFan8_FromSearchCMC();
//					cmc.testOpenFan8_FromGoogle_Multi();
//					cmc.testOpenFan8_FromSearchCMC_Multi();

				// 1:Google - 2:CoinMarketCap
				cmc.testOpenFan8_FromMultiProxy(2, coinToTest);
				cmc.testOpenFan8_FromMultiProxy(1, coinToTest);
				round++;

			} catch (Exception e) {
				System.out.println("### Round error : " + round);
				e.printStackTrace();
			}
		}
	}

//		Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
//		int maxRound = 10;
//		
//		int round = 1;
//		while(true) {
//			try {
//				System.out.println("######### Round : " + round + " #########\n");
////				cmc.testOpenFan8_FromGoogle();
////				cmc.testOpenFan8_FromSearchCMC();
////				cmc.testOpenFan8_FromGoogle_Multi();
////				cmc.testOpenFan8_FromSearchCMC_Multi();
//				
//				//1:Google - 2:CoinMarketCap
//				cmc.testOpenFan8_FromMultiProxy(1);
//				cmc.testOpenFan8_FromMultiProxy(2);
//				round++;
//
//			} catch (Exception e) {
//				System.out.println("### Round error : " + round);
//				e.printStackTrace();
//			}	
//		}

//		for(int i=1;i <= maxRound; i++) {
//			try {
//				System.out.println("######### Round : " + i + " #########");
////				cmc.testOpenCMC();
//				cmc.testOpenFan8_FromGoogle();
//				cmc.testOpenFan8_FromSearchCMC();				
//
//			} catch (InterruptedException e) {
//				System.out.println("### Round error : " + i);
//				e.printStackTrace();
//			}
//		}

}
