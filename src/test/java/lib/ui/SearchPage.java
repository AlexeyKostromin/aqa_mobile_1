package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.Platform;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract public class SearchPage extends MainPage {

    protected static String
            SEARCH_WIKI_MAIN_TOOLBAR,
            SEARCH_TOOLBAR,
            SEARCH_INPUT,
            SEARCH_CLOSE_BTN,
            SEARCH_TOOLBAR_TEXT,
            PAGE_LIST_ITEM_TITLE,
            NAVIGATE_UP,
            ALL_SEARCH_RESULTS,
            SEARCH_RESULTS_BY_TEXT_TPL,
            SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL;

    private static String getXpathForResultsByTitleAndDescription(String title, String description) {
        return SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public void performSearchWithText(String value) {
        clickSearchTextBox();
        sendKeysToSearchTextBox(value);
    }

    public void sendKeysToSearchTextBox(String value) {
        waitForElementAndSendKeys(SEARCH_INPUT, value, "Could not send keys in search window", 5);
    }

    public void clickSearchTextBox() {
        waitForElementAndClick(SEARCH_WIKI_MAIN_TOOLBAR, "Could not click 'Search Wikipedia' text box", 5
        );
    }

    public List<WebElement> getAllSearchResultsByTitle() {
        return waitForElementsPresent(PAGE_LIST_ITEM_TITLE, "Could not get search results", 15);
    }

    public List<WebElement> getAllSearchResults() {
        return waitForElementsPresent(ALL_SEARCH_RESULTS, "Could not get search results", 151);
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        var xpathFull = getXpathForResultsByTitleAndDescription(title, description);
        return waitForElementPresent(
                xpathFull,
                "No results found with provided title and description , " +
                        "expected title was: " + title + " and expected description was: " + description,
                15);
    }

    public void verifySearchResultsContainsText(String expectedText) {
        var xpathFull = SEARCH_RESULTS_BY_TEXT_TPL.replace("{EXPECTED_TEXT}", expectedText);
        waitForElementPresent(xpathFull, "No results found with provided text, text was: " + expectedText, 15);
    }

    public void clickArticleTitleWithText(List<WebElement> elements, String text) {
        findElementByTextInList(elements, text).click();
    }

    public void openArticle(String article) {
        clickElementByText(article);
    }


    public void waitArticlePageLoaded() {
        waitForElementPresent(SEARCH_TOOLBAR, "Toolbar search not found", 10);
    }

    public void clickSearchCloseButton() {
        waitForElementAndClick(SEARCH_CLOSE_BTN, "Cannot click on X button", 5);
    }

    public void clickNavigateUp() {
        waitForElementAndClick(NAVIGATE_UP, "Could not press Navigate Up", 5);
    }

    public void verifySearchCloseButtonNotVisible() {
        waitForElementNotPresent(SEARCH_CLOSE_BTN, "No results found", 5);
    }

    public String getSearchBoxTextByAttribute(String attribute) {
        return waitForElementAndGetAttribute(SEARCH_TOOLBAR_TEXT, attribute, "Could not get text by attribute", 5);
    }

    public String getSearchBoxTextByPlatform() {
        if (Platform.getInstance().isAndroid()) {
            return getSearchBoxTextByAttribute("text");
        } else {
            return getSearchBoxTextByAttribute("name");
        }
    }

    public void assertSearchBoxHasText(String expectedText) {
        var elementText = waitForElementAndGetAttribute(SEARCH_TOOLBAR_TEXT, "text", "Could not get text attribute", 5);
        assertEquals(
                expectedText, elementText,
                "Expected text for element is: " + expectedText + " but was: " + elementText);
    }

    public void verifyArticleDisplays(String article) {
        getElementByText(article);
    }

    public WebElement assertResultsContainsTextInTitle(List<WebElement> elements, String expectedText) {
        var element = findElementByTextInList(elements, expectedText);

        Assertions.assertTrue(element.getText().contains(expectedText),
                "Expected text for element should contain: " + expectedText
                        + "\n but text was: " + element.getText());

        return element;
    }


}
