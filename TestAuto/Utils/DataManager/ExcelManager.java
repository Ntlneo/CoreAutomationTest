package DataManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	HashMap<String,String> accLike4Like = new HashMap<String, String>();
	HashMap<String,String> accFB = new HashMap<String, String>();
	public List<HashMap<String, String>> listAcc_Like4Like = new ArrayList<HashMap<String, String>>();
	public List<HashMap<String, String>> listAcc_FB = new ArrayList<HashMap<String, String>>();

	Workbook wb;

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
		accLike4Like.clear();
		accFB.clear();
		listAcc_Like4Like.clear();
		listAcc_FB.clear();
		for (Sheet sheet : wb) {
			if (sheet.getSheetName().equalsIgnoreCase(sheetLike4Like)) {
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {					
					accLike4Like.put(sheet.getRow(i).getCell(1).getStringCellValue(), 
							sheet.getRow(i).getCell(2).getStringCellValue());
				}
				listAcc_Like4Like.add(accLike4Like);
			}

			if (sheet.getSheetName().equalsIgnoreCase(sheetFB)) {
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {					
					accFB.put(sheet.getRow(i).getCell(1).getStringCellValue(), 
							sheet.getRow(i).getCell(2).getStringCellValue());
				}
				listAcc_FB.add(accFB);
			}
		}
		closeExcel();
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