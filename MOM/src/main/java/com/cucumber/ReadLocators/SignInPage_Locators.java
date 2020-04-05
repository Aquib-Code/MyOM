package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;

public class SignInPage_Locators extends LoggerClass{
	Map <String, String> LocatorData;
	private WebDriver driver;
	Map<String,String> testData;
	public static String username="";
	public static String password="";
	public static String loginBtn="";
	public static String keepMeLoggedIn="";
	public static String forgetPassword="";
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		log.error(locatorFile);	
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			username =LocatorData.get("username");
			password =LocatorData.get("password");
			loginBtn =LocatorData.get("loginBtn");
			keepMeLoggedIn =LocatorData.get("keepMeLoggedIn");
			forgetPassword =LocatorData.get("forgetPassword");
			log.error("values are"+toString());
		}
		catch (Exception e) {			log.error(e.getMessage());			log.error("File Not Found "+locatorFile);		}
	}
	@Override
	public String toString() {
		return "SigninLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
