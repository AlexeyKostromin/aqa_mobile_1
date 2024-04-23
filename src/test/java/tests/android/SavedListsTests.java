package tests.android;

import lib.TestBase;
import lib.ui.*;
import lib.ui.factory.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SavedListsTests extends TestBase {
    @Test
    @Tag("android")
    void saveTwoArticlesToListTest() {
        final String searchText1 = "Java";
        final String expectedTitle1 = "Java (programming language)";
        final String searchText2 = "Kotlin";
        final String expectedTitle2 = "Kotlin (programming language)";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedTitle1);

//        ArticlePage articlePage = ArticlePageFactory.getPage(driver);
        ArticlePage articlePage = PageFactory.getArticlePage(driver);

        final String listName = "my list1";
        articlePage.saveArticleToNewList(listName);
        searchPage.clickNavigateUp();

        searchPage.sendKeysToSearchTextBox(searchText2);
        searchPage.openArticle(expectedTitle2);
        articlePage.saveArticleToExistingList(listName);

        searchPage.clickNavigateUp();
        searchPage.clickNavigateUp();

        NavigationUi navigationUi = PageFactory.getNavigationUiPage(driver);
        navigationUi.goToSavedItems();

        SavedListsPage savedListsPage = PageFactory.getSavedListsPage(driver);
        savedListsPage.openArticle(listName);
        savedListsPage.submitTooltipGotIt();

        savedListsPage.verifyArticleDisplays(expectedTitle1);
        savedListsPage.verifyArticleDisplays(expectedTitle2);

        savedListsPage.swipeElementToLeft(savedListsPage.getElementByText(expectedTitle1));
        savedListsPage.waitArticleNotDisplays(expectedTitle1);
        savedListsPage.verifyArticleDisplays(expectedTitle2);

        savedListsPage.openArticle(expectedTitle2);
        articlePage.assertArticleTitle(expectedTitle2);
    }
}
