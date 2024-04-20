package tests.iOS;

import lib.TestBase;
import lib.ui.WelcomePage;
import lib.ui.factory.WelcomePageFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetStartedTest extends TestBase {

    @Test
    @Tag("ios")
    public void PassWelcomePageTest() {
//        WelcomePage welcomePage = new WelcomePage(driver);
        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);

        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickNext();
        welcomePage.waitForNewWaysScreenLoaded();
        welcomePage.clickNext();
        welcomePage.waitSearchLanguagesScreenLoaded();
        welcomePage.clickNext();
        welcomePage.waitHelpAppBetterScreenLoaded();
        welcomePage.clickGetStarted();
    }
}
