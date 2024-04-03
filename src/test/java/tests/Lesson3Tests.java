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
        performSearchWithText(searchText);
        var searchResultsList = getAllSearchResultsByTitle();
        verifyResultsContainsTextInTitle(searchResultsList, searchText);
    }

    @Test
    void swipeArticleTest() {
        final String searchText1 = "Intellij IDEA";
        final String expectedResult1 = "Integrated development environment";

        waitWelcomePageLoaded();
        skipWelcomePage();

        performSearchWithText(searchText1);
        tapItemByText(expectedResult1);
        swipeUpToTheEndOfArticle();
    }

    @Test
    void saveTwoArticlesToListTest() {
        final String searchText1 = "Java";
        final String expectedResult1 = "Java (programming language)";
        final String searchText2 = "Kotlin";
        final String expectedResult2 = "Kotlin (programming language)";

        waitWelcomePageLoaded();
        skipWelcomePage();

        performSearchWithText(searchText1);
        tapItemByText(expectedResult1);

        final String listName = "my list1";
        saveArticleToNewList(listName);
        clickNavigateUp();

        sendKeysToSearchTextBox(searchText2);
        tapItemByText(expectedResult2);
        saveArticleToExistingList(listName);

        clickNavigateUp();
        clickNavigateUp();

        goToSavedItems();
        tapItemByText(listName);
        submitTooltipGotIt();

        verifyArticleDisplays(expectedResult1);
        verifyArticleDisplays(expectedResult2);

        swipeElementToLeft(getElementByText(expectedResult1));
        waitArticleNotDisplays(expectedResult1);
        verifyArticleDisplays(expectedResult2);

        tapItemByText(expectedResult2);
        waitArticlePageLoaded();
        verifyArticleTitle(expectedResult2);
    }

    public void saveArticleToNewList(String listName) {
        clickSaveArticle();
        clickAddToList();
        setNewListName(listName);
    }

    public void saveArticleToExistingList(String listName) {
        clickSaveArticle();
        clickAddToList();
        tapItemByText(listName);
    }

    public void waitArticlePageLoaded() {
        waitForElementPresent(
                By.id("org.wikipedia.alpha:id/page_toolbar_button_search"),
                "Toolbar search not found",
                10);
    }

    public void performSearchWithText(String value) {
        clickSearchTextBox();
        sendKeysToSearchTextBox(value);
    }

    public void sendKeysToSearchTextBox(String value) {
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

    public List<WebElement> getAllSearchResultsByTitle() {
        return waitForElementsPresent(
                By.id("org.wikipedia.alpha:id/page_list_item_title"),
                "Could not get search results",
                15
        );
    }

    public List<WebElement> getAllSearchResults() {
        return waitForElementsPresent(
                By.xpath("//*[@resource-id='org.wikipedia.alpha:id/search_results_list']/android.view.ViewGroup"),
                "Could not get search results",
                15
        );
    }

    public void clickArticleTitleWithText(List<WebElement> elements, String text) {
        findElementByTextInList(elements, text).click();
    }

    public void clickSaveArticle() {
        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/page_save"),
                "Could not click add save article",
                5);
    }

    public void clickSnackbarAction() {
        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/snackbar_action"),
                "Could not click snackbar action",
                5);
    }

    public void clickAddToList() {
        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Could not click add to list",
                5);
    }

    public void setNewListName(String text) {
        waitForElementAndSendKeys(
                By.id("org.wikipedia.alpha:id/text_input"),
                text, "Could not set name for a new list",
                10);
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='OK']"),
                "Could not press OK in new list dialog",
                10);
    }

    public void clickNavigateUp() {
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Could not press Navigate Up",
                5);
    }

    public void goToSavedItems() {
        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/nav_tab_reading_lists"),
                "Could not press Navigate Up",
                5);
    }

    public void submitTooltipGotIt() {
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia.alpha:id/balloon_card']//*[@text='Got it']"),
                "Could not submit Got it",
                5);
    }

    public void swipeUpToTheEndOfArticle() {
        swipeUpToElement(
                By.xpath("//*[@text = 'ABOUT THIS ARTICLE']"),
                20,
                "Could no swipe to element with text: 'ABOUT THIS ARTICLE'");
    }

    public void waitArticleNotDisplays(String article) {
        String xpathFull = String.format("//*[@text='%s']", article);
        waitForElementNotPresent(
                By.xpath(xpathFull),
                "Article with text: " + article + " was displayed, but should not",
                10);
    }

    public void verifyArticleDisplays(String article) {
        getElementByText(article);
    }

    public WebElement verifyResultsContainsTextInTitle(List<WebElement> elements, String expectedText) {
        var element = findElementByTextInList(elements, expectedText);

        Assertions.assertTrue(element.getText().contains(expectedText),
                "Expected text for element should contain: " + expectedText
                        + "\n but text was: " + element.getText());

        return element;
    }

    public void verifyArticleTitle(String title) {
        getElementByText(title);
    }

}



