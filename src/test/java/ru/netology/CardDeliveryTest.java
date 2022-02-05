package ru.netology;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;



public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldSendForm() {
        String planningDate = generateDate(4);
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Белгород");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $$("[data-day]").get(1).click();
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79008765432");
        $(".checkbox__box").click();
        $("button.button").click();
        $("[data-test-id='notification']").should(appear, Duration.ofSeconds(20));
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }
}

