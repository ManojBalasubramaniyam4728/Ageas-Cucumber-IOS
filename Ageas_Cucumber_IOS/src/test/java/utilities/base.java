package utilities;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.ios.IOSDriver;
import pageObject.ageasScreen;
import pageObject.gmailLandingScreen;
import pageObject.gmailMeetScreen;
import pageObject.gmailYoureTheOnlyOneHereScreen;
import pageObject.settingConfigureProxyScreen;
import pageObject.settingFaceIdAndPasscodeScreen;
import pageObject.settingGeneralScreen;
import pageObject.settingLandingScreen;
import pageObject.settingMoreInfoScreen;
import pageObject.settingVpnAndDeviceManagementScreen;
import pageObject.settingVpnScreen;
import pageObject.settingWifiScreen;

public class base {

	// global Variables
	public static IOSDriver driver;
	public static ageasScreen as;
	public static gmailLandingScreen gls;
	public static gmailMeetScreen gms;
	public static gmailYoureTheOnlyOneHereScreen gytohs;
	public static settingConfigureProxyScreen scps;
	public static settingFaceIdAndPasscodeScreen sfaps;
	public static settingGeneralScreen sgs;
	public static settingLandingScreen sls;
	public static settingMoreInfoScreen smis;
	public static settingVpnAndDeviceManagementScreen svadms;
	public static settingVpnScreen svs;
	public static settingWifiScreen sws;
	public static Logger logger;
	public static String excelPath="Ageas Test Data.xlsx";
	public static String screenshootFilePath="screenshoot/";
	public static String testConfigSheetName="Test Config";
	public static String ageasSheetName="Ageas";
	public static String uniqueDataTestConfig="Test";
	public static String uniqueDataAG001="AG001";
	public static String uniqueDataAG002="AG002";
	public static String uniqueDataAG003="AG003";
	public static LinkedHashMap<String, String> testConfigKeyValue=new LinkedHashMap<>();
	public static LinkedHashMap<String, String> AG001KeyValue=new LinkedHashMap<>();
	public static LinkedHashMap<String, String> AG002KeyValue=new LinkedHashMap<>();
	public static LinkedHashMap<String, String> AG003KeyValue=new LinkedHashMap<>();
	public static boolean isTableVertical=true;
	public static boolean isTableHorizontal=false;
	

//**************************************************************************************************************************************
	// User Defined Method To open Ageas Application

