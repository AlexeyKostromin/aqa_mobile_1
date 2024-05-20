package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.strategy.PageActionsStrategy;
import lib.ui.SearchPage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPage extends SearchPage {

    static {
        SEARCH_WIKI_MAIN_TOOLBAR = "id:Search Wikipedia";
        SEARCH_TOOLBAR = "id:org.wikipedia.alpha:id/page_toolbar_button_search";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CLOSE_BTN = "id:Clear text";
        SEARCH_TOOLBAR_TEXT = "xpath://XCUIElementTypeSearchField";
        PAGE_LIST_ITEM_TITLE = "xpath://XCUIElementTypeStaticText";
        NAVIGATE_UP = "id:Back";
        CLEAR_SEARCH_QUERY_BTN = "id:Clear text";
        CANCEL_SEARCH_BTN = "xpath://XCUIElementTypeButton[@name='Cancel']";
        ALL_SEARCH_RESULTS = "xpath://*[@resource-id='org.wikipedia.alpha:id/search_results_list']/android.view.ViewGroup";
        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{EXPECTED_TEXT}')]";
        SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell[descendant::XCUIElementTypeStaticText[@value=\"{TITLE}\"] " +
                "and descendant::XCUIElementTypeStaticText[@value=\"{DESCRIPTION}\"]]";
    }


    public iOSSearchPage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
