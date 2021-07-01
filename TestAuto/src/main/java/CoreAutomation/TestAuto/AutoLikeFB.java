package CoreAutomation.TestAuto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

	public static void main(String[] args) {
		List<String> listProxy = new ArrayList<>();
		String cmdProxy = " curl \"http://pubproxy.com/api/proxy?limit=10&last_check=1&format=txt&type=http&https=false\"";
		String cmdTest = "cmd /c start cmd.exe /K";
		try {

			// -- Windows --

//			// Run a command
//			Process process = Runtime.getRuntime().exec(cmdTest+cmdProxy);
//			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			System.out.println(reader.readLine());
//			String line;
//			while ((line = reader.readLine()) != null) {
//				listProxy.add(line);
////    	            output.append(line + "\n");
//			}
//
//			int exitVal = process.waitFor();
//			if (exitVal == 0) {
//				System.out.println("Success!");
////				System.out.println(output);
//				System.exit(0);
//			} else {
//				// abnormal...
//			}
			
			URL url = new URL("http://pubproxy.com/api/proxy?limit=10&last_check=1&format=txt&type=http&https=false");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        InputStream responseStream = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream));
	        String line;
	        while ((line = rd.readLine()) != null) {
	            System.out.println(line);
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}
	  	for (String string : listProxy) {
    		 System.out.println(string);			
		}
	}

}
