package lib.ui;

import io.appium.java_client.AppiumDriver;

public class PageBaseIOS extends PageBase {
    public PageBaseIOS(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void clickElementByText(String text) {
        String xpathWithExpectedText = String.format("xpath://*[@text='%s']", text);
        waitForElementAndClick(
                xpathWithExpectedText,
                "Could not select item with text: " + text,
                5);
    }
}
