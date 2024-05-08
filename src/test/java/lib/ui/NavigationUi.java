package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;

public abstract class NavigationUi extends PageBase {
    protected static String
            CONTENTS_ITEM_LIST_BTN,
            SAVED_ITEM_LIST_BTN;

    public NavigationUi(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver,strategy);
    }

    public void goToSavedItems() {
        waitForElementAndClick(SAVED_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }

    public void openContents(){
        waitForElementAndClick(CONTENTS_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }

}
