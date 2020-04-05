package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;
public class MOMHome_Locators extends LoggerClass{
	Map <String, String> LocatorData;
	private WebDriver driver;
	Map<String,String> testData;
	public static String logout="";
	public static String menu="";
	public static String primaryValue="";
	public static String cardTitle   ="";
	public static String cardAmount  ="";
	public static String backBtn  ="";
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		log.error(locatorFile);	
		ExcelReade readData= new ExcelReade();
		try {	
			System.out.println(readData);
			System.out.println(locatorFile);
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			primaryValue =LocatorData.get("primaryValue");
			System.out.println("LocatorData t:\t"+LocatorData );
			System.out.println("primaryValue\t:\t"+primaryValue);
					cardTitle    =LocatorData.get("cardTitle"); 
			cardAmount   =LocatorData.get("cardAmount");
			backBtn		 =LocatorData.get("backBtn");
			logout       =LocatorData.get("logout");
			menu		 =LocatorData.get("menu");
			log.error("values are "+toString());
			//com.oldmutual.myoldmutual:id/hamburger_button
//			com.oldmutual.myoldmutual:id/logOut
		}
		catch (Exception e) {			log.error(e.getMessage());		e.printStackTrace();	log.error("File Not Found "+locatorFile);		}
	}
	@Override
	public String toString() {
//		System.out.println("primaryValue\t:\t"+primaryValue);
		return " [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}