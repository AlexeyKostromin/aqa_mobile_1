package lib.ui.strategy;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface PageActionsStrategy {
    WebElement getElementByText(String text);
    void clickElementByText(String text);
    void setLandscapeOrientation();
    void setPortraitOrientation();
    void runAppInBackground(Duration duration);

}
