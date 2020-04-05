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
import com.cucumber.ReadLocators.OMproducts_Locators;
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


public class ProductDetailsPage extends CommonMethod{
	OMproducts_Locators locators;
	OMlogin_Locators locators1;
	Map<String, String> testData;
	OMlogin_Locators locators2;
	Accessors accessors;
	SoftAssert softassert=new SoftAssert();


	public ProductDetailsPage(AppiumDriver driver, String strDataFile, String strDataSheet, String testCaseId) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("strDataFile\t" + strDataFile); //System.getProperty("TestID");
		accessors=new Accessors();
		testData = ExcelReade.readDataFromSheet(strDataFile, strDataSheet, testCaseId);
		System.out.println("testData:\t" + testData);
		locators=new OMproducts_Locators();		
	}
	
	public void availableBalance() {
		//if(testData.get("Available balance")!=null)
		//{
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.available_balance)));
		String expectedBalance=testData.get("Available balance");
		System.out.println("Actual available Balance is"+ "-"+actualBalance);
		System.out.println("Expectedavailable Balance is"+ "-"+expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);		
		
	}//}
	
	public void totalInvestments() {
		if(testData.get("TotalInvestments")!=null)
		{
		String actualInvestment=getText(getWebElementUsingBy(getElement(locators.total_investments)));
		String expectedInvestments=testData.get("TotalInvestments");
		System.out.println("Actual total investments is"+ "-"+actualInvestment);
		System.out.println("Expected total investments is"+ "-"+expectedInvestments);
		softassert.assertEquals(actualInvestment, expectedInvestments);	
		}
		else
		{
			System.out.println("There is no investment for this user");
		}
		
	}
	
	public void totalCover() {
		if(testData.get("TotalCover")!=null)
		{
		String actualInvestment=getText(getWebElementUsingBy(getElement(locators.total_cover)));
		String expectedInvestments=testData.get("TotalCover");
		System.out.println("Actual total cover is"+ "-"+actualInvestment);
		System.out.println("Expected total cover is"+ "-"+expectedInvestments);
		softassert.assertEquals(actualInvestment, expectedInvestments);			
	}
		else {
			System.out.println("There is no investment for this user");
		}
			}
	
	public void bankProductDetails() {
		bankCard();
		bankBalance();
		productsInBank();
		availableBalanceInBankAccounts();
		backButton();
	}
	public void productDetails() 
	{	
		if(testData.get("UserName").equalsIgnoreCase("richard@oldmutual.com"))
		{
			  insureCard(); 
			  sleep(3);
			  insureBalance();
			  insureShortTermBalance();
			  shortTermVehicleCoverCard();
			  vcCoverAmount();
			  vcMonthlyPremium();
			  vcProduct1();
			  backButton();
			  
			  lifeInsurance_GreenlightCard();
			  sleep(3);
				//WebElement wb=driver.findElement(By.id(locators.page_error));
			  if(driver.findElements(By.id("com.oldmutual.myoldmutual.qa:id/message"))!=null)
			  {
				  String ErrorMessage=testData.get("PageErrorMessage");
				  softassert.assertFalse(true, ErrorMessage);
				  System.out.println("Page failed to load and error message is :"+ ErrorMessage);
				  backButton();
			  }
			  else
			  {
			  greenlightCoverAmount();
			  greenlightMonthlyPremium();
			  greenlightProduct1();
			  backButton();
			  }
			  backButton();	
			  
			  saveAndInvestCard();
			  saveAndInvestBalance();
			  saveAndIvestMonthlyContri();
			  siMaxInvestmentCard();
			  SIMaxInvestmentValue();
			  SIMaxInvestMonthlyContri();
			  SIMaxInvestProductDescription();
			  backButton();
			  siOmInvestCard();
			  siOmInvestMonthlyContri();
			  backButton();
			  backButton();
			}
		else if(testData.get("UserName").equalsIgnoreCase("leigh@oldmutual.com"))
		{
			insureCard(); 
			  sleep(3);
			  insureBalance();
			  insureShortTermBalance();
			  shortTermAllsureCoverCard();
			  allsureCoverAmount();
			  allsureMonthlyPremium();
			  backButton();
			  shortTermVehicleCoverCard();
			  vcCoverAmount();
			  vcMonthlyPremium();
			  vcProduct1();
			  backButton();
			  
				  scrollTillText("Greenlight");
				  lifeInsurance_GreenlightCard();
				  sleep(3);
					//WebElement wb=driver.findElement(By.id(locators.page_error));
				  if(driver.findElements(By.id("com.oldmutual.myoldmutual.qa:id/message"))!=null)
				  {
					  String ErrorMessage=testData.get("PageErrorMessage");
					  softassert.assertFalse(true, ErrorMessage);
					  System.out.println("Page failed to load and error message is :"+ ErrorMessage);
					  backButton();
				  }
				  else
				  {
				  greenlightCoverAmount();
				  greenlightMonthlyPremium();
				  greenlightProduct1();
				  backButton();
				  }
			  
			  backButton();
			  saveAndInvestCard();
			  saveAndInvestBalance();
			  saveAndIvestMonthlyContri();
			  siMaxInvestmentCard();
			  SIMaxInvestmentValue();
			  SIMaxInvestMonthlyContri();
			  SIMaxInvestProductDescription();
			  backButton();
			  siOmInvestCard();
			  siOmInvestMonthlyContri();
			  backButton();
			  backButton();
		}
	}
	//Bank
	public void bankCard() 
	{		
		clickOnElement(getWebElementUsingBy(getElement(locators.card_bank)));		  
		  }		  
	
	public void bankBalance() {
		sleep(3);
		String actualBankBalance=getText(getWebElementUsingBy(getElement(locators.bank_balance)));
		String expectedBankBalance=testData.get("Bank Balance");
		System.out.println("Actual Total available balance in Bank is"+ "-"+actualBankBalance);
		System.out.println("Expected Total available balance in Bank is"+ "-"+expectedBankBalance);
		softassert.assertEquals(actualBankBalance, expectedBankBalance);
	}
		
		public  void backButton() {
		clickOnElement(getWebElementUsingBy(getElement(locators.backButton)));
		sleep(3);
	}
	
	public void productsInBank() {
		String actualBankAcc1=getText(getWebElementUsingBy(getElement(locators.bank_SwipeAccount)));
		String expectedBankAcc1=testData.get("BankSwipeAccount");
		System.out.println("Actual Bank account 1 is"+ "-"+actualBankAcc1);
		System.out.println("Expected Bank account 1 is"+ "-"+expectedBankAcc1);
		softassert.assertEquals(actualBankAcc1, expectedBankAcc1);
		String actualBankAcc2=getText(getWebElementUsingBy(getElement(locators.bank_SaveAccount)));
		String expectedBankAcc2=testData.get("BankSaveAccount");
		System.out.println("Actual Bank account 2 is"+ "-"+actualBankAcc2);
		System.out.println("Expected Bank account 2 is"+ "-"+expectedBankAcc2);
		softassert.assertEquals(actualBankAcc2, expectedBankAcc2);				
		
	}
	public void availableBalanceInBankAccounts() {
		//clickOnElement(getWebElementUsingBy(getElement(locators.card_bank)));
		String actualSwipeAccountBalance=getText(getWebElementUsingBy(getElement(locators.bank_SwipeAccountBalance)));
		String expectedSwipeAccountBalance=testData.get("BankSwipeAccountBalance");
		System.out.println("Actual balance in Swipe account is"+ "-"+actualSwipeAccountBalance);
		System.out.println("Expected balance in Swipe account is"+ "-"+expectedSwipeAccountBalance);
		softassert.assertEquals(actualSwipeAccountBalance, expectedSwipeAccountBalance);
		String actualSaveAccountBalance=getText(getWebElementUsingBy(getElement(locators.bank_SaveAccountBalance)));
		String expectedSaveAccountBalance=testData.get("BankSaveAccountBalance");
		System.out.println("Actual balance in Save account is"+ "-"+actualSaveAccountBalance);
		System.out.println("Expected balance in Save account is"+ "-"+expectedSaveAccountBalance);
		softassert.assertEquals(actualSaveAccountBalance, expectedSaveAccountBalance);			
		
	}
	
	//Save AND Invest	
	public void saveAndInvestCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.card_saveandinvest)));		
		}		
	
	public void saveAndInvestBalance() {
		String actualProductInvestBal=getText(getWebElementUsingBy(getElement(locators.bank_balance)));
		String expectedProductInvestBal=testData.get("SItotalInvestment");
		System.out.println("Actual Save and Invest Total investment is :"+ actualProductInvestBal);
		System.out.println("Expected Save and Invest Total investment is :"+ expectedProductInvestBal);
		softassert.assertEquals(actualProductInvestBal, expectedProductInvestBal);
	}
	public void saveAndIvestMonthlyContri() {
		String actualInsureBalance=getText(getWebElementUsingBy(getElement(locators.insure_short_term_balance)));
		String expectedInsureBalance=testData.get("SItotalMonthlyContrib");
		System.out.println("Actual save and invest monthly contribution is :"+ actualInsureBalance);
		System.out.println("Expected save and invest monthly contribution is :"+ expectedInsureBalance);
		softassert.assertEquals(actualInsureBalance, expectedInsureBalance);
	}
	public void siMaxInvestmentCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.si_MaxInvestmentNeeds)));
	}
	public void SIMaxInvestmentValue() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_coverAmount)));
		String expectedBalance=testData.get("SIMaxInvestmentValue");
		System.out.println("Actual Max Investment, investment value is :"+ actualBalance);
		System.out.println("Expected Max Investment, investment value is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void SIMaxInvestMonthlyContri() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_monthlyPremium)));
		String expectedBalance=testData.get("SIMaxInvestMonthlyContri");
		System.out.println("Actual Max Investment monthly premium is :"+ actualBalance);
		System.out.println("Expected  Max Investment monthly premium is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void SIMaxInvestProductDescription() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.si_OmMaxInvestProductDesc)));
		String expectedBalance=testData.get("SIMaxInvestProDescription");
		System.out.println("Actual Max Invest product description is :"+ actualBalance);
		System.out.println("Expected Max Invest product description is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	
	public void siOmInvestCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.si_OmInvestNeeds)));
	}
	
	public void siOmInvestMonthlyContri() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_monthlyPremium)));
		String expectedBalance=testData.get("SI_OmInvestMonthlyContri");
		System.out.println("Actual OM Investment monthly premium is :"+ actualBalance);
		System.out.println("Expected  OM Investment monthly premium is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	
	
	//INSURE CARD
	public void insureCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.card_insure)));
	}
	public void insureBalance() {
		String actualInsureBalance=getText(getWebElementUsingBy(getElement(locators.insure_balance)));
		String expectedInsureBalance=testData.get("Insure Balance");
		System.out.println("Actual insure balance is :"+ actualInsureBalance);
		System.out.println("Expected insure balance is :"+ expectedInsureBalance);
		softassert.assertEquals(actualInsureBalance, expectedInsureBalance);
	}
	public void insureShortTermBalance() {
		String actualInsureBalance=getText(getWebElementUsingBy(getElement(locators.insure_short_term_balance)));
		String expectedInsureBalance=testData.get("InsureShortTermBalance");
		System.out.println("Actual short term insure balance is :"+ actualInsureBalance);
		System.out.println("Expected short term insure balance is :"+ expectedInsureBalance);
		softassert.assertEquals(actualInsureBalance, expectedInsureBalance);
	}
	public void shortTermVehicleCoverCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.si_VehicleCover)));
	}
	public void vcCoverAmount() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_coverAmount)));
		String expectedBalance=testData.get("StVehicleCoverAmo");
		System.out.println("Actual vehicleCover cover amount is :"+ actualBalance);
		System.out.println("Expected vehicleCover cover amount is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void vcMonthlyPremium() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_monthlyPremium)));
		String expectedBalance=testData.get("StVehicleMonthlyPremium");
		System.out.println("Actual vehicleCover monthly premium is :"+ actualBalance);
		System.out.println("Expected vehicleCover monthly premium is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void vcProduct1() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_product1)));
		String expectedBalance=testData.get("StVehicleProduct1");
		System.out.println("Actual vehicleCover product one is :"+ actualBalance);
		System.out.println("Expected vehicleCover product one is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void shortTermAllsureCoverCard() {
		clickOnElement(getWebElementUsingBy(getElement(locators.si_Allsure)));
	}
	public void allsureCoverAmount() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_coverAmount)));
		String expectedBalance=testData.get("StAllsureCoverAmo");
		System.out.println("Actual Allsure cover amount is :"+ actualBalance);
		System.out.println("Expected Allsure cover amount is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void allsureMonthlyPremium() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_monthlyPremium)));
		String expectedBalance=testData.get("StAllsureMonthlyPremium");
		System.out.println("Actual Allsure monthly premium is :"+ actualBalance);
		System.out.println("Expected Allsure monthly premium is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}

	/*
	 * public void vcProduct1() { String
	 * actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_product1)))
	 * ; String expectedBalance=testData.get("STInsureNeeds1Product1");
	 * System.out.println("Actual vehicleCover product one is :"+ actualBalance);
	 * System.out.println("Expected vehicleCover product one is :"+
	 * expectedBalance); softassert.assertEquals(actualBalance, expectedBalance); }
	 */
	public void lifeInsurance_GreenlightCard() {
		try
		{
			clickOnElement(getWebElementUsingBy(getElement(locators.lifeinsurance_greenlight)));
			
		}
		catch (Throwable e) {
			backButton();
		}
		
	}
	public void greenlightCoverAmount() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_coverAmount)));
		String expectedBalance=testData.get("Greenlight_CoverAmount");
		System.out.println("Actual greenlight cover amount is :"+ actualBalance);
		System.out.println("Expected greenlight cover amount is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void greenlightMonthlyPremium() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_monthlyPremium)));
		String expectedBalance=testData.get("Greenlight_MonthlyPremium");
		System.out.println("Actual greenlight monthly premium is :"+ actualBalance);
		System.out.println("Expected greenlight monthly premium is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void greenlightProduct1() {
		String actualBalance=getText(getWebElementUsingBy(getElement(locators.vc_product1)));
		String expectedBalance=testData.get("Greenlight_ProductName");
		System.out.println("Actual greenlight product one is :"+ actualBalance);
		System.out.println("Expected greenlight product one is :"+ expectedBalance);
		softassert.assertEquals(actualBalance, expectedBalance);
	}
	public void logout() {
		locators1=new OMlogin_Locators();
		
		System.out.println(locators );
		
		clickOnElement(getWebElementUsingBy(getElement(locators1.hamBurgerBtn)));
		clickOnElement(getWebElementUsingBy(getElement(locators1.logoutBtn)));
		softassert.assertAll();
	}
	}

