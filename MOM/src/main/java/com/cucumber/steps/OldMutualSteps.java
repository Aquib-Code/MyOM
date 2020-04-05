package com.cucumber.steps;
import java.io.IOException;

import com.cucumber.businesslogic.CommonMethod;

import com.cucumber.pages.OldMutualMainPage;
import com.cucumber.pages.ProductDetailsPage;
import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.LoggerClass;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class OldMutualSteps extends LoggerClass
{
	static CommonMethod commonMethod=new CommonMethod();
	BrowserFactory bf = new BrowserFactory();

	String strDataFile;
	String strDataSheet;
	String testCaseIdD;
	static OldMutualMainPage oldMutualMain; 
	static ProductDetailsPage productDetailsPage;
		
	
	  @Given("^Old Mutual app is launched in mobile device \"([^\"]*)\" & Data Set \"([^\"]*)\" & caseId \"([^\"]*)\"$") 
	  public void old_Mutual_app_is_launched_in_mobile_device_Data_Set(String TestDataFile, String DataSetSheet, String TestCaseId) throws Throwable 
	  {
	  	  strDataFile = TestDataFile; 
		  strDataSheet = DataSetSheet; 
		  testCaseIdD=TestCaseId;
	  System.out.println("strDataFile"+strDataFile);
	  System.out.println("DataSetSheet\t"+DataSetSheet);
	  //ParentStep.generateUniqueID(strDataFile,strDataSheet);
	  commonMethod.takeScreenShot(); }  
	
	 @When("^user log in to OM app$")
      public void When_user_log_in_to_OM_app() throws Throwable{
	  oldMutualMain = new OldMutualMainPage(BrowserFactory.driver, strDataFile, strDataSheet, testCaseIdD);
	  productDetailsPage=new ProductDetailsPage(BrowserFactory.driver, strDataFile, strDataSheet, testCaseIdD);
	  oldMutualMain.login();
}

@And("^user logs out of OM app$")
public void log_out_of_OM_app() throws Throwable{
	productDetailsPage.logout();
}

@And("^user gives feedback to OM app$")
public void When_user_gives_feedback_to_OM_app() throws Throwable{
	oldMutualMain.feedback();
}
@And("^navigates to product under solution for you card$")
public void navigates_to_product() throws Throwable{
	
	oldMutualMain.productsInSolutionForYou();
}

@And("^user provides details for call me back option$")
public void call_me_back() throws Throwable{
	oldMutualMain.callMeBackOption();
}
@And("^verify Available balance of user$")
public void available_balance() throws Throwable{
	productDetailsPage.availableBalance();	
}

@And("^verify Total investments of user$")
public void total_investments() throws Throwable{
	productDetailsPage.totalInvestments();	
}

@And("^verify Total cover of user$")
public void total_cover() throws Throwable{
	productDetailsPage.totalCover();	
}

@And("^verify Bank accounts of user$")
public void bank_account() throws Throwable{
	productDetailsPage.bankProductDetails();
}

@And("^verify Product details of user$")
public void verifyCustomerProductDetails() throws Throwable{
	productDetailsPage.productDetails();
}
				}

