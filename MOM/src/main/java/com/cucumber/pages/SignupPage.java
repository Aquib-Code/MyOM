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


import com.cucumber.ReadLocators.Signup_Locators;
import com.cucumber.businesslogic.CommonMethod;
//import com.cucumber.listener.Reporter;
import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.ExcelReade;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class SignupPage extends CommonMethod {
	Signup_Locators signup_locators;
	Map<String, String> testData;

	public SignupPage(AppiumDriver driver, String strDataFile, String strDataSheet) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("strDataFile\t" + strDataFile);// System.getProperty("TestID")
		testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, "1");
		System.out.println("testData:\t" + testData);
//		locators = new Dialpad_Locators();
	}
	public SignupPage(AppiumDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		locators = new Dialpad_Locators();
	}
	public void selectCountry()
	{
		clickOnElement(getWebElementUsingBy(getElement(signup_locators.selectCountryDropDown)));
		clickOnElement(getWebElementUsingBy(getElement(signup_locators.country_SA)));
		setTextMobile(signup_locators.inputNumber,"789456123");
		backAndroid();
	
	}
}
