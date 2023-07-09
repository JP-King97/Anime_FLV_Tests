package funtional;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
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

}
