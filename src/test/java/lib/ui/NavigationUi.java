package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPage {
    private static final String
            SAVED_ITEM_LIST_BTN = "org.wikipedia.alpha:id/nav_tab_reading_lists";

    public NavigationUi(AndroidDriver driver) {
        super(driver);
    }

    public void goToSavedItems() {
        waitForElementAndClick(By.id(SAVED_ITEM_LIST_BTN), "Could not press Navigate Up", 5);
    }

}
