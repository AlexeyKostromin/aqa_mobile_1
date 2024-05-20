package lib.ui.mobileWeb;

import lib.ui.ArticlePage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageMW extends ArticlePage {
    static {
        ARTICLE_TITLE = "xpath://*[@id='firstHeading']/span[@class='mw-page-title-main']";
        SAVE_ARTICLE_BNT = "id:";
        ADD_TO_LIST = "xpath:";
        NEW_LIST_NAME_INPUT = "id:";
        OK_BTN = "xpath:";
        SNACK_BAR = "id:";
        ABOUT_THIS_ARTICLE_ELEMENT = "xpath://h2[contains(text(),'Related articles')]";
    }
    public ArticlePageMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }
}
