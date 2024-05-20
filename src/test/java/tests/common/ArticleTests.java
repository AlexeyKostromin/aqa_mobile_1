package tests.common;

import lib.PageBase;
import lib.Platform;
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
    @Tag("mobileWeb")
    void swipeArticleTest() {
        final String searchText1 = "Gradle";
        final String expectedResult1 = "Free software build automation tool";

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

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.openArticle(expectedTitle);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);
        articlePage.assertArticleTitlePresentInstantly(expectedTitle);
    }

}
