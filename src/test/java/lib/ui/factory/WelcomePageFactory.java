package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.WelcomePage;
import lib.ui.android.AndroidWelcomePage;
import lib.ui.ios.iOSWelcomePage;
import lib.ui.strategy.AndroidStrategy;
import lib.ui.strategy.IOSStrategy;

public class WelcomePageFactory {
    public static WelcomePage getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePage(driver, new AndroidStrategy(driver));
        } else {
            return new iOSWelcomePage(driver, new IOSStrategy(driver));
        }
    }
}
