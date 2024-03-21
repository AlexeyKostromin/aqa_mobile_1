package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Lesson3Tests extends TestBase {

    @Test
    void verifyTextInSearchFieldTest() {
        final String expectedText = "Search Wikipedia";

        waitWelcomePageLoaded();
        skipWelcomePage();
        clickSearchTextBox();
        verifySearchBoxHasText(expectedText);
    }

    @Test
    void cancelSearchTest() {
        final String searchText = "Kotlin";
        final String expectedResult = "General-purpose programming language derived from Java";

        waitWelcomePageLoaded();
        skipWelcomePage();
        clickSearchTextBox();
        performSearchWithText(searchText);
        verifySearchResultsContains(expectedResult);
        clickSearchCloseButton();
        verifySearchCloseButtonNotVisible();
    }

    @Test
    void searchAllResultsTest() {
        final String searchText = "Java";

        waitWelcomePageLoaded();
        skipWelcomePage();
        clickSearchTextBox();
        performSearchWithText(searchText);
        var searchResults = getAllSearchResults();
        verifyResultsContainsText(searchResults, searchText);
    }

    public void performSearchWithText(String value) {
        waitForElementAndSendKeys(
                By.id("org.wikipedia.alpha:id/search_src_text"),
                value,
                "Could not send keys in search window",
                5
        );
    }

    public void verifySearchResultsContains(String expectedText) {
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia.alpha:id/page_list_item_description' " +
                        "and @text='" + expectedText + "']"),
                "No results with provided text found",
                15);
    }

    public void clickSearchCloseButton() {
        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/search_close_btn"),
                "Cannot click on X button",
                5
        );
    }

    public void verifySearchCloseButtonNotVisible() {
        waitForElementNotPresent(
                By.id("org.wikipedia.alpha:id/search_close_btn"),
                "No results found",
                5
        );
    }

    public void verifySearchBoxHasText(String expectedText) {
        assertElementHasText(
                By.id("org.wikipedia.alpha:id/search_src_text"),
                expectedText
        );
    }

    public void waitWelcomePageLoaded() {
        waitForElementPresent(
                By.id("org.wikipedia.alpha:id/imageViewCentered"),
                "Welcome image page not visible"
        );
    }

    public void skipWelcomePage() {
        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"),
                "Could not click 'Skip' button",
                5
        );
    }

    public void clickSearchTextBox() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Could not click 'Search Wikipedia' text box",
                5
        );
    }

    public List<WebElement> getAllSearchResults() {
        return waitForElementsPresent(
                By.id("org.wikipedia.alpha:id/page_list_item_title"),
                "Could not get search results",
                15
        );

    }

    public void verifyResultsContainsText(List<WebElement> elements, String expectedText) {
        for (WebElement element : elements) {
            Assertions.assertTrue(element.getText().contains(expectedText),
                    "Expected text for element should contain: " + expectedText
                            + "\n but text was: " + element.getText());
        }

    }
}



