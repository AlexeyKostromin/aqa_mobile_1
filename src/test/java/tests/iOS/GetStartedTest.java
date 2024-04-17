package tests.iOS;

import lib.TestBase;
import lib.ui.WelcomePage;
import org.junit.jupiter.api.Test;

public class GetStartedTest extends TestBase {

    @Test
    public void PassWelcomePageTest(){
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage
                .WaitForFreeEncyclopediaScreenLoaded()
                .ClickNext()
                .WaitForNewWaysScreenLoaded()
                .ClickNext()
                .WaitSearchLanguagesScreenLoaded();


    }
}
