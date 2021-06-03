package homeworkpages.rozetkacompare.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaCompareMonitorCheckPage {

    WebDriver driver;
    WebDriverWait wait;

    By compareButtonBy = By.className("compare-button");

    public RozetkaCompareMonitorCheckPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void clickCompareButton(){
        driver.findElement(compareButtonBy).click();
    }
}
