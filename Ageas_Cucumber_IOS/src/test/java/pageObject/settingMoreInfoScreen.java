package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingMoreInfoScreen {

	// Giving life to driver by Constracor
	public settingMoreInfoScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//**************************************************************************************************************************************		

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Off']")
	public WebElement OffText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Configure Proxy']")
	public WebElement ConfigureProxyButton;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Manual']")
	public WebElement ManualText;
	
}
