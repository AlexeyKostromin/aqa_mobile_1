package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import org.junit.jupiter.api.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    void swipeArticleTest() {
        final String searchText1 = "Intellij IDEA";
        final String expectedResult1 = "Integrated development environment";

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();

        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedResult1);

        ArticlePage articlePage = new ArticlePage(driver);
        articlePage.swipeUpToTheEndOfArticle();
    }

    @Test
    public void assertTitlePresentInstantlyTest() {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();

        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = new ArticlePage(driver);
        articlePage.assertArticleTitlePresentInstantly(expectedTitle);
    }
}
