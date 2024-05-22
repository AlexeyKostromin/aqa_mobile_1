package lib.ui;

import io.qameta.allure.Step;
import lib.BasePage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class NavigationUi extends BasePage {
    protected static String
            CONTENTS_ITEM_LIST_BTN,
            SAVED_ITEM_LIST_BTN;

    public NavigationUi(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver,strategy);
    }

    @Step("Go to saved items (watchlist)")
    public void goToSavedItems() {
        waitForElementAndClick(SAVED_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }

    public void openContents(){
        waitForElementAndClick(CONTENTS_ITEM_LIST_BTN, "Could not press Navigate Up", 5);
    }


    public void openHamburgerMenu() {
    }

    public AuthorizationPage goToAuthPage() {
        return null;
    }


}
