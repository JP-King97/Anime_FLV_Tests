package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnimePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By animeName = By.cssSelector("h1[class=\"Title\"]");

    public AnimePage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
    }

    public String getAnimeName(){
        return driver.findElement(animeName).getText();
    }
}
