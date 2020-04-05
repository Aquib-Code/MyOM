package com.cucumber.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cucumber.businesslogic.Accessors;

public class ExcelReade extends LoggerClass {

	String data;
	Sheet sheet;
	File file;
	FileInputStream fis;
	Workbook workbook;
	static Accessors accessors = new Accessors();
    public Row row = null;
    
	public String data(int row, int cell) {

		if (sheet.getRow(row).getCell(cell).getCellType() == Cell.CELL_TYPE_STRING) {

			data = sheet.getRow(row).getCell(cell).getStringCellValue();
		} else if (sheet.getRow(row).getCell(cell).getCellType() == Cell.CELL_TYPE_BLANK) {

			data = sheet.getRow(row).getCell(cell).getStringCellValue();
		} else if (sheet.getRow(row).getCell(cell).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double d = sheet.getRow(row).getCell(cell).getNumericCellValue();
			data = d.toString();

		}
		return data;
	}
	public static Map<String, String> readDataFromSheet(String FileName,String sheetname, String testcaseID) throws IOException{
	       //log.error("Data Iteration : "+System.getProperty("iteration"));
	      // FileInputStream fio = new FileInputStream(new File(System.getProperty("ResourcesBaseFolder")+System.getProperty("TestDataFile")));
	       FileInputStream fio = new FileInputStream(new File(System.getProperty("ResourcesBaseFolder")+ System.getProperty("TestDataFolder") +FileName + ".xlsx"));
	      System.out.println((System.getProperty("ResourcesBaseFolder")+ System.getProperty("TestDataFolder") +FileName + ".xlsx"));
	       XSSFWorkbook workbook = new XSSFWorkbook(fio);
	       
	       Map <String, String> book = new HashMap<>();
	       DataFormatter formatter = new DataFormatter();
	       
	    System.out.println(sheetname); 
	       XSSFSheet sheet = workbook.getSheet(sheetname);
	       
	       boolean flag=false;
	       for(int i=0;i<=sheet.getLastRowNum();i++)
	       {
	              String TCId = formatter.formatCellValue(sheet.getRow(i+1).getCell(0));
	              if(TCId.equalsIgnoreCase(testcaseID)) {
	            		//log.error("Row Number before : "+accessors.getIteration());
	            	 
	            	  i = i + accessors.getIteration();//Integer.parseInt(System.getProperty("iteration"));
	            		
	            	  //log.error("Row Number after: "+i);
	              for(int j=1 ; j<=sheet.getRow(0).getLastCellNum() ; j++){
	                    
	                    if (!formatter.formatCellValue(sheet.getRow(i).getCell(j)).equals("")){
	                    	//i = i + Integer.parseInt(System.getProperty("iteration"));
	                    	//log.error("Row Number : "+i);
	                          // book.put(formatter.formatCellValue(sheet.getRow(0).getCell(j)), formatter.formatCellValue(sheet.getRow(i).getCell(j)));
	                    	 book.put(formatter.formatCellValue(sheet.getRow(0).getCell(j)), formatter.formatCellValue(sheet.getRow(i).getCell(j)));
	                    }
	                    else{
	                           flag=true;
	                           break;
	                    }
	              }
	              if(flag){
	                    break;
	              }
	              
	       }
	       }
	    return book;
	    }



	 public Map<String, String>  getLocatorData(String FileName,String sheetName) throws IOException
	    {
		  FileInputStream fio;
		  Map <String, String> book = new HashMap<>();
		try {
			fio = new FileInputStream(new File(FileName));
		
			XSSFWorkbook workbook = new XSSFWorkbook(fio);
	       
	      
	       DataFormatter formatter = new DataFormatter();
	       
	    
	       XSSFSheet sheet = workbook.getSheet(sheetName);
	       
	       boolean flag=false;
	       for(int i=0;i<=sheet.getLastRowNum();i++)
	       {
	              	if (!formatter.formatCellValue(sheet.getRow(i).getCell(1)).equals("")){
	                    	 book.put(formatter.formatCellValue(sheet.getRow(i).getCell(0)), formatter.formatCellValue(sheet.getRow(i).getCell(1)) + "|||" + formatter.formatCellValue(sheet.getRow(i).getCell(2)));
	                    }
	                    else{
	                           flag=true;
	                           break;
	                    }
	              }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return book;
	   

	    }
	
	 
	 public static void writeIDToSheet(String testcase, String text, String FileName) throws IOException, InvalidFormatException {
		 FileInputStream fio = new FileInputStream(new File(System.getProperty("ResourcesBaseFolder")+ System.getProperty("TestDataFolder") +FileName + ".xlsx"));
	        Workbook workbook = WorkbookFactory.create(fio);
	        Sheet sheet = workbook.getSheet("UniqueIDnumbers");
	        int count = sheet.getLastRowNum() + 1;
	        sheet.createRow(1).createCell(0).setCellValue("Customer Created");
	        sheet.getRow(1).createCell(1).setCellValue(text);
	        sheet.getRow(1).createCell(2).setCellValue(testcase);

	        FileOutputStream foo = new FileOutputStream(new File(System.getProperty("ResourcesBaseFolder")+ System.getProperty("TestDataFolder") +FileName + ".xlsx"));
	        workbook.write(foo);
	        foo.close();

	    }
}
