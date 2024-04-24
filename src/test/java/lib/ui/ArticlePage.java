package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


public abstract class ArticlePage extends PageBase {

    protected static String
            ARTICLE_TITLE,
            SAVE_ARTICLE_BNT,
            ADD_TO_LIST,
            NEW_LIST_NAME_INPUT,
            OK_BTN,
            SNACK_BAR,
            ABOUT_THIS_ARTICLE_ELEMENT;

    public ArticlePage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

//    protected static String
//            ABOUT_THIS_ARTICLE_ELEMENT = "//*[@text = 'ABOUT THIS ARTICLE']";

    public void swipeUpToTheEndOfArticle() {
        swipeUpToElement(ABOUT_THIS_ARTICLE_ELEMENT,
                40,
                "Could no swipe to element with text: 'ABOUT THIS ARTICLE'");
    }

    public void saveArticleToNewList(String listName) {
        clickSaveArticle();
        clickAddToList();
        setNewListName(listName);
    }

    public void saveArticleToExistingList(String listName) {
        clickSaveArticle();
        clickAddToList();
        clickElementByText(listName);
    }

    public void clickSaveArticle() {
        waitForElementAndClick(SAVE_ARTICLE_BNT, "Could not click add save article", 5);
    }

    public void clickSnackBarAction() {
        waitForElementAndClick(SNACK_BAR, "Could not click snackbar action", 5);
    }

    public void clickAddToList() {
        waitForElementAndClick(ADD_TO_LIST, "Could not click add to list", 5);
    }

    public void setNewListName(String text) {
        waitForElementAndSendKeys(NEW_LIST_NAME_INPUT, text, "Could not set name for a new list", 10);
        waitForElementAndClick(OK_BTN, "Could not press OK in new list dialog", 10);
    }

    public String getArticleTitle() {
        var element = waitForElementPresent(ARTICLE_TITLE, "Title of opened article was not present", 15);
        return element.getText();
    }

    public void assertArticleTitle(String expectedTitle) {
        var actualTitle = getArticleTitle();
        Assertions.assertEquals(expectedTitle, actualTitle, "Title of opened article is wrong");
    }

    public void assertArticleTitlePresentInstantly(String title) {
        var element = waitForElementPresent(
                ARTICLE_TITLE,
                "Title of opened article was not present instantly",
                0);
        Assertions.assertEquals(title, element.getText());
    }
}
