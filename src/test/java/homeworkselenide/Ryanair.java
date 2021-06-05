package homeworkselenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Ryanair {

    @Test
    public void flySearchResultTest(){

        Configuration.baseUrl = "https://www.ryanair.com";
        open("/");
        $x("//button[text()=\"Так, я згоден(-на)\"]").click();

        $("icon.language-selector__button-chevron").click();
        $("a[href='/us/en']").click();
        $("#input-button__departure").clear();
        $("#input-button__departure").val("Kyiv");
        $(".b2.airport-item").click();
        $("#input-button__destination").val("Vienna");
        $(".b2.airport-item").click();

        $("div[data-id='2021-02-20']").click();
        $("div[data-id='2021-02-23']").click();
        $$("div.counter>div:nth-child(3)").get(0).click();
        $("button[data-ref='flight-search-widget__cta']").click();

        $$(".journeys-wrapper>div").shouldHaveSize(2);
        $$(".journeys-wrapper>div").get(0).$("h3").shouldHave(Condition.text(" Kyiv to Vienna "));
        $$(".journeys-wrapper>div").get(1).$("h3").shouldHave(Condition.text(" Vienna to Kyiv "));
        $$(".journeys-wrapper>div").get(0).$(".date-item__day-of-month--selected").shouldHave(Condition.text("20"));
        $$(".journeys-wrapper>div").get(0).$(".date-item__month--selected").shouldHave(Condition.text("Feb"));
        $$(".journeys-wrapper>div").get(1).$(".date-item__day-of-month--selected").shouldHave(Condition.text("23"));
        $$(".journeys-wrapper>div").get(1).$(".date-item__month--selected").shouldHave(Condition.text("Feb"));

    }
}
