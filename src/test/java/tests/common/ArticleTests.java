package tests.common;

import io.qameta.allure.*;
import lib.TestBase;
import lib.ui.ArticlePage;
import lib.ui.SearchPage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ArticleTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("ios")
    @Tag("mobileWeb")
    @DisplayName("Swipe article to the footer")
    @Description("Open article and swipe it to the footer")
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Starting test swipeArticleTest")
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
    @Tag("mobileWeb")
    @DisplayName("Verify article's tittle displays instantly")
    @Severity(value = SeverityLevel.MINOR)
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
