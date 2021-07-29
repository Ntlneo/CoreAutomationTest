package OtherAutoProjects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class LogTestManager {
	
	public File logFile;
	public File logFolder;
	public String path_LogFolder;
	public String path_LogFile;
	public String name_LogFolder;
	public String name_LogFile;
//	static String nameLogFile = "Log_";
//	static String s1 = "\nAlibaba \nva 40 ten cuop\no trong rung";
//	static String s2 = "\nSau do giai tan\nAi ve nha nay";
	
	
	public void printToLogFileAndConsole(String s) {
		System.out.println(s);
		updateLogFile(s);
	}
	
	public void printToLogFileAndConsole(int i) {
		System.out.println(i);
		updateLogFile(i);
	}
	
	public void clearLogFolderIfLargeSize(long size_byMB) {
		String s = FileUtils.byteCountToDisplaySize(FileUtils.sizeOfDirectory(logFolder));
		printToLogFileAndConsole("Log files will be auto deteled if > " + size_byMB + " MB");
		printToLogFileAndConsole("Current size of Log files: " + s);
//		System.out.println(StringUtils.getDigits(s));
		try {
			if(s.equalsIgnoreCase("mb")) {
				long sizeFolder = Long.parseLong(StringUtils.getDigits(s));
				if(sizeFolder > size_byMB) {
					FileUtils.cleanDirectory(logFolder);
					printToLogFileAndConsole("Log files cleared.");
				}
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void appendTextToFile(String s, File f) {
		try {
			FileUtils.write(f, s, CharsetNames.UTF_8,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateLogFile(String s) {
		try {
			FileUtils.write(logFile, s +"\n", CharsetNames.UTF_8,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateLogFile(int i) {
		try {
			FileUtils.write(logFile, i +"\n", CharsetNames.UTF_8,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void writeLogFile(String s) {	
		try {
			FileUtils.write(logFile, s, CharsetNames.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createLogFile(String nameOfLogFile, String nameOfLogFolder) {
		setLogInfo(nameOfLogFile, nameOfLogFolder);
		logFile = new File(path_LogFile);
	}
	
	public void setLogInfo(String nameOfLogFile, String nameOfLogFolder) {
		String userPath = System.getProperty("user.dir");
		String prefixLogFile = nameOfLogFile;
		String postfixLogFile = new SimpleDateFormat("_dd-MM-yyyy_HH-mm-ss").format(new Date());
		name_LogFile = prefixLogFile + postfixLogFile + ".txt";
		if(null != nameOfLogFolder) {
			name_LogFolder = nameOfLogFolder;			
			path_LogFolder = userPath + "/" + nameOfLogFolder + "/";
			logFolder = new File(path_LogFolder);
			path_LogFile = path_LogFolder + "/" + name_LogFile;			
		}else {
			path_LogFile = userPath + "/" + name_LogFile;
		}
		
//		logFolder = new File(path_LogFolder);
				
	}
}
