package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPage;

public class iOSSearchPage extends SearchPage {
    public iOSSearchPage(AppiumDriver driver) {
        super(driver);
    }

    static {
//        ONBOARDING_SKIP_BTN = "id:org.wikipedia.alpha:id/fragment_onboarding_skip_button";
        SEARCH_WIKI_MAIN_TOOLBAR = "id:Search Wikipedia";
        SEARCH_TOOLBAR = "id:org.wikipedia.alpha:id/page_toolbar_button_search";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CLOSE_BTN = "id:Clear text";
//        SEARCH_TOOLBAR_TEXT = "id:org.wikipedia.alpha:id/search_src_text";
        SEARCH_TOOLBAR_TEXT = "xpath://XCUIElementTypeSearchField";
//        PAGE_LIST_ITEM_TITLE = "id:org.wikipedia.alpha:id/page_list_item_title";
        PAGE_LIST_ITEM_TITLE = "xpath://XCUIElementTypeStaticText";
        NAVIGATE_UP = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ALL_SEARCH_RESULTS = "xpath://*[@resource-id='org.wikipedia.alpha:id/search_results_list']/android.view.ViewGroup";
//        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://*[@resource-id='org.wikipedia.alpha:id/page_list_item_description' and @text='{EXPECTED_TEXT}']";
        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{EXPECTED_TEXT}')]";
//        SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia.alpha:id/page_list_item_title' " +
//                "and @text=\"{TITLE}\"" +
//                "and following-sibling::*[@resource-id='org.wikipedia.alpha:id/page_list_item_description'" +
//                "and @text=\"{DESCRIPTION}\"]]";
        SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell[descendant::XCUIElementTypeStaticText[@value=\"{TITLE}\"] " +
                "and descendant::XCUIElementTypeStaticText[@value=\"{DESCRIPTION}\"]]";
    }


}