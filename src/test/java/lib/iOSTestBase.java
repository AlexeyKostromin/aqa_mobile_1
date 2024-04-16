package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class iOSTestBase {

    protected IOSDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("platformVersion", "17.4");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", getAppPath());

        driver = new IOSDriver(new URL(appiumURL), capabilities);

        setPortraitOrientation();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private String getAppPath() {
        String appPath = "src/test/resources/apps/Wikipedia.app";

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
