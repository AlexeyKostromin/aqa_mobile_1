package tests.iOS;

import lib.iOSTestBase;
import lib.ui.WelcomePage;
import org.junit.jupiter.api.Test;

public class GetStartedTest extends iOSTestBase {

    @Test
    public void PassWelcomePageTest(){
        WelcomePage welcomePage = new WelcomePage(driver);
    }
}
