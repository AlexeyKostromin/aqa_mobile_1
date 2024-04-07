package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class ChangeAppConditionsTests extends CoreTestCase {
    @Test
    void searchArticleInBackgroundTest() {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);

        searchPage.runAppInBackground(Duration.ofSeconds(3));
        searchPage.verifySearchResultsContainsText(expectedResult);
    }

    @Test
    public void assertTitleAfterRotationTest() {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();

        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = new ArticlePage(driver);
        var title_before_rotation = articlePage.getArticleTitle();

        searchPage.rotateDeviceToLandscape();
        var title_after_rotation = articlePage.getArticleTitle();

        Assertions.assertEquals(title_before_rotation, title_after_rotation, "Title after rotation differs from title before rotation");
    }
}
