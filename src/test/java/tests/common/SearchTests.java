package tests.common;

import io.qameta.allure.Description;
import lib.TestBase;
import lib.ui.SearchPage;
import lib.ui.WelcomePage;
import lib.ui.factory.PageFactory;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTests extends TestBase {
    @Test
    @Tag("android")
    @Tag("androidOnly")
    @Tag("ios")
    @Tag("mobileWeb")
    @DisplayName("Verify label in search field has correct text")
    @Description("Open search page and ensure that label in search field has text Search Wikipedia")
//    @Order(1)
    void verifyLabelTextInSearchFieldTest() {
        final String expectedText = "Search Wikipedia";

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.clickSearchTextBox();
        String elementText = searchPage.getSearchBoxTextByPlatform();

        assertEquals(
                expectedText, elementText,
                "Expected text for element is: " + expectedText + " but was: " + elementText);
    }

    @Test
    @Tag("android")
    @Tag("ios")
    @DisplayName("Cancel search")
    void cancelSearchTest() {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";
        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(expectedResult);
        searchPage.clickSearchCloseButton();
        searchPage.verifySearchCloseButtonNotVisible();
    }

    @Test
    @Tag("android")
    @Tag("ios")
    @DisplayName("Verify search results by text")
    void searchResultsHasSearchItemTest() {
        final String searchText = "Java";
        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);
        searchPage.verifySearchResultsContainsText(searchText);
    }

    @Test
    @Tag("android")
    @Tag("ios")
    @DisplayName("Verify search results by title and description")
    void searchResultsHasSearchItemByTitleAndDescriptionTest() {
        final String searchText = "facebook";

        final String expectedTitleResult1 = "Facebook";
        final String expectedDescriptionResult1 = "Social-networking service owned by Meta Platforms";

        final String expectedTitleResult2 = "Facebook Platform";
        final String expectedDescriptionResult2 = "Application platform accessing Facebook's social network";

        final String expectedTitleResult3 = "Facebook F8";
        final String expectedDescriptionResult3 = "Mostly-annual conference held by Facebook, intended for developers and entrepreneurs";

        WelcomePage welcomePage = PageFactory.getWelcomePage(driver);
        welcomePage.waitForFreeEncyclopediaScreenLoaded();
        welcomePage.clickSkip();

        SearchPage searchPage = PageFactory.getSearchPage(driver);
        searchPage.performSearchWithText(searchText);

        searchPage.waitForElementByTitleAndDescription(expectedTitleResult1, expectedDescriptionResult1);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult2, expectedDescriptionResult2);
        searchPage.waitForElementByTitleAndDescription(expectedTitleResult3, expectedDescriptionResult3);
    }
}
