package CoreAutomation.TestAuto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class Main_For_Test {

	public static void main(String[] args) {

	      
	     
	     
		writeLogToFile("alibabazzz");
		
		
	}
	
	static public void writeLogToFile(String s) {		 
		try {
			String userPath = System.getProperty("user.dir");
			String nameLogFile = "Log_";
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");  
//			LocalDateTime now = LocalDateTime.now();
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String sDate = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
			String fileName = nameLogFile + sDate +".txt";
			
			//user separator to auto select OS
			String path_LogFile = userPath + "/Log/" + fileName;			
//			String testFile = "D://Log/test.txt";

//		    File file = new File("D:\\ThumucABC");
			File file = new File(path_LogFile);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Thư mục đã được tạo!");
	            } else {
	                System.out.println("Có lỗi xảy ra!");
	            }
			
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(s);
				bw.close();
//			}else {
//				System.out.println("Can't create new log file");
//			}
			
			
			//use buffer
			
		}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
