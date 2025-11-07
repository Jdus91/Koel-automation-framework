import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;

public class PlaylistTests extends BaseTest {
    @Test
    public void addSongToPlaylist() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        homePage.searchSong("Dee Yan-Key - rainday");
        homePage.clickViewAll();
        homePage.selectSongByTitle("Dee Yan-Key - rainday");
        homePage.clickAddToButton();
        homePage.choosePlaylist("Jennys Playlist");

        String expectedMessage = "Added 1 song into \"Jennys Playlist.\"";

        Assert.assertEquals(homePage.getAddToPlaylistSuccessMsg(), expectedMessage);
    }

    @Test
    public void deletePlaylist() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        homePage.chooseExistingPlaylist();

        homePage.selectDeleteBtn();

        String ExpectedString = "Deleted playlist \"Jennys second Playlist.\"";

        Assert.assertEquals(homePage.getDeletePlaylistSuccessMsg(), ExpectedString);
    }

    @Test
    public void deletePlaylist2() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        homePage.chooseExistingPlaylist2();

        homePage.selectDeleteBtn();

        String ExpectedString = "Deleted playlist \"Jennys third Playlist.\"";

        Assert.assertEquals(homePage.getDeletePlaylistSuccessMsg(), ExpectedString);
    }

    @Test
    public void renamePlaylist() {

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        String playlistId = "Jennys Playlist";
        String newPlaylistName = "Edited Jennys Playlist";

        homePage.openPlaylist(playlistId);
        homePage.renamePlaylist(newPlaylistName);

        String updatedPlaylistMsg = "Updated playlist \"Edited Jennys Playlist.\"";

        Assert.assertEquals(homePage.getRenameMessage(), updatedPlaylistMsg);
    }

    @Test
    public void renamePlaylist2(){

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("jennifer.de.jesus@testpro.io").providePassword("FCVlLOni").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

        String playlistId = "Jennys fourth Playlist";
        String newPlaylistName = "Edited Jennys fourth Playlist";

        homePage.openPlaylist2(playlistId);
        homePage.renamePlaylist(newPlaylistName);

        String updatedPlaylistMsg = "Updated playlist \"Edited Jennys fourth Playlist.\"";
        Assert.assertEquals(homePage.getRenameMessage(), updatedPlaylistMsg);
    }
}