package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;

    @FindBy(css = "a[data-testid='btn-logout']")
    WebElement logoutButton;

    @FindBy(css = "a[data-testid='view-profile-link']")
    WebElement profileLink;

    @FindBy(css = "header, .header, .app-header")
    WebElement headerBar;

    // --- Navigation Locators (AC 3, 4) ---
    // Universal locator for sidebar navigation items (All Songs, Albums, Favorites, etc.)
    private final String navLinkXpath = "//nav//a[contains(.,'%s')]";

    // --- Profile & Preferences Form Locators (AC 5, 6) ---

    @FindBy(css = "form[data-testid='update-profile-form']")
    WebElement profileForm;

    @FindBy(css = "form[data-testid='update-profile-form'] input[name='email']")
    WebElement profileEmailField;

    @FindBy(css = "form[data-testid='update-profile-form'] input[name='current_password']")
    WebElement currentPasswordField; // Used for BOTH email and password changes

    @FindBy(css = "form[data-testid='update-profile-form'] input[name='new_password']")
    WebElement newPasswordField;

    @FindBy(css = "form[data-testid='update-profile-form'] button[type='submit'][class='btn-submit']")
    WebElement profileSaveButton;

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement updateProfileModalCloseButton;

    @FindBy(css = "input[type='search']")
    WebElement searchField;

    @FindBy(css = "a[href='#!/songs']")
    WebElement viewAllButton;

    @FindBy(css = "button[data-test='add-to-btn']")
    WebElement addToButton;

    @FindBy(css = "div.success.show")
    WebElement notificationSuccess;

    @FindBy(xpath = "//a[text()='Jennys second Playlist']")
    WebElement existingPlaylist;

    @FindBy(xpath = "//a[text()='Jennys third Playlist']")
    WebElement existingPlaylist2;

    @FindBy(xpath = "//a[contains(@href, '/playlist/105750')]")
    WebElement currentPlaylist;

    @FindBy(xpath = "//a[contains(@href, '/playlist/106208')]")
    WebElement currentPlaylist2;

    @FindBy(xpath = "//button[contains(@class, 'btn-delete-playlist')]")
    WebElement deleteButton;

    @FindBy(css = "[name='name']")
    WebElement playlistInputField;


    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }

    public void logOut() {
       wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
       click(logoutButton);
    }

    // --- Navigation Methods (AC 3, 4) ---
    public void navigateToPage(String pageName) {
        // This dynamic locator handles "Favorites", "All Songs", "Albums", etc. (AC 3)
        By locator = By.xpath(String.format("//nav//a[text()='%s']", pageName));
        // Wait for the element to be clickable before attempting to find it and click
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        click(link);
    }

    public boolean assertPageLoaded(String expectedPage) {
        // Asserts URL contains the page name (e.g., "favorites") (AC 4)
        String expectedUrlPart = expectedPage.toLowerCase().replace(" ", "");
        try {
            wait.until(ExpectedConditions.urlContains(expectedUrlPart));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean profileSettingsLinkAvailable() {
        wait.until(ExpectedConditions.visibilityOf(userAvatarIcon));
        return userAvatarIcon.isDisplayed();
    }

    // --- Account Update Methods (AC 5, 6) ---
    public void openProfileSettings() {
        click(userAvatarIcon);
        wait.until(ExpectedConditions.visibilityOf(profileLink));
        click(profileLink);
        wait.until(ExpectedConditions.elementToBeClickable(profileSaveButton));
        wait.until(ExpectedConditions.visibilityOf(currentPasswordField));
    }

    public void updatePassword(String oldPassword, String newPassword) {
        openProfileSettings();
        currentPasswordField.sendKeys(oldPassword);
        newPasswordField.sendKeys(newPassword);
        click(profileSaveButton);
    }

    public void updateEmailFieldInProfileAndPreferencesForm(String newEmail) {
        wait.until(ExpectedConditions.visibilityOf(profileEmailField));
        profileEmailField.clear();
        profileEmailField.sendKeys(newEmail);
        click(profileSaveButton);
    }

    public void enterMyCurrentPasswordInProfileAndPreferencesForm(String currentPassword) {
        wait.until(ExpectedConditions.visibilityOf(currentPasswordField));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void IClickSaveButtonInProfileAndPreferencesForm(String successMessage) {
        wait.until(ExpectedConditions.visibilityOf(profileSaveButton));
        click(profileSaveButton);
        wait.until(ExpectedConditions.visibilityOf(notificationSuccess));
        Assert.assertEquals(successMessage, notificationSuccess.getText());

        WebDriverWait _wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        _wait.until(ExpectedConditions.invisibilityOf(notificationSuccess));
    }

    public void updatePasswordFieldInProfileAndPreferencesForm(String newPassword) {
        wait.until(ExpectedConditions.visibilityOf(newPasswordField));
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    public void searchSong(String songName) {
        WebElement searchInput = findElement(searchField);
        searchInput.clear();
        searchInput.sendKeys(songName);
    }

    public void clickViewAll() {
        click(viewAllButton);
    }

    public void selectSongByTitle(String songTitle) {
        WebElement song = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@class='title' and text()='" + songTitle + "']")));
        click(song);
    }

    public void clickAddToButton() {
        click(addToButton);
    }

    public void choosePlaylist(String playlistName) {
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'playlist') and normalize-space(text())='Jennys Playlist']")));
        click(playlist);
    }

    public void chooseAllSongsList() {
        click(viewAllButton);
    }

    public String getAddToPlaylistSuccessMsg() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.success.show")));
        return findElement(notificationSuccess).getText();
    }

    public String getDeletePlaylistSuccessMsg() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.success.show")));
        return findElement(notificationSuccess).getText();
    }

    public void openPlaylist(String playlistId) {
        doubleClick(currentPlaylist);

    }
        public void openPlaylist2 (String playlistId){
            doubleClick(currentPlaylist2);

    }

    public void renamePlaylist(String newPlaylistName) {
        playlistInputField = findElement(playlistInputField);
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenameMessage() {
        return findElement(notificationSuccess).getText();
    }

    public void chooseExistingPlaylist() {
        click(existingPlaylist);
    }

    public void chooseExistingPlaylist2() {
        click(existingPlaylist2);
    }

    public void selectDeleteBtn() {
        click(deleteButton);
    }
}
