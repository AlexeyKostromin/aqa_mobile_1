package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUi;
import lib.ui.ios.iOSNavigationUi;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;

public class NavigationUIFactory {
    public static NavigationUi getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUi(driver, new AndroidStrategy(driver));
        } else {
            return new iOSNavigationUi(driver, new IOSStrategy(driver));
        }
    }
}
