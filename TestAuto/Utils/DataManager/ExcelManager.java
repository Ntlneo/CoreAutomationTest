package DataManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	String excelFilePath = "DataTest/DataAutoFB.xlsx";
	String sheetLike4Like = "Like4Like";
	String sheetFB = "FB";
//	String sheetGoogle = "Google";

	Workbook wb;

	public List<String> listUsername_Like4Like = new ArrayList<String>();
	public List<String> listPassword_Like4Like = new ArrayList<String>();
	public List<String> listUsername_FB = new ArrayList<String>();
	public List<String> listPassword_FB = new ArrayList<String>();
//	List<String> listUsername_Google;	//use later
//	List<String> listPassword_Google;	//use later

	// use Later
//	public ExcelManager(String excelFilePath) {
//		this.excelFilePath = excelFilePath;
//		try {
//			wb = new XSSFWorkbook(excelFilePath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public ExcelManager() {
		try {
			String userPath = System.getProperty("user.dir");
			File excelFile = new File(userPath + "/" + excelFilePath);
			wb = new XSSFWorkbook(excelFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readDataFromExcel();
	}

	public void readDataFromExcel() {
		for (Sheet sheet : wb) {
//			System.out.println("Name of sheet = " + sheet.getSheetName());
			if (sheet.getSheetName().equalsIgnoreCase(sheetLike4Like)) {
//				System.out.println("Last row numb = " + sheet.getLastRowNum());
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//					System.out.println(sheet.getRow(i).getLastCellNum());
					listUsername_Like4Like.add(sheet.getRow(i).getCell(1).getStringCellValue());
					listPassword_Like4Like.add(sheet.getRow(i).getCell(2).getStringCellValue());//
				}
			}

			if (sheet.getSheetName().equalsIgnoreCase(sheetFB)) {
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					listUsername_FB.add(sheet.getRow(i).getCell(1).getStringCellValue());
					listPassword_FB.add(sheet.getRow(i).getCell(2).getStringCellValue());
				}
			}
		}
	}
	
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}