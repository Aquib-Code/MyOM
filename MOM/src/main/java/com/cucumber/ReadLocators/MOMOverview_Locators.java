package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;

public class MOMOverview_Locators extends LoggerClass{
	Map <String, String> LocatorData;
	private WebDriver driver;
	Map<String,String> testData;

	public static String primaryValue 	="";
	public static String cardTitle    	="";
	public static String cardAmount   	="";
	public static String nextBtn       ="";
	public static String backBtn       ="";
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		log.error(locatorFile);	
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			primaryValue  =LocatorData.get("primaryValue");
			cardTitle     =LocatorData.get("cardTitle");
			cardAmount    =LocatorData.get("cardAmount");
			nextBtn       =LocatorData.get("nextBtn");
			backBtn       =LocatorData.get("backBtn");
			
			log.error("values are"+toString());
		}
		catch (Exception e) {			log.error(e.getMessage());			log.error("File Not Found "+locatorFile);		}
	}
	
	
	
	
	
	@Override
	public String toString() {
		return " [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
