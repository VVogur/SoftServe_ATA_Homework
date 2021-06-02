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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.security.Key;

public class Gmail {
    String initialUrl = "https://mail.google.com";
    String login = "sstest978@gmail.com";
    String password = "ssautotest978";
    String subject = "test subject";
    String message = "text for test";
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
        // driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
    }

    @Test
    public void test() throws AWTException, InterruptedException {
        driver.findElement(By.name("identifier")).sendKeys(login + Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("password")));
        Thread.sleep(1500);
        driver.findElement(By.name("password")).sendKeys(password + Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(), 'Написать')]")));
        driver.findElement(By.xpath("//div[contains(text(), 'Написать')]")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("to")));
        driver.findElement(By.name("to")).sendKeys(login + Keys.TAB + Keys.TAB + subject + Keys.TAB + message);

        driver.findElement(By.cssSelector("[command=Files]>div")).click();


        StringSelection ss = new StringSelection("C:\\qweqwe1.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[aria-label=\"Удалить прикрепленный файл\"]")));
        driver.findElement(By.xpath("//div[@class='dC']//div[contains(text(), 'Отправить')]")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[role='gridcell']")));
        driver.findElement(By.cssSelector("[role='gridcell']")).click();
        driver.navigate().refresh();
        Thread.sleep(1500);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='"+login+"']")).getText().replaceAll("[<>]", ""), login);
        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='"+subject+"']")).getText(), subject);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id]/div[@dir]")).getText(), message);
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id][text()='qweqwe1.txt']")).isDisplayed());

    }
}
