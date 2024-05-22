package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.ui.AuthorizationPage;
import lib.ui.NavigationUi;
import lib.ui.factory.PageFactory;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUiMW extends NavigationUi {
    static {
        CONTENTS_ITEM_LIST_BTN = "";
        SAVED_ITEM_LIST_BTN = "";
    }

    private static final String HAMBURGER_MENU = "xpath://*[@id='mw-mf-main-menu-button']";
    private static final String HAMBURGER_MENU_LOGIN = "xpath://*[@data-event-name='menu.login']";
    private static final String HAMBURGER_MENU_WATCHLIST = "xpath://*[@data-event-name='menu.watchlist']";


    public NavigationUiMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public void openHamburgerMenu() {
        waitForElementAndClick(HAMBURGER_MENU, "Cannot click on hamburger menu", 5);
    }

    @Step("Go to Auth page")
    public AuthorizationPage goToAuthPage() {
        openHamburgerMenu();
        waitForElementAndClick(HAMBURGER_MENU_LOGIN, "Cannot click on login in hamburger menu", 5);
        return PageFactory.getAuthorizationPage(driver);
    }

    @Override
    public void goToSavedItems() {
        openHamburgerMenu();
        waitForElementAndClick(HAMBURGER_MENU_WATCHLIST, "Cannot click on watchlist in hamburger menu", 5);
    }

//    public void goToWatchlist() {
//        openHamburgerMenu();
//        waitForElementAndClick(HAMBURGER_MENU_WATCHLIST, "Cannot click on watchlist in hamburger menu", 5);
//    }
}
