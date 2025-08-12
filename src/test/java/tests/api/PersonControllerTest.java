package tests.api;

import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import dto.api.user.rq.User;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Slf4j
public class PersonControllerTest {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void createUser() {
        Faker faker = new Faker();
        UsersAdapter usersAdapter = new UsersAdapter();
        User user = User.builder()
                .firstName(faker.name().firstName())
                .secondName(faker.name().lastName())
                .age(faker.number().numberBetween(18, 65))
                .sex(faker.demographic().sex().toUpperCase())
                .money(faker.number().numberBetween(0, 999999))
                .build();
        User rq = usersAdapter.createUser(user);
        softAssert.assertEquals(user.getFirstName(),
                rq.getFirstName(),
                "Значение параметра firstName не соответствует ожидаемому");
        softAssert.assertEquals(user.getSecondName(),
                rq.getSecondName(),
                "Значение параметра secondName не соответствует ожидаемому");
        softAssert.assertEquals(user.getAge(),
                rq.getAge(),
                "Значение параметра age не соответствует ожидаемому");
        softAssert.assertEquals(user.getSex(),
                rq.getSex(),
                "Значение параметра sex не соответствует ожидаемому");
        softAssert.assertEquals(user.getMoney(),
                rq.getMoney(),
                "Значение параметра money не соответствует ожидаемому");
    }
}
