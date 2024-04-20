package tests.android;

import lib.Platform;
import lib.ui.SearchPage;
import lib.TestBase;
import lib.ui.WelcomePage;
import lib.ui.factory.SearchPageFactory;
import lib.ui.factory.WelcomePageFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("ios")
//    @Order(1)
    void verifyTextInSearchFieldTest() {
        final String expectedText = "Search Wikipedia";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.clickSearchTextBox();
//        searchPage.assertSearchBoxHasText(expectedText);
        String elementText = searchPage.getSearchBoxTextByPlatform();

        assertEquals(
                expectedText, elementText,
                "Expected text for element is: " + expectedText + " but was: " + elementText);
    }

    @Test
    @Tag("android")
    @Tag("ios")
    void cancelSearchTest() {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";
        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);
        searchPage.clickSearchCloseButton();
        searchPage.verifySearchCloseButtonNotVisible();
    }

    @Test
    @Tag("android")
    @Tag("ios")
    void searchResultsHasSearchItemTest() {
        final String searchText = "Java";
        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);
        var searchResultsList = searchPage.getAllSearchResultsByTitle();
        searchPage.assertResultsContainsTextInTitle(searchResultsList, searchText);
    }

    @Test
    @Tag("android")
    @Tag("ios")
    void searchResultsHasSearchItemByTitleAndDescriptionTest() {
        final String searchText = "facebook";

        final String expectedTitleResult1 = "Facebook";
        final String expectedDescriptionResult1 = "Social-networking service owned by Meta Platforms";

        final String expectedTitleResult2 = "Facebook Platform";
        final String expectedDescriptionResult2 = "Application platform accessing Facebook's social network";

        final String expectedTitleResult3 = "Facebook F8";
        final String expectedDescriptionResult3 = "Mostly-annual conference held by Facebook, intended for developers and entrepreneurs";

        WelcomePage welcomePage = WelcomePageFactory.getPage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = SearchPageFactory.getPage(driver);
        searchPage.performSearchWithText(searchText);

        searchPage.waitForElementByTitleAndDescription(expectedTitleResult1, expectedDescriptionResult1);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult2, expectedDescriptionResult2);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult3, expectedDescriptionResult3);
    }
}
