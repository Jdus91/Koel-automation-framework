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

public class LoginStepDefinition extends BaseTest {
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
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://qa.koel.app");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
        /*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']"))).sendKeys(email);*/
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        loginPage.providePassword(password);
        /*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).sendKeys(password);*/
    }

    @And("I submit")
    public void iSubmit() {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSubmit();
        /*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']"))).click();*/
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        HomePage homePage = new HomePage(driver);
        homePage.getUserAvatar();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
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
        homePage.clickLogout();

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

    @When("I update my password from {string} to {string}")
    public void iUpdateMyPasswordFromTo(String oldPassword, String newPassword) {
        // AC 6: Simulates the action of updating the password
        HomePage homePage = new HomePage(driver);
        homePage.updatePassword(oldPassword, newPassword);
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


    @Then("I can log in with {string} and password {string}")
    public void iCanLogInWithAndPassword(String email, String password) {
        // This is a full re-login, starting from the login page
        driver.get("https://qa.koel.app");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email).providePassword(password).clickSubmit();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed(),
                "Assertion Failed: Login with NEW credentials failed.");
    }

    @And("I cannot log in with {string} and password {string}")
    public void iCannotLogInWithAndPassword(String email, String password) {
        // This is a full re-login, starting from the login page
        driver.get("https://qa.koel.app");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email).providePassword(password).clickSubmit();

        // Assert that the login FAILED (the error shake appeared)
        Assert.assertTrue(loginPage.assertLoginFailed(),
                "Assertion Failed: Login with OLD credentials should have failed, but it succeeded.");
    }

    // NEW steps to assert presence/adjacency and pages
    @Then("I am on the Home page")
    public void iAmOnTheHomePage() {
        // URL contains #!/home and avatar visible
        Assert.assertTrue(driver.getCurrentUrl().contains("/#!/home"));
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.getUserAvatar().isDisplayed(), "Avatar not visible on Home");
    }

    @Then("I should see the {string} control")
    public void iShouldSeeTheControl(String label) {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHeaderControlVisible(label),
                "Expected to see control: " + label);
    }

    @Then("the {string} control is next to the {string} control")
    public void controlIsNextToControl(String right, String left) {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.areHeaderControlsAdjacent(left, right),
                "Expected '" + right + "' next to '" + left + "'");
    }

    @Then("I am on the Login page")
    public void iAmOnTheLoginPageAssert() {
        // Koel login is the base route; accept either / or /#!/login if your app uses hash routing
        String url = driver.getCurrentUrl();
        boolean looksLikeLogin = url.equals("https://qa.koel.app/") ||
                url.contains("/#!/login");
        Assert.assertTrue(looksLikeLogin, "Not on Login page. URL: " + url);
    }

    //New step definitions for AC5

    @When("profile icon is available")
    public void profileIconIsAvailable() {
        HomePage homepage = new HomePage(driver);
        Assert.assertTrue(homepage.profileSettingsLinkAvailable(), "Profile icon not available.");
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

    //Logout new sep definitions
     @Then("Logout option is visible")
    public void logoutOptionIsVisible() {
        // Write code here that turns the phrase above into concrete actions
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.logoutButton().isDisplayed(), "Logout option not visible.");
    }

    
    @When("I click on Logout option")
    public void iClickOnLogoutOption() {
        // Write code here that turns the phrase above into concrete actions
        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    @Then("I am logged out")
    public void iAmLoggedOut() {    
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    @Then("I am redirected to Login Page")
    public void iAmRedirectedToLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    


    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}