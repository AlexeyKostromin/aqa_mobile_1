package lib.ui.mobileWeb;

import lib.ui.SearchPage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageMW extends SearchPage {
    static {
        SEARCH_WIKI_MAIN_TOOLBAR = "xpath://button[@id='searchIcon']";
        SEARCH_TOOLBAR = "id:";
        SEARCH_INPUT = "xpath://input[@class='search mf-icon-search']";
        SEARCH_CLOSE_BTN = "id:Clear text";
        SEARCH_TOOLBAR_TEXT = SEARCH_INPUT;
        PAGE_LIST_ITEM_TITLE = "xpath:";
        NAVIGATE_UP = "id:Back";
        CLEAR_SEARCH_QUERY_BTN = "id:Clear text";
        CANCEL_SEARCH_BTN = "xpath:";
        ALL_SEARCH_RESULTS = "xpath:";
        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{EXPECTED_TEXT}')]";
        SEARCH_RESULTS_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell[descendant::XCUIElementTypeStaticText[@value=\"{TITLE}\"] " +
                "and descendant::XCUIElementTypeStaticText[@value=\"{DESCRIPTION}\"]]";
    }

    public SearchPageMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
