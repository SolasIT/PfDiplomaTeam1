package dto;

import com.github.javafaker.Faker;
import java.util.Locale;

public class CarFactory {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final String[] VALID_ENGINE_TYPES = {"Gasoline", "Diesel", "Electric"};

    public static Car getCar() {
        return getCar("en");
    }

    public static Car getCar(String locale) {
        Faker localizedFaker = new Faker(new Locale(locale));
        return Car.builder()
                .engineType(faker.options().nextElement(VALID_ENGINE_TYPES))
                .mark(generateValidMark(localizedFaker))
                .model(localizedFaker.lorem().word())
                .price(Double.parseDouble(
                        localizedFaker.commerce().price(5000, 100000).replace(",", ".")))
                .build();
    }

    public static Car getInvalidCar() {
        return Car.builder()
                .engineType("")
                .mark("")
                .model("")
                .price(0)
                .build();
    }

    private static String generateValidMark(Faker faker) {
        return faker.regexify("[A-Z][a-zA-Z0-9]{3,12}");
    }
}