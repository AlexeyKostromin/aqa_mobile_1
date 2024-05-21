package lib.ui.strategy;

import lib.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class MobileWebStrategy implements PageActionsStrategy {
    private BasePage basePage;
    private RemoteWebDriver webDriver;

    public MobileWebStrategy(RemoteWebDriver driver) {
        this.basePage = new BasePage(driver, this);
        webDriver = ((RemoteWebDriver) driver);
    }

    @Override
    public WebElement getElementByText(String text) {
        return null;
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[contains(text(), '%s')]", text);
        basePage.waitForElementAndClick(
                xpathWithExpectedText,
                "Could not select item with text: " + text,
                5);
    }

    @Override
    public void setLandscapeOrientation() {

    }

    @Override
    public void setPortraitOrientation() {

    }

    @Override
    public void runAppInBackground(Duration duration) {

    }
}
