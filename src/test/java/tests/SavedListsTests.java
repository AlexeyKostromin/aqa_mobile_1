package tests;

import lib.TestBase;
import lib.ui.*;
import org.junit.jupiter.api.Test;

public class SavedListsTests extends TestBase {
    @Test
    void saveTwoArticlesToListTest() {
        final String searchText1 = "Java";
        final String expectedTitle1 = "Java (programming language)";
        final String searchText2 = "Kotlin";
        final String expectedTitle2 = "Kotlin (programming language)";

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();

        searchPage.performSearchWithText(searchText1);
        searchPage.openArticle(expectedTitle1);

        ArticlePage articlePage = new ArticlePage(driver);

        final String listName = "my list1";
        articlePage.saveArticleToNewList(listName);
        searchPage.clickNavigateUp();//adf

        searchPage.sendKeysToSearchTextBox(searchText2);
        searchPage.openArticle(expectedTitle2);
        articlePage.saveArticleToExistingList(listName);

        searchPage.clickNavigateUp();
        searchPage.clickNavigateUp();

        NavigationUi navigationUi = new NavigationUi(driver);
        navigationUi.goToSavedItems();

        SavedListsPage savedListsPage = new SavedListsPage(driver);
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
