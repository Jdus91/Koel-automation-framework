package pagefactory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(driver,this);
    }

    public WebElement findElement(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void hover(WebElement webElement) {
        try {
            WebElement el = findElement(webElement);

            if (el.isDisplayed()) {
                actions.moveToElement((el)).perform();
            }

        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            log.error("Reference Error");
        } catch (TimeoutException te) {
            log.error("Timeout");
        }
    }

    public void click(WebElement webElement) {
        findElement(webElement).click();
    }

    public void doubleClick(WebElement webElement) {
        actions.doubleClick(findElement(webElement)).perform();
    }

    public void contextClick(WebElement element) {
        actions.contextClick(findElement(element)).perform();

    }
}
