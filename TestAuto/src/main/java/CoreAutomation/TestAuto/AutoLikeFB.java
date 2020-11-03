package CoreAutomation.TestAuto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * Hello world!
 *
 */
public class AutoLikeFB {

    public static void main( String[] args )
    {
    	
        System.out.println( "WELCOME TO MY AUTO-LIKE SCRIPT" ); 
        getDataFromJson();
        
    	
	}
	

    

	
	static public void getDataFromJson() {
		String acc1_TxtFile = "WalletAccountFile/lamwallet1-account.txt";
		JsonObject jsonObject = null;
		String name_TxtFile = "name";
		String privateKey_TxtFile = "private_key";
		
		
		Gson gs = new Gson();		
		try {
			jsonObject = gs.fromJson(new FileReader(acc1_TxtFile), JsonObject.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("NAME: " + jsonObject.get(name_TxtFile) + "\nPRIVATE KEY: " + jsonObject.get(privateKey_TxtFile));		
	}


  
	String x = "ToiDiHoc";	
	
	public String addSpaceBeforeCapitalCharacter(String string) {
		String stringTemp1 = "",stringTemp2;
//		System.out.println("Before search : " + string);
        
	      //find Capital characters of string and add to new temp1 string
	        for(int i = 0; i < string.length(); i++)  
	         {             
	            char character = string.charAt(i);    
	            if(i != 0 && Character.isUpperCase(character)) 
	            {
	            	stringTemp1 += character;
	            }	       
	         }
	        
	        //replace Capital characters of old string by Space + Capital Characters of new temp1 string
	        System.out.println("S1 length : " + stringTemp1.length());
	        for(int i = 0 ; i <stringTemp1.length(); i++)
	        {
	        	stringTemp2 = string.replace(String.valueOf(stringTemp1.toCharArray()[i]), " " + String.valueOf(stringTemp1.toCharArray()[i]));
	        	string = stringTemp2;
	        	System.out.println("After search " + i + " : " + string);
	        }
//	        System.out.println("After search : " + string);
	        return string;
	}
    
    
}
