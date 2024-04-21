package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUi extends PageBase {
    private static final String
            SAVED_ITEM_LIST_BTN = "id:org.wikipedia.alpha:id/nav_tab_reading_lists";

    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void goToSavedItems() {
        waitForElementAndClick(SAVED_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }

}
