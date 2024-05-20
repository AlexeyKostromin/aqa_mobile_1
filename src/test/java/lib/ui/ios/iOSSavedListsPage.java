package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListsPage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSavedListsPage extends SavedListsPage {
    static {
        TOOLTIP_GOT_IT = "";
    }


    public iOSSavedListsPage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
