package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingVpnScreen {

	// Giving life to driver by Constracor
	public settingVpnScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

// **************************************************************************************************************************************
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='VPN']")
	public WebElement VPNText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Status']/..//XCUIElementTypeSwitch")
	public WebElement VPNStatusToggleButton;

	

}
