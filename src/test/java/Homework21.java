import Pages.LoginPage;
import Pages.PlaylistPage;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{
    @Test
    public void renamePlaylist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        PlaylistPage playlistPage = new PlaylistPage(driver);
        String newPlaylist = playlistPage.generateRandomName();

        loginPage.login("stella_26021987@mail.ru", "te$t$tudent");
        playlistPage.clickPlusBtn();
        playlistPage.clickNewPlaylist();
        playlistPage.addPlaylistName(newPlaylist);
        playlistPage.clickEnter();
        playlistPage.assertPlaylistNameInHeader(newPlaylist);
        assertSuccessBanner();

        playlistPage.rightClickOnPlaylist();
        playlistPage.clickEdit();
        playlistPage.renamePlaylistName(newPlaylist);
        assertSuccessBanner();
        driver.navigate().refresh();
        Thread.sleep(4000);
        playlistPage.assertRenamedPlaylistPresent(newPlaylist);
    }

    @Test

    public void renamePlaylist1() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("stella_26021987@mail.ru", "te$t$tudent");
        assertRenamedPlaylistPresent();
    }
}