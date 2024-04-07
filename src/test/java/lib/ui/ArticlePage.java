package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


public class ArticlePage extends MainPage {

    private static final String
            ARTICLE_TITLE = "//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.widget.TextView[1]",
            SAVE_ARTICLE_BNT = "org.wikipedia.alpha:id/page_save",
            ADD_TO_LIST = "//*[@text='Add to list']",
            NEW_LIST_NAME_INPUT = "org.wikipedia.alpha:id/text_input",
            OK_BTN = "//android.widget.Button[@text='OK']",
            SNACK_BAR = "org.wikipedia.alpha:id/snackbar_action";

    public ArticlePage(AndroidDriver driver) {
        super(driver);
    }

    private static final String
            ABOUT_THIS_ARTICLE_ELEMENT = "//*[@text = 'ABOUT THIS ARTICLE']";

    public void swipeUpToTheEndOfArticle() {
        swipeUpToElement(By.xpath(ABOUT_THIS_ARTICLE_ELEMENT),
                20,
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
        waitForElementAndClick(By.id(SAVE_ARTICLE_BNT), "Could not click add save article", 5);
    }

    public void clickSnackBarAction() {
        waitForElementAndClick(By.id(SNACK_BAR), "Could not click snackbar action", 5);
    }

    public void clickAddToList() {
        waitForElementAndClick(By.xpath(ADD_TO_LIST), "Could not click add to list", 5);
    }

    public void setNewListName(String text) {
        waitForElementAndSendKeys(By.id(NEW_LIST_NAME_INPUT), text, "Could not set name for a new list", 10);
        waitForElementAndClick(By.xpath(OK_BTN), "Could not press OK in new list dialog", 10);
    }

    public String getArticleTitle() {
        var element = waitForElementPresent(By.xpath(ARTICLE_TITLE), "Title of opened article was not present", 15);
        return element.getText();
    }

    public void assertArticleTitle(String expectedTitle) {
        var actualTitle = getArticleTitle();
        Assertions.assertEquals(expectedTitle, actualTitle, "Title of opened article is wrong");
    }

    public void assertArticleTitlePresentInstantly(String title) {
        var element = waitForElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.widget.TextView[1]"),
                "Title of opened article was not present instantly",
                0);
        Assertions.assertEquals(title, element.getText());
    }
}
