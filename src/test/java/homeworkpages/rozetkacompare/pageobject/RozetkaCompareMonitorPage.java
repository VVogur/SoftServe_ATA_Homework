package homeworkpages.rozetkacompare.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class RozetkaCompareMonitorPage {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> listOfElements, listOfImg;
    String priceFirst;
    String nameFirst;
    String priceSecond;
    String nameSecond;

    By goodsTileMenuBy = By.cssSelector("div.goods-tile__inner");
    By goodsTilePicturesBy = By.className("goods-tile__picture");
    By goodsPriceBy = By.cssSelector("span.goods-tile__price-value");

    public RozetkaCompareMonitorPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitTileMenu() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(goodsTileMenuBy));
    }

    public void waitTilePicture() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(goodsTilePicturesBy));
    }

    public void monitorPriceListFirst() throws InterruptedException {

        listOfElements = driver.findElements(goodsPriceBy);
        listOfImg = driver.findElements(goodsTilePicturesBy);

        for (int i = 0; i < listOfElements.size(); i++) {

            String str = listOfElements.get(i).getText().replaceAll(" ", "");

            if (Integer.parseInt(str) < 4000) {
                listOfImg.get(i).click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("compare-button")));
                driver.findElement(By.className("compare-button")).click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span.counter")));
                Thread.sleep(200);
                Assert.assertEquals(driver.findElement(By.cssSelector("span.counter")).getText(), "1");
                priceFirst = str;
                System.out.println(priceFirst);
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product__title")));
                nameFirst = driver.findElement(By.className("product__title")).getText();
                break;
            }
        }
    }

    public void monitorPriceListSecond() throws InterruptedException {

        listOfElements = driver.findElements(goodsPriceBy);
        listOfImg = driver.findElements(goodsTilePicturesBy);

        for (int i = 0; i < listOfElements.size(); i++) {

            String str = listOfElements.get(i).getText().replaceAll(" ", "");

            if (Integer.parseInt(str) < Integer.parseInt(priceFirst)) {
                listOfImg.get(i).click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("compare-button")));
                driver.findElement(By.className("compare-button")).click();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span.counter")));
                Thread.sleep(200);
                Assert.assertEquals(driver.findElement(By.cssSelector("span.counter")).getText(), "2");
                priceSecond = str;
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product__title")));
                nameSecond = driver.findElement(By.className("product__title")).getText();
                break;
            }
        }
    }

    public void clearLists() {
        listOfElements.clear();
        listOfImg.clear();
    }

    public void backPage(){
        driver.navigate().back();
    }

}
