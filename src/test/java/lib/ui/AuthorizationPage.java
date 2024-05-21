package lib.ui;

import lib.BasePage;
import lib.ui.strategy.PageActionsStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPage extends BasePage {

    private static final String
            LOGIN_BUTTON = "xpath://a[@type='button']/span[text()='Log in']",
            USERNAME_INPUT = "xpath://*[@id='wpName1']",
            PASSWORD_INPUT = "xpath://*[@id='wpPassword1']",
            SUBMIT_BUTTON = "xpath://*[@id='wpLoginAttempt']";

    public AuthorizationPage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        super(driver, strategy);
    }

    public void clickAuthButton(){
        waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginData(String userName, String password){
        waitForElementAndSendKeys(USERNAME_INPUT, userName, "Cannot enter username", 5);
        waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot enter password", 5);
    }

    public void submitForm(){
        waitForElementAndClick(SUBMIT_BUTTON, "Cannot click on submit bnt", 5);
    }

}
