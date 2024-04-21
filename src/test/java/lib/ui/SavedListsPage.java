package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SavedListsPage extends PageBase {

    private static final String
            TOOLTIP_GOT_IT = "xpath://*[@resource-id='org.wikipedia.alpha:id/balloon_card']//*[@text='Got it']";

    public SavedListsPage(AppiumDriver driver) {
        super(driver);
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
