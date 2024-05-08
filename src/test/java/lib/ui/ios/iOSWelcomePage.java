package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePage;
import lib.ui.strategy.PageActionsStrategy;


public class iOSWelcomePage extends WelcomePage {
    public iOSWelcomePage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    static {
        STEP_MAIN_WIKIPEDIA = "id:Wikipedia";
        STEP_TRY_FREE_ENCYCLOPEDIA = "id:The free encyclopedia";
        STEP_SKIP_BTN = "xpath://XCUIElementTypeButton[@name='Skip']";
        STEP_NEXT_BTN = "xpath://XCUIElementTypeButton[@name='Next']";
        STEP_NEW_WAYS_LABEL = "id:New ways to explore";
        STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']";
        STEP_HELP_LEARN_MORE_BTN = "xpath://XCUIElementTypeButton[@name='Learn more about data collected']";
        STEP_HELP_GET_STARTED_BTN = "xpath://XCUIElementTypeButton[@name='Get started']";
    }
}
