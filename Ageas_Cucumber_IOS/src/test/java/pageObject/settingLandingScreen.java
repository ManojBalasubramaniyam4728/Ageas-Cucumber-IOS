package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingLandingScreen {

	// Giving life to driver by Constracor
	public settingLandingScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//**************************************************************************************************************************************		
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Settings']")
	public WebElement SettingsText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Wi-Fi']")
	public WebElement WiFiButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='General']")
	public WebElement GeneralIcon;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Face ID & Passcode']")
	public WebElement FaceIDAndPasscodeIcon;

}
