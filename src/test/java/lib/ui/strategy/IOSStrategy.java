package lib.ui.strategy;

import io.appium.java_client.ios.IOSDriver;
import lib.PageBase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class IOSStrategy implements PageActionsStrategy {
    private PageBase pageBase;
    private IOSDriver IOSDriver;

    private static final String TEXT_ELEMENT_TPL = "xpath://XCUIElementTypeStaticText[@name=\"{TEXT}\"]";

    public IOSStrategy(RemoteWebDriver driver) {
        this.pageBase = new PageBase(driver, this);
        IOSDriver = ((IOSDriver) driver);
    }

    private static String getXpathForTextElement(String text) {
        return TEXT_ELEMENT_TPL
                .replace("{TEXT}", text);
    }

    @Override
    public WebElement getElementByText(String text) {
        String xpathWithExpectedText = getXpathForTextElement(text);
        return pageBase.waitForElementPresent(xpathWithExpectedText,
                "Could not find element by text: " + text,
                10);
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = getXpathForTextElement(text);
        pageBase.waitForElementAndClick(
                xpathWithExpectedText,
                "Could not select item with text: " + text,
                5);
    }

    @Override
    public void setLandscapeOrientation() {
        IOSDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @Override
    public void setPortraitOrientation() {
        IOSDriver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void runAppInBackground(Duration duration) {
        IOSDriver.runAppInBackground(duration);
    }


}
