package tests.common;

import lib.TestBase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class ChangeAppConditionsTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("ios")
    void searchArticleInBackgroundTest() throws Exception {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);

        welcomePage.runAppInBackground(Duration.ofSeconds(3));
        searchPage.verifySearchResultsContainsText(expectedResult);
    }

    @Test
    @Tag("android")
    @Tag("ios")
    public void assertTitleAfterRotationTest() throws Exception {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);
        var title_before_rotation = articlePage.getArticleTitle(expectedTitle);

        welcomePage.setLandscapeOrientation();
        var title_after_rotation = articlePage.getArticleTitle(expectedTitle);

        Assertions.assertEquals(title_before_rotation, title_after_rotation, "Title after rotation differs from title before rotation");
    }
}
