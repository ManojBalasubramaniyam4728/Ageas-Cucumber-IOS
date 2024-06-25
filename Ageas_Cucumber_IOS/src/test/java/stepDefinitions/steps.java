package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.ageasScreen;
import pageObject.gmailLandingScreen;
import pageObject.gmailMeetScreen;
import pageObject.gmailYoureTheOnlyOneHereScreen;
import pageObject.settingConfigureProxyScreen;
import pageObject.settingGeneralScreen;
import pageObject.settingLandingScreen;
import pageObject.settingMoreInfoScreen;
import pageObject.settingVpnAndDeviceManagementScreen;
import pageObject.settingVpnScreen;
import pageObject.settingWifiScreen;
import utilities.base;

public class steps extends base {

	//Pre-Conditions
	@Before
	public void setUp() throws IOException {
		// Reading The logger
		logger = Logger.getLogger("Ageas IOS");// Adding logger
		PropertyConfigurator.configure("log4j.properties");// Adding logger

		// Getting Data From Excel And Storing In Key Vale Pair
		testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		AG001KeyValue = getDataFromExcel(excelPath, ageasSheetName, uniqueDataAG001, isTableHorizontal);
		AG002KeyValue = getDataFromExcel(excelPath, ageasSheetName, uniqueDataAG002, isTableHorizontal);
		AG003KeyValue = getDataFromExcel(excelPath, ageasSheetName, uniqueDataAG003, isTableHorizontal);
	}

//**************************************************************************************************************************************          			
     //AG001 Verify proxy checks
	
