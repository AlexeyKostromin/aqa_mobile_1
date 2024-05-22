package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.SavedListsPage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedListsPageMW extends SavedListsPage {
    static {
        TOOLTIP_GOT_IT = "";
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://li[@class='page-summary with-watchstar'][contains(@title, '{TITLE}')]/a[@type='button']";
        REMOVE_FROM_SAVED_BUTTON_STATUS_TPL = "xpath://li[@class='page-summary with-watchstar'][contains(@title, 'Java')]/a[@type='button']/span[contains(text(), 'Watch')]";
    }

    public SavedListsPageMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public String getXpathForRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", articleTitle);
    }

    public String getXpathForRemoveButtonStatusByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON_STATUS_TPL.replace("{TITLE}", articleTitle);
    }

    @Step("Remove article from saved list")
    @Override
    public void removeArticleFromSaved(String articleTitle) {
        var xpathFull = getXpathForRemoveButtonByTitle(articleTitle);
        waitForElementAndClick(xpathFull, "Could not click remove from saved list", 5);
        var xpathButtonStatus = getXpathForRemoveButtonStatusByTitle(articleTitle);
        waitForElementToBeClickable(xpathButtonStatus, "Status was not changed to Unwatched", 5);
        driver.navigate().refresh();
    }

    @Step("Wait Article Not Displays")
    @Override
    public void waitArticleNotDisplays(String article) {
        String xpathFull = String.format("xpath://*[contains(text(), '%s')]", article);
        waitForElementNotPresent(xpathFull,
                "Article with text: " + article + " was displayed, but should not",
                10);
    }


}
