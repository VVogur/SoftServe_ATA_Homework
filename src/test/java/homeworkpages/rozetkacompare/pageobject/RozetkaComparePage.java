package homeworkpages.rozetkacompare.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class RozetkaComparePage {
    WebDriver driver;
    List<WebElement> listOfElements;

    By setOfPrices = By.xpath(".//div[@class='product__prices']/div");
    By priceWithoutDiscount = By.xpath(".//div[@class='product__prices']/div/div");
    By qwe = By.cssSelector("li.products-grid__cell");
    By nameOfProduct = By.xpath(".//a");

    public RozetkaComparePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public int getNumberOfProducts(){
        return driver.findElements(qwe).size();
    }

    public String getPriceByNumber(int number) {
        listOfElements = driver.findElements(qwe);
        Collections.reverse(listOfElements);

        String actualPrice = listOfElements.get(number-1).findElement(setOfPrices).getText();
        String oldPrice = listOfElements.get(number-1).findElement(priceWithoutDiscount).getText();
        actualPrice = (actualPrice.substring(oldPrice.length())).replaceAll("[ ₴]", "").trim();

        return actualPrice;
    }

    public String getNameByNumber(int number) {
        listOfElements = driver.findElements(qwe);
        Collections.reverse(listOfElements);
        return listOfElements.get(number-1).findElement(nameOfProduct).getText();
    }
    public void listAdd (){
        listOfElements=driver.findElements(qwe);
    }
}
