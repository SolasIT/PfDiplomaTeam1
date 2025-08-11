package dto;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.Locale;

public class UserFactory {
    public static User getUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String[] gender = {"MALE", "FEMALE"};
        return User.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.options().nextElement(gender))
                .money(faker.number().randomDouble(2, 1000, 100000))
                .build();
    }
}