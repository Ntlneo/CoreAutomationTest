package OtherAutoProjects;

import java.io.IOException;

public class test {

	public static void main(String[] args) {
		Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
		int maxRound = 10;
		
		int round = 1;
		while(true) {
			try {
				System.out.println("######### Round : " + round + " #########\n");
//				cmc.testOpenFan8_FromGoogle();
//				cmc.testOpenFan8_FromSearchCMC();
//				cmc.testOpenFan8_FromGoogle_Multi();
//				cmc.testOpenFan8_FromSearchCMC_Multi();
				
				//1:Google - 2:CoinMarketCap
				cmc.testOpenFan8_FromMultiProxy(1);
				cmc.testOpenFan8_FromMultiProxy(2);
				round++;

			} catch (Exception e) {
				System.out.println("### Round error : " + round);
				e.printStackTrace();
			}	
		}
		
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

}
