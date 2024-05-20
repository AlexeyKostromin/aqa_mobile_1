package lib.ui.strategy;

import io.appium.java_client.android.AndroidDriver;
import lib.BasePage;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class AndroidStrategy implements PageActionsStrategy {
    private BasePage basePage;
    private AndroidDriver AndroidDriver;

    public AndroidStrategy(RemoteWebDriver driver) {
        this.basePage = new BasePage(driver, this);
        AndroidDriver = ((AndroidDriver) driver);
    }

    @Override
    public WebElement getElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        return basePage.waitForElementPresent(xpathWithExpectedText,
                "Could not find element by text: " + text,
                10);
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        basePage.waitForElementAndClick(
                xpathWithExpectedText,
                "Could not select item with text: " + text,
                5);
    }

    @Override
    public void setLandscapeOrientation() {
        AndroidDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    @Override
    public void setPortraitOrientation() {
        AndroidDriver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void runAppInBackground(Duration duration) {
        AndroidDriver.runAppInBackground(duration);
    }


}
