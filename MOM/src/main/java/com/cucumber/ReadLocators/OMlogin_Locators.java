package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;

public class OMlogin_Locators extends LoggerClass{
	Map <String, String> LocatorData;
	//Map<WebElement, WebElement> webElementData;
	public static String loginBtn ;	
	public static String userName ;
	public static String password;
	public static String loginAfterDetails;
	public static String hamBurgerBtn;
	public static WebElement ham1;
	public static String logoutBtn;
	public static String solutionForAll;
	public static String card_saveandinvest;
	public static String card_bank;
	public static String card_insure;
	public static String card_rewards;
	public static String bankDropDown;
	public static String moneyAccount;
	public static String callMeBackBtn;
	public static String confirmBtn;
	public static String doneBtn;
	public static String btn_biometrics_no;
	public static String ratingApp;

	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		log.error(locatorFile);	
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			loginBtn =LocatorData.get("loginBtn");
			userName =LocatorData.get("userName");
			password =LocatorData.get("password");
			loginAfterDetails =LocatorData.get("loginAfterDetails");
			hamBurgerBtn =LocatorData.get("hamburgerBtn");
			//ham1=webElementData.get(hamBurgerBtn);
			logoutBtn =LocatorData.get("logoutBtn");
			solutionForAll =LocatorData.get("solutionForAll");
			bankDropDown =LocatorData.get("bankDropDown");
			moneyAccount =LocatorData.get("moneyAccount");
			callMeBackBtn =LocatorData.get("callMeBack");
			confirmBtn =LocatorData.get("confirmBtn");
			btn_biometrics_no =LocatorData.get("btn_biometrics_no");
			card_saveandinvest=LocatorData.get("card_saveandinvest");  
			card_bank=LocatorData.get("card_bank");           
			card_insure=LocatorData.get("card_insure");         
			card_rewards=LocatorData.get("card_rewards");       
			doneBtn=LocatorData.get("doneBtn");
			ratingApp=LocatorData.get("ratingApp");
			log.error("values are"+toString());
		}
		catch (Exception e) {			log.error(e.getMessage());			log.error("File Not Found "+locatorFile);		}
	}
	@Override
	public String toString() {
		return "GoogleLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}

