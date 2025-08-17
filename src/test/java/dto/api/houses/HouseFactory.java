package dto.api.houses;

import com.github.javafaker.Faker;
import dto.api.users.rq.UserRequest;

import java.util.Collections;
import java.util.Locale;

public class HouseFactory {

    public static House getHouse(String locale) {
        Faker faker = new Faker(new Locale(locale));

        ParkingPlace parkingPlace = ParkingPlace.builder()
                .isWarm(faker.bool().bool())
                .isCovered(faker.bool().bool())
                .placesCount(faker.number().numberBetween(1, 5))
                .build();

        UserRequest lodger = UserRequest.builder()
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 99))
                .sex(faker.options().option("MALE", "FEMALE"))
                .money(faker.number().randomDouble(2, 1000, 100000))
                .build();

        return House.builder()
                .floorCount(faker.number().numberBetween(1, 50))
                .price(faker.number().randomDouble(2, 1000, 1000000))
                .parkingPlaces(Collections.singletonList(parkingPlace))
                .lodgers(Collections.singletonList(lodger))
                .build();
    }
}