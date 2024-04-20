package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.WelcomePage;
import lib.ui.android.AndroidWelcomePage;
import lib.ui.ios.iOSWelcomePage;

public class WelcomePageFactory {
    public static WelcomePage getPage(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomePage(driver);
        } else {
            return new iOSWelcomePage(driver);
        }
    }
}
