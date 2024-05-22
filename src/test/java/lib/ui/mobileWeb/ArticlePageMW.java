package lib.ui.mobileWeb;

import io.qameta.allure.Step;
import lib.Platform;
import lib.ui.ArticlePage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageMW extends ArticlePage {
    static {
        ARTICLE_TITLE = "xpath://*[@id='firstHeading']/span[@class='mw-page-title-main']";
        SAVE_ARTICLE_BNT = "xpath://*[@id='ca-watch']";
        ADD_TO_LIST = "xpath:";
        NEW_LIST_NAME_INPUT = "id:";
        OK_BTN = "xpath:";
        SNACK_BAR = "id:";
        ABOUT_THIS_ARTICLE_ELEMENT = "xpath://h2[contains(text(),'Related articles')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://span[contains(text(), 'Watch')]/ancestor::a[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://span[contains(text(), 'Unwatch')]/ancestor::a[@id='ca-watch']";
    }
    public ArticlePageMW(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    @Step("Save article")
    @Override
    public void saveArticle() {
        removeArticleFromSavedIfWasAdded();
        clickSaveArticle();
    }

}
