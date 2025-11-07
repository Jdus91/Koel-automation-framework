package pom;

import org.openqa.selenium.*;

public class AllSongsPage extends BasePage {

    By firstSong = By.cssSelector("tr.song-item:first-child");
    By mediaPlayer = By.xpath("//*[@id=\"mainFooter\"]/div[1]");
    By playButton = By.xpath("//span[@data-testid='play-btn']");
    By isSongPlaying = By.xpath("//div[@data-testid='sound-bar-play']");

    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    public void contextClickFirstSong() {
        click(firstSong);
    }

    public void hoverMediaPlayer() {
        hover(mediaPlayer);
    }

    public void clickPlay() {
        click(playButton);
    }

    public boolean isSongPlaying() {
        WebElement soundBar = findElement(isSongPlaying);
        return soundBar.isDisplayed();
    }
}
