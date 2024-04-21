package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SavedListsPage;
import lib.ui.android.AndroidSavedListsPage;
import lib.ui.ios.iOSSavedListsPage;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;

public class SavedListPageFactory {
    public static SavedListsPage getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedListsPage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSSavedListsPage(driver, new IOSStrategy(driver));
        }
    }

}
