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
    private final static String RUNTIME_ENV_MAC = "macRemote";
    private static String PLATFORM;
    private static String RUNTIME_ENV;
    //    private final static String APPIUM_LOCALHOST_URL = "http://127.0.0.1:4723/";
    private final static String APPIUM_LOCALHOST_URL = "http://192.168.0.200:4723/";
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

//    private String getPlatformEnv() {
//        String platform = System.getenv("PLATFORM");
//        platform = "ios";
////        platform = "android";
////        RUNTIME_ENV = "macLocal";
//        RUNTIME_ENV = System.getProperty("runtimeEnv", "macLocal");
//
//        if (platform == null) {
//            throw new IllegalStateException("PLATFORM environment variable is not set.");
//        }
//        return platform;
//    }

    private String getPlatformEnv() {
        PLATFORM = System.getProperty("platform", "ios");
        RUNTIME_ENV = System.getProperty("runtimeEnv", RUNTIME_ENV_MAC);

        return PLATFORM;
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
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("app", getAppPath());

        return capabilities;
    }

//    private DesiredCapabilities getCapabilitiesIOS() {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "iOS");
//        capabilities.setCapability("deviceName", "iPhone 15");
//        capabilities.setCapability("platformVersion", "17.4");
//        capabilities.setCapability("automationName", "XCUITest");
//        capabilities.setCapability("app", getAppPath());
//
//        return capabilities;
//    }

    private DesiredCapabilities getCapabilitiesIOS() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("orientation", "PORTRAIT");
//        capabilities.setCapability("app", getAppPath());
        capabilities.setCapability("app", getAppPathRemote());

        return capabilities;
    }

    private String getAppPath() {
        String appPath = "";

        if (isAndroid()) {
            appPath = "src/test/resources/apps/wikipedia-app-alpha-universal-release.apk";
        } else if (isIOS()) {
            appPath = "src/test/resources/apps/Wikipedia693.app";
        }

        File app = new File(appPath);
        if (!app.exists()) {
            throw new AssertionError("Failed to get application");
        }
        return app.getAbsolutePath();
    }

    private String getAppPathRemote() {
        String appPath = "";

        if (isAndroid()) {
            appPath = "src/test/resources/apps/wikipedia-app-alpha-universal-release.apk";
        } else if (isIOS() && RUNTIME_ENV.equals(RUNTIME_ENV_MAC)) {
            String localPath = "/Users/o.kostromin/IdeaProjects/aqa_mobile_1/";
            appPath = localPath + "src/test/resources/apps/Wikipedia693.app";
        } else if (isIOS()) {
            appPath = "src/test/resources/apps/Wikipedia693.app";
        }

        File app = new File(appPath);
//        if (!app.exists()) {
//            throw new AssertionError("Failed to get application");
//        }
        return appPath;
    }

}
