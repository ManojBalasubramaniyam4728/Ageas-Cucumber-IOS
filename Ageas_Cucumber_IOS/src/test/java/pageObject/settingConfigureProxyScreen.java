package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingConfigureProxyScreen {

	// Giving life to driver by Constracor
	public settingConfigureProxyScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//**************************************************************************************************************************************		
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Configure Proxy']")
	public WebElement ConfigureProxyText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Manual']")
	public WebElement ManualButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Server']")
	public WebElement ServerTextfield;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "// XCUIElementTypeTextField[@name='Port']")
	public WebElement PortTextfield;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "// XCUIElementTypeButton[@name='Save']")
	public WebElement SaveButton;
	
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Off']")
	public WebElement OffButton;

}
