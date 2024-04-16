package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;


public class WelcomePage extends MainPage {

    private static final String
            STEP_TRY_FREE_ENCYCLOPEDIA = "The free encyclopedia",
            STEP_NEXT_BTN = "//XCUIElementTypeButton[@name='Next']",
            STEP_NEW_WAYS = "New ways to explore",
            STEP_SEARCH_LANGUAGE = "Search in nearly 300 languages",
            STEP_HELP_APP_BETTER = "Help make the app better",
            STEP_GET_STARTED_BTN = "//XCUIElementTypeButton[@name='Get started']";


    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }


    public WelcomePage WaitForFreeEncyclopediaScreenLoaded() {
        waitForElementPresent(By.id(STEP_TRY_FREE_ENCYCLOPEDIA), "Free encyclopedia screen not loaded", 10);
        return new WelcomePage(driver);
    }

    public WelcomePage ClickNext() {
        waitForElementAndClick(By.xpath(STEP_NEXT_BTN), "Cannot click button Next", 5);
        return new WelcomePage(driver);
    }

    public WelcomePage WaitForNewWaysScreenLoaded() {
        waitForElementPresent(By.id(STEP_NEW_WAYS), "Free encyclopedia screen not loaded", 5);
        return new WelcomePage(driver);
    }

    public WelcomePage WaitSearchLanguagesScreenLoaded() {
        waitForElementPresent(By.id(STEP_SEARCH_LANGUAGE), "Search language screen not loaded", 5);
        return new WelcomePage(driver);
    }

    public WelcomePage WaitHelpAppBetterScreenLoaded() {
        waitForElementPresent(By.id(STEP_HELP_APP_BETTER), "Help App Better screen not loaded", 5);
        return new WelcomePage(driver);
    }

    public WelcomePage ClickGetStarted() {
        waitForElementAndClick(By.xpath(STEP_GET_STARTED_BTN), "Cannot click button Get started", 5);
        return new WelcomePage(driver);
    }

}
