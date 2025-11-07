import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    // ThreadLocal provides thread-safe storage for the WebDriver instance.
    // Each thread gets its own copy, preventing conflicts during parallel execution.
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    /**
     * Retrieves the thread-safe WebDriver instance.
     * @return The WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setupBrowser(String baseURL) throws MalformedURLException {
        // 1. Pick the browser based on the system property.
        // This creates a new driver instance unique to this test method.
        WebDriver driver = pickBrowser(System.getProperty("browser", "chrome")); // Default to chrome if not specified

        // 2. Store this unique driver instance in our ThreadLocal container.
        threadDriver.set(driver);

        // 3. Configure the browser using the thread-safe getDriver() method.
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        // 4. Navigate to the initial URL.
        getDriver().get(baseURL);
    }

    @AfterMethod(alwaysRun = true) // alwaysRun=true ensures cleanup happens even if a test fails.
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit(); // Use quit() to close all windows and end the session safely.
            threadDriver.remove(); // Clean up the ThreadLocal to prevent memory leaks.
        }
    }

    /**
     * Creates and returns a WebDriver instance based on the specified browser name.
     * This method no longer has side effects; it only returns a new driver.
     *
     * @param browser The name of the browser to instantiate.
     * @return A new WebDriver instance.
     * @throws MalformedURLException If the Selenium Grid URL is invalid.
     */
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.0.97:4444/";

        switch (browser.toLowerCase()) { // Use toLowerCase() for case-insensitive matching.
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
            case "microsoftedge": // Allow for multiple names.
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return new EdgeDriver(edgeOptions);

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);

            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);

            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);

            case "cloud":
                return lambdaTest();

            case "chrome":
            default: // Default case handles "chrome" and any other value.
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("dev");
        HashMap<String, Object> ltOptions = new HashMap<>();
        // It's a best practice to get credentials from environment variables, not hardcode them.
        ltOptions.put("username", "jenniferdejesus");
        ltOptions.put("accessKey", "LT_5X3jPaBgaQ9PaYwyQ4NQdHzv9wQ9vOh1MxX4prdaBdORnDD");
        ltOptions.put("project", "Untitled");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
}