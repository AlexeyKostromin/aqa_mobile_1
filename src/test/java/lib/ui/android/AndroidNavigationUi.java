package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import lib.ui.PageBase;
import lib.ui.strategy.PageActionsStrategy;

public class AndroidNavigationUi extends NavigationUi {
    static {
        CONTENTS_ITEM_LIST_BTN = "id:Contents";
        SAVED_ITEM_LIST_BTN = "id:org.wikipedia.alpha:id/nav_tab_reading_lists";
    }

    public AndroidNavigationUi(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

}
