package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;
import lib.ui.SearchPage;
import lib.ui.android.AndroidSearchPage;
import lib.ui.ios.iOSSearchPage;

public class SearchPageFactory {

    public static SearchPage getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSSearchPage(driver, new IOSStrategy(driver));
        }
    }
}
