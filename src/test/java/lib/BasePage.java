package lib;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lib.ui.strategy.PageActionsStrategy;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class BasePage {
    private PageActionsStrategy strategy;

    protected RemoteWebDriver driver;

    public BasePage(RemoteWebDriver driver, PageActionsStrategy strategy) {
        this.driver = driver;
        this.strategy = strategy;
    }

    public By getLocatorByString(String locatorWithType) {
        String[] locatorWithTypeArray = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = locatorWithTypeArray[0];
        String locator = locatorWithTypeArray[1];

        return switch (byType) {
            case "id" -> By.id(locator);
            case "xpath" -> By.xpath(locator);
            case "css" -> By.cssSelector(locator);
            default -> throw new IllegalStateException("Could not parse locator. Locator: " + locatorWithType);
        };
    }

    public List<WebElement> getElements(String locator) {
        By by = getLocatorByString(locator);
        return driver.findElements(by);
    }

    public Boolean isElementPresent(String locator) {
        return getElements(locator).size() > 0;
    }


    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public WebElement waitForElementToBeClickable(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {
//        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        WebElement element = waitForElementToBeClickable(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndSendKeys(String locator, String keys, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(keys);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String keys, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public WebElement getElementByText(String text) {
        return strategy.getElementByText(text);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void clickElementByText(String text) {
        strategy.clickElementByText(text);
    }

    public void tryClickElementWithAttempts(String locator, String errorMessage, int attempts) {
        int currentAttempts = 0;
        boolean needModeAttempts = true;

        while (needModeAttempts) {
            try {
                waitForElementAndClick(locator, errorMessage, 1);
                needModeAttempts = false;
            } catch (Exception e) {
                if (currentAttempts > attempts) {
                    waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++currentAttempts;
        }
    }

    public WebElement findElementByTextInList(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            var elementText = element.getText();
            if (elementText.contains(text)) {
                return element;
            }
        }
        throw new AssertionError("Could not find element with text: " + text);
    }

    public Boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUp(int timeOfScroll) {
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), centerX, startY))
                .addAction(finger.createPointerDown(0))
                .addAction(finger.createPointerMove(Duration.ofMillis(timeOfScroll), PointerInput.Origin.viewport(), centerX, endY))
                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void swipeUpToElement(String locator, int maxSwipes, String errorMessage) {
        By by = getLocatorByString(locator);
        int swipesCount = 0;

        while (driver.findElements(by).size() == 0) {
            swipeUpQuick();
            ++swipesCount;

            if (swipesCount > maxSwipes) {
                throw new AssertionError("Could not find element by swiping up.\n" + errorMessage);
            }
        }
    }

    public void swipeUpTillElementAppear(String locator, int maxSwipes, String errorMessage) {
        int alreadySwiped = 0;
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                throw new RuntimeException(errorMessage);
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void scrollUpTillElementAppear(String locator, int maxSwipes, String errorMessage) {
        By by = getLocatorByString(locator);
        int swipesCount = 0;

        while (driver.findElements(by).size() == 0) {
            scrollUp(100);
            ++swipesCount;

            if (swipesCount > maxSwipes) {
                throw new AssertionError("Could not find element by swiping up.\n" + errorMessage);
            }
        }
    }

    public void scrollUpTillElementAppearJs(String locator, int maxSwipes, String errorMessage) {
        By by = getLocatorByString(locator);
        int swipesCount = 0;

        while (driver.findElements(by).size() == 0) {
            scrollWebPageUpJs();
            ++swipesCount;

            if (swipesCount > maxSwipes) {
                throw new AssertionError("Could not find element by swiping up.\n" + errorMessage);
            }
        }
    }


    public void scrollUp(int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public void scrollWebPageUpJs() {
        if (Platform.getInstance().isMobileWeb()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) this.driver;
            JSExecutor.executeScript("window.scrollBy(0,50)");
        } else {
            System.out.println("Method scrollWebPageUpJs() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(WebElement element) {
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), rightX, middleY))
                .addAction(finger.createPointerDown(0))
                .addAction(finger.createPointerMove(Duration.ofMillis(150), PointerInput.Origin.viewport(), leftX, middleY))
                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void swipeElementToLeftIOS(WebElement element) {
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        int offsetX = (-1 * element.getSize().getWidth());

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), rightX, middleY))
                .addAction(finger.createPointerDown(0))
                .addAction(finger.createPointerMove(Duration.ofMillis(150), PointerInput.Origin.viewport(), offsetX, middleY))
                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator,
                errorMessage,
                10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), rightX, middleY))
                .addAction(finger.createPointerDown(0))
                .addAction(finger.createPointerMove(Duration.ofMillis(150), PointerInput.Origin.viewport(), leftX, middleY))
                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    public void zoom(int x, int y) {
        Dimension size = driver.manage().window().getSize();
        int startX1 = x - (size.width / 4);
        int startY1 = y;
        int startX2 = x + (size.width / 4);
        int startY2 = y;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence finger1Sequence = new Sequence(finger1, 0);
        Sequence finger2Sequence = new Sequence(finger2, 1);

        // Finger 1 actions (move finger away from the center)
        finger1Sequence.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX1, startY1));
        finger1Sequence.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        finger1Sequence.addAction(finger1.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX1 - 100, startY1));
        finger1Sequence.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Finger 2 actions (move finger away from the center)
        finger2Sequence.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX2, startY2));
        finger2Sequence.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        finger2Sequence.addAction(finger2.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX2 + 100, startY2));
        finger2Sequence.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the zoom gesture
        driver.perform(Arrays.asList(finger1Sequence, finger2Sequence));
    }
    @Step("Rotate screen to landscape mode")
    public void setLandscapeOrientation() {
        strategy.setLandscapeOrientation();
    }
    @Step("Rotate screen to portrait mode")
    public void setPortraitOrientation() {
        strategy.setPortraitOrientation();
    }
    @Step("Run app in background")
    public void runAppInBackground(Duration duration) {
        strategy.runAppInBackground(duration);
    }
    @Step("Open Wiki Web Page For Mobile Web")
    public void openWikiWebPageForMobileWeb() {
        openWebPageForMobileWeb("https://en.m.wikipedia.org");
    }

    public void openWebPageForMobileWeb(String url) {
        if (Platform.getInstance().isMobileWeb()) {
            driver.get(url);
        } else {
            System.out.println("Method openWebPageForMobileWeb() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Platform", Platform.getInstance().getPlatformVar());
            props.setProperty("Runtime Environment", Platform.getInstance().getRuntimeEnvVar());
            props.store(fos, "See documentation");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

    public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File((path)));
            System.out.println("Screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }


}
