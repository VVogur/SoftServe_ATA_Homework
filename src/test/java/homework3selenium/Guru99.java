package homework3selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class Guru99 {
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String userName = "1303";
    String password = "Guru99";
    String incorrectCredsMessage = "User or Password is not valid";
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite(){
        driver.get(initialUrl);
    }

    @Test
    public void testPositiveLogin() {
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();

        WebElement logoutLink = wait.until(presenceOfElementLocated(By.linkText("Log out")));
        logoutLink.click();

        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }

    @Test
    public void testInvalidLogin() {
        driver.findElement(By.name("uid")).sendKeys("qwerty");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }

    @Test
    public void testInvalidPassword() {
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys("qwert");
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }

    @Test
    public void testInvalidLoginAndPassword() {
        driver.findElement(By.name("uid")).sendKeys("qwerty");
        driver.findElement(By.name("password")).sendKeys("qwert");
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }

    @Test
    public void testInvalidPassword1() {
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys("~~~");
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
    @Test
    public void testInvalidLogin1() {
        driver.findElement(By.name("uid")).sendKeys("~~~");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        String resultUrl = driver.getCurrentUrl();
        assertEquals(resultUrl, initialUrl);
    }
}
