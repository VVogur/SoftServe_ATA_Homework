package homework3selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;


public class Guru99Frame {

    String initialUrl = "http://demo.guru99.com/test/guru99home";

    WebDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void setupBrowser() throws AWTException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        Robot robot = new Robot();
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
    public void frameTest() throws AWTException, InterruptedException {
        driver.manage().window().maximize();
        driver.findElements(By.tagName("iframe")).size();

        WebElement elementX = driver.findElement(By.className("rt-logo-block"));
        Point locationX = elementX.getLocation();
        int x1 = locationX.getX();
        int y1 = locationX.getY();

        driver.switchTo().frame(0);
        WebElement elementY = driver.findElement(By.className("ytp-large-play-button"));

        Point locationY = elementY.getLocation();
        int x2 = locationY.getX();
        int y2 = locationY.getY();

        elementY.click();

        Robot robot = new Robot();

        for (int i = 0; i < 2; i++) {
            //robot.mouseMove(x1, y1);
            //Thread.sleep(2000);
            //robot.mouseMove(x2, y2);
            //Thread.sleep(2000);
            robot.mouseMove(1500, 500);
            WebElement button = driver.findElement(By.className("ytp-play-button"));
            if (button.isDisplayed()){
                Assert.assertTrue(button.isDisplayed());
            }
            Thread.sleep(3000);
            robot.mouseMove(800, 500);
            Thread.sleep(1000);
            if (button.isDisplayed()){
                Assert.assertFalse(button.isDisplayed());
            }
            Thread.sleep(3000);
        }



    }
}