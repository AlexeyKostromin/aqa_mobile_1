package lib;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase {
    protected AndroidDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel6_API_34");
        capabilities.setCapability("platformVersion", "14.0");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia.alpha");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", getAppPath());

        driver = new AndroidDriver(new URL(appiumURL), capabilities);

        setPortraitOrientation();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private String getAppPath() {
        String appPath = "src/test/resources/apps/wikipedia-app-alpha-universal-release.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            throw new AssertionError("Failed to get application");
        }
        return app.getAbsolutePath();
    }

    private void setPortraitOrientation() {
        ScreenOrientation currentOrientation = driver.getOrientation();
        if (currentOrientation != ScreenOrientation.PORTRAIT) {
            System.out.println("The device orientation was not Portrait, rotating device to Portrait");
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

}