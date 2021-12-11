package ru.netology;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;



public class CardDeliveryTest {
    @Test
    void shouldSendForm() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Белгород");
        $("[data-test-id='date'] button.icon-button").click();
        $$("[data-day]").get(1).click();
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79008765432");
        $(".checkbox__box").click();
        $$("button").get(1).click();
        $("[data-test-id='notification']").should(appear, Duration.ofSeconds(20));
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        $("[class='notification__content']").shouldHave(text("Встреча успешно забронирована на " + $("[data-test-id=date] input").getValue()));
    }
}

