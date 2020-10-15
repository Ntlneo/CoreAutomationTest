package DataManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
//	String excelFilePath = "Facebook/DataAutoFB.xlsx";	
//	String sheetFB = "FB";	
	
	ArrayList<FaceBookAccountModel> listAccount=new ArrayList();
	
	public ArrayList<FaceBookAccountModel> getListAccount() {
		return listAccount;
	}

	Workbook wb;
	String sheetName="";

	public ExcelManager(String pathToExcelFile, String _sheetName) {
		try {
			sheetName=_sheetName;
			String userPath = System.getProperty("user.dir");
			File excelFile = new File(userPath + "/" + pathToExcelFile);
			wb = new XSSFWorkbook(excelFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// ****************************************************
	// order begins from 1	
	
	
	// ****************************************************
	public void readDataFromExcel() {
		try
		{
			clearOldDataOfList();
			for (Sheet sheet : wb) 
			{
				if (sheet.getSheetName().equalsIgnoreCase(sheetName)) 
				{
					int numberOfRow=sheet.getLastRowNum();
					for (int i = 1; i <= numberOfRow; i++) {
						
						Row row=sheet.getRow(i);
						Cell cellUsername=row.getCell(1);
						Cell cellPassword=row.getCell(2);
						Cell cellUUID=row.getCell(3);
						Cell cell2FA=row.getCell(4);
						Cell cellbackup=row.getCell(5);
						
						
						String username=getCellDataString(cellUsername);
						String password=getCellDataString(cellPassword);
						String uuid=getCellDataString(cellUUID);
						String two_fa=getCellDataString(cell2FA);
						String backup=getCellDataString(cellbackup);
						listAccount.add(new FaceBookAccountModel(username,password,uuid,two_fa,backup));		
					}				
				}
			}
			for(  FaceBookAccountModel acc  : listAccount)
			{
			//	System.out.println(acc.username+"  "+acc.password+"   "+acc.uuid+"  "+acc.two_fa+"\n\n");
			}
		}
		catch(Exception e)
		{
			e.toString();
		}
		
		closeExcel();
	}
	
	
	public FaceBookAccountModel getfacebookAccount(int index)
	{
		return listAccount.get(index);
	}
	public String getCellDataString(Cell cell)
	{
		String ret="";
		if(null!=cell)
		{
			CellType celltype=cell.getCellType();
		    switch (celltype) {
		        case BOOLEAN:
		            //System.out.println(cell.getBooleanCellValue());
		            break;
		        case NUMERIC:
		        	ret=NumberToTextConverter.toText(cell.getNumericCellValue());
		        
		            //System.out.println(ret);
		            	           
		            break;
		        case STRING:
		            //System.out.println(cell.getRichStringCellValue());
		            ret=cell.getStringCellValue();
		            break;
		    }
		}
		
		
		
	
		return ret;
	}
	
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clearOldDataOfList() {
		listAccount.clear();
	}
}