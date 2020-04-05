package com.cucumber.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.ParseException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cucumber.utility.BrowserFactory;
import com.cucumber.utility.ExcelReade;
import com.cucumber.utility.LoggerClass;
import com.cucumber.utility.PropertiesReader;
import com.vimalselvam.cucumber.listener.Reporter;
import com.cucumber.businesslogic.Accessors;
import com.cucumber.businesslogic.CommonMethod;
//import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.android.AndroidDriver;

public class ParentStep extends PropertiesReader{

	CommonMethod common = new CommonMethod();	
	Accessors accessors = new Accessors();
	public static Logger log = Logger.getLogger(LoggerClass.class);
	static Properties currProp;
	public static Properties prop;
	private static String execId;
	public static String executionId = execId;
	public static String TCList;
	public static String environment;
	public static File  reportDir;
	public static int count=0;
	public static String PrevTCID = "";
	public static List<String> TestSuite = new ArrayList<>();
	public ParentStep() {
		prop = new Properties();
//		environment="QA";
		log.error("Environment_"+environment);
		prop = PropertiesReader.loadProperties("Environment_"+environment);			
	}
	@Before
	public static Properties loadEnvironment() {
		log.error(environment);
		currProp = new Properties();
		log.error(	currProp);
		return PropertiesReader.loadProperties("Environment_"+environment);	
	}
	
	//@Before
	@Before("@Counter")
	public void initialSetup(Scenario scenario) {
		int i = 0;
		//log.error("Scenario Name  :  "+scenario.getId()+scenario.getName());
		for(String tag : scenario.getSourceTagNames()){
			// TCList = TCList + "," + tag.substring(0);
			System.setProperty("TestID", tag.substring(1));
			accessors.setTestCaseName(tag.substring(1));
	           if(i==0) {
	        	   break;
	           }
	           i++;
	       }
		
		
		//initialize iteration counter	
		//System.setProperty("iteration",""+ count+"");
		countScenarioOutlineRows();
		int iterLog=0;
		iterLog = count + 1;
		log.info("##########################################################################################################");
		log.info("Scenario Name : " + scenario.getName());
		log.info("Test Case Id : " + accessors.getTestCaseName());   //.getProperty("TestID") );
		log.info("Iteration : " + iterLog );
		log.info("##########################################################################################################");
		Reporter.addScenarioLog("Scenario Name : " + scenario.getName());
		Reporter.addScenarioLog("Test Case Id : " + accessors.getTestCaseName());
		Reporter.addScenarioLog("Iteration : " + iterLog);
		
		//initialize step number
		accessors.setParentStepNo(1);
	}
	

	//@Before("@Counter") 
	public void countScenarioOutlineRows() {	
		//set iteration counter
		if(System.getProperty("PrevTCID")==null) System.setProperty("PrevTCID", PrevTCID);
		if (System.getProperty("PrevTCID").equals(accessors.getTestCaseName())) {
			count++;
			//System.setProperty("iteration",""+ count+"");.
			accessors.setIterationId(count);
		}
	    else{
	    	count = 0;
	    	//initialize iteration counter	       	
	    	accessors.setIterationId(count);
		}
		//log.error("Iteration : "+System.getProperty("iteration"));
	}
	public void  saveVideo(String encodedString)
	{
		 byte[] decodedBytes = Base64.decodeBase64(encodedString);
	        try {
	            FileOutputStream out = new FileOutputStream(reportDir + "/execution.mp4");
	            out.write(decodedBytes);
	            out.close();
	        } catch (Exception e) {
	            // TODO: handle exception
	            Log.info("Error", e.toString());
	        }
	}
	@After
	public void cleanup(Scenario scenario) {

		saveVideo(((AndroidDriver<WebElement>)BrowserFactory.driver).stopRecordingScreen());
		common.closeBrowser();
		System.setProperty("PrevTCID", accessors.getTestCaseName());
		log.info("Scenario Status : " + (scenario.getStatus()).toUpperCase());
		accessors.setStatus((scenario.getStatus()).toUpperCase());
		log.info("##########################################################################################################");
		TestSuite.add(accessors.getApplicationName()+","+accessors.getReleaseNumber()+","+accessors.getReqirementId()+","+accessors.getTestCaseName()+","+accessors.getIteration()+","+accessors.getStepId()+","+accessors.getFunctionalPointId()+","+accessors.getStatus());
	}
	public static String getProperty(String string) {
		// TODO Auto-generated method stub
		return prop.getProperty(string);
	}

	public static void generateUniqueID(String strDataFile, String strDataSheet) throws ParseException, IOException, InvalidFormatException{
		String temp = ExcelReade.readDataFromSheet("TestData" ,"Client Details", System.getProperty("TestID")).get("CD.DOB") + ";" + ExcelReade.readDataFromSheet("TestData","Client Details",System.getProperty("TestID")).get("CD.Gender") + ";SA;8;2";
        String a[]=temp.split(";");
        String b[]= new String[10];
        String id="";
        Random rand = new Random();
        String IDContent= "";
        for(int i=0; i<a.length ;i++)
        {
                  if(i==0){
                      b=a[i].split("/");
                      IDContent=b[2].substring(2,4) + b[1] + b[0];
                      id=id+IDContent;
                  }else if(i==1){
                          if(a[i].equalsIgnoreCase("male")){
                          int n=rand.nextInt(5000) + 5000;
                          IDContent=String.format("%04d", n);
                         }else if(a[i].equalsIgnoreCase("female")){
                            int n=rand.nextInt(5000);
                            IDContent=String.format("%04d", n);
                            }
                            id=id+IDContent;
                  }else if(i==2){
                      if(a[i].equalsIgnoreCase("SA")){
                        IDContent="0";
                      }else if(a[i].equalsIgnoreCase("Other")){
                          IDContent="1";
                      }
                      id=id+IDContent;
                  }else if(i==3){
                      id=id+"8";
                  }else if(i==4){
                      IDContent=CommonMethod.GenerateChecksumLuhnAlgorithm(id);
                      id=id + IDContent;
                  }
                      
        }
       ExcelReade.writeIDToSheet(System.getProperty("TestID"), id, "UniqueIDnumbers");
       System.setProperty("ID", id);
      
	}
	
	
}
