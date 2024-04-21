package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;


abstract public class WelcomePage extends PageBase {

    protected static String
            STEP_TRY_FREE_ENCYCLOPEDIA,
            STEP_SKIP_BTN,
            STEP_NEXT_BTN,
            STEP_NEW_WAYS,
            STEP_SEARCH_LANGUAGE,
            STEP_HELP_APP_BETTER,
            STEP_GET_STARTED_BTN;


    public WelcomePage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public void waitForFreeEncyclopediaScreenLoaded() {
        waitForElementPresent(STEP_TRY_FREE_ENCYCLOPEDIA, "Free encyclopedia screen not loaded", 10);
    }

    public void clickSkip() {
        waitForElementAndClick(STEP_SKIP_BTN, "Cannot click button Next", 5);
    }

    public void clickNext() {
        waitForElementAndClick(STEP_NEXT_BTN, "Cannot click button Next", 5);
    }

    public void waitForNewWaysScreenLoaded() {
        waitForElementPresent(STEP_NEW_WAYS, "Free encyclopedia screen not loaded", 5);
    }

    public void waitSearchLanguagesScreenLoaded() {
        waitForElementPresent(STEP_SEARCH_LANGUAGE, "Search language screen not loaded", 5);
    }

    public void waitHelpAppBetterScreenLoaded() {
        waitForElementPresent(STEP_HELP_APP_BETTER, "Help App Better screen not loaded", 5);
    }

    public void clickGetStarted() {
        waitForElementAndClick(STEP_GET_STARTED_BTN, "Cannot click button Get started", 5);
    }

}
