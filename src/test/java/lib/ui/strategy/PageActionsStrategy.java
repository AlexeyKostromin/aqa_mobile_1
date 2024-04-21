package lib.ui.strategy;

import org.openqa.selenium.WebElement;

public interface PageActionsStrategy {
    WebElement getElementByText(String text);
    void clickElementByText(String text);

}
