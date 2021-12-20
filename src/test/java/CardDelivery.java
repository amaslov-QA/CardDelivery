import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CardDelivery {

    @Test
    public void shouldCheckingTheCorrectForm(){
        open("http://localhost:9999/");
        $("[data-test-id='city']").setValue("Бугульма");
        $("[data-test-id='date']").setValue("22.12.2021");
        $("[data-test-id='name']").setValue("Аркадий Паровозов");
        $("[data-test-id='phone']").setValue("+79112650042");
        $("[data-test-id='agreement']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='notification']").shouldBe(Condition.appear, Duration.ofSeconds(15));

    }
}
