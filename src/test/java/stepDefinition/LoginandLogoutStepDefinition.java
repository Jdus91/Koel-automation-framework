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

public class LoginandLogoutStepDefinition extends BaseTest{
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
        loginPage.assertLoginFailed();
        //By formLocator = By.xpath("//*[@id='app']/div/div/form");
        //wait.until(ExpectedConditions.attributeContains(formLocator, "class", "error"));
        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']/div/div/form"))).isDisplayed());
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

    @When("I update my email from {string} to {string} using password {string}")
    public void iUpdateMyEmailFromToUsingPassword(String oldEmail, String newEmail, String password) {
        // AC 5: Simulates the action of updating the email
        HomePage homePage = new HomePage(driver);
        homePage.updateEmail(newEmail, password);
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
    @After
    public void closeBrowser() {
            if (driver != null) {
                driver.quit();
            }
        }
    }