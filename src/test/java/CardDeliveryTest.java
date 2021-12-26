import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    @Test
    public void shouldCheckingTheCorrectForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("29.12.2021");
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='notification']").shouldBe(Condition.appear, Duration.ofSeconds(15));

    }
    @Test
    public void shouldCheckingTheFormNoPhone() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("29.12.2021");
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }
    @Test
    public void shouldCheckingTheFormNoCity() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").setValue("29.12.2021");
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }
    @Test
    public void shouldCheckingTheFormNoCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("29.12.2021");
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='agreement'].checkbox_text").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }

}



