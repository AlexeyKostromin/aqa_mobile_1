package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePage;


public class iOSWelcomePage extends WelcomePage {
    public iOSWelcomePage(AppiumDriver driver) {
        super(driver);
    }

    static {
        STEP_TRY_FREE_ENCYCLOPEDIA = "id:The free encyclopedia";
        STEP_SKIP_BTN = "xpath://XCUIElementTypeButton[@name='Skip']";
        STEP_NEXT_BTN = "xpath://XCUIElementTypeButton[@name='Next']";
        STEP_NEW_WAYS = "id:New ways to explore";
        STEP_SEARCH_LANGUAGE = "id:Search in nearly 300 languages";
        STEP_HELP_APP_BETTER = "id:Help make the app better";
        STEP_GET_STARTED_BTN = "xpath://XCUIElementTypeButton[@name='Get started']";
    }
}
