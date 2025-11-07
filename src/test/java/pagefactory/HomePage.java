package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;

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
        //public void choosePlaylist(String playlistName) {
        //WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + playlistName + "')]")));
        //click(playlist);
    }

    public void chooseAllSongsList() {
        click(viewAllButton);
    }

    public String getAddToPlaylistSuccessMsg() {
        //WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.success.show")));
        //return notificationSuccess.getText().trim();
        return findElement(notificationSuccess).getText();
    }

    public String getDeletePlaylistSuccessMsg() {
        //WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.success.show")));
        //return notificationSuccess.getText().trim();
        return findElement(notificationSuccess).getText();
    }

    public void openPlaylist(String playlistId) {
        doubleClick(currentPlaylist);


        //public void openPlaylist(String playlistName) {
        // WebElement playlistToOpen = wait.until(ExpectedConditions.elementToBeClickable(
        // By.xpath("//a[contains(.,'" + playlistName + "')]")));
        //WebElement playlistToOpen = wait.until(ExpectedConditions.elementToBeClickable(
        //By.xpath("//a[text()='" + playlistName + "']")));
        //doubleClick(playlistToOpen);
    }
        public void openPlaylist2 (String playlistId){
            doubleClick(currentPlaylist2);

//public void openPlaylist2(String playlistName) {
        //WebElement playlistToOpen = wait.until(ExpectedConditions.elementToBeClickable(
        //By.xpath("//a[contains(.,'" + playlistName + "')]")));
        //WebElement playlistToOpen = wait.until(ExpectedConditions.elementToBeClickable(
        //By.xpath("//a[text()='" + playlistName + "']")));
        //doubleClick(playlistToOpen);


        //public void openPlaylist2(String playlistName) {
        // doubleClick(currentPlaylist2);
        //}
    }

    public void renamePlaylist(String newPlaylistName) {
        //WebElement inputField = wait.until(ExpectedConditions.visibilityOf(playlistInputField));
        //inputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        //inputField.sendKeys(newPlaylistName);
        //inputField.sendKeys(Keys.ENTER);
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
