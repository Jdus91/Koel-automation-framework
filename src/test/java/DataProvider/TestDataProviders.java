package DataProvider;
import org.testng.annotations.DataProvider;

public class TestDataProviders {
    @DataProvider(name = "negativeLoginCredentials")
    public static Object[][] negativeLoginCredentials() {
        // Data format: { email, password, testCaseName }
        return new Object[][]{
                // 1. Incorrect password
                {"jennifer.de.jesus@testpro.io", "wrongPassword", "Incorrect Password"},
                // 2. Non-existent email
                {"nonexistent@testpro.io", "anyPassword", "Non-existent Email"},
                // 3. Correct email, incorrect password
                {"jennifer.de.jesus@testpro.io", "reallybadpass", "Correct Email, Incorrect Password"},
                // 4. Incorrect email format
                {"notanemail@testpro.com", "FCVlLOni", "Incorrect Email Format"}
        };
    }
}
