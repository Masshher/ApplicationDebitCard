import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ApplicationDebitCardTest {



    @Test
    void shouldRegister() {
        open("http://localhost:9999");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
