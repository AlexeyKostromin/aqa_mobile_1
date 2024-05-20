package lib;

import lib.ui.SearchPage;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {

    protected RemoteWebDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
//        SpecialPhoneActionsHelper specialPhoneActionsHelper = new SpecialPhoneActionsHelper();
//        specialPhoneActionsHelper.setPortraitOrientation();
        skipWelcomePageForMobileDevices();
        openWikiWebPage();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void skipWelcomePageForMobileDevices() {
        if (!Platform.getInstance().isMobileWeb()) {
            WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
            welcomePage.waitForFreeEncyclopediaScreenLoaded();
            welcomePage.clickSkip();
        }
    }
    private void openWikiWebPage() {
        if (Platform.getInstance().isMobileWeb()) {
            SearchPage searchPage = PageFactory.getSearchPage(driver);
            searchPage.openWikiWebPageForMobileWeb();
        }
    }
}
