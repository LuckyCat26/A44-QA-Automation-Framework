package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "[class='playlist playlist']")
            private WebElement playlist;
    //By myPlaylist = By.cssSelector("[class='playlist playlist']");
    @FindBy(css = "[title='Delete this playlist']")
            private WebElement deletePlaylistBtn;
    //By deleteBtn = By.cssSelector("[title='Delete this playlist']");
    @FindBy(css = ".ok")
            private WebElement okBtn;
    //By btnOk = By.cssSelector(".ok");
    By btnPlus = By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    By newPlaylistCreate = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");
    @FindBy(css = ".create input")
            private WebElement inputPlaylistName;
    //By playlistNameField = By.cssSelector(".create input");
    @FindBy(css = "#playlistWrapper h1")
            private WebElement playlistHeader;
    //By assertPlaylistNameIsInHeader = By.cssSelector("#playlistWrapper h1");
    @FindBy(css = ".btn-delete-playlist")
            private WebElement deletePlaylistButton;
    //By playlistDeleteBtn = By.cssSelector(".btn-delete-playlist");

    @FindBy(css = ".playlist .active")
            private WebElement activePlaylist;
    //By playlistActive = By.cssSelector(".playlist .active");
    @FindBy(css = ".playlist .menu li ")
            private WebElement editBtn;
    //By playlistMenuEdit = By.cssSelector(".playlist .menu li ");
    @FindBy(css = "input[name='name']")
            private WebElement playlistNameField;
    //By playlistNameEditField = By.cssSelector("input[name='name']");
    private By playlistList = By.cssSelector(".playlist.playlist>a");
    @FindBy(css = "#mainWrapper #playlistWrapper")
            private WebElement createdPlaylist;
    //By newlyCreatedPlaylist = By.cssSelector("#mainWrapper #playlistWrapper");

    public void clickPlaylist() {
        //WebElement playlist = driver.findElement(myPlaylist);
        playlist.click();
    }

    public void deleteSelectedPlaylist() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement deletePlaylistBtn = driver.findElement(deleteBtn);
        js.executeScript("arguments[0].click();", deletePlaylistBtn);
    }

    public void clickOkBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement okBtn = driver.findElement(btnOk);
        js.executeScript("arguments[0].click();", okBtn);
    }

    public void clickPlusBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement plusBtn = wait.until(ExpectedConditions.elementToBeClickable(btnPlus));
        js.executeScript("arguments[0].click();", plusBtn);
    }

    public void clickNewPlaylist() {
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(newPlaylistCreate)).click();
        //js.executeScript("arguments[0].click();", newPlaylistCreate);
    }

    public void addPlaylistName(String newPlaylist) {
        //WebElement inputPlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated(playlistNameField));
        inputPlaylistName.click();
        inputPlaylistName.clear();
        inputPlaylistName.sendKeys(newPlaylist);
    }

    public void assertPlaylistNameInHeader(String newPlaylist) {
        //WebElement playlistHeader = driver.findElement(assertPlaylistNameIsInHeader);
        wait.until(ExpectedConditions
                .textToBePresentInElement(playlistHeader, newPlaylist));
    }

    public void clickDeletePlaylistBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement deletePlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(playlistDeleteBtn));
        js.executeScript("arguments[0].click();", deletePlaylistButton);
    }

    public void assertPlaylistDeleted(String newPlaylist) {
        List<WebElement> playlists = driver.findElements(playlistList);
        List<String> playlistNames = new ArrayList<>();

        for (int i = 0; i < playlists.size(); i++) {
            String playlistName = playlists.get(i).getText();
            playlistNames.add(playlistName);
        }
        System.out.println("The names of available playlists are " + playlistNames);
        Assert.assertFalse(playlistNames.contains(newPlaylist));
    }

    public void clickEnter() {
        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    }

    public void rightClickOnPlaylist() {
        //WebElement activePlaylist = wait.until(ExpectedConditions.elementToBeClickable(playlistActive));
        new Actions(driver)
                .contextClick(activePlaylist)
                .perform();
    }

    public void clickEdit() {
        //WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(playlistMenuEdit));
        editBtn.click();
    }

    public void renamePlaylistName(String newPlaylist) {
        //WebElement inputPlaylistName = wait.until(visibilityOfElementLocated(playlistNameEditField));
        playlistNameField.click();
        playlistNameField.sendKeys((Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE)));
        playlistNameField.sendKeys(newPlaylist);
        playlistNameField.sendKeys(Keys.ENTER);
    }

    public void assertRenamedPlaylistPresent(String newPlaylist) {
        List<WebElement> playlistNames = driver.findElements(playlistList);
        List<String> listOfNames = new ArrayList<>();

        for (int i = 0; i < playlistNames.size(); i++) {
            String playlistName = playlistNames.get(i).getText();
            if (playlistName.equals(newPlaylist)) {
                listOfNames.add(playlistName);
            }
        }
        System.out.println(listOfNames);
        Assert.assertTrue(listOfNames.contains(newPlaylist));
    }
    public String generateRandomName(){
        Faker faker = new Faker(new Locale("en-US"));
        String newName = faker.name().firstName();
        return newName;
    }
    public void assertSongAddedToPlaylist() {
        //WebElement createdPlaylist = wait.until(visibilityOfElementLocated(newlyCreatedPlaylist));
        wait.until(ExpectedConditions
                .textToBePresentInElement(createdPlaylist, "Pluto"));
        }
}