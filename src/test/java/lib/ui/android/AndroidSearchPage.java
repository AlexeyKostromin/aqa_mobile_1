package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;
import lib.ui.SearchPage;

public class AndroidSearchPage extends SearchPage {

//    public AndroidSearchPage(AppiumDriver driver) {
//        super(driver);
//    }

    static {
        SEARCH_WIKI_MAIN_TOOLBAR = "xpath://*[@text='Search Wikipedia']";
        SEARCH_TOOLBAR = "id:org.wikipedia.alpha:id/page_toolbar_button_search";
        SEARCH_INPUT = "id:org.wikipedia.alpha:id/search_src_text";
        SEARCH_CLOSE_BTN = "id:org.wikipedia.alpha:id/search_close_btn";
        SEARCH_TOOLBAR_TEXT = "id:org.wikipedia.alpha:id/search_src_text";
        PAGE_LIST_ITEM_TITLE = "id:org.wikipedia.alpha:id/page_list_item_title";
        NAVIGATE_UP = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        CLEAR_SEARCH_BOX_BTN = "id:Clear query";
        ALL_SEARCH_RESULTS = "xpath://*[@resource-id='org.wikipedia.alpha:id/search_results_list']/android.view.ViewGroup";
        ;
        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://*[@resource-id='org.wikipedia.alpha:id/page_list_item_description' and @text='{EXPECTED_TEXT}']";
        SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia.alpha:id/page_list_item_title' " +
                "and @text=\"{TITLE}\"" +
                "and following-sibling::*[@resource-id='org.wikipedia.alpha:id/page_list_item_description'" +
                "and @text=\"{DESCRIPTION}\"]]";

    }

    public AndroidSearchPage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
