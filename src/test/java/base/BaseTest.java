package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--no sandbox");
        chromeOpt.addArguments("--disable-dev-shm-usage");
        chromeOpt.addArguments("--window-size=1920,1080");
        chromeOpt.addArguments("--headless");
        driver = new ChromeDriver(chromeOpt);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
