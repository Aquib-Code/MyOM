package com.cucumber.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
//appium
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;

/**
 * This class defines driver initializing activity. This class has methods like
 * open browser and close browser.
 */

public class BrowserFactory extends LoggerClass {
	public static AppiumDriver driver;
	public static WebDriverWait wait;

	public static WebDriver getDriver() {
		return driver;
	}

	public static AndroidDriver getAndroidDriver() {
		return (AndroidDriver) driver;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: openBrowser
	 * @purpose: launch the browser according to the properties value
	 * @param :none
	 * @author kuabhis5
	 * @return: void
	 * @throws InterruptedException
	 * 
	 ***/

	public static void openBrowser(Properties properties) throws InterruptedException {
		int flag = 0;
		log.error("properties\n\n\n" + properties);
		String Browser = properties.getProperty("Browser");
//		String Browser="ELBS";
		String Url = properties.getProperty("URL");
		/*
		 * if (Browser.equalsIgnoreCase("Firefox")) {
		 * System.setProperty("webdriver.geckodriver.driver", a
		 * "src/test/resources/BrowserDriver/geckodriver.exe"); driver = new
		 * FirefoxDriver(); // wait = new WebDriverWait(driver, 20);
		 * 
		 * log.info("Fire Fox driver created"); } else if
		 * (Browser.equalsIgnoreCase("chrome")) {
		 * System.setProperty("webdriver.chrome.driver",
		 * "src/test/resources/BrowserDriver/chromedriver.exe"); driver = new
		 * ChromeDriver(); driver.manage().deleteAllCookies(); //wait = new
		 * WebDriverWait(driver, 20); log.info("Chrome driver created"); //
		 * clearBrowserCache();
		 * 
		 * } else if (Browser.equalsIgnoreCase("IE")) {
		 * System.setProperty("webdriver.ie.driver",
		 * "src/test/resources/BrowserDriver/IEDriverServer.exe"); driver = new
		 * InternetExplorerDriver(); //wait = new WebDriverWait(driver, 20);
		 * log.info("IE driver created"); } else
		 */
		if (Browser.equalsIgnoreCase("AndroidNative")) {
			flag = 1;

			// Set the Desired Capabilities
			log.error("AndroidNative");
			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("deviceName", properties.getProperty("an_deviceName", "My Phone"));
				caps.setCapability("udid", properties.getProperty("an_udid", "auto")); // Give Device ID of your mobile
																						// phone
//			caps.setCapability("automationName", "uiautomator2"); 
//				capability.setCapability(“automationName”,“uiautomator2”);

				caps.setCapability("platformName", properties.getProperty("an_platformName", "Android"));
				caps.setCapability("platformVersion", properties.getProperty("an_platformVersion", "10.0"));
				caps.setCapability("appPackage", properties.getProperty("an_appPackage", "com.google.android.dialer"));
				caps.setCapability("appActivity",
						properties.getProperty("an_appActivity", "com.android.dialer.main.impl.MainActivity"));
				caps.setCapability("noReset", Boolean.getBoolean(properties.getProperty("an_noReset", "true")));
				caps.setCapability("uiautomator2ServerLaunchTimeout",
						properties.getProperty("an_uiautomator2ServerLaunchTimeout", "90000"));
				caps.setCapability("autoWebview", Boolean.getBoolean(properties.getProperty("an_autoWebview", "true")));
				caps.setCapability("unicodeKeyboard", true);
				caps.setCapability("resetKeyboard", true);
				// Instantiate Appium Driver
				driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps
				// new DesiredCapabilities()
				);
				((AndroidDriver) driver).startRecordingScreen(
						new AndroidStartScreenRecordingOptions().withTimeLimit(Duration.ofSeconds(1800)));
//				String Recordedvideo= ((AndroidDriver) driver).stopRecordingScreen();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (MalformedURLException e) {
				log.error(e.getMessage());

			}

		} else if (Browser.equalsIgnoreCase("IOSNative")) {
			flag = 1;
			// Set the Desired Capabilities
			log.error("IOSNative");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", properties.getProperty("ios_platformName", "iOS"));
			caps.setCapability("platformVersion", properties.getProperty("ios_platformVersion", "12.0.1"));
			caps.setCapability("deviceName", properties.getProperty("ios_deviceName", "iPhone 8"));
			caps.setCapability("udid", properties.getProperty("ios_udid", "auto"));
			caps.setCapability("bundleId", properties.getProperty("ios_bundleId")); // "<your bundle id>"
			caps.setCapability("xcodeOrgId", properties.getProperty("ios_xcodeOrgId")); // "<your org id>"
			caps.setCapability("xcodeSigningId", properties.getProperty("ios_xcodeSigningId")); // iPhone Developer
			caps.setCapability("updatedWDABundleId", properties.getProperty("ios_updatedWDABundleId")); // <bundle id in
																										// scope of
																										// provisioning
																										// profile>

			caps.setCapability("noReset", properties.getProperty("ios_noReset", "true"));
//			caps.setCapability("uiautomator2ServerLaunchTimeout", properties.getProperty("ios_uiautomator2ServerLaunchTimeout","90000"));

			// Instantiate Appium Driver
			try {
//				driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps
				// new DesiredCapabilities()
//				);

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
//		 6bd17a6d-77f1-4159-a993-aff4a638b33e
		else if (Browser.equalsIgnoreCase("SLEmulator")) {
			String sauceUserName = properties.getProperty("sl_sauceUserName", "");
			String sauceAccessKey = properties.getProperty("sl_sauceAccessKey", "");
			sauceUserName = "jackomspar3";
			sauceAccessKey = "FA10114773BA4F1C9EF2547F018BD91D";
//	        String URL = "https://" + sauceUserName + ":" + sauceAccessKey + "@ondemand.saucelabs.com:443/wd/hub";
			String URL = "https://eu1.appium.testobject.com/wd/hub";// https://ondemand.eu-central-1.saucelabs.com/wd/hub
			System.out.println(URL);
			flag = 1;
//			URL = "http://"+sauceUserName+":"+sauceAccessKey+"@ondemand.saucelabs.com:80/wd/hub";
//			URL = "https://us1-manual.app.testobject.com/wd/hub";
			// URL =
			// "https://jackomspar3:7c1e2c6c-d2a4-4faa-958c-971e13093c52@ondemand.saucelabs.com:443/wd/hub";
			System.out.println(URL);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("deviceName", "iPhone 6");
			capabilities.setCapability("platformVersion", "8.4");
			capabilities.setCapability("testobject_api_key", "BDE1B3692B8144DA85DDF467579A79D4");
			capabilities.setCapability("testobject_app_id", "1");

			capabilities.setCapability("browserName", "");
			capabilities.setCapability("deviceOrientation", "portrait");
			capabilities.setCapability("appiumVersion", "1.5.3");
			//
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			capabilities.setCapability("platformName", properties.getProperty("platformName", "Android"));
//			capabilities.setCapability("deviceName",  properties.getProperty("deviceName", "Samsung Galaxy S4 Emulator"));
//			capabilities.setCapability("platformVersion", properties.getProperty("platformVersion", "4.4"));
//			capabilities.setCapability("app", properties.getProperty("app", "Go_Plus.apk"));
//			capabilities.setCapability("browserName", properties.getProperty("browserName", ""));
//			capabilities.setCapability("deviceOrientation", properties.getProperty("deviceOrientation", "portrait"));
//			capabilities.setCapability("appiumVersion",properties.getProperty("appiumVersion", "1.5.1"  ) );
//			capabilities.setCapability("testobject_api_key",properties.getProperty("testobject_api_key", "1"  ) );
//			
			/*
			 * DesiredCapabilities caps = DesiredCapabilities.android(); //
			 * caps.setCapability("username", sauceUserName);
			 * caps.setCapability("testobjectApiKey", sauceAccessKey);
			 * caps.setCapability("platformName", "Android");
			 * caps.setCapability("platformVersion", "8"); //
			 * caps.setCapability("accessKey", sauceAccessKey);
			 * caps.setCapability("appiumVersion",
			 * properties.getProperty("sl_appiumVersion","1.6.4")); //
			 * caps.setCapability("deviceName",properties.getProperty(
			 * "sl_deviceName","Android Emulator")); //
			 * caps.setCapability("deviceOrientation",properties.getProperty(
			 * "sl_deviceOrientation","portrait") ); //
			 * caps.setCapability("browserName",properties.getProperty("sl_browserName","")
			 * ); // either one of the 105 or 106 will be blank
			 * caps.setCapability("testobject_app_id",properties.getProperty(
			 * "sl_testobject_app_id","1")); // change the my_app.apk to the apk uploaded on
			 * // caps.setCapability("platformVersion",properties.getProperty(
			 * "sl_platformVersion","8.0")); //
			 * caps.setCapability("platformName",properties.getProperty("sl_platformName",
			 * "Android")); // // Instantiate Appium Driver
			 */
			try {
				driver = new AndroidDriver<WebElement>(new URL(URL), capabilities);

//				driver = new AndroidDriver<>(new URL(URL), caps);
//
//				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//			//	 driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);

//				//driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps
				// new DesiredCapabilities()
//				);

			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		} else if (Browser.equalsIgnoreCase("SLRealDevice")) {
			String sauceUserName = properties.getProperty("sl_sauceUserName", "");
			String sauceAccessKey = properties.getProperty("sl_sauceAccessKey", "");
//	        String URL = "https://" + sauceUserName + ":" + sauceAccessKey + "@ondemand.saucelabs.com:443/wd/hub";
			String URL = "https://eu1.appium.testobject.com/wd/hub";
			System.out.println(URL);
			flag = 1;
			DesiredCapabilities caps = DesiredCapabilities.iphone();
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);
			caps.setCapability("autoAcceptAlerts", true);
//			caps.setCapability("autoDismissAlerts", true);

//			caps.setCapability("username", sauceUserName);
			caps.setCapability("testobjectApiKey", sauceAccessKey);
			caps.setCapability("platformName", "iOS");
			caps.setCapability("platformVersion", "12");
//			caps.setCapability("accessKey", sauceAccessKey);
			caps.setCapability("appiumVersion", properties.getProperty("sl_appiumVersion", "1.6.4"));
//			caps.setCapability("deviceName",properties.getProperty("sl_deviceName","Android Emulator"));
//			caps.setCapability("deviceOrientation",properties.getProperty("sl_deviceOrientation","portrait") );
//			caps.setCapability("browserName",properties.getProperty("sl_browserName","") ); // either one of the 105 or 106 will  be blank
			caps.setCapability("testobject_app_id", properties.getProperty("sl_testobject_app_id", "1")); // change the
																											// my_app.apk
																											// to the
																											// apk
																											// uploaded
																											// on
//			caps.setCapability("platformVersion",properties.getProperty("sl_platformVersion","8.0"));
//			caps.setCapability("platformName",properties.getProperty("sl_platformName","Android"));
//			// Instantiate Appium Driver
			try {
				driver = new AppiumDriver<>(new URL(URL), caps);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				((AndroidDriver) driver).startRecordingScreen(
						new AndroidStartScreenRecordingOptions().withTimeLimit(Duration.ofSeconds(5)));
//				 driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);

//				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps
				// new DesiredCapabilities()
//				);

			} catch (MalformedURLException e) {
				log.error(e.getMessage());
			}
		} else if (Browser.equalsIgnoreCase("ELBS")) {
			String userName = properties.getProperty("elbs_username", "");
			String accessKey = properties.getProperty("elbs_access", "");
			String URL = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
			flag = 1;

			DesiredCapabilities caps = DesiredCapabilities.iphone();
			caps.setCapability("appiumVersion", properties.getProperty("appiumVersion", "1.9.1"));
			caps.setCapability("real_mobile", "false");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("device", properties.getProperty("elbs_device", "I"));
			caps.setCapability("os_version", properties.getProperty("elbs_os_version", "11"));
			caps.setCapability("browserName", properties.getProperty("browserName", "")); // either one of the 105 or
																							// 106 will be blank
			caps.setCapability("app",
					properties.getProperty("elbs_app", "bs://7b4a432c83887c896850cea63436751c2c35b809")); // change the
																											// on
			caps.setCapability("name", properties.getProperty("elbs_name", "Demo Test"));
			// Instantiate Appium Driver
			try {
				IOSDriver driver = new IOSDriver(
						new URL("http://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
				driver = new IOSDriver<>(new URL(URL), caps);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//				 driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);

//				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps
				// new DesiredCapabilities()
//				);

			} catch (MalformedURLException e) {
				log.error(e.getMessage());
			}
		}
		if (flag == 0) {

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			log.error("URL1 is :\t" + Url);
			driver.get(Url);
			log.error("URL2 is :\t" + Url);

			if (Url.contains("https://mail.test.com/owa")) {
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"username\"]")));
			} else {
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GSLOLocators.NEXT_BTN)));
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get(Url);
		}
	}

	public static void clearBrowserCache() throws InterruptedException {
		// Clearing browser cache implementation
		driver.get("chrome://settings/clearBrowserData");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("* /deep/ #advancedPage")));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm"))));
		// click the button to clear the cache
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();

		// wait for the button to be gone before returning
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")));
	}

	public void closeBrowser() {
		getDriver().close();
	}
}
