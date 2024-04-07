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
    void searchResultsHasSearchItemTest() {
        final String searchText = "Java";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.performSearchWithText(searchText);
        var searchResultsList = searchPage.getAllSearchResultsByTitle();
        searchPage.assertResultsContainsTextInTitle(searchResultsList, searchText);
    }

    @Test
    void searchResultsHasSearchItemByTitleAndDescriptionTest() {
        final String searchText = "facebook";

        final String expectedTitleResult1 = "Facebook";
        final String expectedDescriptionResult1 = "Social-networking service owned by Meta Platforms";

        final String expectedTitleResult2 = "Facebook Platform";
        final String expectedDescriptionResult2 = "Application platform accessing Facebook's social network";

        final String expectedTitleResult3 = "Facebook F8";
        final String expectedDescriptionResult3 = "Mostly-annual conference held by Facebook, intended for developers and entrepreneurs";

        SearchPage searchPage = new SearchPage(driver);

        searchPage.waitWelcomePageLoaded();
        searchPage.skipWelcomePage();
        searchPage.performSearchWithText(searchText);

        searchPage.waitForElementByTitleAndDescription(expectedTitleResult1, expectedDescriptionResult1);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult2, expectedDescriptionResult2);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult3, expectedDescriptionResult3);
    }
}
