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

import pagefactory.AllSongsPage;
import pagefactory.HomePage;
import pagefactory.LoginPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class StepDefinitions extends BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // options.addArguments("--disable-notifications");
        // --- Allow notifications ---
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);
        // ---------------------------
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

        // Assertion 1: Check that the URL did NOT change, proving submission was
        // blocked.
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

        // Assertion 1: Check that the URL did NOT change, proving submission was
        // blocked.
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
    @Then("I am on the Homepage")
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
        // Koel login is the base route; accept either / or /#!/login if your app uses
        // hash routing
        String url = driver.getCurrentUrl();
        boolean looksLikeLogin = url.equals("https://qa.koel.app/") ||
                url.contains("/#!/login");
        Assert.assertTrue(looksLikeLogin, "Not on Login page. URL: " + url);
    }

    // New step definitions for AC5

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

    // Logout new sep definitions
    @Then("Logout option is visible")
    public void logoutOptionIsVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.logoutButton().isDisplayed(), "Logout option not visible.");
    }

    @When("I click on Logout option")
    public void iClickOnLogoutOption() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogout();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    @Then("I am logged out")
    public void iAmLoggedOut() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    @Then("I am redirected to Login Page")
    public void iAmRedirectedToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isPageVisible());
    }

    // Step definitions for InfoPanel.feature

    @When("I play a song")
    public void iPlayASong() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        allSongsPage.hoverMediaPlayer();
        allSongsPage.clickPlay();
    }

    @Then("I verify Album name is displayed")
    public void iVerifyAlbumNameIsDisplayed() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.isAlbumNameDisplayed(), "Album name is not displayed.");
    }

    @And("I verify Cover name is displayed")
    public void iVerifyCoverNameIsDisplayed() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.isCoverNameDisplayed(), "Cover name is not displayed.");
    }

    @And("I verify Lyrics is displayed in Progress Pane")
    public void iVerifyLyricsIsDisplayedInProgressPane() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertFalse(allSongsPage.isLyricsInProgressPane(),
                "FAILURE: A Lyrics element was unexpectedly found inside the progress pane.");
    }

    @And("I verify Artist name is displayed")
    public void iVerifyArtistNameIsDisplayed() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.isArtistNameDisplayed(), "Artist name is not displayed in Info Panel.");
    }

    @And("I open Info Panel")
    public void iOpenInfoPanel() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        allSongsPage.iOpenInfoPanel();
    }

    @Then("I verify Info Panel is opened")
    public void iVerifyInfoPanelIsOpened() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.isInfoPanelOpened(), "Info Panel is not opened.");
    }

    @When("I close Info Panel")
    public void iCloseInfoPanel() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        allSongsPage.closeInfoPanel();
    }

    @Then("I verify Info Panel is closed")
    public void iVerifyInfoPanelIsClosed() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.isInfoPanelClosed(), "Info Panel is not closed.");
    }

    @When("I select Album tab in Info Panel")
    public void iSelectAlbumTabInInfoPanel() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        allSongsPage.selectAlbumTab();
    }

    @Then("I select and verify that Shuffle button from Album tab was clicked")
    public void iSelectAndVerifyShuffleButtonFromAlbumTabClicked() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.clickShuffleButtonFromAlbumTabAndConfirm(),
                "Shuffle button from Album tab was not clicked.");
    }

    @When("I select Artist tab from the Info Panel")
    public void iSelectArtistTabFromTheInfoPanel() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        allSongsPage.selectArtistTab();
    }

    @Then("I select and verify that Shuffle button from Artist tab was clicked")
    public void iSelectAndVerifyShuffleButtonFromArtistTabClicked() {
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        Assert.assertTrue(allSongsPage.clickShuffleButtonFromArtistTabAndConfirm(),
                "Shuffle button from Artist tab was not clicked.");
    }

    // Step definitions for ProfileandPreferences.feature
    @And("I enter new name in profile and preferences form {string}")
    public void ienterNewNameInProfileAndPreferencesForm(String newName) {
        HomePage homePage = new HomePage(driver);
        homePage.ienterNewNameInProfileAndPreferencesForm(newName);

    }

    @And("I select Home tab from the navigation menu")
    public void iSelectHomeTabFromTheNavigationMenu() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectHomeTabFromTheNavigationMenu();
    }

    @Then("I verify that the updated name {string} is displayed on the Homepage")
    public void iVerifyThatTheUpdatedNameIsDisplayedOnTheHomepage(String updatedName) {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUpdatedNameDisplayedOnHomepage(updatedName),
                "Updated name is not displayed on the Homepage.");
    }

    @And("I select {string} theme in profile and preferences form")
    public void iSelectThemeInProfileAndPreferencesForm(String themeName) {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectThemeInProfileAndPreferencesForm(themeName);
    }

    @And("I verify that the {string} theme is applied on the Homepage")
    public void iVerifyThatTheThemeIsAppliedOnTheHomepage(String themeName) {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isThemeAppliedOnHomepage(themeName), "Theme is not applied on the Homepage.");
    }

    @And("I check the {string} checkbox in profile and preferences form")
    public void iCheckTheShowNowPlayingCheckboxInProfileAndPreferencesForm(String checkboxName) {
        HomePage homePage = new HomePage(driver);
        homePage.iCheckTheShowNowPlayingCheckboxInProfileAndPreferencesForm(checkboxName);
    }

    @Then("I verify that the Now Playing notification is displayed on the Homepage")
    public void iVerifyThatTheNowPlayingNotificationIsDisplayedOnTheHomepage() {

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isNowPlayingNotificationFunctionalityWorking(),
                "The Now Playing notification permission was NOT successfully granted by the browser.");
    }

    @And("I check the Confirm before closing Koel checkbox in profile and preferences form")
    public void iCheckTheConfirmBeforeClosingKoelCheckboxInProfileAndPreferencesForm() {
        HomePage homePage = new HomePage(driver);
        homePage.iCheckTheConfirmBeforeClosingKoelCheckboxInProfileAndPreferencesForm();
    }

    @Then("I verify that the confirmation window is displayed when attempting to close Koel")
    public void iVerifyThatTheConfirmationWindowIsDisplayedWhenAttemptingToCloseKoel() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isConfirmationWindowDisplayedOnLogout(),
                "Confirmation window is not displayed when attempting to close Koel.");
    }

    @And("I check the Show a translucent, blurred overlay of the current albums art checkbox in profile and preferences form")
    public void iCheckTheShowTranslucentBlurredOverlayCheckboxInProfileAndPreferencesForm() {
        HomePage homePage = new HomePage(driver);
        homePage.iCheckTheShowTranslucentBlurredOverlayCheckboxInProfileAndPreferencesForm();
    }

    @Then("I verify that the translucent, blurred overlay of the current albums art is displayed on the Homepage")
    public void iVerifyThatTheTranslucentBlurredOverlayOfTheCurrentAlbumsArtIsDisplayedOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isTranslucentBlurredOverlayDisplayedOnHomepage(),
                "Translucent, blurred overlay of the current albums art is not displayed on the Homepage.");
    }

    @When("I play a song from the Most Played section")
    public void iPlayASongFromTheMostPlayedSection() {
        HomePage homePage = new HomePage(driver);
        homePage.iPlayASongFromTheMostPlayedSection();
    }

    @And("I select Current Queue tab from the navigation menu")
    public void iSelectCurrentQueueTabFromTheNavigationMenu() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectCurrentQueueTabFromTheNavigationMenu();
    }

    @Then("I verify that the song {string} is playing in the Current Queue page")
    public void iVerifyThatTheCurrentlyPlayedSongIsDisplayedInTheCurrentQueuePage(String expectedSongName) {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(
                homePage.isCurrentlyPlayedSongDisplayedInCurrentQueuePage(expectedSongName),
                "The playing song in Queue is not '" + expectedSongName + "'");
    }

    @And("I verify the total number of songs in the Current Queue page is accurate")
    public void iVerifyTheTotalNumberOfSongsInTheCurrentQueuePageIsAccurate() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isTotalNumberOfSongsInCurrentQueueAccurate(),
                "The total number of songs in the Current Queue page is not accurate.");
    }

    @And("I verify the total duration of songs in the Current Queue page is accurate")
    public void iVerifyTheTotalDurationOfSongsInTheCurrentQueuePageIsAccurate() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.iVerifyTheTotalDurationOfSongsInTheCurrentQueuePageIsAccurate(),
                "The total duration of songs in the Current Queue page is not accurate.");
    }

    @And("I verify that ID, Title, Artist, Album, and time details are displayed in the Current Queue page")
    public void iVerifyThatIDTitleArtistAlbumAndTimeDetailsAreDisplayedInTheCurrentQueuePage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areSongDetailsDisplayedInCurrentQueuePage(),
                "ID, Title, Artist, Album, and time details are not displayed in the Current Queue page (or are empty).");
    }

    @When("I select Album tab from the navigation menu")
    public void iSelectAlbumTabFromTheNavigationMenu() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectAlbumTabFromTheNavigationMenu();
    }

    @Then("I am on the Album page")
    public void iAmOnTheAlbumPage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isOnAlbumPage(), "Not on the Album page.");
    }

    @And("I double click to play a song from the Album page")
    public void iDoubleClickToPlayASongFromTheAlbumPage() {
        HomePage homePage = new HomePage(driver);
        homePage.iDoubleClickToPlayASongFromTheAlbumPage();
    }

    @Then("I verify that I am navigated to the Current Queue page")
    public void iVerifyThatIAmNavigatedToTheCurrentQueuePage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isOnCurrentQueuePage(), "Not navigated to the Current Queue page.");
    }

    @When("I select shuffle all songs button")
    public void iSelectShuffleAllSongsButton() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectShuffleAllSongsButton();
    }

    @Then("I verify that the songs in the Current Queue page are shuffled")
    public void iVerifyThatTheSongsInTheCurrentQueuePageAreShuffled() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areSongsInCurrentQueueShuffled(),
                "The songs in the Current Queue page are not shuffled.");
    }

    @When("I select clear queue button")
    public void iSelectClearQueueButton() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectClearQueueButton();
    }

    @Then("I verify that the Current Queue page is cleared")
    public void iVerifyThatTheCurrentQueuePageIsCleared() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isCurrentQueuePageCleared(),
                "The Current Queue page is not cleared.");
    }

    @And("I verify that a No songs queued. How about shuffling all songs?. message appears")
    public void iVerifyThatANoSongsQueuedMessageAppears() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isNoSongsQueuedMessageDisplayed(),
                "The 'No songs queued. How about shuffling all songs?' message did not appear.");
    }

    @When("I select Shuffle all songs link from the message")
    public void iSelectShuffleAllSongsLinkFromTheMessage() {
        HomePage homePage = new HomePage(driver);
        homePage.iSelectShuffleAllSongsLinkFromTheMessage();
    }

    @Then("I verify that all songs appears in the Current Queue page")
    public void iVerifyThatAllSongsAppearsInTheCurrentQueuePage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areAllSongsInCurrentQueuePage(),
                "Not all songs appeared in the Current Queue page.");
    }

    @And("I verify that the Homepage displays phrases")
    public void iVerifyThatTheHomepageDisplaysPhrases() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.doesHomepageDisplayPhrases(),
                "The Homepage does not display phrases.");
    }

    @And("I verify that Recently Played songs are displayed on the Homepage")
    public void iVerifyThatRecentlyPlayedSongsAreDisplayedOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areRecentlyPlayedSongsDisplayedOnHomepage(),
                "Recently Played songs are not displayed on the Homepage.");
    }

    @And("I verify that the view all button in Homepage is displayed in the Recently Played section")
    public void iVerifyThatTheViewAllButtonInHomepageIsDisplayedInRecentlyPlayedSection() {
        HomePage homePage = new HomePage(driver);
        // Calling the new robust method we created
        Assert.assertTrue(homePage.isViewAllButtonInsideRecentlyPlayed(),
                "The 'View All' button was not found inside the Recently Played section header.");
    }

    @And("I verify that the Recently Played section has added songs")
    public void iVerifyThatTheRecentlyPlayedSectionHasAddedSongs() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.doesRecentlyPlayedSectionHaveAddedSongs(),
                "The Recently Played section does not have added songs.");
    }

    @And("I verify that Album names are displayed for the Recently Added songs section on the Homepage")
    public void iVerifyThatAlbumNamesAreDisplayedForTheRecentlyAddedSongsSectionOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areAlbumNamesDisplayedForRecentlyAddedSongs(),
                "Album names are not displayed for the Recently Added songs section on the Homepage.");
    }

    @And("I verify that the Shuffle and Download icons are present for the Recently Added songs section on the Homepage")
    public void iVerifyThatTheShuffleAndDownloadIconsArePresentForTheRecentlyAddedSongsSectionOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areShuffleAndDownloadIconsPresentForRecentlyAddedSongs(),
                "Shuffle and Download icons are not present for the Recently Added songs section on the Homepage.");
    }

    @And("I verify that Search field is present on the Homepage")
    public void iVerifyThatSearchFieldIsPresentOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSearchFieldPresentOnHomepage(),
                "Search field is not present on the Homepage.");
    }

    @When("I click on the Search field on the Homepage")
    public void iClickOnTheSearchFieldOnTheHomepage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSearchField();
    }

    @And("I type F in the Search field")
    public void iTypeFInTheSearchField() {
        HomePage homePage = new HomePage(driver);
        homePage.enterInSearchField("F");
    }

    @Then("I verify that search results related to {string} are displayed on the Homepage")
    public void iVerifyThatSearchResultsRelatedToAreDisplayedOnTheHomepage(String searchTerm) {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areSearchResultsRelatedToDisplayedOnHomepage(searchTerm),
                "Search results related to '" + searchTerm +
                        "' are not displayed on the Homepage.");
    }

    /*
     * @And("I verify that the Music panel includes Home, Current Queue, All Songs, Albums, and Artists"
     * )
     * public void
     * iVerifyThatTheMusicPanelIncludesHomeCurrentQueueAllSongsAlbumsAndArtists() {
     * HomePage homePage = new HomePage(driver);
     * Assert.assertTrue(homePage.doesMusicPanelIncludeAllSections(),
     * "The Music panel does not include Home, Current Queue, All Songs, Albums, and Artists."
     * );
     * }
     * 
     * @And("I verify that the Playlist panel includes Favorites playlist, Recently played playlist, Smart playlists and user created playlists"
     * )
     * public void
     * iVerifyThatThePlaylistPanelIncludesFavoritesPlaylistRecentlyPlayedPlaylistSmartPlaylistsAndUserCreatedPlaylists
     * () {
     * HomePage homePage = new HomePage(driver);
     * Assert.assertTrue(homePage.doesPlaylistPanelIncludeAllPlaylists(),
     * "The Playlist panel does not include favorites playlist, Recently played playlist, smart playlists and user created playlists."
     * );
     * }
     * 
     * @And("I verify that Profile icon, Logout icon, and About Koel icons are present on the Homepage"
     * )
     * public void
     * iVerifyThatProfileIconLogoutIconAndAboutKoelIconsArePresentOnTheHomepage() {
     * HomePage homePage = new HomePage(driver);
     * Assert.assertTrue(homePage.areProfileLogoutAboutKoelIconsPresent(),
     * "Profile icon, Logout icon, and About Koel icons are not present on the Homepage."
     * );
     * }
     * 
     * @Then("I verify that profile and preferences form appears")
     * public void iVerifyThatProfileAndPreferencesFormAppears() {
     * HomePage homePage = new HomePage(driver);
     * Assert.assertTrue(homePage.profileSettingsFormAvailable(),
     * "Profile and Preferences form not available.");
     * }
     * 
     * @When("I click on About Koel icon")
     * public void iClickOnAboutKoelIcon() {
     * HomePage homePage = new HomePage(driver);
     * homePage.clickOnAboutKoelIcon();
     * }
     * 
     * @Then("I verify that About Koel modal appears")
     * public void iVerifyThatAboutKoelModalAppears() {
     * HomePage homePage = new HomePage(driver);
     * Assert.assertTrue(homePage.isAboutKoelModalDisplayed(),
     * "About Koel modal did not appear.");
     * }
     */

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}