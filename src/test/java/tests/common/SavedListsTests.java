package tests.common;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import lib.TestBase;
import lib.ui.*;
import lib.ui.factory.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Tests for articles")
public class SavedListsTests extends TestBase {
    @Test
    @Tag("android")
    @DisplayName("Save two articles and then remove one from saved")
    void saveTwoArticlesToListAndroidTest() {
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

        savedListsPage.removeArticleFromSaved(savedListsPage.getElementByText(expectedTitle1));
        savedListsPage.waitArticleNotDisplays(expectedTitle1);
        savedListsPage.verifyArticleDisplays(expectedTitle2);

        savedListsPage.openArticle(expectedTitle2);
        articlePage.assertArticleTitle(expectedTitle2);
    }

    @Test
    @Tag("ios")
    @DisplayName("Save two articles and verify their presence in saved list")
    void saveTwoArticlesToListIOSTest() {
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
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Favorites")})
    @Tag("mobileWeb")
    @DisplayName("Save two articles and then remove one from saved")
    void saveTwoArticlesToListMobileWebTest() {
        final String userName = "Dejorden";
        final String userPassword = "Done95:end";

        final String searchText1 = "Java";
        final String expectedTitle1 = "(programming language)";
        final String searchText2 = "Kotlin";
        final String expectedTitle2 = "(programming language)";

        NavigationUi navigationUi = PageFactory.getNavigationUiPage(driver);
        AuthorizationPage authorizationPage =navigationUi.goToAuthPage();

        authorizationPage.enterLoginData(userName, userPassword);
        authorizationPage.submitForm();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText1);
        var result = searchPage.verifySearchResultsContainsText(expectedTitle1);
        searchPage.openArticle(result);

        ArticlePage articlePage = PageFactory.getArticlePage(driver);
        articlePage.saveArticle();

        searchPage.performSearchWithText(searchText2);
        result = searchPage.verifySearchResultsContainsText(expectedTitle2);
        searchPage.openArticle(result);
        articlePage.saveArticle();

//        navigationUi.goToWatchlist();
        navigationUi.goToSavedItems();
        SavedListsPage savedListsPage = PageFactory.getSavedListsPage(driver);

        savedListsPage.verifyArticleDisplays(searchText1 + expectedTitle1);
        savedListsPage.verifyArticleDisplays(searchText2 + expectedTitle2);

        savedListsPage.removeArticleFromSaved(searchText1);
        savedListsPage.waitArticleNotDisplays(searchText1);
        savedListsPage.verifyArticleDisplays(searchText2);
    }

}
