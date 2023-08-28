package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.MovieWriter;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import videoRecorder.SpecializedScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class BaseTest {
    public WebDriver driver;
    protected WebDriverWait wait;
    protected HomePage homePg;
    private SpecializedScreenRecorder specializedScreenRecorder;
    private String fileName;
    //Metodo para detener la grabación
    private void stopRecording() throws IOException {
        this.specializedScreenRecorder.stop();
    }

    //Metodo para Empezar la grabación, le especificamos la ruta donde quiero que lo guarde con el "String videoPath"
    private void startRecording(String videoPath) throws IOException, AWTException {

        //Seleccionamos File java.IO
        //Creamos un archivo que se va a guardar en una ruta "Path"
        File file = new File(videoPath);

        //Esta variable ayuda a saber la dimension de la pantalla
        //Dimension java.awt
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Creo variable de altura y ancho
        int width = screenSize.width;
        int height = screenSize.height;

        //Creamos un objeto de tipo Rectangulo para capturar el tamaño
        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        //Especificamos las caracteristicas de mi formato de video
        this.specializedScreenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG,
                        CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey, Rational.valueOf(10),
                        QualityKey,1.0f, KeyFrameIntervalKey, 1),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(1)),
                null, file, "ScreenRecorder");
        //Empezamos la grabación con las caracteristicas del formato previamente establecidas
        this.specializedScreenRecorder.start();
        this.fileName = specializedScreenRecorder.getFileName();
    }

    @Test
    public void testWithVideo() {
        // Your test logic here

        // Attach the video to the test case
        Allure.addAttachment(fileName, "video/avi", String.valueOf(new File("/video/"+fileName)));
    }



    @BeforeSuite
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
        //chromeOpt.addArguments("--window-size=1920,1080");
        //chromeOpt.addArguments("--headless");
        driver = new ChromeDriver(chromeOpt);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15000));

        try {
            startRecording(System.getProperty("user.dir")+"\\video\\");
        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }

        driver.get("https://www3.animeflv.net/");
        homePg = new HomePage(driver,wait);
        driver.manage().window().maximize();
    }



    @AfterSuite
    public void tearDown(){
        try {
            stopRecording();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }

}
