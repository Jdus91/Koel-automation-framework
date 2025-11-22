package pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllSongsPage extends BasePage {

    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "tr.song-item:first-child")
    WebElement firstSong;

    @FindBy(xpath = "//*[@id=\"mainFooter\"]/div[1]")
    WebElement mediaPlayer;

    @FindBy(xpath = "//span[@data-testid='play-btn']")
    WebElement playButton;

    @FindBy(xpath = "//div[@data-testid='sound-bar-play']")
    WebElement soundBarPlaying;

    @FindBy(xpath = "//p[@class='meta']/a[@class='album']")
    WebElement albumName;

    @FindBy(xpath = "//h3[@class='title']")
    WebElement coverName;

    @FindBy(xpath = "//div[@id='progressPane']//div[@class='lyrics-text-container']")
    WebElement lyricsText;

    @FindBy(xpath = "//p[@class='meta']/a[@class='artist']")
    WebElement artistName;

    @FindBy(xpath = "//button[@data-testid='toggle-extra-panel-btn']")
    WebElement infoPanelButton;

    @FindBy(xpath = "//button[@id='extraTabAlbum']")
    WebElement albumTab;

    @FindBy(xpath = "//button[@id=\"extraTabArtist\"]")
    WebElement artistTab;

    @FindBy(css = "#extraPanelAlbum > article > h1 > button")
    WebElement shuffleButtonAlbumTab;

    @FindBy(css = "#extraPanelArtist > article > h1 > button")
    WebElement shuffleButtonArtistTab;

    /*
     * Implementing method to right click on first song
     * returns void
     */
    public void contextClickFirstSong() {
        contextClick(firstSong);
    }

    /*
     * Implementing method to hover on Media Player
     * returns void
     */
    public void hoverMediaPlayer() {
        hover(mediaPlayer);
    }

    /*
     * Implementing method to click Play button
     * returns void
     */
    public void clickPlay() {
        click(playButton);
    }

    /*
     * Implementing method to verify if song is playing
     * returns boolean
     */
    public boolean isSongPlaying() {
        return findElement(soundBarPlaying).isDisplayed();
    }

    /*
     * Implementing method to verify if Album name is displayed
     * returns boolean
     */
    public boolean isAlbumNameDisplayed() { // to be implemented first
        return findElement(albumName).isDisplayed();
    }
    /*
     * Implementing method to verify if Cover name is displayed
     * returns boolean
     */
    public boolean isCoverNameDisplayed() {
        return findElement(coverName).isDisplayed();
    }
    /*
     * Implementing method to verify if Lyrics are inside progressPane
     * returns boolean
     */
    public boolean isLyricsInProgressPane() {
        // Hypothetical XPath for a lyrics element inside the progressPane
        String wrongLocationXPath = "//div[@id='progressPane']//div[@class='lyrics-text-container']";

        try {
            return driver.findElements(By.xpath(wrongLocationXPath)).size() > 0;
        } catch (Exception e) {
            return false;
        }

    }
    /*
     * Implementing method to verify if Artist name is displayed
     * returns boolean
     */
    public boolean isArtistNameDisplayed() {
        return findElement(artistName).isDisplayed();
    }
    /*
     * Implementing method to open Info Panel
     * returns void
     */
    public void iOpenInfoPanel() {
        click(infoPanelButton);
    }
    /*
     * Implementing method to verify if Info Panel is opened
     * returns boolean
     */
    public boolean isInfoPanelOpened() {
        return findElement(infoPanelButton).isDisplayed();
    }
    /*
     * Implementing method to close Info Panel
     * returns void
     */
    public void closeInfoPanel() {
        click(infoPanelButton);
    }
    /*
     * Implementing method to verify if Info Panel is closed
     * returns boolean
     */
    public boolean isInfoPanelClosed() {
        try {
            // If the element is found AND displayed, the panel IS open, so return FALSE.
            return infoPanelButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If NoSuchElementException is thrown, the element is NOT present.
            // This means the panel is NOT open, so return TRUE.
            return true;
        }
    }
    /*
     * Implementing method to select Album tab
     * returns void
     */
    public void selectAlbumTab() {
        click(infoPanelButton);
        click(albumTab);
    }
    /*
     * Implementing method to click Shuffle button from Album tab and confirm
     * returns boolean
     */
    public boolean clickShuffleButtonFromAlbumTabAndConfirm() {
        Boolean buttonClicked = false;
        try {
            click(shuffleButtonAlbumTab);
            buttonClicked = true;
        } catch (Exception e) {
            buttonClicked = false;
        }

        return buttonClicked;
    }
    /*
     * Implementing method to select Artist tab
     * returns void
     */
    public void selectArtistTab() {
        click(artistTab);
    }
    /*
     * Implementing method to click Shuffle button from Artist tab and confirm
     * returns boolean
     */
    public boolean clickShuffleButtonFromArtistTabAndConfirm() {
        Boolean buttonClicked = false;
        try {
            click(shuffleButtonArtistTab);
            buttonClicked = true;
        } catch (Exception e) {
            buttonClicked = false;
        }

        // Set the flag to true when shuffle button is clicked
         return buttonClicked;
    }
}
