package Generator;

import User.UserInfo;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InfoGenerator {

    public static UserInfo generateUser(int days) {
        return new UserInfo(generateCity(), generateDate(days), generateName(), generatePhoneNumber());
    }

    public static UserInfo generateUserWithInvalidName(int days) {
        return new UserInfo(generateCity(), generateDate(days), generateInvalidName(), generatePhoneNumber());
    }

    public static UserInfo generateUserWithInvalidCity(int days) {
        return new UserInfo(generateInvalidCity(), generateDate(days), generateName(), generatePhoneNumber());
    }
    public static UserInfo generateUserWithInvalidPhone(int days) {
        return new UserInfo(generateCity(), generateDate(days), generateName(), generateInvalidPhoneNumber());
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhoneNumber() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {

        String[] cities = new String[] {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Донецк", "Магас", "Нальчик", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Луганск", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань"};

        Faker faker = new Faker(new Locale("ru"));
        return cities[faker.number().numberBetween(0, cities.length - 1)];
    }

    public static String generateInvalidCity() {
        Faker faker = new Faker(new Locale("en"));
        return faker.address().cityName();
    }

    public static String generateInvalidName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generateInvalidPhoneNumber() {
        Faker faker = new Faker(new Locale("arm"));
        return faker.phoneNumber().phoneNumber();
    }

}