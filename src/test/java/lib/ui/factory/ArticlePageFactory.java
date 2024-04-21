package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.ui.android.AndroidArticlePage;
import lib.ui.android.AndroidSearchPage;
import lib.ui.ios.iOSArticlePage;
import lib.ui.ios.iOSSearchPage;

public class ArticlePageFactory {
    public static ArticlePage getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePage(driver);
        } else {
            return new iOSArticlePage(driver);
        }
    }
}
