package tests.android;

import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.TestBase;
import lib.ui.WelcomePage;
import lib.ui.factory.SearchPageFactory;
import lib.ui.factory.WelcomePageFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ArticleTests extends TestBase {
    @Test
    @Tag("android")
    void swipeArticleTest() {
        final String searchText1 = "Intellij IDEA";
        final String expectedResult1 = "Integrated development environment";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedResult1);

        ArticlePage articlePage = new ArticlePage(driver);
        articlePage.swipeUpToTheEndOfArticle();
    }

    @Test
    @Tag("android")
    public void assertTitlePresentInstantlyTest() {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = new ArticlePage(driver);
        articlePage.assertArticleTitlePresentInstantly(expectedTitle);
    }
}
