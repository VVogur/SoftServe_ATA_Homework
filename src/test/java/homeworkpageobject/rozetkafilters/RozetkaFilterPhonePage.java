package homeworkpageobject.rozetkafilters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class RozetkaFilterPhonePage {

    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> listPhone;

    By checkboxAppleBy = By.xpath("//label[contains(text(), 'Apple')]");
    By checkboxHuaweiBy = By.xpath("//label[contains(text(), 'Huawei')]");
    By phoneListBy = By.className("goods-tile__title");

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

    public void listPhoneElement() {
        List<WebElement> listPhone = driver.findElements(phoneListBy);
    }

    public void isSamsungHuaweiApplePhone(List<WebElement> mobList) {
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
}







