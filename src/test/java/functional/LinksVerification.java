package functional;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AnimePage;
import pages.DirectoryPage;
import pages.EpisodePage;

public class LinksVerification extends BaseTest {

    @Test
    public void homeButtonLink(){
        homePg.clickHomeButton();
        String title = homePg.checkHeaderPage();
        Assert.assertEquals(title,"Ver Anime Online HD — AnimeFLV","Page title incorrect");
    }

    @Test
    public void directoryButtonLink(){
        DirectoryPage directoryPg = homePg.clickDirectoryButton();
        String title = directoryPg.titleCheck();
        Assert.assertEquals(title,"Lista completa de Animes");
    }

    @Test
    public void homeLogoLink(){
        homePg.clickHomeLogo();
        String title = homePg.checkHeaderPage();
        Assert.assertEquals(title,"Ver Anime Online HD — AnimeFLV","Page title incorrect");
    }

    @Test
    public void checkBroadcastingAnimesLinks(){
        for(int i = 0; i< homePg.getNumberOfAnimesInBroadcast(); i++){
            WebElement animeBC = homePg.getAnimeInBroadcast(i);
            wait.until(ExpectedConditions.visibilityOf(animeBC));
            String animeNameOnList = animeBC.getAttribute("innerText");
            if (animeNameOnList.contains("ANIME")){
                animeNameOnList = animeBC.getAttribute("innerText").replace("\n" + "ANIME","");
            }else{
                animeNameOnList = animeBC.getAttribute("innerText").replace("\n" + "OVA","");
            }
            animeBC.sendKeys(Keys.ENTER);
            AnimePage iAnimeOnBroadcast = new AnimePage(driver,wait);
            Assert.assertEquals(iAnimeOnBroadcast.getAnimeName(),animeNameOnList,"Name on page is different to the name on the broadcasting list");
            driver.navigate().back();
        }
    }

    @Test
    public void checkLastEpisodes(){
        for(int i=0; i<homePg.getNumberOfLastEpisodes();i++){
            WebElement lastEpisode = homePg.getLastEpisode(i);
            wait.until(ExpectedConditions.visibilityOf(lastEpisode));
            String animeEp = homePg.getEpNumber_LastEps(i);
            String animeName = homePg.getAnimeName_LastEps(i);
            lastEpisode.sendKeys(Keys.ENTER);
            EpisodePage animeEpPg = new EpisodePage(driver,wait);
            Assert.assertEquals(animeEpPg.getTitle(),animeName+" "+animeEp,"Episode title different from home page last episodes");
            driver.navigate().back();
        }
    }

    @Test
    public void checkLastAnimesAdded(){
        for(int i=0 ;i<homePg.getNumberOfAnimesRecentlyAdded();i++){
            WebElement lastAnimeAdded = homePg.getLastAnimeAdded(i);
            wait.until(ExpectedConditions.visibilityOf(lastAnimeAdded));
            String animeTvType = homePg.getTvType_RecentlyAdded(i);
            String animeName = homePg.getAnimeName_RecentlyAdded(i);
            lastAnimeAdded.sendKeys(Keys.ENTER);
            AnimePage animeRecentlyAdded = new AnimePage(driver,wait);
            Assert.assertEquals(animeRecentlyAdded.getAnimeName(),animeName,"Anime name is different from home page animes recently added");
            driver.navigate().back();
        }
    }

}

