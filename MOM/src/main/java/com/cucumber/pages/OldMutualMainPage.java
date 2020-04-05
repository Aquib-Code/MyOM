package com.cucumber.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import com.cucumber.ReadLocators.OMAPP_Locators;
import com.cucumber.ReadLocators.OMlogin_Locators;
import com.cucumber.businesslogic.Accessors;
import com.cucumber.businesslogic.CommonMethod;
//import com.cucumber.listener.Reporter;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.ExcelReade;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.internal.ResponseSpecificationImpl.HamcrestAssertionClosure;


public class OldMutualMainPage extends CommonMethod{
	OMlogin_Locators locators;
	Map<String, String> testData;
	Accessors accessors;
	SoftAssert softassert=new SoftAssert();

	  public OldMutualMainPage(AppiumDriver driver, String strDataFile, String  strDataSheet, String testCaseId) throws IOException 
	  { 
		  this.driver = driver;
	  PageFactory.initElements(driver, this); 
	  //System.out.println("strDataFile\t"+ strDataFile); //System.getProperty("testCaseId");
	  accessors=new Accessors(); 
	  testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, testCaseId);
	  System.out.println("testData:\t" + testData); 
	  locators = new OMlogin_Locators(); 
	  }
		 
	public void login() {
		locators=new OMlogin_Locators();
		System.out.println(locators );
		clickOnElement(getWebElementUsingBy(getElement(locators.loginBtn)));
		//setTextMobile(locators.userName, "richard@oldmutual.com");
		//setTextMobile(locators.password, "abc123");
		setTextMobile(locators.userName, testData.get("UserName"));
		setTextMobile(locators.password, testData.get("Password"));	
		clickOnElement(getWebElementUsingBy(getElement(locators.loginAfterDetails)));
		//waitForElementToAppear(locators.ham1);
		sleep(10);
		//dismissBiometric();
	}
	public void logout() {
		locators=new OMlogin_Locators();
		
		System.out.println(locators );
		
		clickOnElement(getWebElementUsingBy(getElement(locators.hamBurgerBtn)));
		clickOnElement(getWebElementUsingBy(getElement(locators.logoutBtn)));
		softassert.assertAll();
	}
	public void feedback() {
		locators=new OMlogin_Locators();
		System.out.println(locators );
		setStarRating(locators.ratingApp,5);
		clickOnElement(getWebElementUsingBy(getElement(locators.doneBtn)));
	}
	public void productsInSolutionForYou() {
		locators=new OMlogin_Locators();
		System.out.println(locators );
		sleep(2);
		swipeByElements(locators.card_saveandinvest ,locators.card_bank);
		swipeByElements(locators.card_rewards,locators.card_insure);
		
//		clickOnElement(getWebElementUsingBy(getElement(locators.solutionForAll)));
//		moveTo(locators.solutionForAll);
//		userLeftclick(locators.solutionForAll);
//		touch(locators.solutionForAll);
		touchpoints(500,1539);
		clickOnElement(getWebElementUsingBy(getElement(locators.bankDropDown)));
		clickOnElement(getWebElementUsingBy(getElement(locators.moneyAccount)));
	}
	public void callMeBackOption() {
		locators=new OMlogin_Locators();
		System.out.println(locators );
		clickOnElement(getWebElementUsingBy(getElement(locators.callMeBackBtn)));
		clickOnElement(getWebElementUsingBy(getElement(locators.confirmBtn)));
		clickOnElement(getWebElementUsingBy(getElement(locators.doneBtn)));
		
	}
	
	  public void dismissBiometric() 
	  { 
		  //clickIfExists(locators.btn_biometrics_no);
		  userLeftclick(locators.btn_biometrics_no); 
		  }
	 
}
