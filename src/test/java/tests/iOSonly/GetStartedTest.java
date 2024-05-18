package tests.iOSonly;

import lib.TestBase;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class GetStartedTest extends TestBase {

    @Test
    @Tag("androidOnly")
    public void PassWelcomePageTest() {
//        try { Thread.sleep(10000); } catch (InterruptedException e) { /* Do nothing */ }
        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);

        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickNext();
//        welcomePage.waitForNewWaysScreenLoaded();
//        welcomePage.clickNext();
//        welcomePage.waitSearchLanguagesScreenLoaded();
//        welcomePage.clickNext();
//        welcomePage.waitHelpAppBetterScreenLoaded();
//        welcomePage.clickGetStarted();
    }

    @Test
    @Tag("iosOnly")
    public void SwipeWelcomePageTest() {
        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);

        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.swipeOnboardingScreen();

//        welcomePage.waitForNewWaysScreenLoaded();
//        welcomePage.swipeOnboardingScreen();
//
//        welcomePage.waitSearchLanguagesScreenLoaded();
//        welcomePage.swipeOnboardingScreen();
//
//        welcomePage.waitHelpAppBetterScreenLoaded();
//        welcomePage.clickGetStarted();
    }
}
