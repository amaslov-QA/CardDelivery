import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    String planningDate = generateDate(5);

    @Test
    public void shouldCheckingTheCorrectForm() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='notification']").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(visible).
                shouldHave(exactText("Встреча успешно забронирована на " + planningDate));

    }
    @Test
    public void shouldCheckingTheFormNoPhone() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }
    @Test
    public void shouldCheckingTheFormNoCity() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
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
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }
    @Test
    public void shouldCheckingTheFormMoreNumberPhone() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+791126500428");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").
                shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    public void shouldCheckingTheFormPhonePlus() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("79112650042+");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='phone'].input_invalid .input__sub").
                shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }    @Test
    public void shouldCheckingTheFormNoAdministrativeCenter() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Бугульма");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Аркадий Паровозов");
        $("[data-test-id='phone'] input").setValue("+79112650042");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='city'].input_invalid .input__sub").
                shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }


}



