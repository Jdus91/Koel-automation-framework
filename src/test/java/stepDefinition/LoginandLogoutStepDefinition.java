package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pagefactory.HomePage;
import pagefactory.LoginPage;

import java.time.Duration;

public class LoginandLogoutStepDefinition extends BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I open Login Page")
    public void iOpenLoginPage() {
        driver.get("https://qa.koel.app");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.providePassword(password);
    }

    @And("I submit")
    public void iSubmit() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSubmit();
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        HomePage homePage = new HomePage(driver);
        homePage.getUserAvatar();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Then("I see an error message")
    public void iSeeAnErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.assertLoginFailed(), "Error message not found.");
    }

    @Then("I see the email required field validation message")
    public void iSeeTheEmailRequiredFieldValidationMessage() {
        // 1. Define the expected Login Page URL (ensure this is correct)
        final String expectedUrl = "https://qa.koel.app/";

        // 2. Define the form locator to ensure the element is still present
        By formLocator = By.xpath("//*[@id='app']/div/div/form");

        // Assertion 1: Check that the URL did NOT change, proving submission was blocked.
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl,
                "Expected to remain on the Login page, but the URL changed.");

        // Assertion 2: Check that the form element is still visible.
        Assert.assertTrue(driver.findElement(formLocator).isDisplayed(),
                "Login form is not displayed, suggesting the page content changed.");
    }

    @Then("I see the password required field validation message")
    public void iSeeThePasswordRequiredFieldValidationMessage() {
        // 1. Define the expected Login Page URL (ensure this is correct)
        final String expectedUrl = "https://qa.koel.app/";

        // 2. Define the form locator to ensure the element is still present
        By formLocator = By.xpath("//*[@id=\"app\"]/div/div/form/input[2]");

        // Assertion 1: Check that the URL did NOT change, proving submission was blocked.
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl,
                "Expected to remain on the Login page, but the URL changed.");

        // Assertion 2: Check that the form element is still visible.
        Assert.assertTrue(driver.findElement(formLocator).isDisplayed(),
                "Login form is not displayed, suggesting the page content changed.");
    }

    @And("I navigate to {string}")
    public void iNavigateTo(String pageName) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToPage(pageName);
    }

    @And("I navigate to Favorites page")
    public void iNavigateToFavoritesPage() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToPage("Favorites");
    }

    @And("I log out")
    public void iLogOut() {
        // AC 4, 6: Logs the user out
        HomePage homePage = new HomePage(driver);
        homePage.logOut();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    @Then("I am taken to the {string} page")
    public void iAmTakenToThePage(String expectedPage) {
        // AC 3 & 4: Checks if the URL/element matches the page
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.assertPageLoaded(expectedPage),
                "Assertion Failed: Expected to land on the '" + expectedPage + "' page.");
    }

    @And("I enter new email in profile and preferences form {string}")
    public void iUpdateMyEmailInProfileAndPreferencesForm(String newEmail) {
        // AC 5: Simulates the action of updating the email
        HomePage homePage = new HomePage(driver);
        homePage.updateEmailFieldInProfileAndPreferencesForm(newEmail);
    }

    @And("I enter new password in profile and preferences form {string}")
    public void iUpdateMyPasswordInProfileAndPreferencesForm(String newPassword) {
        // AC 6: Simulates the action of updating the password
        HomePage homePage = new HomePage(driver);
        homePage.updatePasswordFieldInProfileAndPreferencesForm(newPassword);
    }

    @And("I enter current password in profile and preferences form {string}")
    public void iEnterMyCurrentPasswordInProfileAndPreferencesForm(String currentPassword) {
        // AC 5: Simulates the action of entering the current password
        HomePage homePage = new HomePage(driver);
        homePage.enterMyCurrentPasswordInProfileAndPreferencesForm(currentPassword);
    }

    @When("I click save on profile and preferences form a {string} message appears")
    public void IClickSaveButtonInProfileAndPreferencesForm(String successMessage) {
        HomePage homePage = new HomePage(driver);
        homePage.IClickSaveButtonInProfileAndPreferencesForm(successMessage);
    }

    @When("profile icon is available")
    public void profileIconIsAvailable() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.profileSettingsLinkAvailable(), "Profile icon not available.");
    }

    @And("I click profile icon")
    public void clickProfileIcon() {
        HomePage homePage = new HomePage(driver);
        homePage.openProfileSettings();
    }

    @When("profile and preferences form appears")
    public void profileAndPreferencesFormIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.profileSettingsFormAvailable(), "Profile and Preferences form not available.");

    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}