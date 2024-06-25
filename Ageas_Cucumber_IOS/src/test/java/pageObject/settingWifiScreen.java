package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class settingWifiScreen {

	// Giving life to driver by Constracor
	public settingWifiScreen(IOSDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//**************************************************************************************************************************************	

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Wi‑Fi'])[1]")
	public WebElement WiFiText;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='More Info'])[1]")
	public WebElement MoreInfoIcon;

	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Unsecured Network']")
	public WebElement UnsecuredNetwork;
	
	// Locaters and Storing In Variable
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='MY NETWORKS']/following-sibling::XCUIElementTypeCell[1]")
	public WebElement MyNetwork;
	

	

}
