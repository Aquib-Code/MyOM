package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;
public class OMproducts_Locators extends LoggerClass{
	Map <String, String> LocatorData;
	//Map<WebElement, WebElement> webElementData;
	
	public static String solutionForAll;
	public static String card_saveandinvest;
	public static String card_bank;
	public static String card_insure;
	public static String card_rewards;
	public static String bankDropDown;
	public static String moneyAccount;
	public static String callMeBackBtn;
	public static String bank_SwipeAccount;
	public static String bank_SaveAccount;
	public static String bank_SwipeAccountBalance;
	public static String bank_SaveAccountBalance;
	public static String backButton;
	public static String available_balance;
	public static String total_investments;
	public static String total_cover;
	public static String bank_balance;
	
	public static String insure_balance;
	public static String insure_short_term_balance;
	public static String si_VehicleCover;
	public static String vc_coverAmount;
	public static String vc_monthlyPremium;
	public static String vc_product1;
	public static String si_Allsure;
	public static String lifeinsurance_greenlight;
	
	public static String insure_product1;
	public static String insure_product2;
	
	public static String si_MaxInvestmentNeeds;
	public static String si_OmMaxInvestProductDesc;
	public static String si_OmInvestNeeds;
	
	public static String page_error;
	

	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		log.error(locatorFile);	
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			solutionForAll =LocatorData.get("solutionForAll");
			bankDropDown =LocatorData.get("bankDropDown");
			moneyAccount =LocatorData.get("moneyAccount");
			callMeBackBtn =LocatorData.get("callMeBack");
			bank_SwipeAccount =LocatorData.get("bank_SwipeAccount");
			bank_SaveAccount =LocatorData.get("bank_SaveAccount");
			bank_SwipeAccountBalance=LocatorData.get("bank_SwipeAccountBalance");
			bank_SaveAccountBalance=LocatorData.get("bank_SaveAccountBalance");
			card_saveandinvest=LocatorData.get("card_saveandinvest");  
			card_bank=LocatorData.get("card_bank");           
			card_insure=LocatorData.get("card_insure");         
			card_rewards=LocatorData.get("card_rewards");       
			available_balance=LocatorData.get("available_balance");
			total_investments=LocatorData.get("total_investments");
			total_cover=LocatorData.get("total_cover");
			backButton=LocatorData.get("back_button");
			bank_balance=LocatorData.get("bank_balance");
			insure_balance=LocatorData.get("insure_balance");
			insure_short_term_balance=LocatorData.get("insure_short_term_balance");
			si_VehicleCover=LocatorData.get("sti_VehicleCover");
			vc_coverAmount=LocatorData.get("vc_coverAmount");
			vc_monthlyPremium=LocatorData.get("vc_monthlyPremium");
			vc_product1=LocatorData.get("vc_product1");
			si_Allsure=LocatorData.get("sti_AllsureCover");
			lifeinsurance_greenlight=LocatorData.get("Lifei_greenlight");
			
			insure_product1=LocatorData.get("insure_product1");
			insure_product2=LocatorData.get("insure_product2");
			
			si_MaxInvestmentNeeds=LocatorData.get("si_MaxInvestmentNeeds");
			si_OmMaxInvestProductDesc=LocatorData.get("si_OmMaxInvestProductDesc");	
			si_OmInvestNeeds=LocatorData.get("si_OmInvestNeeds");
			page_error=LocatorData.get("page_error");
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


	



