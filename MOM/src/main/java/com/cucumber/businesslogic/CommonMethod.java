package com.cucumber.businesslogic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

//import com.cucumber.listener.*;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.BrowserFactory;

import com.cucumber.utility.ExcelReade;
import com.cucumber.businesslogic.Accessors;

//import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.vimalselvam.cucumber.listener.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import io.appium.java_client.touch.offset.PointOption;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

@SuppressWarnings("unused")
public class CommonMethod extends BrowserFactory {
	WebElement webelement;

	Accessors accessor = new Accessors();
	public static int parentStepNo;
	public static int childStepNo;

	public int getintFromRString(String str) {
		return new Integer((int) (new Double(str.substring(1).replaceAll(" ", "")) * 100));
	}

	public String getString(String str) {
		String ret = (getWebElementUsingBy(getElement(str))).getText();
		reportStep(ret);
		return ret;
	}

	public By getElement(String elementId) {
		By el = By.id(elementId);
		if (elementId.contains("|||")) {
			String descriptor = elementId.split("\\|\\|\\|")[0];
			String value = elementId.split("\\|\\|\\|")[1];
			switch (descriptor.toUpperCase()) {
			case "XPATH":
				el = By.xpath(value);
				break;
			case "NAME":
				el = By.name(value);
				break;
			case "ID":
				el = By.id(value);
				break;
			case "CSS":
				el = By.cssSelector(value);
				break;
			case "CLASS":
				el = By.className(value);
			case "LINKTEXT":
				el = By.linkText(value);
			case "PARTIALLINKTEXT":
				el = By.partialLinkText(value);
			case "TAGNAME":
				el = By.tagName(value);
				break;
			default:// default is id
				el = By.id(value);
				break;
			}
		}
		return el;
	}

	public WebElement getWebElementUsingBy(By el) {
		return driver.findElement(el);
	}

	public List<WebElement> getWebElementsUsingBy(By el) {
		return driver.findElements(el);
	}

	public WebElement getWebElementUsingLocator(String str) {
		return driver.findElement(getElement(str));
	}

	public List<WebElement> getWebElementsUsingLocator(String str) {
		return driver.findElements(getElement(str));
	}

	public WebElement getWebElementUsingLocatorStringFormat(String str, String ar) {
		return driver.findElement(getElement(String.format(str, ar)));
	}

	public List<WebElement> getWebElementsUsingLocatorStringFormat(String str, String ar) {
		return driver.findElements(getElement(String.format(str, ar)));
	}

