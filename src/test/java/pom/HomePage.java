package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By userAvatarIcon = By.cssSelector("img.avatar");
    By searchField = By.cssSelector("input[type='search']");
    By viewAllButton = By.cssSelector("a[href='#!/songs']");
    By addToButton = By.cssSelector("button[data-test='add-to-btn']");
    By notificationSuccess = By.cssSelector("div.success.show");
    By existingPlaylist = By.xpath("//a[text()='Jennys second Playlist']");
    By deleteButton = By.xpath("//button[contains(@class, 'btn-delete-playlist')]");

    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }

    public void searchSong(String songName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        searchInput.clear();
        searchInput.sendKeys(songName);
    }

    public void clickViewAll() {
        WebElement viewAll = wait.until(ExpectedConditions.elementToBeClickable(viewAllButton));
        viewAll.click();
    }

    public void selectSongByTitle(String songTitle) {
        WebElement song = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='title' and text()='" + songTitle + "']")));
        song.click();
    }

    public void clickAddToButton() {
        WebElement addTo = wait.until(ExpectedConditions.elementToBeClickable(addToButton));
        addTo.click();
    }

    public void choosePlaylist(String playlistName) {
        WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + playlistName + "')]")));
        playlist.click();
    }

    public void chooseAllSongsList() {
        click(viewAllButton); // reuse viewAllButton locator
    }

    public String getAddToPlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationSuccess));
        return notification.getText();

    }

    public By playlistLocatorById() {
        return By.xpath("//a[contains(@href, '/playlist/105750')]");
    }

    public void openPlaylist(String playlistName) {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/playlist/105750')]")));
        doubleClick(playlistLocatorById());
    }

    public void renamePlaylist(String newName) {
        enterNewPlaylistName(newName);
    }

    public String getRenameMessage() {
        return getRenamePlayListSuccessMsg();
    }

    public void chooseExistingPlaylist() {
        click(existingPlaylist);
    }

    public void selectDeleteBtn() {
        click(deleteButton);
    }
}