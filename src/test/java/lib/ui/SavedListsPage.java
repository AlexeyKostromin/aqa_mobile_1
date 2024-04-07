package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SavedListsPage extends MainPage {

    private static final String
            TOOLTIP_GOT_IT = "//*[@resource-id='org.wikipedia.alpha:id/balloon_card']//*[@text='Got it']";

    public SavedListsPage(AndroidDriver driver) {
        super(driver);
    }

    public void openArticle(String article) {
        clickElementByText(article);
    }

    public void submitTooltipGotIt() {
        waitForElementAndClick(By.xpath(TOOLTIP_GOT_IT), "Could not submit Got it", 5);
    }

    public void verifyArticleDisplays(String article) {
        getElementByText(article);
    }

    public void waitArticleNotDisplays(String article) {
        String xpathFull = String.format("//*[@text='%s']", article);
        waitForElementNotPresent(By.xpath(xpathFull),
                "Article with text: " + article + " was displayed, but should not",
                10);
    }


}