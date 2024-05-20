package lib.ui;

import lib.BasePage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class WelcomePage extends BasePage {

    protected static String
            STEP_MAIN_WIKIPEDIA,
            STEP_TRY_FREE_ENCYCLOPEDIA,
            STEP_SKIP_BTN,
            STEP_NEXT_BTN,
            STEP_NEW_WAYS_LABEL,
            STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN,
            STEP_HELP_LEARN_MORE_BTN,
            STEP_HELP_GET_STARTED_BTN;


    public WelcomePage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public void waitForFreeEncyclopediaScreenLoaded() {
        waitForElementPresent(STEP_TRY_FREE_ENCYCLOPEDIA, "Free encyclopedia screen not loaded", 10);
    }

    public void clickSkip() {
        waitForElementAndClick(STEP_SKIP_BTN, "Cannot click button Next", 5);
    }

    public void clickNext() {
//        waitForElementAndClick(STEP_NEXT_BTN, "Cannot click button Next", 5);
        waitNextBtnClickable().click();
    }

    private WebElement waitNextBtnClickable() {
        return waitForElementToBeClickable(STEP_NEXT_BTN, "Next button not clickable", 10);
    }

    public void waitForNewWaysScreenLoaded() {
        waitForElementToBeClickable(STEP_NEW_WAYS_LABEL, "Free encyclopedia screen not loaded", 10);
        waitNextBtnClickable();
    }

    public void waitSearchLanguagesScreenLoaded() {
        waitForElementToBeClickable(STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN, "Search language screen not loaded", 10);
        waitNextBtnClickable();
    }

    public void waitHelpAppBetterScreenLoaded() {
        waitForElementToBeClickable(STEP_HELP_LEARN_MORE_BTN, "Help App Better screen not loaded", 10);
        waitForElementToBeClickable(STEP_HELP_GET_STARTED_BTN, "Button Get started not clickable", 5);
    }

    public void clickGetStarted() {
        waitForElementAndClick(STEP_HELP_GET_STARTED_BTN, "Cannot click button Get started", 5);
    }

    public void swipeOnboardingScreen() {
//        try { Thread.sleep(1000); } catch (InterruptedException e) { /* Do nothing */ }
        var element = waitForElementToBeClickable(STEP_MAIN_WIKIPEDIA, "wiki element not clickaple", 10);
        swipeElementToLeft(element);
    }

}
