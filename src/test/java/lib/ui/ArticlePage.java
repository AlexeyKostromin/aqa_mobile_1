package lib.ui;

import io.qameta.allure.Step;
import lib.BasePage;
import lib.Platform;
import lib.ui.strategy.PageActionsStrategy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.RemoteWebDriver;


public abstract class ArticlePage extends BasePage {

    protected static String
            ARTICLE_TITLE,
            SAVE_ARTICLE_BNT,
            ADD_TO_LIST,
            CREATE_NEW_LIST_BTN,
            NEW_LIST_NAME_INPUT,
            OK_BTN,
            SNACK_BAR,
            ABOUT_THIS_ARTICLE_ELEMENT,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;

    public ArticlePage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    @Step("Swipe up to the end of the article")
    public void swipeUpToTheEndOfArticle() {
        screenshot(takeScreenshot("articleTitle"));

        if (Platform.getInstance().isAndroid()) {
            swipeUpToElement(ABOUT_THIS_ARTICLE_ELEMENT,
                    40,
                    "Could no swipe to element with text: 'ABOUT THIS ARTICLE'");
        } else if (Platform.getInstance().isIOS()) {
            swipeUpTillElementAppear(ABOUT_THIS_ARTICLE_ELEMENT,
                    40,
                    "Could no swipe to element with text: 'ABOUT THIS ARTICLE'");
        } else if (Platform.getInstance().isMobileWeb()) {

            scrollUpTillElementAppear(ABOUT_THIS_ARTICLE_ELEMENT,
                    40,
                    "Could no swipe to element with text: 'ABOUT THIS ARTICLE'");
        }
    }

    @Step("Save article")
    public void saveArticle() {
        clickSaveArticle();
    }

    @Step("Save article to new list {listName}")
    public void saveArticleToNewList(String listName) {
        clickSaveArticle();
        clickAddToList();
        if (Platform.getInstance().isIOS()) {
            clickNewList();
        }
        setNewListName(listName);
    }

    @Step("Save article to existing list {listName}")
    public void saveArticleToExistingList(String listName) {
        clickSaveArticle();
        clickAddToList();
        clickElementByText(listName);
    }

    @Step("Click save article")
    public void clickSaveArticle() {
        waitForElementAndClick(SAVE_ARTICLE_BNT, "Could not click add save article", 5);
    }

    public void clickSnackBarAction() {
        waitForElementAndClick(SNACK_BAR, "Could not click snackbar action", 5);
    }

    public void clickNewList() {
        waitForElementAndClick(CREATE_NEW_LIST_BTN, "Could not click add to list", 5);
    }

    public void clickAddToList() {
        waitForElementAndClick(ADD_TO_LIST, "Could not click add to list", 5);
    }

    public void setNewListName(String text) {
        waitForElementAndSendKeys(NEW_LIST_NAME_INPUT, text, "Could not set name for a new list", 10);
        waitForElementAndClick(OK_BTN, "Could not press OK in new list dialog", 10);
    }

    @Step("Remove article from saved list if it was already there")
    public void removeArticleFromSavedIfWasAdded() {
        if (isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click remove article from my list button",
                    1);
        }
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        waitForElementToBeClickable(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find add to my list button after removing it from this list before ",
                5);
    }

    @Step("Get article title {title}")
    public String getArticleTitle(String title) {
        screenshot(takeScreenshot("articleTitle"));

        var actualTitle = "";
        if (Platform.getInstance().isAndroid()) {
            actualTitle = getArticleTitleAndroid();
        } else if (Platform.getInstance().isIOS()) {
            actualTitle = getArticleTitleIOS(title);
        } else if (Platform.getInstance().isMobileWeb()) {
            actualTitle = getArticleTitleMobileWeb();
        }
        return actualTitle;
    }

    private String getArticleTitleAndroid() {
        var element = waitForElementPresent(ARTICLE_TITLE, "Title of opened article was not present", 15);
        return element.getText();
    }


    private String getArticleTitleIOS(String expectedTitle) {
        String javaTitleId = "id:" + expectedTitle;
        try {
            waitForElementPresent(javaTitleId, "Title of opened article was not present", 15);
        } catch (Exception e) {
            throw new RuntimeException("Could not get title from article, expected title was: " + expectedTitle);
        }
        return expectedTitle;
    }

    private String getArticleTitleMobileWeb() {
        return getArticleTitleAndroid();
    }

    @Step("Assert opened article title to expected, expected id {expectedTitle}")
    public void assertArticleTitle(String expectedTitle) {
        var actualTitle = getArticleTitle(expectedTitle);

        Assertions.assertEquals(expectedTitle, actualTitle, "Title of opened article is wrong");
    }

    @Step("Assert opened article title displays instantly")
    public void assertArticleTitlePresentInstantly(String title) {
        var element = waitForElementPresent(
                ARTICLE_TITLE,
                "Title of opened article was not present instantly",
                0);
        Assertions.assertEquals(title, element.getText());
    }
}