	protected void fillInputValue(String strwebElement, String strValue) {
		try {
			WebElement webElement = getWebElementUsingBy(getElement(strwebElement));
			webElement.clear();
			webElement.sendKeys(strValue);
			reportStep(strValue + " Entered in " + webElement);
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}

	protected void userLeftclick(String strwebElement) {
		try {
			WebElement webElement = getWebElementUsingBy(getElement(strwebElement));
			webElement.click();
			reportStep("Clicked on element");
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
		}
	}

	protected String getPageTitle() {
		try {
			String title = driver.getTitle();
			reportStep("Page title is : " + title);
			return title;
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
			accessor.setStatus("FAILED");
			takeScreenShot_reporter();
			log.info(e.getMessage());
			return "";
		}
	}

	public void takeScreenShot() {

		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			parentStepNo = accessor.getParentStepNumber();
			childStepNo = accessor.getChildStepNumber();
			File ScreenShotPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID") + "\\"
					+ ParentStep.getProperty("Browser"));
			File destinationPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID")
					+ "\\" + ParentStep.getProperty("Browser") + "\\" + "\\Step_" + parentStepNo + "." + childStepNo
					+ ".png"); // new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".png");
			childStepNo++;
			accessor.setChildStepno(childStepNo);
			if (!ScreenShotPath.exists()) {
				ScreenShotPath.mkdirs();
			}
			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

		} catch (IOException e) {
		}
	}

	public void takeScreenShot_reporter() {

		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			parentStepNo = accessor.getParentStepNumber();
			childStepNo = accessor.getChildStepNumber();

			File ScreenShotPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID") + "\\"
					+ ParentStep.getProperty("Browser"));
			File destinationPath = new File(System.getProperty("ReportPath") + "\\" + System.getProperty("TestID")
					+ "\\" + ParentStep.getProperty("Browser") + "\\Step_" + parentStepNo + "." + childStepNo + ".png"); // new
																															// SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
																															// Date())
																															// +
																															// ".png");
			parentStepNo++;
			accessor.setParentStepNo(parentStepNo);
			// re initialize child step count as parent step changed
			accessor.setChildStepno(1);
			if (!ScreenShotPath.exists()) {
				ScreenShotPath.mkdirs();
			}
			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

			// This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destinationPath.toString());

		} catch (IOException e) {
		}
	}

	public void reportStep(String string) {

		takeScreenShot();
		accessor.setStepId(parentStepNo + "." + childStepNo);
		log.info(string);
		Reporter.addStepLog(string);
	}

	public boolean clickElementUsingJavascript(WebElement element) {
		try {
			this.waitForElementToAppear(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			reportStep(element.getText() + " clicked ");
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2500);
			return true;
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param element
	 */
	public String getCurrentDate(String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date date = new Date();
			reportStep("current date " + formatter.format(date) + " entered");
			return formatter.format(date);
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return null;
		}
	}

	public void waitForElementToAppear(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public static String generateRandomDate(Integer StartYear, Integer EndYear) throws ParseException {

		GregorianCalendar gc = new GregorianCalendar();

		int year = StartYear + (int) Math.round(Math.random() * (EndYear - StartYear));

		gc.set(gc.YEAR, year);

		int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		log.error(gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.YEAR));

		return String.format("%02d", gc.get(gc.DAY_OF_MONTH)) + "/" + String.format("%02d", (gc.get(gc.MONTH) + 1))
				+ "/" + gc.get(gc.YEAR);

	}

	public static ExpectedCondition<Boolean> toastMatches(String matchText, Boolean isRegexp) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				ImmutableMap<String, Object> args = ImmutableMap.of("text", matchText, "isRegexp", isRegexp);
				return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: isToastVisible", args);
			}

			@Override
			public String toString() {
				return "toast to be present";
			}
		};
	}

	public void setTextMobile(String strwebElement, String textToEnter) {
		try {
			WebElement elementToTypeIn = getWebElementUsingBy(getElement(strwebElement));
//				elementToTypeIn.click();
//				elementToTypeIn.clear();
			if (elementToTypeIn.isEnabled() || elementToTypeIn.equals("")
					|| !elementToTypeIn.getAttribute("readonly").equals("readonly")) {
				elementToTypeIn.sendKeys(textToEnter);
				takeScreenShot_reporter();
			}
			/*
			 * if(driver.findElement(By.xpath("//*[@id='"
			 * +elementToTypeIn.getAttribute("id") +
			 * "']/parent::td/preceding-sibling::td")).isDisplayed()){ reportStep("Text '" +
			 * textToEnter + "' Entered'"); }else{
			 */
//				String elementName = elementToTypeIn.getAttribute("id");
//				if (elementName.isEmpty()) {
//					elementName = elementToTypeIn.getAttribute("name");
//				}
			reportStep("Text '" + textToEnter + "' Entered  '");
			// }
		} catch (Exception e) {

			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void enterText(String strwebElement, String textToEnter) {
		try {
			WebElement elementToTypeIn = getWebElementUsingBy(getElement(strwebElement));
			// this.waitForElementToAppear(elementToTypeIn);
			if (elementToTypeIn.isEnabled() || elementToTypeIn.equals("")
					|| !elementToTypeIn.getAttribute("readonly").equals("readonly")) {
				elementToTypeIn.clear();
				elementToTypeIn.click();
				elementToTypeIn.sendKeys(textToEnter);
			}
			/*
			 * if(driver.findElement(By.xpath("//*[@id='"
			 * +elementToTypeIn.getAttribute("id") +
			 * "']/parent::td/preceding-sibling::td")).isDisplayed()){ reportStep("Text '" +
			 * textToEnter + "' Entered'"); }else{
			 */
			String elementName = elementToTypeIn.getAttribute("id");
			if (elementName.isEmpty()) {
				elementName = elementToTypeIn.getAttribute("name");
			}

			reportStep("Text '" + textToEnter + "' Entered in '" + elementName + "'");
			// }
		} catch (Exception e) {

			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void sleep(int xx) {
		try {
			Thread.sleep(xx * 1000);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}

	}
	public void clickIfExists(String strwebElement) {
		try {
			WebElement elementToTypeIn = getWebElementUsingBy(getElement(strwebElement));
			// this.waitForElementToAppear(elementToTypeIn);
			Boolean visible=elementToTypeIn.isDisplayed();
			if (visible==true)
					{				
				elementToTypeIn.click();
				
			}}
			finally {
				System.out.println("Element is not present");
			}}
		

	public void clickOnElement(WebElement element) {
		try {
			waitForElementToAppear(element);
			reportStep(element.getText() + " clicked ");
			element.click();
			takeScreenShot_reporter();
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public String getText(WebElement element) {
		String str = "";
		try {
			waitForElementToAppear(element);
			reportStep(element.getText() + " Text Recevied ");
			str = element.getText();
			takeScreenShot_reporter();
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
		return str;
	}

	/**
	 * 
	 * /**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: selectValueFromDropdown
	 * @purpose: Selecting value from drop-down
	 * @param :dropDown and selectValue
	 * @author sansayed
	 * @return: void
	 * @throws InterruptedException
	 * 
	 ***/
	public void selectValueFromDropdown(WebElement dropDown, String selectValue) throws InterruptedException {
		try {
			waitForElementToAppear(dropDown);
			if (dropDown.isEnabled()) {

				Select value = new Select(dropDown);
				value.selectByValue(selectValue);
			}
			reportStep("Option " + selectValue + " selected ");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: setStarRating
	 * @purpose: set the stars in the rating bar
	 * @param :String Element, int stars
	 * @author Prasanna
	 * @return: void
	 * 
	 ***/
	public void setStarRating(String ele, int stars) {
		// Locate threeStarRatingbar.
		WebElement StarRatingbar = getWebElementUsingLocator(ele);
		// Get start point of threeStarRatingbar.
		int startX = StarRatingbar.getLocation().getX();
		System.out.println(startX);
		// Get end point of threeStarRatingbar.
		int endX = StarRatingbar.getSize().getWidth();
		System.out.println(endX);
		// Get vertical location of threeStarRatingbar.
		int yAxis = StarRatingbar.getLocation().getY();

		// Set threeStarRatingbar tap position to set Rating = 1 star.
		// You can use endX * 0.3 for 1 star, endX * 0.6 for 2 star, endX * 1 for 3
		// star.
		int tapAt = (int) (endX * (1 / 5) * stars);
		// Set threeStarRatingbar to Rating = 1.0 using TouchAction class.
		TouchAction action = new TouchAction(driver);
		action.tap(PointOption.point(tapAt, yAxis)).release().perform();
	}

	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: swipeByElements
	 * @purpose: verifies that the object is displayed on the screen or not
	 * @param :String Element and selectValue
	 * @author Prasanna
	 * @return: Boolean
	 * 
	 ***/
	public void swipeByElements(String strstartElement, String strendElement) {
		WebElement startElement = getWebElementUsingLocator(strstartElement);
		WebElement endElement = getWebElementUsingLocator(strendElement);
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

		int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
		int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
		
		new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(endX, endY)).release().perform();
		sleep(2);

	}

	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: touch
	 * @purpose: perform touch action 
	 * @param :String Element 
	 * @author Prasanna
	 * @return: void
	 * 
	 ***/
	public void touch(String strstartElement) {
		WebElement startElement = getWebElementUsingLocator(strstartElement);
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
		System.out.println(startX+"\t "+startY);
		new TouchAction(driver).press(point(startX, startY)).moveTo(point(startX, startY)).release().perform();
		sleep(1);

	}	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: touchpoints
	 * @purpose: clicks on the x and y point given 
	 * @param :String Element and selectValue
	 * @author Prasanna
	 * @return: Boolean
	 * 
	 ***/
	public void touchpoints(int startX,int startY) {
		new TouchAction(driver).press(point(startX, startY)).moveTo(point(startX, startY)).release().perform();
		sleep(1);

	}

	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: swipeByElements
	 * @purpose: verifies that the object is displayed on the screen or not
	 * @param :String Element and selectValue
	 * @author Prasanna
	 * @return: Boolean
	 * 
	 ***/
	public void moveTo(String strstartElement) {
		WebElement startElement = getWebElementUsingLocator(strstartElement);
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
		new TouchAction(driver).moveTo(point(startX, startY)).release().perform();
		sleep(1);

	}

	/**
	 * 
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: isDisplayed
	 * @purpose: verifies that the object is displayed on the screen or not
	 * @param :String Element and selectValue
	 * @author Prasanna
	 * @return: Boolean
	 * 
	 ***/
	public boolean isDisplayed(String element) {

		return getWebElementUsingLocator(element).isDisplayed();
	}
	public void scrollTillText(String strElementText) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+strElementText+"\").instance(0))"));
		sleep(1);

	}

	public void closeBrowser() {
		try {
			reportStep("Browser closed");
			if (BrowserFactory.getDriver() != null)
				BrowserFactory.getDriver().quit();
		} catch (Exception e) {
//				reportStep(e.getMessage());
//				takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: minimize
	 * @purpose: minimize the browser
	 * @param :
	 * @author kuabhis5
	 * @return: void
	 * 
	 * 
	 ***/
	public void minimize() throws AWTException {

		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_N);
			reportStep("Browser minimized");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}

	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: maximize
	 * @purpose: maximize the browser
	 * @param :
	 * @author kuabhis5
	 * @return: void
	 * 
	 * 
	 ***/
	public void maximize() throws AWTException {

		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void backAndroid() {

		try {
			driver.navigate().back();
//				((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);

//				((MobileDriver)driver).back();
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void tab() throws InterruptedException {
		try {
			Actions a = new Actions(driver);
			a.keyDown(Keys.TAB).perform();
			reportStep("Browser maximized");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void Shifttab() {
		try {
			WebElement backdir = driver.findElement(By.name("$PpyWorkPage$pPartLinesList$l1$pItemName"));
			backdir.sendKeys(Keys.SHIFT, Keys.TAB);
			reportStep("SHIFT + TAB key pressed");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}

	}

	// accept alert
	public void accept_Alert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			reportStep("Switched to alert");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void switch_to_new_Window() {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			reportStep("Switched to new Window");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void SelectFromDropdown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(value);
			reportStep("Option " + value + " Selected from " + " dropdown");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public boolean checkBoxSelection(WebElement element, String mustBeSelected) {
		try {
			// Thread.sleep(2000);
			this.waitForElementToAppear(element);
			WebDriverWait wait = new WebDriverWait(driver, 600);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isSelected() != Boolean.valueOf(mustBeSelected)) {
				element.click();
			}
			reportStep("Checkbox " + element.getText() + " checked");
			return true;
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
			return false;
		}
	}

	public void switchToFramebyElementPresence(By byControlIdentifier) {
		try {
			driver.switchTo().defaultContent();
			int size = driver.findElements(By.tagName("iframe")).size();
			for (int i = 0; i < size; i++) {
				driver.switchTo().defaultContent();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(i));
				int total = driver.findElements(byControlIdentifier).size();
				if (total > 0) {
					log.error("Element found in frame index : " + i);
					break;
				}
			}
			reportStep("Switched to frame");
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public void doubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			reportStep("Doubleclick Performed for " + element.getText());
			action.moveToElement(element).doubleClick().perform();
		} catch (Exception e) {
			reportStep(e.getMessage());
			takeScreenShot_reporter();
			Assert.assertFalse(true, e.getMessage());
		}
	}

	public static String GenerateChecksumLuhnAlgorithm(String num) {
		Long number = Long.parseLong(num);
		Long digits[] = new Long[20];
		long newNumber = 0;

		for (int i = 0; i < num.length(); i++) {
			digits[i] = number % 10;
			number = number / 10;
		}
		for (int j = 0; j < num.length(); j += 2) {
			if (digits[j] * 2 > 9) {
				digits[j] = (((digits[j] * 2) % 10) + ((digits[j] * 2) / 10) % 10);
			} else {
				digits[j] = digits[j] * 2;
			}
		}
		for (int k = 0; k < num.length(); k++) {
			newNumber = newNumber + digits[k];
		}
		return String.valueOf((newNumber * 9) % 10);
	}

}
