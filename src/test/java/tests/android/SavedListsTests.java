package tests.android;

import lib.TestBase;
import lib.ui.*;
import lib.ui.factory.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SavedListsTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("ios")
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

        ArticlePage articlePage = PageFactory.getArticlePage(driver);

        final String listName = "my list1";
        articlePage.saveArticleToNewList(listName);
        searchPage.clickNavigateUp();

        searchPage.performSearchWithText(searchText2);
        searchPage.openArticle(expectedTitle2);
        articlePage.saveArticleToExistingList(listName);

        searchPage.clickNavigateUp();
        searchPage.closeSearchMode();

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

    @Test
    @Tag("android")
    @Tag("ios")
    void saveTwoArticlesToListTest1() {
        final String searchText1 = "Java";
        final String expectedTitle1 = "Java (programming language)";
//        final String searchText2 = "Kotlin";
//        final String expectedTitle2 = "Kotlin (programming language)";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedTitle1);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);

        final String listName = "my list1";
        articlePage.saveArticleToNewList(listName);
        searchPage.clickNavigateUp();

//        searchPage.performSearchWithText(searchText2);
//        searchPage.openArticle(expectedTitle2);
//        articlePage.saveArticleToExistingList(listName);

//        searchPage.clickNavigateUp();
        searchPage.closeSearchMode();

        NavigationUi navigationUi = PageFactory.getNavigationUiPage(driver);
        navigationUi.goToSavedItems();

        SavedListsPage savedListsPage = PageFactory.getSavedListsPage(driver);
        savedListsPage.openArticle(listName);
        savedListsPage.submitTooltipGotIt();

        savedListsPage.verifyArticleDisplays(expectedTitle1);
//        savedListsPage.verifyArticleDisplays(expectedTitle2);

        var element = savedListsPage.getElementByText(expectedTitle1);
//        savedListsPage.swipeElementToLeftIOS(savedListsPage.getElementByText(expectedTitle1));
        savedListsPage.swipeElementToLeftIOS(element);
        savedListsPage.waitArticleNotDisplays(expectedTitle1);
//        savedListsPage.verifyArticleDisplays(expectedTitle2);

        savedListsPage.openArticle(expectedTitle1);
        articlePage.assertArticleTitle(expectedTitle1);
    }
}
