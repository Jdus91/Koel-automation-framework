package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {
    //URL for navigation check
    private final String expectedLoginUrl = "https://qa.koel.app/";

    @FindBy(css = "input[type='email']")
    WebElement emailField;
    @FindBy(css = "input[type='password']")
    WebElement passwordField;
    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;
    @FindBy(xpath = "//*[@id='app']/div/div/form")
    WebElement loginFormContainer;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/form/input[2]")
    WebElement loginFormContainer2;


    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Fluent interface
    public LoginPage provideEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmit() {
        click(submitBtn);
        return this;
    }

    public void assertLoginFailed() {
        // Wait for the 'class' attribute of the loginFormContainer to contain "error".
        // This is the assertion for failed login.
        wait.until(ExpectedConditions.attributeContains(loginFormContainer, "class", "error"));
    }

    public void assertLoginFailed2() {
        // Wait for the 'class' attribute of the loginFormContainer to contain "error".
        // This is the assertion for failed login.
        wait.until(ExpectedConditions.attributeContains(loginFormContainer2, "class", "error"));
    }
    public void assertEmailFieldBlocked() {
        // Assertion 1: Check that the URL did NOT change, proving submission was blocked.
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl,
                "Expected to remain on the Login page (Email Block), but URL changed.");

        // Assertion 2: Check that the Email Input WebElement is visible and present.
        // FIX: Using the WebElement 'emailField' directly.
        Assert.assertTrue(emailField.isDisplayed(),
                "Email input element is not displayed, suggesting the page content changed.");
    }

    /**
     * Asserts client-side block (native browser validation) on the Password field.
     * Used for correct email but blank password submission.
     */
    public void assertPasswordFieldBlocked() {
        // Assertion 1: Check that the URL did NOT change, proving submission was blocked.
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedLoginUrl,
                "Expected to remain on the Login page (Password Block), but URL changed.");

        // Assertion 2: Check that the Password Input WebElement is visible and present.
        // FIX: Using the WebElement 'passwordField' directly.
        Assert.assertTrue(passwordField.isDisplayed(),
                "Password input element is not displayed, suggesting the page content changed.");
    }
}
