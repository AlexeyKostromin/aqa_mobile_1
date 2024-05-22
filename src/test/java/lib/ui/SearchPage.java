package lib.ui;

import io.qameta.allure.Step;
import lib.BasePage;
import lib.ui.strategy.PageActionsStrategy;
import lib.Platform;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract public class SearchPage extends BasePage {

    protected static String
            SEARCH_WIKI_MAIN_TOOLBAR,
            SEARCH_TOOLBAR,
            SEARCH_INPUT,
            SEARCH_CLOSE_BTN,
            SEARCH_TOOLBAR_TEXT,
            PAGE_LIST_ITEM_TITLE,
            NAVIGATE_UP,
            CLEAR_SEARCH_QUERY_BTN,
            CANCEL_SEARCH_BTN,
            ALL_SEARCH_RESULTS,
            SEARCH_RESULTS_BY_TEXT_TPL,
            SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL;

    public SearchPage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    private static String getXpathForResultsByTitleAndDescription(String title, String description) {
        return SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }

    @Step("Perform search with text: {value}")
    public void performSearchWithText(String value) {
        clickSearchTextBox();
        clearTextBox();
        sendKeysToSearchTextBox(value);
    }

    @Step("Clear text box")
    public void clearTextBox() {
        waitForElementAndClear(SEARCH_INPUT, "", "Could not clear search box", 5);
    }

    @Step("Click cancel search")
    public void clickCancelSearch() {
        waitForElementAndClick(CANCEL_SEARCH_BTN, "Could not click cancel search btn", 5);
    }

    @Step("Send Keys To Search Tex tBox {value}")
    public void sendKeysToSearchTextBox(String value) {
        waitForElementAndSendKeys(SEARCH_INPUT, value, "Could not send keys in search box", 5);
    }

    @Step("Click Search Tex tBox")
    public void clickSearchTextBox() {
        waitForElementAndClick(SEARCH_WIKI_MAIN_TOOLBAR, "Could not click 'Search Wikipedia' text box", 5
        );
    }

    public List<WebElement> getAllSearchResultsByTitle() {
        return waitForElementsPresent(PAGE_LIST_ITEM_TITLE, "Could not get search results", 15);
    }

    public List<WebElement> getAllSearchResults() {
        return waitForElementsPresent(ALL_SEARCH_RESULTS, "Could not get search results", 15);
    }

    @Step("Search for element:by title: {title} and description: {description}")
    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        var xpathFull = getXpathForResultsByTitleAndDescription(title, description);
        return waitForElementPresent(
                xpathFull,
                "No results found with provided title and description , " +
                        "expected title was: " + title + " and expected description was: " + description,
                15);
    }

    @Step("Verify Search Results Contains Text: {expectedText}")
    public WebElement verifySearchResultsContainsText(String expectedText) {
        var xpathFull = SEARCH_RESULTS_BY_TEXT_TPL.replace("{EXPECTED_TEXT}", expectedText);
        return waitForElementPresent(xpathFull, "No results found with provided text, text was: " + expectedText, 15);
    }

    public void clickArticleTitleWithText(List<WebElement> elements, String text) {
        findElementByTextInList(elements, text).click();
    }
    @Step("Open article: {article}")
    public void openArticle(String article) {
        clickElementByText(article);
    }

    @Step("Open article by element")
    public void openArticle(WebElement element) {
        clickElement(element);
    }


    public void waitArticlePageLoaded() {
        waitForElementPresent(SEARCH_TOOLBAR, "Toolbar search not found", 10);
    }

    @Step("Click Search Close Button")
    public void clickSearchCloseButton() {
        waitForElementAndClick(SEARCH_CLOSE_BTN, "Cannot click on X button", 5);
    }

    @Step("Click Navigate Up")
    public void clickNavigateUp() {
        waitForElementAndClick(NAVIGATE_UP, "Could not press Navigate Up", 5);
    }

    public void clickClearQuery() {
        waitForElementAndClick(CLEAR_SEARCH_QUERY_BTN, "Could not press Clear text btn (x)", 5);
    }

    @Step("Verify Search Close Button Not Visible")
    public void verifySearchCloseButtonNotVisible() {
        waitForElementNotPresent(SEARCH_CLOSE_BTN, "No results found", 5);
    }

    public String getSearchBoxTextByAttribute(String attribute) {
        return waitForElementAndGetAttribute(SEARCH_TOOLBAR_TEXT, attribute, "Could not get text by attribute", 5);
    }

    @Step("Get text from search box depending of a platform")
    public String getSearchBoxTextByPlatform() {
        if (Platform.getInstance().isAndroid()) {
            return getSearchBoxTextByAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return getSearchBoxTextByAttribute("name");
        } else Platform.getInstance().isMobileWeb(); {
            return getSearchBoxTextByAttribute("aria-label");
        }
    }

    @Step("Close search mode")
    public void closeSearchMode() {
        if (Platform.getInstance().isAndroid()) {
            clickNavigateUp();
        } else if (Platform.getInstance().isIOS()) {
            clickCancelSearch();
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
