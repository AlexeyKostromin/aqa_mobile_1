package lib;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().setAppiumDriver();
//        SpecialPhoneActionsHelper specialPhoneActionsHelper = new SpecialPhoneActionsHelper();
//        specialPhoneActionsHelper.setPortraitOrientation();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
