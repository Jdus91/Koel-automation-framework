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
}
