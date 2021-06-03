package homeworkpages.rozetkafilters;

import homeworkpages.rozetkafilters.pageobject.RozetkaFilterMainPage;
import homeworkpages.rozetkafilters.pageobject.RozetkaFilterPhonePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RozetkaFilterMainTestPageObject {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private RozetkaFilterMainPage rozetkaFilterMainPage;
    private RozetkaFilterPhonePage rozetkaFilterPhonePage;

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
        rozetkaFilterMainPage = new RozetkaFilterMainPage(driver);
        rozetkaFilterPhonePage = new RozetkaFilterPhonePage(driver);
    }

    @Test
    public void test1(){
        rozetkaFilterMainPage.inputSearchWord("samsung");
        rozetkaFilterMainPage.waitPhoneMenu();
        rozetkaFilterMainPage.clickPhoneMenu();
        rozetkaFilterPhonePage.waitClickAppleCheckbox();
        rozetkaFilterPhonePage.clickAppleCheckbox();
        rozetkaFilterPhonePage.waitClickHuaweiCheckbox();
        rozetkaFilterPhonePage.clickHuaweiCheckbox();
        rozetkaFilterPhonePage.waitPhoneList();
        rozetkaFilterPhonePage.isSamsungHuaweiApplePhone("Samsung", "Apple", "Huawei");
    }

    @Test
    public void test2(){
        rozetkaFilterMainPage.inputSearchWord("samsung");
        rozetkaFilterMainPage.waitPhoneMenu();
        rozetkaFilterMainPage.clickPhoneMenu();
        rozetkaFilterPhonePage.minPriceSet();
        rozetkaFilterPhonePage.maxPriceSet();
        rozetkaFilterPhonePage.clickToSlider();
        Assert.assertTrue(rozetkaFilterPhonePage.priceDiapason(5000, 15000));
    }

    @Test
    public void test3(){
        rozetkaFilterMainPage.inputSearchWord("samsung");
        rozetkaFilterMainPage.waitPhoneMenu();
        rozetkaFilterMainPage.clickPhoneMenu();
        rozetkaFilterPhonePage.waitClickXiaomiCheckbox();
        rozetkaFilterPhonePage.clickXiaomiCheckbox();
        rozetkaFilterPhonePage.isSamsungHuaweiApplePhone("Samsung", "Xiaomi");
    }
}
