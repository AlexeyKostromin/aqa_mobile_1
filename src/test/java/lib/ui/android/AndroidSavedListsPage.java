package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListsPage;
import lib.ui.strategy.PageActionsStrategy;

public class AndroidSavedListsPage extends SavedListsPage {

    static {
        TOOLTIP_GOT_IT = "xpath://*[@resource-id='org.wikipedia.alpha:id/balloon_card']//*[@text='Got it']";
    }

    public AndroidSavedListsPage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
