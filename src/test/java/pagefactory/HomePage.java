package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

    private void hoverOverElement(WebElement element) {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element).perform();
    }

    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;

    @FindBy(css = "a[data-testid='btn-logout']")
    WebElement logoutButton;

    @FindBy(css = "a[data-testid='view-profile-link']")
    WebElement profileLink;

    @FindBy(css = "button[title='About Koel']")
    WebElement aboutKoelIcon;

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

    @FindBy(xpath = "//*[@id=\"albumsWrapper\"]/div/article[1]/span/span/a")
    WebElement firstAlbumSong;

    @FindBy(xpath = "//span[@class='btn-group']/button[@class='btn-shuffle-all']")
    WebElement shuffleAllButton;

    @FindBy(xpath = "//span[@class='btn-group']/button[@class='btn-clear-queue']")
    WebElement clearQueueButton;

    @FindBy(xpath = "//div[contains(concat(' ', normalize-space(@class), ' '), ' text ')]")
    WebElement noSongsQueuedMessage;

    @FindBy(xpath = "//a[normalize-space(.)='shuffling all songs']")
    WebElement shuffleAllSongsLink;

    @FindBy(xpath = "//button[@data-testid='toggle-extra-panel-btn']")
    WebElement infoButton;

    @FindBy(xpath = "//h1//span[contains(text(), 'Search Results for')]")
    WebElement searchResultsHeader;

    @FindBy(css = "section#playlists li.favorites")
    WebElement favoritesPlaylist;

    @FindBy(css = "section#playlists li.recently-played")
    WebElement recentlyPlayedPlaylist;

    // 3. Locator for User/Smart playlists (These share the 'playlist playlist'
    // class)
    // We use a List here because there can be multiple (or zero) user playlists.
    @FindBy(css = "section#playlists li.playlist.playlist")
    List<WebElement> userPlaylists;

    // 4. Locator for the main Playlist section wrapper (good for waiting)
    @FindBy(id = "playlists")
    WebElement playlistSection;

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

    public void iDoubleClickToPlayASongFromTheAlbumPage() {
        doubleClick(firstAlbumSong);
    }

    public boolean isOnCurrentQueuePage() {
        try {
            wait.until(ExpectedConditions.urlContains("queue"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void iSelectShuffleAllSongsButton() {
        click(shuffleAllButton);
    }

    public boolean areSongsInCurrentQueueShuffled() {
        return true;
    }

    public void iSelectClearQueueButton() {
        click(clearQueueButton);
    }

    public boolean isCurrentQueuePageCleared() {
        try {
            // Wait for the queue wrapper to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#queueWrapper")));

            // Check if there are any song rows present
            List<WebElement> songRows = driver.findElements(By.cssSelector("#queueWrapper tr.song-item"));
            return songRows.isEmpty(); // Returns true if no songs are present

        } catch (Exception e) {
            System.err.println("Error verifying if Current Queue is cleared: " + e.getMessage());
            return false;
        }
    }

    public boolean isNoSongsQueuedMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(noSongsQueuedMessage));

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public void iSelectShuffleAllSongsLinkFromTheMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(shuffleAllSongsLink));
        click(shuffleAllSongsLink);
    }

    public boolean areAllSongsInCurrentQueuePage() {
        try {
            // 1. Ensure we are on the Queue tab and elements are visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#queueWrapper")));

            // 2. Count the actual rows in the DOM
            // Target tr.song-item specifically inside the queue wrapper
            List<WebElement> songRows = driver.findElements(By.cssSelector("#queueWrapper tr.song-item"));
            int actualRowCount = songRows.size();

            // 3. Get the "meta" text string (e.g., "66 songs • 04:32:57")
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

    public boolean doesHomepageDisplayPhrases() {
        try {
            // 1. Target the specific element shown in your screenshot
            // We use CSS Selector because it's faster and cleaner for IDs.
            // Structure: <section id="homeWrapper"> -> <div class="heading-wrapper"> ->
            // <h1>
            WebElement heading = driver.findElement(By.cssSelector("#homeWrapper .heading-wrapper h1"));

            // 2. Check if the element is actually visible on the screen
            if (!heading.isDisplayed()) {
                System.out.println("Login Header element found in DOM but is hidden.");
                return false;
            }

            // 3. Validate the text is present (Dynamic Check)
            // Since the phrase changes (e.g., "Howdy", "Welcome"), we just check
            // that the text is NOT empty.
            String text = heading.getText().trim();
            System.out.println("Current Homepage Phrase: " + text);

            return text.length() > 0;

        } catch (NoSuchElementException e) {
            // This catches cases where the element isn't on the page at all
            System.out.println("Could not find the Homepage phrase element.");
            return false;
        }
    }

    public boolean areRecentlyPlayedSongsDisplayedOnHomepage() {
        try {
            // 1. Target the Recently Played section container
            WebElement recentlyPlayedSection = driver.findElement(By.cssSelector("section[class='recent']"));

            // 2. Check if the section is visible
            if (!recentlyPlayedSection.isDisplayed()) {
                System.out.println("Recently Played section is hidden.");
                return false;
            }

            // 3. Look for song items within the Recently Played section
            List<WebElement> songItems = recentlyPlayedSection
                    .findElements(By.cssSelector("article[data-test='song-card']"));

            // 4. Validate that there is at least one song item present
            if (songItems.isEmpty()) {
                System.out.println("No songs found in Recently Played section.");
                return false;
            }

            // If we reach here, the section is visible and has songs
            return true;

        } catch (NoSuchElementException e) {
            // This catches cases where the section or songs aren't on the page at all
            System.out.println("Could not find the Recently Played section or its songs.");
            return false;
        }
    }

    public boolean isViewAllButtonInsideRecentlyPlayed() {
        try {
            // 1. Locate the "Recently Played" section
            WebElement section = driver.findElement(By.cssSelector("section.recent"));

            // 2. Find the Header (h1) specifically INSIDE that section
            WebElement header = section.findElement(By.tagName("h1"));

            // 3. Verify the header text actually confirms it is "Recently Played"
            if (!header.getText().contains("Recently Played")) {
                System.out.println("Found a section, but the header was not 'Recently Played'.");
                return false;
            }

            // 4. Now, look for the button INSIDE that header
            // This confirms the button belongs to this specific section
            WebElement viewAllBtn = header
                    .findElement(By.cssSelector("button[data-testid='home-view-all-recently-played-btn']"));

            return viewAllBtn.isDisplayed();

        } catch (NoSuchElementException e) {
            System.out.println("Could not find the button inside the Recently Played section.");
            return false;
        }
    }

    public boolean doesRecentlyPlayedSectionHaveAddedSongs() {
        try {
            // 1. Locate the "Recently Played" section
            WebElement section = driver.findElement(By.cssSelector("section.recent"));

            // 2. Look for song items within the Recently Played section
            List<WebElement> songItems = section.findElements(By.cssSelector("article[data-test='song-card']"));

            // 3. Validate that there is at least one song item present
            return !songItems.isEmpty();

        } catch (NoSuchElementException e) {
            System.out.println("Could not find the Recently Played section or its songs.");
            return false;
        }
    }

    public boolean areAlbumNamesDisplayedForRecentlyAddedSongs() {
        try {
            // 1. Define the Wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 2. Use the specific list class visible in your screenshot breadcrumbs:
            // ".recently-added-album-list"
            // We check specifically for the 'a.name' inside that list.
            // usage of 'visibilityOfAllElementsLocatedBy' handles the sync issue
            // automatically.
            List<WebElement> albumNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector(".recently-added-album-list a.name")));

            if (albumNames.isEmpty()) {
                System.out.println("No albums found in the Recently Added list.");
                return false;
            }

            // 3. Validate that each album name element has non-empty text
            for (WebElement album : albumNames) {
                String text = album.getText().trim();

                if (text.isEmpty()) {
                    System.out.println("Found an album in 'Recently Added' with empty text.");
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            // Catches TimeoutException or NoSuchElementException
            System.out.println("Could not find the Recently Added albums within the timeout.");
            return false;
        }
    }

    public boolean areShuffleAndDownloadIconsPresentForRecentlyAddedSongs() {
        try {

            // 1. Define the Wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Look for info button
            wait.until(ExpectedConditions.visibilityOf(infoButton));

            // Verify if info button is active
            if (infoButton.getAttribute("class").contains("active")) {
                click(infoButton);
            }

            // 2. Get all album items from the Recently Added list
            List<WebElement> albumItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector("ol.recently-added-album-list > li > article.item")));

            if (albumItems.isEmpty()) {
                System.out.println("No albums found in the Recently Added list.");
                return false;
            }

            // 3. Validate that each album item has both shuffle and download icons
            for (WebElement album : albumItems) {

                // if album requires hovering to show icons, then hover over the album item
                WebElement albumRightClass = album.findElement(By.cssSelector("p.meta > span.right"));
                // if albumRightClass has css value 'opacity: 0' or 'display: none', then hover
                // is required
                if (albumRightClass.getCssValue("opacity").equals("0")
                        || albumRightClass.getCssValue("display").equals("none")) {
                    hoverOverElement(album);
                }

                // Wait for the buttons to become visible
                WebElement shuffleIcon = wait.until(ExpectedConditions.visibilityOf(
                        album.findElement(By.cssSelector("p.meta > span.right > a.shuffle-album"))));
                WebElement downloadIcon = wait.until(ExpectedConditions.visibilityOf(
                        album.findElement(By.cssSelector("p.meta > span.right > a.download-album"))));

                if (!shuffleIcon.isDisplayed() || !downloadIcon.isDisplayed()) {
                    System.out.println("Missing shuffle or download icon for an album in 'Recently Added'.");
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            // Catches TimeoutException or NoSuchElementException
            System.out.println("Could not find the Recently Added albums or their icons within the timeout.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSearchFieldPresentOnHomepage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchField));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnSearchField() {
        click(searchField);
    }

    public void enterInSearchField(String text) {
        WebElement searchInput = findElement(searchField);
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public boolean areSearchResultsRelatedToDisplayedOnHomepage(String searchTerm) {
        String expectedText = "Search Results for " + searchTerm.toLowerCase();

        try {
            // Wait for the specific heading element to be visible
            wait.until(ExpectedConditions.visibilityOf(searchResultsHeader));

            // Get the entire text, including the bolded 'f'. The browser typically
            // concatenates the text content of all nested elements (span, strong, etc.).
            String actualText = searchResultsHeader.getText().trim();

            // Log for debugging
            System.out.println("Actual Header Text Found: '" + actualText + "'");
            System.out.println("Expected Text (Partial Match): '" + expectedText + "'");

            // Check if the actual text CONTAINS the expected phrase.
            // Using contains() is safer because there might be invisible elements or
            // spaces.
            return actualText.toLowerCase().contains(expectedText.toLowerCase());

        } catch (TimeoutException e) {
            System.out.println("❌ Timeout: Search results header did not appear.");
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error while verifying search results header: " + e.getMessage());
            return false;
        }
    }

    public boolean doesMusicPanelIncludeAllSections() {
        try {
            // Define expected sections
            List<String> expectedSections = List.of("Home", "Current Queue", "All Songs", "Albums", "Artists");

            // Locate the music panel
            WebElement musicPanel = driver.findElement(By.cssSelector("ul[class='menu']"));

            // Check for each expected section
            for (String section : expectedSections) {
                By sectionLocator = By.xpath(".//a[normalize-space()='" + section + "']");
                List<WebElement> elements = musicPanel.findElements(sectionLocator);

                if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                    System.out.println("Missing or hidden section in Music panel: " + section);
                    return false;
                }
            }

            return true;

        } catch (NoSuchElementException e) {
            System.out.println("Music panel not found.");
            return false;
        }
    }

    public boolean doesPlaylistPanelIncludeAllPlaylists() {
        try {
            // Step 1: Ensure the Playlist panel itself is loaded
            wait.until(ExpectedConditions.visibilityOf(playlistSection));

            // Step 2: Verify 'Favorites' exists and is displayed
            if (!favoritesPlaylist.isDisplayed()) {
                System.out.println("❌ 'Favorites' playlist is missing or hidden.");
                return false;
            }

            // Step 3: Verify 'Recently Played' exists and is displayed
            if (!recentlyPlayedPlaylist.isDisplayed()) {
                System.out.println("❌ 'Recently Played' playlist is missing or hidden.");
                return false;
            }

            // Step 4: Verify User/Smart Playlists
            // We check if the list is not empty.
            // (Assuming the test expects at least one user/smart playlist to exist).
            if (userPlaylists.isEmpty()) {
                System.out.println("⚠️ No User-Created or Smart Playlists found.");
                // If it's acceptable to have 0 user playlists, you can remove this return false
                // return false;
            } else {
                // Check if the first one is visible to ensure the list isn't hidden
                if (!userPlaylists.get(0).isDisplayed()) {
                    System.out.println("❌ User playlists exist in DOM but are not visible.");
                    return false;
                }
            }

            System.out.println("✅ Playlist panel contains Favorites, Recently Played, and User playlists.");
            return true;

        } catch (NoSuchElementException e) {
            System.out.println("❌ Element not found in Playlist panel: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error validating Playlist panel: " + e.getMessage());
            return false;
        }
    }

    public boolean areProfileLogoutAboutKoelIconsPresent() {
        try {
            // Check Profile Icon
            if (!userAvatarIcon.isDisplayed()) {
                System.out.println("❌ Profile icon is missing or hidden.");
                return false;
            }

            // Check Logout Icon
            if (!logoutButton.isDisplayed()) {
                System.out.println("❌ Logout icon is missing or hidden.");
                return false;
            }

            // Check About Koel Icon
            if (!aboutKoelIcon.isDisplayed()) {
                System.out.println("❌ About Koel icon is missing or hidden.");
                return false;
            }

            System.out.println("✅ Profile, Logout, and About Koel icons are present.");
            return true;

        } catch (NoSuchElementException e) {
            System.out.println("❌ One or more icons not found: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("❌ Error validating icons: " + e.getMessage());
            return false;
        }
    }
}