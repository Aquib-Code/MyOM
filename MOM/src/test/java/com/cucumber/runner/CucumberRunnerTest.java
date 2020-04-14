package com.cucumber.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.cucumber.steps.ParentStep;
import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.android.AndroidDriver;

import com.cucumber.businesslogic.ProjectAccessors;
import com.cucumber.utility.BrowserFactory;
//import com.cucumber.utility.FileOutputStream;
import com.cucumber.utility.LoggerClass;
@SuppressWarnings("unused")
@CucumberOptions(
		//tags={ "@Version1" },
		features = "src/test/resources/tempFeatures", glue = { "com.cucumber.steps" }, plugin = {
				"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:" }, strict = true, dryRun = false, monochrome = true)

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
	
	//static ExtentProperties extentProperties;
	ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	public static Properties testProperties;
	public static String Tag;
	ProjectAccessors pAccessor = new ProjectAccessors();
	public static File reportDir ;

	@AfterClass
	public void writeExtentReport() throws IOException {
		
		Reporter.loadXMLConfig(new File("src/test/resources/extentReportConfig/extentConfig.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File((System.getProperty("user.dir") + "/src/test/resources/propertiesFile/maven.properties"))));
		Reporter.setSystemInfo("Device Name", properties.getProperty(BrowserFactory.driver.getCapabilities().getCapability("deviceName").toString()));
		Reporter.setSystemInfo("OS Version", properties.getProperty("maven.compiler.version"));
		Reporter.setSystemInfo("Maven", properties.getProperty("maven.compiler.version"));
		Reporter.setSystemInfo("Java Version", properties.getProperty("java.version"));
		Reporter.setSystemInfo("Selenium Version", properties.getProperty("selenium.version"));
		Reporter.setSystemInfo("Cucumber Version", properties.getProperty("cucumber.version"));
		
	}
	@BeforeClass
	public void setup() {
		  System.setOut(new java.io.PrintStream(System.out) {
	            private StackTraceElement getCallSite() {
	                for (StackTraceElement e : Thread.currentThread()
	                        .getStackTrace())
	                    if (!e.getMethodName().equals("getStackTrace")
	                            && !e.getClassName().equals(getClass().getName()))
	                        return e;
	                return null;
	            }

	            @Override
	            public void println(String s) {
	                println((Object) s);
	            }

	            @Override
	            public void println(Object o) {
	                StackTraceElement e = getCallSite();
	                String callSite = e == null ? "??" :
	                    String.format("%s.%s(%s:%d)",
	                                  e.getClassName(),
	                                  e.getMethodName(),
	                                  e.getFileName(),
	                                  e.getLineNumber());
	                super.println(o + "\t\tat " + callSite);
	            }
	        });

	        System.out.println("Hello world");
//	        printStuff();
	    
	Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\CurrentExecution.properties")));
		} catch (FileNotFoundException e) {
			System.out.println("CurrentExecution.properties file is not found. " + e.getMessage());
//			LoggerClass.log(e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception in file handling. " + e.getMessage());
//			LoggerClass.log(e.getMessage());
		}

		ParentStep.environment = properties.getProperty("Environment");
		//System.out.println("Environment  " + properties.getProperty("Environment"));
		
		System.setProperty("ResourcesBaseFolder", properties.getProperty("ResourcesBaseFolder"));
		System.setProperty("FeaturesLocation", properties.getProperty("FeaturesLocation"));
		System.setProperty("LocatorsBaseFolder", properties.getProperty("LocatorsBaseFolder"));
		System.setProperty("TestDataFolder", properties.getProperty("TestDataFolder"));
		System.setProperty("EnvironmentProps", properties.getProperty("EnvironmentProps"));
		String sTimeStamp = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
		//String sReportPath = System.getProperty("user.dir")+"\\src\\TestReports\\Run_"+ sTimeStamp;
		String sReportPath = properties.getProperty("ReportBaseFolder")+"\\Run_"+ sTimeStamp;
		reportDir = new File(sReportPath);
		ParentStep.reportDir=reportDir;
		System.setProperty("ReportPath", sReportPath);
		System.setProperty("logfile.name",sReportPath + "\\Logger.log");
		System.out.println("System."+System.getProperty("logfile.name"));
		if(!reportDir.exists()) {
		}
		pAccessor.setApplicationName(properties.getProperty("ApplicationName"));
		pAccessor.setRelease(properties.getProperty("Release"));
		extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(reportDir + "\\Run_" + sTimeStamp + ".html");
		extentProperties.setProjectName(pAccessor.getApplicationName());
		new ParentStep();
		try {
			BrowserFactory.openBrowser(ParentStep.prop);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}