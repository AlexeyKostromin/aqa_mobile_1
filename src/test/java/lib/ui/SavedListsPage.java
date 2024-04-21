package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;

public abstract class SavedListsPage extends PageBase {

    protected static String
            TOOLTIP_GOT_IT;

    public SavedListsPage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public void openArticle(String article) {
        clickElementByText(article);
    }

    public void submitTooltipGotIt() {
        waitForElementAndClick(TOOLTIP_GOT_IT, "Could not submit Got it", 5);
    }

    public void verifyArticleDisplays(String article) {
        getElementByText(article);
    }

    public void waitArticleNotDisplays(String article) {
        String xpathFull = String.format("xpath://*[@text='%s']", article);
        waitForElementNotPresent(xpathFull,
                "Article with text: " + article + " was displayed, but should not",
                10);
    }


}
