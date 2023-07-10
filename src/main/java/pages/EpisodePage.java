package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EpisodePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By title = By.cssSelector("h1.Title");

    public EpisodePage(WebDriver driver,WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public String getTitle(){
        return driver.findElement(title).getAttribute("innerText");
    }

}
