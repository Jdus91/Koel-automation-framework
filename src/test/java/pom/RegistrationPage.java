package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    private By registrationLink = By.cssSelector("a[href='registration']");
    public void navigateToMainPage() {
        driver.get("https://qa.koel.app/");
    }

    public void clickRegistrationLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(registrationLink));
        link.click();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }    
}
