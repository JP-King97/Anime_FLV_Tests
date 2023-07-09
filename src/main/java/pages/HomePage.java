package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By homeButton = By.cssSelector("ul.Menu > li:nth-child(1) > a");
    private final By directoryButton = By.cssSelector("ul.Menu > li:nth-child(2) > a");
    private final By homeLogo = By.cssSelector("div.Logo > a");
    private final By animeOnBroadcastList = By.cssSelector("a[class=\"fa-play-circle\"]");

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public HomePage clickHomeButton(){
        //wait.until(ExpectedConditions.elementToBeClickable(homeButton));
        driver.findElement(homeButton).sendKeys(Keys.ENTER);
        return new HomePage(driver,wait);
    }

    public String checkHeaderPage(){
        return driver.getTitle();
    }

    public DirectoryPage clickDirectoryButton(){
        driver.findElement(directoryButton).sendKeys(Keys.ENTER);
        return new DirectoryPage(driver,wait);
    }

    public HomePage clickHomeLogo(){
        driver.findElement(homeLogo).click();
        return new HomePage(driver,wait);
    }

    public WebElement getAnimeInBroadcast(int numberOnList){
        return driver.findElements(animeOnBroadcastList).get(numberOnList);
    }

    public int numberOfAnimesInBroadcast(){
        return driver.findElements(animeOnBroadcastList).size();
    }


}
