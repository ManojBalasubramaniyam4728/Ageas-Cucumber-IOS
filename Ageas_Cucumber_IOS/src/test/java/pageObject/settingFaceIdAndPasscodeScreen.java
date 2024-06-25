package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingFaceIdAndPasscodeScreen {
	/// Giving life to driver by Constracor
	public settingFaceIdAndPasscodeScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// **************************************************************************************************************************************
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Enter Passcode']")
	public WebElement EnterPasscodeText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='passcodeDot-outline'])[1]")
	public WebElement PasscodeTextfileds;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Face ID & Passcode']")
	public WebElement FaceAndIDPasscodeText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Turn Passcode Off']")
	public WebElement TurnPasscodeOffOption;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Turn Off Passcode']")
	public WebElement TurnPasscodeOffText;
	
}
