package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingGeneralScreen {

	/// Giving life to driver by Constracor
	public settingGeneralScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//**************************************************************************************************************************************		
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='General']")
	public WebElement GeneralText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='ManagedConfigurationList']")
	public WebElement ManagedConfigurationListButton;

}
