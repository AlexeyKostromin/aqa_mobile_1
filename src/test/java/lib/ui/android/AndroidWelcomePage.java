package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePage;
import lib.ui.strategy.PageActionsStrategy;


public class AndroidWelcomePage extends WelcomePage {
    public AndroidWelcomePage(AppiumDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    static {
        STEP_TRY_FREE_ENCYCLOPEDIA = "id:org.wikipedia.alpha:id/primaryTextView";
        STEP_SKIP_BTN = "id:org.wikipedia.alpha:id/fragment_onboarding_skip_button";
        STEP_NEXT_BTN = "";
        STEP_NEW_WAYS_LABEL = "";
        STEP_SEARCH_LANGUAGE_ADD_PREFFERED_LANG_BTN = "";
        STEP_HELP_LEARN_MORE_BTN = "";
        STEP_HELP_GET_STARTED_BTN = "";
    }


}
