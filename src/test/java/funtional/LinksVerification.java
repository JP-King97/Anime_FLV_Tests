package funtional;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AnimePage;
import pages.DirectoryPage;

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
        for(int i=0;i< homePg.numberOfAnimesInBroadcast();i++){
            WebElement animeBC = homePg.getAnimeInBroadcast(i);
            wait.until(ExpectedConditions.visibilityOf(animeBC));
            String animeNameOnList = animeBC.getAttribute("innerText").replace("\n"+"ANIME","");
            animeBC.sendKeys(Keys.ENTER);
            AnimePage iAnimeOnBroadcast = new AnimePage(driver,wait);
            Assert.assertEquals(iAnimeOnBroadcast.getAnimeName(),animeNameOnList,"Name on page is different to the name on the broadcasting list");
            driver.navigate().back();



        }
    }

}
