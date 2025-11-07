import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.LoginPage;
import pagefactory.HomePage;
import DataProvider.TestDataProviders;

public class LoginTests extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

    @Test
    public void loginValidEmailPassword() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    // Updated annotation to point to the external DataProvider class
    @Test(dataProvider = "negativeLoginCredentials", dataProviderClass = TestDataProviders.class)
    public void loginNegativeCredentialsTest(String email, String password, String testCaseName) {
        log.info("Running Test: " + testCaseName);
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();

        // Assertion: Verify the server-side error (form shake/error class) is displayed
        loginPage.assertLoginFailed();
    }
    // Scenario: Login with correct email and blank password
    @Test
    public void loginNegativeBlankPasswordTest() {
        log.info("Running Test: Correct Email, Blank Password");
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io")
                .providePassword("") // Blank password
                .clickSubmit();

        // Assertion: Verify the submission was blocked, focused on the password field
        loginPage.assertPasswordFieldBlocked();
    }

    // Scenario: Login with empty email and empty password
    @Test
    public void loginNegativeEmptyEmailAndPasswordTest() {
        log.info("Running Test: Empty Email and Password");
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("") // Blank email
                .providePassword("") // Blank password
                .clickSubmit();

        // Assertion: Verify the submission was blocked, focused on the email field (first required field)
        loginPage.assertEmailFieldBlocked();
    }

    // Scenario: Login with blank email and correct password
    @Test
    public void loginNegativeBlankEmailTest() {
        log.info("Running Test: Blank Email, Correct Password");
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("") // Blank email
                .providePassword("FCVlLOni")
                .clickSubmit();

        // Assertion: Verify the submission was blocked, focused on the email field (first required field)
        loginPage.assertEmailFieldBlocked();
    }
}
