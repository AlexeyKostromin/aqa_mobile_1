package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;

public abstract class NavigationUi extends PageBase {
    protected static String
            SAVED_ITEM_LIST_BTN;

    public NavigationUi(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver,strategy);
    }

    public void goToSavedItems() {
        waitForElementAndClick(SAVED_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }

}
