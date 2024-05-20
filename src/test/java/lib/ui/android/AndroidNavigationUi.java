package lib.ui.android;

import lib.ui.NavigationUi;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUi extends NavigationUi {
    static {
        CONTENTS_ITEM_LIST_BTN = "id:Contents";
        SAVED_ITEM_LIST_BTN = "id:org.wikipedia.alpha:id/nav_tab_reading_lists";
    }

    public AndroidNavigationUi(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

}
