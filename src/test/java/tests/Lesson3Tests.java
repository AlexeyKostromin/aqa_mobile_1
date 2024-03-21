package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lesson3Tests extends TestBase {

    @Test
    void verifyTextInSearchField() {
        waitForElementPresent(
                By.id("org.wikipedia.alpha:id/imageViewCentered"),
                "Main image page not visible"
        );

        waitForElementAndClick(
                By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"),
                "Could not click 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Could not click 'Search Wikipedia' text box",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia.alpha:id/search_src_text"),
                "Search Wikipedia"
        );

    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String keys, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(keys);
        return element;
    }

    private void assertElementHasText(By by, String expectedText) {
        WebElement element = waitForElementPresent(by, "Failed to locate element");
        String elementText = element.getAttribute("text");
        assertEquals(
                expectedText, elementText,
                "Expected text for element is: " + expectedText + " but was: " + elementText);
    }

}
