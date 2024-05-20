package lib.ui.strategy;

import io.appium.java_client.ios.IOSDriver;
import lib.BasePage;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class IOSStrategy implements PageActionsStrategy {
    private BasePage basePage;
    private IOSDriver IOSDriver;

    private static final String TEXT_ELEMENT_TPL = "xpath://XCUIElementTypeStaticText[@name=\"{TEXT}\"]";

    public IOSStrategy(RemoteWebDriver driver) {
        this.basePage = new BasePage(driver, this);
        IOSDriver = ((IOSDriver) driver);
    }

    private static String getXpathForTextElement(String text) {
        return TEXT_ELEMENT_TPL
                .replace("{TEXT}", text);
    }

    @Override
    public WebElement getElementByText(String text) {
        String xpathWithExpectedText = getXpathForTextElement(text);
        return basePage.waitForElementPresent(xpathWithExpectedText,
                "Could not find element by text: " + text,
                10);
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = getXpathForTextElement(text);
        basePage.waitForElementAndClick(
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
