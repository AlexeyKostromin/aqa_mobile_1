package lib;

import io.qameta.allure.Step;
import lib.ui.SearchPage;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {

    protected RemoteWebDriver driver;

    @BeforeEach
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        BasePage basePage = PageFactory.getBasePage(driver);
        basePage.createAllurePropertyFile();
//        SpecialPhoneActionsHelper specialPhoneActionsHelper = new SpecialPhoneActionsHelper();
//        specialPhoneActionsHelper.setPortraitOrientation();
        skipWelcomePageForMobileDevices();
        openWikiWebPage();
    }

    @AfterEach
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Skip Welcome Page For Mobile Devices")
    private void skipWelcomePageForMobileDevices() {
        if (!Platform.getInstance().isMobileWeb()) {
            WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
            welcomePage.waitForFreeEncyclopediaScreenLoaded();
            welcomePage.clickSkip();
        }
    }

        private void openWikiWebPage() {
        if (Platform.getInstance().isMobileWeb()) {
            BasePage basePage = PageFactory.getBasePage(driver);
            basePage.openWikiWebPageForMobileWeb();
        }
    }
}
