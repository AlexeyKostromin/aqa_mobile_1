package tests.android;

import lib.TestBase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ArticleTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("ios")
    void swipeArticleTest() {
        final String searchText1 = "Intellij IDEA";
        final String expectedResult1 = "Integrated development environment";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedResult1);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);
        articlePage.swipeUpToTheEndOfArticle();
    }

    @Test
    @Tag("android")
    public void assertTitlePresentInstantlyTest() {
        final String searchText = "Java";
        final String expectedTitle = "Java (programming language)";

//        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

//        SearchPage searchPage = SearchPageFactory.getPage(driver);
        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);
        articlePage.assertArticleTitlePresentInstantly(expectedTitle);
    }
}
