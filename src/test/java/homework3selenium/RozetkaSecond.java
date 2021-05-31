package homework3selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaSecond {
    String initialUrl = "https://rozetka.com.ua/";
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> listPhone;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
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
    public void testFirst() {
        driver.findElement(By.name("search")).sendKeys("samsung");
        driver.findElement(By.className("search-form__submit")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")));
        driver.findElement(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Apple')]")));
        driver.findElement(By.xpath("//label[contains(text(), 'Apple')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Huawei')]")));
        driver.findElement(By.xpath("//label[contains(text(), 'Huawei')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='goods-tile__inner']")));
        listPhone = driver.findElements(By.className("goods-tile__title"));


        for (int i = 0; i < listPhone.size(); i++) {

            String str = listPhone.get(i).getText();
            boolean a = false;
            if (str.contains("Apple")) {
                a = str.contains("Apple");
            } else if (str.contains("Samsung")) {
                a = str.contains("Samsung");
            } else if (str.contains("Huawei")) {
                a = str.contains("Huawei");
            }

            Assert.assertTrue(a);
        }
    }

    @Test
    public void testSecond() {
        driver.findElement(By.name("search")).sendKeys("samsung");
        driver.findElement(By.className("search-form__submit")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")));
        driver.findElement(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")).click();

        driver.findElement(By.xpath("//input[@formcontrolname='min']")).sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@formcontrolname='min']")).sendKeys("5000");

        driver.findElement(By.xpath("//input[@formcontrolname='max']")).sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@formcontrolname='max']")).sendKeys("15000");

        driver.findElement(By.className("slider-filter__button")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.goods-tile__inner")));
        listPhone = driver.findElements(By.cssSelector("span.goods-tile__price-value"));

        for (int i = 0; i < listPhone.size(); i++) {

            String str = listPhone.get(i).getText().replaceAll(" ", "");
            boolean a = false;
            if (Integer.parseInt(str) > 5000) {
                a = Integer.parseInt(str) > 5000;
            } else if (Integer.parseInt(str) < 15000) {
                a = Integer.parseInt(str) < 15000;
            }


            Assert.assertTrue(a);

        }
    }

    @Test
    public void testThird() {
        driver.findElement(By.name("search")).sendKeys("samsung");
        driver.findElement(By.className("search-form__submit")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")));
        driver.findElement(By.xpath("//span[contains(text(), 'Мобильные телефоны')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Xiaomi')]")));
        driver.findElement(By.xpath("//label[contains(text(), 'Xiaomi')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='goods-tile__inner']")));
        listPhone = driver.findElements(By.className("goods-tile__title"));

        for (int i = 0; i < listPhone.size(); i++) {

            String str = listPhone.get(i).getText();
            boolean a = false;
            if (str.contains("Xiaomi")) {
                a = str.contains("Xiaomi");
            } else if (str.contains("Samsung")) {
                a = str.contains("Samsung");
            }

            Assert.assertTrue(a);
        }
    }
}


