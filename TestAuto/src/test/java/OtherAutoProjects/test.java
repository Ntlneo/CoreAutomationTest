package OtherAutoProjects;

import java.io.IOException;

public class test {

	public static void main(String[] args) {
		Test_Pump_Fan8_InCMC cmc = new Test_Pump_Fan8_InCMC();
		int maxRound = 10;
		for(int i=1;i <= maxRound; i++) {
			try {
				System.out.println("### Round : " + i);
//				cmc.testOpenCMC();
				cmc.testOpenFan8CMC_Direct();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
