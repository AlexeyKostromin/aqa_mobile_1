package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.*;
import lib.ui.android.*;
import lib.ui.ios.*;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;

public class PageFactory {
    public static WelcomePage getWelcomePage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSWelcomePage(driver, new IOSStrategy(driver));
        }
    }

    public static SearchPage getSearchPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSSearchPage(driver, new IOSStrategy(driver));
        }
    }

    public static ArticlePage getArticlePage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSArticlePage(driver, new IOSStrategy(driver));
        }
    }

    public static NavigationUi getNavigationUiPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver, new AndroidStrategy(driver));
        } else {
            return new iOSNavigationUi(driver, new IOSStrategy(driver));
        }
    }

    public static SavedListsPage getSavedListsPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedListsPage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSSavedListsPage(driver, new IOSStrategy(driver));
        }
    }

}
