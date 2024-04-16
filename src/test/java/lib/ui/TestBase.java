package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
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

    private final static String PLATFORM_ANDROID = "android";
    private final static String PLATFORM_IOS = "ios";

    protected WebDriver driver;
    private static String appiumLocalhostURL = "http://127.0.0.1:4723/";
    private static String platform;

    DesiredCapabilities capabilities;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        getPlatform();
        capabilities = getCapabilities();
        driver = getDriver();
        setPortraitOrientation();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    public String getPlatform() {
        platform = System.getenv("PLATFORM");
        if (platform == null) {
            throw new IllegalStateException("PLATFORM environment variable is not set.");
        }
        return platform;
    }

    private DesiredCapabilities getCapabilities() {
//        String platform = System.getenv("PLATFORM");

        capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Pixel6_API_34");
            capabilities.setCapability("platformVersion", "14.0");
            capabilities.setCapability("automationName", "UIAutomator2");
            capabilities.setCapability("appPackage", "org.wikipedia.alpha");
            capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
            capabilities.setCapability("app", getAppPath());
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 15");
            capabilities.setCapability("platformVersion", "17.4");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("app", getAppPath());
        } else {
            throw new RuntimeException("Invalid platform specified. Expected " + PLATFORM_ANDROID + " or " + PLATFORM_IOS);
        }
        return capabilities;
    }

    private String getAppPath() {
//        String platform = System.getenv("PLATFORM");
        String appPath = "";

        if (platform.equals(PLATFORM_ANDROID)) {
            appPath = "src/test/resources/apps/wikipedia-app-alpha-universal-release.apk";
        } else if ( if (platform.equals(PLATFORM_IOS)) {
            appPath = "src/test/resources/apps/Wikipedia.app";
        }

        File app = new File(appPath);
        if (!app.exists()) {
            throw new AssertionError("Failed to get application");
        }
        return app.getAbsolutePath();
    }

    public AppiumDriver getDriver() throws MalformedURLException {
//        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID)) {
            return getAndroidDriver(capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            return getIosDriver(capabilities);
        } else {
            throw new RuntimeException("Driver could not be determined");
        }
    }

    public AndroidDriver getAndroidDriver(MutableCapabilities caps) throws MalformedURLException {
        return new AndroidDriver(new URL(appiumLocalhostURL), caps);
    }

    public IOSDriver getIosDriver(MutableCapabilities caps) throws MalformedURLException {
        return new IOSDriver(new URL(appiumLocalhostURL), caps);
    }

    private void setPortraitOrientation() {
        if (driver instanceof AppiumDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Driver is not an instance of AppiumDriver or IOSDriver, cannot set orientation.");
        }
    }

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
