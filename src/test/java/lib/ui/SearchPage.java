package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends MainPage {

    private static final String
            WELCOME_PAGE_IMAGE = "org.wikipedia.alpha:id/imageViewCentered",
            ONBOARDING_SKIP_BTN = "org.wikipedia.alpha:id/fragment_onboarding_skip_button",
            SEARCH_WIKI_MAIN_TOOLBAR = "//*[@text='Search Wikipedia']",
            SEARCH_TOOLBAR = "org.wikipedia.alpha:id/page_toolbar_button_search",
            SEARCH_INPUT = "org.wikipedia.alpha:id/search_src_text",
            SEARCH_CLOSE_BTN = "org.wikipedia.alpha:id/search_close_btn",
            SEARCH_TOOLBAR_TEXT = "org.wikipedia.alpha:id/search_src_text",
            PAGE_LIST_ITEM_TITLE = "org.wikipedia.alpha:id/page_list_item_title",
            NAVIGATE_UP = "//android.widget.ImageButton[@content-desc='Navigate up']",

    ALL_SEARCH_RESULTS = "//*[@resource-id='org.wikipedia.alpha:id/search_results_list']/android.view.ViewGroup",
            SEARCH_RESULTS_BY_TEXT_TPL = "//*[@resource-id='org.wikipedia.alpha:id/page_list_item_description' and @text='{EXPECTED_TEXT}']";


    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public void waitWelcomePageLoaded() {
        waitForElementPresent(By.id(WELCOME_PAGE_IMAGE), "Welcome image page not visible");
    }

    public void skipWelcomePage() {
        waitForElementAndClick(
                By.id(ONBOARDING_SKIP_BTN), "Could not click 'Skip' button", 5);
    }

    public void performSearchWithText(String value) {
        clickSearchTextBox();
        sendKeysToSearchTextBox(value);
    }

    public void sendKeysToSearchTextBox(String value) {
        waitForElementAndSendKeys(By.id(SEARCH_INPUT), value, "Could not send keys in search window", 5);
    }

    public void clickSearchTextBox() {
        waitForElementAndClick(
                By.xpath(SEARCH_WIKI_MAIN_TOOLBAR), "Could not click 'Search Wikipedia' text box", 5
        );
    }

    public List<WebElement> getAllSearchResultsByTitle() {
        return waitForElementsPresent(By.id(PAGE_LIST_ITEM_TITLE), "Could not get search results", 15);
    }

    public List<WebElement> getAllSearchResults() {
        return waitForElementsPresent(By.xpath(ALL_SEARCH_RESULTS), "Could not get search results", 151);
    }

    public void verifySearchResultsContainsText(String expectedText) {
        var xpathFull = SEARCH_RESULTS_BY_TEXT_TPL.replace("{EXPECTED_TEXT}", expectedText);
        waitForElementPresent(By.xpath(xpathFull), "No results with provided text found, text was: "+expectedText, 15);
    }

    public void clickArticleTitleWithText(List<WebElement> elements, String text) {
        findElementByTextInList(elements, text).click();
    }

    public void openArticle(String article) {
        clickElementByText(article);
    }


    public void waitArticlePageLoaded() {
        waitForElementPresent(By.id(SEARCH_TOOLBAR), "Toolbar search not found", 10);
    }

    public void clickSearchCloseButton() {
        waitForElementAndClick(By.id(SEARCH_CLOSE_BTN), "Cannot click on X button", 5);
    }

    public void verifySearchCloseButtonNotVisible() {
        waitForElementNotPresent(By.id(SEARCH_CLOSE_BTN), "No results found", 5);
    }

    public void verifySearchBoxHasText(String expectedText) {
        assertElementHasText(By.id(SEARCH_TOOLBAR_TEXT), expectedText);
    }


    public void clickNavigateUp() {
        waitForElementAndClick(By.xpath(NAVIGATE_UP), "Could not press Navigate Up", 5);
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
