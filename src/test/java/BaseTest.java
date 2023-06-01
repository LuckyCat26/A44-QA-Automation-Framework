
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import java.time.Duration;
import java.util.Locale;


public class BaseTest {
    public static WebDriver driver=null;
    public static String url="https://bbb.testpro.io/";
    public static WebDriverWait wait=null;

    @BeforeSuite
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})

    public void setUpBrowser(String BaseURL) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String url=BaseURL;
        openUrl(url);

        //wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //driver.manage().deleteAllCookies();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    public void openUrl(String url) {
        driver.get(url);
    }

    public void waitCommand() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean assertSuccessBanner() {
        WebElement successBanner = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".success")));
        return successBanner.isDisplayed();
    }
}