package pageObject;

import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ageasScreen {

	// Giving life to driver by Constracor
	public ageasScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

// **************************************************************************************************************************************
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Welcome']")
	public WebElement WelcomeText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Proxy Network Identified']")
	public WebElement ProxyNetworkIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='AFLI App identified your device is connected to an unsafe proxy network. Please connect to a safe network and try again. For more details, contact AFLI IT Help-Desk.']")
	public WebElement ProxyNetworkIdentifiedMessageText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Skip']")
	public WebElement skipButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Identified')]")
	public WebElement IdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='VPN Network Identified']")
	public WebElement VPNNetworkIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='AFLI App identified your device is connected to VPN network. For security reasons, please turn off the VPN network and try again. For more details, contact AFLI IT Help-Desk.']")
	public WebElement VPNNetworkIdentifiedMessageText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Screen Broadcasting']")
	public WebElement ScreenBroadcastingText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public WebElement OkButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Screen Mirroring Identified']")
	public WebElement ScreenMirroringIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='AFLI App identified screen sharing is enabled on your device, which may be unsafe. For more details, contact AFLI IT Help-Desk.']")
	public WebElement ScreenMirroringIdentifiedMessageText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Unsecured Wi-Fi Identified']")
	public WebElement UnsecuredWiFiIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='AFLI App identifiedunsecured Wi-Fi network is enabled on your device, which may be unsafe. For more details, contact AFLI IT Help-Desk']")
	public WebElement UnsecuredWiFiIdentifiedMessageText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Insecure App Installation Identified']")
	public WebElement insecureAppInstallationIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='AFLI App identified Insecure App Installation, which may be unsafe. For more details, contact AFLI IT Help-Desk']")
	public WebElement InsecureAppInstallationIdentifiedMessageText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Face ID & Passcode']")
	public WebElement DeviceLockDisabledIdentifiedText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='For security reasons, we recommend you to STOP screen sharing and thereafter, continue to use the YES Mobile app. For more details, please contact customer care 1800 1200.']")
	public WebElement DeviceLockDisabledIdentifiedMessageText;
}
