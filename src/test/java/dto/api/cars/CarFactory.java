package dto.api.cars;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CarFactory {
    public static Car getCar(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String[] engineType = {"Diesel", "Electric", "Gasoline"};
        String[] model = {"X5", "A7", "A3", "Q2", "Q1", "Q7", "Z3", "Z7", "X7", "A7", "S7", "WW123", "W564"};
        String[] mark = {"BMW", "Volvo", "Volkswagen", "Toyota", "Skoda", "Renault", "Peugeot", "Opel"};
        return Car.builder()
                .engineType(faker.options().nextElement(engineType))
                .mark(faker.options().nextElement(mark))
                .model(faker.options().nextElement(model))
                .price(faker.number().randomDouble(2, 100, 5000))
                .build();
    }
}