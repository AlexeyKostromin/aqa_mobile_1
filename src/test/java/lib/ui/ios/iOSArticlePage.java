package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePage;
import lib.ui.strategy.PageActionsStrategy;


public class iOSArticlePage extends ArticlePage {


    static {
        ARTICLE_TITLE = "xpath:";
        SAVE_ARTICLE_BNT = "id:Save for later";
        ADD_TO_LIST = "xpath://*[contains(@name,\"to a reading list?\")]";
        CREATE_NEW_LIST_BTN = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        NEW_LIST_NAME_INPUT = "xpath://*[@value='reading list title']";
        OK_BTN = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        SNACK_BAR = "id:";
//        ABOUT_THIS_ARTICLE_ELEMENT = "//*[@text = 'ABOUT THIS ARTICLE']";
        ABOUT_THIS_ARTICLE_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='ABOUT THIS ARTICLE']";
    }

    public iOSArticlePage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver,strategy);
    }

}
