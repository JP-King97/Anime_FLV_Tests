package base;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamStreamer;
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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    public WebDriver driver;
    protected WebDriverWait wait;
    protected HomePage homePg;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();

        chromeOpt.addArguments("--start-maximized");
        chromeOpt.addArguments("--disable-infobars");
        chromeOpt.addArguments("--disable-extensions");

        chromeOpt.addArguments("--disable-gpu");
        chromeOpt.addArguments("--disable-software-rasterizer");

        chromeOpt.addArguments("--no-sandbox");
        chromeOpt.addArguments("--disable-dev-shm-usage");
        chromeOpt.addArguments("--window-size=1920,1080");
        chromeOpt.addArguments("--headless");
        driver = new ChromeDriver(chromeOpt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15000));
        //Webcam webcam = Webcam.getDefault();
        //webcam.setViewSize(WebcamResolution.VGA.getSize()); // Set video resolution
        //webcam.open();
//
        //// Specify the output directory and file format (e.g., AVI)
        //String outputDirectory = "path_to_output_directory";
        //String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //String outputFileName = "video_" + timestamp + ".avi";
        //File outputFilePath = new File(outputDirectory, outputFileName);
//
        //// Initialize the video streamer
        //WebcamStreamer streamer = new WebcamStreamer(webcam, outputFilePath, Webcam);

        driver.get("https://www3.animeflv.net/");
        homePg = new HomePage(driver,wait);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
     //  Webcam webcam = Webcam.getDefault();
     //  webcam.close();
        driver.quit();
    }

}
