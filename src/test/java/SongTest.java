import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.AllSongsPage;
import pagefactory.HomePage;
import pagefactory.LoginPage;

public class SongTest extends BaseTest {

    @Test
    public void playSong() {

        //LoginPage loginPage = new LoginPage(driver);
        //HomePage homePage = new HomePage(driver);
        //AllSongsPage allSongs = new AllSongsPage(driver);
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        AllSongsPage allSongs = new AllSongsPage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        homePage.chooseAllSongsList();

        allSongs.contextClickFirstSong();

        allSongs.hoverMediaPlayer();

        allSongs.clickPlay();

        Assert.assertTrue(allSongs.isSongPlaying());
    }
}