package lib.ui.ios;

import lib.ui.NavigationUi;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUi extends NavigationUi {
    static {
        CONTENTS_ITEM_LIST_BTN = "id:Table of contents";
        SAVED_ITEM_LIST_BTN = "id:Saved";
    }

    public iOSNavigationUi(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }


}
