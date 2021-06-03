package homeworkpages.rozetkafilters.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaFilterPhonePage {

    WebDriver driver;
    WebDriverWait wait;

    By checkboxAppleBy = By.xpath("//label[contains(text(), 'Apple')]");
    By checkboxHuaweiBy = By.xpath("//label[contains(text(), 'Huawei')]");
    By phoneListBy = By.xpath("//div[@class='goods-tile__inner']");
    By productName = By.cssSelector("span.goods-tile__title");

    By minPriceField = By.xpath("//input[@formcontrolname='min']");
    By maxPriceField = By.xpath("//input[@formcontrolname='max']");
    By sliderButtonBy = By.className("slider-filter__button");
    By productPrice = By.cssSelector("span.goods-tile__price-value");

    By checkboxXiaomiBy = By.xpath("//label[contains(text(), 'Xiaomi')]");

    public RozetkaFilterPhonePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void clickAppleCheckbox() {
        driver.findElement(checkboxAppleBy).click();
    }

    public void clickHuaweiCheckbox() {
        driver.findElement(checkboxHuaweiBy).click();
    }

    public void waitClickAppleCheckbox() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkboxAppleBy));
    }

    public void waitClickHuaweiCheckbox() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkboxHuaweiBy));
    }

    public void waitPhoneList() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(phoneListBy));
    }
    public void minPriceSet(){
        driver.findElement(minPriceField).sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        driver.findElement(minPriceField).sendKeys("5000");
    }
    public void maxPriceSet(){
        driver.findElement(maxPriceField).sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        driver.findElement(maxPriceField).sendKeys("15000");
    }
    public void clickToSlider(){
        driver.findElement(sliderButtonBy).click();
    }
    public void clickXiaomiCheckbox(){
        driver.findElement(checkboxXiaomiBy).click();
    }
    public void waitClickXiaomiCheckbox(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkboxXiaomiBy));
    }

    public boolean isSamsungHuaweiApplePhone(String... arr) {

        wait.until(ExpectedConditions.elementToBeClickable(phoneListBy));
        List<WebElement> listOfElements = driver.findElements(productName);
        boolean check = false;
        for(WebElement webElem : listOfElements){
            String nameOfProduct = webElem.getText();
            for(String word : arr){
                check=check || nameOfProduct.contains(word);
            }
            if(!check) {break;}

        }
        return check;
    }

    public boolean priceDiapason(int min, int max){
        wait.until(ExpectedConditions.elementToBeClickable(phoneListBy));
        List<WebElement> listOfElements = driver.findElements(productPrice);
        boolean check = true;
        for(WebElement webElem : listOfElements){
            String priceOfProduct = webElem.getText().replaceAll(" ", "");
            if(Integer.parseInt(priceOfProduct)>max && Integer.parseInt(priceOfProduct)<min) {check =false;}
        }
        return check;
    }
}







