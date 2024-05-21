package lib.ui.mobileWeb;

import lib.ui.SearchPage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageMW extends SearchPage {
    static {
        SEARCH_WIKI_MAIN_TOOLBAR = "xpath://button[@id='searchIcon']";
        SEARCH_INPUT = "xpath://input[@class='search mf-icon-search']";
        SEARCH_TOOLBAR_TEXT = SEARCH_INPUT;
        SEARCH_RESULTS_BY_TEXT_TPL = "xpath://h3[contains(., '{EXPECTED_TEXT}')]";
    }

    public SearchPageMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }


}
