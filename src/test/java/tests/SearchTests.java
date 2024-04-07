package tests;

import lib.CoreTestCase;
import lib.ui.SearchPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTests extends CoreTestCase {
    @Test
//    @Order(1)
    void verifyTextInSearchFieldTest() {
        final String expectedText = "Search Wikipedia";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.clickSearchTextBox();
        searchPage.verifySearchBoxHasText(expectedText);
    }

    @Test
    void cancelSearchTest() {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);
        searchPage.clickSearchCloseButton();
        searchPage.verifySearchCloseButtonNotVisible();
    }

    @Test
    void searchAllResultsTest() {
        final String searchText = "Java";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.performSearchWithText(searchText);
        var searchResultsList = searchPage.getAllSearchResultsByTitle();
        searchPage.assertResultsContainsTextInTitle(searchResultsList, searchText);
    }
}
