package homeworkselenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Wizzair {

    @Test
    public void flySearchResultTest(){
        Configuration.baseUrl = "https://wizzair.com";
        Configuration.timeout = 40000;
        open("/");

        $("#search-departure-station").val("Vienna");
        $x("//mark").click();
        $("#search-arrival-station").val("Kyiv");
        $x("//mark").click();

        $("#search-departure-date").click();
        $x("//button[@data-pika-day='19'][@data-pika-month='1']").click();
        $x("//button[@data-pika-day='26'][@data-pika-month='1']").click();
        $x("//button[@data-test=\"calendar-shrinkable-ok-button\"]").click();
        $("#search-passenger").click();
        $$(".stepper__button--add").get(0).click();
        $x("//button[@data-test=\"flight-search-hide-panel\"]").click();
        $x("//button[@data-test=\"flight-search-submit\"]").click();

        switchTo().window(1);
        $$(".flight-select__fare-selector").shouldHaveSize(2);
        $$(".flight-select__fare-selector").get(0).$("address").shouldHave(Condition.text("Vienna (VIE)   Kyiv - Zhulyany (IEV)"));
        $$(".flight-select__fare-selector").get(1).$("address").shouldHave(Condition.text("Kyiv - Zhulyany (IEV)  Vienna (VIE)"));
        $$(".flight-select__flight-info__day").get(0).shouldHave(Condition.text(" Fri, 19 Feb 2021 "));
        $$(".flight-select__flight-info__day").get(1).shouldHave(Condition.text(" Fri, 26 Feb 2021 "));
    }
}
