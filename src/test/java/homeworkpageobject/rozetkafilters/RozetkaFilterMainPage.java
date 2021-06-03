package homeworkpageobject.rozetkafilters;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaFilterMainPage {
    WebDriver driver;
    WebDriverWait wait;

    By searchFieldBy = By.name("search");
    By searchButtonEnterBy = By.className("search-form__submit");
    By mobPhoneFilterBy = By.xpath("//span[contains(text(), 'Мобильные телефоны')]");

    public RozetkaFilterMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }
    public void inputSearchWord(String search){
        driver.findElement(searchFieldBy).sendKeys(search+ Keys.ENTER);
    }
    public void clickSearchButtonEnter(){
        driver.findElement(searchButtonEnterBy).click();
    }
    public void waitPhoneMenu(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mobPhoneFilterBy));
    }
}
