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

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--no sandbox");
        chromeOpt.addArguments("--disable-dev-shm-usage");
        chromeOpt.addArguments("--window-size=1920,1080");
        chromeOpt.addArguments("--headless");
        driver = new ChromeDriver(chromeOpt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15000));
        driver.get("https://www3.animeflv.net/");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