	public static IOSDriver openAgeasApplication() throws IOException {
		testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String deviceName=fetchDatFromMap(testConfigKeyValue, "Device_Name");
        String platformName=fetchDatFromMap(testConfigKeyValue, "Platform_Name");
        String platformVersion=fetchDatFromMap(testConfigKeyValue, "Platform_Version");
        String automationName=fetchDatFromMap(testConfigKeyValue, "Automation_Name");
        String udid=fetchDatFromMap(testConfigKeyValue, "Udid");
        String agentPath=fetchDatFromMap(testConfigKeyValue, "Agent_Path");
        String bootstrapPath=fetchDatFromMap(testConfigKeyValue, "Bootstrap_Path");
        String ageasBundleId=fetchDatFromMap(testConfigKeyValue, "Ageas_Bundle_Id");
        String implicitlyWait=fetchDatFromMap(testConfigKeyValue, "Implicit_Wait _Time");
        int implicitlyWaitTime = Integer.parseInt(implicitlyWait);
        String baseUrl=fetchDatFromMap(testConfigKeyValue, "Base_URL");
        
        
		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", ageasBundleId);

		// Url To server
		URL url = new URL(baseUrl);

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method To open Setting Application
	public static IOSDriver openSettingApplication() throws IOException {
		testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String deviceName=fetchDatFromMap(testConfigKeyValue, "Device_Name");
        String platformName=fetchDatFromMap(testConfigKeyValue, "Platform_Name");
        String platformVersion=fetchDatFromMap(testConfigKeyValue, "Platform_Version");
        String automationName=fetchDatFromMap(testConfigKeyValue, "Automation_Name");
        String udid=fetchDatFromMap(testConfigKeyValue, "Udid");
        String agentPath=fetchDatFromMap(testConfigKeyValue, "Agent_Path");
        String bootstrapPath=fetchDatFromMap(testConfigKeyValue, "Bootstrap_Path");
        String settingsBundleId=fetchDatFromMap(testConfigKeyValue, "Settings_Bundle_Id");
        String implicitlyWait=fetchDatFromMap(testConfigKeyValue, "Implicit_Wait _Time");
        int implicitlyWaitTime = Integer.parseInt(implicitlyWait);
        String baseUrl=fetchDatFromMap(testConfigKeyValue, "Base_URL");

		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", settingsBundleId);

		// Url To server
		URL url = new URL(baseUrl);
	

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		return driver;
	}

//**************************************************************************************************************************************
	// User Defined Method To open Gmail Application
	public static IOSDriver openGmailApplication() throws IOException {
		testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
        String deviceName=fetchDatFromMap(testConfigKeyValue, "Device_Name");
        String platformName=fetchDatFromMap(testConfigKeyValue, "Platform_Name");
        String platformVersion=fetchDatFromMap(testConfigKeyValue, "Platform_Version");
        String automationName=fetchDatFromMap(testConfigKeyValue, "Automation_Name");
        String udid=fetchDatFromMap(testConfigKeyValue, "Udid");
        String agentPath=fetchDatFromMap(testConfigKeyValue, "Agent_Path");
        String bootstrapPath=fetchDatFromMap(testConfigKeyValue, "Bootstrap_Path");
        String gmailBundleId=fetchDatFromMap(testConfigKeyValue, "Gmail_Bundle_Id");
        String implicitlyWait=fetchDatFromMap(testConfigKeyValue, "Implicit_Wait _Time");
        int implicitlyWaitTime = Integer.parseInt(implicitlyWait);
        String baseUrl=fetchDatFromMap(testConfigKeyValue, "Base_URL");

		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("platformName", platformName);
		cap.setCapability("platformVersion", platformVersion);
		cap.setCapability("automationName", automationName);
		cap.setCapability("udid", udid);
		cap.setCapability("agentPath", agentPath);
		cap.setCapability("bootstrapPath", bootstrapPath);
		cap.setCapability("bundleId", gmailBundleId);

		// Url To server
		URL url = new URL(baseUrl);

		// open the app
		driver = new IOSDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		return driver;
	}
	
//**************************************************************************************************************************************
		//User Defined Method Get Data FromExcel
		public static LinkedHashMap<String,String> getDataFromExcel(String excelPath, String sheetName, String uniqueData,boolean isTableVertical) throws IOException  {
				FileInputStream fisExcel=new FileInputStream(excelPath);
				Workbook workbook = WorkbookFactory.create(fisExcel);
				DataFormatter df=new DataFormatter();
				LinkedHashMap<String, String> map=new LinkedHashMap<>();
				Sheet sheet = workbook.getSheet(sheetName);
				int lastRowNumber = sheet.getLastRowNum(); //return index ==> index

				String value="";
				String actualTestCaseName = "";
				String actualKey = "";
				
				//For Horizontal Data Featching In Key Value Pair
				if (isTableVertical==false) {
				for (int i = 0; i <=lastRowNumber; i++) {
					String actualTestcase = df.formatCellValue(sheet.getRow(i).getCell(0));
					if (actualTestcase.equals(uniqueData)) {
						short lastcellNumber = sheet.getRow(i).getLastCellNum(); //return count/size ==> count-1
						for (int j = 1; j < lastcellNumber-1; j++) {
							actualKey = df.formatCellValue(sheet.getRow(i-1).getCell(j));
							value = df.formatCellValue(sheet.getRow(i).getCell(j));
							map.put(actualKey, value);
						}
						break;
				     	}
				    }
				}
				
				//For Vertical Data Featching In Key Value Pair
				else if(isTableVertical==true) {
					for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

						try {
							actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

						} catch (Exception e) {
						}
						if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
							for (int j = 0; j<= sheet.getLastRowNum(); j++) {

								try {
									actualKey = df.formatCellValue(sheet.getRow(j).getCell(i-1));
									try {
										value = df.formatCellValue(sheet.getRow(j).getCell(i));
									} catch (Exception e) {
									}

									if ((actualKey.isEmpty()&&value.isEmpty()) ||actualKey.isEmpty()) {
									} else {
										map.put(actualKey, value);
									}
								} catch (Exception e) {
								}
							}
							break;
						}
					}
				 }
				workbook.close();
				fisExcel.close();
				return map;
		   }
		
//**************************************************************************************************************************************
		//User Defined Method Get Data Map
		public static String fetchDatFromMap(LinkedHashMap<String, String> testConfigKeyValue,String value) {
			return value = testConfigKeyValue.get(value);
		}
		
