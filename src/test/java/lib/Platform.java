package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class Platform {
    private final static String PLATFORM_ANDROID = "android";
    private final static String PLATFORM_IOS = "ios";
    private final static String APPIUM_LOCALHOST_URL = "http://127.0.0.1:4723/";
    private static Platform instance;

    private Platform() {
    }



    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getAppiumDriver() throws Exception {
        URL url = new URL(APPIUM_LOCALHOST_URL);

        if (isAndroid()) {
            return new AndroidDriver(url, getCapabilitiesAndroid());
        } else if (isIOS()) {
            return new IOSDriver(url, getCapabilitiesIOS());
        } else {
            throw new Exception("Cannot detect type of driver. Platform env is: " + getPlatformEnv());
        }
    }

    public Boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public Boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private String getPlatformEnv() {
        String platform = System.getenv("PLATFORM");
        platform = "ios";
        String runtimeEnvironment = System.getProperty("runtimeEnv", "local");
        if (platform == null) {
            throw new IllegalStateException("PLATFORM environment variable is not set.");
        }
        return platform;
    }

    private Boolean isPlatform(String expectedPlatform) {
        String platform = getPlatformEnv();
        return platform.equals(expectedPlatform);
    }

    private DesiredCapabilities getCapabilitiesAndroid() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel6_API_34");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia.alpha");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", getAppPath());

        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesIOS() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("platformVersion", "17.4");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", getAppPath());

        return capabilities;
    }

    private String getAppPath() {
        String appPath = "";

        if (isAndroid()) {
            appPath = "src/test/resources/apps/wikipedia-app-alpha-universal-release.apk";
        } else if (isIOS()) {
            appPath = "src/test/resources/apps/Wikipedia.app";
        }

        File app = new File(appPath);
        if (!app.exists()) {
            throw new AssertionError("Failed to get application");
        }
        return app.getAbsolutePath();
    }

}
