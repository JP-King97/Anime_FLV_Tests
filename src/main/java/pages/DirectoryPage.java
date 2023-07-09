package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DirectoryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By title = By.cssSelector("div.Title > h1");

    public DirectoryPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public String titleCheck(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(title)));
        return driver.findElement(title).getAttribute("innerText");
    }



}