	@Given("Launch the settings application")
	public void launch_the_settings_application() throws IOException {
		driver = openSettingApplication();
		//Giving Driver Life To Respective Pages
		sls = new settingLandingScreen(driver);
		sws =new settingWifiScreen(driver);
		smis=new settingMoreInfoScreen(driver);
		scps=new settingConfigureProxyScreen(driver);
		logger.info("******************* Settings Application Opened Successfully *******************");
		//Fetching datas
		String settingsScreenVerification = fetchDatFromMap(testConfigKeyValue, "Settings_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		verifyElementIsHavingExpectedText(sls.WiFiButton, settingsScreenVerification, ignoreCase);
		logger.info("WiFi Button Element Is Having Expected "+settingsScreenVerification+" Text");
	}

	@And("Tap On wi-fi button")
	public void tap_on_wi_fi_button() throws IOException {
	    //Steps
        tap(sls.WiFiButton, driver);
        logger.info("Successfully Taped On WiFi Button");
	}

	@Then("Verify wi-fi screen")
	public void verify_wi_fi_screen() {
		//Fetching datas
		String wifiTextVerification = fetchDatFromMap(AG001KeyValue, "Wifi_Text_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
		logger.info("WiFi Text Element Is Having Expected "+wifiTextVerification+" Text");
	}

	@And("Tap on info icon of the wifi")
	public void tap_on_info_icon_of_the_wifi() throws IOException {
		//Steps
		tap(sws.MoreInfoIcon, driver);
		logger.info("Successfully Taped On More Info Icon");
	}

	@When("Proxy is in off condition on the proxy")
	public void proxy_is_in_off_condition_on_the_proxy() throws IOException {
		//Fetching datas
		String ifProxyInOff = fetchDatFromMap(AG001KeyValue, "If_Proxy_In_Off");
		boolean proxyButtonOff=stringToBoolean(ifProxyInOff);
		String configureProxyScreenVerification = fetchDatFromMap(AG001KeyValue, "Configure_Proxy_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String serverNumber = fetchDatFromMap(AG001KeyValue, "Server_number");
		String portNumber = fetchDatFromMap(AG001KeyValue, "Port_Number");
		//Steps
		boolean isDisplayed=verifyElementIsDisplayedAreNot(smis.OffText);
		if (isDisplayed) {
			logger.info("Element Proxy Off Toggle Button Is in Off");
		}
		else {
		logger.info("Element Proxy Off Toggle Button Is in On");
		}
		if (isDisplayed==proxyButtonOff) {
			smis.ConfigureProxyButton.click();
			logger.info("Successfully Clicked On Configure Proxy Button");
			verifyElementIsHavingExpectedText(scps.ConfigureProxyText, configureProxyScreenVerification,ignoreCase );
			logger.info("Configure Proxy Text Element Is Having Expected "+configureProxyScreenVerification+" Text");
		    tap(scps.ManualButton, driver);
		    logger.info("Successfully Taped On Manual Button");
		    clearTheTextAndEnterInputIntoTheElement(scps.ServerTextfield, serverNumber);
		    logger.info("Successfully Cleared Text In Server Textfield And Entred "+serverNumber+" Into Server Textfield");
		    clearTheTextAndEnterInputIntoTheElement(scps.PortTextfield, portNumber);
		    logger.info("Successfully Cleared Text In Server Textfield And Entred "+portNumber+" Into Server Textfield");
		    tap(scps.SaveButton, driver);
		    logger.info("Successfully Taped On Save Button");
		    logger.info("Successfully Setted The Proxy");
		}
	}

	@And("Close the setting application")
	public void close_the_setting_application() {
		//Steps
		closeTheApplication();
		logger.info("******************* Setting Application Closed Successfully *******************");
	}

	@Given("Launch the agease application")
	public void launch_the_agease_application() throws IOException {
		driver=openAgeasApplication();
		//Giving Driver Life To Respective Pages
		as=new ageasScreen(driver);
		logger.info("******************* Ageas Application Opened Successfully *******************");
		//Fetching datas
//		String ageasSecurityScreenVerification = fetchDatFromMap(testConfigKeyValue, "Ageas_Security_Screen_Verification");
//		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
//		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
//		verifyElementIsHavingExpectedText(as.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
//		logger.info("Welcome  Element Is Having Expected "+ageasSecurityScreenVerification+" Text");
	}


	@When("Proxy popup is present in ageas application or not")
	public void proxy_popup_is_present_in_ageas_application_or_not() throws EncryptedDocumentException, IOException {
		//Fetching datas
		String expectedTextPopUp = fetchDatFromMap(AG001KeyValue, "Expected_Text_Pop_Up");
		//Steps
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Present In Ageas Screen");
		String arrayListToString=arrayListToString(popUpTextTitle);
		//Write Back The Data To Excel
		WriteDataInToExcel(excelPath,ageasSheetName, uniqueDataAG001,"Pop_Up_Title_Text_List", arrayListToString, isTableHorizontal);
		logger.info("Successfully written "+arrayListToString+" Pop-Up Into Excel");
	}

	@Then("Verify proxy network identified header")
	public void verify_proxy_network_identified_header() {
		//Fetching datas
		String proxyNetworkIdentifiedTextVerification = fetchDatFromMap(AG001KeyValue, "Proxy_Network_dentified_Text_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		boolean ProxyNetworkIdentifiedTextResult=verifyElementIsHavingExpectedText(as.ProxyNetworkIdentifiedText,proxyNetworkIdentifiedTextVerification, ignoreCase);
        Assert.assertTrue(ProxyNetworkIdentifiedTextResult);
		logger.info("Proxy Network header is Present in the Ageas "+ProxyNetworkIdentifiedTextResult);
	}
	

	@Then("Verify proxy network identified message")
	public void verify_proxy_network_identified_message() {
		//Fetching datas
		String proxyNetworkPopupMessage = fetchDatFromMap(AG001KeyValue, "Popup_Message");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		boolean ProxyNetworkIdentifiedMessageTextResult= verifyElementIsHavingExpectedText(as.ProxyNetworkIdentifiedMessageText,proxyNetworkPopupMessage, ignoreCase);
	    Assert.assertTrue(ProxyNetworkIdentifiedMessageTextResult);
		logger.info("Proxy Network header Boday is Present in the Ageas "+ProxyNetworkIdentifiedMessageTextResult);
	}

	@And("Close the ageas application")
	public void close_the_ageas_application() {
		closeTheApplication();
		logger.info("******************* Ageas Application Closed Successfully *******************");
	}
	
	@And("Negative validation turn Off proxy")
	public void negative_validation_turn_off_proxy() throws IOException {
		driver = openSettingApplication();
		//Giving Driver Life To Respective Pages
		sls = new settingLandingScreen(driver);
		sws =new settingWifiScreen(driver);
		smis=new settingMoreInfoScreen(driver);
		scps=new settingConfigureProxyScreen(driver);
		logger.info("******************* Settings Application Opened Successfully *******************");
		//Fetching datas
		String settingsScreenVerification = fetchDatFromMap(testConfigKeyValue, "Settings_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String wifiTextVerification = fetchDatFromMap(AG001KeyValue, "Wifi_Text_Verification");
		String ifProxyInManual = fetchDatFromMap(AG001KeyValue, "If_Proxy_In_Manual");
		boolean proxyButtonManual=stringToBoolean(ifProxyInManual);
		String configureProxyScreenVerification = fetchDatFromMap(AG001KeyValue, "Configure_Proxy_Screen_Verification");
		//Steps
		verifyElementIsHavingExpectedText(sls.WiFiButton, settingsScreenVerification, ignoreCase);
		logger.info("WiFi Button Element Is Having Expected "+settingsScreenVerification+" Text");
		tap(sls.WiFiButton, driver);
	    logger.info("Successfully Taped On WiFi Button");
	    verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
		logger.info("WiFi Text Element Is Having Expected "+wifiTextVerification+" Text");
		tap(sws.MoreInfoIcon, driver);
		logger.info("Successfully Taped On More Info Icon");
		boolean isDisplayed=verifyElementIsDisplayedAreNot(smis.ManualText);
		if (isDisplayed) {
			logger.info("Element Proxy Manual Toggle Button Is in On");
		}
		else {
		logger.info("Element Proxy Manual Toggle Button Is in off");
		}
		if (isDisplayed==proxyButtonManual) {
			smis.ConfigureProxyButton.click();
			logger.info("Successfully Clicked On Configure Proxy Button");
			verifyElementIsHavingExpectedText(scps.ConfigureProxyText, configureProxyScreenVerification,ignoreCase );
			logger.info("Configure Proxy Text Element Is Having Expected "+configureProxyScreenVerification+" Text");
		    tap(scps.OffButton, driver);
		    logger.info("Successfully Taped On Off Button");
		    tap(scps.SaveButton, driver);
		    logger.info("Successfully Taped On Save Button");
		    logger.info("Successfully Turned Off The Proxy");
		}
		closeTheApplication();
		logger.info("******************* Setting Application Closed Successfully *******************");
	}

	@Then("Verify proxy popup is not appearing in ageas")
	public void verify_proxy_popup_is_not_appearing_in_ageas() throws IOException {
		driver=openAgeasApplication();
		//Giving Driver Life To Respective Pages
		as=new ageasScreen(driver);
		logger.info("******************* Ageas Application Opened Successfully *******************");
		//Fetching datas
		String ageasSecurityScreenVerification = fetchDatFromMap(testConfigKeyValue, "Ageas_Security_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String expectedTextPopUp = fetchDatFromMap(AG001KeyValue, "Expected_Text_Pop_Up");
		//Steps
		verifyElementIsHavingExpectedText(as.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		logger.info("Welcome  Element Is Having Expected "+ageasSecurityScreenVerification+" Text");
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Not Present In Ageas Screen");
		closeTheApplication();
		logger.info("******************* Ageas Application Closed Successfully *******************");
	}
	
//**************************************************************************************************************************************          			
    //AG002 Verify VPN popup
	
	@And("Tap On general icon")
	public void tap_on_general_icon() {
	   //Giving Driver Life To Respective Pages
	   sls=new settingLandingScreen(driver);
	   sgs=new settingGeneralScreen(driver);
	   svadms=new settingVpnAndDeviceManagementScreen(driver);
	   svs=new settingVpnScreen(driver);
	   //Steps
	   sls.GeneralIcon.click();
	   logger.info("Successfully Clicked On General Icon Button");
	}

	@And("Tap On VPN and device management icon")
	public void tap_on_vpn_and_device_management_icon() {
		//Fetching datas
		String generalScreenVerification = fetchDatFromMap(AG002KeyValue, "General_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		verifyElementIsHavingExpectedText(sgs.GeneralText, generalScreenVerification, ignoreCase);
		logger.info("General Element Is Having Expected "+generalScreenVerification+" Text");
		sgs.ManagedConfigurationListButton.click();
		logger.info("Successfully Clicked On Managed Configuration List Button Button");
	}

	@Then("Verify if VPN is in not connected Turn on the VPN")
	public void verify_if_vpn_is_in_not_connected_turn_on_the_vpn() throws IOException {
		//Fetching datas
		String vpnAndDeviceManagementVerification = fetchDatFromMap(AG002KeyValue, "VPN_And_Device_Management_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String notConnectedStatus = fetchDatFromMap(AG002KeyValue, "Not_Connected_Status");
		String vpnScreenVerification = fetchDatFromMap(AG002KeyValue, "Vpn_Screen_Verification");
		//Steps
		verifyElementIsHavingExpectedText(svadms.VpnAndDeviceManagementText, vpnAndDeviceManagementVerification, ignoreCase);
		logger.info("Vpn And Device Management Element Is Having Expected "+vpnAndDeviceManagementVerification+" Text");
	    String obtainedText=getTextFromElement(svadms.VPNStatusText);
        logger.info("Successfully Featched "+obtainedText+" Text From VPN Status Element");
	    if(obtainedText.equals(notConnectedStatus)) {
	    	logger.info(obtainedText+" Is Equals "+notConnectedStatus);
	    	tap(svadms.VPNStatusText, driver);
	    	logger.info("Successfully Tapped On VPN Status Element");
	    	verifyElementIsHavingExpectedText(svs.VPNText, vpnScreenVerification, ignoreCase);
	    	logger.info("VPN Element Is Having Expected "+vpnScreenVerification+" Text");
	    	svs.VPNStatusToggleButton.click();
	    	logger.info("Successfully Clicked On VPN Status Toggle Button Element");
	    }
	}

	@When("VPN popup is present in ageas application or not")
	public void vpn_popup_is_present_in_ageas_application_or_not() throws EncryptedDocumentException, IOException {
		//Fetching datas
		String expectedTextPopUp = fetchDatFromMap(AG002KeyValue, "Expected_Text_Pop_Up");
		//Steps
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Present In Ageas Screen");
		String arrayListToString=arrayListToString(popUpTextTitle);
		//Write Back The Data To Excel
		WriteDataInToExcel(excelPath,ageasSheetName, uniqueDataAG002,"Pop_Up_Title_Text_List", arrayListToString, isTableHorizontal);
		logger.info("Successfully written "+arrayListToString+" Pop-Up Into Excel");
	}

	@Then("Verify VPN network identified header")
	public void verify_vpn_network_identified_header() {
		//Fetching datas
		String vpnNetworkIdentifiedVerification = fetchDatFromMap(AG002KeyValue, "VPN_Network_Identified_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
	    boolean vpnNetworkIdentifiedTextResult=verifyElementIsHavingExpectedText(as.VPNNetworkIdentifiedText,vpnNetworkIdentifiedVerification, ignoreCase);
		Assert.assertTrue(vpnNetworkIdentifiedTextResult);
		logger.info("VPN Network header is Present in the Ageas "+vpnNetworkIdentifiedTextResult);
	}

	@Then("Verify VPN network identified message")
	public void verify_vpn_network_identified_message() {
		//Fetching datas
		String popupMessageVerification = fetchDatFromMap(AG002KeyValue, "Popup_Message_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		boolean vpnNetworkIdentifiedMessageTextResult= verifyElementIsHavingExpectedText(as.VPNNetworkIdentifiedMessageText,popupMessageVerification, ignoreCase);
	    Assert.assertTrue(vpnNetworkIdentifiedMessageTextResult);
		logger.info("VPN Network header Boday is Present in the Ageas "+vpnNetworkIdentifiedMessageTextResult);
	}

	@And("Negative validation turn Off VPN")
	public void negative_validation_turn_off_vpn() throws IOException {
	   driver=openSettingApplication();
	   logger.info("******************* Settings Application Opened Successfully *******************");
	   //Giving Driver Life To Respective Pages
	   sls=new settingLandingScreen(driver);
	   sgs=new settingGeneralScreen(driver);
	   svadms=new settingVpnAndDeviceManagementScreen(driver);
	   svs=new settingVpnScreen(driver);
	   //Fetching datas
	   String generalScreenVerification = fetchDatFromMap(AG002KeyValue, "General_Screen_Verification");
	   String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
	   boolean ignoreCase=stringToBoolean(ignoreCaseInString);
	   String vpnAndDeviceManagementVerification = fetchDatFromMap(AG002KeyValue, "VPN_And_Device_Management_Verification");
	   String connectedStatus = fetchDatFromMap(AG002KeyValue, "Connected_Status");
	   String vpnScreenVerification = fetchDatFromMap(AG002KeyValue, "Vpn_Screen_Verification");
		
	   //Steps
	   sls.GeneralIcon.click();
	   logger.info("Successfully Clicked On General Icon Button");
	   verifyElementIsHavingExpectedText(sgs.GeneralText, generalScreenVerification, ignoreCase);
	   logger.info("General Element Is Having Expected "+generalScreenVerification+" Text");
	   sgs.ManagedConfigurationListButton.click();
	   logger.info("Successfully Clicked On Managed Configuration List Button Button");
	   verifyElementIsHavingExpectedText(svadms.VpnAndDeviceManagementText, vpnAndDeviceManagementVerification, ignoreCase);
	   logger.info("Vpn And Device Management Element Is Having Expected "+vpnAndDeviceManagementVerification+" Text");
	   String obtainedText=getTextFromElement(svadms.VPNStatusText);
       logger.info("Successfully Featched "+obtainedText+" Text From VPN Status Element");
	   if(obtainedText.equals(connectedStatus)) {
	    	logger.info(obtainedText+" Is Equals "+connectedStatus);
	    	tap(svadms.VPNStatusText, driver);
	    	logger.info("Successfully Tapped On VPN Status Element");
	    	verifyElementIsHavingExpectedText(svs.VPNText, vpnScreenVerification, ignoreCase);
	    	logger.info("VPN Element Is Having Expected "+vpnScreenVerification+" Text");
	    	svs.VPNStatusToggleButton.click();
	    	logger.info("Successfully Turned Off VPN");
	      }
	    closeTheApplication();
		logger.info("******************* Setting Application Closed Successfully *******************");
	 }
	 
	@Then("Verify VPN popup is not appearing in ageas")
	public void verify_vpn_popup_is_not_appearing_in_ageas() throws IOException {
		driver=openAgeasApplication();
		//Giving Driver Life To Respective Pages
		as=new ageasScreen(driver);
		logger.info("******************* Ageas Application Opened Successfully *******************");
		//Fetching datas
		String ageasSecurityScreenVerification = fetchDatFromMap(testConfigKeyValue, "Ageas_Security_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String expectedTextPopUp = fetchDatFromMap(AG002KeyValue, "Expected_Text_Pop_Up");
		//Steps
		verifyElementIsHavingExpectedText(as.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		logger.info("Welcome  Element Is Having Expected "+ageasSecurityScreenVerification+" Text");
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Not Present In Ageas Screen");
		closeTheApplication();
		logger.info("******************* Ageas Application Closed Successfully *******************");
	}
	
//**************************************************************************************************************************************          			
    //AG003 Verify screen mirroring popup
	
	@Given("Launch the gmail application")
	public void launch_the_gmail_application() throws IOException {
	   driver=openGmailApplication();
	   logger.info("******************* Gmail Application Opened Successfully *******************");
	   //Giving Driver Life To Respective Pages
	   gls=new gmailLandingScreen(driver);
	   gms=new gmailMeetScreen(driver);
	   gytohs=new gmailYoureTheOnlyOneHereScreen(driver);
	   //Fetching datas
	   String gmailScreenVerification = fetchDatFromMap(testConfigKeyValue, "Gmail_Screen_Verification");
	   String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
	   boolean ignoreCase=stringToBoolean(ignoreCaseInString);
	   //Steps
	   verifyElementIsHavingExpectedText(gls.MettButton, gmailScreenVerification, ignoreCase);
	   logger.info("Mett Button Element Is Having Expected "+gmailScreenVerification+" Text");
	}

	@And("Tap on meeting icon and tap on new meeting icon")
	public void tap_on_meeting_icon_and_tap_on_new_meeting_icon() throws IOException, InterruptedException {
		//Fetching datas
		String meetScreenVerification = fetchDatFromMap(AG003KeyValue, "Meet_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String startAnInstantMeetingOptionVerification = fetchDatFromMap(AG003KeyValue, "Start_An_Instant_Meeting_Option_Verification");
		//Steps
	    tap(gls.MettButton, driver);
	    logger.info("Successfully Tapped On Mett Button Element");
	    verifyElementIsHavingExpectedText(gms.NewMettingButton, meetScreenVerification, ignoreCase);
	    logger.info("New Metting Button Element Is Having Expected "+meetScreenVerification+" Text");
	    Thread.sleep(2000);
	    tap(gms.NewMettingButton, driver);
	    logger.info("Successfully Tapped On New Metting Button Element");
	    verifyElementIsHavingExpectedText(gms.InstantMeetingButton, startAnInstantMeetingOptionVerification, ignoreCase);
	    logger.info("Instant Meeting Button Element Is Having Expected "+startAnInstantMeetingOptionVerification+" Text");
	    tap(gms.InstantMeetingButton, driver);
	    logger.info("Successfully Tapped On Instant Meeting Button Element");
	}

	@And("Tap On start an instant meeting")
	public void tap_on_start_an_instant_meeting() throws IOException {
		//Fetching datas
		String shareScreenOptionVerification = fetchDatFromMap(AG003KeyValue, "Share_Screen_Option_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
	    tap(gytohs.OptionsButton, driver);
	    logger.info("Successfully Tapped On Options Button Element");
	    verifyElementIsHavingExpectedText(gytohs.ShareScreenButton, shareScreenOptionVerification, ignoreCase);
	    logger.info("Share Screen Button Element Is Having Expected "+shareScreenOptionVerification+" Text");
	    tap(gytohs.ShareScreenButton, driver);
	    logger.info("Successfully Tapped On Share Screen Button Element");
	}

	@Then("Verify you are shareing the screen")
	public void verify_you_are_shareing_the_screen() throws IOException, InterruptedException {
		//Fetching datas
		String presentToEveryoneScreenVerification = fetchDatFromMap(AG003KeyValue, "Present_To_Everyone_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String startBroadcastScreenVerification = fetchDatFromMap(AG003KeyValue, "Start_Broadcast_Screen_Verification");
		String vysourPopupTextIsOrNot = fetchDatFromMap(AG003KeyValue, "Vysour _Popup_Text_Is_Or_Not");
		boolean vysourPopupText=stringToBoolean(vysourPopupTextIsOrNot);
		//Steps
	    verifyElementIsHavingExpectedText(gytohs.PresentToEveryoneText, presentToEveryoneScreenVerification, ignoreCase);
	    logger.info("Present To Everyone Text Element Is Having Expected "+presentToEveryoneScreenVerification+" Text");
	    tap(gytohs.ContinueButton, driver);
	    logger.info("Successfully Tapped On Continue Button Element");
	    verifyElementIsHavingExpectedText(gytohs.StattBroadcastButton, startBroadcastScreenVerification, ignoreCase);
	    logger.info("Statt Broadcast Button Element Is Having Expected "+startBroadcastScreenVerification+" Text");
	    tap(gytohs.StattBroadcastButton, driver);
	    logger.info("Successfully Tapped On Statt Broadcast Button Element");
	    boolean isDisplayed=verifyElementIsDisplayedAreNot(gytohs.vysourPopupText);
	    if (isDisplayed==vysourPopupText) {
	    	logger.info(isDisplayed+" is Equale to "+vysourPopupText);
			tap(gytohs.OkButton, driver);
			logger.info("Successfully Tapped On Ok Button Element");
		}
	    pressHomeButton();
	    logger.info("Successfully Navigated To Home screen");
	    WaitUntilvisibilityOfElement(gytohs.MeetingLobbyButton);
	    logger.info("Successfully Waited Untill visibility Of Meeting Lobby Button Element");
	    pressHomeButton();
	    logger.info("Successfully Navigated To Home screen");
	    logger.info("Successfully Shreared The Screen");
	}

	@And("Close the gmail application")
	public void close_the_gmail_application() {
	   closeTheApplication();
	   logger.info("******************* Gmail Application Closed Successfully *******************");
	}

	@When("Screen mirroring popup is present in ageas application or not")
	public void screen_mirroring_popup_is_present_in_ageas_application_or_not() throws EncryptedDocumentException, IOException {
		//Fetching datas
		String expectedTextPopUp = fetchDatFromMap(AG003KeyValue, "Expected_Text_Pop_Up");
		String screenBroadcast = fetchDatFromMap(AG003KeyValue, "screenBroadcastIsOrNot");
		boolean screenBroadcastIsOrNot=stringToBoolean(screenBroadcast);
		//Steps
		boolean brodacastScrrenText=verifyElementIsDisplayedAreNot(as.ScreenBroadcastingText);
		   if (brodacastScrrenText==screenBroadcastIsOrNot) {
			   logger.info(brodacastScrrenText+" is Equale to "+screenBroadcastIsOrNot);
			   tap(as.OkButton, driver);
			   logger.info("Successfully Tapped On Ok Button Element");
			}
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Present In Ageas Screen");
		String arrayListToString=arrayListToString(popUpTextTitle);
		//Write Back The Data To Excel
		WriteDataInToExcel(excelPath,ageasSheetName, uniqueDataAG003,"Pop_Up_Title_Text_List", arrayListToString, isTableHorizontal);
		logger.info("Successfully written "+arrayListToString+" Pop-Up Into Excel");
	}


	@Then("Verify screen mirroring header")
	public void verify_screen_mirroring_header() {
		//Fetching datas
		String screenMirroringDetectedPopupVerification = fetchDatFromMap(AG003KeyValue, "Screen_Mirroring_Detected_Popup_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
	    boolean screenMirroringIdentifiedTextResult=verifyElementIsHavingExpectedText(as.ScreenMirroringIdentifiedText,screenMirroringDetectedPopupVerification, ignoreCase);
		Assert.assertTrue(screenMirroringIdentifiedTextResult);
		logger.info("Screen Mirroring Identified Text Result header is Present in the Ageas "+screenMirroringIdentifiedTextResult);
	}

	@Then("Verify screen mirroring message")
	public void verify_screen_mirroring_message() {
		//Fetching datas
		String popupMessageVerification = fetchDatFromMap(AG003KeyValue, "Popup_Message_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		//Steps
		boolean screenMirroringIdentifiedMessageTextResult= verifyElementIsHavingExpectedText(as.ScreenMirroringIdentifiedMessageText,popupMessageVerification, ignoreCase);
	    Assert.assertTrue(screenMirroringIdentifiedMessageTextResult);
		logger.info("Screen Mirroring Identified Boday is Present in the Ageas "+screenMirroringIdentifiedMessageTextResult);
	}

	@And("Negative validation turn Off screen mirroring")
	public void negative_validation_turn_off_screen_mirroring() throws IOException {
		driver = openSettingApplication();
		logger.info("******************* Settings Application Opened Successfully *******************");
		
	}

	@Then("Verify screen mirroring popup is not appearing in ageas")
	public void verify_screen_mirroring_popup_is_not_appearing_in_ageas() {
		//Fetching datas
		String ageasSecurityScreenVerification = fetchDatFromMap(testConfigKeyValue, "Ageas_Security_Screen_Verification");
		String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
		boolean ignoreCase=stringToBoolean(ignoreCaseInString);
		String expectedTextPopUp = fetchDatFromMap(AG003KeyValue, "Expected_Text_Pop_Up");
		//Steps
		verifyElementIsHavingExpectedText(as.WelcomeText, ageasSecurityScreenVerification, ignoreCase);
		logger.info("Welcome  Element Is Having Expected "+ageasSecurityScreenVerification+" Text");
		ArrayList<String> popUpTextTitle=new ArrayList<String>();
		popUpTextTitle=verifyElementIsHavingExpectedTextAndAddThatTexttoList(as.skipButton, as.IdentifiedText, expectedTextPopUp);
		logger.info(popUpTextTitle+" Pop-Up Is Not Present In Ageas Screen");
		closeTheApplication();
		logger.info("******************* Ageas Application Closed Successfully *******************");
			}

//**************************************************************************************************************************************          			
	//Post-Conditions
	@After
	public void takeScraenshotOnFailureAndTearDownBrowser(Scenario scenario) throws IOException, InterruptedException {
		testConfigKeyValue = getDataFromExcel(excelPath, testConfigSheetName, uniqueDataTestConfig, isTableVertical);
		String screenshotType = fetchDatFromMap(testConfigKeyValue, "Screenshoot_Type");
		String testCaseNameOfAG001= fetchDatFromMap(AG001KeyValue, "Test_Case_Name");
		String testCaseNameOfAG002= fetchDatFromMap(AG002KeyValue, "Test_Case_Name");
		String testCaseNameOfAG003= fetchDatFromMap(AG003KeyValue, "Test_Case_Name");

		// Take Screenshoot On Failure
		if (scenario.isFailed()) {
			TakesScreenshot tsc = (TakesScreenshot) driver;
			File screenshot = tsc.getScreenshotAs(OutputType.FILE);
			String screenshotName = scenario.getName();
			File file = new File(screenshootFilePath + screenshotName + screenshotType);
			FileUtils.copyFile(screenshot, file);
			logger.info("Successfully Captured Screenshot Of The " + screenshotName + " And Strored In Screenshots Floder");
			
			
			//If scenario is failed due to some reason turn off the proxy
			String scenarioName=scenario.getName();
			if (scenarioName.equals(testCaseNameOfAG001)) {
				driver = openSettingApplication();
				//Giving Driver Life To Respective Pages
				sls = new settingLandingScreen(driver);
				sws =new settingWifiScreen(driver);
				smis=new settingMoreInfoScreen(driver);
				scps=new settingConfigureProxyScreen(driver);
				logger.info("******************* Settings Application Opened Successfully *******************");
				//Fetching datas
				String settingsScreenVerification = fetchDatFromMap(testConfigKeyValue, "Settings_Screen_Verification");
				String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
				boolean ignoreCase=stringToBoolean(ignoreCaseInString);
				String wifiTextVerification = fetchDatFromMap(AG001KeyValue, "Wifi_Text_Verification");
				String ifProxyInManual = fetchDatFromMap(AG001KeyValue, "If_Proxy_In_Manual");
				boolean proxyButtonManual=stringToBoolean(ifProxyInManual);
				String configureProxyScreenVerification = fetchDatFromMap(AG001KeyValue, "Configure_Proxy_Screen_Verification");
				//Steps
				verifyElementIsHavingExpectedText(sls.WiFiButton, settingsScreenVerification, ignoreCase);
				logger.info("WiFi Button Element Is Having Expected "+settingsScreenVerification+" Text");
				tap(sls.WiFiButton, driver);
			    logger.info("Successfully Taped On WiFi Button");
			    verifyElementIsHavingExpectedText(sws.WiFiText, wifiTextVerification, ignoreCase);
				logger.info("WiFi Text Element Is Having Expected "+wifiTextVerification+" Text");
				tap(sws.MoreInfoIcon, driver);
				logger.info("Successfully Taped On More Info Icon");
				boolean isDisplayed=verifyElementIsDisplayedAreNot(smis.ManualText);
				if (isDisplayed) {
					logger.info("Element Proxy Manual Toggle Button Is in On");
				}
				else {
				logger.info("Element Proxy Manual Toggle Button Is in off");
				}
				if (isDisplayed==proxyButtonManual) {
					smis.ConfigureProxyButton.click();
					logger.info("Successfully Clicked On Configure Proxy Button");
					verifyElementIsHavingExpectedText(scps.ConfigureProxyText, configureProxyScreenVerification,ignoreCase );
					logger.info("Configure Proxy Text Element Is Having Expected "+configureProxyScreenVerification+" Text");
				    tap(scps.OffButton, driver);
				    logger.info("Successfully Taped On Off Button");
				    tap(scps.SaveButton, driver);
				    logger.info("Successfully Taped On Save Button");
				    logger.info("Successfully Turned Off The Proxy");
				}
			}
			
			else if (scenarioName.equals(testCaseNameOfAG002)) {
				   driver=openSettingApplication();
				   //Giving Driver Life To Respective Pages
				   sls=new settingLandingScreen(driver);
				   sgs=new settingGeneralScreen(driver);
				   svadms=new settingVpnAndDeviceManagementScreen(driver);
				   svs=new settingVpnScreen(driver);
				   //Fetching datas
				   String generalScreenVerification = fetchDatFromMap(AG002KeyValue, "General_Screen_Verification");
				   String ignoreCaseInString = fetchDatFromMap(testConfigKeyValue, "Ignore_Case");
				   boolean ignoreCase=stringToBoolean(ignoreCaseInString);
				   String vpnAndDeviceManagementVerification = fetchDatFromMap(AG002KeyValue, "VPN_And_Device_Management_Verification");
				   String connectedStatus = fetchDatFromMap(AG002KeyValue, "Connected_Status");
				   String vpnScreenVerification = fetchDatFromMap(AG002KeyValue, "Vpn_Screen_Verification");
					
				   //Steps
				   sls.GeneralIcon.click();
				   logger.info("Successfully Clicked On General Icon Button");
				   verifyElementIsHavingExpectedText(sgs.GeneralText, generalScreenVerification, ignoreCase);
				   logger.info("General Element Is Having Expected "+generalScreenVerification+" Text");
				   sgs.ManagedConfigurationListButton.click();
				   logger.info("Successfully Clicked On Managed Configuration List Button Button");
				   verifyElementIsHavingExpectedText(svadms.VpnAndDeviceManagementText, vpnAndDeviceManagementVerification, ignoreCase);
				   logger.info("Vpn And Device Management Element Is Having Expected "+vpnAndDeviceManagementVerification+" Text");
				   String obtainedText=getTextFromElement(svadms.VPNStatusText);
			       logger.info("Successfully Featched "+obtainedText+" Text From VPN Status Element");
				    if(obtainedText.equals(connectedStatus)) {
				    	logger.info(obtainedText+" Is Equals "+connectedStatus);
				    	tap(svadms.VPNStatusText, driver);
				    	logger.info("Successfully Tapped On VPN Status Element");
				    	verifyElementIsHavingExpectedText(svs.VPNText, vpnScreenVerification, ignoreCase);
				    	logger.info("VPN Element Is Having Expected "+vpnScreenVerification+" Text");
				    	svs.VPNStatusToggleButton.click();
				    	logger.info("Successfully Turned Off VPN");
				      }
				 }
			
			else if (scenarioName.equals(testCaseNameOfAG003)) {
				logger.info("It Is Screen Mirroring No Need Of Turn Off");
			}
		
			
			// Tear Down Browser
			Thread.sleep(2000);
			closeTheApplication();
			logger.info("******************* Application Closedr Closed Successfully *******************");
		}
	}

}