//**************************************************************************************************************************************
		//User Defined Method Get Data FromExcel
		public static void WriteDataInToExcel(String excelPath, String sheetName, String uniqueData, String header,String data,boolean isTableVertical ) throws EncryptedDocumentException, IOException {
			FileInputStream excelFile = new FileInputStream(new File(excelPath));
			Workbook workbook =  WorkbookFactory.create(excelFile);
			Sheet sheet = workbook.getSheet(sheetName);
			DataFormatter df=new DataFormatter();
			boolean flag = false;
			String actualTestCaseName="";
			String actualKey="";
			
			//For Horizontal Data Write Into Excel
			if (isTableVertical==false) {
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				try {
					actualTestCaseName =df.formatCellValue(sheet.getRow(i).getCell(0));
				} catch (Exception e) {
				       }
				if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
					for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
						try {
							actualKey =df.formatCellValue( sheet.getRow(i - 1).getCell(j));
						} catch (Exception e) {
						}
						if (actualKey.equalsIgnoreCase(header)) {
							try {
								sheet.getRow(i).createCell(j).setCellValue(data);
							} catch (Exception e) {
							     }
							flag = true;
							break;
						}
					}
				}
				if (flag==true) {
					break;
				}
			  }
			}
			//For Vertical Data Write Into Excel
			else if (isTableVertical==true) {
				for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

					try {
						actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

					} catch (Exception e) {
					}
					if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
						for (int j = 0; j <= sheet.getLastRowNum(); j++) {

							try {
								actualKey = df.formatCellValue(sheet.getRow(j).getCell(i-1));
							} catch (Exception e) {
							}
							if (actualKey.equalsIgnoreCase(header)) {
								try {
									sheet.getRow(j).createCell(i).setCellValue(data);
								} catch (Exception e) {
								     }
								flag = true;
								break;
							    }
							}
						}
					if (flag==true) {
						break;
					}
			    }
			}
			FileOutputStream fos = new FileOutputStream(excelPath);
			workbook.write(fos);
			fos.close();
			workbook.close();
			excelFile.close();
		} 
		
//**************************************************************************************************************************************
		// User Defined Method To Explicit Wait
		public void WaitUntilvisibilityOfElement(WebElement Element) throws IOException {
			testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
	        String explicitWait=fetchDatFromMap(testConfigKeyValue, "Explicit_Wait_Time");
			Integer explicitWaitTime = Integer.parseInt(explicitWait);
			// logic gos here
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
			wait.until(ExpectedConditions.visibilityOf(Element));
		}

//**************************************************************************************************************************************
		// User Defined Method To Explicit Wait
		public void LongWaitUntilvisibilityOfElement(WebElement Element) throws IOException {
			testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
	        String explicitLongWait=fetchDatFromMap(testConfigKeyValue, "Explicit_Wait_Time_Long_Wait_Time");
			Integer explicitLongWaitTime = Integer.parseInt(explicitLongWait);
			// logic gos here
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitLongWaitTime));
			wait.until(ExpectedConditions.visibilityOf(Element));
		}

//**************************************************************************************************************************************
		// User Defined Method To Explicit Wait LocaterValue
		public void WaitUntilvisibilityOfElementLocated(By.ByXPath LocaterValue) throws IOException {
			testConfigKeyValue=getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
	        String explicitWait=fetchDatFromMap(testConfigKeyValue, "Explicit_Wait_Time");
			Integer explicitWaitTime = Integer.parseInt(explicitWait);
			// logic gos here
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
			wait.until(ExpectedConditions.visibilityOfElementLocated(LocaterValue));
		}

//**************************************************************************************************************************************
		// User Defined Method To Tap on Element
		public void tap(WebElement element, IOSDriver driver) throws IOException {
			Point sourceLocation = element.getLocation();
			Dimension sourceSize = element.getSize();
			int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
			int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(tap));
		}

//**************************************************************************************************************************************
		// User Defined Method For Closing The Application
		public void closeTheApplication() {
			driver.quit();
			driver = null;
		}

//**************************************************************************************************************************************
		// User Defined Method To Swipe To The Element
		public void swipeToTheElement(WebElement element, int maximumCount, IOSDriver driver) throws IOException {
			// Taking mobile X and y center of screen
			int centerScreenX = driver.manage().window().getSize().getWidth() / 2;
			int centerScreenY = driver.manage().window().getSize().getHeight() / 2;

			// Frome center of the screen swiping 30% upword
			int endY = (int) (driver.manage().window().getSize().getHeight() * 0.02);

			int maxCount = maximumCount;
			int count = 0;
			for (int i = count; i < maxCount; i++) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1);

				try {
					if (element.isDisplayed()) {
						break;
					}
				} catch (NoSuchElementException e) {
					swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerScreenX,
							centerScreenY));
					swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(),
							centerScreenX, endY));
					swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Arrays.asList(swipe));
				}
				if (count >= maxCount) {
					break;
				}
			}
		}

//**************************************************************************************************************************************
		// Verify Element Is having Expected Text
		public boolean verifyElementIsHavingExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
			// Get the text from the element
			String actualText = element.getText();
			// Perform text verification (case insensitive)
			boolean isTextMatching;

			if (ignoreCase == true) {
				isTextMatching = actualText.equalsIgnoreCase(expectedText);
			} else {
				isTextMatching = actualText.equals(expectedText);
			}
			return isTextMatching;
		}

