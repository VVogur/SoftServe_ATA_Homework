package homework3selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoogleSearch {
    String initialUrl = "https://www.google.com.ua/";
    List<WebElement> listOfElements;
    WebDriver driver;
    WebDriverWait wait;
    boolean elementFound = false;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
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
    public void testSearch() {

        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("iphone kyiv buy" + Keys.ENTER);

        for (int i = 0; i < 5; i++) {
            listOfElements = driver.findElements(By.xpath("//cite[contains(text(), 'stylus.ua')]"));
            if (!listOfElements.isEmpty()) {
                System.out.println("STYLUS.UA found on " + (i + 1) + " page");
                elementFound = true;
                break;
            }
            driver.findElement(By.id("pnnext")).click();
        }
        assertTrue(elementFound, "STYLUS.UA not found on first 5 pages");

    }


}
