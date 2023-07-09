package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By homeButton = By.cssSelector("ul.Menu > li:nth-child(1) > a");
    private final By directoryButton = By.cssSelector("ul.Menu > li:nth-child(2) > a");

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public HomePage clickHomeButton(){
        driver.findElement(homeButton).click();
        return new HomePage(driver,wait);
    }

    public DirectoryPage clickDirectoryButton(){
        driver.findElement(directoryButton).click();
        return new DirectoryPage(driver,wait);
    }


}
