package pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public void contextClickFirstSong() {
        contextClick(firstSong);
    }

    public void hoverMediaPlayer() {
        hover(mediaPlayer);
    }

    public void clickPlay() {
        click(playButton);
    }

    public boolean isSongPlaying() {
        return findElement(soundBarPlaying).isDisplayed();
    }

    public boolean isAlbumNameDisplayed() {
        boolean result = false;
        return result;
    }

    public boolean isCoverNameDisplayed() {
        boolean result = false;
        return result;
    }

    public boolean isLyricsDisplayed() {
        boolean result = false;
        return result;
    }

     public boolean isArtistNameDisplayed() {
        boolean result = false;
        return result;
    }

    public void openInfoPanel() {
        // Implementation to open Info Panel
    }

    public boolean isInfoPanelOpened() {
        boolean result = false;
        return result;
    }

    public void closeInfoPanel() {
        // Implementation to close Info Panel
    }

    public boolean isInfoPanelClosed() {
        boolean result = false;
        return result;
    }

    public void selectAlbumTab() {
        // Implementation to select Album tab in Info Panel
    }

    public void clickShuffleButton() {
        // Implementation to click Shuffle button from Album tab
    }

    public boolean areSongsShuffledByAlbum() {
        boolean result = false;
        return result;
    }

    public void selectArtistTab() {
        // Implementation to select Artist tab in Info Panel
    }

    public void clickShuffleButtonFromArtistTab() {
        // Implementation to click Shuffle button from Artist tab
    }

    public boolean areSongsShuffledByArtist() {
        boolean result = false;
        return result;
    }    
}
