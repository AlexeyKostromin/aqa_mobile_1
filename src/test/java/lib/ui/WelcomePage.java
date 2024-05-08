package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.WebElement;


abstract public class WelcomePage extends PageBase {

    protected static String
            STEP_MAIN_WIKIPEDIA,
            STEP_TRY_FREE_ENCYCLOPEDIA,
            STEP_SKIP_BTN,
            STEP_NEXT_BTN,
            STEP_NEW_WAYS_LABEL,
            STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN,
            STEP_HELP_LEARN_MORE_BTN,
            STEP_HELP_GET_STARTED_BTN;


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
        waitForElementPresent(STEP_NEW_WAYS_LABEL, "Free encyclopedia screen not loaded", 10);
    }

    public void waitSearchLanguagesScreenLoaded() {
        waitForElementPresent(STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN, "Search language screen not loaded", 10);
    }

    public void waitHelpAppBetterScreenLoaded() {
        waitForElementPresent(STEP_HELP_LEARN_MORE_BTN, "Help App Better screen not loaded", 10);
    }

    public void clickGetStarted() {
        waitForElementAndClick(STEP_HELP_GET_STARTED_BTN, "Cannot click button Get started", 5);
    }

    public void swipeOnboardingScreen() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { /* Do nothing */ }
        var element = waitForElementPresent(STEP_MAIN_WIKIPEDIA, "wiki element not found", 10);
        swipeElementToLeft(element);
    }

//    public void swipeOnboardingScreen() {
//        try { Thread.sleep(1000); } catch (InterruptedException e) { /* Do nothing */ }
//        var element = waitForElementPresent(STEP_MAIN_WIKIPEDIA, "wiki element not found", 10);
//        swipeElementToLeftIOS(element);
//    }

}