//**************************************************************************************************************************************
		// Verify Element Is Contains Expected Text
		public boolean verifyElementIsContainsExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
			// Get the text from the element
			String actualText = element.getText();
			// Perform text verification (case insensitive)
			boolean isTextMatching;

			if (ignoreCase == true) {
				isTextMatching = actualText.toLowerCase().contains(expectedText);
			} else {
				isTextMatching = actualText.contains(expectedText);
			}

			return isTextMatching;

		}

//**************************************************************************************************************************************
		// Verify Attribute Of Element Is having Expected Text
		public boolean verifyAttributOfElementIsHavingExpectedText(WebElement element, String attributName,
				String expectedText, boolean ignoreCase) {
			// Get the text from the element
			String actualText = element.getAttribute(attributName);
			// Perform text verification (case insensitive)
			boolean isTextMatching;

			if (ignoreCase == true) {
				isTextMatching = actualText.equalsIgnoreCase(expectedText);
			} else {
				isTextMatching = actualText.equals(expectedText);
			}

			return isTextMatching;

		}

//**************************************************************************************************************************************
		// Verify Attribute Of Element Is Contains Expected Text
		public boolean verifyAttributOfElementIsContainsExpectedText(WebElement element, String attributName,
				String expectedText, boolean ignoreCase) {
			// Get the text from the element
			String actualText = element.getAttribute(attributName);
			// Perform text verification (case insensitive)
			boolean isTextMatching;

			if (ignoreCase == true) {
				isTextMatching = actualText.toLowerCase().contains(expectedText);
			} else {
				isTextMatching = actualText.contains(expectedText);
			}

			return isTextMatching;

		}

//**************************************************************************************************************************************
		// Verify Element is displayed are Not
		public boolean verifyElementIsDisplayedAreNot(WebElement element) {
			boolean isDisplayed;
			try {
				isDisplayed = element.isDisplayed();
				isDisplayed = true;

			} catch (NoSuchElementException e) {
				isDisplayed = false;
			}
			return isDisplayed;
		}

//**************************************************************************************************************************************
		// Enter Input Into The Element
		public void enterInputIntoTheElement(WebElement element, String input) {
			element.sendKeys(input);
		}

//**************************************************************************************************************************************
		// Clear The Text And Enter The Input Into The Element
		public void clearTheTextAndEnterInputIntoTheElement(WebElement element, String input) {
			element.clear();
			element.sendKeys(input);
		}

//**************************************************************************************************************************************
		// Verify Element is having Expected Text And Add That Text to List
		public ArrayList<String> verifyElementIsHavingExpectedTextAndAddThatTexttoList(WebElement staticElement,
				WebElement textElement, String expectedText) {
			ArrayList<String> popUpTextTitle = new ArrayList<String>();
			try {
				while (staticElement.isDisplayed()) {
					String actualText = textElement.getText();
					popUpTextTitle.add(actualText);
					Boolean result = actualText.contains(expectedText);
					if (result == true) {
						break;
					} else if (result == false) {
						staticElement.click();
					}
				}

			} 
			catch (NoSuchElementException e) {
			}
			catch (Exception e) {
			}
			return popUpTextTitle;
		}

//**************************************************************************************************************************************
		// Convert ArrayList To String
		public String arrayListToString(ArrayList<String> arrayList) {
			// Convert ArrayList to String using toString() method
			String arrayListAsString = arrayList.toString();
			return arrayListAsString;
		}

//**************************************************************************************************************************************  
		// Convert String to Boolean
		public boolean stringToBoolean(String Value) {
			boolean booleanValue = Boolean.parseBoolean(Value);
			return booleanValue;
		}

//**************************************************************************************************************************************  
		// User Defined Method To Tap On Center Of The Screen
		public void tapOnCenterOfTheScreen(IOSDriver driver) throws IOException {
			// Taking mobile X and y center of screen
			int centerScreenX = driver.manage().window().getSize().getWidth() / 2;
			int centerScreenY = driver.manage().window().getSize().getHeight() / 2;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);

			swipe.addAction(
					finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerScreenX, centerScreenY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(swipe));

		}

//**************************************************************************************************************************************          			
		// User Defined Method To Tap X And Y Coordinates
		public void tapOnXAndYCoordinates(IOSDriver driver, int xCoordinate, int yCoordinate) throws IOException {

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), xCoordinate,
					yCoordinate));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(tap));
		}

//**************************************************************************************************************************************          			
		// Get Text From Element
		public String getTextFromElement(WebElement element) throws IOException {
			String obtainedText = element.getText();
			return obtainedText;
		}
		
//**************************************************************************************************************************************          			
		// Get Text From Element
			public void pressHomeButton() throws IOException {
				driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
			}
//**************************************************************************************************************************************          			
			
	}

	
