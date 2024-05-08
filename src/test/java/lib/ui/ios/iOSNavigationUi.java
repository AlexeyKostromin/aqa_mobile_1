package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import lib.ui.PageBase;
import lib.ui.strategy.PageActionsStrategy;

public class iOSNavigationUi extends NavigationUi {
    static {
        CONTENTS_ITEM_LIST_BTN = "id:Table of contents";
        SAVED_ITEM_LIST_BTN = "id:Saved";
    }

    public iOSNavigationUi(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }


}
