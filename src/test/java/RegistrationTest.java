import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.RegistrationPage;
import stepDefinition.BaseTest;

public class RegistrationTest extends BaseTest {
    @Test
    public void registrationNavigation() {

        //RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(getDriver());

        registrationPage.navigateToMainPage();

        registrationPage.clickRegistrationLink();

        String registrationUrl = "https://qa.koel.app/registration";
        Assert.assertEquals(registrationPage.getCurrentUrl(), registrationUrl);
    }
}
