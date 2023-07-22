import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ApplicationDebitCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test//Тест проходит
    void positiveScenarioShouldRegister() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test//Пустая строка Фамилия и имя
    void negativeScenarioNameEmptyField() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"name\"].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test// Имя введено на латинице
    void negativeScenarioNameInputInLatin() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"name\"].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test// В имени есть знак препинания
    void negativeScenarioNameInputWithCharacters() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван.");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"name\"].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test // Строка телефон пустая
    void negativeScenarioPhoneEmptyField() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test // Телефон без знака плюс
    void negativeScenarioPhoneNoPlusSign() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("79999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test // Телефон состоит из 10 цифр
    void negativeScenarioPhoneTenDigits() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+7999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test // Телефон состоит из 12 цифр
    void negativeScenarioPhoneTwelveDigits() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+799999999999");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test // знаке в строке телефон
    void negativeScenarioPhoneWithSigns() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+7(999)999-99-99");
        form.$("[data-test-id=\"agreement\"]").click();
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test // Не поставлен чек бокс
    void negativeScenarioWithOutCheckbox() {
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=\"name\"] input").setValue("Иванов Иван");
        form.$("[data-test-id=\"phone\"] input").setValue("+79999999999");
        form.$(".button_theme_alfa-on-white").click();
        form.$("[data-test-id=\"agreement\"]").should(cssClass("input_invalid"));
    }
}
