package homeworkpages.rozetkacompare;

import homeworkpages.rozetkacompare.pageobject.RozetkaCompareMainPage;
import homeworkpages.rozetkacompare.pageobject.RozetkaCompareMonitorCheckPage;
import homeworkpages.rozetkacompare.pageobject.RozetkaCompareMonitorPage;
import homeworkpages.rozetkacompare.pageobject.RozetkaComparePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RozetkaCompareMainTestPageObject {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private String priceOfFirstProduct;
    private String nameOfFirstProduct;
    private String priceOfSecondProduct;
    private String nameOfSecondProduct;

    private RozetkaCompareMonitorPage rozetkaCompareMonitorPage;
    private RozetkaCompareMonitorCheckPage rozetkaCompareMonitorCheckPage;
    private RozetkaComparePage rozetkaComparePage;
    private RozetkaCompareMainPage rozetkaCompareMainPage;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
        rozetkaCompareMonitorPage = new RozetkaCompareMonitorPage(driver);
        rozetkaCompareMonitorCheckPage = new RozetkaCompareMonitorCheckPage(driver);
        rozetkaComparePage = new RozetkaComparePage(driver);
        rozetkaCompareMainPage = new RozetkaCompareMainPage(driver);
    }

    @Test
    public void test() throws InterruptedException {
        rozetkaCompareMainPage.moveToBurger();
        rozetkaCompareMainPage.waitMonitorLink();
        rozetkaCompareMainPage.clickMonitorLink();
        rozetkaCompareMonitorPage.waitTileMenu();
        rozetkaCompareMonitorPage.waitTilePicture();
        rozetkaCompareMonitorPage.monitorPriceListFirst();
        rozetkaCompareMonitorPage.backPage();
        rozetkaCompareMonitorPage.waitTileMenu();
        rozetkaCompareMonitorPage.waitTilePicture();
        rozetkaCompareMonitorPage.clearLists();
        rozetkaCompareMonitorPage.monitorPriceListSecond();
        rozetkaCompareMonitorCheckPage.clickCompareButton();
        rozetkaCompareMonitorPage.clearLists();
        rozetkaComparePage.listAdd();
        Assert.assertEquals(rozetkaComparePage.getNumberOfProducts(), 2);
        Assert.assertEquals(rozetkaComparePage.getPriceByNumber(1), priceOfFirstProduct);
        Assert.assertEquals(rozetkaComparePage.getNameByNumber(1), nameOfFirstProduct);
        Assert.assertEquals(rozetkaComparePage.getPriceByNumber(2), priceOfSecondProduct);
        Assert.assertEquals(rozetkaComparePage.getNameByNumber(2), nameOfSecondProduct);
    }
}
