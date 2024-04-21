package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePage;


public class AndroidArticlePage extends ArticlePage {

    static  {
        ARTICLE_TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.widget.TextView[1]";
                SAVE_ARTICLE_BNT = "id:org.wikipedia.alpha:id/page_save";
                ADD_TO_LIST = "xpath://*[@text='Add to list']";
                NEW_LIST_NAME_INPUT = "id:org.wikipedia.alpha:id/text_input";
                OK_BTN = "xpath://android.widget.Button[@text='OK']";
                SNACK_BAR = "id:org.wikipedia.alpha:id/snackbar_action";
    }

    public AndroidArticlePage(AppiumDriver driver) {
        super(driver);
    }

}
