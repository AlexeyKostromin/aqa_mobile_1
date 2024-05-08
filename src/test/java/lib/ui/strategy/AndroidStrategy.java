package lib.ui.strategy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.PageBase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class AndroidStrategy implements PageActionsStrategy {
    private PageBase pageBase;
    private AndroidDriver AndroidDriver;

    public AndroidStrategy(AppiumDriver driver) {
        this.pageBase = new PageBase(driver, this);
        AndroidDriver = ((AndroidDriver) driver);
    }

    @Override
    public WebElement getElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        return pageBase.waitForElementPresent(xpathWithExpectedText,
                "Could not find element by text: " + text,
                10);
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        pageBase.waitForElementAndClick(
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
