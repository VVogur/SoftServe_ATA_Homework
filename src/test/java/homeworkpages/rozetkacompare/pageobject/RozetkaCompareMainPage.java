package homeworkpages.rozetkacompare.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaCompareMainPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    By burgerMenuBy = By.xpath("//sidebar-fat-menu//a[contains (@href, 'computers-notebooks')]");
    By monitorLinkBy = By.xpath("//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]");

    public RozetkaCompareMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }
    public void moveToBurger(){
        WebElement pc = driver.findElement(burgerMenuBy);
        actions.moveToElement(pc).build().perform();
    }
    public void waitMonitorLink(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(monitorLinkBy));
    }
    public void clickMonitorLink(){
        driver.findElement(monitorLinkBy);
    }

}
