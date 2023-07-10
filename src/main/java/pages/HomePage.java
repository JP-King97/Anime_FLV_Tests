package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By homeButton = By.cssSelector("ul.Menu > li:nth-child(1) > a");
    private final By directoryButton = By.cssSelector("ul.Menu > li:nth-child(2) > a");
    private final By homeLogo = By.cssSelector("div.Logo > a");
    private final By animeOnBroadcastList = By.cssSelector("a[class=\"fa-play-circle\"]");
    private final By lastEpisodesList = By.cssSelector("ul.ListEpisodios > li > a");
    private final By lastAddedAnimes = By.cssSelector("ul.ListAnimes  article > a");

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

    public int getNumberOfAnimesInBroadcast(){
        return driver.findElements(animeOnBroadcastList).size();
    }

    //////////////////////////////////
    public int getNumberOfLastEpisodes(){
        return driver.findElements(lastEpisodesList).size();
    }
    public WebElement getLastEpisode(int numberOnList){
        return driver.findElements(lastEpisodesList).get(numberOnList);
    }

    public String getEpNumber_LastEps(int numberOnList){
        return driver.findElements(By.cssSelector("span.Capi")).get(numberOnList).getAttribute("innerText");
    }

    public String getAnimeName_LastEps(int numberOnList){
        return driver.findElements(By.cssSelector("strong.Title")).get(numberOnList).getAttribute("innerText");
    }

    ///////////////////////////////////
    public int getNumberOfAnimesRecentlyAdded(){
        return driver.findElements(lastAddedAnimes).size();
    }

    public WebElement getLastAnimeAdded(int numberOnList){
        return driver.findElements(lastAddedAnimes).get(numberOnList);
    }

    public String getTvType_RecentlyAdded(int numberOnList){
        return driver.findElements(By.cssSelector("ul.ListAnimes a  span.Type.tv")).get(numberOnList).getAttribute("innerText");
    }

    public String getAnimeName_RecentlyAdded(int numberOnList){
        return driver.findElements(By.cssSelector("ul.ListAnimes a  h3.Title")).get(numberOnList).getAttribute("innerText");
    }

}
