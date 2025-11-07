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

public class LoginStepDefinition {
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

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

