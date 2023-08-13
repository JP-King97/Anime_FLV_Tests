package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected WebDriverWait wait;
    protected HomePage homePg;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--no-sandbox");
        chromeOpt.addArguments("--disable-dev-shm-usage");
        chromeOpt.addArguments("--window-size=1920,1080");
        //chromeOpt.addArguments("--headless");
        driver = new ChromeDriver(chromeOpt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15000));
        driver.get("https://www3.animeflv.net/");
        homePg = new HomePage(driver,wait);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
