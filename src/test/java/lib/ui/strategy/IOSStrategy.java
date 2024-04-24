package lib.ui.strategy;

import io.appium.java_client.AppiumDriver;
import lib.ui.PageBase;
import org.openqa.selenium.WebElement;

public class IOSStrategy implements PageActionsStrategy {
    private PageBase pageBase;

    private static final String TEXT_ELEMENT_TPL = "";

    public IOSStrategy(AppiumDriver driver) {
        this.pageBase = new PageBase(driver, this);
    }
    @Override
    public WebElement getElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://XCUIElementTypeStaticText[@name='%s']", text);
        return pageBase.waitForElementPresent(xpathWithExpectedText,
                "Could not find element by text: " + text,
                10);
    }

    @Override
    public void clickElementByText(String text) {
//        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        String xpathWithExpectedText = String.format("xpath://XCUIElementTypeStaticText[@name='%s']", text);
        pageBase.waitForElementAndClick(
                xpathWithExpectedText,
                "Could not select item with text: " + text,
                5);
    }
}
