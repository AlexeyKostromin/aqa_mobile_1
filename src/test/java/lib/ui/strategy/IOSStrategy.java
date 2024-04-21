package lib.ui.strategy;

import io.appium.java_client.AppiumDriver;
import lib.ui.PageBase;
import org.openqa.selenium.WebElement;

public class IOSStrategy implements PageActionsStrategy {
    private PageBase pageBase;

    public IOSStrategy(AppiumDriver driver) {
        this.pageBase = new PageBase(driver, this);
    }
    @Override
    public WebElement getElementByText(String text) {
        return null;
    }

    @Override
    public void clickElementByText(String text) {

    }
}
