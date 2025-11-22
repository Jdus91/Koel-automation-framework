package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='registration']")
    WebElement registrationLink;

    public void navigateToMainPage() {
        driver.get("https://qa.koel.app/");
    }

    public void clickRegistrationLink() {
        click(registrationLink);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
