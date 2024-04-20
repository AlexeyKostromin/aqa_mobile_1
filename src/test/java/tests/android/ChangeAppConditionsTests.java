package tests.android;

import lib.SpecialPhoneActionsHelper;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.TestBase;
import lib.ui.WelcomePage;
import lib.ui.factory.SearchPageFactory;
import lib.ui.factory.WelcomePageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class ChangeAppConditionsTests extends TestBase {
    @Test
    void searchArticleInBackgroundTest() throws Exception {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);

        SpecialPhoneActionsHelper specialPhoneActionsHelper = new SpecialPhoneActionsHelper();
        specialPhoneActionsHelper.runAppInBackground(Duration.ofSeconds(3));
//        searchPage.runAppInBackground(Duration.ofSeconds(3));
        searchPage.verifySearchResultsContainsText(expectedResult);
    }

    @Test
    public void assertTitleAfterRotationTest() throws Exception {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = new ArticlePage(driver);
        var title_before_rotation = articlePage.getArticleTitle();


        SpecialPhoneActionsHelper specialPhoneActionsHelper = new SpecialPhoneActionsHelper();
        specialPhoneActionsHelper.setLandscapeOrientation();
//        searchPage.rotateDeviceToLandscape();
        var title_after_rotation = articlePage.getArticleTitle();

        Assertions.assertEquals(title_before_rotation, title_after_rotation, "Title after rotation differs from title before rotation");
    }
}
