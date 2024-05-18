package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Platform {
    private final static String PLATFORM_ANDROID = "android";
    private final static String PLATFORM_IOS = "ios";
    private final static String RUNTIME_ENV_LOCALHOST = "localHostRuntimeEnv";
    private final static String RUNTIME_ENV_WINDOWS = "windowsRuntimeEnv";
    private final static String RUNTIME_ENV_MAC = "macRuntimeEnv";

    private static String PLATFORM;
    private static String RUNTIME_ENV;
    private static String APPIUM_URL;

    private final static String LOCALHOST_APPIUM_URL = "http://127.0.0.1:4723/";
    private final static String WINDOWS_APPIUM_URL = "http://192.168.0.204:4723/";
    private final static String MAC_APPIUM_URL = "http://192.168.0.202:4723/";
    private static Platform instance;

    private Platform() {
        initConfig();
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private void initConfig() {
        Logger logger = LogManager.getLogger(Platform.class);
//        PLATFORM = System.getProperty("platform", PLATFORM_IOS);
        PLATFORM = System.getProperty("platform", PLATFORM_ANDROID);
//        PLATFORM = System.getenv("PLATFORM");
        RUNTIME_ENV = System.getProperty("runtimeEnv", RUNTIME_ENV_LOCALHOST);
//        RUNTIME_ENV = System.getProperty("runtimeEnv", RUNTIME_ENV_MAC);
//        RUNTIME_ENV = System.getenv("RUNTIME_ENV");
        logger.info("Platform: {}", PLATFORM);
        logger.info("Runtime Environment: {}", RUNTIME_ENV);

//        if (!PLATFORM.equals(PLATFORM_IOS)){
//            throw new RuntimeException("platform is not ios!, but was: " + PLATFORM);
//        }
//        if (!Objects.equals(RUNTIME_ENV, RUNTIME_ENV_LOCALHOST)){
//            throw new RuntimeException("runtime is not localhost!, but was: " + RUNTIME_ENV);
//        }

        setAppiumUrl();
    }

    public AppiumDriver setAppiumDriver() throws Exception {
        URL url = new URL(APPIUM_URL);

        if (isAndroid()) {
            return new AndroidDriver(url, getCapabilitiesAndroid());
        } else if (isIOS()) {
            return new IOSDriver(url, getCapabilitiesIOS());
        } else {
            throw new Exception("Cannot detect type of driver. Platform env is: " + PLATFORM);
        }
    }

    private void setAppiumUrl() {
        if (isWindowsRuntimeEnv()) {
            APPIUM_URL = WINDOWS_APPIUM_URL;
        } else if (isMacRuntimeEnv()) {
            APPIUM_URL = MAC_APPIUM_URL;
        } else if (isLocalHostRuntimeEnv()) {
            APPIUM_URL = LOCALHOST_APPIUM_URL;
        }
    }


    private Boolean isPlatform(String expectedPlatform) {
        return PLATFORM.equals(expectedPlatform);
    }

    private Boolean isRuntimeEnv(String expectedRuntimeEnv) {
        return RUNTIME_ENV.equals(expectedRuntimeEnv);
    }

    public Boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public Boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public Boolean isLocalHostRuntimeEnv() {
        return isRuntimeEnv(RUNTIME_ENV_LOCALHOST);
    }

    public Boolean isWindowsRuntimeEnv() {
        return isRuntimeEnv(RUNTIME_ENV_WINDOWS);
    }

    public Boolean isMacRuntimeEnv() {
        return isRuntimeEnv(RUNTIME_ENV_MAC);
    }

    private DesiredCapabilities getCapabilitiesAndroid1() {
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

    private UiAutomator2Options getCapabilitiesAndroid() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion("14.0")
                .setDeviceName("Pixel7")
                .setApp(getAppPath())
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.main.MainActivity")
                .setAvdLaunchTimeout(Duration.ofSeconds(30));   //wait until Android emulator is started
//                .setCapability("appium:disableIdLocatorAutocompletion", true);
        return options;
    }

    private DesiredCapabilities getCapabilitiesIOS() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("orientation", "PORTRAIT");
//        capabilities.setCapability("app", getAppPath());
        capabilities.setCapability("app", getAppPath());

        return capabilities;
    }

    private String getAppPath() {
        if (isLocalHostRuntimeEnv()) {
            return getAppPathLocal();
        } else
            return getAppPathRemote();
    }

    private String getAppPathLocal() {
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
        } else if (isIOS() && isMacRuntimeEnv()) {
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
