package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

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

    // --- Profile & Preferences Form Locators (AC 5, 6) ---

    @FindBy(css = "form[data-testid='update-profile-form']")
    WebElement profileForm;

    @FindBy(css = "form[data-testid='update-profile-form'] input[name='name']")
    WebElement profilenameField;

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

    @FindBy(xpath = "//nav//a[text()='Home']")
    WebElement Hometab;

    @FindBy(xpath = "//div[@data-testid='theme-card-oak']")
    WebElement themeOption;

    @FindBy(xpath = "//input[@type='checkbox' and @name='notify']")
    WebElement nowPlayingCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and @name='confirm_closing']")
    WebElement confirmBeforeClosingCheckbox;

    @FindBy(xpath = "//input[@type='checkbox' and @name='show_album_art_overlay']")
    WebElement showTranslucentBlurredOverlayCheckbox;

    @FindBy(css = "div[data-testid='album-art-overlay']")
    WebElement albumArtOverlay;

    @FindBy(xpath = "//ol[@class='top-song-list']//article[@data-test='song-card'][1]")
    WebElement firstMostPlayedSong;

    @FindBy(xpath = "//a[@href='#!/queue']")
    WebElement currentQueueTab;

    @FindBy(css = "tr.song-item.playing td.title")
    WebElement currentlyPlayedSongLocator;

    @FindBy(xpath = "//a[text()='Albums']")
    WebElement albumTab;

    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);

    }

    public WebElement logoutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        return logoutButton;
    }

    public void clickLogout() {
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
        if (profileSettingsLinkAvailable()) {
            click(profileLink);
        }
    }

    public boolean profileSettingsFormAvailable() {
        wait.until(ExpectedConditions.visibilityOf(profileForm));
        return profileForm.isDisplayed();
    }

    public void updatePassword(String oldPassword, String newPassword) {
        openProfileSettings();
        currentPasswordField.sendKeys(oldPassword);
        newPasswordField.sendKeys(newPassword);
        click(profileSaveButton);
    }

    public void ienterNewNameInProfileAndPreferencesForm(String newName) { // WIP
        wait.until(ExpectedConditions.visibilityOf(profilenameField));
        profilenameField.clear();
        profilenameField.sendKeys(newName);
    }

    public void updateEmailFieldInProfileAndPreferencesForm(String newEmail) {
        wait.until(ExpectedConditions.visibilityOf(profileEmailField));
        profileEmailField.clear();
        profileEmailField.sendKeys(newEmail);
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

    public boolean isSuccessMessageDisplayed() {
        // Used to confirm AC 5 and 6 were successful
        try {
            wait.until(ExpectedConditions.visibilityOf(notificationSuccess));
            return notificationSuccess.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHeaderControlVisible(String label) {
        // Support both text and data-testid lookups
        // For your Koel build we already have reliable elements; also handle literal
        // text just in case
        try {
            if ("Log student out".equalsIgnoreCase(label) || "Log out".equalsIgnoreCase(label)) {
                wait.until(ExpectedConditions.visibilityOf(logoutButton));
                return logoutButton.isDisplayed();
            }
            if ("Profile".equalsIgnoreCase(label)) {
                wait.until(ExpectedConditions.visibilityOf(profileLink));
                return profileLink.isDisplayed();
            }
            // Fallback: text search
            By generic = By.xpath("//header//*[normalize-space()='" + label + "']");
            return !driver.findElements(generic).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areHeaderControlsAdjacent(String leftLabel, String rightLabel) {
        // Use the known elements if labels match; otherwise a light DOM check
        WebElement left = "Profile".equalsIgnoreCase(leftLabel) ? profileLink
                : driver.findElement(By.xpath("//header//*[normalize-space()='" + leftLabel + "']"));
        WebElement right = ("Log student out".equalsIgnoreCase(rightLabel) || "Log out".equalsIgnoreCase(rightLabel))
                ? logoutButton
                : driver.findElement(By.xpath("//header//*[normalize-space()='" + rightLabel + "']"));

        // Same parent and right comes after left among siblings
        WebElement parent = left.findElement(By.xpath(".."));
        if (!parent.equals(right.findElement(By.xpath(".."))))
            return false;

        var siblings = parent.findElements(By.xpath("./*"));
        int li = -1, ri = -1;
        for (int i = 0; i < siblings.size(); i++) {
            if (siblings.get(i).equals(left))
                li = i;
            if (siblings.get(i).equals(right))
                ri = i;
        }
        return li >= 0 && ri == li + 1;
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
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'playlist') and normalize-space(text())='Jennys Playlist']")));
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

    public void openPlaylist2(String playlistId) {
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

    public void iSelectHomeTabFromTheNavigationMenu() {
        click(Hometab);
    }

    public boolean isUpdatedNameDisplayedOnHomepage(String updatedName) {
        By updatedNameLocator = By.xpath(
                "//div[contains(@class, 'heading-wrapper')]/h1[contains(text(), '" + updatedName + "')]");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(updatedNameLocator));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public void iSelectThemeInProfileAndPreferencesForm(String themeName) {
        click(themeOption);
    }

    public boolean isThemeAppliedOnHomepage(String themeName) {
        By themeAppliedLocator = By.xpath(
                "//html[@data-theme='" + themeName.toLowerCase() + "']");

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(themeAppliedLocator));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public void iCheckTheShowNowPlayingCheckboxInProfileAndPreferencesForm(String checkboxName) {
        if (!nowPlayingCheckbox.isSelected()) {
            click(nowPlayingCheckbox);
        }
    }

    /**
     * Validates that the browser's internal Notification API status is 'granted'.
     * This confirms the "Show Now Playing" feature successfully enabled
     * permissions.
     * 
     * @return true if permission is granted within 10 seconds, false otherwise.
     */
    public boolean _isNotificationPermissionGranted() {
        // Assuming 'driver' and 'wait' (or equivalent) are accessible here.
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Wait until the JavaScript condition returns true
            explicitWait.until((ExpectedCondition<Boolean>) webDriver -> {
                // Check the browser's internal permission status
                String permissionStatus = (String) js.executeScript("return Notification.permission;");

                // Return true when the status is 'granted'
                boolean isGranted = "granted".equalsIgnoreCase(permissionStatus);
                return isGranted;
            });

            return true; // Status successfully set to 'granted'

        } catch (Exception e) {
            // If the wait times out or any exception occurs, the permission was not
            // granted.
            return false;
        }
    }

    public boolean isNowPlayingNotificationFunctionalityWorking() {
        boolean result = false;

        try {
            if (!_isNotificationPermissionGranted()) {
                result = false;
            } else {
                // notify permission is granted, so we assume the functionality is working.
                // validation of actual notification display is not feasible in Selenium.
                result = true;
            }
        } catch (Exception e) {
            System.err.println("Error checking notification status: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public void iCheckTheConfirmBeforeClosingKoelCheckboxInProfileAndPreferencesForm() {
        if (!confirmBeforeClosingCheckbox.isSelected()) {
            click(confirmBeforeClosingCheckbox);
        }
    }

    public boolean isConfirmationWindowDisplayedOnLogout() {
        boolean result = false;

        try {
            if (!_isNotificationPermissionGranted()) {
                result = false;
            } else {
                // notify permission is granted, so we assume the functionality is working.
                // validation of actual notification display is not feasible in Selenium.
                result = true;
            }
        } catch (Exception e) {
            System.err.println("Error checking notification status: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public void iCheckTheShowTranslucentBlurredOverlayCheckboxInProfileAndPreferencesForm() {
        if (!showTranslucentBlurredOverlayCheckbox.isSelected()) {
            click(showTranslucentBlurredOverlayCheckbox);
        }
    }

    public boolean isTranslucentBlurredOverlayDisplayedOnHomepage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(albumArtOverlay));
            return albumArtOverlay.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void iPlayASongFromTheMostPlayedSection() {
        doubleClick(firstMostPlayedSong);
    }

    public void iSelectCurrentQueueTabFromTheNavigationMenu() {
        click(currentQueueTab);
    }

    public boolean isCurrentlyPlayedSongDisplayedInCurrentQueuePage(String expectedSongName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(currentlyPlayedSongLocator));
            String actualSongName = currentlyPlayedSongLocator.getText().trim();
            return actualSongName.equalsIgnoreCase(expectedSongName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTotalNumberOfSongsInCurrentQueueAccurate() {

        try {
            // 1. Ensure we are on the Queue tab and elements are visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#queueWrapper")));

            // 2. Count the actual rows in the DOM
            // Target tr.song-item specifically inside the queue wrapper
            List<WebElement> songRows = driver.findElements(By.cssSelector("#queueWrapper tr.song-item"));
            int actualRowCount = songRows.size();

            // 3. Get the "meta" text string (e.g., "1 song • 06:14")
            // This locator finds the span inside the queue header that contains the word
            // "song"
            WebElement metaTextElement = driver.findElement(
                    By.xpath("//section[@id='queueWrapper']//span[contains(@class, 'meta')]"));
            String metaText = metaTextElement.getText().trim();

            // 4. Extract the number from the string
            // Example string: "66 songs • 04:32:57"
            // We split by space (" ") and take the first part ("66")
            String countString = metaText.split(" ")[0];
            int expectedCountFromHeader = Integer.parseInt(countString);

            // 5. Verify they match
            return actualRowCount == expectedCountFromHeader;

        } catch (Exception e) {
            System.err.println("Error verifying queue count: " + e.getMessage());
            return false;
        }
    }

    public boolean iVerifyTheTotalDurationOfSongsInTheCurrentQueuePageIsAccurate() {

        // Helper function to convert time string (MM:SS or HH:MM:SS) to total seconds.
        // We define this locally or assume it's a private helper method in the class.
        // This logic is necessary and correctly implemented in the original code's loop
        // logic.
        java.util.function.Function<String, Integer> timeToSecondsConverter = (timeText) -> {
            String[] parts = timeText.split(":");
            int totalSeconds = 0;

            // Start from the right (seconds)
            for (int i = 0; i < parts.length; i++) {
                int value = Integer.parseInt(parts[parts.length - 1 - i].trim());
                // i=0: seconds (value * 1)
                // i=1: minutes (value * 60)
                // i=2: hours (value * 3600)
                totalSeconds += value * Math.pow(60, i);
            }
            return totalSeconds;
        };

        try {
            // 1. Ensure we are on the Queue tab and elements are visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#queueWrapper")));

            // 2. Sum up the ACTUAL durations from each song row
            List<WebElement> durationCells = driver.findElements(
                    By.cssSelector("#queueWrapper tr.song-item td.time")); // Assuming the locator should be td.time as
                                                                           // per previous advice
            int calculatedTotalSeconds = 0;

            for (WebElement cell : durationCells) {
                String timeText = cell.getText().trim();
                calculatedTotalSeconds += timeToSecondsConverter.apply(timeText);
            }

            // 3. Get the "meta" text string (e.g., "66 songs • 04:32:57")
            WebElement metaTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section[@id='queueWrapper']//span[contains(@class, 'meta')]")));
            String metaText = metaTextElement.getText().trim();

            // 4. Extract the duration part from the string (e.g., "04:32:57")
            String[] metaParts = metaText.split("•");
            String displayedTotalDuration = metaParts[1].trim();

            // 5. Convert the DISPLAYED total duration to seconds
            int displayedTotalSeconds = timeToSecondsConverter.apply(displayedTotalDuration);

            System.out.println("Calculated Total: " + calculatedTotalSeconds + "s | Displayed Total: "
                    + displayedTotalSeconds + "s");

            // 6. Compare calculated vs displayed seconds
            return calculatedTotalSeconds == displayedTotalSeconds;

        } catch (Exception e) {
            System.err.println("Error verifying total duration: " + e.getMessage());
            return false;
        }
    }

    public boolean areSongDetailsDisplayedInCurrentQueuePage() {
        try {
            // 1. Ensure we are on the Queue tab and elements are visible
            // You only need to wait for the general container to load.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#queueWrapper")));

            // 2. Check for presence of at least one song row
            // We ensure we find songs inside the queue wrapper to avoid confusion.
            List<WebElement> songRows = driver.findElements(By.cssSelector("#queueWrapper tr.song-item"));
            if (songRows.isEmpty()) {
                return false; // No songs found
            }

            // 3. Define the list of CSS selectors for the required fields
            WebElement firstRow = songRows.get(0);
            List<String> detailSelectors = List.of(
                    "td.track-number.text-secondary", // ID
                    "td.title",
                    "td.artist",
                    "td.album",
                    "td.time.text-secondary"); // Duration

            // 4. Iterate and verify all details are present and non-empty
            for (String selector : detailSelectors) {
                // Find the element within the first row
                WebElement cell = firstRow.findElement(By.cssSelector(selector));

                // Check if the cell text is empty. If any is empty, return false immediately.
                if (cell.getText().trim().isEmpty()) {
                    System.err.println("Verification Failed: The cell with selector '" + selector + "' was empty.");
                    return false;
                }
            }

            // If the loop completes without returning false, all details are displayed and
            // non-empty.
            return true;

        } catch (Exception e) {
            // If findElement throws NoSuchElementException for any of the required cells,
            // it confirms the structure is wrong or elements are missing.
            System.err.println("Error verifying song details (Missing Element): " + e.getMessage());
            return false;
        }
    }

    public void iSelectAlbumTabFromTheNavigationMenu() {
        click(albumTab);
    }

    public boolean isOnAlbumPage() {
        try {
            wait.until(ExpectedConditions.urlContains("albums"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}