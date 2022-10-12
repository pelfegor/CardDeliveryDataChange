import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryDataChangeTest {

    private Faker faker;

    InfoGenerator info = new InfoGenerator();

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
        open("http://localhost:9999/");
    }

    @Test
    public void ShouldReplanningDateTheDayAhead() { // Перепланировка даты доставки на день вперед

        UserInfo user = info.generateUser(5);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + user.getDate()));
        $("[data-test-id='success-notification'] button").click();
        $("[data-test-id='success-notification']").should(hidden);
        ///////////
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(info.generateDate(6));
        $("button .button__text").click();
        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .notification__content button").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + info.generateDate(6)));
    }

    @Test
    public void ShouldReplanningDateTheDayAgo() { // Перепланировка на один день назад

        UserInfo user = info.generateUser(5);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + user.getDate()));
        $("[data-test-id='success-notification'] button").click();
        $("[data-test-id='success-notification']").should(hidden);
        ///////////
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(info.generateDate(4));
        $("button .button__text").click();
        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .notification__content button").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + info.generateDate(4)));
    }

    @Test
    public void ShouldReplanningDateForTheSameDay() { // Перепланировка на тот же день

        UserInfo user = info.generateUser(5);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + user.getDate()));
        $("[data-test-id='success-notification'] button").click();
        $("[data-test-id='success-notification']").should(hidden);
        ///////////
        $("button .button__text").click();
        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .notification__content button").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + user.getDate()));
    }

    @Test
    public void ShouldWarningIfReplanningToInvalidDate() { // Ошибка при перепланировании на невалидную дату

        UserInfo user = info.generateUser(5);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='success-notification'].notification_visible").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification_visible .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification'].notification_visible .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + user.getDate()));
        $("[data-test-id='success-notification'] button").click();
        $("[data-test-id='success-notification']").should(hidden);
        ///////////
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(info.generateDate(1));
        $("button .button__text").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
        }

    @Test
    public void shouldWarningIfInvalidCity() {  // Ошибка при невалдном городе
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue("Piter");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void shouldWarningIfInvalidDate() {  // Ошибка при невалдной дате
        UserInfo user = info.generateUser(1);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void shouldWarningIfEmptyName() { // Ошибка при пустом имени
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldWarningIfInvalidName() {  // Ошибка при невалдном имени
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue("Ivan");
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldWarningIfEmptyPhone() {  // Ошибка при пустом поле номера телефона
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldWarningIfInvalidPhone() {  // Ошибка при невалдном номере телефона
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue("+7123456789");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldWarningIfEmptyCheckbox() {  // Ошибка при пустом чекбоксе
        UserInfo user = info.generateUser(5);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhoneNumber());
        $("button .button__text").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
