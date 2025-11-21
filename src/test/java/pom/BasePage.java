package pom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    public static final Logger log = LoggerFactory.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void hover(By locator) {
        try {
            WebElement el = findElement(locator);

            if (el.isDisplayed()) {
                actions.moveToElement((el)).perform();
            }

        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            log.error("Reference Error");
        } catch (TimeoutException te) {
            log.error("Timeout");
        }
    }

    public void click(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            log.error("Reference Error");
        } catch (TimeoutException te) {
            log.error("Timeout");
        }
    }
    public void doubleClick (By locator) {
        actions.doubleClick(findElement(locator)).perform();
    }

    public void enterNewPlaylistName(String newPlaylistName) {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlayListSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }
}
