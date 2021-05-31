package homework3selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RozetkaFirst {
    String initialUrl = "https://rozetka.com.ua/";
    List<WebElement> listOfElements, listOfImg;
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    String priceFirst;
    String nameFirst;
    String priceSecond;
    String nameSecond;


    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    @AfterClass
    public void closeBrowser() {
         driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
    }

    @Test
    public void testSearch() throws InterruptedException {
        WebElement pc = driver.findElement(By.xpath("//sidebar-fat-menu//a[contains (@href, 'computers-notebooks')]"));
        actions.moveToElement(pc).build().perform();
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]")));
        driver.findElement(By.xpath("//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.goods-tile__inner")));
        listOfElements = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("goods-tile__picture")));
        listOfImg = driver.findElements(By.className("goods-tile__picture"));

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

        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.goods-tile__inner")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("goods-tile__picture")));
        listOfElements.clear();
        listOfImg.clear();
        listOfElements = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
        listOfImg = driver.findElements(By.className("goods-tile__picture"));
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

        driver.findElement(By.className("compare-button")).click();
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("comparison-modal__link")));
        //driver.findElement(By.className("comparison-modal__link")).click();

        listOfElements.clear();
        listOfElements=driver.findElements(By.cssSelector("li.products-grid__cell"));
        Assert.assertEquals(listOfElements.size(), 2);
        Collections.reverse(listOfElements);

        String price1= listOfElements.get(0).findElement(By.xpath(".//div[@class='product__prices']/div")).getText();
        String oldPrice = listOfElements.get(0).findElement(By.xpath(".//div[@class='product__prices']/div/div")).getText();
        price1=(price1.substring(oldPrice.length())).replaceAll("[ ₴]", "").trim();

        String price2= listOfElements.get(1).findElement(By.xpath(".//div[@class='product__prices']/div")).getText();
        oldPrice = listOfElements.get(1).findElement(By.xpath(".//div[@class='product__prices']/div/div")).getText();
        price2=(price2.substring(oldPrice.length())).replaceAll("[ ₴]", "").trim();

        String name1=listOfElements.get(0).findElement(By.xpath(".//a")).getText();
        String name2=listOfElements.get(1).findElement(By.xpath(".//a")).getText();

        Assert.assertEquals(price1, priceFirst);
        Assert.assertEquals(price2, priceSecond);
        Assert.assertEquals(name1, nameFirst);
        Assert.assertEquals(name2, nameSecond);



        }

    }

