package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class gmailYoureTheOnlyOneHereScreen {

	// Giving life to driver by Constracor
	public gmailYoureTheOnlyOneHereScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// **************************************************************************************************************************************
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='You're the only one here']")
	public WebElement YoureTheOnlyOneHeretext;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='CallControls.overflowButton']")
	public WebElement OptionsButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Share screen']")
	public WebElement ShareScreenButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Present to everyone']")
	public WebElement PresentToEveryoneText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='AudioScreenShareAlert.continueButton']")
	public WebElement ContinueButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Start Broadcast']")
	public WebElement StattBroadcastButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Not available when AirPlay, CarPlay, Restrictions or screen mirroring is active.']/..//XCUIElementTypeStaticText[@name='Screen Broadcast']")
	public WebElement vysourPopupText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
	public WebElement OkButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Double tap to flip to rear camera']")
	public WebElement MeetingLobbyButton;

}
