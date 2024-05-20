package lib.ui.factory;

import lib.Platform;
import lib.ui.*;
import lib.ui.android.*;
import lib.ui.ios.*;
import lib.ui.mobileWeb.ArticlePageMW;
import lib.ui.mobileWeb.SearchPageMW;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;
import lib.ui.strategy.MobileWebStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PageFactory {
    public static WelcomePage getWelcomePage(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSWelcomePage(driver, new IOSStrategy(driver));
        }
    }

    public static SearchPage getSearchPage(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPage(driver, new AndroidStrategy(driver));
        } if (Platform.getInstance().isIOS()){
            return new iOSSearchPage(driver, new IOSStrategy(driver));
        } else {
            return new SearchPageMW(driver, new MobileWebStrategy(driver));
        }
    }

    public static ArticlePage getArticlePage(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePage(driver, new AndroidStrategy(driver));
        } else if (Platform.getInstance().isIOS()) {
            return new iOSArticlePage(driver, new IOSStrategy(driver));
        } else {
            return new ArticlePageMW(driver, new MobileWebStrategy(driver));
        }
    }

    public static NavigationUi getNavigationUiPage(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver, new AndroidStrategy(driver));
        } else {
            return new iOSNavigationUi(driver, new IOSStrategy(driver));
        }
    }

    public static SavedListsPage getSavedListsPage(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedListsPage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSSavedListsPage(driver, new IOSStrategy(driver));
        }
    }

}
