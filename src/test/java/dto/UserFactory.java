package dto;

import com.github.javafaker.Faker;

public class UserFactory {
    public static User getUser() {
        Faker faker = new Faker();
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