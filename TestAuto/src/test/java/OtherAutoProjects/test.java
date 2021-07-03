package OtherAutoProjects;

import java.io.File;
import java.io.IOException;

public class test {
	static Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
	
	public static void main(String[] args) throws IOException {		
		String proxyUserInput = "";
		try {
			proxyUserInput = args[0];
			cmc.listURL_PhuongCao.clear();
			cmc.listURL_PhuongCao.add(proxyUserInput);
			runTest();
		} catch (ArrayIndexOutOfBoundsException e){
	        System.out.println("ArrayIndexOutOfBoundsException caught");
	    } finally {
			runTest();
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
	
	static void runTest() {
//		Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
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
	}

}
