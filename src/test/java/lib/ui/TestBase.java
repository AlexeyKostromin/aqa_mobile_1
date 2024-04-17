package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.Platform;
import lib.SpecialActionsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        Platform platform = new Platform();
        driver = platform.getAppiumDriver();
        SpecialActionsHelper specialActionsHelper = new SpecialActionsHelper();
        specialActionsHelper.setPortraitOrientation();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

//    private void setPortraitOrientation() {
//        if (driver instanceof AppiumDriver) {
//            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
//        } else if (driver instanceof IOSDriver) {
//            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
//        } else {
//            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
//        }
//    }

//    private void setPortraitOrientation() {
//        ScreenOrientation currentOrientation = driver.getOrientation();
//        if (currentOrientation != ScreenOrientation.PORTRAIT) {
//            System.out.println("The device orientation was not Portrait, rotating device to Portrait");
//            driver.rotate(ScreenOrientation.PORTRAIT);
//        }
//    }


//    private void setPortraitOrientation() {
//        String platform = System.getenv("PLATFORM");
//
//        if (platform.equals(PLATFORM_ANDROID)) {
//            ScreenOrientation currentOrientation = ((AndroidDriver) driver).getOrientation();
//            if (currentOrientation != ScreenOrientation.PORTRAIT) {
//                System.out.println("The device orientation was not Portrait, rotating device to Portrait");
//                ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
//            }
//
//        } else if (platform.equals(PLATFORM_IOS)) {
//            ScreenOrientation currentOrientation = ((IOSDriver) driver).getOrientation();
//            if (currentOrientation != ScreenOrientation.PORTRAIT) {
//                System.out.println("The device orientation was not Portrait, rotating device to Portrait");
//                ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
//            }
//        } else {
//
//            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
//        }
//    }

}
