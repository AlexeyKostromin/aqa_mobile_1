package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class SpecialPhoneActionsHelper {
    protected AppiumDriver driver;

    public SpecialPhoneActionsHelper() throws Exception {
        driver = Platform.getInstance().getAppiumDriver();
    }


    public void setPortraitOrientation() {
        if (driver instanceof AppiumDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
        }
    }

    public void setLandscapeOrientation() {
        if (driver instanceof AppiumDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
        }
    }

    public void runAppInBackground(Duration duration) {
        if (driver instanceof AppiumDriver) {
            ((AndroidDriver) driver).runAppInBackground(duration);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).runAppInBackground(duration);
        } else {
            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
        }
    }

//    public void rotateDeviceToLandscape() {
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//    }
//
//    public void rotateDeviceToPortrait() {
//        driver.rotate(ScreenOrientation.PORTRAIT);
//    }
//
//    public void runAppInBackground(Duration duration) {
//        driver.runAppInBackground(duration);
//    }
}
